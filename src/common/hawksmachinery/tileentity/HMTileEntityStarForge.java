
package hawksmachinery.tileentity;

import net.minecraft.src.ItemStack;

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
		
	}
	
	public boolean canForge()
	{
		return this.output != null && (this.electricityStored >= this.ELECTRICITY_REQUIRED * 2 && !this.isDisabled()) && (this.containingItems[9] == null || (this.output.isItemEqual(this.containingItems[9]) && this.output.stackSize + this.containingItems[9].stackSize <= this.output.getMaxStackSize()));
	}
	
}
