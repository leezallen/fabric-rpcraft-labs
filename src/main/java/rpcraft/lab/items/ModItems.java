package rpcraft.lab.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import rpcraft.lab.LabMain;
import rpcraft.lab.items.custom.EyePadItem;

import static rpcraft.lab.LabMain.MODID;


public class ModItems {

	// Define item details
	public static final Item EYEPAD = RegisterItem("eyepad", new EyePadItem(new FabricItemSettings().group(ModItemGroups.RPCLAB).maxCount(1)));
	public static final Item URANIUM_RAW = RegisterItem("uranium_raw", new Item(new FabricItemSettings().group(ModItemGroups.RPCLAB).maxCount(16)));
	public static final Item URANIUM_INGOT = RegisterItem("uranium_ingot", new Item(new FabricItemSettings().group(ModItemGroups.RPCLAB).maxCount(16)));
	public static final Item LITHIUM_RAW = RegisterItem("lithium_raw", new Item(new FabricItemSettings().group(ModItemGroups.RPCLAB)));
	public static final Item LITHIUM_INGOT = RegisterItem("lithium_ingot", new Item(new FabricItemSettings().group(ModItemGroups.RPCLAB)));
	
	private static Item RegisterItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(MODID, name), item);
	}

	public static void RegisterModItems () {
		LabMain.LOGGER.debug("Registered items for " + LabMain.MODID);
	}
	

}
