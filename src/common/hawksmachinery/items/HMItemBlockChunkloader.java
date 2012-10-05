
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
public class HMItemBlockChunkloader extends ItemBlock
{
	public HMItemBlockChunkloader(int id)
	{
		super(id);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return EnumRarity.rare;
	}
	
}
