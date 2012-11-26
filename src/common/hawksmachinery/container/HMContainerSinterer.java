
package hawksmachinery.container;

import hawksmachinery.tileentity.HMTileEntitySinterer;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Slot;

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
			this.addSlotToContainer(new Slot(this.tileEntity, counter, 24, 16 + (counter * 18)));
			this.addSlotToContainer(new Slot(this.tileEntity, counter + 3, 76, 16 + (counter * 18)));
			
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
	
}
