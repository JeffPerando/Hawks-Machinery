
package hawksmachinery;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemEndiumKatana extends ItemSword
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemEndiumKatana(int id)
	{
		super(id, BASEMOD.endiumTool);
	}
	
	@Override
	public String getTextureFile()
	{
		return BASEMOD.ITEM_TEXTURE_FILE;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		if (item.isItemEnchanted())
		{
			return EnumRarity.rare;
		}
		
		return EnumRarity.uncommon;
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return true;
	}
	
	@Override
	public int getDamageVsEntity(Entity entity)
	{
		if (entity instanceof EntityEnderman)
		{
			return super.getDamageVsEntity(entity) / 2;
		}
		
		return super.getDamageVsEntity(entity);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack item)
	{
		return EnumAction.none;
	}
	
}
