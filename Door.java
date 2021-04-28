public class Door
{
    private boolean alocked;
    private Room aNeighbor;
    private String aDirection;
    
    /**
     * Constructor for objects of class Door
     * @param pDirection desared Direction
     * @param pNeighbor next room
     * @param pLocked locked or not
     */
    public Door(String pDirection, Room pNeighbor, boolean pLocked)
    {
        this.alocked = pLocked;
        this.aDirection=pDirection;
        this.aNeighbor=pNeighbor;        
    }
     
    /**
     * Locks this door.
     */
    public void lock()
    {
        this.alocked = true;
        return;
    }
    
    /**
     * Unlocks this door.
     */
    public void unlock()
    {
        this.alocked = false;
        return;
    }
    
    /**
     * 
     * @return locked or not.
     */
    public boolean getLocked()
    {
        return this.alocked;
    }
    
    /**
     * @return aDirection
     */
    public String getDirection()
    {
        return this.aDirection;
    }
    
    /**
     * @return aNeighbor
     */
    public Room getNeighbor()
    {
        return this.aNeighbor;
    }
    /**
     * @param pDirection desared Direction
     */
    public void setDirection(String pDirection)
    {
        this.aDirection = pDirection;
    }
    
    /**
     * @param pNeighbor  set room 
     */
    public void setNeighbor(Room pNeighbor)
    {
        this.aNeighbor = pNeighbor;
    }
    
}