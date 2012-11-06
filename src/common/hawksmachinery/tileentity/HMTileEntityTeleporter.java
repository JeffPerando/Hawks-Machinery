
package hawksmachinery.tileentity;

import hawksmachinery.api.HMEndiumTeleporterCoords;
import hawksmachinery.api.HMTeleportationHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityEnderChest;
import net.minecraft.src.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityTeleporter extends HMTileEntityMachine
{
	public HMEndiumTeleporterCoords coords;
	
	public int[] coordsArray = new int[3];
	
	public HMTileEntityTeleporter()
	{
		ELECTRICITY_LIMIT = 100000;
		ELECTRICITY_REQUIRED = 200;
		voltage = 120;
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (this.isReadyToTeleport() && this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) != 0)
		{
			WorldServer receivingDim = DimensionManager.getWorld(this.coords.dim());
			
			if (receivingDim.getBlockId(this.coords.x(), this.coords.y() + 1, this.coords.z()) == 0)
			{
				TileEntity potenTileEntity = this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
				
				if (HMTeleportationHelper.instance().isTileEntityWhitelisted(potenTileEntity))
				{
					int blockID = this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord);
					int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord + 1, this.zCoord);
					
					receivingDim.setBlockAndMetadataWithUpdate(this.coords.x(), this.coords.y() + 1, this.coords.z(), blockID, meta, true);
					this.worldObj.setBlockAndMetadata(this.xCoord, this.yCoord, this.zCoord, 0, 0);
					this.doTeleportationSpecialEffects();
					this.electricityStored = 0;
					--this.machineHP;
					
				}
				
			}
			
		}
		
	}
	
	public boolean isReadyToTeleport()
	{
		return this.electricityStored == this.ELECTRICITY_LIMIT && this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) && this.coords != null && this.blockMetadata == 0 && !this.isDisabled();
	}
	
	public void tryTeleportEntity(Entity entity)
	{
		if (this.isReadyToTeleport())
		{
			HMTeleportationHelper.instance().teleportEntity(entity, this.coords);
			this.doTeleportationSpecialEffects();
			this.electricityStored = 0;
			--this.machineHP;
			
		}
		
	}
	
	public void updateCoords(int newCoord)
	{
		this.coordsArray[0] = this.coordsArray[1];
		this.coordsArray[1] = this.coordsArray[2];
		this.coordsArray[2] = newCoord;
		
	}
	
	public void wipeCoords()
	{
		this.coordsArray[0] = 0;
		this.coordsArray[1] = 0;
		this.coordsArray[2] = 0;
		this.coords = null;
		
	}
	
	public void registerCoords()
	{
		if (this.coordsArray[0] != 0 && this.coordsArray[1] != 0 && this.coordsArray[2] != 0)
		{
			if (this.blockMetadata == 0)
			{
				this.coords = HMTeleportationHelper.instance().getCoordsFromSymbols(this.coordsArray[0], this.coordsArray[1], this.coordsArray[2]);
				
			}
			else if (this.blockMetadata == 1)
			{
				HMEndiumTeleporterCoords newCoords = new HMEndiumTeleporterCoords(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getWorldInfo().getDimension(), this.coordsArray[0], this.coordsArray[1], this.coordsArray[2]);
				
				if (HMTeleportationHelper.instance().registerCoords(newCoords))
				{
					this.coords = newCoords;
					
				}
				
			}
			
		}
		
	}
	
	public void doTeleportationSpecialEffects()
	{
		//TODO Add special effects.
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side.ordinal() == 0;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		
		this.coordsArray = NBTTag.getIntArray("coords");
		this.registerCoords();
		
	}

	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		
		NBTTag.setIntArray("coords", this.coordsArray);
		
	}
	
}
