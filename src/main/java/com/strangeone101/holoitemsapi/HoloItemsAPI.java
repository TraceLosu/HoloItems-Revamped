//package com.strangeone101.holoitemsapi;
//
//import com.strangeone101.holoitemsapi.listener.CraftListener;
//import com.strangeone101.holoitemsapi.listener.GenericListener;
//import com.strangeone101.holoitemsapi.listener.LootListener;
//import com.strangeone101.holoitemsapi.recipe.RecipeManager;
//import org.bukkit.Bukkit;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.plugin.java.JavaPlugin;
//
//import java.io.File;
//
//public class HoloItemsAPI {
//
//    private static JavaPlugin plugin;
//    private static Keys keys;
//
//    /**
//     * Setup the HoloItemsAPI for the provided plugin to be used as a base. This should be called within `onEnable()`
//     * before any items are created.
//     * @param plugin The plugin
//     * @return True if setup was successful
//     */
//    public static boolean setup(JavaPlugin plugin) {
//        if (HoloItemsAPI.plugin != null) {
//            plugin.getLogger().warning("Failed to setup HoloItemsAPI! Already setup with " + HoloItemsAPI.plugin.getName());
//            return false;
//        }
//
//        HoloItemsAPI.plugin = plugin;
//
//        Bukkit.getPluginManager().registerEvents(new CraftListener(), plugin);
//        Bukkit.getPluginManager().registerEvents(new LootListener(), plugin);
//        Bukkit.getPluginManager().registerEvents(new GenericListener(), plugin);
//
//        return true;
//    }
//
//    /**
//     * Stops all functions started by the HoloItemsAPI. Should be called within `onDisable()`
//     */
//    public static void shutdown() {
//        RecipeManager.unregisterAll();
//    }
//
//    /**
//     * Set a file to be used as a general config file. Should be a
//     * yml file. Is optional.
//     * @param file The file to use as a general config
//     */
//    public static void setGeneralConfig(File file) {
//        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
//        Config.generalConfig = config;
//        Config.generalConfigFile = file;
//    }
//
//    /**
//     * Set a file to be used as a death message config file. Should be a
//     * yml file. Is optional.
//     * @param file The file to use as a death message config
//     */
//    public static void setDeathMessageConfig(File file) {
//        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
//        Config.deathMessageConfig = config;
//        Config.deathMessageConfigFile = file;
//    }
//}
