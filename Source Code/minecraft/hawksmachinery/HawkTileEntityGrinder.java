
package hawksmachinery;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.forge.ISidedInventory;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.universalelectricity.electricity.ElectricityManager;
import net.minecraft.src.universalelectricity.electricity.TileEntityElectricUnit;
import net.minecraft.src.universalelectricity.extend.IRedstoneReceptor;
import net.minecraft.src.universalelectricity.extend.IRotatable;
import net.minecraft.src.universalelectricity.extend.ItemElectric;

/**
 * A tile entity that grinds stuff
 * @author Elusivehawk
 *
 */
public class HawkTileEntityGrinder extends TileEntityElectricUnit implements IRedstoneReceptor, ITextureProvider, IInventory, ISidedInventory, IRotatable
{
	public int electricityRequired = 60;

	public int ticksNeededtoProcess = 160;
	
	public byte facingDirection = 0;
	
	public float electricityStored = 0;
	
	public int workTicks = 0;
	
	private boolean isBeingPoweredByRedstone;
	
    private ItemStack[] containingItems = new ItemStack[3];
    
    public int electricityCapacity = 1250;
    
    public HawkTileEntityGrinder()
    {
    	ElectricityManager.registerElectricUnit(this);
    }
    
    @Override
	public void onUpdate(float watts, float voltage, byte side)
	{
		super.onUpdate(watts, voltage, side);
		
		if(!this.worldObj.isRemote)
        {			
			if(voltage > this.getVoltage())
	    	{
	    		 this.worldObj.createExplosion((Entity)null, this.xCoord, this.yCoord, this.zCoord, 0.7F);
	    	}
			
			//The slot is for portable batteries to be used in the grinder
	    	if(this.containingItems[0] != null)
	        {
	            if(this.containingItems[0].getItem() instanceof ItemElectric)
	            {
		           	ItemElectric electricItem = (ItemElectric)this.containingItems[0].getItem();
		           	
	            	if(electricItem.canProduceElectricity())
		           	{
		            	double receivedElectricity = electricItem.onUseElectricity(electricItem.getTransferRate(), this.containingItems[0]);
		            	this.electricityStored += receivedElectricity;
		           	}
	            }
	        }
	    	
			this.electricityStored += watts;
						
	    	if (this.canGrind() && !this.isDisabled())
	    	{
		    	if(this.containingItems[1] != null && this.workTicks == 0)
		        {
		        	this.workTicks = this.ticksNeededtoProcess;
		        }
		    	
		        if(this.canGrind() && this.workTicks > 0)
		    	{
		    		this.workTicks -= this.getTickInterval();
		    		
		    		if(this.workTicks < 1*this.getTickInterval())
		    		{
		    			this.grindItem();
		    			this.workTicks = 0;
		    		}
		    		
		    		this.electricityStored = this.electricityStored - this.electricityRequired;
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
	    	
	    	if (this.electricityStored >= this.electricityCapacity)
	    	{
	    		this.electricityStored = this.electricityCapacity;
	    	}
	    	
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
        	if (this.electricityStored >= this.electricityRequired * 2)
        	{
                ItemStack var1 = HawkProcessingRecipes.getGrindingResult(this.containingItems[1]);
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
            ItemStack var1 = HawkProcessingRecipes.getGrindingResult(this.containingItems[1]);

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
    }

	@Override
	public float electricityRequest()
	{
		if (this.canGrind())
		{
			return electricityRequired;
		}
		else
		{
			if (this.electricityCapacity != this.electricityStored)
			{
				return (this.electricityCapacity - this.electricityStored) / 10;
			}
			else
			{
				return 0;
			}
		}
	}

	@Override
	public boolean canReceiveFromSide(byte side)
	{
		return true;
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
    public byte getDirection()
    {
	    return this.facingDirection;
    }

	@Override
    public void setDirection(byte facingDirection)
    {
	    this.facingDirection = facingDirection;
    }
	
    /**
     * Reads a tile entity from NBT.
     */
    @Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readFromNBT(par1NBTTagCompound);
    	this.electricityStored = par1NBTTagCompound.getFloat("electricityStored");
    	this.facingDirection = par1NBTTagCompound.getByte("facingDirection");
    	
    	NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
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
    /**
     * Writes a tile entity to NBT.
     */
    @Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeToNBT(par1NBTTagCompound);
    	par1NBTTagCompound.setFloat("electricityStored", this.electricityStored);
    	par1NBTTagCompound.setByte("facingDirection", this.facingDirection);
    	
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
        par1NBTTagCompound.setTag("Items", var2);
    }
    
	@Override
    public float getVoltage()
    {
	    return 120;
    }

	@Override
    public String getTextureFile()
    {
	    return HawkManager.blockTextureFile;
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
			return "Disabled!";
		}
		else if (this.workTicks > 0)
		{
			return "Grinding";
		}
		else
		{
			return "Idle";
		}
	}
	
	public float getElectricityStored()
	{
		return this.electricityStored;
	}
}
