
package hawksmachinery.padAPI;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * 
 * Implement this if you want to manipulate Pad block interaction.
 * 
 * @author Elusivehawk
 */
public interface IHawkPadInteraction
{
	
	/**
	 * 
	 * Called after isItemValidForPad() in IHawkPadEffect.
	 * 
	 * @param padItem The item in the Pad.
	 * @param world A world instance.
	 * @param x The x position of the Pad.
	 * @param y The y position of the Pad.
	 * @param z The z position of the Pad.
	 * @param player The Player right-clicking.
	 * @return True if something happens, false otherwise
	 */
	public boolean onPadActivated(ItemStack padItem, World world, int x, int y, int z, EntityPlayer player);
	
	/**
	 * 
	 * Called after isItemValidForPad() in IHawkPadEffect and onPadActivated.
	 * 
	 * @param padItem The item in the Pad.
	 * @param world A world instance.
	 * @param x The x position of the Pad.
	 * @param y The y position of the Pad.
	 * @param z The z position of the Pad.
	 * @param player The Player right-clicking.
	 * @return True if something happens, false otherwise
	 */
	public boolean onPadWrenched(ItemStack padItem, World world, int x, int y, int z, EntityPlayer player);
	
	/**
	 * 
	 * Used in order to determine if a Pad can drop the padItem in it.
	 * 
	 * @param padItem The item in the Pad.
	 * @param isBeingRedstoned Whether or not the Pad is being powered by redstone.
	 * @return True if the pad can drop padItem on beng destroyed, false otherwise.
	 */
	public boolean canPadItemBeDroppedOnDestroyed(ItemStack padItem, boolean isBeingRedstoned);
	
}
