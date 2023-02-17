package com.khanhpham.patchoulidatagen.examplecode;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LanguageTestProvider extends LanguageProvider {
    public LanguageTestProvider(DataGenerator gen, String modid, String locale) {
        super(gen.getPackOutput(), modid, locale);
    }

    @Override
    protected void addTranslations() {
        add("test_book.name", "Test Book Name");
        add("test_book.landing_text", "Test Book Landing Text");
        add("test_category", "Test Category");
        add("test.category.desc", "Test Category Description");
    }
}
