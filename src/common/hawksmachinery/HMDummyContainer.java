
package hawksmachinery;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMDummyContainer extends DummyModContainer
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
	
}
