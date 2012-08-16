
package hawksmachinery;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockMachine extends ItemBlock
{
	public static HawksMachinery BASEMOD;
	
	/**
	 * English names.
	 */
	public static String[] en_USNames = {"Empty", "Basic", "Advanced", "Elite", "Basic Redstone-Treated", "Advanced Redstone-Treated", "Elite Redstone-Treated"};
	
	public HawkItemBlockMachine(int id)
    {
	    super(id);
	    setHasSubtypes(true);
	    setMaxDamage(0);
    }
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		int dmg = item.getItemDamage();
		
		if (dmg == 0)
		{
			player.addStat(HawkAchievements.shellOfAMachine, 1);
		}
		
		if (dmg <= 3 && dmg != 0)
		{
			player.addStat(HawkAchievements.buildABetterMachineBlock, 1);
		}
		
		if (dmg <= 6 && dmg > 3)
		{
			player.addStat(HawkAchievements.redstonedWithCare, 1);
		}
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return (new StringBuilder()).append(super.getItemName()).append(".").append(en_USNames[item.getItemDamage()]).toString();
	}
	
}
