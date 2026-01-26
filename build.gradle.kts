import org.javamodularity.moduleplugin.extensions.TestModuleOptions

plugins {
    java
    jacoco
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

    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25

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
    version = "25"
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
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.junit.jupiter)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
    useJUnitPlatform()
    extensions.configure(TestModuleOptions::class) {
        runOnClasspath = true
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
        csv.required = false
    }
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude("com/jackalope/thumptest/controller/*.class")
            }
        })
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = BigDecimal.valueOf(0.5)
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
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