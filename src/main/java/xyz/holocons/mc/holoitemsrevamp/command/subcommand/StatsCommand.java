package xyz.holocons.mc.holoitemsrevamp.command.subcommand;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import xyz.holocons.mc.holoitemsrevamp.command.SubCommand;

import java.util.Arrays;
import java.util.List;

public class StatsCommand implements SubCommand {

    @Override
    public String getName() {
        return "stats";
    }

    @Override
    public String getDesc() {
        return "Modify player statistics";
    }

    @Override
    public String getFormat() {
        return "<get/set> <player> <statistic> [specifier] [goal]";
    }

    @Override
    public String getPermission() {
        return "holoitems.stats";
    }

    @Override
    public List<String> getAutoComplete(String[] args) {
        // get player untyped_statistic
        // set player untyped_statistic goal
        // get player typed_statistic qualifier
        // set player typed_statistic qualifier goal
        return switch (args.length) {
            case 1 -> List.of("get","set");
            case 2 -> null;
            case 3 -> Arrays.stream(Statistic.values()).map(Statistic::toString).toList();
            case 4 -> {
                Statistic statistic;
                try {
                    statistic = Statistic.valueOf(args[2]);
                } catch (IllegalArgumentException e) {
                    statistic = null;
                }
                if (statistic == null) {
                    yield List.of();
                }

                yield switch (statistic.getType()) {
                    case BLOCK, ITEM -> Arrays.stream(Material.values()).map(Material::toString).toList();
                    case ENTITY -> Arrays.stream(EntityType.values()).map(EntityType::toString).toList();
                    case UNTYPED -> List.of();
                };
            }
            default -> List.of();
        };
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 3 || !sender.hasPermission(getPermission())) {
            return false;
        }

        OfflinePlayer targetPlayer;
        Statistic statistic;
        Integer goal = null;
        Enum<?> specifier = null;

        try {
            targetPlayer = Bukkit.getOfflinePlayerIfCached(args[1]);
            // Bukkit#getOfflinePlayerIfCached returns null if the player hasn't played on the server before
            if (targetPlayer == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            sender.sendMessage(Component.text("Player " + args[1] + " is not valid!", NamedTextColor.YELLOW));
            return false;
        }

        try {
            statistic = Statistic.valueOf(args[2]);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(Component.text("Statistic " + args[2] + " does not exist!", NamedTextColor.YELLOW));
            return false;
        }

        final var statisticType = statistic.getType();

        if (args.length > 3) {
            // If the statistic is not untyped, assign the specifier
            switch (statisticType) {
                case BLOCK, ITEM -> {
                    try {
                        specifier = Material.valueOf(args[3]);
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(Component.text("Material " + args[3] + " is not valid!",
                            NamedTextColor.YELLOW));
                        return false;
                    }
                }
                case ENTITY -> {
                    try {
                        specifier = EntityType.valueOf(args[3]);
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(Component.text("Entity " + args[3] + " is not valid!",
                            NamedTextColor.YELLOW));
                        return false;
                    }
                }
                case UNTYPED -> {}
            }
        }

        if (specifier == null && statisticType != Statistic.Type.UNTYPED) {
            // Statistic type requires a specifier, but only 3 args were given
            sender.sendMessage(Component.text("Statistic " + statistic + " needs a specifier!",
                NamedTextColor.YELLOW));
            return false;
        }

        if (args[0].equalsIgnoreCase("get")) {
            // First arg is get
            var statComponent = Component.text();
            statComponent.append(
                Component.text(targetPlayer.getName() + "'s ", NamedTextColor.AQUA),
                Component.text(statistic + " ", NamedTextColor.BLUE),
                Component.text("statistic ")
            );

            switch (statisticType) {
                case BLOCK, ITEM -> {
                    statComponent.append(
                        Component.text("with specifier "),
                        Component.text(specifier + " ", NamedTextColor.YELLOW),
                        Component.text("is valued at "),
                        Component.text(targetPlayer.getStatistic(statistic, (Material) specifier), NamedTextColor.GREEN)
                    );
                }
                case ENTITY -> {
                    statComponent.append(
                        Component.text("with specifier "),
                        Component.text(specifier + " ", NamedTextColor.YELLOW),
                        Component.text("is valued at "),
                        Component.text(targetPlayer.getStatistic(statistic, (EntityType) specifier), NamedTextColor.GREEN)
                    );
                }
                case UNTYPED -> {
                    statComponent.append(
                        Component.text("is valued at "),
                        Component.text(targetPlayer.getStatistic(statistic), NamedTextColor.GREEN)
                    );
                }
            }

            sender.sendMessage(statComponent.build());
            return true;
        } else if (args[0].equalsIgnoreCase("set")) {
            // First arg is set
            // Goal arg position shifts based on whether there is a specifier arg
            final var goalArgIndex = statisticType == Statistic.Type.UNTYPED ? 3 : 4;
            if (args[0].equalsIgnoreCase("set")) {
                // Since first arg is set, assign the goal
                if (args.length > goalArgIndex) {
                    try {
                        goal = Integer.parseInt(args[goalArgIndex]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(Component.text("Goal " + args[goalArgIndex] + " is not valid!"));
                        return false;
                    }
                } else {
                    sender.sendMessage(Component.text("No goal is specified!", NamedTextColor.YELLOW));
                    return false;
                }
            }

            final var statComponent = Component.text();
            statComponent.append(
                Component.text(targetPlayer.getName() + "'s ", NamedTextColor.AQUA),
                Component.text(statistic + " ", NamedTextColor.BLUE)
            );

            switch (statisticType) {
                case BLOCK, ITEM -> {
                    targetPlayer.setStatistic(statistic, (Material) specifier, goal);
                    statComponent.append(
                        Component.text("with specifier "),
                        Component.text(specifier.toString(), NamedTextColor.YELLOW)
                    );
                }
                case ENTITY -> {
                    targetPlayer.setStatistic(statistic, (EntityType) specifier, goal);
                    statComponent.append(
                        Component.text("with specifier "),
                        Component.text(specifier.toString(), NamedTextColor.YELLOW)
                    );
                }
                case UNTYPED -> {
                    targetPlayer.setStatistic(statistic, goal);
                }
            }

            statComponent.append(
                Component.text("has been set to "),
                Component.text(goal, NamedTextColor.GREEN)
            );
            sender.sendMessage(statComponent.build());
            return true;
        } else {
            // First arg was neither get nor set
            return false;
        }
    }
}
