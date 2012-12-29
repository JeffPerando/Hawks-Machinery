
package hawksmachinery.common.api.logo;

import hawksmachinery.common.HMEntityRobot;
import hawksmachinery.common.api.HMArrayHelper;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoCore
{
	private static HMLogoCore INSTANCE = new HMLogoCore();
	
	public HMLogoReadError runProgram(String[] program, HMEntityRobot robot)
	{
		//The accepted words, ready to be invoked.
		ArrayList<String> processedWords = new ArrayList<String>();
		ArrayList<String[]> args = new ArrayList<String[]>();
		ArrayList<HMLogoSubroutine> subroutines = new ArrayList<HMLogoSubroutine>();
		
		//The current subroutine being worked on.
		HMLogoSubroutine currentSub = null;
		
		//Nesting stuff.
		int nestingLayer = 0;
		String nestedArg = null;
		
		for (String line : program)
		{
			//The code, split up into individual, easy-to-for-loop pieces.
			ArrayList<String> words = HMArrayHelper.instance().convertBracketsToArray(line.split(" "));
			
			//The current word being examined.
			IHMLogoWord currentWord = null;
			
			for (int counter = 0; counter < words.size(); ++counter)
			{
				ArrayList<String> argList = new ArrayList<String>();
				
				String word = words.get(counter).toLowerCase().replace("	", " ");
				
				if (word.equals(";")) break;
				
				if (nestingLayer > 0)
				{
					nestedArg += " " + word + " ";
					continue;
					
				}
				
				if (currentWord != null)
				{
					if (word.equals("["))
					{
						if (currentWord.supportsNesting())
						{
							++nestingLayer;
							nestedArg += " " + word + " ";
							continue;
							
						}
						else
						{
							return HMLogoReadError.IMPROPER_NEST;
						}
						
					}
					
					if (word.equals("]"))
					{
						if (currentWord.supportsNesting() && nestingLayer > 0)
						{
							--nestingLayer;
							nestedArg += " " + word + " ";
							continue;
							
						}
						else
						{
							return HMLogoReadError.IMPROPER_NEST;
						}
						
					}
					
					if (currentWord.supportsNesting() && nestingLayer == 0 && nestedArg != null)
					{
						processedWords.add(word);
						continue;
						
					}
					
				}
				
				if (word.equals("to"))
				{
					if (currentSub == null && words.size() > counter + 1)
					{
						currentSub = new HMLogoSubroutine(words.get(counter + 1));
						continue;
						
					}
					else
					{
						return HMLogoReadError.INVALID_SYNTAX;
					}
					
				}
				
				if (word.equals("end"))
				{
					if (currentSub != null)
					{
						subroutines.add(currentSub);
						currentSub = null;
						continue;
						
					}
					else
					{
						return HMLogoReadError.INVALID_SYNTAX;
					}
					
				}
				
				currentWord = HMLogoWordRegistry.instance().getHandlerForWord(word);
				
				if (currentWord != null)
				{
					if (currentWord.argCount() > 0)
					{
						for (int counter2 = counter + 1; counter2 < currentWord.argCount() + counter + 1; ++counter2)
						{
							if (words.size() > counter2)
							{
								if (currentWord.isValidArgument(words.get(counter2)) && !words.get(counter2).equals(";"))
								{
									argList.add(words.get(counter2));
									
								}
								else if (!words.get(counter2).equals(";"))
								{
									return HMLogoReadError.INVALID_ARGS;
								}
								
							}
							else
							{
								return HMLogoReadError.INVALID_ARGS;
							}
							
						}
						
						if (currentSub != null)
						{
							currentSub.args.add((String[])HMArrayHelper.instance().convertArrayToBrackets(argList));
							currentSub.words.add(word);
							continue;
							
						}
						else
						{
							args.add((String[])HMArrayHelper.instance().convertArrayToBrackets(argList));
							
						}
						
					}
					else
					{
						args.add(null);
						
					}
					
					processedWords.add(word);
					continue;
					
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
