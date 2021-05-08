/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class is used to trait Beamer used to teleport .
 * 
 * @author Firas miladi  
 * @version 30/04/2020
 */
public class Beamer extends Item{
    private Room aSavedRoom;
    private boolean aLoaded;
    
    /**
     * Constructor - initialise the command words.
     * 
     * @param pNoun the object noun .
     * @param pDescription the object description 
     * @param pPrice the object price 
     * @param pWeight the object weight 
     */
    public Beamer(final String pNoun, final String pDescription, final double pPrice,final double pWeight){
        super(pNoun, pDescription, pPrice, pWeight);
        this.aLoaded = false;
    }
    
    /**
     * @return The Saved Room.
     */
    public Room getSavedRoom(){
        return this.aSavedRoom;
    }
    
    /**
     * set Saved Room.
     * @param pSavedRoom desired room 
     */
    public void setSavedRoom(final Room pSavedRoom){
        this.aSavedRoom = pSavedRoom;
    }
    /**
     * @return The beamer is Loaded or not 
     */
    public boolean isLoaded(){
        return this.aLoaded;
    }
    /**
     * set Loaded the beamer.
     * @param pLoaded Loaded or not 
     */
    public void setLoaded(final boolean pLoaded){
        this.aLoaded = pLoaded;
    }
}