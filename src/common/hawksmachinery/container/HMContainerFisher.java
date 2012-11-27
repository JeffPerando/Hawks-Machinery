
package hawksmachinery.container;

import universalelectricity.prefab.SlotElectricItem;
import hawksmachinery.tileentity.HMTileEntityFisher;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMContainerFisher extends Container
{
	private HMTileEntityFisher tileEntity;
	
	public HMContainerFisher(InventoryPlayer playerInv, HMTileEntityFisher tileEntity)
	{
		this.tileEntity = tileEntity;
		this.tileEntity.openChest();
		this.addSlotToContainer(new SlotElectricItem(tileEntity, 0, 34, 61));
		
		for (int counter = 1; counter < 10; ++counter)
		{
			this.addSlotToContainer(new Slot(tileEntity, counter, 8 + ((counter - 1) * 18), 14));
			this.addSlotToContainer(new Slot(tileEntity, counter + 9, 8 + ((counter - 1) * 18), 40));
			
		}
		
		for (int counter = 0; counter < 3; ++counter)
		{
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
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot)this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
			if (par2 == 0)
			{
				if (!this.mergeItemStack(var5, 10, 46, true))
				{
					return null;
				}
				
				var4.onSlotChange(var5, var3);
			}
			else if (par2 >= 10 && par2 < 37)
			{
				if (!this.mergeItemStack(var5, 37, 46, false))
				{
					return null;
				}
			}
			else if (par2 >= 37 && par2 < 46)
			{
				if (!this.mergeItemStack(var5, 10, 37, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(var5, 10, 46, false))
			{
				return null;
			}
			
			if (var5.stackSize == 0)
			{
				var4.putStack((ItemStack)null);
				
			}
			else
			{
				var4.onSlotChanged();
				
			}
			
			if (var5.stackSize == var3.stackSize)
			{
				return null;
			}
			
			var4.onPickupFromSlot(par1EntityPlayer, var5);
			
		}
		
		return var3;
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);
        this.tileEntity.closeChest();
    }
	
}
