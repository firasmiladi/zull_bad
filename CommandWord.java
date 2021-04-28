/**
 * Decrivez votre classe CommandWord ici.
 * 
 * @author (Firas miladi ) 
 * @version (20/04/2020)
 */
public enum CommandWord
{
    // A value for each command word, plus one for unrecognised
    // commands.
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), BACK("back"), TAKE("take"), DROP("drop"), TEST("test"), ITEMS("items"), UNKNOWN("?"), LOAD("load"), TELEPORT("teleport"), LOCK("lock"),UNLOCK("unlock") ;
    
    private String aCommandString;
    /**
     * Initialise with the corresponding command word.
     * @param pCommandString The command string.
     */
    CommandWord(final String pCommandString)
    {this.aCommandString    =    pCommandString;}
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return this.aCommandString;
    }
} // CommandWord
