import java.util.HashMap;
import java.util.Set;
 /**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds a HashMap of all rooms and their exits of the game.
 *
 * @author  Miladi Firas
 * 
 * @version 2021.02.01
 */
public class Room
{
    private String aDescription ;
    private HashMap<String, Room> aExits;
    /**/ private String aImageName;

    /**
     * Constructor - to create a room . 
     * 
    * @param The room's description.
    */
    public Room(final String pDescription/**/, final String pImage)
    {
        this.aDescription = pDescription;
        aExits= new HashMap<String, Room> ();
	/**/ this.aImageName = pImage;
        
    }//Room
    
    /**
     * get the room's description.
     * 
     * @return the room's description.
     */
    public String getDescription()
    {
        return aDescription;
    }//getDescription
    /**
     * define the exits ,every direction steer to another room 
     * @param desired direction 
     * @param the correspondent room
     */
    public void setExits(final String pdirection,final Room pneighbor )
    {
        aExits.put(pdirection, pneighbor);
    }//setExits
    
    /**
     * get the new room . 
     * @param desired direction
     * @return new room .
     */
    public Room getExit(final String pdirection)
    {
        return aExits.get(pdirection);
    }//getExit
    
    /**
     * get the Exit in a String.
     * @return different exits in a String.
     */
    public String getExitString()
    {
        StringBuilder returnString = new StringBuilder("Exits: ");
        for ( String vS : aExits.keySet() )
            returnString.append( " " + vS );
        return returnString.toString();
    }//getExitString
    
    /**
     * get the first word of the command
     * @return the first word of the command from the user.
     */
    public String getLongDescription()
    {
        return "You are "+ this.aDescription+"\n" +getExitString();
    }//getLongDescription
    /**
     * Return a string describing the room's image name
     */
    /**/ public String getImageName()
    /**/ {
    /**/     return this.aImageName;
    /**/ }
}
 // Room
