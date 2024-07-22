package net.thanatux.niobium.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thanatux.niobium.Niobium;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Niobium.MOD_ID);

    public static final RegistryObject<Item> NIOBIUM_INGOT = ITEMS.register("niobium_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_SCRAP = ITEMS.register("niobium_scrap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NIOBIUM_FRAGMENT = ITEMS.register("niobium_fragment",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
