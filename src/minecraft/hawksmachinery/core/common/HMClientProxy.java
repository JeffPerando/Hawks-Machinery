
package hawksmachinery.core.common;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMClientProxy extends HMCommonProxy
{
	public static final String[] SUPPORTED_LANGS = new String[]{"en_US"};
	
	public void registerRenderInformation()
	{
		super.registerRenderInformation();
		
		MinecraftForgeClient.preloadTexture(HMCore.instance().BLOCK_TEXTURE_FILE);
		MinecraftForgeClient.preloadTexture(HMCore.instance().ITEM_TEXTURE_FILE);
		
		for (String lang : SUPPORTED_LANGS)
		{
			LanguageRegistry.instance().loadLocalization(HMCore.LANG_PATH + "/" + lang + ".txt", lang, false);
			
		}
		
	}
	
}
