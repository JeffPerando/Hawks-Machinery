
package hawksmachinery.machine.common.container;

import hawksmachinery.core.common.api.HMRecipes;
import hawksmachinery.core.common.api.HMRecipes.HMEnumProcessing;
import hawksmachinery.machine.common.tileentity.HMTileEntitySinterer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMContainerSinterer extends Container
{
	public HMTileEntitySinterer tileEntity;
	
	public HMContainerSinterer(InventoryPlayer playerInv, HMTileEntitySinterer tileEntity)
	{
		this.tileEntity = tileEntity;
		this.tileEntity.openChest();
		
		for (int counter = 0; counter < 3; ++counter)
		{
			this.addSlotToContainer(new Slot(this.tileEntity, counter, 53, 17 + (counter * 18)));
			this.addSlotToContainer(new SlotFurnace(playerInv.player, this.tileEntity, counter + 3, 107, 17 + (counter * 18)));
			
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(playerInv, var4 + counter * 9 + 9, 8 + var4 * 18, 84 + counter * 18));
			}
			
		}
		
		for (int counter = 0; counter < 9; ++counter)
		{
			this.addSlotToContainer(new Slot(playerInv, counter, 8 + counter * 18, 142));
		}
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return true;
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);
        this.tileEntity.closeChest();
    }
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par1)
	{
		ItemStack var2 = null;
		Slot var3 = (Slot)this.inventorySlots.get(par1);
		
		if (var3 != null && var3.getHasStack())
		{
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();
			
			if (par1 == 2)
			{
				if (!this.mergeItemStack(var4, 3, 39, true))
				{
					return null;
				}
				
				var3.onSlotChange(var4, var2);
			}
			else if (par1 != 1 && par1 != 0)
			{
				if (HMRecipes.getResult(var4, HMEnumProcessing.SINTERER) != null)
				{
					if (!this.mergeItemStack(var4, 1, 3, false))
					{
						return null;
					}
					
				}
				else if (par1 >= 3 && par1 < 30)
				{
					if (!this.mergeItemStack(var4, 30, 39, false))
					{
						return null;
					}
					
				}
				else if (par1 >= 30 && par1 < 39 && !this.mergeItemStack(var4, 3, 30, false))
				{
					return null;
				}
				
			}
			else if (!this.mergeItemStack(var4, 3, 39, false))
			{
				return null;
			}
			
			if (var4.stackSize == 0)
			{
				var3.putStack((ItemStack)null);
				
			}
			else
			{
				var3.onSlotChanged();
				
			}
			
			if (var4.stackSize == var2.stackSize)
			{
				return null;
			}
			
			var3.onPickupFromSlot(player, var4);
			
		}
		
		return var2;
	}
	
}
