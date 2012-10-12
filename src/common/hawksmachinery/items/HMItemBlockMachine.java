
package hawksmachinery.items;

import java.util.List;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockMachine extends ItemBlock
{
	public HMItemBlockMachine(int id)
	{
		super(id);
	}
	
	@Override
	public void addInformation(ItemStack item, List list)
	{
		list.add("Machine Health: " + item.getItemDamage());
	}
	
	@Override
	public int getItemStackLimit()
	{
		return 1;
	}
	
}
