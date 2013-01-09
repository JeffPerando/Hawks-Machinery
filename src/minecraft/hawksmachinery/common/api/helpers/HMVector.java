
package hawksmachinery.common.api.helpers;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;

/**
 * 
 * Because 5 Vectors just isn't enough!
 * 
 * @author Elusivehawk
 */
public class HMVector
{
	public final World worldObj;
	
	public int xCoord, yCoord, zCoord;
	
	public ForgeDirection dir = ForgeDirection.UNKNOWN;
	
	public HMVector(TileEntity tileEntity)
	{
		this(tileEntity, ForgeDirection.UNKNOWN);
		
	}
	
	public HMVector(TileEntity tileEntity, ForgeDirection dir)
	{
		this(tileEntity.worldObj, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, dir);
		
	}
	
	public HMVector(NBTTagCompound tag)
	{
		this(DimensionManager.getWorld(tag.getInteger("Dim")), tag.getInteger("xCoord"), tag.getInteger("yCoord"), tag.getInteger("zCoord"));
		
	}
	
	public HMVector(World world, int x, int y, int z)
	{
		this(world, x, y, z, ForgeDirection.UNKNOWN);
		
	}
	
	public HMVector(World world, int x, int y, int z, ForgeDirection direction)
	{
		worldObj = world;
		xCoord = x;
		yCoord = y;
		zCoord = z;
		dir = direction;
		
	}
	
	public int getBlockId()
	{
		return this.worldObj.getBlockId(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
	}
	
	public Block getBlock()
	{
		return Block.blocksList[this.getBlockId()];
	}
	
	public int getMetadata()
	{
		return this.worldObj.getBlockMetadata(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
	}
	
	public TileEntity getTileEntity()
	{
		return this.worldObj.getBlockTileEntity(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
	}
	
	public int getLightValue()
	{
		return this.worldObj.getBlockLightValue(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
	}
	
	public int getDim()
	{
		return this.worldObj.provider.dimensionId;
	}
	
	public boolean isGettingRedstoned()
	{
		return this.worldObj.isBlockGettingPowered(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
	}
	
	public boolean isGettingIndirectlyRedstoned()
	{
		return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
	}
	
	public boolean canSeeTheSky()
	{
		return this.worldObj.canBlockSeeTheSky(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
	}
	
	public boolean isDaytime()
	{
		return this.worldObj.isDaytime();
	}
	
	public void markBlockForRenderUpdate()
	{
		this.worldObj.markBlockForRenderUpdate(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
		
	}
	
	public void updateNeighboringBlocks()
	{
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ, this.getBlockId());
		
	}
	
	public Vector3 toVector3()
	{
		return new Vector3(this.xCoord + this.dir.offsetX, this.yCoord + this.dir.offsetY, this.zCoord + this.dir.offsetZ);
	}
	
	public NBTTagCompound writeToNBTTag(NBTTagCompound tag)
	{
		tag.setInteger("xCoord", this.xCoord);
		tag.setInteger("yCoord", this.yCoord);
		tag.setInteger("zCoord", this.zCoord);
		tag.setInteger("Dir", this.dir.ordinal());
		tag.setInteger("Dim", this.worldObj.provider.dimensionId);
		return tag;
	}
	
	public HMVector changeDir(ForgeDirection dir)
	{
		this.dir = dir;
		return this;
	}
	
	public HMVector modifyFromDir(ForgeDirection dir)
	{
		return this.modifyFromDir(dir, 1);
	}
	
	public HMVector modifyFromDir(ForgeDirection dir, int amount)
	{
		this.xCoord += (dir.offsetX * amount);
		this.yCoord += (dir.offsetY * amount);
		this.zCoord += (dir.offsetZ * amount);
		return this;
	}
	
	public HMVector reset(TileEntity tile, ForgeDirection dir)
	{
		this.xCoord = tile.xCoord;
		this.yCoord = tile.yCoord;
		this.zCoord = tile.zCoord;
		this.dir = dir;
		return this;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj != null)
		{
			if (obj instanceof HMVector)
			{
				return ((HMVector)obj).getDim() == this.getDim() && ((HMVector)obj).xCoord == this.xCoord && ((HMVector)obj).yCoord == this.yCoord && ((HMVector)obj).zCoord == this.zCoord && ((HMVector)obj).dir.ordinal() == this.dir.ordinal();
			}
			
		}
		
		return false;
	}
	
}
