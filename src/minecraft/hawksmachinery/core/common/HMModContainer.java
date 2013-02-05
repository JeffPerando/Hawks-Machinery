
package hawksmachinery.core.common;

import java.util.Arrays;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMModContainer extends DummyModContainer
{
	public HMModContainer()
	{
		super(new ModMetadata());
		getMetadata().modId = "HawksMachinery";
		getMetadata().name = "Hawk's Machinery";
		getMetadata().version = HMCore.VERSION;
		getMetadata().credits = "*shrugs*";
		getMetadata().authorList = Arrays.asList("Elusivehawk");
		getMetadata().description = "The mod that adds stuff to Minecraft!";
		getMetadata().url = "";
		getMetadata().updateUrl = "";
		getMetadata().screenshots = new String[0];
		getMetadata().logoFile = HMCore.RESOURCES_PATH + "/HMLogo.png";
		
		
	}
	
}
