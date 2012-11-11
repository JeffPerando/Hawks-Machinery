
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
	public HMItemRefinedDust(int id)
	{
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabs.tabMaterials);
		//this.en_USNames = new String[]{"Diamond", "Ender", "Glass", 
		//		"Iron", "Gold", "Copper",
		//		"Tin", "Emerald", "Star",
		//		"Endium"};
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 16 + dmg;
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
		return null; //en_USNames[item.getItemDamage()].toLowerCase() + "Dust";
	}
	
}
