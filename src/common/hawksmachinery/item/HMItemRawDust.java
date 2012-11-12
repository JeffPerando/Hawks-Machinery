
package hawksmachinery.item;

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
		return "dustUnref" + item.getItemDamage();
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return item.getItemDamage() == 6 ? EnumRarity.rare : EnumRarity.common;
		
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() == 6;
	}
	
}
