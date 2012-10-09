
package hawksmachinery.tileentity;

import java.util.Random;
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
import hawksmachinery.interfaces.HMRepairInterfaces.IHMRepairable;
import hawksmachinery.interfaces.HMRepairInterfaces.IHMSapper;
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
public abstract class HMTileEntityMachine extends TileEntityElectricityReceiver implements ISidedInventory, IRotatable, IPacketReceiver, IHMRepairable
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
	
	public int machineHP;
	
	private ItemStack sapper;

	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side != ForgeDirection.UP && side != this.facingDirection;
	}
	
	@Override
	public void onReceive(TileEntity tileEntity, double amps, double voltage, ForgeDirection side)
	{
		if (voltage > this.getVoltage())
		{
			this.explodeMachine(5.0F);
		}
		
		this.electricityStored += ElectricInfo.getWatts(amps, voltage);
		
	}
	
	@Override
	public void updateEntity()
	{
		if (this.electricityStored < 0)
		{
			this.electricityStored = 0;
		}
		
		if (this.electricityStored > this.ELECTRICITY_LIMIT)
		{
			this.electricityStored = this.ELECTRICITY_LIMIT;
		}
		
		if (this.machineHP < 0)
		{
			this.machineHP = 0;
		}
		
		if (this.machineHP > this.getMaxHP())
		{
			this.machineHP = this.getMaxHP();
		}
		
		if (!this.worldObj.isRemote && this.isOpen)
		{
			this.sendPacket();
		}
		
		if (this.isBeingSapped())
		{
			((IHMSapper)this.sapper.getItem()).sapperTick(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
		
		if (this.facingDirection.ordinal() != this.blockMetadata)
		{
			this.facingDirection = ForgeDirection.getOrientation(this.blockMetadata);
			
		}
		
	}
	
	public double getVoltage()
	{
		return voltage;
	}
	
	protected void explodeMachine(float strength)
	{
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
		System.out.println("NBT reading executed!");//TODO Debugging
		super.readFromNBT(NBTTag);
		this.electricityStored = NBTTag.getDouble("electricityStored");
		this.workTicks = NBTTag.getInteger("workTicks");
		this.machineHP = NBTTag.getInteger("MachineHP");
		
		if (NBTTag.hasKey("Sapper"))
		{
			this.sapper = ItemStack.loadItemStackFromNBT(NBTTag.getCompoundTag("Sapper"));
		}
		
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
		System.out.println("NBT writing executed!");//TODO Debugging
		super.writeToNBT(NBTTag);
		NBTTag.setDouble("electricityStored", this.electricityStored);
		NBTTag.setInteger("workTicks", this.workTicks);
		NBTTag.setInteger("MachineHP", this.machineHP);
		
		if (this.sapper != null)
		{
			NBTTag.setCompoundTag("Sapper", this.sapper.writeToNBT(new NBTTagCompound()));
			
		}
		
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
	
	public void randomlyDamageSelf()
	{
		if (new Random().nextInt(10) == 6)
		{
			--this.machineHP;
		}
		
	}
	
	public boolean attemptToRepair(int repairAmount)
	{
		if (this.machineHP != this.getMaxHP() && !this.isBeingSapped())
		{
			this.machineHP += repairAmount;
			return true;
		}
		
		return false;
	}
	
	public boolean setSapper(ItemStack sapper)
	{
		if (this.sapper == null)
		{
			this.sapper = sapper;
			return true;
		}
		
		return false;
	}
	
	public boolean attemptToUnSap(EntityPlayer player)
	{
		if (this.isBeingSapped())
		{
			int randomDigit = new Random().nextInt(((IHMSapper)this.sapper.getItem()).getRemovalValue(this.sapper, player));
			
			if (randomDigit == ((IHMSapper)this.sapper.getItem()).getRemovalValue(this.sapper, player) / 2)
			{
				((IHMSapper)this.sapper.getItem()).onRemoved(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
				this.sapper = null;
				return true;
			}
			
		}
		
		return false;
	}
	
	public boolean isBeingSapped()
	{
		return this.sapper != null;
	}
	
	@Override
	public boolean isDisabled()
	{
		return this.isBeingSapped() || this.machineHP == 0;
	}
	
	public int getMaxHP()
	{
		return 20;
	}
	
}