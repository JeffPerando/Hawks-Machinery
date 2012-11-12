
package hawksmachinery.block;

import java.util.List;
import java.util.Random;
import hawksmachinery.item.HMItemBlockMetalStorage;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

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
		GameRegistry.registerBlock(this, HMItemBlockMetalStorage.class);
		setCreativeTab(CreativeTabs.tabBlock);
		
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
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (world.getBlockMetadata(x, y, z) == 0 && world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			world.spawnParticle("portal", x + 0.5, y + 1, z + 0.5, 0, 0.5, 0);
		}
		
	}
	
}
