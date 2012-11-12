
package hawksmachinery.tileentity;

import java.util.Random;
import universalelectricity.electricity.ElectricInfo;
import universalelectricity.implement.IItemElectric;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import hawksmachinery.api.HMProcessingRecipes.HMEnumProcessing;
import hawksmachinery.item.HMItem;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityFisher extends HMTileEntityMachine
{
	public HMTileEntityFisher()
	{
		super();
		ELECTRICITY_REQUIRED = 5;
		ELECTRICITY_LIMIT = 1000;
		containingItems = new ItemStack[19];
		voltage = 120;
		
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
						this.electricityStored += ElectricInfo.getWatts(receivedElectricity);
					}
				}
			}
			
			if (this.worldObj.getBlockId(this.xCoord, this.yCoord - 1, this.zCoord) == Block.waterStill.blockID && this.canFish())
			{
				this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord, 0);
				this.electricityStored -= this.ELECTRICITY_REQUIRED;
				
				if (new Random().nextInt(100) > 5)
				{
					this.addFishAndRemoveFood();
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side == ForgeDirection.UP;
	}
	
	public boolean canFish()
	{
		int fullStacks = 0;
		boolean hasFood = false;
		
		for (int counter = 1; counter < 10; ++counter)
		{
			if (this.containingItems[counter] != null)
			{
				if (this.containingItems[counter].stackSize < this.containingItems[counter].getMaxStackSize())
				{
					continue;
				}
				else
				{
					++fullStacks;
					
				}
				
			}
			
			if (this.containingItems[counter + 9] != null && !hasFood)
			{
				hasFood = this.containingItems[counter + 9].itemID == HMItem.fishFood.shiftedIndex && this.containingItems[counter + 9].stackSize > 0;
			}
			
		}
		
		return fullStacks != 9 && hasFood && this.electricityStored >= (this.ELECTRICITY_REQUIRED * 2);
	}
	
	public void addFishAndRemoveFood()
	{
		if (this.canFish())
		{
			boolean removedFood = false;
			boolean addedFish = false;
			
			for (int counter = 1; counter < 10; ++counter)
			{
				if (!addedFish)
				{
					if (this.containingItems[counter] != null)
					{
						if (this.containingItems[counter].stackSize < this.containingItems[counter].getMaxStackSize())
						{
							++this.containingItems[counter].stackSize;
							addedFish = true;
							
						}
						
					}
					else
					{
						this.containingItems[counter] = new ItemStack(Item.fishRaw);
						addedFish = true;
						
					}
					
				}
				
				if (this.containingItems[counter + 9] != null && !removedFood)
				{
					if (this.containingItems[counter + 9].isItemEqual(new ItemStack(HMItem.fishFood)))
					{
						if (this.containingItems[counter + 9].stackSize > 0)
						{
							--this.containingItems[counter + 9].stackSize;
							if (this.containingItems[counter + 9].stackSize == 0)
							{
								this.containingItems[counter + 9] = null;
							}
							
							removedFood = true;
							
						}
						
					}
					
				}
				
				if (addedFish && removedFood)
				{
					break;
				}
				
			}
			
		}
		
	}
	
}
