package xyz.holocons.mc.holoitemsrevamp.collection;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.item.BackdashBoots;
import xyz.holocons.mc.holoitemsrevamp.item.DummyBlockBlock;
import xyz.holocons.mc.holoitemsrevamp.item.MagnetBook;
import xyz.holocons.mc.holoitemsrevamp.item.MementoItem;
import xyz.holocons.mc.holoitemsrevamp.item.TideRiderItem;
import xyz.holocons.mc.holoitemsrevamp.item.TimeLapseroniItem;

public class CollectionManager {

    private final List<IdolCollection> idolCollections;
    private final Map<String, CustomItem> customItems;

    public CollectionManager(HoloItemsRevamp plugin) {
        this.idolCollections = buildIdolCollections(plugin);

        // Key is the internal name, value is the initialized custom item
        this.customItems = idolCollections.stream()
                .<CustomItem>mapMulti((idolCollection, consumer) -> {
                    for (final var idol : idolCollection.getIdolSet()) {
                        for (final var customItem : idol.getItemSet()) {
                            consumer.accept(customItem);
                        }
                    }
                })
                .collect(Collectors.toMap(CustomItem::getInternalName, Function.identity()));
    }

    /**
     * @return a list of all idol collections
     */
    public List<IdolCollection> getAllCollections() {
        return idolCollections;
    }

    /**
     * @return all custom items that the plugin contains
     */
    public Map<String, CustomItem> getAllItems() {
        return customItems;
    }

    private static List<IdolCollection> buildIdolCollections(HoloItemsRevamp plugin) {
        var en1 = new IdolCollection(
                buildGura(plugin), buildIRyS(plugin), buildCalliope(plugin), buildIna(plugin), buildKiara(plugin),
                buildAmelia(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("EN Generation 1", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var en2 = new IdolCollection(
                buildFauna(plugin), buildBaelz(plugin), buildMumei(plugin), buildKronii(plugin), buildSana(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("EN Generation 2", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var gamers = new IdolCollection(
                buildKorone(plugin), buildOkayu(plugin), buildMio(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Gamers", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var gen0 = new IdolCollection(
                buildAZKi(plugin), buildSuisei(plugin), buildRoboco(plugin), buildMiko(plugin), buildSora(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Generation 0", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var gen1 = new IdolCollection(
                buildHaato(plugin), buildAki(plugin), buildMatsuri(plugin), buildFubuki(plugin), buildMel(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Generation 1", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var gen2 = new IdolCollection(
                buildAqua(plugin), buildShion(plugin), buildAyame(plugin), buildSubaru(plugin), buildChoco(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Generation 2", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var gen3 = new IdolCollection(
                buildMarine(plugin), buildFlare(plugin), buildNoel(plugin), buildRushia(plugin), buildPekora(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Generation 3", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var gen4 = new IdolCollection(
                buildKanata(plugin), buildLuna(plugin), buildCoco(plugin), buildTowa(plugin), buildWatame(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Generation 4", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var gen5 = new IdolCollection(
                buildNene(plugin), buildPolka(plugin), buildBotan(plugin), buildLamy(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Generation 5", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var gen6 = new IdolCollection(
                buildKoyori(plugin), buildIroha(plugin), buildLaplus(plugin), buildChloe(plugin), buildLui(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Generation 6", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var id1 = new IdolCollection(
                buildIofi(plugin), buildRisu(plugin), buildMoona(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive ID Generation 1", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var id2 = new IdolCollection(
                buildAnya(plugin), buildOllie(plugin), buildReine(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive ID Generation 2", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var misc = new IdolCollection(
                buildAchan(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hololive Misc", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var stars1 = new IdolCollection(
                buildAruran(plugin), buildMiyabi(plugin), buildIzuru(plugin), buildRikka(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Holostars Generation 1", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var stars2 = new IdolCollection(
                buildAstel(plugin), buildTemma(plugin), buildRoberu(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Holostars Generation 2", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        var stars3 = new IdolCollection(
                buildOga(plugin), buildShien(plugin)) {

            @Override
            public @NotNull Material getMaterial() {
                return Material.PAPER;
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Holostars Generation 3", TextColor.color(0x1D83FF));
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };

        return List.of(
                en1, en2, gamers, gen0, gen1, gen2, gen3, gen4, gen5, gen6, id1, id2, misc, stars1, stars2, stars3);
    }

    private static Idol buildGura(HoloItemsRevamp plugin) {
        return new Idol(new TideRiderItem(plugin)) {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNhNWQ0MzQ5MDZiY2M1ZGRhMDJlODRlMmMwOWVjNzI4NDU5NDAwYjI2NDYzZWM3YmFhOTJiMzVkYmE1MDBkYiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Gawr Gura")
                        .color(TextColor.color(0x3969B2))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildIRyS(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWQ2YjMxNTcxNGE2YmUzZTlmMzVhNWNhZWE0NDQ1MTE0Y2JkYTYxMTBjNWUwMzliNGIzODJkNDI5NTg3MGVkMiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("IRyS")
                        .color(TextColor.color(0x361028))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildCalliope(HoloItemsRevamp plugin) {
        return new Idol(new MementoItem(plugin)) {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRiNzU5NmViOTllMzhiOTE3ZDliNmI2YzhlMjAxMDA0MDhjZThhNjZmMDBkZDVjY2E0ODdlYjJhOTA5MDg4YyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Mori Calliope")
                        .color(TextColor.color(0x9F0306))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildIna(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2IzMDY4YmY3YzQ0MTI4MGRhNTVmMGQ5NjY4NmUzOTQwOGM0NjM1ZWMxMGJmYWY1OTRlOThlNzQzMzE5ZTk2In19fQ==";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Ninomae Inanis")
                        .color(TextColor.color(0x3F3F6A))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildKiara(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRmNzdiZDFlODQ1ZDY5ZDZiZWRjMmZlMGNiZTJhMmJiNTFmYzY4MGQ1MWI5ZWM4ZDMwNjY1MTQyMGMzODMwMCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Takanashi Kiara")
                        .color(TextColor.color(0xDC3907))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAmelia(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQ1NDYxOWZlNzE5ODU2YTM0NDA2ZDFmZWNjY2E0ODA4NWE1ZTA0YjM3MTEyN2UyMDViODk0ZGQxOTA5NjRjOSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Watson Amelia")
                        .color(TextColor.color(0xF2BD37))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildFauna(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBjZDNlMTAyNWM1NGNmNTg4Zjc5NmRjZDBmYTNjNWRkODRjZmZiMTE1OGQ0ZTMxZDljNDlhOWFhMDdmZDAzMCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Ceres Fauna")
                        .color(TextColor.color(0x33CA65))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildBaelz(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRhOTg1ZjE1NjBmNTdjMTgzODNkYjNjYzQ2MTUwMjg3OWY5YjYyOGM0ZDNlMTJhZGUzN2JlNjU2NzMyMzY5MCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hakos Baelz")
                        .color(TextColor.color(0xD3251E))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildMumei(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ3NmFkMjM1YzAzOTFlYmJkZTcyNTllYjljZDBlYWQ3NzY4MzNkZTI3MjI3N2JlNDc3NTI4ZjBjOGJiMmY3In19fQ==";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Nanashi Mumei")
                        .color(TextColor.color(0xC29371))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildKronii(HoloItemsRevamp plguin) {
        return new Idol(new TimeLapseroniItem(plguin)) {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTI1ZWFiZjkxNjA4ZGU2ZDI1NGRkOGQwZGU2MjRmZGRiOTkyNDg1NTBkYzZiNDY3NzZlMDlhNmIwNWEyYmFjYiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Ouro Kronii")
                        .color(TextColor.color(0x4B4F67))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildSana(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2QxYWY4NTMxMzk4OTI2NGQ5NTFiZWJmMjM3YWQzNjcyM2U5MjYwZjE1MmQwMjVhOGFiNWE3NjQ4MDc4MDZjOCJ9fX0=";
            }

            @Override
            public @NotNull Component getDisplayName() {
                return Component.text("Tsukumo Sana")
                        .color(TextColor.color(0xD584AB))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildKorone(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTJlN2NkZDkwYzEyZGFkOWFmYzAwYjkzYTZlYjA4ZmNhZTkzNmM0ZDlmOTE1ZTc0MGQ0NWQ3MzAyMjkyNTczNCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Inugami Korone")
                        .color(TextColor.color(0xDBB314))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildOkayu(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc5ZGQ2ZDYwZWIzOTM0NTY1ODcwOTJhYzQ5MDhmM2U1NmFlYmNiNzVkZTIxODY4MzY1MTJiNjhiOGY2NDUyZCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Nekomata Okayu")
                        .color(TextColor.color(0xB37DCF))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildMio(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VkYTNhNWFhZjY1YmNhYTIwYzc0NjE4YTA4NjEzMGJmMTA5OTFiODAyZmE5MDMwNTU2MGI3YjAzMTNiNjVhNCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Ookami Mio")
                        .color(TextColor.color(0xD91733))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAZKi(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDVhNjU0ZjM4MzAyZDI0NWU1OWVjNWY5ZjZjYjQ2NzQ4YzgzNDJjYjU1MmQ3OTY1M2YxMTk4YTVmYWEwYTQ2OCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("AZKi")
                        .color(TextColor.color(0xD11C76))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildSuisei(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRlZWNiM2MwYjRmYzYyNWY1OTQ5OWIwZjJjNDlkNGRiMmQ0MWU0NWFkYmYwZjc5Y2VkM2RiZGViMDc3NjJiMyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hoshimachi Suisei")
                        .color(TextColor.color(0x5E8ABB))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildRoboco(HoloItemsRevamp plugins) {
        return new Idol(new MagnetBook(plugins)) {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWIyOWVlYmM5ZWJkZGFiMmZlNmQ4Nzc1YTdiOGE5NGExZDQxMDg5YmI4MTc2Y2E1ZTY2OWU2ZDYxYTgwNjdmNCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Roboco")
                        .color(TextColor.color(0x804F7F))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildMiko(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk5ZGMwMWRjZjI4NDQ1NTc2ZjIyNjg4ODJhNzc3MDZmY2I5MzUzYWIwYzk1NGY5NjA0NTU2MWE3OTI0NGMxZSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Sakura Miko")
                        .color(TextColor.color(0xFF4C74))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildSora(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg2ZjZmZWIyODViNTNlN2E4NWY5MjRkYzAzMmQyZTU4MTZmNTA0MmE0NTMwZWVjYzVjMDM0YmVlMTdiMWJkMCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Tokino Sora")
                        .color(TextColor.color(0x0444E7))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildHaato(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTc2M2ZjYzY1NzQ5NDBjODNiMTIwY2M2ZGUxNmQ1YzhhZWU0YzRlMzRjMzA0M2E5M2FlZThhN2U3NGFjMWVkNCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Akai Haato")
                        .color(TextColor.color(0xD90629))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAki(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBhMTQ0ZWMwMzFmMDNiM2YzZGQ0MDcwYzc4YjM0Y2M1YTRiMWI5YmExNWJkNWVhYmY1ZDAyODBkYzI1ZGJmMSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Aki Rosenthal")
                        .color(TextColor.color(0xD80E89))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildMatsuri(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDhlN2UzYjA4YmExN2M4OWRiYzJiODVhYjVmYTViNDk5MTZlZmVmMTQ5YzgwMzViOTZjNjJmMjE1Y2U5YzJjMSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Natsuiro Matsuri")
                        .color(TextColor.color(0xFF5506))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildFubuki(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVjOTc1ZGViMjhkZTMzNjQ3ODc5MDhmYjBmNjkzYjIwMGViMTZjNTBmZmQ3ZmNmNjJjZGJlMjM2NjViOGYzOSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Shirakami Fubuki")
                        .color(TextColor.color(0x6BBFD9))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildMel(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWRlNmMxMWU0ODk4NmFlNWM0MTE3N2UyZmIzMTdiMGU3NTdiNTUxM2RjOTVlOGJhMTg5MWQzMjY4YjVhOGZjMCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Yozora Mel")
                        .color(TextColor.color(0xFEC104))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAqua(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODU1M2Y1NGVlNGE4ODY0YjdkNTIyZDVjYTkyNTE2Y2U5ZTlmOWE0YWU4YmYzZmRkMmQzOWJmYjc0MzY0ZDgyOCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Minato Aqua")
                        .color(TextColor.color(0xFE5DD5))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildShion(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjkxZDE3NTJjMjVmNzVjYTIzZmE4MmFmYTUyNzEwMDEzYjBhMjNmNTdmNmI5ZjYyMjYyYmU2YzI1Yjc3MDI4YiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Murasaki Shion")
                        .color(TextColor.color(0x8A55CB))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAyame(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE3Y2JiM2FhY2Q2ZTNkMDgyNjQ3OGJlM2I2ZjQyNTkwOWQ3ZTAyM2IzMGNmNzMzMmYzY2ExZjk5NWVmYTg2OCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Nakiri Ayame")
                        .color(TextColor.color(0xCA2339))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildSubaru(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGE3MjllNDViN2I4N2U5YzIwYWMyYzI0NTNlYWRmNzQ4MDRlYTA0NzBmYzNjZjA3N2JlMTRmNWI1NTY5Zjk1MiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Oozora Subaru")
                        .color(TextColor.color(0xBCE719))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildChoco(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFkOGI0Njg4ZGUyNGFkZDFjYTkwNmVkOGEyYmVhOWYzYTVjZTI0MTZkMjk4ZTI1YmUwNzJjZTAzYjUwYTY1MSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Yuzuki Choco")
                        .color(TextColor.color(0xDA5884))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildMarine(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQ1Mjc5OTMwMTU2OGFiZDVjNWU5YWJjZDU4MzE3NmJiNTkxMGQxNzA4NzE1MTA5YmY2Y2Y1MjM3OWY1N2U3In19fQ==";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Houshou Marine")
                        .color(TextColor.color(0xA42218))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildFlare(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhN2JhM2YxZjEwNzZkYTQ3OTA0MWU0MDdjNTljNDNmNDcyOGIxZWY0YTMwYzc1NWMyMGJjZmU2YWRkNzczNCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Shiranui Flare")
                        .color(TextColor.color(0xFB4E26))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildNoel(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjYzMjQ2MTQ0NWU0YmZmOTIwMzlkZmNiODljNWIxN2ZkYzJkNzNhNWQxMTIwYjFjOTI3MWQ4NjQ0YTRlOGMwOCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Shirogane Noel")
                        .color(TextColor.color(0x8A939E))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildRushia(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjJmNzZiODFmN2VhZmRiZDc4MjUyZWY1ZGFiMmI0YWExY2FmZDRhY2Y2ZTAxMzdhMmExNDc5NzVlN2E4YWU5YyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Uruha Rushia")
                        .color(TextColor.color(0x0CE1BB))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildPekora(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiYmRiMDAxOTViZjU2OWFhNWZkYjBmNjFlNTBiZDkxMjM1ZWJiMmYzNmJhYTRhZDY2YjYyZjc0ZTU2ZGZhNSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Usada Pekora")
                        .color(TextColor.color(0x6BB8E7))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildKanata(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdhYmRhMGRiYzRhMWE1NDJjNTRhZTM4MDUxMDFhZWUxNWI4MDgyZDVkNjM5ZTI1NDNlODk4OGRjYWZmZTljIn19fQ==";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Amane Kanata")
                        .color(TextColor.color(0x75C1E7))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildLuna(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJkZTI2ODMxZjViNDQ2MTM1N2MyZWI4OWI2ODQ1ZDM0NzRiMjUyMjgyN2Y0MDY2ZGZlYThlZGMxYTgyNGY3MSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Himemori Luna")
                        .color(TextColor.color(0xD86EAD))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildCoco(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWZlODczZDI3Y2M1YzM5ODgwYjk0ZGQyZGJmNDViOWM3NTc4OWExOGVhNDQyZmJlZTFmYTI4YWI4N2FjMTk4MSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Kiryu Coco")
                        .color(TextColor.color(0xDC7310))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildTowa(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGY5NWZjMjMzOTJhZjdlNWM1YzIwYzc2YTIyMDhkZjQ5MjJiMTA4ZjJjODllMzM1MjI0MDI2MTJjNThkYjUwOCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Tokoyami Towa")
                        .color(TextColor.color(0x805A9E))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildWatame(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU4M2JhYWZhMjIwMGFhZGJhOWZlYTkxZTBkOGExNzRiYTEwZTNmYzY5NTU3M2M4YTUwZDAwMmYzMzhlNDg2YiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Tsunomaki Watame")
                        .color(TextColor.color(0xDBDB89))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildNene(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM0NjRlODY5ZjZhNmEwNGVhN2M0OTQ4Yjk4YjYyMzM0NWQ2ODViYTMyMzM4YTY5ZGIyOTcxOTJjZTE2NzcyNCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Momosuzu Nene")
                        .color(TextColor.color(0xFA7B10))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildPolka(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ3NmVjMWE0ZGQwNTllOWU5M2FkZWNmYTBkNGRkOThiYWM3MzFiMTFlMjNkMTMyYTdlODg5MTA4MzQ5Y2E1NyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Omaru Polka")
                        .color(TextColor.color(0xDCB3A6))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildBotan(HoloItemsRevamp plugin) {
        return new Idol(new BackdashBoots(plugin)) {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTYwMWM4MTJmMDM5N2M4MjEzOWFiNmJhZWE2MTRlNjlmZDhhYTNlNTYzYjU3NDM3YTlmZTA4Nzk1NzZmNmI1MSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Shishiro Botan")
                        .color(TextColor.color(0x9E8788))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildLamy(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmExNTlmMzIxMmViZTI2OWUwZmQ3YzA4NjZhMjc5MTRjOTFiNWZkM2Y2NzY3MmFmZjE1MGZiMDAzNTQwYTEyOCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Yukihana Lamy")
                        .color(TextColor.color(0x3D99CE))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildKoyori(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTkxMDUzZWI1MTA2NjdhYmNhMDE4Mzg0NzFhNmYxZGIzMDBlYzBiNTU1MWUzYmFiNWI3MmFiMDMyNGRiYzI1ZiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hakui Koyori")
                        .color(TextColor.color(0xFDB4CA))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildIroha(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWI1ZTA2ZTBhODViNjU3ZjFkZmU1YTU3Y2I3YzliZDdmZDZhYzQzYzYxODJlNWFkYTE5NWU3YzIwYjM3MTY0ZCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Kazama Iroha")
                        .color(TextColor.color(0xA0D9E8))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildLaplus(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRiMTgwNTYxNDVlZDgwMDIwOWRjZGRhYTFmOTFjNTRkMTkwNDM0M2FjODkwOTg3NDgzYzFkMjRjYjlmZTY5NCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("La+ Darknesss")
                        .color(TextColor.color(0x9A6EC7))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildChloe(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ3NmVjMWE0ZGQwNTllOWU5M2FkZWNmYTBkNGRkOThiYWM3MzFiMTFlMjNkMTMyYTdlODg5MTA4MzQ5Y2E1NyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Sakamata Chloe")
                        .color(TextColor.color(0x8A2E30))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildLui(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFmYWMzNTg1YWMyOTA1OTIxNmEwM2UwMTIzNGQ1MTg5MDU2ZGQ4YWI3ODJiMWYyNGU0OTlhY2M3NWUwMDUyZSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Takane Lui")
                        .color(TextColor.color(0xDA9190))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildIofi(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDNiOWQyNzJkNmFmYmUyOGYwNGI3ODljMjc1YzJlY2YwMzMyOTljYTY3MGM1MjI1MTU1YzIwODViMGNlMTUyZSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Airani Iofifteen")
                        .color(TextColor.color(0x70D30D))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildRisu(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY5ZTdmZTgxMjY5MjM5MzU1NDI0NWIxZjkzYTk1NTBkZGI0Y2M1Nzc1MjExYmY4ZTE2NzIyNTdkOTA2ZWU4YyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Ayunda Risu")
                        .color(TextColor.color(0xDC7977))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildMoona(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdjYzc4YzNlMmI5ZDczYmJiMTM3NzFjNGY1YzVlYzJlYjFmMGEzMzI0ODdjYzgxY2IwMDI5NjhjMGE2MzUzOSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Moona Hoshinova")
                        .color(TextColor.color(0x7148B3))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAnya(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU0N2YwZWI4ZjM2NDRlOTExMWE5NTdlMTExOTI0NWQ3YTQwMzJkZTIwMDk2ZTAzODg4OWUyZDk2M2FiNzRmNiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Anya Melfissa")
                        .color(TextColor.color(0xE89D10))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildOllie(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTljMWQ2NDQ3MGVmZmNlOTU1ZWRlYjIyMzRlODU1Zjg2OWM2OGJkMzdiYTQ1NjJmYmRlNGI5ZjI1ZDE1YWM2ZiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Kureiji Ollie")
                        .color(TextColor.color(0xD50D56))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildReine(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZGQyZjA2N2JiOTY1MjI5N2Q0ZWZlYmViMDlkODEyMThjYzM3NjllMjVlNTg0YTQ4ZDI2NWFhMmM5MzQ4MiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Pavolia Reine")
                        .color(TextColor.color(0x040F7F))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAchan(HoloItemsRevamp plugin) {
        return new Idol(new DummyBlockBlock(plugin)) {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTA0OGQ2ZGFlZWI4MDgyNjlhZmY1MzMzNzI0YWY4Y2Y4NTY1NmI5MjM2MGI0NTM1M2FmZjcyZWNhMmIwYzZkNiJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("A-chan")
                        .color(TextColor.color(0x534A92))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAruran(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmUxZDQ1YzgzOWQxOGI0OThiYmMyMWVhMWQwZmRjZGMxY2MwN2ZmYmQ4MzkzYjA0MjVmMTk2NmJiYjk2MThiMyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Arurandeisu")
                        .color(TextColor.color(0x3E815F))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildMiyabi(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM2NDQ4NDcyZjRiOWE0OGY4MDM0NThhZWUxMDgwZDliODJmNmFjYzE1OWYzMTJkZWUxZjAwZjM5MGYyYWVhNyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Hanasaki Miyabi")
                        .color(TextColor.color(0xFF4B5A))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildIzuru(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJhY2EzODBhODNkYTljY2ZhMjYyMWYzZGVkZGIxOWM0YTFiNDA5NWY1MjM3NTZhZjEzNGMyMzE2N2QwNTViZSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Kanade Izuru")
                        .color(TextColor.color(0x314BB6))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildRikka(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFhYmE3ZTZlNjBiY2M4MDgyY2QyNTExZjZkOTZjODJkYTMxODRhMGJiMmI2MmYxN2QyMDBkNDhjOTZiM2U3MyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Rikka")
                        .color(TextColor.color(0xFED2D5))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildAstel(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2MyNzRiZDdmMjdlMDc4NzE0NjAxMzI3MTQ2MzZhNzE2ZGQ2MTk4MmEyNzUxOTc3MjE2YTljYmZjZGQ5YWJiNCJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Astel Leda")
                        .color(TextColor.color(0x0B55BB))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildTemma(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmJhNTI1MjhjMTM4M2QwMzI5MzJjNzQxYTQ4ZWQyYTg3Y2JiMjJjOTllMWIzOTdkNTMyMjQ1NTE1NTU4M2MwYSJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Kishido Temma")
                        .color(TextColor.color(0xF5ED94))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildRoberu(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTIyMTlhOTliZmI2MDNlMjUzMjM5NTE5NWQzNzQ2ZGRlZTI1NWYyMTIyNjk5MzZiZDZjYTk3MjQ2MzRmZTg1In19fQ==";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Yukoku Roberu")
                        .color(TextColor.color(0xE56B00))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildOga(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQzODA0YzE5NTA0MTAwY2IwZWI1MjMxZGZjOWVjY2ZiMWIwNTRlNjRhNWU1OWQ3MTQ5NmU0ZjJhYzBiNjIzIn19fQ==";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Aragami Oga")
                        .color(TextColor.color(0xA4FB41))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }

    private static Idol buildShien(HoloItemsRevamp plugin) {
        return new Idol() {

            @Override
            public @NotNull String getSkinBase64() {
                return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI1YWRjYzU0M2FkYWIzYWY2OWNkOTFjOWY2NTg3ZTljOTY1MDEzNDljMmNkYjAxMjIzMTAzYzY2NDA1M2Y5NyJ9fX0=";
            }

            @Override
            public Component getDisplayName() {
                return Component.text("Kageyama Shien")
                        .color(TextColor.color(0x8B69AE))
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false);
            }

            @Override
            public List<Component> getLore() {
                return null;
            }
        };
    }
}
