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
    private String aImageName;
    private Item aItem;
    private ItemList alist;
    private HashMap<String, Door> aDoors;

    /**
     * Constructor - to create a room . 
     * 
    * @param pDescription The room's description.
    * @param pImage The room's image.
    */
    public Room(final String pDescription, final String pImage)
    {
        this.aDescription = pDescription;
        this.aExits= new HashMap<String, Room> ();
        this.aImageName = pImage;
        this.alist= new ItemList ();
        this.aDoors = new HashMap<String, Door>();
    }//Room
    
    /**
     * get the room's description.
     * 
     * @return the room's description.
     */
    public String getDescription()
    {
        return this.aDescription;
    }//getDescription
    
    /**
     * define the exits ,every direction steer to another room 
     * @param pDirection desired direction 
     * @param pNeighbor the correspondent room
     */
    public void setExits(final String pDirection,final Room pNeighbor )
    {
        this.aExits.put(pDirection, pNeighbor);
    }//setExits
    
    /**
     * set Door.
     * @param pDirection noun 
     * @param pNeighbor room 
     * @param pLocked Locked or not 
     */
    public void setDoor(String pDirection, Room pNeighbor, boolean pLocked)
    {
        Door temp = new Door(pDirection, pNeighbor, pLocked);
        this.aDoors.put(pDirection, temp);
    }
    
    
    /**
     * get the new room . 
     * @param pDirection desired direction
     * @return new room .
     */
    public Room getExit(final String pDirection)
    {
        return this.aExits.get(pDirection);
    }//getExit
    
    /**
     * add anitem in the room . 
     * @param pItem desired item
     */
    public void addItemroom  (Item pItem){
        this.getRoomItemlist().addItemToTheList(pItem.getNounItem(),pItem);
    }

    /**
     * get the room's Itemlist . 
     * @return Itemlist.
     */
    public ItemList getRoomItemlist()
    {
        return this.alist;
    }//getRoomItemlist
    
    
    /**
     * get the Exit in a String.
     * @return different exits in a String.
     */
    public String getExitString()
    {
        if (aExits.size()==0){return "there is no exits";}
        else{
            StringBuilder vReturnString = new StringBuilder("Exits: ");
            
            for ( String vS : aExits.keySet() )
                vReturnString.append( " " + vS );
            return vReturnString.toString();
        }
        
        
    }//getExitString
    
    public boolean isUnlocked (Room pDesiredRoom)
    {   
        for(String vDirection : aDoors.keySet()){
            Door vDoor = this.aDoors.get(vDirection) ;
            if(vDoor.getNeighbor().equals(pDesiredRoom)){
                return !vDoor.getLocked();
            }
        }
        return true ;
    }
    
    /**
     * get the Doors in a String.
     * @return different doors in a String.
     */
    public String getDoorString()
    {
        String vReturnString = "Doors:";
        if (aDoors.size()==0){return "there is no door in this room";}
        else{
        Set<String> keys = aDoors.keySet();
        for(String vdoor : keys)
            vReturnString += " " + vdoor;
        return vReturnString;
      }
    }
    
    /**
     * @return get Description of the room, the exits, and items .
     */
    public String getLongDescription()
    {
        return "You are "+ this.aDescription+"\n" +getExitString()+"\n" + getDoorString() +"\n"+ this.alist.getItemListStringRoom();
    }//getLongDescription
     
    /**
     * @return a string describing the room's image name
     */
     public String getImageName()
     {
         return this.aImageName;
    }
    
    /**
     * Return the room reached if we go from room A to B.
     * If there is no room in that direction, return null.
     * @param pDirection desired direction
     * @return desired door
     */
    public Room getDoor(String pDirection)
    {
        Door tempDoor = this.aDoors.get(pDirection);
        if (tempDoor != null)
        {
            return tempDoor.getNeighbor();
        }
        return null;
    }
    
    /**
     * @param pDirection desired direction
     * @return locked or not.
     */
    public boolean getLocked(String pDirection)
    {
        return this.aDoors.get(pDirection).getLocked();
    }
    
    public boolean isExit(Room pRoom)
    {
        return this.aExits.containsValue(pRoom);
    }
    
    /**
     * @param pDirection desired direction
     * @return The Actual Door.
     * 
     */
    public Door getActualDoor(String pDirection)
    {
        return this.aDoors.get(pDirection);
    }
    
}
 // Room
