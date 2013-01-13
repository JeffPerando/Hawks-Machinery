
package hawksmachinery.machine.common.item;

import hawksmachinery.core.common.item.HMItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

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
		return "item.HMDustRefined" + item.getItemDamage();
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return item.getItemDamage() == 9 ? EnumRarity.rare : EnumRarity.common;
		
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() == 9;
	}
	
}
