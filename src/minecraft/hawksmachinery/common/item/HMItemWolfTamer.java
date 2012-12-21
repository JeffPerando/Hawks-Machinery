
package hawksmachinery.common.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemWolfTamer extends HMItem
{
	public HMItemWolfTamer(int id)
	{
		super(id);
		
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityLiving entity)
	{
		if (!entity.worldObj.isRemote && entity instanceof EntityWolf)
		{
			if (((EntityWolf)entity).isAngry())
			{
				((EntityWolf)entity).setAngry(false);
				((EntityWolf)entity).setAttackTarget(null);
				--item.stackSize;
				if (item.stackSize == 0) item = null;
				
			}
			
		}
		
		return true;
	}
	
}
