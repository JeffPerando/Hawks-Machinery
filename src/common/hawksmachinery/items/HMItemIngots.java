
package hawksmachinery.items;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemIngots extends HMItem
{
	public static String[] en_USNames = {"Endium"};
	
	public HMItemIngots(int id)
	{
		super(id);
		setCreativeTab(CreativeTabs.tabMaterials);
		setRarity(EnumRarity.rare, 0);
		setItemName("endium", 0);
		setIconIndex(35, 0);
		setEffect(0);
		
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return this.itemNames[item.getItemDamage()] + "Ingot";
	}
	
}
