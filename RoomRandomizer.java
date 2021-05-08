import java.util.Random;
import java.util.ArrayList;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class is used to generate randomly a room.
 * 
 * @author Firas miladi  
 * @version 30/04/2020
 */
public class RoomRandomizer { 
    private Random aRandom;
    private ArrayList<Room> aArray; 
     /**
     * Constructor
     * @param pRoom : get an array of all rooms
     */
    public RoomRandomizer(final ArrayList pRoom)
    {
        this.aRandom = new Random();
        this.aArray = new ArrayList<Room>(pRoom);
    } 
    /**
     * @return a room choosen randomly  .
     */
    public Room nextRoom()
    {
        int vRandom = aRandom.nextInt(aArray.size());
        
        return this.aArray.get(vRandom);
    }

}