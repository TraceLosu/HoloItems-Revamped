package com.strangeone101.holoitemsapi.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

public class VoidStatsWrapper extends StatsWrapper<Void>{

    public VoidStatsWrapper(Statistic statistic, int goal) {
        super(statistic, null, goal);

        if(statistic.getType() != Statistic.Type.UNTYPED)
            throw new IllegalArgumentException("Statistic " + statistic + " uses a specifier.");
    }

    @Override
    public boolean checkPlayer(OfflinePlayer player) {
        return player.getStatistic(getStatistic()) >= getGoal();
    }

    @Override
    public int inspectPlayer(OfflinePlayer player) {
        return getGoal() - player.getStatistic(getStatistic());
    }
}
