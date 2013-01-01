
package hawksmachinery.common.logo;

import hawksmachinery.common.HMEntityRobot;
import hawksmachinery.common.api.HMArrayHelper;
import hawksmachinery.common.api.logo.HMEnumErrorType;
import hawksmachinery.common.api.logo.HMEnumCharType;
import hawksmachinery.common.api.logo.HMLogoError;
import hawksmachinery.common.api.logo.IHMLogoInterpreter;
import hawksmachinery.common.api.logo.IHMLogoWord;
import hawksmachinery.common.api.logo.IHMRobot;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMLogoInterpreter implements IHMLogoInterpreter
{
	private IHMRobot robot;
	
	public HMLogoInterpreter(IHMRobot robot)
	{
		this.robot = robot;
		
	}
	
	@Override
	public HMLogoError runProgram(String[] program, boolean saveTemps)
	{
		//The accepted words, ready to be invoked.
		ArrayList<String> processedWords = new ArrayList<String>();
		ArrayList<String[]> args = new ArrayList<String[]>();
		HashMap<String, HMLogoSubroutine> subroutines = new HashMap<String, HMLogoSubroutine>();
		HashMap<String, Integer> integers = new HashMap<String, Integer>();
		
		//The current subroutine being worked on.
		HMLogoSubroutine currentSubroutine = null;
		
		//Nesting stuff.
		int nestingLayer = 0;
		String nestedArg = null;
		
		//The current word being examined.
		IHMLogoWord currentWord = null;
		
		for (String line : program)
		{
			if (!line.equals(""))
			{
				//The code, split up into individual, easy-to-for-loop pieces.
				ArrayList<String> words = HMArrayHelper.instance().convertBracketsToArray(line.split(" "));
				
				for (int counter = 0; counter < words.size(); ++counter)
				{
					ArrayList<String> argListTemp = new ArrayList<String>();
					
					String word = words.get(counter).toLowerCase().trim();
					
					if (word.equals(";")) break;
					
					if (currentWord != null)
					{
						for (Character letter : word.toCharArray())
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
								if (nestingLayer > 0)
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
							
						}
						
					}
					
					if (word.equals("to"))
					{
						if (currentSubroutine == null && words.size() > counter + 1)
						{
							currentSubroutine = new HMLogoSubroutine(words.get(counter + 1));
							continue;
							
						}
						else
						{
							return new HMLogoError(HMEnumErrorType.INVALID_SYNTAX, word, null);
						}
						
					}
					
					if (word.equals("end"))
					{
						if (currentSubroutine != null)
						{
							subroutines.put(currentSubroutine.name, currentSubroutine);
							currentSubroutine = null;
							continue;
							
						}
						else
						{
							return new HMLogoError(HMEnumErrorType.INVALID_SYNTAX, word, null);
						}
						
					}
					
					if (counter == (words.size() - 1) && currentSubroutine != null) return new HMLogoError(HMEnumErrorType.INVALID_SYNTAX, currentSubroutine.name, null);
					
					currentWord = this.robot.getHandlerForWord(word);
					
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
										argListTemp.add(words.get(counter2));
										
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
							
							if (currentWord.supportsNesting() && nestingLayer == 0 && nestedArg != null)
							{
								args.add(nestedArg.split(" "));
								processedWords.add(word);
								continue;
								
							}
							
							if (currentSubroutine != null)
							{
								currentSubroutine.args.add((String[])HMArrayHelper.instance().convertArrayToBrackets(argListTemp));
								currentSubroutine.words.add(word);
								continue;
								
							}
							else
							{
								args.add((String[])HMArrayHelper.instance().convertArrayToBrackets(argListTemp));
								
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
					IHMLogoWord wordHandler = this.robot.getHandlerForWord(processedWords.get(counter));
					
					if (wordHandler != null)
					{
						error = wordHandler.activateWord(args.get(counter), this.robot);
						
					}
					else
					{
						if (subroutines.size() > 0)
						{
							HMLogoSubroutine sub = subroutines.get(processedWords.get(counter));
							error = sub.invoke(this.robot);
							
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
	
	private HMEnumCharType getTypeFromChar(Character letter)
	{
		char[] ints = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		char[] ops = new char[]{'+', '-', '/', '*', '='};
		char[] brackets = new char[]{'{', '}', '[', ']'};
		
		for (Character potenInt : ints) if (letter.equals(potenInt)) return HMEnumCharType.INT;
		
		for (Character potenOp : ops) if (letter.equals(potenOp)) return HMEnumCharType.OPERATOR;
		
		for (Character potenBracket : brackets) if (letter.equals(potenBracket)) return HMEnumCharType.BRACKET;
		
		return HMEnumCharType.STRING;
	}
	
}
