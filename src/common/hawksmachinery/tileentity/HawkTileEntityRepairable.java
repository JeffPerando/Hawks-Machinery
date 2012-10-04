
package hawksmachinery.tileentity;

import java.util.Random;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import hawksmachinery.IHawkRepairable;
import hawksmachinery.IHawkSapper;

/**
 * 
 * Extend this instead of {@link HawkTileEntityMachine} if you're going to also implement {@link IHawkRepairable}.
 * 
 * @author Elusivehawk
 */
public abstract class HawkTileEntityRepairable extends HawkTileEntityMachine implements IHawkRepairable
{
	public int machineHealth;
	public int maxMachineHP;
	public boolean isBeingSapped;
	public IHawkSapper sapper;
	
	@Override
	public void updateEntity()
	{
		if (this.isBeingSapped || this.machineHealth <= 0)
		{
			++this.disabledTicks;
			this.machineHealth -= this.sapper.getSapRate();
		}
		
		this.sapper.sapperTick(this);
		super.updateEntity();
		
	}
	
	public boolean attemptToUnSap(EntityPlayer player)
	{
		if (this.sapper.getSapperRemovalChance() <= 0)
		{
			player.addChatMessage("The Sapper cannot be removed.");
		}
		else
		{
			int randomInt = new Random().nextInt(this.sapper.getSapperRemovalChance());
			
			if (randomInt == 2)
			{
				this.isBeingSapped = false;
			}
			
		}
		
		return !this.isBeingSapped;
	}
	
	public boolean randomlyDamageSelf()
	{
		int randomDigit = new Random().nextInt(10);
		
		if (randomDigit == 6)
		{
			--this.machineHealth;
		}
		
		return false;
	}
	
	public boolean attemptRepair(World world, int x, int y, int z, int repairAmount)
	{
		//TODO Wait for Rivet Gun implementation.
		return false;
	}
	
	@Override
	public boolean isDisabled()
	{
		return this.isBeingSapped || this.machineHealth <= 0;
	}
	
}
