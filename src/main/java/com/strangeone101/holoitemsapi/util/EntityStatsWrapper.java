package com.strangeone101.holoitemsapi.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

public class EntityStatsWrapper extends StatsWrapper<EntityType>{

    public EntityStatsWrapper(Statistic statistic, EntityType specifier, int goal) {
        super(statistic, specifier, goal);

        if(statistic.getType() != Statistic.Type.ENTITY)
            throw new IllegalArgumentException("Statistic " + statistic + " doesn't use EntityType specifier.");
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
