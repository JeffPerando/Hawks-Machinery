
package hawksmachinery;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemHoe;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemEndiumHoe extends ItemHoe
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemEndiumHoe(int id)
	{
		super(id, BASEMOD.endiumTool);
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
