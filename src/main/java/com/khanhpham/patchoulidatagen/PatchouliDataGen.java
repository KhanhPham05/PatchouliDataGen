package com.khanhpham.patchoulidatagen;

import com.khanhpham.patchoulidatagen.data.impl.LanguageTestProvider;
import com.khanhpham.patchoulidatagen.data.impl.PatchouliGeneratorImpl;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(value = Names.MOD_ID)
@Mod.EventBusSubscriber(modid = Names.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PatchouliDataGen {
    public PatchouliDataGen() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event) {
        DataGenerator data = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        data.addProvider(new PatchouliGeneratorImpl(data, fileHelper, "test_book", Names.MOD_ID));
        data.addProvider(new LanguageTestProvider(data, Names.MOD_ID, "en_us"));
    }
}
