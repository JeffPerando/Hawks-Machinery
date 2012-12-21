
package hawksmachinery.common.item;

import hawksmachinery.common.HawksMachinery;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import universalelectricity.prefab.UETab;

/**
 * 
 * My personal preferences. Extend this instead of {@link Item} and you'll save yourself some trouble.
 * 
 * @author Elusivehawk
 */
public class HMItem extends Item
{
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Obsidian, 6 - Endium, 7 - Cobalt.
	 */
	public static HMItem dustRaw;
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Emerald, 8 - Nether Star, 9 - Endium, 10 - Cobalt.
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
	public static Item machineMeter;
	public static Item cobaltBone;
	public static Item testTube;
	public static Item nanites;
	
	public static HawksMachinery HM;
	private int maxDmg;
	
	public HMItem(int id)
	{
		super(id);
		setTextureFile(HM.ITEM_TEXTURE_FILE);
		setCreativeTab(UETab.INSTANCE);
		
	}
	
	public HMItem setMaxDmg(int dmg)
	{
		this.maxDmg = dmg;
		return this;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		if (this.hasSubtypes)
		{
			for (int counter = 0; counter <= this.maxDmg; ++counter)
			{
				if (this.getItemNameIS(new ItemStack(this, 1, counter)) != null) itemList.add(new ItemStack(this, 1, counter));
				
			}
			
		}
		else itemList.add(new ItemStack(this));
		
	}
	
}
