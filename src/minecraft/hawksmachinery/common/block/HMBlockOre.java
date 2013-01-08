
package hawksmachinery.common.block;

import hawksmachinery.common.HawksMachinery;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockOre extends HMBlock
{
	public HMBlockOre(int id)
	{
		super(id, Material.rock, -1, null);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(HawksMachinery.instance().tab);
		
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 227 + meta;
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) != 0;
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		
	}
	
}
