
package hawksmachinery;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemStack;

/**
 * 
 * Just a wrapper for ItemAxe.
 * 
 * @author Elusivehawk
 */
public class HawkItemEndiumAxe extends ItemAxe
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemEndiumAxe(int id, EnumToolMaterial mat)
	{
		super(id, mat);
	}

	@Override
	public String getTextureFile()
	{
		return BASEMOD.ITEM_TEXTURE_FILE;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		if (item.isItemEnchanted())
		{
			return EnumRarity.rare;
		}
		
		return EnumRarity.uncommon;
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return true;
	}
	
}
