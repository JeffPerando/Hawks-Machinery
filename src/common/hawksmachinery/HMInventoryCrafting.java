
package hawksmachinery;

import net.minecraft.src.Container;
import net.minecraft.src.InventoryCrafting;

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
	
	public HMInventoryCrafting(String name, Container container, int width, int height)
	{
		super(container, width, height);
		this.name = name;
		this.inventoryHeight = height;
		
	}
	
	@Override
	public String getInvName()
	{
		return this.name;
	}
	
}
