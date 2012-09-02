
package hawksmachinery;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemRefinedDust extends Item
{
	public static HawksMachinery BASEMOD;
	
	public static String[] en_USNames = {"Diamond Dust", "Ender Dust", "Glass Dust", 
										"Iron Dust", "Gold Dust", 
										"Copper Dust", "Tin Dust", 
										"Titanium Dust", "Aluminum Dust", 
										"Silver Dust", "Emerald Dust"};
	
	public HawkItemRefinedDust(int id)
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
			case 0: return 42;
			case 1: return 43;
			case 2: return 44;
			case 3: return 25;
			case 4: return 26;
			case 5: return 28;
			case 6: return 27;
			case 7: return 20;
			case 8: return 24;
			case 9: return 21;
			case 10: return 58;
			default: return 0;
		}
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter  = 0; counter <= 10; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	public String getTextureFile()
	{
		return BASEMOD.ITEM_TEXTURE_FILE;
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityLiving entity)
	{
		int effect;
		
		switch (item.getItemDamage())
		{
			case 0: effect = 12; break;
			case 1: effect = 9; break;
			case 2: effect = 3; break;
			case 7: effect = 8; break;
			case 8: effect = 6; break;
			default: effect = 4; break;
		}
		
		((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.blindness.getId(), effect, 25));
		((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.poison.getId(), effect, 25));
		
		--item.stackSize;
		
		return true;
	}
}
