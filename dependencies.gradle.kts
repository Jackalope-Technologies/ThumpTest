extra.apply {
    // Plugin versions
    set("modulePluginVersion", "1.8.15")
    set("javafxPluginVersion", "0.0.13")
    set("jlinkPluginVersion", "2.25.0")
    set("lombokPluginVersion", "8.6")

    // Dependency versions
    set("controlsfxVersion", "11.2.1")
    set("formsfxVersion", "11.6.0")
    set("oshiVersion", "6.6.5")
    set("logbackVersion", "1.5.11")
    set("junitVersion", "5.10.0")
    set("testfxVersion", "4.0.18")
    set("hamcrestVersion", "3.0")

    val controlsfxVersion = project.extra.get("controlsfxVersion") as String
    val formsfxVersion = project.extra.get("formsfxVersion") as String
    val oshiVersion = project.extra.get("oshiVersion") as String
    val logbackVersion = project.extra.get("logbackVersion") as String
    val junitVersion = project.extra.get("junitVersion") as String
    val testfxVersion = project.extra.get("testfxVersion") as String
    val hamcrestVersion = project.extra.get("hamcrestVersion") as String

    set("controlsFX", "org.controlsfx:controlsfx:$controlsfxVersion")
    set("formsFX", "com.dlsc.formsfx:formsfx-core:$formsfxVersion")
    set("oshi", "com.github.oshi:oshi-core:$oshiVersion")
    set("logback", "ch.qos.logback:logback-classic:$logbackVersion")
    set("testFX", "org.testfx:testfx-junit5:$testfxVersion")
    set("hamcrest", "org.hamcrest:hamcrest:$hamcrestVersion")
    set("junitJupiterEngine", "org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    set("junitJupiterAPI", "org.junit.jupiter:junit-jupiter-api:$junitVersion")
}