package com.khanhpham.patchoulidatagen.data;

import com.khanhpham.patchoulidatagen.Names;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.TickTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder exampleShaped1 = ShapedRecipeBuilder.shaped(Blocks.CRAFTING_TABLE, 2);

        exampleShaped1.pattern("AA");
        exampleShaped1.pattern("AA");

        exampleShaped1.define('A', Items.ACACIA_WOOD);
        exampleShaped1.unlockedBy("unlock", new TickTrigger.TriggerInstance(EntityPredicate.Composite.ANY));
        exampleShaped1.save(consumer, new ResourceLocation(Names.MOD_ID, "txt_recipe1"));
    }
}
