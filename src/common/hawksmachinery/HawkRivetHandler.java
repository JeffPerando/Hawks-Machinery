
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
	private static List rivets;
	
	public static void registerRivet(IHawkRivet rivet, int meta)
	{
		if (rivet.isValidRivet(new ItemStack(rivet.getRivet(), 1, meta)))
		{
			rivets.add(Arrays.asList(rivet, meta));
			
		}
		
	}
	
	public static List getRivetsList()
	{
		return rivets;
	}
	
}
