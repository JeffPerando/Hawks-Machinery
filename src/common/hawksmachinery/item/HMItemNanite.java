
package hawksmachinery.item;

import hawksmachinery.api.IHMNanite;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.ItemStack;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemNanite extends HMItem implements IHMNanite
{
	public HMItemNanite(int id)
	{
		super(id);
		setMaxStackSize(16);
		setHasSubtypes(true);
		
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack item)
	{
		return EnumAction.drink;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack item)
	{
		return 16;
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (this.getPotionEffect(item) != null)
		{
			player.setItemInUse(item, this.getMaxItemUseDuration(item));
			
		}
		
		return item;
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack item, World world, EntityPlayer player)
	{
		if (!player.capabilities.isCreativeMode)
		{
			--item.stackSize;
			
		}
		
		if (!world.isRemote)
		{
			player.addPotionEffect(new PotionEffect(this.getPotionEffect(item)));
			
		}
		
		return item;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return "item.HMNanite" + item.getItemDamage();
	}
	
	public PotionEffect getPotionEffect(ItemStack item)
	{
		return null;
	}
	
	public Enchantment getEnchantment(ItemStack item)
	{
		return null;
	}
	
}
