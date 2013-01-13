
package hawksmachinery.core.common;

import hawksmachinery.core.common.block.HMBlock;
import net.minecraft.item.ItemStack;
import universalelectricity.prefab.ore.OreGenReplace;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCobaltOreGen extends OreGenReplace
{
	public HMCobaltOreGen()
	{
		super("Cobalt Ore", "oreCobalt", new ItemStack(HMCore.ore, 1, 1), 1, 12, 30, 32, 1, "pickaxe", 2);
		this.shouldGenerate = HMCore.instance().MANAGER.generateCobalt;
		
	}
	
}
