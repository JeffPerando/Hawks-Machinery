
package hawksmachinery.item;

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
		setHasSubtypes(true);
		
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "HMOre" + item.getItemDamage();
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
