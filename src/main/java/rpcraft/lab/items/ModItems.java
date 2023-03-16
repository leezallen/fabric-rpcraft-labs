package rpcraft.lab.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import rpcraft.lab.LabMain;

import static rpcraft.lab.LabMain.MODID;


public class ModItems {

	// Define item details
	public static final Item EYEPAD = AddLabItem("eyepad", ModItemGroups.RPCLAB, 1);
	public static final Item URANIUM_ORE = AddLabItem("uraniumore", ModItemGroups.RPCLAB, 16);
	public static final Item URANIUM_INGOT = AddLabItem("uraniumingot", ModItemGroups.RPCLAB, 16);
	public static final Item LITHIUM_ORE = AddLabItem("lithiumore", ModItemGroups.RPCLAB, 64);
	public static final Item LITHIUM_INGOT = AddLabItem("lithiumingot", ModItemGroups.RPCLAB, 64);
	
	public static Item AddLabItem(String name, ItemGroup tab, int maxCount) {
		LabMain.LOGGER.info("Registering item: " + name);
	    Item item = new Item(new FabricItemSettings().group(tab).maxCount(maxCount));
	    Identifier itemID = new Identifier(MODID, name);
	    Registry.register(Registry.ITEM, itemID, item);
	    return item;
	}
	
	public static void RegisterModItems () {
		LabMain.LOGGER.debug("Registered items for " + LabMain.MODID);
	}
	

}
