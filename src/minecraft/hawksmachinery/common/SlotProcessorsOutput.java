
package hawksmachinery.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class SlotProcessorsOutput extends Slot
{
    private EntityPlayer thePlayer;
    private int field_48437_b;
    
    public SlotProcessorsOutput(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        this.thePlayer = par1EntityPlayer;
    }
    
    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }
    
    @Override
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.field_48437_b += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }
    
    @Override
    protected void onCrafting(ItemStack item, int i)
    {
    	this.field_48437_b += i;
    	item.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_48437_b);
    }
}
