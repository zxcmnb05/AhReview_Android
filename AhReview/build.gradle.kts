// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(Dependencies.Gradle.gradle)
        classpath(Dependencies.Gradle.kotlin)
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}