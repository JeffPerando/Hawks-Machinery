
package hawksmachinery.tools.common.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import hawksmachinery.core.common.item.HMItemElectric;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemElectricSword extends HMItemElectric
{
	public HMItemElectricSword(int id)
	{
		super(id, 25000, 120);
		
	}
	
	@Override
	public boolean hitEntity(ItemStack item, EntityLiving entity, EntityLiving player)
	{
		if (item.stackTagCompound.getBoolean("isOn"))
		{
			this.onUse(100, item);
			
		}
		
		return true;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		super.onCreated(item, world, player);
		item.stackTagCompound.setBoolean("isOn", false);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		item.stackTagCompound.setBoolean("isOn", !item.stackTagCompound.getBoolean("isOn"));
		return item;
	}
	
}
