
package hawksmachinery.interfaces;

import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ServerConfigurationManager;
import net.minecraft.src.World;
import net.minecraftforge.common.DimensionManager;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTeleportationHelper
{
	private List<HMEndiumTeleporterCoords> coordsList;
	private static HMTeleportationHelper INSTANCE;
	
	public HMTeleportationHelper()
	{
		INSTANCE = this;
	}
	
	public boolean registerCoords(HMEndiumTeleporterCoords coords)
	{
		for (HMEndiumTeleporterCoords otherCoords : this.coordsList)
		{
			if (otherCoords.isEqual(coords.symA(), coords.symB(), coords.symC()))
			{
				return false;
			}
			else
			{
				continue;
			}
			
		}
		
		this.coordsList.add(coords);
		return true;
	}
	
	public boolean removeCoords(HMEndiumTeleporterCoords coords)
	{
		return this.coordsList.remove(coords);
	}
	
	public HMEndiumTeleporterCoords getCoordsFromSymbols(int sym1, int sym2, int sym3)
	{
		for (HMEndiumTeleporterCoords coords : this.coordsList)
		{
			if (coords.isEqual(sym1, sym2, sym3))
			{
				return coords;
			}
			
		}
		
		return null;
	}
	
	public void teleportEntity(Entity entity, HMEndiumTeleporterCoords coords)
	{
		if (new Random().nextInt(100) > 10)
		{
			entity.travelToTheEnd(entity.dimension);
			return;
		}
		
		if (!entity.worldObj.isRemote)
		{
			if (coords.dimension() != entity.dimension)
			{
				ServerConfigurationManager manager = MinecraftServer.getServer().getConfigurationManager();
				
				if (entity instanceof EntityPlayerMP)
				{
					manager.transferPlayerToDimension(((EntityPlayerMP)entity), coords.dimension());
					
				}
				else
				{
					manager.func_82448_a(entity, coords.dimension(), DimensionManager.getWorld(entity.dimension), DimensionManager.getWorld(coords.dimension()));
					
				}
				
			}
			
			entity.setPosition(coords.x() - 0.5, coords.y(), coords.z() - 0.5);
			
		}
		
	}
	
	public static HMTeleportationHelper instance()
	{
		return INSTANCE;
	}
	
}
