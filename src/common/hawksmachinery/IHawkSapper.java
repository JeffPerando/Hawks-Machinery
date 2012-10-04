
package hawksmachinery;

import hawksmachinery.tileentity.HawkTileEntityRepairable;
import net.minecraft.src.World;

/**
 * 
 * Implement this if you want your item to function as a sapper.
 * 
 * @author Elusivehawk
 */
public interface IHawkSapper
{
	/**
	 * 
	 * Used to determine if this sapper is consumed when used.
	 * 
	 * @return True if it is, false otherwise.
	 */
	public boolean isSapperConsumed();
	
	/**
	 * 
	 * Used to determine if this sapper is "silent".
	 * 
	 * @return True if it is, false otherwise.
	 */
	public boolean isSapperSilent();
	
	/**
	 * 
	 * Used to determine how many machine points are taken away per tick.
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
	public void sapperTick(HawkTileEntityRepairable tileEntity);
	
}
