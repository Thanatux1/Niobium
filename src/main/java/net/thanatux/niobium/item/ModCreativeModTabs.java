package net.thanatux.niobium.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thanatux.niobium.Niobium;
import net.thanatux.niobium.block.ModBlocks;

import javax.swing.*;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Niobium.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NIOBIUM_TAB = CREATIVE_MODE_TABS.register("niobium_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NIOBIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.niobium_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.NIOBIUM_INGOT.get());
                        output.accept(ModItems.NIOBIUM_SCRAP.get());
                        output.accept(ModItems.NIOBIUM_FRAGMENT.get());

                        output.accept(ModBlocks.DEEPSLATE_NIOBIUM_ORE.get());
                        output.accept(ModBlocks.NIOBIUM_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
