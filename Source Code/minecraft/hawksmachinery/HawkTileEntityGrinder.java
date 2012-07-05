/**
 * 
 */
package hawksmachinery;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.src.*;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.UEIConsumer;
import net.minecraft.src.universalelectricity.UEIPacketReceiver;
import net.minecraft.src.universalelectricity.UEIProducer;
import net.minecraft.src.universalelectricity.UEIRedstoneReceptor;
import net.minecraft.src.universalelectricity.UEIRotatable;
import net.minecraft.src.universalelectricity.components.UniversalComponents;

/**
 * @author Elusivehawk
 *
 */
public class HawkTileEntityGrinder extends TileEntity implements UEIPacketReceiver, UEIRedstoneReceptor, ITextureProvider, UEIConsumer, IInventory, ISidedInventory, UEIRotatable
{
	public static HawkTileEntityGrinder instance;

	public static int timeNeededtoProcess = 100;
	
	public byte facingDirection = 0;
	
	public static int electricityStored = 0;
	
	public static int workTicks = 0;
	
	private static boolean isBeingPoweredByRedstone;
	
    private ItemStack[] processingItemStacks = new ItemStack[3];
    
    public HawkTileEntityGrinder()
    {
    	UniversalComponents.packetManager.registerPacketUser(this);
    	instance = this;
    }
	
	@Override
    public int getStartInventorySide(int side)
    {
		if (side == 0)
        {
        	return 1;
        }
        if (side == 1)
        {
        	return 0;
        }
        return 2;
    }
	
	@Override
    public int getSizeInventorySide(int side)
    {
	    return 1;
    }

	@Override
    public int getSizeInventory()
    {
		return this.processingItemStacks.length;
    }

	@Override
    public ItemStack getStackInSlot(int var1)
    {
		return this.processingItemStacks[var1];
    }

	@Override
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.processingItemStacks[var1] != null)
        {
            ItemStack var3;

            if (this.processingItemStacks[var1].stackSize <= var2)
            {
                var3 = this.processingItemStacks[var1];
                this.processingItemStacks[var1] = null;
                return var3;
            }
            else
            {
                var3 = this.processingItemStacks[var1].splitStack(var2);

                if (this.processingItemStacks[var1].stackSize == 0)
                {
                    this.processingItemStacks[var1] = null;
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
        if (this.processingItemStacks[var1] != null)
        {
            ItemStack var2 = this.processingItemStacks[var1];
            this.processingItemStacks[var1] = null;
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
        this.processingItemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

	@Override
    public String getInvName()
    {
	    return "Grinder";
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
    public void openChest() {}

	@Override
    public void closeChest() {}

	@Override
	public void onPacketData(NetworkManager network, String channel, byte[] data)
	{
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(data));

        try
        {
        	int packetID = dataStream.readInt();
        	this.facingDirection = (byte)dataStream.readDouble();
        	this.electricityStored = (int)dataStream.readDouble();
        	this.timeNeededtoProcess = (int)dataStream.readDouble();
        	this.isBeingPoweredByRedstone = (boolean)dataStream.readBoolean();
        }
        catch(IOException e)
        {
             e.printStackTrace();
        }
	}

	@Override
    public void onDisable(int duration)
    {
		for (this.isBeingPoweredByRedstone = true;;)
		{
			duration = 30;
		}
    }

	@Override
    public boolean isDisabled()
    {
	    return this.isBeingPoweredByRedstone;
    }

	@Override
    public byte getDirection()
    {
	    return this.facingDirection;
    }

	@Override
    public void setDirection(byte facingDirection)
    {
	    this.facingDirection = facingDirection;
    }

	@Override
    public int onReceiveElectricity(int watts, int voltage, byte side)
    {
		if (this.getStoredElectricity() <= getElectricityCapacity()) //If the currently stored electricity isn't maxed out.
		{
			if ((this.getStoredElectricity() + watts) <= getElectricityCapacity()) //If the received watts won't overflow the machine's capacity.
			{
				this.electricityStored = this.electricityStored + watts;
				return 0;
			}
			else //Otherwise...
			{
				this.electricityStored = getElectricityCapacity();
				return ((getStoredElectricity() + watts) - getElectricityCapacity());
			}
		}
	    return 0;
    }

	@Override
    public boolean canReceiveElectricity(byte side)
    {
	    return true;
    }
	
	public void updateEntity()
	{
		if((this.getStoredElectricity()) >= 100 && (this.canGrind()))
		{
			--this.electricityStored;
			++this.workTicks;
			
			if (this.workTicks == this.timeNeededtoProcess)
			{
				this.grindItem();
			}
		}
	}
	
	
    /**
     * Reads a tile entity from NBT.
     */
    @Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readFromNBT(par1NBTTagCompound);
    	this.electricityStored = par1NBTTagCompound.getInteger("electricityStored");
    	this.facingDirection = par1NBTTagCompound.getByte("facingDirection");
    	this.timeNeededtoProcess = par1NBTTagCompound.getInteger("timeNeededtoProcess");
    	NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.processingItemStacks = new ItemStack[this.getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < this.processingItemStacks.length)
            {
                this.processingItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }
    /**
     * Writes a tile entity to NBT.
     */
    @Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeToNBT(par1NBTTagCompound);
    	par1NBTTagCompound.setInteger("electricityStored", this.electricityStored);
    	par1NBTTagCompound.setByte("facingDirection", this.facingDirection);
    	par1NBTTagCompound.setInteger("timeNeededtoProcess", this.timeNeededtoProcess);
    	NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.processingItemStacks.length; ++var3)
        {
            if (this.processingItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.processingItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        par1NBTTagCompound.setTag("Items", var2);
    }
    
    private boolean canGrind()
    {
        if (this.processingItemStacks[0] == null && this.electricityStored >= timeNeededtoProcess)
        {
            return false;
        }
        else
        {
            ItemStack var1 = ((HawkProcessingRecipes) HawkProcessingRecipes.getGrindingList()).getGrindingResult(this.processingItemStacks[0]);
            if (var1 == null) return false;
            if (this.processingItemStacks[2] == null) return true;
            if (!this.processingItemStacks[2].isItemEqual(var1)) return false;
            int result = processingItemStacks[2].stackSize + var1.stackSize;
            return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
        }
    }


    public void grindItem()
    {
        if (this.canGrind())
        {
            ItemStack var1 = HawkProcessingRecipes.getGrindingResult(this.processingItemStacks[0]);

            if (this.processingItemStacks[2] == null)
            {
                this.processingItemStacks[2] = var1.copy();
            }
            else if (this.processingItemStacks[2].isItemEqual(var1))
            {
                ++this.processingItemStacks[2].stackSize;
            }

            --this.processingItemStacks[0].stackSize;

            if (this.processingItemStacks[0].stackSize <= 0)
            {
                this.processingItemStacks[0] = null;
            }
        }
    }
    

	@Override
    public int getStoredElectricity()
    {
	    return this.electricityStored;
    }

	@Override
    public int getElectricityCapacity()
    {
	    return 1800;
    }

	@Override
    public int getVolts()
    {
	    return 120;
    }

	@Override
    public String getTextureFile()
    {
	    return "/hawksmachinery/blocks.png";
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

	@Override
    public int getPacketID()
    {
	    // TODO Make an SMP version
	    return 0;
    }

}
