
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
public class HMItemBlockMetalStorage extends ItemBlock
{
	public HMItemBlockMetalStorage(int id)
	{
		super(id);
		setHasSubtypes(true);
		
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "HMmetalblock" + item.getItemDamage();
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
