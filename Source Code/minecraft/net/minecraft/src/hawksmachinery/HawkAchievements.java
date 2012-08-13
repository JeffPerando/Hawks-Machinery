
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementList;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.AchievementPage;
import net.minecraft.src.forge.MinecraftForge;

/**
 * 
 * Where all of the achievements for Hawk's Machinery are handled.
 * 
 * @author Elusivehawk
 */
public class HawkAchievements
{
	public static mod_HawksMachinery BASEMOD;
	
	public static Achievement shellOfAMachine = new Achievement(HawkManager.ACHshellOfAMachine, "Shell of a Machine", 0, 0, new ItemStack(BASEMOD.blockEmptyMachine, 1, 0), AchievementList.blazeRod).registerAchievement();
	public static Achievement buildABetterMachineBlock = new Achievement(HawkManager.ACHbuildABetterMachineBlock, "Build a Better Machine Block", 2, -2, new ItemStack(BASEMOD.blockEmptyMachine, 1, 1), shellOfAMachine).registerAchievement();
	public static Achievement redstonedWithCare = new Achievement(HawkManager.ACHredstonedWithCare, "Redstoned With Care", 4, -4, new ItemStack(BASEMOD.blockEmptyMachine, 1, 5), buildABetterMachineBlock).registerAchievement();
	public static Achievement timeToGrind = new Achievement(HawkManager.ACHtimeToGrind, "Time to Grind", 4, -6, BASEMOD.blockGrinder, redstonedWithCare).registerAchievement().setSpecial();
	
	public static Achievement minerkiin = new Achievement(HawkManager.ACHminerkiin, "Minerkiin", 4, -3, new ItemStack(BASEMOD.blockOre, 1, 1), AchievementList.buildBetterPickaxe).registerAchievement();
	public static Achievement spartaMiner = new Achievement(HawkManager.ACHspartaMiner, "Spartan Miner", -6, -2, new ItemStack(BASEMOD.blockOre, 1, 3), AchievementList.portal).registerAchievement();
	public static Achievement compactCompact = new Achievement(HawkManager.ACHcompactCompact, "Compact Compact", 2, -3, new ItemStack(BASEMOD.blockMetalStorage, 1, 3), minerkiin).registerAchievement();
	
	public static AchievementPage HAWKSPAGE = new AchievementPage("Hawk's Machinery", shellOfAMachine, buildABetterMachineBlock, timeToGrind, redstonedWithCare, minerkiin, spartaMiner, compactCompact);
	
	public static void achievementStuff()
	{
		ModLoader.addAchievementDesc(shellOfAMachine, "Shell of a Machine", "The hull of a machine");
		ModLoader.addAchievementDesc(buildABetterMachineBlock, "Build a Better Machine Block", "Couldn't find a mouse trap mod...");
		ModLoader.addAchievementDesc(redstonedWithCare, "Redstoned with Care", "Isn't Redstone awesome?");
		ModLoader.addAchievementDesc(timeToGrind, "Time to Grind!", "The matriarch of Hawk's Machinery");
		
		ModLoader.addAchievementDesc(minerkiin, "Minerkiin", "In their tongue...");
		ModLoader.addAchievementDesc(spartaMiner, "Spartan Miner", "TONIGHT, WE MINE, IN HELL!");
		ModLoader.addAchievementDesc(compactCompact, "Compact Compact", "Am I missing an eyebrow?");
		
		MinecraftForge.registerAchievementPage(HAWKSPAGE);
		
	}
}
