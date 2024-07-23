package net.thanatux.niobium.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thanatux.niobium.Niobium;
import net.thanatux.niobium.item.custom.NiobiumMetalDetectorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Niobium.MOD_ID);

    public static final RegistryObject<Item> NIOBIUM_INGOT = ITEMS.register("niobium_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_SCRAP = ITEMS.register("niobium_scrap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_FRAGMENT = ITEMS.register("niobium_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_METAL_DETECTOR = ITEMS.register("niobium_metal_detector",
            () -> new NiobiumMetalDetectorItem(new Item.Properties().durability(35)));

    public static final RegistryObject<Item> NIOBIUM_SWORD = ITEMS.register("niobium_sword",
            () -> new SwordItem(ModToolTiers.NIOBIUM, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_PICKAXE = ITEMS.register("niobium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.NIOBIUM, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_AXE = ITEMS.register("niobium_axe",
            () -> new AxeItem(ModToolTiers.NIOBIUM, 7, 1, new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_HOE = ITEMS.register("niobium_hoe",
            () -> new HoeItem(ModToolTiers.NIOBIUM, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_SHOVEL = ITEMS.register("niobium_shovel",
            () -> new ShovelItem(ModToolTiers.NIOBIUM, 0, 0, new Item.Properties()));

    public static final RegistryObject<Item> NIOBIUM_HELMET = ITEMS.register("niobium_helmet",
            () -> new ArmorItem(ModArmorMaterials.NIOBIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_CHESTPLATE = ITEMS.register("niobium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.NIOBIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_LEGGINGS = ITEMS.register("niobium_leggings",
            () -> new ArmorItem(ModArmorMaterials.NIOBIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_BOOTS = ITEMS.register("niobium_boots",
            () -> new ArmorItem(ModArmorMaterials.NIOBIUM, ArmorItem.Type.BOOTS, new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
