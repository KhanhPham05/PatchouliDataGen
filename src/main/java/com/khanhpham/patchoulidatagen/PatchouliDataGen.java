package com.khanhpham.patchoulidatagen;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(value = Names.MOD_ID)
public class PatchouliDataGen {

    public PatchouliDataGen() {
        MinecraftForge.EVENT_BUS.register(this);
    }


}
