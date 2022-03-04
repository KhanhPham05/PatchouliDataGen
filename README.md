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
  implementation fg.deobf('curse.maven:patchouli-datagen-ptd-lib-585667:3670846')
}
```
