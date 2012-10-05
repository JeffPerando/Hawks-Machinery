
package hawksmachinery;

import hawksmachinery.items.*;
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
	public static HMManager ACH;
	public static LanguageRegistry LANG = LanguageRegistry.instance();
	
	public static void addToolTips()
	{
		LANG.addNameForObject(BASEMOD.crusher, "en_US", "Crusher");
		LANG.addNameForObject(BASEMOD.washer, "en_US", "Washer");
		if (BASEMOD.MANAGER.enableChunkloader)
		{
			LANG.addNameForObject(BASEMOD.chunkloader, "en_US", "Endium Chunkloader");
		}
		
		LANG.addStringLocalization(ACH.timeToCrush.getName(), "en_US", "Time to Crush!");
		LANG.addStringLocalization(ACH.timeToCrush.getName() + ".desc", "en_US", "The matriarch of Hawk's Machinery!");
		
		LANG.addStringLocalization(ACH.prospector.getName(), "en_US", "Prospector");
		LANG.addStringLocalization(ACH.prospector.getName() + ".desc", "en_US", "There's Emerald in them hills!");
		
		LANG.addStringLocalization(ACH.wash.getName(), "en_US", "Workin' at the--");
		LANG.addStringLocalization(ACH.wash.getName() + ".desc", "en_US", "Wait, what?");
		
		for (int counter = 0; counter <= 12; ++counter)
		{
			if (counter <= 5)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.parts, 1, counter), "en_US", HMItemParts.en_USNames[counter]);
				
			}
			
			if (counter <= 9)
			{
				if (HMItemRawDust.en_USNames[counter] != "Coal" && HMItemRawDust.en_USNames[counter] != "Obsidian")
				{
					LANG.addNameForObject(new ItemStack(BASEMOD.dustRaw, 1, counter), "en_US", "Unrefined " + HMItemRawDust.en_USNames[counter] + " Dust");
				}
				else
				{
					LANG.addNameForObject(new ItemStack(BASEMOD.dustRaw, 1, counter), "en_US", HMItemRawDust.en_USNames[counter] + " Dust");
				}
				
			}
			
			if (counter <= 8)
			{
				LANG.addNameForObject(new ItemStack(BASEMOD.blueprints, 1, counter), "en_US", "Blueprints (" + HMItemBlueprints.en_USNames[counter] + ")");
			}
			
			LANG.addNameForObject(new ItemStack(BASEMOD.dustRefined, 1, counter), "en_US", HMItemRefinedDust.en_USNames[counter] + " Dust");
			
		}
		
	}
	
}
