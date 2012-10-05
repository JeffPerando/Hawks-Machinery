
package hawksmachinery.interfaces;

import java.util.Arrays;
import java.util.List;
import net.minecraft.src.ItemStack;

/**
 * 
 * Used in order to add the Rivet Gun's reloading recipes automatically.
 * 
 * @author Elusivehawk
 */
public class HMRivetHandler
{
	private static List<ItemStack> rivets;
	
	public static void registerRivet(IHMRivet rivet, int meta)
	{
		if (rivet.isValidRivet(new ItemStack(rivet.getRivet(), 1, meta)))
		{
			rivets.add(new ItemStack(rivet.getRivet(), 1, meta));
			
		}
		
	}
	
	public static List<ItemStack> getRivetsList()
	{
		return rivets;
	}
	
}
