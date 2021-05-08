import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Miladi Firas 
 * @version 2021.02.01
 */
public class Parser 
{
    private CommandWords aValidCommands;  // (voir la classe CommandWords)
    private Scanner aReader;
    
    /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
        this.aReader        = new Scanner( System.in );
    } // Parser()

    /**
     * get command
     * @param pInputLine command    
     * @return command from the user.
     */
    public Command getCommand(final String pInputLine) 
    {   String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // get first word
        else
            vWord1 = null;

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // get second word
        else
            vWord2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        return new Command(this.aValidCommands.getCommandWord(vWord1), vWord2);
    }
    
    /**
     * Print out a list of valid command words.
     * @return list of command
     */
    public String showCommands()
    {
        return this.aValidCommands.showAll();
    }
    } // Parser
