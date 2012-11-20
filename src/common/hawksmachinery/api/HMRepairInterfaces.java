
package hawksmachinery.api;

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
		public boolean attemptToRepair(int repairValue);
		
		public boolean setSapper(ItemStack sapper);
		
		public boolean attemptToUnSap(EntityPlayer player);
		
		public boolean isBeingSapped();
		
		public int getMaxHP();
		
		public int getHP();
		
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
		
		public void sapperTick(World world, int x, int y, int z, ItemStack sapper);
		
		public void onRemoved(World world, int x, int y, int z);
		
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
