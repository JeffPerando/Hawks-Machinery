
package hawksmachinery.common;

import hawksmachinery.common.api.HMRecipes;
import hawksmachinery.common.tileentity.HMTileEntityStarForge;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

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
	
	public HMInventoryCrafting(String name, int width, int height, HMTileEntityStarForge tileEntity)
	{
		super(null, width, height);
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
