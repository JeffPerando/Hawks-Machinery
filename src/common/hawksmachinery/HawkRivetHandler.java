
package hawksmachinery;

import java.util.Arrays;
import java.util.List;
import net.minecraft.src.ItemStack;
import hawksmachinery.interfaces.IHawkRivet;

/**
 * 
 * Used in order to add the Rivet Gun's reloading recipes.
 * 
 * @author Elusivehawk
 */
public class HawkRivetHandler
{
	private static List<ItemStack> rivets;
	
	public static void registerRivet(IHawkRivet rivet, int meta)
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
