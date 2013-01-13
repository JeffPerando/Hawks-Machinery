
package hawksmachinery.core.common.api.helpers;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
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
	
	public HMVector(TileEntity tileEntity)
	{
		this(tileEntity.worldObj, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
		
	}
	
	public HMVector(NBTTagCompound tag)
	{
		this(DimensionManager.getWorld(tag.getInteger("Dim")), tag.getInteger("xCoord"), tag.getInteger("yCoord"), tag.getInteger("zCoord"));
		
	}
	
	public HMVector(World world, int x, int y, int z)
	{
		worldObj = world;
		xCoord = x;
		yCoord = y;
		zCoord = z;
		
	}
	
	public HMVector(HMVector oldVec)
	{
		this(oldVec.worldObj, oldVec.xCoord, oldVec.yCoord, oldVec.zCoord);
		
	}
	
	public boolean setBlockId(int id)
	{
		return this.setBlockWithMeta(id, 0);
	}
	
	public boolean setBlockWithMeta(int id, int meta)
	{
		return this.worldObj.setBlockAndMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, id, meta);
	}
	
	public int getBlockId()
	{
		return this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public Block getBlock()
	{
		return Block.blocksList[this.getBlockId()];
	}
	
	public boolean setMeta(int meta)
	{
		return this.setBlockWithMeta(this.getBlockId(), meta);
	}
	
	public int getMetadata()
	{
		return this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public TileEntity getTileEntity()
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public void setLightValue(EnumSkyBlock lightType, int value)
	{
		this.worldObj.setLightValue(lightType, this.xCoord, this.yCoord, this.zCoord, value);
	}
	
	public int getLightValue()
	{
		return this.worldObj.getBlockLightValue(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public int getDim()
	{
		return this.worldObj.provider.dimensionId;
	}
	
	public boolean isGettingRedstoned()
	{
		return this.worldObj.isBlockGettingPowered(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public boolean isGettingIndirectlyRedstoned()
	{
		return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public boolean canSeeTheSky()
	{
		return this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public boolean isDaytime()
	{
		return this.worldObj.isDaytime();
	}
	
	public void markBlockForRenderUpdate()
	{
		this.worldObj.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
		
	}
	
	public void updateNeighboringBlocks()
	{
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockId());
		
	}
	
	public void explode(float str, Entity entity, boolean idk)
	{
		this.worldObj.createExplosion(entity, this.xCoord, this.yCoord, this.zCoord, str, idk);
	}
	
	/**
	 * 
	 * Apparently, you CAN self-terminate.
	 * 
	 * @return
	 */
	public boolean selfTerminate()
	{
		return this.setBlockId(0);
	}
	
	public Vector3 toVector3()
	{
		return new Vector3(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public NBTTagCompound writeToNBTTag(NBTTagCompound tag)
	{
		tag.setInteger("xCoord", this.xCoord);
		tag.setInteger("yCoord", this.yCoord);
		tag.setInteger("zCoord", this.zCoord);
		tag.setInteger("Dim", this.worldObj.provider.dimensionId);
		return tag;
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
	
	public HMVector navigatePath(ArrayList<ForgeDirection> directions, int[] amount)
	{
		for (int counter = 0; counter < directions.size(); ++counter)
		{
			this.modifyFromDir(directions.get(counter), amount[counter]);
			
		}
		
		return this;
	}
	
	public HMVector reset(TileEntity tile)
	{
		this.xCoord = tile.xCoord;
		this.yCoord = tile.yCoord;
		this.zCoord = tile.zCoord;
		return this;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj != null)
		{
			if (obj instanceof HMVector)
			{
				return ((HMVector)obj).getDim() == this.getDim() && ((HMVector)obj).xCoord == this.xCoord && ((HMVector)obj).yCoord == this.yCoord && ((HMVector)obj).zCoord == this.zCoord;
			}
			
		}
		
		return false;
	}
	
	@Override
	public HMVector clone()
	{
		return new HMVector(this);
	}
	
}
