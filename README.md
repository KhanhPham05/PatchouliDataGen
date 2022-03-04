# PatchouliDataGen
A DataGenerator library mod for mod makers to create Patchouli Books with no JSON error

# Installation guide

add those line in your `build.gradle` file
**IMPORTANT** : Please DO NOT make any changes in the `buildscript {...}` block 

```gradle
repositories {
  maven {
    url = "https://www.cursemaven.com/"
  }
}

dependencies {

  //Please put this line BELOW the minecraft dependency
  implementation fg.deobf('curse.maven:patchouli-datagen-ptd-lib-585667:${file_id}')
}
```

## How to check for ${file_id} ?
go to https://www.curseforge.com/minecraft/mc-mods/patchouli-datagen-ptd-lib/files then find and click on a version that you want to use, then the file id is the number in the end of the link, you can you that number to install the proper lib version
