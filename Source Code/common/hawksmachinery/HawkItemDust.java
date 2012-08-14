
package hawksmachinery;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.*;

/**
 * @author Elusivehawk
 *
 */
public class HawkItemDust extends Item
{
	public HawkItemDust(int id)
	{
	    super(id);
	    this.setHasSubtypes(true);
	    this.setMaxDamage(0);
	    ModLoader.addName(this, "dust");
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		switch (dmg)
		{
			case 0: return 1;
			case 1: return 10;
			case 2: return 42;
			case 3: return 11;
			case 4: return 12;
			case 5: return 18;
			case 6: return 44;
			case 7: return 43;
			case 8: return 2;
			case 9: return 26;
			case 10: return 28;
			case 11: return 27;
			case 12: return 3;
			case 13: return 19;
			default: return 0;
		}
	}
	
	@Override
    public String getItemDisplayName(ItemStack item)
    {
    	switch (MathHelper.clamp_int(item.getItemDamage(), 0, 13))
    	{
    		case 0: return "Coal Dust";
    		case 1: return "Diamond Dust";
    		case 2: return "Unrefined Gold Dust";
    		case 3: return "Ender Dust";
    		case 4: return "Glass Dust";
    		case 5: return "Unrefined Iron Dust";
    		case 6: return "Unrefined Copper Dust";
    		case 7: return "Unrefined Tin Dust";
    		case 8: return "Iron Dust";
    		case 9: return "Gold Dust";
    		case 10: return "Copper Dust";
    		case 11: return "Tin Dust";
    		case 12: return "Obsidian Dust";
    		case 13: return "Emerald Dust";
    		default: return "Look Jay, 0% body fat!";
		}
    }
    
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter  = 0; counter <= 13; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
    
    public String getTextureFile()
    {
		return HawkManager.ITEM_TEXTURE_FILE;
    }

}
