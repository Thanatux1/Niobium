package net.thanatux.niobium.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thanatux.niobium.Niobium;
import net.thanatux.niobium.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS  =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Niobium.MOD_ID);

    public static final  RegistryObject<Block> DEEPSLATE_NIOBIUM_ORE = registerBlock("deepslate_niobium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> NIOBIUM_BLOCK = registerBlock("niobium_block",
    () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;

    }

    private static <T extends Block>RegistryObject registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));

    }

    public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
    }
}
