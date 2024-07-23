package net.thanatux.niobium.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.thanatux.niobium.Niobium;
import net.thanatux.niobium.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier NIOBIUM = TierSortingRegistry.registerTier(
            new ForgeTier(4, 1821, 8.5f, 3.5f, 13,
                    ModTags.Blocks.NEEDS_NIOBIUM_TOOL, () -> Ingredient.of(ModItems.NIOBIUM_INGOT.get())),
            new ResourceLocation(Niobium.MOD_ID, "niobium_ingot"), List.of(Tiers.DIAMOND), List.of());
}
