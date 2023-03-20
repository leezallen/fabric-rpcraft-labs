package rpcraft.lab;

import net.fabricmc.api.ModInitializer;
import rpcraft.lab.blocks.ModBlocks;
import rpcraft.lab.items.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rpcraft.lab.networking.ModMessagePackets;
import rpcraft.lab.world.feature.ModConfiguredFeatures;
import rpcraft.lab.world.gen.ModOreGeneration;

public class LabMain implements ModInitializer {

	public static final String MODID = "rpcraftlab";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {

		LOGGER.info("RPCraft Lab Initializing... It will (probably) be OK!");
		ModConfiguredFeatures.registerConfiguredFeatures();

		ModBlocks.RegisterModBlocks();
		ModItems.RegisterModItems();

		ModMessagePackets.registerC2SPackets();

		ModOreGeneration.generateOres();
		LOGGER.info("RPCraft Lab Initialized...");
	}
}
