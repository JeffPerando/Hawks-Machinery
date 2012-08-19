
package hawksmachinery.padAPI;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * 
 * Implement this if you want to be able to control what a Pad does every tick.
 * 
 * @author Elusivehawk
 */
public interface IHawkPadUpdate
{
	/**
	 * 
	 * Called every tick.
	 * 
	 * @param padItem The item in the Pad.
	 * @param electricityStored The electricity stored in the Pad.
	 * @param isBeingRedstoned Whether or not the Pad is being powered by redstone.
	 * @param x The x position of the Pad.
	 * @param y The y position of the Pad.
	 * @param z The z position of the Pad.
	 * @param world The World.
	 */
	public void onPadUpdate(ItemStack padItem, int electricityStored, boolean isBeingRedstoned, int x, int y, int z, World world);
}
