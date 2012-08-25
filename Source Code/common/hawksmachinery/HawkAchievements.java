
package hawksmachinery;

import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementList;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.AchievementPage;

/**
 * 
 * Where all of the achievements for Hawk's Machinery are stored.
 * 
 * @author Elusivehawk
 */
public class HawkAchievements
{
	public static HawksMachinery BASEMOD;
	
	public static Achievement prospector = new Achievement(HawkManager.ACHprospector, "Prospectpr", -1, 0, new ItemStack(BASEMOD.blockOre, 1, 0), AchievementList.buildBetterPickaxe).registerAchievement();
	public static Achievement timeToGrind = new Achievement(HawkManager.ACHtimeToGrind, "Time to Grind", -2, -3, new ItemStack(BASEMOD.blockGrinder, 1, 0), prospector).registerAchievement().setSpecial();
	public static Achievement compactCompact = new Achievement(HawkManager.ACHcompactCompact, "Compact Compact", 0, -2, new ItemStack(BASEMOD.blockMetalStorage, 1, 2), prospector).registerAchievement();
	public static Achievement minerkiin = new Achievement(HawkManager.ACHminerkiin, "Minerkiin", -5, 2, new ItemStack(BASEMOD.blockOre, 1, 3), AchievementList.theEnd2).registerAchievement();
	
	public static AchievementPage HAWKSPAGE = new AchievementPage("Hawk's Machinery", timeToGrind, minerkiin, compactCompact);
	
}
