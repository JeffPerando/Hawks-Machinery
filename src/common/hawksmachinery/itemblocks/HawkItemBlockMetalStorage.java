
package hawksmachinery.itemblocks;

import hawksmachinery.HawksMachinery;
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
	public static HawksMachinery BASEMOD;
	
	/**
	 * English names.
	 */
	public static String[] en_USNames = {"Titanium", "Aluminum", "Silver", "Endium"};
	public static String[] en_PTNames = {"Better 'n Iron", "Cheap Bullion", "Silver Bullion", "Metal 'o the Tallone"};
	
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
	public EnumRarity getRarity(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 3: return EnumRarity.uncommon;
			default: return EnumRarity.common;
		}
	}

	@Override
	public String getItemNameIS(ItemStack item)
	{
		return (new StringBuilder()).append(super.getItemName()).append(".").append(en_USNames[item.getItemDamage()]).toString();
	}
	
}
