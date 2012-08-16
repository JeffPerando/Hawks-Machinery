
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
	
	public static void addToolTips()
	{
		LanguageRegistry.instance().addNameForObject(BASEMOD.blockGrinder, "en_US", "Grinder");
		
		for (int counter = 0; counter <= 6; ++counter)
		{
			LanguageRegistry.addName(new ItemStack(BASEMOD.blockEmptyMachine, 1, counter), HawkItemBlockMachine.en_USNames[counter]);
		}
		
		for (int counter = 0; counter <= 10; ++counter)
		{
			if (HawkItemBlockOre.en_USNames[counter] != null)
			{
				LanguageRegistry.addName(new ItemStack(BASEMOD.blockOre, 1, counter), HawkItemBlockOre.en_USNames[counter]);
			}
		}
		
		for (int counter = 0; counter <= 3; ++counter)
		{
			LanguageRegistry.addName(new ItemStack(BASEMOD.blockMetalStorage, 1, counter), HawkItemBlockMetalStorage.en_USNames[counter]);
		}
	}
}
