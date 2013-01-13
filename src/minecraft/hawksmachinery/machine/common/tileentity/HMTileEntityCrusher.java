
package hawksmachinery.machine.common.tileentity;

import hawksmachinery.core.common.HMCore;
import hawksmachinery.core.common.api.HMRecipes;
import hawksmachinery.core.common.api.HMRecipes.HMEnumProcessing;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.electricity.ElectricInfo;
import universalelectricity.core.implement.IItemElectric;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityCrusher extends HMTileEntityMachine
{
	public HMTileEntityCrusher()
	{
		super();
		ELECTRICITY_REQUIRED = 5;
		TICKS_REQUIRED = FMLCommonHandler.instance().getSide().isServer() ? HMCore.instance().MANAGER.crusherTicks : 200;
		ELECTRICITY_LIMIT = 2500;
		containingItems = new ItemStack[3];
		machineEnum = HMEnumProcessing.CRUSHING;
		VOLTAGE = 120;
		isProcessor = true;
		canRotate = true;
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!this.isDisabled())
		{
			if (this.containingItems[0] != null)
			{
				if (this.containingItems[0].getItem() instanceof IItemElectric)
				{
					IItemElectric electricItem = (IItemElectric)this.containingItems[0].getItem();
					
					if (electricItem.canProduceElectricity() && this.electricityStored > this.ELECTRICITY_LIMIT)
					{
						double receivedElectricity = electricItem.onUse(Math.min(electricItem.getMaxJoules()*0.01, ElectricInfo.getWattHours(this.ELECTRICITY_REQUIRED)), this.containingItems[0]);
						this.electricityStored += ElectricInfo.getJoules(receivedElectricity, 1);
						
					}
					
				}
				
			}
			
			if (this.canWork())
			{
				if (this.workTicks == 0)
				{
					this.workTicks = this.TICKS_REQUIRED;
					
				}
				else
				{
					--this.workTicks;
					this.electricityStored -= this.ELECTRICITY_REQUIRED;
					
					if (this.workTicks == 1)
					{
						this.crushItem();
						this.workTicks = 0;
						
					}
					
				}
				
			}
			else
			{
				this.workTicks = 0;
				
			}
			
		}
		
	}
	
	@Override
	public void onInventoryChanged()
	{
		
	}
	
	public boolean canWork()
	{
		ItemStack output = HMRecipes.getResult(this.containingItems[1], this.machineEnum);
		return output != null && (this.electricityStored >= (this.ELECTRICITY_REQUIRED * 2)) && (this.containingItems[2] == null || (output.isItemEqual(this.containingItems[2]) && output.stackSize + this.containingItems[2].stackSize <= output.getMaxStackSize()));
	}
	
	private void crushItem()
	{
		if (this.canWork())
		{
			ItemStack newItem = HMRecipes.getResult(this.containingItems[1], this.machineEnum);
			
			if (this.containingItems[2] == null)
			{
				this.containingItems[2] = newItem.copy();
			}
			else if (this.containingItems[2].isItemEqual(newItem))
			{
				this.containingItems[2].stackSize += newItem.stackSize;
			}
			
			this.decrStackSize(1, HMRecipes.getQuantity(this.containingItems[1], this.machineEnum));
			
			this.randomlyDamageSelf();
			
		}
		
	}
	
	@Override
	public int getStartInventorySide(ForgeDirection side)
	{
		if (side == ForgeDirection.UP) return 1;
		
		if (side == ForgeDirection.DOWN) return 0;
		
		return 2;
	}
	
	@Override
	public int getSizeInventorySide(ForgeDirection side)
	{
		return 1;
	}
	
	@Override
	public String getInvName()
	{
		return "HMCrusher";
	}
	
	public int calculateCrushingDuration(int par1)
	{
		return this.workTicks * par1 / this.TICKS_REQUIRED;
	}
	
}
