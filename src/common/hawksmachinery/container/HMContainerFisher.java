
package hawksmachinery.container;

import universalelectricity.prefab.SlotElectricItem;
import hawksmachinery.tileentity.HMTileEntityFisher;
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
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);
        this.tileEntity.closeChest();
    }
	
}
