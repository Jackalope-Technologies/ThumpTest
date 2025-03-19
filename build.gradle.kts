import org.javamodularity.moduleplugin.extensions.TestModuleOptions

plugins {
    java
    application
    alias(libs.plugins.moduleplugin)
    alias(libs.plugins.javafxplugin)
    alias(libs.plugins.jLink)
    alias(libs.plugins.lombok)
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

dependencies {
    implementation(libs.controlsfx)
    implementation(libs.formsfx) {
        exclude(group = "org.openjfx")
    }
    implementation(libs.oshi)
    implementation(libs.logback)

    testImplementation(libs.junit.jupiter.api)
    implementation(libs.testfx.junit) {
        exclude(group = "org.hamcrest")
    }
    testImplementation(libs.hamcrest)
    testRuntimeOnly(libs.junit.jupiter.engine)
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