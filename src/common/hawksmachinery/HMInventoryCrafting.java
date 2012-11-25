
package hawksmachinery;

import hawksmachinery.tileentity.HMTileEntityStarForge;
import net.minecraft.src.Container;
import net.minecraft.src.InventoryCrafting;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMInventoryCrafting extends InventoryCrafting
{
	public final String name;
	public final int inventoryHeight;
	public final HMTileEntityStarForge tileEntity;
	
	public HMInventoryCrafting(String name, Container container, int width, int height, HMTileEntityStarForge tileEntity)
	{
		super(container, width, height);
		this.name = name;
		this.inventoryHeight = height;
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	public String getInvName()
	{
		return this.name;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		return this.tileEntity.decrStackSize(par1, par2);
	}
	
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.tileEntity.setInventorySlotContents(par1, par2ItemStack);
		
	}
	
	@Override
    public ItemStack getStackInSlot(int par1)
    {
    	return this.tileEntity.getStackInSlot(par1);
    }
	
}
