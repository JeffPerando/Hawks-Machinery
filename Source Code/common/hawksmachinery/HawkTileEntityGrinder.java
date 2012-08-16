
package hawksmachinery;

import java.io.DataInputStream;
import java.io.IOException;
import com.google.common.io.ByteArrayDataInput;
import universalelectricity.electricity.ElectricityManager;
import universalelectricity.electricity.TileEntityElectricUnit;
import universalelectricity.extend.IRedstoneReceptor;
import universalelectricity.extend.IRotatable;
import universalelectricity.network.IPacketReceiver;
import universalelectricity.network.PacketManager;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import universalelectricity.extend.IItemElectric;

/**
 * 
 * A tile entity that grinds stuff
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityGrinder extends TileEntityElectricUnit implements IRedstoneReceptor, IInventory, ISidedInventory, IRotatable, IPacketReceiver
{
	public int electricityRequired = 10;

	public int ticksNeededtoProcess = 100;
	
	public byte facingDirection = 0;
	
	public float electricityStored = 0;
	
	public int workTicks = 0;
	
	private boolean isBeingPoweredByRedstone;
	
    private ItemStack[] containingItems = new ItemStack[3];
    
    private int grinderStatus;
    
    public int electricityCapacity = 2500;
    
    public HawkTileEntityGrinder()
    {
    	ElectricityManager.registerElectricUnit(this);
    }
    
    @Override
	public void onUpdate(float watts, float voltage, ForgeDirection side)
    {
		super.onUpdate(watts, voltage, side);
    			
		if (!worldObj.isRemote)
        {			
			if (voltage > this.getVoltage())
	    	{
				this.explodeGrinder(0.7F);
	    	}
			
			//The slot is for portable batteries to be used in the grinder
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
	    	
			this.electricityStored += watts;
						
	    	if ((this.canGrind() || this.canExplode()) && !this.isDisabled())
	    	{
		    	if(this.containingItems[1] != null && this.workTicks == 0)
		        {
		        	this.workTicks = this.ticksNeededtoProcess;
		        }
		    	
		        if((this.canGrind() || this.canExplode()) && this.workTicks > 0)
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
	    	
			PacketManager.sendTileEntityPacket(this, "HawksMachinery", new double[]{this.disabledTicks, this.workTicks, this.electricityStored, this.grinderStatus});
			
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

    private boolean canExplode()
    {
        if (this.containingItems[1] == null)
        {
            return false;
        }
        else
        {
        	if (this.electricityStored >= this.electricityRequired * 2)
        	{
                ItemStack var1 = HawkProcessingRecipes.getGrindingExplosive(this.containingItems[1]);
                
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
        else
        {
        	if (this.canExplode())
        	{
                --this.containingItems[1].stackSize;
        		this.explodeGrinder(2.0F);
        	}
        }
    }

	@Override
	public float electricityRequest()
	{
		if (this.canGrind() || this.canExplode())
		{
			return electricityRequired;
		}
		else
		{
			if (this.electricityCapacity != this.electricityStored)
			{
				if (this.electricityStored + this.electricityRequired >= this.electricityCapacity)
				{
					return this.electricityCapacity - this.electricityStored;
				}
				else
				{
					return this.electricityRequired;
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
		if (side == ForgeDirection.UP)
		{
			return false;
		}
		
		if (side == ForgeDirection.getOrientation(this.getBlockMetadata()))
		{
			return false;
		}
		
		return true;
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
	public void readFromNBT(NBTTagCompound NBTTag)
    {
    	super.readFromNBT(NBTTag);
    	this.electricityStored = NBTTag.getFloat("electricityStored");
    	this.facingDirection = NBTTag.getByte("facingDirection");
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
    /**
     * Writes a tile entity to NBT.
     */
    @Override
	public void writeToNBT(NBTTagCompound NBTTag)
    {
    	super.writeToNBT(NBTTag);
    	NBTTag.setFloat("electricityStored", this.electricityStored);
    	NBTTag.setByte("facingDirection", this.facingDirection);
    	NBTTag.setInteger("workTicks", this.workTicks);
    	
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
    public float getVoltage()
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
	
	public float getElectricityStored()
	{
		return this.electricityStored;
	}
	
	@Override
    public void handlePacketData(NetworkManager network, String channel, ByteArrayDataInput dataStream)
    {
		try
		{
			this.disabledTicks = (int)dataStream.readDouble();
			this.workTicks = (int)dataStream.readDouble();
			this.electricityStored = (int)dataStream.readDouble();
			this.grinderStatus = (int)dataStream.readDouble();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    }
	
	/**
	 * Causes the current Grinder to explode.
	 * @param strength The strength of the explosion.
	 */
	private void explodeGrinder(float strength)
	{
		 this.worldObj.createExplosion((Entity)null, this.xCoord, this.yCoord, this.zCoord, strength);
	}
}
