
package hawksmachinery.tileentity;

import hawksmachinery.HMProcessingRecipes;
import hawksmachinery.HMProcessingRecipes.HawkEnumProcessing;
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
public class HMTileEntityCrusher extends HMTileEntityMachine implements ISpecialInventory, IItemTransfer
{
	private int crusherStatus;
	
	public HMTileEntityCrusher()
	{
		super();
		ELECTRICITY_REQUIRED = 10;
		TICKS_REQUIRED = FMLCommonHandler.instance().getSide().isServer() ? HawksMachinery.MANAGER.crusherTicks : 180;
		ELECTRICITY_LIMIT = 2500;
		containingItems = new ItemStack[3];
		machineEnum = HawkEnumProcessing.CRUSHING;
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
					
					if (electricItem.canProduceElectricity() && this.electricityStored + electricItem.getTransferRate() <= this.ELECTRICITY_LIMIT)
					{
						double receivedElectricity = electricItem.onUseElectricity(electricItem.getTransferRate(), this.containingItems[0]);
						this.electricityStored += receivedElectricity;
					}
				}
			}
			
			if ((this.canCrush() || this.canExplode()) && !this.isDisabled())
			{
				if (this.containingItems[1] != null && this.workTicks == 0)
				{
					this.workTicks = this.TICKS_REQUIRED;
				}
				
				if ((this.canCrush() || this.canExplode()) && this.workTicks > 0)
				{
					--this.workTicks;
					
					if (this.workTicks < 1)
					{
						this.crushItem();
						this.workTicks = 0;
					}
					
					this.electricityStored -= this.ELECTRICITY_REQUIRED;
				}
				else
				{
					this.workTicks = 0;
				}
			}
			
			if (!(this.canCrush() || this.canExplode()) && this.workTicks != 0)
			{
				this.workTicks = 0;
			}
			
		}
		
	}
	
	private boolean canCrush()
	{
		if (this.containingItems[1] == null)
		{
			return false;
		}
		else
		{
			if (this.electricityStored >= this.ELECTRICITY_REQUIRED * 2 && !this.isDisabled())
			{
				ItemStack var1 = HMProcessingRecipes.getResult(this.containingItems[1], this.machineEnum);
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
				ItemStack var1 = HMProcessingRecipes.getResult(this.containingItems[1], HawkEnumProcessing.CRUSHING_EXPLOSIVES);
				
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
	
	private void crushItem()
	{
		if (this.canCrush())
		{
			ItemStack var1 = HMProcessingRecipes.getResult(this.containingItems[1], machineEnum);
			
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
			
			this.randomlyDamageSelf();
			
		}
		else
		{
			if (this.canExplode())
			{
				--this.containingItems[1].stackSize;
				this.explodeMachine(2.0F);
			}
		}
	}
	
	@Override
	public double wattRequest()
	{
		if (this.isDisabled())
		{
			return 0;
		}
		else
		{
			if ((this.canCrush() || this.canExplode()) && this.electricityStored + this.ELECTRICITY_REQUIRED <= this.ELECTRICITY_LIMIT)
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
			this.crusherStatus = 1;
		}
		else if (this.isDisabled())
		{
			this.crusherStatus = 2;
		}
		else
		{
			this.crusherStatus =  0;
		}
		
		switch (this.crusherStatus)
		{
			case 1: return "Crushing";
			case 2: return "Disabled!";
			default: return "Idle";
		}
	}
	
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
	
}