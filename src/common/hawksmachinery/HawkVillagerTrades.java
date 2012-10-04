
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
 * Where all trades are handled.
 * 
 * @author Elusivehawk
 */
public class HawkVillagerTrades implements IVillageTradeHandler
{
	public static HawksMachinery BASEMOD;
	
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		int profession = villager.getProfession();
		
		if (profession == 1)
		{
			for (int emeralds = 10; emeralds <= 58; ++emeralds)
			{
				if (emeralds <= 17)
				{
					recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(BASEMOD.blueprints, 1, 7)));
					
				}
				
				if (emeralds >= 24 && emeralds <= 32)
				{
					for (int meta = 0; meta <= 6; ++meta)
					{
						recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(BASEMOD.blueprints, 1, meta)));
					}
					
				}
				
				if (emeralds >= 52)
				{
					recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(BASEMOD.blueprints, 1, 8)));
				}
				
			}
			
		}
		
		if (profession == 2)
		{
			if (BASEMOD.enableChunkloader)
			{
				recipeList.add(new MerchantRecipe(new ItemStack(BASEMOD.chunkloader, 1), new ItemStack(Item.emerald, 12)));
			}
			
		}
		
		if (profession == 3)
		{
			//TODO Add Blacksmith trades.
		}
		
	} 
	
}
