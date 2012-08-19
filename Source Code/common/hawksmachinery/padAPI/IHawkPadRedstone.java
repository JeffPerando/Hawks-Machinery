
package hawksmachinery.padAPI;

import net.minecraft.src.ItemStack;

/**
 * 
 * Implement this is you want to be able to manipulate Pad redstoning.
 * 
 * @author Elusivehawk
 */
public interface IHawkPadRedstone
{
	
	/**
	 * 
	 * Called when a Pad receives redstone.
	 * 
	 * @param padItem The item in the Pad.
	 * @param electricityStored The electricity stored in the Pad.
	 */
	public void onRedstoneOn(ItemStack padItem, int electricityStored);
	
	/**
	 * 
	 * Called when a Pad loses redstone.
	 * 
	 * @param padItem The item in the Pad.
	 * @param electricityStored The electricity stored in the Pad.
	 */
	public void onRedstoneOff(ItemStack padItem, int electricityStored);
}
