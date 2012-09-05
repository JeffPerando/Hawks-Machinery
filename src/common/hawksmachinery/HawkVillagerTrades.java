
package hawksmachinery;

import java.util.Random;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MerchantRecipe;
import net.minecraft.src.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

/**
 * 
 * Where all trades are done.
 * 
 * @author Elusivehawk
 */
public class HawkVillagerTrades implements IVillageTradeHandler
{
	public static HawksMachinery BASEMOD;
	
	public HawkVillagerTrades() {}
	
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		int profession = villager.getProfession();
		
		if (profession == 1)
		{
			//TODO: Make Librarian trades.
		}
		
		if (profession == 2)
		{
			//TODO: Make Priest trades.
		}
		
		if (profession == 3)
		{
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 6), new ItemStack(BASEMOD.ingots, 1, 0)));
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 7), new ItemStack(BASEMOD.ingots, 1, 0)));
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 8), new ItemStack(BASEMOD.ingots, 1, 0)));
			
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 6), new ItemStack(BASEMOD.ingots, 1, 1)));
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 7), new ItemStack(BASEMOD.ingots, 1, 1)));
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 8), new ItemStack(BASEMOD.ingots, 1, 1)));
			
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 6), new ItemStack(BASEMOD.ingots, 1, 2)));
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 7), new ItemStack(BASEMOD.ingots, 1, 2)));
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 8), new ItemStack(BASEMOD.ingots, 1, 2)));
			
		}
		
	}
	
}
