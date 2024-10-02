import org.javamodularity.moduleplugin.extensions.TestModuleOptions

plugins {
    java
    application
    id("org.javamodularity.moduleplugin").version("1.8.15")
    id("org.openjfx.javafxplugin").version("0.0.13")
    id("org.beryx.jlink").version("2.25.0")
    id("io.freefair.lombok").version("8.6")
}

repositories {
    mavenCentral()
    google()
}

val junitVersion by extra("5.10.0")
val testFXVersion by extra("4.0.18")

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
    implementation("org.controlsfx:controlsfx:11.2.1")
    implementation("com.dlsc.formsfx:formsfx-core:11.6.0") {
        exclude(group = "org.openjfx")
    }
    implementation("com.github.oshi:oshi-core:6.6.5")
    implementation("ch.qos.logback:logback-classic:1.5.8")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    implementation("org.testfx:testfx-junit5:${testFXVersion}") {
        exclude(group = "org.hamcrest")
    }
    testImplementation("org.hamcrest:hamcrest:3.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
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