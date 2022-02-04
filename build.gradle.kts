plugins {
    `java-library`
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1" // Generates plugin.yml
    id("com.github.johnrengelman.shadow") version "7.1.2" // Shades and relocates dependencies into our plugin jar
    id("xyz.jpenilla.run-paper") version "1.0.6" // Adds runServer and runMojangMappedServer tasks for testing
}

group = "xyz.holocons.mc"
version = "0.0.1"
description = "A plugin for HoloCons SMP that adds a ton of custom items and blocks with unique abilities."

java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://raw.githubusercontent.com/StrangeOne101/HoloItemsAPI/repository/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
    implementation("com.strangeone101:HoloItemsAPI:0.7.4")
    implementation("com.github.stefvanschie.inventoryframework:IF:0.10.4")
}

tasks {
    // Configure shadowJar to run when invoking the build task
    assemble {
        dependsOn(shadowJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }

    // Configure the name of the unshaded jar
    jar {
        archiveClassifier.set("incomplete")
        archiveVersion.set("")
    }

    // Shade and relocate dependencies
    // https://github.com/johnrengelman/shadow
    shadowJar {
        relocate("com.strangeone101", "shadow.strangeone101")
        relocate("com.github.stefvanschie.inventoryframework", "shadow.inventoryframework")

        archiveClassifier.set("")
    }

    // Configure the Minecraft version for runServer task
    // https://github.com/jpenilla/run-paper
    runServer {
        minecraftVersion("1.18.1")
    }
}

// Configure plugin.yml generation
// https://github.com/Minecrell/plugin-yml
bukkit {
    main = "xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp"
    apiVersion = "1.18"
    authors = listOf("TraceL", "dlee13")
    website = "holocons.xyz"
    prefix = "HoloItems"

    commands {
        register("holoitems") {
            usage = "/holoitems"
        }
    }
}
