package dev.bukgeuk.polarmagic.util;

import net.minecraft.entity.EntityType;

import java.io.Serializable;
import java.util.UUID;

public class MagicCircleEntityData implements Serializable {
    public EntityType<?> type;
    public UUID entityUUID;
    public int entityID;
    public double x;
    public double y;
    public double z;
    public float pitch;
    public float yaw;
    
    public MagicCircleEntityData(EntityType<?> type, UUID entityUUID, int entityID, double x, double y, double z, float pitch, float yaw) {
        this.type = type;
        this.entityUUID = entityUUID;
        this.entityID = entityID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }
}
