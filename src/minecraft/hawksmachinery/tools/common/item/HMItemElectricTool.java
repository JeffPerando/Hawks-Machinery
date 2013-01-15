
package hawksmachinery.tools.common.item;

import hawksmachinery.core.common.item.HMItemElectric;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemElectricTool extends HMItemElectric
{
	public final float toolSpeed;
	
	public HMItemElectricTool(int id, float speed)
	{
		super(id, 50000, 120);
		setFull3D();
		toolSpeed = speed;
		
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, int blockID, int x, int y, int z, EntityLiving entity)
	{
		if (ForgeHooks.canToolHarvestBlock(Block.blocksList[blockID], world.getBlockMetadata(x, y, z), item))
		{
			this.onUse(250, item);
			
		}
		else
		{
			this.onUse(500, item);
			
		}
		
		return true;
	}
	
	@Override
	public float getStrVsBlock(ItemStack item, Block block, int meta)
	{
		return ForgeHooks.canToolHarvestBlock(block, meta, item) ? this.toolSpeed : 1.0F;
	}
	
}
