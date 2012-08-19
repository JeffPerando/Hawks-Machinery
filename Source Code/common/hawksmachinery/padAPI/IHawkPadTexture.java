
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
	 * @param padItem The item in the pad.
	 * @return A String containing a block texture file to use.
	 */
	public String getTextureFile(ItemStack padItem);
	
	/**
	 * 
	 * Used in order to determine what swatch on the current texture file the Pad will use.
	 * 
	 * @param padItem The item in the pad.
	 * @return What swatch the Pad will use on the current texture file.
	 */
	public int getTextureLocation(ItemStack padItem);
	
}
