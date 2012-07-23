
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.*;
import net.minecraft.server.*;
import net.minecraft.src.forge.*;
import cpw.mods.fml.server.FMLServerHandler;
/**
 * @author Elusivehawk
 *
 */
public class SlotProcessorsOutput extends Slot
{

    private EntityPlayer thePlayer;
    private int field_48437_f;

    
    public SlotProcessorsOutput(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        this.thePlayer = par1EntityPlayer;
    }
    

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }

    
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.field_48437_f += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }


    public void onPickupFromSlot(ItemStack par1ItemStack)
    {
        this.func_48416_b(par1ItemStack);
        super.onPickupFromSlot(par1ItemStack);
    }

    
    protected void func_48435_a(ItemStack par1ItemStack, int par2)
    {
        this.field_48437_f += par2;
        this.func_48416_b(par1ItemStack);
    }
}
