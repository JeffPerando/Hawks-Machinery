
package hawksmachinery.common.block;

import hawksmachinery.common.tileentity.HMTileEntityFisher;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockFisher extends HMBlockMachine
{
	public HMBlockFisher(int id)
	{
		super("HMFisher", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		
	}

	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && !super.onMachineActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
		{
			player.openGui(BASEMOD.instance(), 3, world, x, y, z);
			
		}
		
		return true;
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityFisher();
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		switch (side)
		{
			case 0: return 194;
			case 1: return 192;
			default: return 193;
			
		}
		
	}
	
}
