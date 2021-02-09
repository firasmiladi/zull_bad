 public class Room
{
    private String aDescription ;
    public Room aNorthExit;
    public Room aSouthExit;
    public Room aEastExit;
    public Room aWestExit;

    
    public Room( final String pRoom)
    {
        this.aDescription = pRoom;
    }
    
    public String getDescription()
    {
        return this.aDescription;
    }
    
    public void setExits (final Room pNorth,final Room pSouth,final Room pEast
    ,final Room pWest)
    {   
        this.aNorthExit=pNorth;
        this.aSouthExit=pSouth;
        this.aEastExit=pEast;
        this.aWestExit=pWest;
    }
    
} // Room
