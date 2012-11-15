
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
public class HMEndiumOreGen extends OreGenReplace
{
	public static HawksMachinery BASEMOD;
	
	public HMEndiumOreGen()
	{
		super("Endium Ore", "oreEndium", new ItemStack(HMBlock.ore), 121, 12, 64, 8, 8, "pickaxe", 3);
		this.generateEnd = true;
		this.shouldGenerate = BASEMOD.MANAGER.generateEndium;
		
	}
	
}
