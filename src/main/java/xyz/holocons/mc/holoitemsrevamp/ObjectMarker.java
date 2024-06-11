package xyz.holocons.mc.holoitemsrevamp;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Can be used to "mark" an object for a certain amount of ticks. The most obvious use-case is for cooldowns (plow's
 * block-breaking cooldown, comet and ukw's attack cooldown) however it does have some other uses, such as Holy Fire
 * (though that one is similar to a cooldown.) Note: Generics are used since certain cases need player cooldowns (plow,
 * comet, ukw) and other cases need block cooldowns (holy fire, maybe launch pad?)
 */
public class ObjectMarker<T> {

    // Map from marked objects to when that object will no longer be marked
    private final Map<T, Integer> markedObjects = new HashMap<>();

    public ObjectMarker(){

    }

    /**
     * Mark an object for a certain number of ticks.
     * @return Whether the markedObjects map was changed or updated.
     * @implNote This marks full ticks. Therefore, if you call markObject(obj, 0) followed immediately by isMarked(obj),
     * the second one will return true.
     */
    public boolean markObject(T obj, int ticks){
        // Should I have a "bool force" parameter to force-mark the object? So -1 can be used with force to
        //  force-unmark an object, or a smaller number can be used to accelerate object marking?
        if(ticks < 0){
            return false;
        }
        var currentTick = getCurrentTick();
        var stopTick = currentTick + ticks;
        var prevStopTick = markedObjects.getOrDefault(obj, -1);
        if(prevStopTick >= stopTick){
            // The existing stop-tick is later than the new proposed stop-tick
            return false;
        }
        // New proposed stop-tick is different and good
        markedObjects.put(obj, stopTick);
        return true;
    }

    /**
     * Checks whether an object is marked. See markObject for more details on marking.
     */
    public boolean isMarked(T obj){
        var currentTick = getCurrentTick();
        var stopTick = markedObjects.getOrDefault(obj, -1);
        // <= used to implement the functionality listed in markObject
        // (ie, 0 ticks should mark for the rest of the current tick)
        return currentTick <= stopTick;
    }

    /**
     * Returns a list of marked objects. Off the top of my head, Holy Fire might need this depending on implementation.
     */
    public List<T> getMarkedObjects(){
        // Is it worth it to implement a cleanup function?
        // This function isn't used (yet) so I'm not gonna bother, but in the future it might be worth
        // implementing a cleanupMarkedObjects() just for this.
        List<T> ret = new LinkedList<>();
        markedObjects.forEach((k, v) ->{
            if(isMarked(k)) ret.add(k);
        });
        return ret;
    }

    private int getCurrentTick(){
        return Bukkit.getCurrentTick();
    }
}
