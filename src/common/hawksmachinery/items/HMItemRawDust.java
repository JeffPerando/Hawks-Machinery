
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
public class HMItemRawDust extends HMItem
{
	public HMItemRawDust(int id)
	{
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabs.tabMaterials);
		//this.en_USNames = new String[]{"Coal", 
		//		"Iron", "Gold", 
		//		"Copper", "Tin", 
		//		"Obsidian", "Endium"};
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 1 + dmg;
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
		return null; //en_USNames[item.getItemDamage()] + "DustUnref";
	}
	
}
