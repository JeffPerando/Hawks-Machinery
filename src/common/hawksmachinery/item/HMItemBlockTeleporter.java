
package hawksmachinery.item;

import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockTeleporter extends HMItemBlockEndium
{
	public HMItemBlockTeleporter(int id)
	{
		super(id);
		setHasSubtypes(true);
		
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return item.getItemDamage() == 0 ? "endiumTeleSender" : "endiumTeleReceiver";
	}
	
}
