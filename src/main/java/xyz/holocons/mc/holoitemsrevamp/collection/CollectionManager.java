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
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.collection.en1.GawrGura;
import xyz.holocons.mc.holoitemsrevamp.collection.en1.IRyS;
import xyz.holocons.mc.holoitemsrevamp.collection.en1.MoriCalliope;
import xyz.holocons.mc.holoitemsrevamp.collection.en1.NinomaeInanis;
import xyz.holocons.mc.holoitemsrevamp.collection.en1.TakanashiKiara;
import xyz.holocons.mc.holoitemsrevamp.collection.en1.WatsonAmelia;
import xyz.holocons.mc.holoitemsrevamp.collection.en2.CeresFauna;
import xyz.holocons.mc.holoitemsrevamp.collection.en2.HakosBaelz;
import xyz.holocons.mc.holoitemsrevamp.collection.en2.NanashiMumei;
import xyz.holocons.mc.holoitemsrevamp.collection.en2.OuroKronii;
import xyz.holocons.mc.holoitemsrevamp.collection.en2.TsukumoSana;
import xyz.holocons.mc.holoitemsrevamp.collection.gamers.InugamiKorone;
import xyz.holocons.mc.holoitemsrevamp.collection.gamers.NekomataOkayu;
import xyz.holocons.mc.holoitemsrevamp.collection.gamers.OokamiMio;
import xyz.holocons.mc.holoitemsrevamp.collection.gen0.AZKi;
import xyz.holocons.mc.holoitemsrevamp.collection.gen0.HoshimachiSuisei;
import xyz.holocons.mc.holoitemsrevamp.collection.gen0.Roboco;
import xyz.holocons.mc.holoitemsrevamp.collection.gen0.SakuraMiko;
import xyz.holocons.mc.holoitemsrevamp.collection.gen0.TokinoSora;
import xyz.holocons.mc.holoitemsrevamp.collection.gen1.AkaiHaato;
import xyz.holocons.mc.holoitemsrevamp.collection.gen1.AkiRosenthal;
import xyz.holocons.mc.holoitemsrevamp.collection.gen1.NatsuiroMatsuri;
import xyz.holocons.mc.holoitemsrevamp.collection.gen1.ShirakamiFubuki;
import xyz.holocons.mc.holoitemsrevamp.collection.gen1.YozoraMel;
import xyz.holocons.mc.holoitemsrevamp.collection.gen2.MinatoAqua;
import xyz.holocons.mc.holoitemsrevamp.collection.gen2.MurasakiShion;
import xyz.holocons.mc.holoitemsrevamp.collection.gen2.NakiriAyame;
import xyz.holocons.mc.holoitemsrevamp.collection.gen2.OozoraSubaru;
import xyz.holocons.mc.holoitemsrevamp.collection.gen2.YuzukiChoco;
import xyz.holocons.mc.holoitemsrevamp.collection.gen3.HoushouMarine;
import xyz.holocons.mc.holoitemsrevamp.collection.gen3.ShiranuiFlare;
import xyz.holocons.mc.holoitemsrevamp.collection.gen3.ShiroganeNoel;
import xyz.holocons.mc.holoitemsrevamp.collection.gen3.UruhaRushia;
import xyz.holocons.mc.holoitemsrevamp.collection.gen3.UsadaPekora;
import xyz.holocons.mc.holoitemsrevamp.collection.gen4.AmaneKanata;
import xyz.holocons.mc.holoitemsrevamp.collection.gen4.HimemoriLuna;
import xyz.holocons.mc.holoitemsrevamp.collection.gen4.KiryuCoco;
import xyz.holocons.mc.holoitemsrevamp.collection.gen4.TokoyamiTowa;
import xyz.holocons.mc.holoitemsrevamp.collection.gen4.TsunomakiWatame;
import xyz.holocons.mc.holoitemsrevamp.collection.gen5.MomosuzuNene;
import xyz.holocons.mc.holoitemsrevamp.collection.gen5.OmaruPolka;
import xyz.holocons.mc.holoitemsrevamp.collection.gen5.ShishiroBotan;
import xyz.holocons.mc.holoitemsrevamp.collection.gen5.YukihanaLamy;
import xyz.holocons.mc.holoitemsrevamp.collection.gen6.HakuiKoyori;
import xyz.holocons.mc.holoitemsrevamp.collection.gen6.KazamaIroha;
import xyz.holocons.mc.holoitemsrevamp.collection.gen6.LaplusDarknesss;
import xyz.holocons.mc.holoitemsrevamp.collection.gen6.SakamataChloe;
import xyz.holocons.mc.holoitemsrevamp.collection.gen6.TakaneLui;
import xyz.holocons.mc.holoitemsrevamp.collection.id1.AiraniIofifteen;
import xyz.holocons.mc.holoitemsrevamp.collection.id1.AyundaRisu;
import xyz.holocons.mc.holoitemsrevamp.collection.id1.MoonaHoshinova;
import xyz.holocons.mc.holoitemsrevamp.collection.id2.AnyaMelfissa;
import xyz.holocons.mc.holoitemsrevamp.collection.id2.KureijiOllie;
import xyz.holocons.mc.holoitemsrevamp.collection.id2.PavoliaReine;
import xyz.holocons.mc.holoitemsrevamp.collection.misc.Achan;
import xyz.holocons.mc.holoitemsrevamp.collection.stars1.Arurandeisu;
import xyz.holocons.mc.holoitemsrevamp.collection.stars1.HanasakiMiyabi;
import xyz.holocons.mc.holoitemsrevamp.collection.stars1.KanadeIzuru;
import xyz.holocons.mc.holoitemsrevamp.collection.stars1.Rikka;
import xyz.holocons.mc.holoitemsrevamp.collection.stars2.AstelLeda;
import xyz.holocons.mc.holoitemsrevamp.collection.stars2.KishidoTemma;
import xyz.holocons.mc.holoitemsrevamp.collection.stars2.YukokuRoberu;
import xyz.holocons.mc.holoitemsrevamp.collection.stars3.AragamiOga;
import xyz.holocons.mc.holoitemsrevamp.collection.stars3.KageyamaShien;
import xyz.holocons.mc.holoitemsrevamp.item.BackdashBoots;
import xyz.holocons.mc.holoitemsrevamp.item.DummyBlockBlock;
import xyz.holocons.mc.holoitemsrevamp.item.MagnetBook;
import xyz.holocons.mc.holoitemsrevamp.item.MementoItem;
import xyz.holocons.mc.holoitemsrevamp.item.TideRiderItem;

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
    public List<IdolCollection> getAllGens() {
        return idolCollections;
    }

    /**
     * @return all custom items that the plugin contains
     */
    public Map<String, CustomItem> getAllItems() {
        return customItems;
    }

    private static List<IdolCollection> buildIdolCollections(HoloItemsRevamp plugin) {
        var gura = new GawrGura(
                new TideRiderItem(plugin));
        var irys = new IRyS();
        var calliope = new MoriCalliope(
                new MementoItem(plugin));
        var ina = new NinomaeInanis();
        var kiara = new TakanashiKiara();
        var amelia = new WatsonAmelia();
        var en1 = new IdolCollection(gura, irys, calliope, ina, kiara, amelia) {

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

        var fauna = new CeresFauna();
        var baelz = new HakosBaelz();
        var mumei = new NanashiMumei();
        var kronii = new OuroKronii();
        var sana = new TsukumoSana();
        var en2 = new IdolCollection(fauna, baelz, mumei, kronii, sana) {

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

        var korone = new InugamiKorone();
        var okayu = new NekomataOkayu();
        var mio = new OokamiMio();
        var gamers = new IdolCollection(korone, okayu, mio) {

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

        var azki = new AZKi();
        var suisei = new HoshimachiSuisei();
        var roboco = new Roboco(
                new MagnetBook(plugin));
        var miko = new SakuraMiko();
        var sora = new TokinoSora();
        var gen0 = new IdolCollection(azki, suisei, roboco, miko, sora) {

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

        var haato = new AkaiHaato();
        var aki = new AkiRosenthal();
        var matsuri = new NatsuiroMatsuri();
        var fubuki = new ShirakamiFubuki();
        var mel = new YozoraMel();
        var gen1 = new IdolCollection(haato, aki, matsuri, fubuki, mel) {

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

        var aqua = new MinatoAqua();
        var shion = new MurasakiShion();
        var ayame = new NakiriAyame();
        var subaru = new OozoraSubaru();
        var choco = new YuzukiChoco();
        var gen2 = new IdolCollection(aqua, shion, ayame, subaru, choco) {

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

        var marine = new HoushouMarine();
        var flare = new ShiranuiFlare();
        var noel = new ShiroganeNoel();
        var rushia = new UruhaRushia();
        var pekora = new UsadaPekora();
        var gen3 = new IdolCollection(marine, flare, noel, rushia, pekora) {

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

        var kanata = new AmaneKanata();
        var luna = new HimemoriLuna();
        var coco = new KiryuCoco();
        var towa = new TokoyamiTowa();
        var watame = new TsunomakiWatame();
        var gen4 = new IdolCollection(kanata, luna, coco, towa, watame) {

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

        var nene = new MomosuzuNene();
        var polka = new OmaruPolka();
        var botan = new ShishiroBotan(
                new BackdashBoots(plugin));
        var lamy = new YukihanaLamy();
        var gen5 = new IdolCollection(nene, polka, botan, lamy) {

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

        var koyori = new HakuiKoyori();
        var iroha = new KazamaIroha();
        var laplus = new LaplusDarknesss();
        var chloe = new SakamataChloe();
        var lui = new TakaneLui();
        var gen6 = new IdolCollection(koyori, iroha, laplus, chloe, lui) {

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

        var iofi = new AiraniIofifteen();
        var risu = new AyundaRisu();
        var moona = new MoonaHoshinova();
        var id1 = new IdolCollection(iofi, risu, moona) {

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

        var anya = new AnyaMelfissa();
        var ollie = new KureijiOllie();
        var reine = new PavoliaReine();
        var id2 = new IdolCollection(anya, ollie, reine) {

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

        var achan = new Achan(
                new DummyBlockBlock(plugin));
        var misc = new IdolCollection(achan) {

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

        var aruran = new Arurandeisu();
        var miyabi = new HanasakiMiyabi();
        var izuru = new KanadeIzuru();
        var rikka = new Rikka();
        var stars1 = new IdolCollection(aruran, miyabi, izuru, rikka) {

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

        var astel = new AstelLeda();
        var temma = new KishidoTemma();
        var roberu = new YukokuRoberu();
        var stars2 = new IdolCollection(astel, temma, roberu) {

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

        var oga = new AragamiOga();
        var shien = new KageyamaShien();
        var stars3 = new IdolCollection(oga, shien) {

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
}
