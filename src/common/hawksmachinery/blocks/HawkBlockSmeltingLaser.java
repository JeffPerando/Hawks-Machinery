
package hawksmachinery.blocks;

import universalelectricity.implement.IConnector;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkBlockSmeltingLaser extends HawkBlockMachine
{
	public HawkBlockSmeltingLaser(int id)
	{
		super("HMSmeltingLaser", id, Material.iron);
		setHardness(2.0F);
		setResistance(2.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
		
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return world.isBlockSolidOnSide(x, y + 1, z, ForgeDirection.DOWN, false) || Block.blocksList[world.getBlockId(x, y + 1, z)] instanceof IConnector;
	}
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return side.ordinal() == 1;
	}
	
}
