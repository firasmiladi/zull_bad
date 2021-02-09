 public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    public Command(final String pFirst ,final String pSecond) 
    {
        this.aCommandWord = pFirst;
        this.aSecondWord = pSecond;
    }
    
    public String getCommandWord()
    {
        return this.aCommandWord;
    }
    
    public String getSecondWord()
    {
        return this.aSecondWord;
    }
    
    public boolean hasSecondWord ()
    {
        return this.aSecondWord != null ; 
    }
    
    public boolean isUnknown ()
    {
        return this.aCommandWord == null ; 
    }
}// Command
