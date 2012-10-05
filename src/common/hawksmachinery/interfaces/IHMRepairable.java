
package hawksmachinery.interfaces;

import java.util.Random;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.World;

/**
 * 
 * Implement this if you would like your machine to require maintenance.
 * 
 * @author Elusivehawk
 */
public interface IHMRepairable
{
	int machineHealth = 0;
	IHMSapper sapper = null;
	
	public boolean attemptToUnSap(EntityPlayer player);
	
	public boolean randomlyDamageSelf();
	
	public boolean attemptRepair(World world, int x, int y, int z, int repairAmount);
	
	public int getMaxHP();
	
	public boolean isBeingSapped();
	
}
