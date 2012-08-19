
package hawksmachinery.padAPI;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * Implement this if you want to manipulate what a Pad does when it comes in contact with an entity.
 * 
 * @author Elusivehawk
 */
public interface IHawkPadEffect
{
	
	/**
	 * 
	 * Used in order to determine the effect of a Pad. Note: Called every tick!
	 * 
	 * @param padItem The item in the Pad.
	 * @param world The world.
	 * @param x The x position of the block.
	 * @param y The y position of the block.
	 * @param z The z position of the block.
	 * @param entity The Entity that's on the pad.
	 * @param isBeingRedstoned Whether or not the Pad is being powered by redstone.
	 */
	public void getPadEffect(ItemStack padItem, World world, int x, int y, int z, EntityLiving entity, boolean isBeingRedstoned);
	
	/**
	 * 
	 * Used in order to determine if an item can be put in a Pad.
	 * 
	 * @param padItem The item in the Pad.
	 * @param world
	 * @param x The x position of the block.
	 * @param y The y position of the block.
	 * @param z The z position of the block.
	 * @param player The player that's right-clicking the Pad.
	 * @param padDirection The direction the pad is facing.
	 * @param isBeingRedstoned Whether or not the Pad is being powered by redstone.
	 * @return True if it is, false otherwise.
	 */
	public boolean isItemValidForPad(ItemStack padItem, World world, int x, int y, int z, EntityPlayer player, ForgeDirection padDirection, boolean isBeingRedstoned);
	
}
