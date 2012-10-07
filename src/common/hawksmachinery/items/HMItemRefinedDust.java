
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemRefinedDust extends HMItem
{
	public static String[] en_USNames = {"Diamond", "Ender", "Glass", 
										"Iron", "Gold", "Copper",
										"Tin", "Emerald", "Star",
										"Endium"};
	
	public HMItemRefinedDust(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		switch (dmg)
		{
			case 0: return 42;
			case 1: return 43;
			case 2: return 44;
			case 3: return 25;
			case 4: return 26;
			case 5: return 28;
			case 6: return 27;
			case 7: return 58;
			case 8: return 60;
			case 9: return 19;
			default: return 0;
			
		}
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter = 0; counter <= 9; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityLiving entity)
	{
		int effect;
		
		switch (item.getItemDamage())
		{
			case 0: effect = 12; break;
			case 1: effect = 9; break;
			case 2: effect = 3; break;
			case 8: effect = 15; break;
			case 9: effect = 18; break;
			default: effect = 4; break;
		}
		
		entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), effect, 25));
		entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), effect, 25));
		
		--item.stackSize;
		
		return true;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return en_USNames[item.getItemDamage()].toLowerCase() + "Dust";
	}
	
	public void addCreativeTab()
	{
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
}
