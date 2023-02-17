package com.khanhpham.patchoulidatagen.examplecode;

import com.khanhpham.patchoulidatagen.bookelement.BookCategory;
import com.khanhpham.patchoulidatagen.bookelement.BookElement;
import com.khanhpham.patchoulidatagen.bookelement.BookEntry;
import com.khanhpham.patchoulidatagen.bookelement.BookHeader;
import com.khanhpham.patchoulidatagen.provider.PatchouliBookProvider;
import com.khanhpham.patchoulidatagen.pages.pagetype.defaults.*;
import com.khanhpham.patchoulidatagen.utils.PatchouliMultiblock;
import net.minecraft.core.Direction;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class PatchouliGeneratorImpl extends PatchouliBookProvider {
    public PatchouliGeneratorImpl(DataGenerator generator, ExistingFileHelper fileHelper,
            String bookName, String modid) {
        super(generator, fileHelper, bookName, modid);
    }

    @Override
    protected void buildPages(Consumer<BookElement> consumer) {
        BookHeader header = BookHeader.Builder.header()
            .enableI18n()
            .setBookComponent(modid, translate("test_book.name"),
                    translate("test_book.landing_text"))
            .build(consumer);

        BookCategory category1 = BookCategory.Builder.category()
            .bookHeader(header)
            .setDisplay(Component.translatable("test_category"),
                    Component.translatable("test_category.desc"), Items.DIRT)
            .save(consumer, "test");
        BookCategory category2 = BookCategory.Builder.category()
            .bookHeader(header)
            .setDisplay("Test Second Category", "test Second Category Description",
                    Items.BIRCH_WOOD)
            .save(consumer, "test_second_category");

        CraftingRecipePage crafting =
                CraftingRecipePage.setup().mainRecipe(Blocks.CRAFTING_TABLE).build();
        MultiblockPage multiblockPage = MultiblockPage.setup()
            .multiblock("Test Multiblock",
                    PatchouliMultiblock.Builder.multiblock()
                        .pattern("A   0", "A   A")
                        .pattern("AAAAA", "AAAAA")
                        .mapping('A', Blocks.DIRT)
                        .mapping('0', Blocks.DIRT))
            .build();
        ImagePage imagePage = ImagePage.setup()
            .addImage(new ResourceLocation("textures/block/acacia_log.png"))
            .build();
        TextPage textPage = TextPage.Builder.setup()
            .text("This is a text page")
            .title("Test Page Title")
            .build();
        SmeltingRecipePage smeltingPage = SmeltingRecipePage.setup()
            .mainRecipe(Blocks.STONE)
            .secondaryRecipe(Blocks.SMOOTH_STONE)
            .build();
        SpotlightPage spotlightPage = SpotlightPage.Builder.setup().item(Items.ITEM_FRAME).build();
        EntityPage entityPage = EntityPage.setup().entity(EntityType.CHICKEN).build();
        /**
         * @see net.minecraft.world.level.block.StairBlock
         */
        MultiblockPage multiblockPage1 =
                MultiblockPage.setup()
                    .multiblock("test_multiblock_2", PatchouliMultiblock.setup()
                        .pattern("XXX0XXX")
                        .mapping('X', Blocks.OAK_STAIRS, StairBlock.FACING, Direction.NORTH))
                    .build();

        BookEntry.setup()
            .category(category1)
            .display("Test First Entry", Items.DIAMOND_SWORD)
            .addPage(multiblockPage1)
            .addPage(smeltingPage)
            .addPage(spotlightPage)
            .build(consumer, "test_entry_category_one");
        BookEntry.setup()
            .category(category2)
            .display("Test Second Entry", Items.GOLD_BLOCK)
            .addPage(imagePage)
            .addPage(textPage)
            .addPage(multiblockPage)
            .addPage(entityPage)
            .addPage(crafting)
            .build(consumer, "test_entry_category_2");
    }

    private Component translate(String key) {
        return Component.translatable(key);
    }
}
