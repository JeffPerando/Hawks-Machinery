
package hawksmachinery.block;

import hawksmachinery.tileentity.HMTileEntityMulti;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import universalelectricity.prefab.multiblock.BlockMulti;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockMulti extends BlockMulti
{
	public HMBlockMulti(int id)
	{
		super(id);
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityMulti();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		return ((HMTileEntityMulti)world.getBlockTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player);
	}
	
}
