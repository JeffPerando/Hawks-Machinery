
package hawksmachinery.core.common.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockEndium extends ItemBlock
{
	public HMItemBlockEndium(int id)
	{
		super(id);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return EnumRarity.rare;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
}
