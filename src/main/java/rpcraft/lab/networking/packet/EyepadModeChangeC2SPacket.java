package rpcraft.lab.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import rpcraft.lab.datatypes.eNUMTabletMode;
import rpcraft.lab.items.custom.EyePadItem;

public class EyepadModeChangeC2SPacket {
    private static final String MESSAGE_CHANGED_MODES = "message.rpcraftlab.eyepad.changedmode";
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender ) {
        // Reminder: Everything here happens only on the server.
        ItemStack heldItemStack = player.getStackInHand(player.getActiveHand());
        if(heldItemStack.getItem() instanceof EyePadItem eyepadItem){
            //eNUMTabletMode nextMode = eyepadItem.setNextMode(heldItemStack);
            eNUMTabletMode nextMode = eyepadItem.setNextMode(heldItemStack);
            player.sendMessage(Text.translatable(MESSAGE_CHANGED_MODES.formatted(Formatting.LIGHT_PURPLE), nextMode.name()),true);
        }
    }
}
