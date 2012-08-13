
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
public class HawkItemRawDust extends Item implements ITextureProvider
{
	public HawkItemRawDust(int id)
	{
	    super(id);
	    this.setHasSubtypes(true);
	    this.setMaxDamage(0);
	    ModLoader.addName(this, "Raw Dust");
	}
	
	@Override
	public void addCreativeItems(ArrayList itemList)
	{       
		for (int counter = 0; counter <= 8; ++counter)
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
			case 0: effect = 2; break;
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
