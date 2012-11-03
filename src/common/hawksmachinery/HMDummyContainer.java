
package hawksmachinery;

import java.util.Map;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.SaveHandler;
import net.minecraft.src.WorldInfo;
import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.WorldAccessContainer;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMDummyContainer extends DummyModContainer implements WorldAccessContainer
{
	public HMDummyContainer()
	{
		super(new ModMetadata());
		ModMetadata info = getMetadata();
		info.modId = "HawksMachinery";
		info.name = "Hawk's Machinery";
		info.version = HawksMachinery.VERSION;
		info.credits = "Elusivehawk";
		info.description = "Hawk's Machinery is a Universal Electricity mod for Minecraft that adds more ways to process materials.";
		info.url = "http://calclavia.com/forum/index.php/topic,2.0.html";
		info.updateUrl = "https://github.com/Elusivehawk/Hawks-Machinery";
		
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		return true;
	}
	
	@Override
	public NBTTagCompound getDataForWriting(SaveHandler handler, WorldInfo info)
	{
		return null;
	}
	
	@Override
	public void readData(SaveHandler handler, WorldInfo info, Map<String, NBTBase> propertyMap, NBTTagCompound tag)
	{
		
	}
	
}
