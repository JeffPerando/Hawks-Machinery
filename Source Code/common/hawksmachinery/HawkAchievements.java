
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
	
	public static Achievement shellOfAMachine = new Achievement(HawkManager.ACHshellOfAMachine, "Shell of a Machine", 0, 0, new ItemStack(BASEMOD.blockEmptyMachine, 1, 0), AchievementList.blazeRod).registerAchievement();
	public static Achievement buildABetterMachineBlock = new Achievement(HawkManager.ACHbuildABetterMachineBlock, "Build a Better Machine Block", 2, -2, new ItemStack(BASEMOD.blockEmptyMachine, 1, 1), shellOfAMachine).registerAchievement();
	public static Achievement redstonedWithCare = new Achievement(HawkManager.ACHredstonedWithCare, "Redstoned With Care", 4, -4, new ItemStack(BASEMOD.blockEmptyMachine, 1, 5), buildABetterMachineBlock).registerAchievement();
	public static Achievement timeToGrind = new Achievement(HawkManager.ACHtimeToGrind, "Time to Grind", 4, -6, new ItemStack(BASEMOD.blockMachinery, 1, 0), redstonedWithCare).registerAchievement().setSpecial();
	
	public static Achievement minerkiin = new Achievement(HawkManager.ACHminerkiin, "Minerkiin", -5, -3, new ItemStack(BASEMOD.blockOre, 1, 1), AchievementList.buildBetterPickaxe).registerAchievement();
	public static Achievement spartaMiner = new Achievement(HawkManager.ACHspartaMiner, "Spartan Miner", -6, -2, new ItemStack(BASEMOD.blockOre, 1, 3), AchievementList.portal).registerAchievement();
	public static Achievement compactCompact = new Achievement(HawkManager.ACHcompactCompact, "Compact Compact", -4, -3, new ItemStack(BASEMOD.blockMetalStorage, 1, 3), minerkiin).registerAchievement();
	
	public static AchievementPage HAWKSPAGE = new AchievementPage("Hawk's Machinery", shellOfAMachine, buildABetterMachineBlock, timeToGrind, redstonedWithCare, minerkiin, spartaMiner, compactCompact);
	
}
