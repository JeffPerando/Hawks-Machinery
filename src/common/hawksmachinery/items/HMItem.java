
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
	public static HMItem dustRaw;
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Emerald, 8 - Nether Star, 9 - Endium.
	 */
	public static HMItem dustRefined;
	/**
	 * Parts! 0 - Electric Pistons, 1 - Laser, 2 - Being Redone, 3 - Light Bulb, 4 - Heating Coil, 5 - Electric Magnet, 6 - Engine.
	 */
	public static HMItem parts;
	public static HMItem blueprints;
	public static HMItem plating;
	public static HMItem rivets;
	public static Item rivetGun;
	public static HMItem ingots;
	public static Item fishFood;
	
	public static HawksMachinery BASEMOD;
	private int maxDmg;
	
	public HMItem(int id)
	{
		super(id);
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		
	}
	
	public HMItem registerMaxDamage(int dmg)
	{
		this.maxDmg = dmg;
		return this;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		if (this.hasSubtypes)
		{
			for (int counter = 0; this.getItemNameIS(new ItemStack(this, 1, counter)) != null && counter <= this.maxDmg; ++counter)
			{
				itemList.add(new ItemStack(this, 1, counter));
				
			}
			
		}
		else
		{
			itemList.add(new ItemStack(this));
			
		}
		
	}
	
}
