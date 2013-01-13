
package hawksmachinery.core.common.api.logo;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;

/**
 * 
 * Implement this if you would like your item to function as a disk.
 * 
 * @author Elusivehawk
 */
public interface IHMLogoDisk
{
	/**
	 * 
	 * Tells the caller what kind of disk this is.
	 * 
	 * @param item The disk.
	 * @return What type of disk this is.
	 */
	public HMEnumDiskType getDiskType(ItemStack item);
	
	/**
	 * 
	 * Only called when {@link getDiskType()} returns {@link HMEnumDiskType.PROTOCOL}.
	 * 
	 * @param item The disk.
	 * @return The words this disk enables access to.
	 */
	public ArrayList<IHMLogoWord> getWords(ItemStack item);
	
}
