# PatchouliDataGen
A DataGenerator library mod for mod makers to create Patchouli Books with no JSON error

# Installation guide

add those line in your `build.gradle` file
**IMPORTANT** : Please DO NOT make any changes in the `buildscript {...}` block 

```gradle
repositories {
  mavenCentral()
}

dependencies {

  //Please put this line BELOW the minecraft dependency
  implementation fg.deobf('io.github.realyusufismail:patchouli-datagen:${version}')
}
```

## How to check for version

See below for the latest version

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.realyusufismail/patchouli-datagen/badge.svg)](https://maven-badges.herokuapp.com/maven-central/realyusufismail/patchouli-datagen)
