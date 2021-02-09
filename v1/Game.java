package v1;

public class Game
{
    private Room aCurrentRoom ;
    private Parser aParser ;
    /**
     * create the game and initialise its internal map.
     */
    public Game(){
        this.createRooms(); 
        this.aParser=new Parser();
    }//game
    
    /**
     * main play routine 
     */
    public void play (){
        printWelcome();   //say welcome and give directions   
        
        boolean vFinished =false ;
       
        while (!vFinished){
            Command vuser= this.aParser.getCommand();
            vFinished = this.processCommand( vuser );
            }
        System.out.println( "Thank you for playing. Good bye." );
    }//play
    

    /**
     * create all the rooms and their exits
     */
    private void createRooms (){

        Room vOutside= new Room ("outside the main entrance of the university");
        Room vTheatre = new Room ( "in a lecture theatre" );
        Room vPub = new Room ("in the campus pub");
        Room vLab = new Room ("in a computing lab");
        Room vOffice  = new Room ("in the computing admin office");
        //creation des rooms 

        vOutside.setExits(null,vLab,vTheatre,vPub);
        vTheatre.setExits(null,null,null,vOutside);
        vPub.setExits(null,null,vOutside,null);
        vLab.setExits(vOutside,null,vOffice,null);
        vOffice.setExits(null,null,null,vLab);

        //positionnement des sorties

        this.aCurrentRoom=vOutside;
        // lieu initial 
    }//createRooms
    
    /**
     * give,choose directions/exits 
     */
    private void goRoom (final  Command pCom)
    {
        if (!pCom.hasSecondWord()) {
            System.out.println( "Go where ?" );
            return;
        }
        //where we go----> go without direction 
        
        Room vNextRoom = null;
        String vDirection = pCom.getSecondWord();
        
        
        if (vDirection.equals("north")){
            vNextRoom = this.aCurrentRoom.aNorthExit;
        }
        else if (vDirection.equals("south")){
            vNextRoom = this.aCurrentRoom.aSouthExit;
        }
        else if (vDirection.equals("west")){
            vNextRoom = this.aCurrentRoom.aWestExit;
        }
        else if (vDirection.equals("east")){
            vNextRoom = this.aCurrentRoom.aEastExit;
        }else{
            System.out.println("Unknown direction !");
        }
        this.aCurrentRoom=vNextRoom;
        String vAccum= "";
        if(this.aCurrentRoom.aNorthExit!=null)
        {
            vAccum+=" north";
        }
        else if(this.aCurrentRoom.aSouthExit!=null)
        {
            vAccum+=" south";
        }
        else if(this.aCurrentRoom.aWestExit!=null)
        {
            vAccum+=" west";
        }
        else if(this.aCurrentRoom.aEastExit!=null)
        {
            vAccum+=" east";
        }
        
        if(vNextRoom==null) {System.out.println("There is no door !");}
        else{
            System.out.println( "You are " +this.aCurrentRoom);
            System.out.print("Exits:"+vAccum);
        }
            
    }

    private void printWelcome()
    {
         String vAccum= "";
        if(this.aCurrentRoom.aNorthExit!=null)
        {
            vAccum+=" north";
        }
        else if(this.aCurrentRoom.aSouthExit!=null)
        {
            vAccum+=" south";
        }
        else if(this.aCurrentRoom.aWestExit!=null)
        {
            vAccum+=" west";
        }
        else if(this.aCurrentRoom.aEastExit!=null)
        {
            vAccum+=" east";
        }
        System.out.println("Welcome to the World of Zuul! \n World of Zuul is a new, incredibly boring adventure game.\n Type 'help' if you need help."+"You are " +this.aCurrentRoom+"\n"+"Exits:"+vAccum);
    }
    
    
    
    /**
     * print help information
     */
    private void printHelp ()
    {
        System.out.println("You are lost. You are alone. You wander around at the university.");
        System.out.println("");
        System.out.println("Your command words are:");
        System.out.println("go quit help");
    }  
    
    
    
    private boolean quit (final  Command pCom){
        if(!pCom.hasSecondWord()){
            System.out.println("Quit what ??");
        }
        return pCom.hasSecondWord() ;
    }
    
    /**
     * execute the commands 
     * @param pCom command that will be executed 
     * @return true to quit or false to continue 
     */
    private boolean processCommand  (final  Command pCom){
        boolean vReturn = false ;
        if(pCom.isUnknown())
        {
            System.out.println("I don't know what you mean..."); 
            return false;
        } 
        
        String vcomtostring = pCom.getCommandWord();
        
        if(vcomtostring.equals("quit"))
        {
            vReturn=true;
        }
        else if(vcomtostring.equals("help"))
        {
            printHelp();
        }
        else if(vcomtostring.equals("go"))
        {
            goRoom (pCom);
        }
                        
        return vReturn;
    }
    
} // Game
