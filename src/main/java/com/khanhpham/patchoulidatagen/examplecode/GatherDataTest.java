package com.khanhpham.patchoulidatagen.examplecode;

import com.khanhpham.patchoulidatagen.Names;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Names.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GatherDataTest {
    public GatherDataTest() {
    }

    @SubscribeEvent
    public static void event(GatherDataEvent event) {
        var data = event.getGenerator();
        data.addProvider(new LanguageTestProvider(data, Names.MOD_ID, "en_us"));
        data.addProvider(new PatchouliGeneratorImpl(data, event.getExistingFileHelper(), "test_book", Names.MOD_ID));

    }
}
