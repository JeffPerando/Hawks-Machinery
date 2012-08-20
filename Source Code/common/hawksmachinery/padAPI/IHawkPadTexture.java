
package hawksmachinery.padAPI;

import net.minecraft.src.ItemStack;

/**
 * 
 * Implement this if you want to manipulate Pad textures.
 * 
 * @author Elusivehawk
 */
public interface IHawkPadTexture
{
	
	/**
	 * 
	 * Used in order to determine what texture file a Pad uses.
	 * 
	 * @param padItem The item in the Pad.
	 * @param isBeingRedstoned Whether or not the Pad is being powered by redstone.
	 * @param electricityStored The electricity stored in the Pad.
	 * @return A String containing a block texture file to use.
	 */
	public String getPadTextureFile(ItemStack padItem, boolean isBeingRedstoned, float electricityStored);
	
	/**
	 * 
	 * Used in order to determine what swatch on the current texture file the Pad will use.
	 * 
	 * @param padItem The item in the Pad.
	 * @param isBeingRedstoned Whether or not the Pad is being powered by redstone.
	 * @param electricityStored The electricity stored in the Pad.
	 * @return What swatch the Pad will use on the current texture file.
	 */
	public int getPadTextureLocation(ItemStack padItem, boolean isBeingRedstoned, float electricityStored);
	
	/**
	 * 
	 * Used in order to determine what color the Pad is.
	 * 
	 * @param padItem The item in the Pad.
	 * @param electricityStored The electricity stored in the Pad.
	 * @return The same number you would if you were using vMC's getBlockColor().
	 */
	public int getPadColor(ItemStack padItem, float electricityStored);
	
}
