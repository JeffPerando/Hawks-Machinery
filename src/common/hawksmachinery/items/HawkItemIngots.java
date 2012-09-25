
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemIngots extends HawkItem
{
	public static String[] en_USNames = {"Titanium", "Aluminum", "Silver", "Endium"};
	public static String[] en_PTNames = {"Better 'n Iron", "Cheap Bullion", "Silver Bullion", "Metal 'o the Tallone"};
	
	public HawkItemIngots(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		
		OreDictionary.registerOre("ingotTitanium", new ItemStack(this, 1, 0));
		OreDictionary.registerOre("ingotAluminum", new ItemStack(this, 1, 1));
		OreDictionary.registerOre("ingotSilver", new ItemStack(this, 1, 2));
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		switch (dmg)
		{
			case 0: return 36;
			case 1: return 40;
			case 2: return 37;
			case 3: return 35;
			default: return 0;
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		if (item.getItemDamage() == 3)
		{
			return EnumRarity.uncommon;
		}
		
		return EnumRarity.common;
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
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter  = 0; counter <= 3; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return en_USNames[item.getItemDamage()] + "Ingot";
	}
	
	public void addCreativeTab()
	{
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
}
