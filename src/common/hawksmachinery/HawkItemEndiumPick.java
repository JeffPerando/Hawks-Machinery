
package hawksmachinery;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.ItemStack;

/**
 * 
 * Just a wrapper for ItemPickaxe.
 * 
 * @author Elusivehawk
 */
public class HawkItemEndiumPick extends ItemPickaxe
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemEndiumPick(int id, EnumToolMaterial mat)
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
