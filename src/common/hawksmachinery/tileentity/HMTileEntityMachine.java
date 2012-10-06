
package hawksmachinery.tileentity;

import com.google.common.io.ByteArrayDataInput;
import buildcraft.api.inventory.ISpecialInventory;
import railcraft.common.api.carts.IItemTransfer;
import universalelectricity.electricity.ElectricInfo;
import universalelectricity.implement.IRotatable;
import universalelectricity.network.IPacketReceiver;
import universalelectricity.network.PacketManager;
import universalelectricity.prefab.TileEntityElectricityReceiver;
import universalelectricity.prefab.Vector3;
import hawksmachinery.HMProcessingRecipes;
import hawksmachinery.HMProcessingRecipes.HawkEnumProcessing;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

/**
 * 
 * Extend this if you'd like to make a machine slightly faster.
 * 
 * @author Elusivehawk
 */
public abstract class HMTileEntityMachine extends TileEntityElectricityReceiver implements ISidedInventory, IRotatable, IPacketReceiver
{
	public int ELECTRICITY_REQUIRED;
	
	public int TICKS_REQUIRED;
	
	public ForgeDirection facingDirection = ForgeDirection.UNKNOWN;
	
	public double electricityStored;
	
	public int workTicks;
	
	public ItemStack[] containingItems;
	
	public int ELECTRICITY_LIMIT;
	
	public boolean isOpen;
	
	public double voltage;
	
	public HawkEnumProcessing machineEnum;
	
	@Override
	public void onReceive(TileEntity tileEntity, double amps, double voltage, ForgeDirection side)
	{
		if (voltage > this.getVoltage())
		{
			this.explodeMachine(20.0F);
		}
		
		this.electricityStored += ElectricInfo.getWatts(amps, voltage);
		
	}
	
	@Override
	public void updateEntity()
	{
		if (this.electricityStored <= 0)
		{
			this.electricityStored = 0;
		}
		
		if (this.electricityStored >= this.ELECTRICITY_LIMIT)
		{
			this.electricityStored = this.ELECTRICITY_LIMIT;
		}
		
		if (!this.worldObj.isRemote && this.isOpen)
		{
			this.sendPacket();
		}
		
	}
	
	public double getVoltage()
	{
		return voltage;
	}
	
	protected void explodeMachine(float strength)
	{
		System.out.println(this.getVoltage());
		this.worldObj.createExplosion((Entity)null, this.xCoord, this.yCoord, this.zCoord, strength);
		
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		return PacketManager.getPacket("HawksMachinery", this, this.workTicks, this.electricityStored);
	}
	
	/**
	 * Override this if you want to put more into 1 packet.
	 */
	private void sendPacket()
	{
		PacketManager.sendPacketToClients(this.getDescriptionPacket(), this.worldObj, Vector3.get(this), 8);
	}
	
	@Override
	public void handlePacketData(NetworkManager network, int type, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
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
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
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
	public ForgeDirection getDirection()
	{
		return this.facingDirection;
	}
	
	@Override
	public void setDirection(ForgeDirection facingDirection)
	{
		this.facingDirection = facingDirection;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		this.electricityStored = NBTTag.getFloat("electricityStored");
		this.workTicks = NBTTag.getInteger("workTicks");
		
		if (this.containingItems.length > 0)
		{
			NBTTagList tagList = NBTTag.getTagList("Items");
			this.containingItems = new ItemStack[this.getSizeInventory()];
			for (int counter = 0; counter < tagList.tagCount(); ++counter)
			{
				NBTTagCompound newTag = (NBTTagCompound)tagList.tagAt(counter);
				byte slot = newTag.getByte("Slot");
				if (slot >= 0 && slot < this.containingItems.length)
				{
					this.containingItems[slot] = ItemStack.loadItemStackFromNBT(newTag);
				}
				
			}
			
		}
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		NBTTag.setDouble("electricityStored", this.electricityStored);
		NBTTag.setInteger("workTicks", this.workTicks);
		
		if (this.containingItems.length > 0)
		{
			NBTTagList tagList = new NBTTagList();
			
			for (int counter = 0; counter < this.containingItems.length; ++counter)
			{
				if (this.containingItems[counter] != null)
				{
					NBTTagCompound newTag = new NBTTagCompound();
					newTag.setByte("Slot", (byte)counter);
					this.containingItems[counter].writeToNBT(newTag);
					tagList.appendTag(newTag);
				}
				
			}
			
			NBTTag.setTag("Items", tagList);
			
		}
		
	}
	
}
