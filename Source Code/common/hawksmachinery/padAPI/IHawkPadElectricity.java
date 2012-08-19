
package hawksmachinery.padAPI;

import net.minecraft.src.ItemStack;

/**
 * 
 * Implement this if you want to manipulate how a Pad handles electricity.
 * 
 * @author Elusivehawk
 */
public interface IHawkPadElectricity
{
	
	/**
	 * 
	 * Used in order to determine how much electricity a Pad uses per tick with a specific item in it.
	 * 
	 * @param padItem The item in the Pad.
	 * @param electricityStored The electricity stored in the Pad.
	 * @param isBeingRedstoned Whether or not the Pad is being powered by redstone.
	 * @return How much electricity is required per tick.
	 */
	public int getRequiredElectricityForPad(ItemStack padItem, int electricityStored, boolean isBeingRedstoned);
	
	/**
	 * 
	 * Used in order to determine if a Pad can conduct electricity to nearby Pads.
	 * 
	 * @param padItem The item in the Pad.
	 * @param electricityStored The electricity stored in the Pad.
	 * @param isBeingRedstoned Whether or not the Pad is being powered by redstone.
	 * @return True if the pad can conduct electricity, false otherwise.
	 */
	public boolean canConductElectricity(ItemStack padItem, int electricityStored, boolean isBeingRedstoned);
	
}
