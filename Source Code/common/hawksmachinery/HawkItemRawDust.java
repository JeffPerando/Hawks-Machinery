
package hawksmachinery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemRawDust extends Item
{
	public HawkItemRawDust(int id)
	{
		super(id);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		setTabToDisplayOn(CreativeTabs.tabMaterials);
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		switch (dmg)
		{
			case 0: return 1;
			case 1: return 41;
			case 2: return 42;
			case 3: return 44;
			case 4: return 43;
			case 5: return 20;
			case 6: return 22;
			case 7: return 23;
			case 8: return 3;
			default: return 0;
		}
	}
	
	@Override
	public String getItemDisplayName(ItemStack item)
	{
    	switch (item.getItemDamage())
    	{
			case 0: return "Coal Dust";
			case 1: return "Unrefined Iron Dust";
			case 2: return "Unrefined Gold Dust";
			case 3: return "Unrefined Copper Dust";
			case 4: return "Unrefined Tin Dust";
			case 5: return "Unrefined Titanium Dust";
			case 6: return "Unrefined Aluminum Dust";
			case 7: return "Unrefined Silver Dust";
			case 8: return "Obsidian Dust";
			default: return "Look Jay, 0% body fat!";
		}
    }
    
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter  = 0; counter <= 8; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
    
    @Override
	public String getTextureFile()
	{
		return HawkManager.ITEM_TEXTURE_FILE;
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityLiving entity)
	{
		int effect;
		
		switch (item.getItemDamage())
		{
			case 0: effect = 2; break;
			default: effect = 4; break;
		}
		
		((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.blindness.getId(), effect, 1));
		((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.poison.getId(), effect, 1));
		
		--item.stackSize;
		
		return true;
	}
}
