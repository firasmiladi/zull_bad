import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Firas Miladi
 * 
 * @version 10.04.2020
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private HashMap<String, CommandWord> avalidCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.avalidCommands = new HashMap<String, CommandWord>();
        for(CommandWord vcommand : CommandWord.values()) {
            if(vcommand != CommandWord.UNKNOWN) {
                this.avalidCommands.put(vcommand.toString(), vcommand);
            }
        }
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     * @param pString command input
     */
    public boolean isCommand( final String pString )
    {
        return this.avalidCommands.containsKey(pString);
    } // isCommand()
    
    /**
     * Print all valid commands to System.out.
     * @return all valid commands
     */
    public String showAll() 
    {
        StringBuilder returnString = new StringBuilder("");
        
        for ( String vS : avalidCommands.keySet() )
                returnString.append( " " + vS );
        return returnString.toString();
    }
    
     /**
     * Find the CommandWord associated with a command word.
     * @param pCommandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String pCommandWord)
    {
        CommandWord vcommand = this.avalidCommands.get(pCommandWord);
        if(vcommand != null) {
            return vcommand;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
}
 // CommandWords