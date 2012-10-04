
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemRawDust extends HawkItem
{
	public static String[] en_USNames = {"Coal", 
										"Iron", "Gold", 
										"Copper", "Tin", 
										"Obsidian", "Endium"};
	
	public HawkItemRawDust(int id)
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
			case 0: return 57;
			case 1: return 9;
			case 2: return 10;
			case 3: return 12;
			case 4: return 11;
			case 5: return 59;
			case 6: return 3;
			default: return 0;
			
		}
	}
    
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter  = 0; counter <= 6; ++counter)
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
			case 0: effect = 2; break;
			case 6: effect = 20; break;
			default: effect = 4; break;
		}
		
		entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), effect, 1));
		entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), effect, 1));
		
		--item.stackSize;
		
		return true;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return en_USNames[item.getItemDamage()] + "DustUnref";
	}
	
	public void addCreativeTab()
	{
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
}
