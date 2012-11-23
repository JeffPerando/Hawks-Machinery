
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
	
	public int getInvWidth()
	{
		try
		{
			return (Integer)this.getClass().getField("inventoryWidth").get(Integer.class);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
