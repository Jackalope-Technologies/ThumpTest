import org.javamodularity.moduleplugin.extensions.TestModuleOptions

buildscript {
    apply("dependencies.gradle.kts")
}

plugins {
    val modulePluginVersion = project.extra.get("modulePluginVersion") as String
    val javafxPluginVersion = project.extra.get("javafxPluginVersion") as String
    val jlinkPluginVersion = project.extra.get("jlinkPluginVersion") as String
    val lombokPluginVersion = project.extra.get("lombokPluginVersion") as String

    java
    application
    id("org.javamodularity.moduleplugin").version(modulePluginVersion)
    id("org.openjfx.javafxplugin").version(javafxPluginVersion)
    id("org.beryx.jlink").version(jlinkPluginVersion)
    id("io.freefair.lombok").version(lombokPluginVersion)
}

repositories {
    mavenCentral()
    google()
}

java {
    group = "com.jackalope"
    version = "1.0-SNAPSHOT"

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    modularity.inferModulePath = false
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule = "com.jackalope.thumptest"
    mainClass = "com.jackalope.thumptest.ThumpTestApplication"
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

val controlsFX = project.extra.get("controlsFX") as String
val formsFX = project.extra.get("formsFX") as String
val oshi = project.extra.get("oshi") as String
val logback = project.extra.get("logback") as String
val testFX = project.extra.get("testFX") as String
val hamcrest = project.extra.get("hamcrest") as String
val junitJupiterEngine = project.extra.get("junitJupiterEngine") as String
val junitJupiterAPI = project.extra.get("junitJupiterAPI") as String

dependencies {
    implementation(controlsFX)
    implementation(formsFX) {
        exclude(group = "org.openjfx")
    }
    implementation(oshi)
    implementation(logback)

    testImplementation(junitJupiterAPI)
    implementation(testFX) {
        exclude(group = "org.hamcrest")
    }
    testImplementation(hamcrest)
    testRuntimeOnly(junitJupiterEngine)
}

tasks.test {
    useJUnitPlatform()
    extensions.configure(TestModuleOptions::class) {
        runOnClasspath = true
    }
}

jlink {
    imageZip = project.file("${layout.buildDirectory}/distributions/app-${javafx.platform.classifier}.zip")
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))

    launcher {
        name = "app"
    }
}

tasks.jlinkZip {
    group = "distribution"
}