
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
	
}
