
package hawksmachinery.core.common;

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
		
	}
	
}
