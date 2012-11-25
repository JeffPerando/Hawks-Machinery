
package hawksmachinery.tileentity;

import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.multiblock.IMultiBlock;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityStarForge extends HMTileEntityMachine implements IMultiBlock
{
	/**
	 * The item this Star Forge is going to spit out.
	 */
	public ItemStack output;
	
	public HMTileEntityStarForge()
	{
		super();
		ELECTRICITY_REQUIRED = 15;
		TICKS_REQUIRED = 100;
		ELECTRICITY_LIMIT = 2000;
		containingItems = new ItemStack[10];
		voltage = 120;
		isProcessor = true;
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!this.isDisabled())
		{
			if (this.canForge())
			{
				if (this.workTicks == 0)
				{
					this.workTicks = this.TICKS_REQUIRED;
					
				}
				else
				{
					--this.workTicks;
					
				}
				
				if (this.workTicks == 1)
				{
					this.forgeItem();
					this.workTicks = 0;
					
				}
				
			}
			else
			{
				this.workTicks = 0;
			}
			
		}
		
	}
	
	public void setForgeResult(ItemStack item)
	{
		this.output = item;
		
	}
	
	public boolean canForge()
	{
		return this.output != null && (this.electricityStored >= this.ELECTRICITY_REQUIRED * 2 && !this.isDisabled()) && (this.containingItems[9] == null || (this.output.isItemEqual(this.containingItems[9]) && this.output.stackSize + this.containingItems[9].stackSize <= this.output.getMaxStackSize()));
	}
	
	public void forgeItem()
	{
		if (this.containingItems[9] == null)
		{
			this.containingItems[9] = this.output;
			
		}
		else
		{
			if (this.containingItems[9].isItemEqual(this.output) && this.output.stackSize + this.containingItems[9].stackSize <= this.output.getMaxStackSize())
			{
				this.containingItems[9].stackSize += this.output.stackSize;
				
			}
			
		}
		
		for (int counter = 0; counter < 9; ++counter)
		{
			--this.containingItems[counter].stackSize;
			
			if (this.containingItems[counter].stackSize == 0) this.containingItems[counter] = null;
			
		}
		
		this.randomlyDamageSelf();
		
	}

	@Override
	public boolean onActivated(EntityPlayer entityPlayer)
	{
		return false;
	}

	@Override
	public void onCreate(Vector3 placedPosition)
	{
		
	}

	@Override
	public void onDestroy(TileEntity callingBlock)
	{
		
	}
	
}
