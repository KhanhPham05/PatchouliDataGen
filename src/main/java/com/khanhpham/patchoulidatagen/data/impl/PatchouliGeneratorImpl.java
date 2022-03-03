package com.khanhpham.patchoulidatagen.data.impl;

import com.khanhpham.patchoulidatagen.data.PatchouliBookProvider;
import com.khanhpham.patchoulidatagen.pages.*;
import com.khanhpham.patchoulidatagen.pages.pagetype.defaults.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class PatchouliGeneratorImpl extends PatchouliBookProvider {
    public PatchouliGeneratorImpl(DataGenerator generator, ExistingFileHelper fileHelper, String bookName, String modid) {
        super(generator, fileHelper, bookName, modid);
    }

    @Override
    protected void registerPages(Consumer<BookElement> consumer) {
        BookHeader header = BookHeader.Builder.header().enableI18n().setBookComponent(modid, translate("test_book.name"), translate("test_book.landing_text")).build(consumer);

        BookCategory category1 = BookCategory.Builder.category().bookHeader(header).setDisplay(new TranslatableComponent("test_category"), new TranslatableComponent("test_category.desc"), Items.DIRT).save(consumer, "test");
        BookCategory category2 = BookCategory.Builder.category().bookHeader(header).setDisplay("Test Second Category", "test Second Category Description", Items.BIRCH_WOOD).save(consumer, "test_second_category");

        CraftingRecipePage crafting = CraftingRecipePage.setup().mainRecipe(Blocks.CRAFTING_TABLE).build();
        MultiblockPage multiblockPage = MultiblockPage.Builder.setup().multiblock("Test Multiblock", PatchouliMultiblock.Builder.multiblock().pattern("A   0", "A   A").pattern("AAAAA", "AAAAA").mapping('A', Blocks.DIRT).mapping('0', Blocks.DIRT)).build();
        //ImagePage imagePage = ImagePage.Builder.setup().addImage(new ResourceLocation("textures/block/acacia_log.png")).build();
        TextPage textPage = TextPage.Builder.setup().text("This is a text page").title("Test Page Title").build();
        SmeltingRecipePage smeltingPage = SmeltingRecipePage.Builder.setup().mainRecipe(Blocks.STONE).secondaryRecipe(Blocks.SMOOTH_STONE).build();
        SpotlightPage spotlightPage = SpotlightPage.Builder.setup().item(Items.ITEM_FRAME).build();
        EntityPage entityPage = EntityPage.setup().entity(EntityType.CHICKEN).build();

        BookEntry.setup().category(category1).display("Test First Entry", Items.DIAMOND_SWORD).addPage(smeltingPage).addPage(spotlightPage).build(consumer, "test_entry_category_one");
        BookEntry.setup().category(category2).display("Test Second Entry", Items.GOLD_BLOCK).addPage(textPage).addPage(multiblockPage).addPage(entityPage).addPage(crafting).build(consumer, "test_entry_category_2");
    }

    private TranslatableComponent translate(String key) {
        return new TranslatableComponent(key);
    }
}
