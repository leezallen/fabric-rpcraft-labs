package rpcraft.lab.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import rpcraft.lab.LabMain;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class LabMixin {
	
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		LabMain.LOGGER.info("beep boop... mixin doing something....");
	}
}
