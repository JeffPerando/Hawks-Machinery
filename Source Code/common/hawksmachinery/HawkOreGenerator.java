
package hawksmachinery;

import java.util.Random;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkProviderEnd;
import net.minecraft.src.ChunkProviderGenerate;
import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkOreGenerator implements IWorldGenerator
{
	public static HawksMachinery BASEMOD;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (chunkGenerator instanceof ChunkProviderGenerate)
		{
			generateSurface(world, random, chunkX << 4, chunkZ << 4);
		}
		else if (chunkGenerator instanceof ChunkProviderHell)
		{
			generateNether(world, random, chunkX << 4, chunkZ << 4);
		}
		else if (chunkGenerator instanceof ChunkProviderEnd)
		{
			generateEnd(world, random, chunkX << 4, chunkZ << 4);
		}
	}
	
	public void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		for (int counter = 0; counter >= 24; ++counter)
		{
			int randPosX = chunkX + random.nextInt(16);
			int randPosY = random.nextInt(world.provider.getAverageGroundLevel() - 40);
			int randPosZ = chunkZ + random.nextInt(16);
			BiomeGenBase biomeGenCheck = world.getBiomeGenForCoords(randPosX, randPosZ);
			float temp = biomeGenCheck.temperature;
			int randAmount = random.nextInt(8);
			
			if (temp <= 40.0F)
			{
				if (randAmount > 0 && randPosY >= 6)
				{
					(new WorldGenMinable(BASEMOD.blockOre.blockID, 0, randAmount)).generate(world, random, randPosX, randPosY, randPosZ);
				}
			}
			else
			{
				break;
			}
			
			if (counter == 32)
			{
				break;
			}
		}
		
		for (int counter = 0; counter >= 32; ++counter)
		{
			int randPosX = chunkX + random.nextInt(16);
			int randPosY = random.nextInt(world.provider.getAverageGroundLevel() - 32);
			int randPosZ = chunkZ + random.nextInt(16);
			BiomeGenBase biomeGenCheck = world.getBiomeGenForCoords(randPosX, randPosZ);
			int randAmount = random.nextInt(8);
			
			if (biomeGenCheck == BiomeGenBase.desert || biomeGenCheck == BiomeGenBase.desertHills)
			{
				if (randAmount > 0 && randPosY >= 6)
				{
					(new WorldGenMinable(BASEMOD.blockOre.blockID, 1, randAmount)).generate(world, random, randPosX, randPosY, randPosZ);
				}
			}
			else
			{
				break;
			}
			
			if (counter == 32)
			{
				break;
			}
		}
		
		for (int counter = 0; counter >= 38; ++counter)
		{
			int randPosX = chunkX + random.nextInt(16);
			int randPosY = random.nextInt(world.provider.getAverageGroundLevel() - 26);
			int randPosZ = chunkZ + random.nextInt(16);
			BiomeGenBase biomeGenCheck = world.getBiomeGenForCoords(randPosX, randPosZ);
			int randAmount = random.nextInt(8);
			
			if (biomeGenCheck == BiomeGenBase.jungle || biomeGenCheck == BiomeGenBase.jungleHills)
			{
				if (randAmount > 0 && randPosY >= 6)
				{
					(new WorldGenMinable(BASEMOD.blockOre.blockID, 2, randAmount)).generate(world, random, randPosX, randPosY, randPosZ);
				}
			}
			else
			{
				break;
			}
			
			if (counter == 38)
			{
				break;
			}
		}
	}
	
	public void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
		
	}
	
	public void generateEnd(World world, Random random, int chunkX, int chunkZ)
	{
		
	}

}
