
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
	public int getIconFromDamage(int dmg)
	{
		switch (dmg)
		{
			case 0: return 10;
			case 1: return 11;
			case 2: return 12;
			case 3: return 2;
			case 4: return 26;
			case 5: return 28;
			case 6: return 27;
			case 7: return 19;
			default: return 0;
		}
	}
	
	@Override
    public String getItemDisplayName(ItemStack item)
    {
    	switch (MathHelper.clamp_int(item.getItemDamage(), 0, 13))
    	{
    		case 0: return "Diamond Dust";
    		case 1: return "Ender Dust";
    		case 2: return "Glass Dust";
    		case 3: return "Iron Dust";
    		case 4: return "Gold Dust";
    		case 5: return "Copper Dust";
    		case 6: return "Tin Dust";
    		case 7: return "Emerald Dust";
    		default: return "Look Jay, 0% body fat!";
		}
    }
    
    @Override
    public void addCreativeItems(ArrayList itemList)
    {       
        for (int counter = 0; counter <= 13; ++counter)
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
