
package net.minecraft.src.hawksmachinery;

import java.util.ArrayList;
import net.minecraft.src.*;
import net.minecraft.src.forge.ITextureProvider;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemRefinedDust extends Item implements ITextureProvider
{
	public HawkItemRefinedDust(int id)
	{
	    super(id);
	    this.setHasSubtypes(true);
	    this.setMaxDamage(0);
	    ModLoader.addName(this, "Refined Dust");
	}
	
	@Override
	public void addCreativeItems(ArrayList itemList)
	{
		for (int counter = 0; counter <= 7; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	public String getTextureFile()
	{
		return HawkManager.ITEM_TEXTURE_FILE;
    }
    
	@Override
	public boolean onLeftClickEntity(ItemStack item, EntityPlayer player, Entity entity)
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
		
		((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.blindness.getId(), effect, 1));
		((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.poison.getId(), effect, 1));
		
		if (!player.capabilities.isCreativeMode)
		{
			--item.stackSize;
		}
		
		return true;
	}
}
