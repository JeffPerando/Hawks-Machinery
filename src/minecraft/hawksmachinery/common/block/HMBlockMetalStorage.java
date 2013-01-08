
package hawksmachinery.common.block;

import hawksmachinery.common.HawksMachinery;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockMetalStorage extends HMBlock
{
	public HMBlockMetalStorage(int id)
	{
		super(id, Material.iron, -1, null);
		setStepSound(Block.soundMetalFootstep);
		setCreativeTab(HawksMachinery.instance().tab);
		
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 243 + meta;
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		
	}
	
	@Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		switch (world.getBlockMetadata(x, y, z))
		{
			case 0: return 25;
			case 1: return 50;
			default: return 0;
		}
		
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (world.getBlockMetadata(x, y, z) == 0 && world.isBlockGettingPowered(x, y, z))
		{
			world.spawnParticle("portal", x + 0.5, y + 1, z + 0.5, 0, 0.5, 0);
			
		}
		
	}
	
}
