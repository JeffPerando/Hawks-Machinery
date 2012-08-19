
package hawksmachinery.padAPI;

import net.minecraft.src.ItemStack;

/**
 * 
 * Implement this if you want to manipulate miscellaneous Pad aspects.
 * 
 * @author Elusivehawk
 */
public interface IHawkPadMisc
{
	
	/**
	 * 
	 * Used in order to determine if a Pad can drop a certain item once destroyed.
	 * 
	 * @param padItem The item in the pad.
	 * @return True if the pad can drop padItem on beng destroyed, false otherwise.
	 */
	public boolean canPadItemBeDroppedOnDestroyed(ItemStack padItem);
	
}
