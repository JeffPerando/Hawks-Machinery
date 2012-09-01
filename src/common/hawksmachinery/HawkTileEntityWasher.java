
package hawksmachinery;

import com.google.common.io.ByteArrayDataInput;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import universalelectricity.electricity.TileEntityElectricUnit;
import universalelectricity.extend.IItemElectric;
import universalelectricity.extend.IRedstoneReceptor;
import universalelectricity.extend.IRotatable;
import universalelectricity.network.IPacketReceiver;
import universalelectricity.network.PacketManager;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityWasher extends TileEntityElectricUnit implements IInventory, ISidedInventory, IRotatable, IPacketReceiver
{
	public int ELECTRICITY_REQUIRED = 10;
	
	public int TICKS_REQUIRED = 80;
	
	public ForgeDirection facingDirection = ForgeDirection.UNKNOWN;
	
	public float electricityStored = 0;
	
	public int workTicks = 0;
	
	public ItemStack[] containingItems = new ItemStack[5];
	
	public int ELECTRICITY_LIMIT = 1200;
	
	public float waterUnits = 0;
	
	public float WATER_LIMIT = 10.0F;
	
	public HawkTileEntityWasher()
	{
		super();
	}
	
	@Override
	public void onUpdate(float watts, float voltage, ForgeDirection side)
	{
		super.onUpdate(watts, voltage, side);
		
		if (!this.worldObj.isRemote)
		{
			if (voltage > this.getVoltage())
			{
				this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 0.7F);
			}
			
			if (this.containingItems[0] != null)
			{
				if (this.containingItems[0].getItem() instanceof IItemElectric)
				{
					IItemElectric electricItem = (IItemElectric)this.containingItems[0].getItem();
					
					if (electricItem.canProduceElectricity())
					{
						double receivedElectricity = electricItem.onUseElectricity(electricItem.getTransferRate(), this.containingItems[0]);
						this.electricityStored += receivedElectricity;
					}
				}
			}
			
			if (this.containingItems[2] == new ItemStack(Item.bucketWater, 1) && this.waterUnits <= this.WATER_LIMIT)
			{
				this.waterUnits += 1.0;
				this.containingItems[2] = null;
			}
			
	    	if (this.electricityStored <= 0)
	    	{
	    		this.electricityStored = 0;
	    	}
	    	
	    	if (this.electricityStored >= this.ELECTRICITY_LIMIT)
	    	{
	    		this.electricityStored = this.ELECTRICITY_LIMIT;
	    	}
	    	
			PacketManager.sendTileEntityPacket(this, "HawksMachinery", this.workTicks, this.electricityStored);
			
		}
	}
	
	private boolean canWash()
	{
		if (this.containingItems[1] == null)
		{
			return false;
		}
		else
		{
			if (this.electricityStored >= this.ELECTRICITY_REQUIRED * 2 && this.waterUnits >= 1.0F)
			{
				ItemStack var1 = HawkProcessingRecipes.getWashingResult(this.containingItems[1]);
				if (var1 == null) return false;
				if (this.containingItems[3] == null || this.containingItems[4] == null || this.containingItems[5] == null) return true;
				if (!this.containingItems[3].isItemEqual(var1) && !this.containingItems[4].isItemEqual(var1) && !this.containingItems[5].isItemEqual(var1)) return false;
				
				int result1 = containingItems[3].stackSize + var1.stackSize;
				int result2 = containingItems[4].stackSize + var1.stackSize;
				int result3 = containingItems[5].stackSize + var1.stackSize;
				
				return ((result1 <= getInventoryStackLimit() || result2 <= getInventoryStackLimit() || result3 <= getInventoryStackLimit()) && 
						(result1 <= var1.getMaxStackSize() || result2 <= var1.getMaxStackSize() || result3 <= var1.getMaxStackSize()));
			}
			else
			{
				return false;
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
		return "Washer";
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
	public void openChest() {}
	
	@Override
	public void closeChest() {}
	
	@Override
	public float electricityRequest()
	{
		return 0;
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side != ForgeDirection.UP && side != ForgeDirection.getOrientation(this.facingDirection.ordinal());
	}
	
}
