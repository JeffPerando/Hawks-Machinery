
package hawksmachinery.interfaces;

import hawksmachinery.tileentity.HMTileEntityRepairable;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMRepairCore
{
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
	
	/**
	 * 
	 * Implement this if you want your item to function as a sapper.
	 * 
	 * @author Elusivehawk
	 */
	public interface IHMSapper
	{
		/**
		 * 
		 * Used to determine if the current damage value counts as a sapper.
		 * 
		 * @param sapper The sapper.
		 * @return True if it is, false otherwise.
		 */
		public boolean isSapper(ItemStack sapper);
		
		/**
		 * 
		 * Used to determine if this sapper is "silent".
		 * 
		 * @param sapper The sapper.
		 * @return True if it is, false otherwise.
		 */
		public boolean isSapperSilent(ItemStack sapper);
		
		/**
		 * 
		 * Used to determine if this sapper is dropped when removed.
		 * 
		 * @return True if it is, false otherwise.
		 */
		public boolean isSapperSingleUse(ItemStack sapper);
		
		/**
		 * 
		 * Used to determine the quantity needed for the sapper to be attached to a machine.
		 * 
		 * @param item The item.
		 * @return How many Sappers are needed.
		 */
		public int getSapperQuantity(ItemStack sapper);
		
		/**
		 * 
		 * Used to determine how many machine points are taken away per second.
		 * 
		 * @return How many machine points are taken away.
		 */
		public int getSapRate();
		
		/**
		 * 
		 * Used to determine how difficult the sapper is to get rid of.
		 * 
		 * @return How difficult it is, or 0 if it's impossible to remove.
		 */
		public int getSapperRemovalChance();
		
		/**
		 * 
		 * Called once the Sapper is attached to the machine.
		 * 
		 * @param world The World.
		 * @param x The x position of the machine.
		 * @param y The y position of the machine.
		 * @param z The z position of the machine.
		 */
		public void onSapperAttached(World world, int x, int y, int z);
		
		/**
		 * 
		 * Called every tick, in case the Sapper does more than sap.
		 * 
		 * @param tileEntity The machine the sapper is connected to.
		 */
		public void sapperTick(HMTileEntityRepairable tileEntity);
		
	}
	
	public enum HMEnumRivet
	{
		COPPER(2, null),
		GOLD(3, null),
		BRONZE(4, null),
		IRON(4, null),
		STEEL(5, null),
		DIAMOND(7, null);
		
		private int repairValue;
		private ItemStack endResult;
		
		HMEnumRivet(int value, ItemStack result)
		{
			this.repairValue = value;
			this.endResult = result;
			
		}
		
		public int getRepairAmount()
		{
			return this.repairValue;
		}
		
		public ItemStack getEndResult()
		{
			return this.endResult;
		}
		
		public static HMEnumRivet getRivetFromNumber(int id)
		{
			return HMEnumRivet.values()[id];
		}
		
	}
	
}
