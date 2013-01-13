
package hawksmachinery.common.block;

import hawksmachinery.common.HawksMachinery;
import hawksmachinery.common.tileentity.HMTileEntityWasher;
import java.util.Random;
import universalelectricity.prefab.implement.IRotatable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 *
 *
 *
 * @author Elusivehawk
 */
public class HMBlockWasher extends HMBlockMachine
{
	public HMBlockWasher(int id)
	{
		super("HMWasher", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (super.onMachineActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
		{
			return false;
		}
		
		if (!world.isRemote)
		{
			player.openGui(HawksMachinery.instance(), 1, world, x, y, z);
			
		}
		
		return true;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		int newDirection = 3;
		
		switch (((IRotatable)world.getBlockTileEntity(x, y, z)).getDirection().ordinal())
		{
			case 2: newDirection = 5; break;
			case 5: newDirection = 3; break;
			case 3: newDirection = 4; break;
			case 4: newDirection = 2; break;
			
		}
		
		((IRotatable)world.getBlockTileEntity(x, y, z)).setDirection(ForgeDirection.getOrientation(newDirection));
		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
	{
		int direction = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int newDirection = 3;
		
		switch (direction)
		{
			case 0: newDirection = 2; break;
			case 1: newDirection = 5; break;
			case 2: newDirection = 3; break;
			case 3: newDirection = 4; break;
			
		}
		
		((IRotatable)world.getBlockTileEntity(x, y, z)).setDirection(ForgeDirection.getOrientation(newDirection));
		
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new HMTileEntityWasher();
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
		return HawksMachinery.PROXY.getHMRenderID();
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (((HMTileEntityWasher)world.getBlockTileEntity(x, y, z)).canWork())
		{
			world.spawnParticle("bubble", x + 0.5, y + 1, z + 0.5, 0, 0.1, 0);
			world.spawnParticle("bubble", x + 0.5, y + 1, z + 0.5, 0, 0.1, 0);
			
		}
		
	}
	
}
