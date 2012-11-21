
package hawksmachinery.block;

import hawksmachinery.tileentity.HMTileEntityCrusher;
import hawksmachinery.tileentity.HMTileEntityFireBlock;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockFireBlock extends HMBlockMachine
{
	public HMBlockFireBlock(String name, int id, Material mat)
	{
		super("HMFireBlock", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityFireBlock();
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
}
