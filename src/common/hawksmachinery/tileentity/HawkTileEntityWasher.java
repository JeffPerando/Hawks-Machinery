
package hawksmachinery.tileentity;

import hawksmachinery.HawkEnumProcessing;
import hawksmachinery.HawkProcessingRecipes;
import hawksmachinery.HawksMachinery;
import java.util.Random;
import railcraft.common.api.carts.IItemTransfer;
import railcraft.common.api.core.items.EnumItemType;
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
public class HawkTileEntityWasher extends TileEntityElectricityReceiver implements IInventory, ISidedInventory, IRotatable, IPacketReceiver, IItemTransfer
{
	public int ELECTRICITY_REQUIRED = 10;
	
	public int TICKS_REQUIRED = FMLCommonHandler.instance().getSide().isServer() ? HawksMachinery.crusherTicks : 100;
	
	public ForgeDirection facingDirection = ForgeDirection.UNKNOWN;
	
	public double electricityStored = 0;
	
	public int workTicks = 0;
	
	public ItemStack[] containingItems = new ItemStack[6];
	
	public int ELECTRICITY_LIMIT = 1200;
	
	public float waterUnits = 0;
	
	public float WATER_LIMIT = 25.0F;
	
	public boolean isOpen;
	
	public HawkTileEntityWasher()
	{
		super();
	}
	
	@Override
	public void onReceive(TileEntity tileEntity, double amps, double voltage, ForgeDirection side)
	{
		if (voltage > this.getVoltage())
		{
			this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 0.7F);
		}
		
		this.electricityStored += ElectricInfo.getWatts(amps, voltage);
		
	}
	
	@Override
	public void updateEntity()
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
		
		if (this.electricityStored <= 0)
		{
			this.electricityStored = 0;
		}
		
		if (this.electricityStored >= this.ELECTRICITY_LIMIT)
		{
			this.electricityStored = this.ELECTRICITY_LIMIT;
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
		
		if (!this.worldObj.isRemote && this.isOpen)
		{
			PacketManager.sendTileEntityPacketWithRange(this, "HawksMachinery", 8, this.workTicks, this.electricityStored, this.waterUnits);
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
				ItemStack var1 = HawkProcessingRecipes.getResult(this.containingItems[2], HawkEnumProcessing.WASHING);
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
			ItemStack newItem = HawkProcessingRecipes.getResult(this.containingItems[2], HawkEnumProcessing.WASHING);
			
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
	
	@Override
	public ForgeDirection getDirection()
	{
		return this.facingDirection;
	}
	
	@Override
	public void setDirection(ForgeDirection facingDirection)
	{
		this.facingDirection = facingDirection;
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
		this.electricityStored = NBTTag.getFloat("electricityStored");
		this.workTicks = NBTTag.getInteger("workTicks");
		this.waterUnits = NBTTag.getFloat("waterUnits");
		
		NBTTagList var2 = NBTTag.getTagList("Items");
		this.containingItems = new ItemStack[this.getSizeInventory()];
		for (int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");
			if (var5 >= 0 && var5 < this.containingItems.length)
			{
				this.containingItems[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		NBTTag.setDouble("electricityStored", this.electricityStored);
		NBTTag.setInteger("workTicks", this.workTicks);
		NBTTag.setFloat("waterUnits", this.waterUnits);
		
		NBTTagList var2 = new NBTTagList();
		
		for (int var3 = 0; var3 < this.containingItems.length; ++var3)
		{
			if (this.containingItems[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)var3);
				this.containingItems[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		
		NBTTag.setTag("Items", var2);
		
	}
	
	@Override
	public int getSizeInventorySide(ForgeDirection side)
	{
		return 1;
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.containingItems.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int var1)
	{
		return this.containingItems[var1];
	}
	
	@Override
	public ItemStack decrStackSize(int var1, int var2)
	{
		if (this.containingItems[var1] != null)
		{
			ItemStack var3;
			
			if (this.containingItems[var1].stackSize <= var2)
			{
				var3 = this.containingItems[var1];
				this.containingItems[var1] = null;
				return var3;
			}
			else
			{
				var3 = this.containingItems[var1].splitStack(var2);
				
				if (this.containingItems[var1].stackSize == 0)
				{
					this.containingItems[var1] = null;
				}
				
				return var3;
			}
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int var1)
	{
		if (this.containingItems[var1] != null)
		{
			ItemStack var2 = this.containingItems[var1];
			this.containingItems[var1] = null;
			return var2;
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void setInventorySlotContents(int var1, ItemStack var2)
	{
		this.containingItems[var1] = var2;
		
		if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
		{
			var2.stackSize = this.getInventoryStackLimit();
		}
	}
	
	@Override
	public String getInvName()
	{
		return "HMWasher";
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return true;
	}
	
	@Override
	public void openChest()
	{
		this.isOpen = true;
	}
	
	@Override
	public void closeChest()
	{
		this.isOpen = false;
	}
	
	@Override
	public double wattRequest()
	{
		if (!this.isDisabled() && this.canWash() && this.electricityStored + this.ELECTRICITY_REQUIRED <= this.ELECTRICITY_LIMIT)
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
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side != ForgeDirection.UP && side != ForgeDirection.getOrientation(this.facingDirection.ordinal());
	}
	
	@Override
	public ItemStack offerItem(Object source, ItemStack offer)
	{
		if (HawkProcessingRecipes.getResult(offer, HawkEnumProcessing.WASHING) != null)
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
	
}
