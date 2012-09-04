
package hawksmachinery;

import com.google.common.collect.ObjectArrays;
import net.minecraft.src.Block;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.ItemSpade;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemTool;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemEndiumTool extends ItemTool
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemEndiumTool(int id, int dmg, Block[] blockArray)
	{
		super(id, dmg, BASEMOD.endiumTool, blockArray);
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
