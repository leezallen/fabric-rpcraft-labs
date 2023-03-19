package rpcraft.lab.items.custom;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import rpcraft.lab.datatypes.eNUMTabletMode;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EyePadItem extends Item {

    public static eNUMTabletMode TABLET_MODE;
    private static final String MODE_TAG = "currentMode";
    public EyePadItem(Settings settings) {
        super(settings);
    }

    // Get the current mode of the item from its NBT tag
    public static eNUMTabletMode getCurrentMode(ItemStack itemStack) {
        NbtCompound tag = itemStack.getOrCreateNbt();
        if (tag.contains(MODE_TAG)) {
            return eNUMTabletMode.valueOf(tag.getString(MODE_TAG));
        } else {
            return eNUMTabletMode.SLEEP;
        }
    }
    // Set the current mode of the item in its NBT tag
    public static eNUMTabletMode setNextMode(ItemStack itemStack) {
        NbtCompound tag = itemStack.getOrCreateNbt();
        eNUMTabletMode mode = getCurrentMode(itemStack).getNextMode();
        tag.putString(MODE_TAG, mode.name());
        itemStack.setNbt(tag);
        return mode;
    }
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        // Stops block destruction but does not seem to cancel the actual mining.
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("Check controls for the key to change modes").formatted(Formatting.LIGHT_PURPLE));
        } else {
            tooltip.add(Text.literal("Current Mode: " + getCurrentMode(stack).name()).formatted(Formatting.LIGHT_PURPLE));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);
        if(!world.isClient) {
            eNUMTabletMode mode = getCurrentMode(itemStack);
            switch (mode) {
                case TELEPORT:
                    tabletModeTeleport(world, user, hand);
                    break;
                default:
                    break;
            }
        }

        return super.use(world, user, hand);

    }

    private void tabletModeTeleport (World world, PlayerEntity user, Hand hand) {


        ItemStack stack = user.getStackInHand(hand);
        NbtCompound tag = stack.getOrCreateNbt();

        if(!world.isClient() && hand == Hand.MAIN_HAND) {
            // Different outcomes based on if the user is sneaking or not
            if(user.isSneaking()) {
                // Process on server when in players Main Hand
                tag.putDouble("x", user.getX());
                tag.putDouble("y", user.getY());
                tag.putDouble("z", user.getZ());
                tag.putString("dim", world.getRegistryKey().getValue().toString());

                user.sendMessage(Text.literal("Teleport destination is set.").formatted(Formatting.LIGHT_PURPLE),true);
                // add a cooldown
                user.getItemCooldownManager().set(this, 20);
            }
            else if (tag.contains("x") && tag.contains("y") && tag.contains("z")) {

                double x = tag.getDouble("x");
                double y = tag.getDouble("y");
                double z = tag.getDouble("z");
                String dim = tag.getString("dim");
                if (dim.equals(world.getRegistryKey().getValue().toString())) {
                    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    CountdownTeleport countdownTeleport = new CountdownTeleport(user, x, y, z, scheduler);
                    //final int delay = 3;
                    final int interval = 1;
                    scheduler.scheduleAtFixedRate(countdownTeleport, interval, interval, TimeUnit.SECONDS);
                    user.getItemCooldownManager().set(this, 80);
                } else {
                    user.sendMessage(Text.literal("Teleporting does not work between dimensions. Your point is in " + dim).formatted(Formatting.GOLD),true);
                }
            } else {
                user.sendMessage(Text.literal("Sneak and right click to save this position").formatted(Formatting.LIGHT_PURPLE),true);
                user.getItemCooldownManager().set(this, 20);
            }

        }
    }

    private static void playerTeleportEffect (PlayerEntity player, ServerWorld world) {
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.5f);
        world.spawnParticles(ParticleTypes.LARGE_SMOKE, player.getX(), player.getY() + 1, player.getZ(), 100, 0.5, 0.5, 0.5, 0.01);
        world.spawnParticles(ParticleTypes.PORTAL, player.getX(), player.getY() + 1, player.getZ(), 150, 0.5, 0.5, 0.5, 1);
    }

    private static class CountdownTeleport implements Runnable {
        private final PlayerEntity player;
        private final double x;
        private final double y;
        private final double z;
        private final ScheduledExecutorService scheduler;
        private int remainingTime;



        public CountdownTeleport(PlayerEntity player, double x, double y, double z, ScheduledExecutorService scheduler) {
            this.player = player;
            this.x = x;
            this.y = y;
            this.z = z;
            this.scheduler = scheduler;
            this.remainingTime = 3;

        }

        @Override
        public void run() {

            ServerWorld world = (ServerWorld) player.getEntityWorld();

            if (remainingTime <= 0) {

                playerTeleportEffect(player, world);
                player.teleport(x, y+0.5, z);
                player.setVelocity(0,0,0);
                //noinspection RedundantCast
                ((LivingEntity) player).fallDistance=0; // Fixes issue #1 Teleporting still gives fall damage.
                playerTeleportEffect(player, world);

                scheduler.shutdown();

                return;
            }

            // Spawn purple particles around the player
            for (int i = 0; i < 10; i++) {
                double offsetX = player.getRandom().nextGaussian() * 0.2;
                double offsetY = player.getRandom().nextGaussian() * 0.2;
                double offsetZ = player.getRandom().nextGaussian() * 0.2;
                world.spawnParticles(ParticleTypes.PORTAL, player.getX() + offsetX, player.getY() + offsetY, player.getZ() + offsetZ, 20 * (4-remainingTime), 0.5, 0.5, 0.5, 1);
            }

            player.sendMessage(Text.literal("Teleporting in " + remainingTime + " seconds...").formatted(Formatting.LIGHT_PURPLE),true);
            remainingTime--;
        }

    }

}
