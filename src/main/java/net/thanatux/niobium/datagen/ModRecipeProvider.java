package net.thanatux.niobium.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.thanatux.niobium.Niobium;
import net.thanatux.niobium.block.ModBlocks;
import net.thanatux.niobium.item.ModItems;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> NIOBIUM_SMELTABLES = List.of(ModItems.NIOBIUM_SCRAP.get(),
            ModBlocks.DEEPSLATE_NIOBIUM_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, NIOBIUM_SMELTABLES, RecipeCategory.MISC, ModItems.NIOBIUM_FRAGMENT.get(), 0.25f, 200, "niobium");
        oreBlasting(pWriter, NIOBIUM_SMELTABLES, RecipeCategory.MISC, ModItems.NIOBIUM_FRAGMENT.get(), 0.25f, 100, "niobium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NIOBIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.NIOBIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.NIOBIUM_INGOT.get()), has(ModItems.NIOBIUM_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NIOBIUM_METAL_DETECTOR.get())
                .pattern("  S")
                .pattern(" R ")
                .pattern("NNN")
                .define('N', ModItems.NIOBIUM_INGOT.get())
                .define('R', Items.REDSTONE)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.NIOBIUM_INGOT.get()), has(ModItems.NIOBIUM_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NIOBIUM_AXE.get())
                .pattern("NI ")
                .pattern("NS ")
                .pattern(" S ")
                .define('N', ModItems.NIOBIUM_INGOT.get())
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.NIOBIUM_INGOT.get()), has(ModItems.NIOBIUM_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NIOBIUM_PICKAXE.get())
                .pattern("NIN")
                .pattern(" S ")
                .pattern(" S ")
                .define('N', ModItems.NIOBIUM_INGOT.get())
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.NIOBIUM_INGOT.get()), has(ModItems.NIOBIUM_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NIOBIUM_SWORD.get())
                .pattern(" N ")
                .pattern(" I ")
                .pattern(" S ")
                .define('N', ModItems.NIOBIUM_INGOT.get())
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.NIOBIUM_INGOT.get()), has(ModItems.NIOBIUM_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NIOBIUM_SHOVEL.get())
                .pattern(" N ")
                .pattern(" S ")
                .pattern(" S ")
                .define('N', ModItems.NIOBIUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.NIOBIUM_INGOT.get()), has(ModItems.NIOBIUM_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NIOBIUM_HOE.get())
                .pattern("NI ")
                .pattern(" S ")
                .pattern(" S ")
                .define('N', ModItems.NIOBIUM_INGOT.get())
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.NIOBIUM_INGOT.get()), has(ModItems.NIOBIUM_INGOT.get()))
                .save(pWriter);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NIOBIUM_INGOT.get(), 9)
                .requires(ModBlocks.NIOBIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.NIOBIUM_BLOCK.get()), has(ModBlocks.NIOBIUM_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.NIOBIUM_INGOT.get(), 1)
                .requires(ModItems.NIOBIUM_FRAGMENT.get(), 4)
                .requires(Items.IRON_INGOT, 4)
                .unlockedBy(getHasName(ModItems.NIOBIUM_FRAGMENT.get()), has(ModItems.NIOBIUM_FRAGMENT.get()))
                .save(pWriter, Niobium.MOD_ID + ":niobium_ingot_from_ore/niobium_ingot_recipe");

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  Niobium.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
