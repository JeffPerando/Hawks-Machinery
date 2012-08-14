
package hawksmachinery;

import universalelectricity.basiccomponents.SlotElectricItem;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.TileEntity;

/**
 * @author Elusivehawk
 *
 */
public class HawkContainerGrinder extends Container
{
	private static HawkTileEntityGrinder tileEntity;
	
	public boolean canInteractWith(EntityPlayer var1)
	{
		return true;
	}
	
    public HawkContainerGrinder(InventoryPlayer par1InventoryPlayer, HawkTileEntityGrinder tileEntity)
    {
        this.tileEntity = tileEntity;
        this.addSlotToContainer(new SlotElectricItem(tileEntity, 0, 55, 49));
        this.addSlotToContainer(new Slot(tileEntity, 1, 55, 25));
        this.addSlotToContainer(new SlotProcessorsOutput(par1InventoryPlayer.player, tileEntity, 2, 108, 25));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
    }
    
    public TileEntity getGrinderContainer()
    {
    	return this.tileEntity;
    }
    
    public ItemStack transferStackInSlot(int par1)
    {
		return null;
    }

}
