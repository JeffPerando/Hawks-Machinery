
package hawksmachinery.common.api.logo;

import java.util.HashMap;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoWordDictionary
{
	private HashMap<String, IHMLogoWord> wordDictionary = new HashMap<String, IHMLogoWord>();
	private HashMap<String, String> wordDictionaryShort = new HashMap<String, String>();
	
	public void registerWord(IHMLogoWord word)
	{
		if (word.getWord() != null)
		{
			this.wordDictionary.put(word.getWord(), word);
			
			if (word.getShortWord() != null)
			{
				this.wordDictionaryShort.put(word.getShortWord(), word.getWord());
				
			}
			
		}
		
	}
	
	public IHMLogoWord getHandlerForWord(String word)
	{
		if (this.wordDictionary.containsKey(word))
		{
			return this.wordDictionary.get(word);
		}
		else
		{
			if (this.wordDictionaryShort.containsKey(word))
			{
				return this.wordDictionary.get(this.wordDictionaryShort.get(word));
			}
			
		}
		
		return null;
	}
	
}
