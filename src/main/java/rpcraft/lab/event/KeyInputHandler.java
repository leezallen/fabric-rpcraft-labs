package rpcraft.lab.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import rpcraft.lab.items.custom.EyePadItem;
import rpcraft.lab.networking.ModMessagePackets;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_RPCLAB = "key.category.rpcraftlab";
    public static final String KEY_EYEPAD_CHANGE_MODE = "key.rpcraftlab.eyepad.changemode";

    public static KeyBinding eyepadMode;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (eyepadMode.wasPressed()) {
                // eyepad mode cycle
                PlayerEntity user = client.player;
                ItemStack heldItemStack = user.getStackInHand(user.getActiveHand());
                if(heldItemStack.getItem() instanceof EyePadItem eyepadItem){
                    // User is holding a Eyepad, send a packet to the server to update the
                    ClientPlayNetworking.send(ModMessagePackets.EYEPAD_UPDATE, PacketByteBufs.create());
                }
            }
        });
    }

    public static void register() {
        eyepadMode = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_EYEPAD_CHANGE_MODE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KEY_CATEGORY_RPCLAB
        ));

        registerKeyInputs();
    }
}
