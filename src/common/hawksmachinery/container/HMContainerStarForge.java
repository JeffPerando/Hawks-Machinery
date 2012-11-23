
package hawksmachinery.container;

import hawksmachinery.HMInventoryCrafting;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryPlayer;
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
	public HMInventoryCrafting matrix = new HMInventoryCrafting("Star Forge", this, 3, 3);
	//public HMTileEntityStarForge tileEntity;
	
	public HMContainerStarForge(InventoryPlayer playerInv)
	{
		//this.tileEntity = tileEntity;
		int slotNumber = 0;
		
		for (int width = 0; width < this.matrix.getInvWidth(); ++width)
		{
			for (int height = 0; height < this.matrix.inventoryHeight; ++height)
			{
				this.addSlotToContainer(new Slot(this.matrix, slotNumber, ((height + 1) * 18) + 14, ((width + 1) * 18) + 15));
				++slotNumber;
				
			}
			
		}
		
		this.addSlotToContainer(new SlotFurnace(playerInv.player, this.matrix, 9, 32, 107));

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
	public void onCraftMatrixChanged(IInventory inventory)
	{
		//this.tileEntity.setForgeResult(HMRecipes.getForgeResult(this.matrix, this.tileEntity.worldObj));
		
	}
	
}
