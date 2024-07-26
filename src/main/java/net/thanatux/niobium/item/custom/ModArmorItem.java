package net.thanatux.niobium.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thanatux.niobium.Niobium;
import net.thanatux.niobium.item.ModArmorMaterials;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Niobium.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            ImmutableMap.of(ModArmorMaterials.NIOBIUM, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1, false, false, true));

    public ModArmorItem(ArmorMaterial material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
        MinecraftForge.EVENT_BUS.register(this); // Registrar esta classe para eventos Forge
    }

    // Método que será chamado durante o tick do jogador
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.player != null) {
            for (ItemStack stack : event.player.getArmorSlots()) {
                if (stack.getItem() instanceof ModArmorItem) {
                    ModArmorItem armorItem = (ModArmorItem) stack.getItem();
                    if (armorItem.hasFullSuitOfArmorOn(event.player)) {
                        armorItem.evaluateArmorEffects(event.player);
                    }
                }
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().armor.get(0);
        ItemStack leggings = player.getInventory().armor.get(1);
        ItemStack chestplate = player.getInventory().armor.get(2);
        ItemStack helmet = player.getInventory().armor.get(3);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        ItemStack[] armor = new ItemStack[]{
                player.getInventory().armor.get(0),
                player.getInventory().armor.get(1),
                player.getInventory().armor.get(2),
                player.getInventory().armor.get(3)
        };

        ArmorItem boots = (ArmorItem) armor[0].getItem();
        ArmorItem leggings = (ArmorItem) armor[1].getItem();
        ArmorItem chestplate = (ArmorItem) armor[2].getItem();
        ArmorItem helmet = (ArmorItem) armor[3].getItem();

        return helmet.getMaterial() == material &&
                chestplate.getMaterial() == material &&
                leggings.getMaterial() == material &&
                boots.getMaterial() == material;
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }
    }
}
