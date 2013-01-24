
package hawksmachinery.core.common;

import java.util.Map;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCorePlugin implements IFMLLoadingPlugin
{
	@Override
	public String[] getLibraryRequestClass()
	{
		return null; //TODO Find a nice library or two.
	}
	
	@Override
	public String[] getASMTransformerClass()
	{
		return null; //TODO Get to transforming!
	}
	
	@Override
	public String getModContainerClass()
	{
		return "hawksmachinery.core.common.HMModContainer";
	}
	
	@Override
	public String getSetupClass()
	{
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data)
	{
		//Ignore the method behind the interface!
		
	}
	
}
