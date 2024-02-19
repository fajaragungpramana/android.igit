// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugin.APPLICATION) version Version.GRADLE apply false
    id(Plugin.KOTLIN) version Version.KOTLIN apply false
    id(Plugin.HILT) version Version.HILT apply false
}
buildscript {
    dependencies {
        classpath(Classpath.HILT)
        classpath(Classpath.NAVIGATION)
    }
}