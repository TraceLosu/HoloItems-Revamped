package xyz.holocons.mc.holoitemsrevamp.collection;

import com.strangeone101.holoitemsapi.CustomItem;
import xyz.holocons.mc.holoitemsrevamp.collection.en1.EN1Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.en2.EN2Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.gamers.GamersCollection;
import xyz.holocons.mc.holoitemsrevamp.collection.gen0.Gen0Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.gen1.Gen1Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.gen2.Gen2Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.gen3.Gen3Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.gen4.Gen4Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.gen5.Gen5Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.gen6.Gen6Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.id1.ID1Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.id2.ID2Collection;
import xyz.holocons.mc.holoitemsrevamp.collection.misc.MiscCollection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionManager {

    private final List<IdolCollection> idolCollections;
    private final Map<String, CustomItem> allItems;

    public CollectionManager(){
        idolCollections = List.of(
            new EN1Collection(),
            new EN2Collection(),
            new GamersCollection(),
            new Gen0Collection(),
            new Gen1Collection(),
            new Gen2Collection(),
            new Gen3Collection(),
            new Gen4Collection(),
            new Gen5Collection(),
            new Gen6Collection(),
            new ID1Collection(),
            new ID2Collection(),
            new MiscCollection()
        );
        //Creates a map from the list of idols, key is the internal name, value is the initialized class.
        allItems = idolCollections.stream()
                .flatMap(i -> i.getAllItem().stream())
                .collect(Collectors.toMap(CustomItem::getInternalName, string -> string));
    }

    /**
     * Gets a list of all generations
     * @return a list
     */
    public List<IdolCollection> getAllGens() {
        return idolCollections;
    }

    /**
     * Gets a map that contains all custom items as its values, and their internal name as the key. Used for
     * autocompletion
     * @return All custom items that the plugin contains
     */
    public Map<String, CustomItem> getAllItems() {
        return allItems;
    }
}
