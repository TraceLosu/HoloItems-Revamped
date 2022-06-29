package com.strangeone101.holoitemsapi.statistic;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

public class MaterialStatsWrapper extends StatsWrapper<Material>{

    public MaterialStatsWrapper(Statistic statistic, Material specifier, int goal) {
        super(statistic, specifier, goal);

        if(statistic.getType() != Statistic.Type.BLOCK && statistic.getType() != Statistic.Type.ITEM)
            throw new IllegalArgumentException("Statistic " + statistic + " doesn't use Material specifier.");
    }

    @Override
    public boolean checkPlayer(OfflinePlayer player) {
        return player.getStatistic(getStatistic(), getSpecifier()) >= getGoal();
    }

    @Override
    public int inspectPlayer(OfflinePlayer player) {
        return getGoal() - player.getStatistic(getStatistic(), getSpecifier());
    }
}
