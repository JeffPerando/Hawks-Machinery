
package hawksmachinery.redstone.common.block;

import hawksmachinery.core.common.block.HMBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockTileSensor extends HMBlock
{
	public HMBlockTileSensor(int id)
	{
		super(id, Material.iron, -1, null);
		setStepSound(Block.soundMetalFootstep);
		
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float blockID, int meta)
	{
		return 0;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	}
	
	@Override
	public boolean canProvidePower()
	{
		return true;
	}
	
	@Override
	public boolean isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
	{
		return false;
	}
	
}
