
package hawksmachinery.interfaces;

import java.util.List;
import java.util.Random;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;

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
	
	public void teleportEntity(EntityPlayer player, HMEndiumTeleporterCoords coords)
	{
		if (new Random().nextInt(100) > 10)
		{
			coords.setDimension(1);
		}
		
		
		
	}
	
	public static HMTeleportationHelper instance()
	{
		return INSTANCE;
	}
	
}
