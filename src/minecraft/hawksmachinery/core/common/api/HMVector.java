
package hawksmachinery.core.common.api;

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
	
	public boolean setBlockIdWithDir(int id, ForgeDirection dir)
	{
		return this.setBlockWithMetaPlusDir(id, 0, dir);
	}
	
	public boolean setBlockWithMeta(int id, int meta)
	{
		return this.setBlockWithMetaPlusDir(id, meta, ForgeDirection.UNKNOWN);
	}
	
	public boolean setBlockWithMetaPlusDir(int id, int meta, ForgeDirection dir)
	{
		return this.worldObj.setBlockAndMetadataWithNotify(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, id, meta);
	}
	
	public int getBlockId()
	{
		return this.getBlockIdWithDir(ForgeDirection.UNKNOWN);
	}
	
	public int getBlockIdWithDir(ForgeDirection dir)
	{
		return this.worldObj.getBlockId(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public Block getBlock()
	{
		return this.getBlockWithDir(ForgeDirection.UNKNOWN);
	}
	
	public Block getBlockWithDir(ForgeDirection dir)
	{
		return Block.blocksList[this.getBlockIdWithDir(dir)];
	}
	
	public boolean setMeta(int meta)
	{
		return this.setMetaWithDir(meta, ForgeDirection.UNKNOWN);
	}
	
	public boolean setMetaWithDir(int meta, ForgeDirection dir)
	{
		return this.setBlockWithMetaPlusDir(this.getBlockId(), meta, dir);
	}
	
	public int getMetadata()
	{
		return this.getMetadataWithDir(ForgeDirection.UNKNOWN);
	}
	
	public int getMetadataWithDir(ForgeDirection dir)
	{
		return this.worldObj.getBlockMetadata(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public TileEntity getTileEntity()
	{
		return this.getTileEntityWithDir(ForgeDirection.UNKNOWN);
	}
	
	public TileEntity getTileEntityWithDir(ForgeDirection dir)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public void setLightValue(EnumSkyBlock lightType, int value)
	{
		this.setLightValueWithDir(lightType, value, ForgeDirection.UNKNOWN);
	}
	
	public void setLightValueWithDir(EnumSkyBlock lightType, int value, ForgeDirection dir)
	{
		this.worldObj.setLightValue(lightType, this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, value);
	}
	
	public int getLightValue()
	{
		return this.getLightValueWithDir(ForgeDirection.UNKNOWN);
	}
	
	public int getLightValueWithDir(ForgeDirection dir)
	{
		return this.worldObj.getBlockLightValue(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public int getDim()
	{
		return this.worldObj.provider.dimensionId;
	}
	
	public boolean isGettingRedstoned()
	{
		return this.isGettingRedstonedWithDir(ForgeDirection.UNKNOWN);
	}
	
	public boolean isGettingRedstonedWithDir(ForgeDirection dir)
	{
		return this.worldObj.isBlockGettingPowered(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public boolean isGettingIndirectlyRedstoned()
	{
		return this.isGettingIndirectlyRedstoneWithDir(ForgeDirection.UNKNOWN);
	}
	
	public boolean isGettingIndirectlyRedstoneWithDir(ForgeDirection dir)
	{
		return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public boolean canSeeTheSky()
	{
		return this.canSeeTheSkyWithDir(ForgeDirection.UNKNOWN);
	}
	
	public boolean canSeeTheSkyWithDir(ForgeDirection dir)
	{
		return this.worldObj.canBlockSeeTheSky(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
	}
	
	public boolean isDaytime()
	{
		return this.worldObj.isDaytime();
	}
	
	public void markBlockForRenderUpdate()
	{
		this.markNeighborBlockForRenderUpdate(ForgeDirection.UNKNOWN);
		
	}
	
	public void markNeighborBlockForRenderUpdate(ForgeDirection dir)
	{
		this.worldObj.markBlockForRenderUpdate(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
		
	}
	
	public void updateNeighboringBlocks()
	{
		this.updateNeighboringBlocksWithDir(ForgeDirection.UNKNOWN);
		
	}
	
	public void updateNeighboringBlocksWithDir(ForgeDirection dir)
	{
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, this.getBlockIdWithDir(dir));
		
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
