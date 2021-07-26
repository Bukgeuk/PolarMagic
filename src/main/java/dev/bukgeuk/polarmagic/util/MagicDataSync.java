package dev.bukgeuk.polarmagic.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.UUID;

public class MagicDataSync {
    private final static HashMap<UUID, MagicData> table = new HashMap<>();
    private static MagicData clientData = null;
    public final static Identifier packetChannel = new Identifier("polarmagic", "magicdata");

    public static MagicData getData(UUID uuid) { return table.get(uuid); }

    public static void setData(UUID uuid, MagicData data, Boolean isClient, ServerPlayerEntity player) {
        table.put(uuid, data);

        if (!isClient && player != null) {
            sendData(data, player);
        }
    }

    public static void setData(HashMap<UUID, MagicData> map, Boolean isClient, ServerPlayerEntity player) {
        map.forEach((uuid, data) -> setData(uuid, data, isClient, player));
    }

    public static void sendData(MagicData data, ServerPlayerEntity player) {
        PacketByteBuf buf = ByteKt.convertMagicDataToPacketByteBuf(data);
        ServerPlayNetworking.send(player, packetChannel, buf);
    }

    public static void setClientData(MagicData data) {
        clientData = data;
    }

    public static MagicData getClientData() {
        return clientData;
    }
}