package rpcraft.lab;

import net.fabricmc.api.ModInitializer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LabMain implements ModInitializer {

	public static final String MODID = "rpcraftlab";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {

		LOGGER.info("RPCraft Lab Initializing... It will (probably) be OK!");
		RegisterBlocks.RegisterAllBlocks();
		
		// Registry.register(Registry.BLOCK, new Identifier("lab", "hazard_block"), HAZARD_BLOCK);
		// Registry.register(Registry.ITEM, new Identifier("lab", "hazard_block"), new BlockItem(HAZARD_BLOCK, new FabricItemSettings()));
	}
}
