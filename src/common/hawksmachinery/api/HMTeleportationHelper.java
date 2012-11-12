
package hawksmachinery.api;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ServerConfigurationManager;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.DimensionManager;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTeleportationHelper
{
	private ArrayList<HMEndiumTeleporterCoords> coordsList = new ArrayList<HMEndiumTeleporterCoords>();
	private ArrayList<TileEntity> teWhitelist = new ArrayList<TileEntity>();
	private static HMTeleportationHelper INSTANCE = new HMTeleportationHelper();
	
	public boolean registerCoords(HMEndiumTeleporterCoords coords)
	{
		for (HMEndiumTeleporterCoords otherCoords : this.coordsList)
		{
			if (otherCoords != null)
			{
				if (otherCoords.isEqual(coords.symA(), coords.symB(), coords.symC()))
				{
					return false;
				}
				
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
			if (coords != null)
			{
				if (coords.isEqual(sym1, sym2, sym3))
				{
					return coords;
				}
				
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
			if (coords.dim() != entity.dimension)
			{
				ServerConfigurationManager manager = MinecraftServer.getServer().getConfigurationManager();
				
				if (entity instanceof EntityPlayerMP)
				{
					manager.transferPlayerToDimension(((EntityPlayerMP)entity), coords.dim());
					
				}
				else
				{
					manager.func_82448_a(entity, coords.dim(), DimensionManager.getWorld(entity.dimension), DimensionManager.getWorld(coords.dim()));
					
				}
				
			}
			
			entity.setPosition(coords.x() + 0.5, coords.y() + 1, coords.z() + 0.5);
			
		}
		
	}
	
	public boolean addTileEntityToWhitelist(TileEntity allowedTE)
	{
		return this.teWhitelist.add(allowedTE);
	}
	
	public boolean isTileEntityWhitelisted(TileEntity potenTE)
	{
		if (potenTE != null)
		{
			for (TileEntity te : this.teWhitelist)
			{
				if (te.equals(potenTE))
				{
					return true;
				}
				
			}
			
		}
		
		return false;
	}
	
	public static HMTeleportationHelper instance()
	{
		return INSTANCE;
	}
	
}
