
package hawksmachinery;

import net.minecraft.src.CreativeTabs;
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
	
	/**
	 * English names.
	 */
	public static String[] en_USNames = {"Titanium Block", "Aluminum Block", "Silver Block", "Block"};
	
	public HawkItemBlockMetalStorage(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		if (item.getItemDamage() == 3)
		{
			return true;
		}
		
		return false;
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

	@Override
	public String getItemNameIS(ItemStack item)
	{
		return (new StringBuilder()).append(super.getItemName()).append(".").append(en_USNames[item.getItemDamage()]).toString();
	}
	
}
