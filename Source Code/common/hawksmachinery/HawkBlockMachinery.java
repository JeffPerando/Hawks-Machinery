
package hawksmachinery;

import cpw.mods.fml.common.registry.GameRegistry;
import universalelectricity.extend.BlockMachine;
import net.minecraft.src.*;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * Just the block for the Grinder.
 * 
 * @author Elusivehawk
 */
public class HawkBlockMachinery extends BlockMachine
{
	public HawkTileEntityGrinder tileEntityGrinder = new HawkTileEntityGrinder();
	
	private int xCoord;
	private int yCoord;
	private int zCoord;
	
	private ForgeDirection blockDirection;
	
	public HawkBlockMachinery(int id)
	{
		super("Grinder", id, Material.iron);
		setHardness(2.0F);
		setResistance(20.0F);
		setRequiresSelfNotify();
		GameRegistry.registerBlock(this, HawkItemBlockGrinder.class);
		setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@Override
	protected int damageDropped(int metadata)
	{
		return 0;
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (!world.isRemote)
		{
			player.openGui(HawkManager.getModInstance(), 0, world, x, y, z);
			return true;
		}
		
		return true;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer par5EntityPlayer)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		switch (metadata)
		{
			case 0: switch (this.tileEntityGrinder.facingDirection.ordinal())
					{
						case 2: this.updateDirection(ForgeDirection.WEST, metadata); break;
						case 5: this.updateDirection(ForgeDirection.NORTH, metadata); break;
						case 3: this.updateDirection(ForgeDirection.EAST, metadata); break;
						case 4: this.updateDirection(ForgeDirection.SOUTH, metadata); break;
					}
		}
		
		return true;
	}
	
	public void updateDirection(ForgeDirection direction, int metadata)
	{
		switch (metadata)
		{
			case 0: this.tileEntityGrinder.setDirection(direction);
		}
		
		this.blockDirection = direction;
	}
	
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
	{
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		int metadata = world.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		
		int direction = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		ForgeDirection newDirection = ForgeDirection.SOUTH;
		
		switch (direction)
		{
			case 0: newDirection = ForgeDirection.NORTH; break;
			case 1: newDirection = ForgeDirection.EAST; break;
			case 2: newDirection = ForgeDirection.SOUTH; break;
			case 3: newDirection = ForgeDirection.WEST; break;
		}
		
		this.updateDirection(newDirection, metadata);
	}
	
	@Override
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		if (metadata == 0)
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		switch (world.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord))
		{
			case 0: return this.tileEntityGrinder;
		}
		
		return null;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		if (metadata == 0)
		{
			switch (this.tileEntityGrinder.facingDirection.ordinal())
			{
				case 2: switch (side)
						{
							case 1: return 5;
							case 2: return 6;
							default: return 7;
						}
				case 3: switch (side)
						{
							case 1: return 5;
							case 3: return 6;
							default: return 7;
						}
				case 4: switch (side)
						{
							case 1: return 23;
							case 4: return 6;
							default: return 7;
						}
				case 5: switch (side)
						{
							case 1: return 23;
							case 5: return 6;
							default: return 7;
						}
				default: switch (side)
						{
							case 1: return 5;
							case 3: return 6;
							default: return 7;
						}
			}
		}
		
		return 0;
	}
    
	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		if (side == 0)
		{
			return false;
		}
		
		return true;
	}
	
}
