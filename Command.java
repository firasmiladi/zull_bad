/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class is used to trait commands as they are typed in.
 *
 * @author  Miladi Firas
 * 
 * @version 2021.02.1
 */
 public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    /**
     * Constructor - initialise the command words.
     * 
     * @param  first word of the command  
     * @param  second word of the command
     */
    public Command(final String pFirst ,final String pSecond) 
    {
        this.aCommandWord = pFirst;
        this.aSecondWord = pSecond;
    }//Command
    
    /**
     * get the first word of the command
     * @return the first word of the command from the user.
     */
    public String getCommandWord()
    {
        return this.aCommandWord;
    }//getCommandWord
    
    /**
     * get the second word of the command
     * @return the second word of the command from the user.
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    }//getSecondWord
    
    /**
     * Check if the command has a second word. 
     * @return true if the command has a second  word,
     * false if not.
     */
    public boolean hasSecondWord ()
    {
        return this.aSecondWord != null ; 
    }//hasSecondWord
    
    /**
     * Check if there is a command or not . 
     * @return true if there is not a command
     * false if there is .
     */
    public boolean isUnknown ()
    {
        return this.aCommandWord == null ; 
    }//isUnknown
}// Command
