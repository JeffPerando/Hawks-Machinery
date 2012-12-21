
package hawksmachinery.common.tileentity;

import hawksmachinery.common.HawksMachinery;
import hawksmachinery.common.api.HMRecipes;
import hawksmachinery.common.api.HMRecipes.HMEnumProcessing;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.electricity.ElectricInfo;
import universalelectricity.core.implement.IItemElectric;
import universalelectricity.prefab.network.PacketManager;
import com.google.common.io.ByteArrayDataInput;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityWasher extends HMTileEntityMachine
{
	public float waterUnits = 0;
	
	public float WATER_LIMIT = 25.0F;
	
	public HMTileEntityWasher()
	{
		super();
		ELECTRICITY_REQUIRED = 5;
		TICKS_REQUIRED = FMLCommonHandler.instance().getSide().isServer() ? HawksMachinery.instance().MANAGER.crusherTicks : 100;
		ELECTRICITY_LIMIT = 1200;
		containingItems = new ItemStack[6];
		machineEnum = HMEnumProcessing.WASHING;
		voltage = 120;
		isProcessor = true;
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		this.waterUnits = Math.min(this.waterUnits, this.WATER_LIMIT);
		//this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord + 1, this.zCoord, this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord));
		
		if (this.worldObj.getTotalWorldTime() % 3L == 0L && this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) == Block.waterStill.blockID && this.waterUnits + 1.0F <= this.WATER_LIMIT)
		{
			this.waterUnits += 1.0F;
			this.worldObj.setBlockAndMetadataWithUpdate(this.xCoord, this.yCoord + 1, this.zCoord, 0, 0, true);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord + 1, this.zCoord, 0);
			
		}
		
		if (!this.isDisabled())
		{
			
			if (this.containingItems[0] != null)
			{
				if (this.containingItems[0].getItem() instanceof IItemElectric)
				{
					IItemElectric electricItem = (IItemElectric)this.containingItems[0].getItem();
					
					if (electricItem.canProduceElectricity() && this.electricityStored > this.ELECTRICITY_LIMIT)
					{
						double receivedElectricity = electricItem.onUse(Math.min(electricItem.getMaxJoules()*0.01, ElectricInfo.getWattHours(this.ELECTRICITY_REQUIRED)), this.containingItems[0]);
						this.electricityStored += ElectricInfo.getWatts(receivedElectricity);
						
					}
					
				}
				
			}
			
			if (this.containingItems[1] != null)
			{
				if (this.containingItems[1].getItem() == Item.bucketWater && this.waterUnits + 1.0F <= this.WATER_LIMIT)
				{
					this.waterUnits += 1.0;
					this.containingItems[1] = new ItemStack(Item.bucketEmpty, 1);
					
				}
				
			}
			
			if (this.canWork())
			{
				this.electricityStored -= this.ELECTRICITY_REQUIRED;
				
				if (this.workTicks == 0)
				{
					this.workTicks = this.TICKS_REQUIRED;
					
				}
				else
				{
					--this.workTicks;
					this.waterUnits -= 0.01F;
					
					if (this.workTicks == 1)
					{
						this.washItem();
						this.workTicks = 0;
					}
					
				}
				
			}
			else
			{
				this.workTicks = 0;
				
			}
			
		}
		
	}
	
	public boolean canWork()
	{
		ItemStack output = HMRecipes.getResult(this.containingItems[2], this.machineEnum);
		return output != null && (this.electricityStored >= (this.ELECTRICITY_REQUIRED * 2) && this.waterUnits >= 1.0F) && (this.containingItems[3] == null || (output.isItemEqual(this.containingItems[3]) && output.stackSize + this.containingItems[3].stackSize <= output.getMaxStackSize()));
	}
	
	private void washItem()
	{
		if (this.canWork())
		{
			ItemStack newItem = HMRecipes.getResult(this.containingItems[2], this.machineEnum);
			
			if (this.containingItems[3] == null)
			{
				this.containingItems[3] = newItem.copy();
			}
			else if (this.containingItems[3].isItemEqual(newItem))
			{
				this.containingItems[3].stackSize += newItem.stackSize;
			}
			
			this.decrStackSize(2, HMRecipes.getQuantity(this.containingItems[2], this.machineEnum));
			
			this.randomlyDamageSelf();
			
		}
		
	}

	@Override
	public Packet getDescriptionPacket()
	{
		if (this.isOpen)
		{
			return PacketManager.getPacket("HawksMachinery", this, this.workTicks, this.electricityStored, this.machineHP, this.waterUnits);
		}
		
		return PacketManager.getPacket("HawksMachinery", this, this.electricityStored, this.machineHP, this.waterUnits);
	}
	
	@Override
	public void handlePacketData(INetworkManager network, int type, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
	{
		try
		{
			if (this.isOpen)
			{
				this.workTicks = dataStream.readInt();
				
			}
			
			this.electricityStored = dataStream.readDouble();
			this.machineHP = dataStream.readInt();
			this.waterUnits = dataStream.readFloat();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public int getWashingStatus(int par1)
	{
		return this.workTicks * par1 / 200;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		this.waterUnits = NBTTag.getFloat("waterUnits");
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		NBTTag.setFloat("waterUnits", this.waterUnits);
		
	}
	
	@Override
	public int getSizeInventorySide(ForgeDirection side)
	{
		return this.getStartInventorySide(side) == 3 ? 3 : 1;
	}

	@Override
	public int getStartInventorySide(ForgeDirection side)
	{
		if (side == ForgeDirection.UP) return 1;
		if (side == ForgeDirection.DOWN) return 0;
		if (side == this.getDirection()) return 2;
		
		return 3;
	}
	
	@Override
	public String getInvName()
	{
		return "HMWasher";
	}
	
	/*
	@Override
	public ItemStack offerItem(Object source, ItemStack offer)
	{
		if (HMProcessingRecipes.getResult(offer, this.machineEnum) != null)
		{
			if (this.containingItems[2] == null)
			{
				this.containingItems[2] = offer;
				return null;
			}
			else
			{
				if (this.containingItems[2].isItemEqual(offer))
				{
					if (this.containingItems[2].stackSize + offer.stackSize <= 64)
					{
						this.containingItems[2].stackSize += offer.stackSize;
						return null;
					}
					else
					{
						int extraAmount = (this.containingItems[1].stackSize + offer.stackSize) - 64;
						
						this.containingItems[2].stackSize += offer.stackSize;
						return new ItemStack(offer.getItem(), extraAmount, offer.getItemDamage());
					}
				}
			}
		}
		else
		{
			if (offer.getItem() instanceof IItemElectric)
			{
				if (((IItemElectric)offer.getItem()).canProduceElectricity())
				{
					this.containingItems[0] = offer;
					return null;
				}
			}
			
			if (offer.getItem() == Item.bucketWater)
			{
				if (this.containingItems[1] == null)
				{
					this.containingItems[1] = offer;
					return null;
				}
				else
				{
					if (this.containingItems[1].getItem() == Item.bucketEmpty)
					{
						this.containingItems[1] = offer;
						return new ItemStack(Item.bucketEmpty, 1);
					}
				}
			}
		}
		
		return offer;
	}
	
	@Override
	public ItemStack requestItem(Object source)
	{
		return null;
	}
	
	@Override
	public ItemStack requestItem(Object source, ItemStack request)
	{
		return null;
	}
	
	@Override
	public ItemStack requestItem(Object source, EnumItemType request)
	{
		return null;
	}
	*/
	
}
