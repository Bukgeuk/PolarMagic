package dev.bukgeuk.polarmagic.util;

import java.util.HashMap;
import java.util.UUID;

public class MagicDataTable {
    private final static HashMap<UUID, MagicData> table = new HashMap<>();

    public static MagicData getData(UUID uuid) { return table.get(uuid); }

    public static void setData(UUID uuid, MagicData data) { table.put(uuid, data); }
}