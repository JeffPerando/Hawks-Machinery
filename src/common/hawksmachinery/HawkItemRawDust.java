
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
	public static HawksMachinery BASEMOD;
	
	public static String[] en_USNames = {"Coal", 
										"Iron", "Gold", 
										"Copper", "Tin", 
										"Titanium", "Aluminum", 
										"Silver", "Obsidian"};
	
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
			case 0: return 57;
			case 1: return 9;
			case 2: return 10;
			case 3: return 12;
			case 4: return 11;
			case 5: return 4;
			case 6: return 8;
			case 7: return 5;
			case 8: return 59;
			default: return 0;
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
		return BASEMOD.ITEM_TEXTURE_FILE;
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
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return en_USNames[item.getItemDamage()] + "DustUnref";
	}
	
}
