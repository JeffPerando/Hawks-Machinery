
package hawksmachinery;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockMetalStorage extends ItemBlock
{
	public HawksMachinery BASEMOD;
	
	public HawkItemBlockMetalStorage(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public String getItemDisplayName(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "Titanium Block";
			case 1: return "Aluminum Block";
			case 2: return "Silver Block";
			case 3: return "Block";
			default: return "Buggy coding!";
		}
	}
	
	@Override
	public int getBlockID()
	{
		return BASEMOD.blockMetalStorage.blockID;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 3: return true;
			default: return false;
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 3: return EnumRarity.epic;
			default: return EnumRarity.common;
		}
	}
}
