package net.raccoon.will.willsworkshop.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.raccoon.will.willsworkshop.WillsWorkshop;
import net.raccoon.will.willsworkshop.block.WWBlockRegistry;
import net.raccoon.will.willsworkshop.item.WWItemRegistry;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WWRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public WWRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
            List<ItemLike> ENZORITE_SMELTABLES = List.of(WWItemRegistry.RAW_ENZORITE,
                    WWBlockRegistry.DEEPSLATE_ENZORITE_ORE);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WWBlockRegistry.ENZORITE_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', WWItemRegistry.ENZORITE.get())
                .unlockedBy("has_enzorite", has(WWItemRegistry.ENZORITE.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WWItemRegistry.ENZORITE.get(), 9)
                .requires(WWBlockRegistry.ENZORITE_BLOCK.get())
                .unlockedBy("has_enzorite_block", has(WWBlockRegistry.ENZORITE_BLOCK.get())).save(recipeOutput);

        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, WWItemRegistry.RAW_ENZORITE.get(), RecipeCategory.MISC, WWBlockRegistry.RAW_ENZORITE_BLOCK,
                "willsworkshop:raw_enzorite", "enzorite", "willsworkshop:raw_enzorite_block", "ezorite");
        oreSmelting(recipeOutput, ENZORITE_SMELTABLES, RecipeCategory.MISC, WWItemRegistry.ENZORITE.get(), 0.25f, 200, "enzorite");
        oreBlasting(recipeOutput, ENZORITE_SMELTABLES, RecipeCategory.MISC, WWItemRegistry.ENZORITE.get(), 0.25f, 100, "enzorite");
    }



    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory category, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, category, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory category, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, category, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory category, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, WillsWorkshop.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

