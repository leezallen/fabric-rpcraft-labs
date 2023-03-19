package rpcraft.lab;

import net.fabricmc.api.ClientModInitializer;
import rpcraft.lab.event.KeyInputHandler;
import rpcraft.lab.networking.ModMessagePackets;

public class LabMainClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();
        ModMessagePackets.registerS2CPackets();
    }
}
