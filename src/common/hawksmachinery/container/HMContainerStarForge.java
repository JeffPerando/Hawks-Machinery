
package hawksmachinery.container;

import hawksmachinery.HMInventoryCrafting;
import hawksmachinery.api.HMRecipes;
import hawksmachinery.tileentity.HMTileEntityStarForge;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.SlotFurnace;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMContainerStarForge extends Container
{
	private HMTileEntityStarForge tileEntity;
	public HMInventoryCrafting matrix;
	
	public HMContainerStarForge(InventoryPlayer playerInv, HMTileEntityStarForge tileEntity)
	{
		this.tileEntity = tileEntity;
		this.matrix = new HMInventoryCrafting("Star Forge", this, 3, 3, tileEntity);
		int slotNumber = 0;
		
		for (int width = 0; width < 3; ++width)
		{
			for (int height = 0; height < 3; ++height)
			{
				this.addSlotToContainer(new Slot(this.matrix, slotNumber, (((width + 1) * 18) - 3), ((height + 1) * 18) - 4));
				++slotNumber;
				
			}
			
		}
		
		this.addSlotToContainer(new SlotFurnace(playerInv.player, this.tileEntity, 9, 107, 32));

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
	public boolean canInteractWith(EntityPlayer player)
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
	
}
