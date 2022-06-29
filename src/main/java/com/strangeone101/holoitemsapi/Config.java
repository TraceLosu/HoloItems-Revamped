package com.strangeone101.holoitemsapi;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

import com.strangeone101.holoitemsapi.item.CustomItem;

public class Config {

    static FileConfiguration generalConfig;
    static File generalConfigFile;

    public static boolean hasGeneralConfig() {
        return generalConfig != null;
    }

    public static FileConfiguration getGeneralConfig() {
        return generalConfig;
    }

    public static boolean addDefaultConfigOption(String path, Object default_) {
        if (!hasGeneralConfig()) {
            return false;
        }

        generalConfig.addDefault(path, default_);
        try {
            generalConfig.save(generalConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean addDefaultConfigOption(CustomItem item, String option, Object default_) {
        return addDefaultConfigOption("Items." + item.getInternalName() + "." + option, default_);
    }
}
