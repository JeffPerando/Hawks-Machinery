
package hawksmachinery.common.block;

import hawksmachinery.common.HawksMachinery;
import hawksmachinery.common.tileentity.HMTileEntityCrusher;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.prefab.implement.IRotatable;

/**
 *
 * Just the block for the Crusher.
 *
 * @author Elusivehawk
 */
public class HMBlockCrusher extends HMBlockMachine
{
	public HMBlockCrusher(int id)
	{
		super("HMCrusher", id, Material.iron);
		setHardness(5.0F);
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
			player.openGui(HawksMachinery.instance(), 0, world, x, y, z);
			
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
		return new HMTileEntityCrusher();
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
		if (((HMTileEntityCrusher)world.getBlockTileEntity(x, y, z)).canWork())
		{
			switch (((IRotatable)world.getBlockTileEntity(x, y, z)).getDirection().ordinal())
			{
				case 2: world.spawnParticle("smoke", x + 0.5, y + 1, z + 0.8, 0, 0.1, 0); break;
				case 3: world.spawnParticle("smoke", x + 0.5, y + 1, z + 0.2, 0, 0.1, 0); break;
				case 4: world.spawnParticle("smoke", x + 0.8, y + 1, z + 0.5, 0, 0.1, 0); break;
				case 5: world.spawnParticle("smoke", x + 0.2, y + 1, z + 0.5, 0, 0.1, 0); break;
				
			}
			
		}
		
	}
	
}
