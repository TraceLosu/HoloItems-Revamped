package xyz.holocons.mc.holoitemsrevamp;

import org.bukkit.entity.Entity;

import java.util.List;
import java.util.UUID;

public class EntityMarker extends ObjectMarker<UUID>{

    public EntityMarker(){

    }

    public void markObject(Entity obj, int ticks){
        super.markObject(obj.getUniqueId(), ticks);
    }

    public boolean isMarked(Entity obj){
        return super.isMarked(obj.getUniqueId());
    }
}
