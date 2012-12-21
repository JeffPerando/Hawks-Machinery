
package hawksmachinery.common.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public interface IHMNanite
{
	public PotionEffect getPotionEffect(ItemStack item);
	
	public Enchantment getEnchantment(ItemStack item);
	
}
