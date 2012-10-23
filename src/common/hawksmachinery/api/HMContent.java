
package hawksmachinery.api;

import net.minecraft.src.Block;
import net.minecraft.src.Item;

/**
 * 
 * Just references to all of the blocks and items in HM, requested by Darkguardsman.
 * 
 * NOTICE: You will need to do a check for HM before using these, otherwise you will crash for obvious reasons.
 * 
 * @author Elusivehawk
 */
public class HMContent
{
	/**
	 * Raw dusts! 0 - Coal, 1 - Iron, 2 - Gold, 3 - Copper, 4 - Tin, 5 - Obsidian, 6 - Endium.
	 */
	public static Item dustRaw;
	/**
	 * Refined dusts! 0 - Diamond, 1 - Ender, 2 - Glass, 3 - Iron, 4 - Gold, 5 - Copper, 6 - Tin, 7 - Emerald, 8 - Nether Star, 9 - Endium.
	 */
	public static Item dustRefined;
	/**
	 * Parts! 0 - Electric Pistons, 1 - Laser, 2 - Being Redone, 3 - Light Bulb, 4 - Heating Coil, 5 - Electric Magnet, 6 - Engine.
	 */
	public static Item parts;
	public static Item blueprints;
	public static Item endiumPlate;
	public static Item rivets;
	public static Item rivetGun;
	public static Item ingots;
	
	public static Block crusher;
	public static Block washer;
	public static Block endiumChunkloader;
	public static Block endiumOre;
	
}
