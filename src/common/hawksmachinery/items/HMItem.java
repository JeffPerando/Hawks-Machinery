
package hawksmachinery.items;

import java.util.List;
import hawksmachinery.HawksMachinery;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * 
 * My personal preferences. Extend this instead of {@link Item} and you'll save yourself some trouble.
 * 
 * @author Elusivehawk
 */
public class HMItem extends Item
{
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Obsidian, 6 - Endium.
	 */
	public static Item dustRaw;
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Emerald, 8 - Nether Star, 9 - Endium.
	 */
	public static Item dustRefined;
	public static Item parts;
	public static Item blueprints;
	public static Item endiumPlate;
	public static Item rivets;
	public static Item rivetGun;
	public static Item ingots;
	
	public static HawksMachinery BASEMOD;
	protected EnumRarity[] rarity = new EnumRarity[16];
	protected boolean[] hasEffect = new boolean[16];
	protected String[] itemNames = new String[16];
	protected int[] iconIndexes = new int[16];
	
	public HMItem(int id)
	{
		super(id);
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		setHasSubtypes(this.itemNames[1] != null);
		
		for (int counter = 0; counter < 16; ++counter)
		{
			if (this.rarity[counter] == null)
			{
				this.rarity[counter] = EnumRarity.common;
			}
			
		}
		
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return this.rarity[item.getItemDamage()];
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return this.hasEffect[item.getItemDamage()];
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return this.itemNames[item.getItemDamage()];
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return this.iconIndexes[dmg];
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter = 0; this.itemNames[counter] != null; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
//--------------------------------------------SETTERS START-------------------------------------------
	
	public HMItem setRarity(EnumRarity rarity, int meta)
	{
		this.rarity[meta] = rarity;
		return this;
	}
	
	public HMItem setEffect(int meta)
	{
		this.hasEffect[meta] = true;
		return this;
	}
	
	public HMItem setItemName(String name, int meta)
	{
		this.itemNames[meta] = name;
		return this;
	}
	
	public HMItem setIconIndex(int index, int meta)
	{
		this.iconIndexes[meta] = index;
		return this;
	}
	
}
