
package hawksmachinery.tileentity;

import hawksmachinery.HawksMachinery;
import hawksmachinery.api.HMRecipes;
import hawksmachinery.api.HMRecipes.HMEnumProcessing;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.electricity.ElectricInfo;
import universalelectricity.core.implement.IItemElectric;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * 
 * The Tile Entity for the Grinder.
 * 
 * @author Elusivehawk
 */
public class HMTileEntityCrusher extends HMTileEntityMachine
{
	public HMTileEntityCrusher()
	{
		super();
		ELECTRICITY_REQUIRED = 5;
		TICKS_REQUIRED = FMLCommonHandler.instance().getSide().isServer() ? HawksMachinery.instance().MANAGER.crusherTicks : 180;
		ELECTRICITY_LIMIT = 2500;
		containingItems = new ItemStack[3];
		machineEnum = HMEnumProcessing.CRUSHING;
		voltage = 120;
		isProcessor = true;
		
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
					
					if (this.workTicks == 1)
					{
						this.crushItem();
						this.workTicks = 0;
						
					}
					
				}
				
				this.electricityStored -= this.ELECTRICITY_REQUIRED;
				
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
	public String getInvName()
	{
		return "HMCrusher";
	}
	
	public int calculateCrushingDuration(int par1)
	{
		return this.workTicks * par1 / 200;
	}
	
	public String getCrusherStatus()
	{
		if (this.workTicks > 0)
		{
			return "Crushing";
		}
		else if (this.isDisabled())
		{
			return "Disabled";
		}
		else
		{
			return "Idle";
		}
		
	}
	/*
	@Override
	public ItemStack offerItem(Object source, ItemStack offer)
	{
		if (offer != null)
		{
			if (HMProcessingRecipes.getResult(offer, this.machineEnum) != null)
			{
				if (this.containingItems[1] == null)
				{
					this.containingItems[1] = offer;
					return null;
				}
				else
				{
					if (this.containingItems[1].isItemEqual(offer))
					{
						if (this.containingItems[1].stackSize + offer.stackSize <= 64)
						{
							this.containingItems[1].stackSize += offer.stackSize;
							return null;
						}
						else
						{
							int extraAmount = (this.containingItems[1].stackSize + offer.stackSize) - 64;
							
							this.containingItems[1].stackSize += offer.stackSize;
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
							return null;
						}
						else
						{
							if (((IItemElectric)offer.getItem()).getJoules(offer) > ((IItemElectric)offer.getItem()).getJoules(this.containingItems[0]))
							{
								ItemStack oldElectricItem = this.containingItems[0];
								
								this.containingItems[0] = offer;
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
		
		if (from.ordinal() == 1 && HMProcessingRecipes.getResult(stack, this.machineEnum) != null)
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
	*/
	
}
