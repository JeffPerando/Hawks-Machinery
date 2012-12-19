
package hawksmachinery.item;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;

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
		return "item.HMDustUnref" + item.getItemDamage();
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
