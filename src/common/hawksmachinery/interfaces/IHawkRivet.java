
package hawksmachinery.interfaces;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * 
 * Implement this if your Item acts as a rivet.
 * 
 * @author Elusivehawk
 */
public interface IHawkRivet
{
	public boolean isValidRivet(ItemStack rivet);
	
	public Item getRivet();
	
	public int getRepairAmount(ItemStack rivet);
	
}
