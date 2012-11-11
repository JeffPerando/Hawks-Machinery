
package hawksmachinery.api;

import java.util.ArrayList;
import java.util.HashMap;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
@SideOnly(Side.CLIENT)
public class HMLanguageCore
{
	private static ArrayList<IHMLangHandler> langHandlers = new ArrayList<IHMLangHandler>();
	private static HashMap<IHMLangHandler, String> langList = new HashMap<IHMLangHandler, String>();
	
	public interface IHMLangHandler
	{
		public static LanguageRegistry LANG = LanguageRegistry.instance();
		
		public void addToolTips(String lang);
		
	}
	
	public static void registerLangHandler(IHMLangHandler langHandler, String lang)
	{
		if (lang != null)
		{
			langList.put(langHandler, lang);
			langHandlers.add(langHandler);
			
		}
		
	}
	
	public static void addToolTips()
	{
		for (IHMLangHandler handler : langHandlers)
		{
			if (handler != null)
			{
				if (langList.get(handler) != null)
				{
					handler.addToolTips(langList.get(handler));
					
				}
				
			}
			
		}
		
	}
	
}
