
package hawksmachinery.item;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.ItemStack;

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
