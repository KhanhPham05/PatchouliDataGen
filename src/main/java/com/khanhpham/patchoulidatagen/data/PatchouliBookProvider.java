package com.khanhpham.patchoulidatagen.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khanhpham.patchoulidatagen.pages.BookCategory;
import com.khanhpham.patchoulidatagen.pages.BookElement;
import com.khanhpham.patchoulidatagen.pages.BookEntry;
import com.khanhpham.patchoulidatagen.pages.BookHeader;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @see net.minecraft.data.advancements.AdvancementProvider
 */
public abstract class PatchouliBookProvider implements DataProvider {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final DataGenerator generator;
    private final ExistingFileHelper fileHelper;
    protected final String modid;
    private final String bookName;

    public PatchouliBookProvider(DataGenerator generator, ExistingFileHelper fileHelper, String bookName, String modid) {
        this.fileHelper = fileHelper;
        this.generator = generator;
        this.modid = modid;
        this.bookName = bookName;
    }

    @Override
    public void run(@Nonnull HashCache pCache) {
        Path dataFolder = generator.getOutputFolder();
        Set<String> bookLocations = new HashSet<>();
        final String bookDefaultPath = "data/" + modid + "/patchouli_books/" + bookName + "/en_us/";
        Consumer<BookElement> elementConsumer = ((element) -> {
            if (bookLocations.add(element.getSaveName())) {
                if (element instanceof BookEntry entries) {
                    Path entryFolder = resolvePath(dataFolder, bookDefaultPath + "entries/" + entries.getSaveName() + ".json");
                    this.saveData(gson, pCache, entries, entryFolder);
                } else if (element instanceof BookCategory category) {
                    Path categoryFolder = resolvePath(dataFolder, bookDefaultPath + "categories/" + category.getSaveName() + ".json");
                    this.saveData(gson, pCache, category, categoryFolder);
                } else if (element instanceof BookHeader header) {
                    Path headerFolder = resolvePath(dataFolder, "data/" + modid + "/patchouli_books/" + bookName + "/book.json");
                    this.saveData(gson, pCache, header, headerFolder);
                }

            } else {
                ResourceLocation bookLocation = new ResourceLocation(modid, element.getSaveName());
                throw new IllegalStateException("Duplicate book page [" + bookLocation + "]");
            }
        });

        registerPages(elementConsumer);
    }

    private Path resolvePath(Path path, String pathOther) {
        return path.resolve(pathOther);
    }

    private <T extends BookElement> void saveData(Gson gson, HashCache cache, T jsonObject, Path bookElementPath) {
        try {
            DataProvider.save(gson, cache, jsonObject.toJson(), bookElementPath);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    protected abstract void registerPages(Consumer<BookElement> consumer);

    @Override
    public String getName() {
        return "Patchouli Book: " + bookName.toLowerCase();
    }
}
