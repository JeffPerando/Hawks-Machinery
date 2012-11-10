
package hawksmachinery.blocks;

import net.minecraft.src.Achievement;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockOre extends HMBlock
{
	public static String[] en_USNames = new String[]{"Endium", "Cobalt"};
	
	public HMBlockOre(int id)
	{
		super(id, Material.rock, -1, null);
		setHardness(2.0F);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
		
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
	
}
