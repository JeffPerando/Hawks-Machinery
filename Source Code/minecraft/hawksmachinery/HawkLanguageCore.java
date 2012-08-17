
package hawksmachinery;

import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * This class manages language support for Hawk's Machinery.
 * 
 * @author Elusivehawk
 */
public class HawkLanguageCore
{
	public static HawksMachinery BASEMOD;
	public static HawkAchievements ACH;
	
	public static void addToolTips()
	{
		LanguageRegistry.instance().addNameForObject(BASEMOD.blockGrinder, "en_US", "Grinder");
		
		
		LanguageRegistry.instance().addStringLocalization(ACH.shellOfAMachine.getName(), "en_US", "Shell of a Machine");
		LanguageRegistry.instance().addStringLocalization(ACH.shellOfAMachine.getName() + ".desc", "en_US", "The hull of a machine");
		
		LanguageRegistry.instance().addStringLocalization(ACH.buildABetterMachineBlock.getName(), "en_US", "Build a Better Machine Block");
		LanguageRegistry.instance().addStringLocalization(ACH.buildABetterMachineBlock.getName() + ".desc", "en_US", "No, seriously, build one.");
		
		LanguageRegistry.instance().addStringLocalization(ACH.redstonedWithCare.getName(), "en_US", "Redstoned With Care");
		LanguageRegistry.instance().addStringLocalization(ACH.redstonedWithCare.getName() + ".desc", "en_US", "Isn't Redstone awesome?");
		
		LanguageRegistry.instance().addStringLocalization(ACH.timeToGrind.getName(), "en_US", "Time to Grind!");
		LanguageRegistry.instance().addStringLocalization(ACH.timeToGrind.getName() + ".desc", "en_US", "The matriarch of Hawk's Machinery!");
		
		LanguageRegistry.instance().addStringLocalization(ACH.minerkiin.getName(), "en_US", "Minerkiin");
		LanguageRegistry.instance().addStringLocalization(ACH.minerkiin.getName() + ".desc", "en_US", "In their tongue...");
		
		LanguageRegistry.instance().addStringLocalization(ACH.spartaMiner.getName(), "en_US", "Spartan Miner");
		LanguageRegistry.instance().addStringLocalization(ACH.spartaMiner.getName() + ".desc", "en_US", "TONIGHT, WE MINE, IN HELL!");
		
		LanguageRegistry.instance().addStringLocalization(ACH.compactCompact.getName(), "en_US", "Compact Compact");
		LanguageRegistry.instance().addStringLocalization(ACH.compactCompact.getName() + ".desc", "en_US", "Am I missing, an eyebrow?");
		
		
		for (int counter = 0; counter <= 6; ++counter)
		{
			LanguageRegistry.instance().addNameForObject(new ItemStack(BASEMOD.blockEmptyMachine, 1, counter), "en_US", HawkItemBlockMachine.en_USNames[counter] + " Machine Block");
		}
		
		for (int counter = 0; counter <= 10; ++counter)
		{
			if (HawkItemBlockOre.en_USNames[counter] != null)
			{
				LanguageRegistry.instance().addNameForObject(new ItemStack(BASEMOD.blockOre, 1, counter), "en_US", HawkItemBlockOre.en_USNames[counter]);
			}
		}
		
		for (int counter = 0; counter <= 3; ++counter)
		{
			LanguageRegistry.instance().addNameForObject(new ItemStack(BASEMOD.blockMetalStorage, 1, counter), "en_US", HawkItemBlockMetalStorage.en_USNames[counter]);
		}
	}
}
