package data;

import com.khanhpham.patchoulidatagen.Names;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.DistanceTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder exampleShaped1 = ShapedRecipeBuilder
            .shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CRAFTING_TABLE, 2);

        exampleShaped1.pattern("AA");
        exampleShaped1.pattern("AA");
        exampleShaped1.define('A', Items.ACACIA_WOOD);
        exampleShaped1.unlockedBy("unlock",
                new DistanceTrigger.TriggerInstance(CriteriaTriggers.NETHER_TRAVEL.getId(),
                        EntityPredicate.Composite.ANY, LocationPredicate.ANY,
                        DistancePredicate.ANY));
        exampleShaped1.save(consumer, new ResourceLocation(Names.MOD_ID, "txt_recipe1"));
    }
}
