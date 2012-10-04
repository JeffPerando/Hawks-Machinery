
package hawksmachinery;

import java.util.Random;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkProviderEnd;
import net.minecraft.src.ChunkProviderGenerate;
import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.PlayerManager;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkOreGenerator implements IWorldGenerator
{
	public static HawksMachinery BASEMOD;
	
	public HawkOreGenerator() {}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (chunkGenerator instanceof ChunkProviderGenerate)
		{
			generateSurface(world, random, chunkX << 4, chunkZ << 4);
		}
		else if (chunkGenerator instanceof ChunkProviderHell)
		{
			//generateNether(world, random, chunkX << 4, chunkZ << 4);
		}
		else if (chunkGenerator instanceof ChunkProviderEnd)
		{
			generateEnd(world, random, chunkX << 4, chunkZ << 4);
		}
	}
	
	public void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		
	}
	
	//TODO: Do SOMETHING with the Nether.
	//public void generateNether(World world, Random random, int chunkX, int chunkZ){}
	
	public void generateEnd(World world, Random random, int chunkX, int chunkZ)
	{
		/*
		if (BASEMOD.generateEndium)
		{
			for (int counter = 0; counter <= 16; ++counter)
			{
				int randPosX = chunkX + random.nextInt(16);
				int randPosY = random.nextInt(50);
				int randPosZ = chunkZ + random.nextInt(16);
				int randAmount = random.nextInt(9);
				
				if (randAmount > 0 && randPosY < 70)
				{
					(new HawkWorldOreBlock(BASEMOD.blockOre.blockID, 3, randAmount)).generate(world, random, randPosX, randPosY, randPosZ);
				}
			}
		}
		*/
	}
	
}
