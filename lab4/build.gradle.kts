// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}
val sourceCompatibility by extra(JavaVersion.VERSION_21)
val sourceCompatibility1 by extra(sourceCompatibility)

