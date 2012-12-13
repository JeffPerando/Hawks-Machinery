
package hawksmachinery.api;

import net.minecraft.src.Enchantment;
import net.minecraft.src.ItemStack;
import net.minecraft.src.PotionEffect;

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
