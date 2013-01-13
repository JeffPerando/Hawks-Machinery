
package hawksmachinery.machine.common.block;

import hawksmachinery.machine.common.HMMachines;
import hawksmachinery.machine.common.tileentity.HMTileEntitySinterer;
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
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockSinterer extends HMBlockMachine
{
	public HMBlockSinterer(int id)
	{
		super("HMSinterer", id, Material.iron);
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
			player.openGui(HMMachines.instance(), 5, world, x, y, z);
			
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
		return new HMTileEntitySinterer();
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
		return HMMachines.PROXY.getHMRenderID();
	}
	
}
