
package hawksmachinery.itemblocks;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockOre extends ItemBlock
{
	public static HawksMachinery BASEMOD;
	
	/**
	 * English names.
	 */
	public static String[] en_USNames = {"Titanium", "Bauxium", "Silver", "Endium"};
	public static String[] en_PTNames = {"Better 'n Iron", "Cheap Bullion", "Silver Bullion", "Metal 'o the Tallone"};
	
	public HawkItemBlockOre(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return this.en_USNames[item.getItemDamage()];
	}
	
}
