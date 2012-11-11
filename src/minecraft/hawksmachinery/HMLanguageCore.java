
package hawksmachinery;

import hawksmachinery.blocks.*;
import hawksmachinery.items.*;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * This class manages language support for Hawk's Machinery.
 * 
 * @author Elusivehawk
 */
public class HMLanguageCore
{
	public static HawksMachinery BASEMOD;
	public static LanguageRegistry LANG = LanguageRegistry.instance();
	public static final String[] langs = new String[]{"en_US"};
	
	public static void addToolTips()
	{
		LANG.addNameForObject(HMBlock.crusher, "en_US", "Crusher");
		LANG.addNameForObject(HMBlock.washer, "en_US", "Washer");
		LANG.addNameForObject(HMBlock.endiumChunkloader, "en_US", "Endium Chunkloader");
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter), "en_US", "Endium Teleporter (Send)");
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter, 1, 1), "en_US", "Endium Teleporter (Receive)");
		
		LANG.addNameForObject(new ItemStack(HMBlock.ore), "en_US", "Endium Ore");
		LANG.addNameForObject(new ItemStack(HMBlock.ore, 1, 1), "en_US", "Cobalt Ore");
		
		LANG.addNameForObject(HMItem.rivetGun, "en_US", "Rivet Gun");
		LANG.addNameForObject(HMItem.fishFood, "en_US", "Fish Food");
		
		LANG.addStringLocalization(BASEMOD.timeToCrush.getName(), "en_US", "Time to Crush!");
		LANG.addStringLocalization(BASEMOD.timeToCrush.getName() + ".desc", "en_US", "Craft a Crusher.");
		LANG.addStringLocalization(BASEMOD.minerkiin.getName(), "en_US", "Minerkiin");
		LANG.addStringLocalization(BASEMOD.minerkiin.getName() + ".desc", "en_US", "Mine some Endium.");
		LANG.addStringLocalization(BASEMOD.wash.getName(), "en_US", "Workin' at the--");
		LANG.addStringLocalization(BASEMOD.wash.getName() + ".desc", "en_US", "Craft a Washer.");
		
	}
	
}
