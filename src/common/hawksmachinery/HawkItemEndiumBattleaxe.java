
package hawksmachinery;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemEndiumBattleaxe extends ItemAxe
{
	public static HawksMachinery BASEMOD;
	
	public HawkItemEndiumBattleaxe(int id)
	{
		super(id, BASEMOD.endiumTool);
	}
	
	@Override
	public String getTextureFile()
	{
		return BASEMOD.ITEM_TEXTURE_FILE;
	}
	
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
	public boolean hitEntity(ItemStack item, EntityLiving mob, EntityLiving player)
	{
		item.damageItem(1, player);
		return true;
	}
}
