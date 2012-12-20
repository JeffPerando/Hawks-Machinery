
package hawksmachinery.item;

import hawksmachinery.api.IHMNanite;
import java.util.List;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.StatCollector;
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
		setMaxStackSize(1);
		setHasSubtypes(true);
		
	}
	
	@Override
    public boolean requiresMultipleRenderPasses()
    {
    	return true;
    }
	
	@Override
    public int getIconFromDamageForRenderPass(int dmg, int pass)
    {
    	return pass == 0 ? 73 : 72;
    }
	
	@Override
    public int getColorFromItemStack(ItemStack item, int pass)
    {
    	return pass == 1 ? 16777215 : 8000000;
    }
	
	@Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean advancedDesc)
	{
		PotionEffect potion = this.getPotionEffect(item);
		Enchantment enchant = this.getEnchantment(item);
		if (potion != null)
		{
			String desc = StatCollector.translateToLocal(potion.getEffectName()).trim();
			if (potion.getAmplifier() > 0) desc += " " + StatCollector.translateToLocal("potion.potency." + potion.getAmplifier());
			if (potion.getDuration() > 20) desc += " (" + Potion.getDurationString(potion) + ")";
			list.add(desc);
		}
		if (enchant != null) list.add(StatCollector.translateToLocal(enchant.getName()));
		
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack item)
	{
		return EnumAction.drink;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack item)
	{
		return 20;
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (this.getPotionEffect(item) != null) player.setItemInUse(item, this.getMaxItemUseDuration(item));
		
		return item;
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack item, World world, EntityPlayer player)
	{
		if (!world.isRemote) player.addPotionEffect(new PotionEffect(this.getPotionEffect(item)));
		if (player.capabilities.isCreativeMode)
		{
			--item.stackSize;
		}
		
		return new ItemStack(HMItem.testTube);
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return item.getItemDamage() == 0 ? "item.HMNanitesEmpty" : "item.HMNanites";
	}
	
	public PotionEffect getPotionEffect(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 1: return new PotionEffect(8, 2400, 1);
			case 2: return new PotionEffect(10, 1200);
			case 3: return new PotionEffect(19, 400);
			default: return null;
		}
		
	}
	
	public Enchantment getEnchantment(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 1: return Enchantment.enchantmentsList[2];
			//TODO Finish Regen. Enchantment.
			case 3: return Enchantment.enchantmentsList[7];
			default: return null;
		}
		
	}
	
}
