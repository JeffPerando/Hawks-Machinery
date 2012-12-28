
package hawksmachinery.common.api.logo;

import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoCore
{
	private static HMLogoCore INSTANCE = new HMLogoCore();
	
	public HMLogoReadError runProgram(String[] code, EntityLiving robot)
	{
		for (String line : code)
		{
			HMLogoReadError potenError = this.processLOGO(line, robot);
			
			if (potenError.getErrorMessage() != null)
			{
				return potenError;
			}
			
		}
		
		return HMLogoReadError.FINE;
	}
	
	public HMLogoReadError processLOGO(String code, EntityLiving robot)
	{
		//The code, split up into individual, easy-to-for-loop pieces.
		String[] words = code.split(" ");
		
		//The accepted words, ready to be invoked.
		ArrayList<String> processedWords = new ArrayList<String>();
		ArrayList<String[]> args = new ArrayList<String[]>();
		ArrayList<HMLogoSubroutine> subroutines = new ArrayList<HMLogoSubroutine>();
		
		//The current word being examined.
		IHMLogoWord currentWord = null;
		
		//The current subroutine being worked on.
		HMLogoSubroutine currentSub = null;
		
		//Nesting stuff.
		ArrayList<Boolean> nested = new ArrayList<Boolean>();
		int lastNest = 0;
		
		for (int counter = 0; counter < words.length; ++counter)
		{
			String word = words[counter];
			
			if (word.equals(";")) break;
			
			if (word.equals("["))
			{
				boolean properNesting = false;
				
				if (currentWord != null)
				{
					if (currentWord.supportsNesting())
					{
						nested.add(true);
						properNesting = true;
						
					}
					
				}
				
				if (!properNesting) return HMLogoReadError.IMPROPER_NEST;
				
			}
			
			if (word.equals("]"))
			{
				boolean properNesting = false;
				
				if (currentWord != null)
				{
					if (currentWord.supportsNesting())
					{
						boolean successfulNest = false;
						
						if (nested.size() > 0)
						{
							if (nested.get(nested.size() - 1))
							{
								nested.remove(nested.size() - 1);
								if (nested.size() == 0) lastNest = counter;
								successfulNest = true;
								properNesting = true;
								
							}
							
						}
						
						if (!successfulNest) return HMLogoReadError.UNFINISHED_NEST;
						
					}
					
				}
				
				if (!properNesting) return HMLogoReadError.IMPROPER_NEST;
				
			}
			
			if (word.equals("TO") && counter < (words.length - 1))
			{
				if (!words[counter + 1].equals(";"))
				{
					currentSub = new HMLogoSubroutine(words[counter + 1]);
					continue;
					
				}
				else
				{
					return HMLogoReadError.INVALID_ARGS;
				}
				
			}
			
			if (word.equals("END") && currentSub != null)
			{
				subroutines.add(currentSub);
				currentSub = null;
				
			}
			
			currentWord = HMLogoWordRegistry.instance().getHandlerForWord(word);
			
			if (currentWord != null)
			{
				if (currentWord.supportsNesting())
				{
					if (lastNest == 0)
					{
						return HMLogoReadError.MISSING_NEST;
					}
					else
					{
						String[] arguments = new String[lastNest - counter];
						
						for (int counter2 = counter + 1; counter2 < lastNest; ++counter2)
						{
							arguments[counter2] = words[counter];
							
						}
						
						if (currentSub != null)
						{
							currentSub.words.add(word);
							currentSub.args.add(arguments);
							
						}
						else
						{
							processedWords.add(word);
							args.add(arguments);
							
						}
						
					}
					
				}
				else
				{
					if (words.length != 1 && counter != words.length && !words[counter + 1].equals(";"))
					{
						if (currentSub != null)
						{
							currentSub.words.add(word);
							currentSub.args.add(new String[]{words[counter + 1]});
							
						}
						else
						{
							processedWords.add(word);
							args.add(new String[]{words[counter + 1]});
							
						}
						
					}
					else
					{
						return HMLogoReadError.MISSING_ARGS;
					}
					
				}
				
			}
			else
			{
				if (subroutines.size() > 0)
				{
					for (int counter2 = 0; counter2 < subroutines.size(); ++counter2)
					{
						HMLogoSubroutine sub = subroutines.get(counter2);
						
						if (sub.name == word)
						{
							processedWords.add(sub.name);
							break;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		if (processedWords.size() > 0)
		{
			for (int counter = 0; counter < processedWords.size(); ++counter)
			{
				HMLogoReadError error = null;
				IHMLogoWord wordHandler = HMLogoWordRegistry.instance().getHandlerForWord(processedWords.get(counter));
				
				if (wordHandler != null)
				{
					error = wordHandler.activateWord(args.get(counter), robot);
					
				}
				else
				{
					if (subroutines.size() > 0)
					{
						for (int counter2 = 0; counter2 < subroutines.size(); ++counter2)
						{
							HMLogoSubroutine sub = subroutines.get(counter2);
							
							if (sub.name.equals(processedWords.get(counter)))
							{
								error = sub.invoke(robot);
								break;
								
							}
							
						}
						
					}
					
				}
				
				if (error != null)
				{
					String errorMessage = error.getErrorMessage();
					
					if (errorMessage != null)
					{
						return error;
					}
					
				}
				
			}
			
		}
		
		return HMLogoReadError.FINE;
	}
	
	public static HMLogoCore instance()
	{
		return INSTANCE;
	}
	
}
