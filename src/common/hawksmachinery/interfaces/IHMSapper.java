
package hawksmachinery.interfaces;

import hawksmachinery.tileentity.HMTileEntityRepairable;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

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
