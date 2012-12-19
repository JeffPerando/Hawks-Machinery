
package hawksmachinery;

import hawksmachinery.api.HMRecipes;
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
		inventoryHeight = height;
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
	public void onInventoryChanged()
	{
		this.tileEntity.onInventoryChanged();
		this.tileEntity.output = HMRecipes.getForgeResult(this, this.tileEntity.worldObj);
		
	}
	
	@Override
    public ItemStack getStackInSlot(int slot)
    {
    	return this.tileEntity.getStackInSlot(slot);
    }
	
	@Override
    public ItemStack decrStackSize(int slot, int quantity)
    {
		this.onInventoryChanged();
    	return this.tileEntity.decrStackSize(slot, quantity);
    }
	
	@Override
    public void setInventorySlotContents(int slot, ItemStack item)
    {
		this.onInventoryChanged();
    	this.tileEntity.setInventorySlotContents(slot, item);
    	
    }
	
}
