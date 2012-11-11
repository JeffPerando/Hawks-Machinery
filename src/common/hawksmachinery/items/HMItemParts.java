
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
import java.util.List;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * 
 * Parts!
 * 
 * @author Elusivehawk
 */
public class HMItemParts extends HMItem
{
	public HMItemParts(int id)
	{
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabs.tabMaterials);
		//this.en_USNames = new String[]{"Electric Piston", "Laser", "I have NO idea!", "Light Bulb", "Heating Coil", "Electric Magnet", "Engine"};
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 64 + dmg;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "electricPiston";
			case 1: return "laser";
			case 2: return "idk";//TODO Add a new part.
			case 3: return "lightBulb";
			case 4: return "heatingCoil";
			case 5: return "electroMagnet";
			case 6: return "engine";
			
		}
		
		return null;
	}
	
}
