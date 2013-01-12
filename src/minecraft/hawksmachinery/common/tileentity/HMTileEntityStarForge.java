
package hawksmachinery.common.tileentity;

import hawksmachinery.common.HMInventoryCrafting;
import hawksmachinery.common.api.HMRecipes;
import hawksmachinery.common.api.IHMTechnicalMultiBlock;
import hawksmachinery.common.api.helpers.HMVector;
import hawksmachinery.common.block.HMBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityStarForge extends HMTileEntityMachine
{
	/**
	 * The item this Star Forge is going to spit out.
	 */
	public ItemStack output;
	public HMInventoryCrafting matrix = new HMInventoryCrafting("Star Forge", 3, 3, this);
	
	public HMTileEntityStarForge()
	{
		super();
		ELECTRICITY_REQUIRED = 20;
		TICKS_REQUIRED = 100;
		ELECTRICITY_LIMIT = 2000;
		containingItems = new ItemStack[10];
		VOLTAGE = 120;
		isProcessor = true;
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
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
				
				if (this.workTicks == 1)
				{
					this.forgeItem();
					this.workTicks = 0;
					
				}
				
			}
			
		}
		else
		{
			this.workTicks = 0;
			
		}
		
	}
	
	public boolean canWork()
	{
		this.output = HMRecipes.getForgeResult(this.matrix, this.worldObj);
		return this.output != null && !this.isDisabled() && (this.electricityStored >= (this.ELECTRICITY_REQUIRED * 2)) && (this.containingItems[9] == null || (this.output.isItemEqual(this.containingItems[9]) && this.output.stackSize + this.containingItems[9].stackSize <= this.output.getMaxStackSize()));
	}
	
	public void forgeItem()
	{
		if (this.containingItems[9] == null)
		{
			this.containingItems[9] = this.output.copy();
			
		}
		else
		{
			if (this.containingItems[9].isItemEqual(this.output))
			{
				this.containingItems[9].stackSize += this.output.copy().stackSize;
				
			}
			
		}
		
		for (int counter = 0; counter < 9; ++counter)
		{
			if (this.containingItems[counter] != null)
			{
				this.decrStackSize(counter, 1);
				
			}
			
		}
		
		this.randomlyDamageSelf();
		
	}
	
	public void destroyExtraBlocks()
	{
		for (int x = -1; x < 2; ++x)
		{
			for (int z = -1; z < 2; ++z)
			{
				if (x != 0 || z != 0)
				{
					this.worldObj.setBlockWithNotify(this.xCoord + x, this.yCoord, this.zCoord + z, 0);
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		if (NBTTag.hasKey("output")) this.output = ItemStack.loadItemStackFromNBT(NBTTag.getCompoundTag("output"));
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		if (this.output != null) NBTTag.setCompoundTag("output", this.output.writeToNBT(new NBTTagCompound()));
		
	}
	
}
