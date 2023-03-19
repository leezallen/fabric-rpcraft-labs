package rpcraft.lab.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import rpcraft.lab.LabMain;
import rpcraft.lab.networking.packet.EyepadModeChangeC2SPacket;

public class ModMessagePackets {
    public static final Identifier EYEPAD_UPDATE = new Identifier(LabMain.MODID, "eyepadupdate");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(EYEPAD_UPDATE, EyepadModeChangeC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}
