
package hawksmachinery.items;

import hawksmachinery.blocks.HMBlockOre;
import net.minecraft.src.Block;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlockOre extends ItemBlock
{
	public HMItemBlockOre(int id)
	{
		super(id);
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "oreEndium";
			case 1: return "oreCobalt";
			default: return null;
		}
		
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return item.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.common;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
}
