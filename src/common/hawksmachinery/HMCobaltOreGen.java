
package hawksmachinery;

import hawksmachinery.block.HMBlock;
import net.minecraft.src.ItemStack;
import universalelectricity.prefab.ore.OreGenReplace;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCobaltOreGen extends OreGenReplace
{
	public static HawksMachinery BASEMOD;
	
	public HMCobaltOreGen()
	{
		super("Cobalt Ore", "oreCobalt", new ItemStack(HMBlock.ore, 1, 1), 1, 12, 30, 4, 1, "pickaxe", 2);
		this.generateSurface = true;
		this.shouldGenerate = BASEMOD.MANAGER.generateCobalt;
		
	}
	
}
