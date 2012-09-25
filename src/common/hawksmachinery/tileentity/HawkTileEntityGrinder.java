
package hawksmachinery.tileentity;

import hawksmachinery.HawkEnumProcessing;
import hawksmachinery.HawkProcessingRecipes;
import hawksmachinery.HawksMachinery;
import java.io.DataInputStream;
import java.io.IOException;
import railcraft.common.api.carts.IItemTransfer;
import railcraft.common.api.core.items.EnumItemType;
import buildcraft.api.core.Orientations;
import buildcraft.api.inventory.ISpecialInventory;
import com.google.common.io.ByteArrayDataInput;
import cpw.mods.fml.common.FMLCommonHandler;
import universalelectricity.electricity.ElectricInfo;
import universalelectricity.electricity.ElectricityManager;
import universalelectricity.implement.IRedstoneReceptor;
import universalelectricity.implement.IRotatable;
import universalelectricity.implement.ITier;
import universalelectricity.network.IPacketReceiver;
import universalelectricity.network.PacketManager;
import universalelectricity.prefab.TileEntityElectricityReceiver;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import universalelectricity.implement.IItemElectric;

/**
 * 
 * The Tile Entity for the Grinder.
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityGrinder extends TileEntityElectricityReceiver implements IRedstoneReceptor, IInventory, ISidedInventory, ISpecialInventory, IRotatable, IPacketReceiver, IItemTransfer
{
	public int ELECTRICITY_REQUIRED = 10;
	
	public int TICKS_REQUIRED = FMLCommonHandler.instance().getSide().isServer() ? HawksMachinery.crusherTicks : 180;
	
	public ForgeDirection facingDirection = ForgeDirection.UNKNOWN;
	
	public double electricityStored = 0;
	
	public int workTicks = 0;
	
	private boolean isBeingPoweredByRedstone;
	
	public ItemStack[] containingItems = new ItemStack[3];
	
	private int grinderStatus;
	
	public int ELECTRICITY_LIMIT = 2500;
	
	public boolean isOpen;
	
	public HawkTileEntityGrinder()
	{
		super();
	}
	
	@Override
	public void onReceive(TileEntity tileEntity, double amps, double voltage, ForgeDirection side)
	{
		
		if (voltage > this.getVoltage())
		{
			this.explodeCrusher(0.7F);
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
		
		if ((this.canGrind() || this.canExplode()) && !this.isDisabled())
		{
			if (this.containingItems[1] != null && this.workTicks == 0)
			{
				this.workTicks = this.TICKS_REQUIRED;
			}
			
			if ((this.canGrind() || this.canExplode()) && this.workTicks > 0)
			{
				--this.workTicks;
				
				if (this.workTicks < 1)
				{
					this.grindItem();
					this.workTicks = 0;
				}
				
				this.electricityStored -= this.ELECTRICITY_REQUIRED;
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
		
		if ((!this.canGrind() || !this.canExplode()) && this.workTicks != 0)
		{
			this.workTicks = 0;
		}
		
		if (!this.worldObj.isRemote && this.isOpen)
		{
			PacketManager.sendTileEntityPacketWithRange(this, "HawksMachinery", 8, this.workTicks, this.electricityStored);
		}
		
	}
	
	private boolean canGrind()
	{
		if (this.containingItems[1] == null)
		{
			return false;
		}
		else
		{
			if (this.electricityStored >= this.ELECTRICITY_REQUIRED * 2)
			{
				ItemStack var1 = HawkProcessingRecipes.getResult(this.containingItems[1], HawkEnumProcessing.CRUSHING);
				if (var1 == null) return false;
				if (this.containingItems[2] == null) return true;
				if (!this.containingItems[2].isItemEqual(var1)) return false;
				int result = containingItems[2].stackSize + var1.stackSize;
				return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
			}
			else
			{
				return false;
			}
		}
	}
	
	private boolean canExplode()
	{
		if (this.containingItems[1] == null)
		{
			return false;
		}
		else
		{
			if (this.electricityStored >= this.ELECTRICITY_REQUIRED * 2)
			{
				ItemStack var1 = HawkProcessingRecipes.getResult(this.containingItems[1], HawkEnumProcessing.CRUSHING_EXPLOSIVES);
				
				if (var1 == null) return false;
				if (this.containingItems[2] == null) return true;
				if (!this.containingItems[2].isItemEqual(var1)) return false;
				int result = containingItems[2].stackSize + var1.stackSize;
				return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
			}
			else
			{
				return false;
			}
		}
	}
	
	private void grindItem()
	{
		if (this.canGrind())
		{
			ItemStack var1 = HawkProcessingRecipes.getResult(this.containingItems[1], HawkEnumProcessing.CRUSHING);
			
			if (this.containingItems[2] == null)
			{
				this.containingItems[2] = var1.copy();
			}
			else if (this.containingItems[2].isItemEqual(var1))
			{
				++this.containingItems[2].stackSize;
			}
			
			--this.containingItems[1].stackSize;
			
			if (this.containingItems[1].stackSize <= 0)
			{
				this.containingItems[1] = null;
			}
		}
		else
		{
			if (this.canExplode())
			{
				--this.containingItems[1].stackSize;
				this.explodeCrusher(2.0F);
			}
		}
	}
	
	@Override
	public double wattRequest()
	{
		if (!this.isDisabled() && (this.canGrind() || this.canExplode()) && this.electricityStored + this.ELECTRICITY_REQUIRED <= this.ELECTRICITY_LIMIT)
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
		return "HMCrusher";
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
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
		
		NBTTagList var2 = new NBTTagList();
		
		for (int counter = 0; counter < this.containingItems.length; ++counter)
		{
			if (this.containingItems[counter] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)counter);
				this.containingItems[counter].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		
		NBTTag.setTag("Items", var2);
	}
	
	@Override
	public double getVoltage()
	{
		return 120;
	}
	
	@Override
	public void onPowerOn()
	{
		this.isBeingPoweredByRedstone = true;
	}
	
	@Override
	public void onPowerOff()
	{
		this.isBeingPoweredByRedstone = false;
	}
	
	public int getGrindingStatus(int par1)
	{
		return this.workTicks * par1 / 200;
	}
	
	public String getGrinderStatus()
	{	
		if (this.isDisabled())
		{
			this.grinderStatus = 2;
		}
		else if (this.workTicks > 0)
		{
			this.grinderStatus = 1;
		}
		else
		{
			this.grinderStatus =  0;
		}
		
		switch (this.grinderStatus)
		{
			case 1: return "Grinding";
			case 2: return "Disabled!";
			default: return "Idle";
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
	
	/**
	 * Causes the current Crusher to explode.
	 * @param strength The strength of the explosion.
	 */
	private void explodeCrusher(float strength)
	{
		this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, strength);
	}
	
	@Override
	public ItemStack offerItem(Object source, ItemStack offer)
	{
		if (offer != null)
		{
			if (HawkProcessingRecipes.getResult(offer, HawkEnumProcessing.CRUSHING) != null)
			{
				if (this.containingItems[1] == null)
				{
					this.containingItems[1] = offer;
					System.out.println("Stuff put into 1!");
					return null;
				}
				else
				{
					if (this.containingItems[1].isItemEqual(offer))
					{
						if (this.containingItems[1].stackSize + offer.stackSize <= 64)
						{
							this.containingItems[1].stackSize += offer.stackSize;
							System.out.println("Crap put into 1!");
							return null;
						}
						else
						{
							int extraAmount = (this.containingItems[1].stackSize + offer.stackSize) - 64;
							
							this.containingItems[1].stackSize += offer.stackSize;
							System.out.println("Dung put into 1!");
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
						if (this.containingItems[0] !=null)
						{
							this.containingItems[0] = offer;
							System.out.println("Bittery put into 0!");
							return null;
						}
						else
						{
							if (((IItemElectric)offer.getItem()).getWattHours(offer) > ((IItemElectric)offer.getItem()).getWattHours(this.containingItems[0]))
							{
								ItemStack oldElectricItem = this.containingItems[0];
								
								this.containingItems[0] = offer;
								System.out.println("Battery put into 0!");
								return oldElectricItem;
							}
						}
						
					}
				}
			}
			
		}
		
		return offer;
	}
	
	@Override
	public ItemStack requestItem(Object source)
	{
		return this.containingItems[2];
	}
	
	@Override
	public ItemStack requestItem(Object source, ItemStack request)
	{
		return this.containingItems[1];
	}
	
	@Override
	public ItemStack requestItem(Object source, EnumItemType request)
	{
		return this.containingItems[1];
	}
	
	@Override
	public int addItem(ItemStack stack, boolean doAdd, Orientations from)
	{
		if (from.ordinal() == 0 && stack.getItem() instanceof IItemElectric)
		{
			if (this.containingItems[0] == null)
			{
				if (doAdd)
				{
					this.containingItems[0] = stack;
				}
				
				return stack.stackSize;
			}
			else
			{
				if (this.containingItems[0].isItemEqual(stack) && this.containingItems[0].isStackable())
				{
					if (stack.stackSize + this.containingItems[0].stackSize <= stack.getMaxStackSize())
					{
						if (doAdd)
						{
							this.containingItems[0].stackSize += stack.stackSize;
						}
						
						return stack.stackSize;
					}
					else
					{
						if (doAdd)
						{
							this.containingItems[0].stackSize += stack.getMaxStackSize();
						}
						
						return stack.getMaxStackSize() - stack.stackSize;
					}
					
				}
			}
		}
		
		if (from.ordinal() == 1 && HawkProcessingRecipes.getResult(stack, HawkEnumProcessing.CRUSHING) != null)
		{
			if (this.containingItems[1] == null)
			{
				if (doAdd)
				{
					this.containingItems[1] = stack;
				}
				
				return stack.stackSize;
			}
			else
			{
				if (this.containingItems[1].isItemEqual(stack) && this.containingItems[1].isStackable())
				{
					if (stack.stackSize + this.containingItems[1].stackSize <= stack.getMaxStackSize())
					{
						if (doAdd)
						{
							this.containingItems[1].stackSize += stack.stackSize;
						}
						
						return stack.stackSize;
					}
					else
					{
						if (doAdd)
						{
							this.containingItems[1].stackSize += stack.getMaxStackSize();
						}
						
						return stack.getMaxStackSize() - stack.stackSize;
					}
					
				}
			}
		}
		
		return 0;
	}
	
	@Override
	public ItemStack[] extractItem(boolean doRemove, Orientations from, int maxItemCount)
	{
		if (from.ordinal() == this.facingDirection.ordinal() && this.containingItems[2] != null)
		{
			return new ItemStack[]{this.containingItems[2]};
		}
		
		return null;
	}
	
}