
package hawksmachinery.items;

import hawksmachinery.HawksMachinery;
import java.util.List;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemBlueprints extends HMItem
{
	public HMItemBlueprints(int id)
	{
		super(id);
		setHasSubtypes(true);
		setContainerItem(this);
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(1);
		setTextureFile("/gui/items.png");
		//this.en_USNames = new String[]{"Crusher", "Washer", "Bottler", "Compressor",
		//		"Mini-Drill Tank", "Cutter", "Sinterer",
		//		"Rivet Gun", "HM-E2MM"};
		
	}
	
	@Override
	public int getIconFromDamage(int dmg)
	{
		return 188;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return item.getItemDamage() == 8 ? EnumRarity.rare : EnumRarity.uncommon;
		
	}
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() == 8;
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack item)
	{
		return false;
	}
	
	@Override
	public String getItemNameIS(ItemStack item)
	{
		return null; //en_USNames[item.getItemDamage()].toLowerCase() + "Blueprint";
	}
	
}
