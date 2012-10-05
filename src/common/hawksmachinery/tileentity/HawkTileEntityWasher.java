
package hawksmachinery.tileentity;

import hawksmachinery.HawkProcessingRecipes;
import hawksmachinery.HawkProcessingRecipes.HawkEnumProcessing;
import hawksmachinery.HawksMachinery;
import java.util.Random;
import railcraft.common.api.carts.IItemTransfer;
import railcraft.common.api.core.items.EnumItemType;
import buildcraft.api.core.Orientations;
import com.google.common.io.ByteArrayDataInput;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import universalelectricity.electricity.ElectricInfo;
import universalelectricity.prefab.TileEntityElectricityReceiver;
import universalelectricity.implement.IItemElectric;
import universalelectricity.implement.IRedstoneReceptor;
import universalelectricity.implement.IRotatable;
import universalelectricity.network.IPacketReceiver;
import universalelectricity.network.PacketManager;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityWasher extends HawkTileEntityRepairable implements IItemTransfer
{
	public float waterUnits = 0;
	
	public float WATER_LIMIT = 25.0F;
	
	public HawkTileEntityWasher()
	{
		super();
		ELECTRICITY_REQUIRED = 10;
		TICKS_REQUIRED = FMLCommonHandler.instance().getSide().isServer() ? HawksMachinery.crusherTicks : 100;
		ELECTRICITY_LIMIT = 1200;
		containingItems = new ItemStack[6];
		machineEnum = HawkEnumProcessing.WASHING;
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!this.isDisabled())
		{
			if (this.containingItems[0] != null)
			{
				if (this.containingItems[0].getItem() instanceof IItemElectric)
				{
					IItemElectric electricItem = (IItemElectric)this.containingItems[0].getItem();
					
					if (electricItem.canProduceElectricity() && this.electricityStored + electricItem.getTransferRate() <= this.ELECTRICITY_LIMIT)
					{
						double receivedElectricity = electricItem.onUseElectricity(electricItem.getTransferRate(), this.containingItems[0]);
						this.electricityStored += receivedElectricity;
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
			
			if (this.canWash())
			{
				if (this.containingItems[2] != null && this.workTicks == 0)
				{
					this.workTicks = this.TICKS_REQUIRED;
				}
				
				if (this.canWash() && this.workTicks > 0)
				{
					--this.workTicks;
					this.waterUnits -= 0.01F;
					
					if (this.workTicks == 1)
					{
						this.washItem();
						this.workTicks = 0;
					}
					
					this.electricityStored = this.electricityStored - this.ELECTRICITY_REQUIRED;
				}
				else
				{
					this.workTicks = 0;
				}
			}
			
			if (this.waterUnits > this.WATER_LIMIT)
			{
				this.waterUnits = this.WATER_LIMIT;
			}
			
			if (this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) == Block.waterStill.blockID && this.waterUnits + 1.0F <= this.WATER_LIMIT)
			{
				this.waterUnits += 1.0F;
				this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord + 1, this.zCoord, 0);
			}
			
			if (!this.canWash() && this.workTicks != 0)
			{
				this.workTicks = 0;
			}
			
		}
		
	}
	
	public boolean canWash()
	{
		if (this.containingItems[2] == null)
		{
			return false;
		}
		else
		{
			if (this.electricityStored >= this.ELECTRICITY_REQUIRED * 2 && this.waterUnits >= 1.0F)
			{
				ItemStack var1 = HawkProcessingRecipes.getResult(this.containingItems[2], this.machineEnum);
				if (var1 == null) return false;
				if (this.containingItems[3] == null) return true;
				if (!this.containingItems[3].isItemEqual(var1)) return false;
				int result = containingItems[3].stackSize + var1.stackSize;
				return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
			}
			else
			{
				return false;
			}
		}
	}
	
	private void washItem()
	{
		if (this.canWash())
		{
			ItemStack newItem = HawkProcessingRecipes.getResult(this.containingItems[2], this.machineEnum);
			
			if (this.canWash())
			{
				if (this.containingItems[3] == null)
				{
					this.containingItems[3] = newItem.copy();
				}
				else if (this.containingItems[3].isItemEqual(newItem))
				{
					++this.containingItems[3].stackSize;
				}
				
				--this.containingItems[2].stackSize;
				
				if (this.containingItems[2].stackSize <= 0)
				{
					this.containingItems[2] = null;
				}
				
			}
		}
	}
	
	@Override
	public void handlePacketData(NetworkManager network, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
	{
		try
		{
			this.workTicks = dataStream.readInt();
			this.electricityStored = dataStream.readFloat();
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
	public int getStartInventorySide(ForgeDirection side)
	{
		if (side == ForgeDirection.UP)
		{
			return 1;
		}
		
		if (side == ForgeDirection.DOWN)
		{
			return 0;
		}
		
		return 2;
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
		return 1;
	}
	
	@Override
	public String getInvName()
	{
		return "HMWasher";
	}

	@Override
	public double wattRequest()
	{
		if (this.isDisabled())
		{
			return 0;
		}
		else
		{
			if (this.canWash() && this.electricityStored + this.ELECTRICITY_REQUIRED <= this.ELECTRICITY_LIMIT)
			{
				return this.ELECTRICITY_REQUIRED;
			}
			else
			{
				if (this.ELECTRICITY_LIMIT != this.electricityStored)
				{
					if (this.electricityStored + this.ELECTRICITY_REQUIRED >= this.ELECTRICITY_LIMIT)
					{
						return this.ELECTRICITY_LIMIT - this.electricityStored;
					}
					else
					{
						return this.ELECTRICITY_REQUIRED;
					}
					
				}
				else
				{
					return 0;
				}
				
			}
			
		}
		
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side != ForgeDirection.UP && side != ForgeDirection.getOrientation(this.facingDirection.ordinal());
	}
	
	@Override
	public ItemStack offerItem(Object source, ItemStack offer)
	{
		if (HawkProcessingRecipes.getResult(offer, this.machineEnum) != null)
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
	
	@Override
	public int addItem(ItemStack stack, boolean doAdd, Orientations from)
	{
		return 0;
	}
	
	@Override
	public ItemStack[] extractItem(boolean doRemove, Orientations from, int maxItemCount)
	{
		return null;
	}
	
}
