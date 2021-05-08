import java.util.HashMap;
import java.util.ArrayList;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class is used to trait Transporter Room .
 * 
 * @author Firas miladi  
 * @version 30/04/2020
 */
public class TransporterRoom extends Room{
    private HashMap<String,Room> aRooms;
    private ArrayList<Room> aArrayInRooms;
    private RoomRandomizer aRandomRoom;
    private String aAleaString;
    /**
     * Constructor
     * @param pDescription :get the room Description
     * @param pImage : get the room image .
     * @param pRooms : get table of rooms .
     */
    public TransporterRoom(final String pDescription,final String pImage, final HashMap<String,Room> pRooms)
    {
        super(pDescription, pImage);
        this.aRooms = pRooms;
        this.aArrayInRooms = new ArrayList<Room>(this.aRooms.values());
        this.aRandomRoom = new RoomRandomizer(this.aArrayInRooms);
        this.aAleaString = null ;
    }
    
    /**
     * @param pDirection direction exit
     * @return get a room randomly .
     */
    @Override 
    public Room getExit(final String pDirection)
    {
        if(this.aAleaString==null){return this.findRandomRoom();}
        else{return aRooms.get(aAleaString);}
    }
    
    /**
     * choose the destination (TEST MODE ONLY)
     * @param pRoom room name
     */
    public void setAlea(final String pRoom)
    {
        this.aAleaString = pRoom ;
    }
    
    /**
     * @return get a room randomly .
     */
    private Room findRandomRoom () {
        return this.aRandomRoom.nextRoom();
    }
} // TransporterRoom
