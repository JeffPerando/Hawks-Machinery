
package hawksmachinery.interfaces;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * 
 * All of HM's repair interfaces.
 * 
 * @author Elusivehawk
 */
public class HMRepairInterfaces
{
	/**
	 * 
	 * 
	 * 
	 * @author Elusivehawk
	 */
	public interface IHMRepairable
	{
		int machineHP = 0;
		public ItemStack sapper = null;
		
		public boolean attemptToRepair(int repairValue);
		
		public boolean setSapper(ItemStack sapper);
		
		public boolean attemptToUnSap(EntityPlayer player);
		
		public boolean isBeingSapped();
		
		public int getMaxHP();
		
		void randomlyDamageSelf();
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @author Elusivehawk
	 */
	public interface IHMSapper
	{
		public int sappersRequired(ItemStack sapper);
		
		public int getRemovalValue(ItemStack sapper, EntityPlayer player);
		
		public void sapperTick(World world, int x, int y, int z);
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @author Elusivehawk
	 */
	public interface IHMRivet
	{
		public int getRepairAmount(ItemStack rivet);
	}
	
}
