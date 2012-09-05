
package hawksmachinery.misc;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementList;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.AchievementPage;
import net.minecraft.src.Item;

/**
 * 
 * Where all of the achievements for Hawk's Machinery are stored.
 * 
 * @author Elusivehawk
 */
public class HawkAchievements
{
	public static HawksMachinery BASEMOD;
	
	public static Achievement prospector = new Achievement(BASEMOD.ACHprospector, "Prospectpr", -1, 0, new ItemStack(Item.pickaxeSteel, 1), AchievementList.buildBetterPickaxe).registerAchievement();
	public static Achievement timeToGrind = new Achievement(BASEMOD.ACHtimeToGrind, "Time to Grind", -2, -3, new ItemStack(BASEMOD.blockGrinder, 1, 0), prospector).registerAchievement().setSpecial();
	public static Achievement compactCompact = new Achievement(BASEMOD.ACHcompactCompact, "Compact Compact", 0, -2, new ItemStack(BASEMOD.blockMetalStorage, 1, 2), prospector).registerAchievement();
	public static Achievement minerkiin = new Achievement(BASEMOD.ACHminerkiin, "Minerkiin", -5, 2, new ItemStack(BASEMOD.blockOre, 1, 3), AchievementList.theEnd2).registerAchievement();
	
	public static AchievementPage HAWKSPAGE = new AchievementPage("Hawk's Machinery", timeToGrind, minerkiin, compactCompact, prospector);
	
}
