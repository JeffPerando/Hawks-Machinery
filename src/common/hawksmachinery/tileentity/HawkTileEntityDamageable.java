
package hawksmachinery.tileentity;

import java.util.Random;
import net.minecraft.src.World;

/**
 * 
 * Extend this if you want your machine to require maintenance.
 * 
 * @author Elusivehawk
 */
public abstract class HawkTileEntityDamageable extends HawkTileEntityMachine
{
	private int machineHealth;
	private int maxMachineHP;
	
	public boolean randomlyDamageSelf()
	{
		int randomNumber = new Random().nextInt(10);
		
		if (randomNumber == 6)
		{
			--this.machineHealth;
			return true;
		}
		
		return false;
	}
	
	public boolean attemptRepair(World world, int x, int y, int z, int repairAmount)
	{
		if (repairAmount > 0)
		{
			if (this.machineHealth + repairAmount <= this.maxMachineHP)
			{
				this.machineHealth += repairAmount;
				return true;
			}
			else
			{
				this.machineHealth = this.maxMachineHP;
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public boolean isMachineAtMaxHealth()
	{
		return this.machineHealth == this.maxMachineHP;
	}
	
}
