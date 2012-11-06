
package hawksmachinery.items;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

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
