
package hawksmachinery.core.common.item;

import hawksmachinery.core.common.HMCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import universalelectricity.prefab.ItemElectric;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemElectric extends ItemElectric
{
	public final double maxJoules, maxVolts;
	
	public HMItemElectric(int id, double joules, double volts)
	{
		super(id);
		setTextureFile(HMCore.ITEM_TEXTURE_FILE);
		setCreativeTab(HMCore.instance().tab);
		maxJoules = joules;
		maxVolts = volts;
		
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		super.onCreated(item, world, player);
		item.stackTagCompound = new NBTTagCompound();
		
	}
	
	@Override
	public double getMaxJoules(Object... data)
	{
		return this.maxJoules;
	}
	
	@Override
	public double getVoltage(Object... data)
	{
		return this.maxVolts;
	}
	
}
