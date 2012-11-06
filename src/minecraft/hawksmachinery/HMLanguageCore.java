
package hawksmachinery;

import hawksmachinery.blocks.HMBlock;
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
	
	public static void addToolTips()
	{
		LANG.addNameForObject(HMBlock.crusher, "en_US", "Crusher");
		LANG.addNameForObject(HMBlock.washer, "en_US", "Washer");
		LANG.addNameForObject(HMBlock.endiumOre, "en_US", "Endium Ore");
		if (BASEMOD.MANAGER.enableChunkloader)
		{
			LANG.addNameForObject(HMBlock.endiumChunkloader, "en_US", "Endium Chunkloader");
		}
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter), "en_US", "Endium Teleporter (Send)");
		LANG.addNameForObject(new ItemStack(HMBlock.endiumTeleporter, 1, 1), "en_US", "Endium Teleporter (Receive)");
		
		LANG.addNameForObject(HMItem.rivetGun, "en_US", "Rivet Gun");
		LANG.addNameForObject(new ItemStack(HMItem.endiumPlate), "en_US", "Endium Plate");
		
		LANG.addStringLocalization(BASEMOD.timeToCrush.getName(), "en_US", "Time to Crush!");
		LANG.addStringLocalization(BASEMOD.timeToCrush.getName() + ".desc", "en_US", "Craft a Crusher.");
		LANG.addStringLocalization(BASEMOD.minerkiin.getName(), "en_US", "Minerkiin");
		LANG.addStringLocalization(BASEMOD.minerkiin.getName() + ".desc", "en_US", "Mine some Endium.");
		LANG.addStringLocalization(BASEMOD.wash.getName(), "en_US", "Workin' at the--");
		LANG.addStringLocalization(BASEMOD.wash.getName() + ".desc", "en_US", "Craft a Washer.");
		
		for (int counter = 0; counter <= 9; ++counter)
		{
			if (counter == 0)
			{
				LANG.addNameForObject(new ItemStack(HMItem.ingots, 1, counter), "en_US", HMItemIngots.en_USNames[counter] + " Ingot");
				
			}
			
			if (counter <= 5)
			{
				LANG.addNameForObject(new ItemStack(HMItem.rivets, 1, counter), "en_US", HMItemRivets.en_USNames[counter] + " Rivet");
				
			}
			
			if (counter <= 6)
			{
				if (HMItemRawDust.en_USNames[counter] != "Coal" && HMItemRawDust.en_USNames[counter] != "Obsidian")
				{
					LANG.addNameForObject(new ItemStack(HMItem.dustRaw, 1, counter), "en_US", "Unrefined " + HMItemRawDust.en_USNames[counter] + " Dust");
				}
				else
				{
					LANG.addNameForObject(new ItemStack(HMItem.dustRaw, 1, counter), "en_US", HMItemRawDust.en_USNames[counter] + " Dust");
				}
				
				LANG.addNameForObject(new ItemStack(HMItem.parts, 1, counter), "en_US", HMItemParts.en_USNames[counter]);
				
			}
			
			if (counter <= 8)
			{
				LANG.addNameForObject(new ItemStack(HMItem.blueprints, 1, counter), "en_US", "Blueprints (" + HMItemBlueprints.en_USNames[counter] + ")");
			}
			
			LANG.addNameForObject(new ItemStack(HMItem.dustRefined, 1, counter), "en_US", HMItemRefinedDust.en_USNames[counter] + " Dust");
			
		}
		
	}
	
}
