
package hawksmachinery.common;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMEntityRobot extends EntityLiving
{
	public HMEntityRobot(World world)
	{
		super(world);
		
	}
	
	@Override
	public int getMaxHealth()
	{
		return 50;
	}
	
	@Override
    protected boolean canDespawn()
    {
    	return false;
    }
	
}
