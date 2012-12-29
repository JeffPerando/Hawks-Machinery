
package hawksmachinery.common.api.logo;

import hawksmachinery.common.HMEntityRobot;
import hawksmachinery.common.api.HMArrayHelper;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoInterpreter
{
	private IHMRobot robot;
	
	public HMLogoInterpreter(IHMRobot robot)
	{
		this.robot = robot;
		
	}
	
	public HMLogoError runProgram(String[] program)
	{
		//The accepted words, ready to be invoked.
		ArrayList<String> processedWords = new ArrayList<String>();
		ArrayList<String[]> args = new ArrayList<String[]>();
		ArrayList<HMLogoSubroutine> subroutines = new ArrayList<HMLogoSubroutine>();
		HashMap<String, Integer> integers = new HashMap<String, Integer>();
		
		//The current subroutine being worked on.
		HMLogoSubroutine currentSub = null;
		
		//Nesting stuff.
		int nestingLayer = 0;
		String nestedArg = null;
		
		for (String line : program)
		{
			if (!line.equals(""))
			{
				//The code, split up into individual, easy-to-for-loop pieces.
				ArrayList<String> words = HMArrayHelper.instance().convertBracketsToArray(line.split(" "));
				
				//The current word being examined.
				IHMLogoWord currentWord = null;
				
				for (int counter = 0; counter < words.size(); ++counter)
				{
					ArrayList<String> argList = new ArrayList<String>();
					
					String word = words.get(counter).toLowerCase().replace("	", "");
					
					if (word.equals(";")) break;
					
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
								return new HMLogoError(HMEnumErrorType.IMPROPER_NEST, word, null);
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
								return new HMLogoError(HMEnumErrorType.IMPROPER_NEST, word, null);
							}
							
						}
						
						if (nestingLayer > 0)
						{
							nestedArg += " " + word + " ";
							continue;
							
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
							return new HMLogoError(HMEnumErrorType.INVALID_SYNTAX, word, null);
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
							return new HMLogoError(HMEnumErrorType.INVALID_SYNTAX, word, null);
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
										return new HMLogoError(HMEnumErrorType.INVALID_ARGS, word, new String[]{words.get(counter2)});
									}
									
								}
								else
								{
									return new HMLogoError(HMEnumErrorType.INVALID_ARGS, word, null);
								}
								
							}
							
							if (currentWord.supportsNesting())
							{
								String[] newNestedArgs = nestedArg.split(" ");
								
								for (String newTwine : newNestedArgs)
								{
									argList.add(newTwine);
									
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
					HMLogoError error = null;
					IHMLogoWord wordHandler = HMLogoWordRegistry.instance().getHandlerForWord(processedWords.get(counter));
					
					if (wordHandler != null)
					{
						error = wordHandler.activateWord(args.get(counter), this.robot);
						
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
									error = sub.invoke(this.robot);
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
			
		}
		
		return null;
	}
	
}
