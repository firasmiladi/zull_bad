import java.util.Stack;
/**
 * Decrivez votre classe GameEngine ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class GameEngine
{
    private Parser        aParser;
    private Room          aCurrentRoom;
    private UserInterface aGui;
    private Stack<Room>   aBackRooms;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.createRooms();
        this.aBackRooms=new Stack<Room>();
    }

    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        this.aGui.print( "\n" );
        this.aGui.println( "Welcome to the World of Zuul!" );
        this.aGui.println( "World of Zuul is a new, incredibly boring adventure game." );
        this.aGui.println( "Type 'help' if you need help." );
        this.aGui.print( "\n" );
        this.aGui.println( this.aCurrentRoom.getLongDescription() );
        if ( this.aCurrentRoom.getImageName() != null )
            this.aGui.showImage( this.aCurrentRoom.getImageName() );
    }

    private void createRooms ()
    {

        Room vHouse= new Room ("at your home", "outside.gif");
        Room vPark1 = new Room ( "in the ZiYang park ", "outside.gif" );
        
        Room vHospital = new Room ("in the Hospital of Wuhan University ", "outside.gif");
        Room vHospitale1 = new Room ("in the surgical operating room on the second floor ", "outside.gif");        

        Room vLab = new Room ("outside the screening laboratory ", "outside.gif");
        
        Room vOffice  = new Room ("in Shanghai Development Bank", "outside.gif");
        Room vOffice1  = new Room ("in the computing admin office on the second floor ", "outside.gif");
        
        
        Room vDistract1 = new Room ( "in Xujiapeng District", "outside.gif" );
        
        Room vDistract2=new Room ( "in Wuchang District", "outside.gif" );

 
        Room vDowntown= new Room ( "in front of the Yellow Crane Tower", "outside.gif" );
        
        Room vSchool1= new Room ( "in Wuhan Qiaokou Elementary School ", "outside.gif" );
        Room vSchool1e1= new Room ( "in the classroom of your son", "outside.gif" );
        
        Room vSchool2= new Room ( "in Wuhan Conservatory of Music", "outside.gif" );
        
        Room vPark2 = new Room ( "in Hongshan swimming park" , "outside.gif");


        
        Room vSupermarket = new Room ( "in Tesco Supermarket" , "outside.gif");
        
        Room vPolice = new Room ( "Next to the Police station", "outside.gif" );
        
        Item vgel = new Item ("gel",12,0.5);
        vOffice.setItem (vgel);
        
        //creation des rooms 
        vHouse.setExits("south",vPark1);
         
        
        vPark1.setExits("east",vHospital);
        vPark1.setExits("west",vLab);
        vPark1.setExits("north",vHouse);
        vPark1.setExits("south",vDowntown);
        
        
        vDowntown.setExits("north",vPark1);
        vDowntown.setExits("south",vPark2);
        vDowntown.setExits("east",vDistract1);
        vDowntown.setExits("west",vDistract2);
        
        
        vHospital.setExits("west",vPark1);
        vHospital.setExits("south",vDistract1);
        
        
        vLab.setExits("east",vPark1);
        vLab.setExits("south",vDistract2);
        
        
        vOffice.setExits("west",vDistract1);
        
        
        vDistract1.setExits("north",vHospital);
        vDistract1.setExits("south",vSchool2);
        vDistract1.setExits("east",vOffice);
        vDistract1.setExits("west",vDowntown);
        
        
        vDistract2.setExits("north",vLab);
        vDistract2.setExits("south",vPolice);
        vDistract2.setExits("east",vDowntown);
        vDistract2.setExits("west",vSchool1);
        
        
        vSchool1.setExits("east",vDistract2);
        
        
        vPolice.setExits("north",vDistract2);
        vPolice.setExits("east",vSupermarket);
        
        
        vSupermarket.setExits("west",vPolice);
        vSupermarket.setExits("east",vPark2);
        
        
        vPark2.setExits("north",vDowntown);
        vPark2.setExits("east",vSchool2);
        vPark2.setExits("west",vSupermarket);
        
        
        vSchool2.setExits("north",vDistract1);
        vSchool2.setExits("west",vPark2);

        
        vHospital.setExits("up",vHospitale1);
        vHospitale1.setExits("down",vHospital);
        
        
        vOffice.setExits("up",vOffice1);
        vOffice1.setExits("down",vOffice);
        
        
        vSchool1.setExits("up",vSchool1e1);
        vSchool1e1.setExits("down",vSchool1);
        
        
        //positionnement des sorties

        this.aCurrentRoom=vOffice;
        // lieu initial 
    }//createRooms
    
    /**
     * print current location 
     */
    private void look()
    {

        this.aGui.println(this.aCurrentRoom.getLongDescription());
    }//look
    
    /**
     * print not hungry any more message
     */
    private void eat()
    {
       this.aGui.println("You have eaten now and you are not hungry any more.");    
    }//eat
    
    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand( final String pCommandLine ) 
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "help" ) )
            this.printHelp();
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom( vCommand );
        else if ( vCommandWord.equals( "eat" ) )
            this.eat(  );
        else if ( vCommandWord.equals( "look" ) )
            this.look();
        else if ( vCommandWord.equals( "quit" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else
                this.endGame();
        }
        if ( vCommandWord.equals( "back" ) ){
            this.Back();
        }
    }
    
    public void Back(){
        //if(aBackRooms.empty()){}
        this.aCurrentRoom=this.aBackRooms.pop();
        this.printLocationInfo();   
        if ( this.aCurrentRoom.getImageName() != null )
                this.aGui.showImage( this.aCurrentRoom.getImageName() );
        
    }
    
    /**
     * give,choose directions/exits 
     * @param desired direction 
     */
    private void goRoom( final Command pCommand ) 
    {
        if ( ! pCommand.hasSecondWord() ) {
            // if there is no second word, we don't know where to go...
            this.aGui.println( "Go where?" );
            return;
        }

        String vDirection = pCommand.getSecondWord();

        // Try to leave current room.
        Room vNextRoom = this.aCurrentRoom.getExit( vDirection );
          
        if ( vNextRoom == null )
            this.aGui.println( "There is no door!" );
        else {
            this.aBackRooms.push(this.aCurrentRoom); 
            this.aCurrentRoom = vNextRoom;
            //this.aGui.println( this.aCurrentRoom.getLongDescription() );
            this.printLocationInfo();   
            if ( this.aCurrentRoom.getImageName() != null )
                this.aGui.showImage( this.aCurrentRoom.getImageName() );
        }
    }
    
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }

    /**
     * print help information
     */

    private void printHelp() 
    {
        this.aGui.println( "You are lost. You are alone. You wander" );
        this.aGui.println( "around at Wuhan city." + "\n" );
        this.aGui.println( "Your command words are: "  + this.aParser.getCommandList());
        
    }
    
    /**
     * print Location Information
     */

    private void printLocationInfo()
    {
        this.aGui.println("You are "+this.aCurrentRoom.getDescription());
        this.aGui.print(this.aCurrentRoom.getExitString());
        this.aGui.println("");
        this.aGui.boutonvisibility(this.aCurrentRoom);
        if ( this.aCurrentRoom.getImageName() != null )
            this.aGui.showImage( this.aCurrentRoom.getImageName() );
    }//printLocationInfo
} // GameEngine
