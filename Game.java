
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class create the game and initialise its internal map.
 *
 * @author  Miladi Firas
 * 
 * @version 2021.02.01
 */
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
    public void play ()
    {
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
    private void createRooms ()
    {

        Room vHouse= new Room ("at your home");
        Room vPark1 = new Room ( "in the ZiYang park " );
        
        Room vHospital = new Room ("in the Hospital of Wuhan University ");
        Room vHospitale1 = new Room ("in the surgical operating room on the second floor ");        

        Room vLab = new Room ("outside the screening laboratory ");
        
        Room vOffice  = new Room ("in Shanghai Development Bank");
        Room vOffice1  = new Room ("in the computing admin office on the second floor ");
        
        
        Room vDistract1 = new Room ( "in Xujiapeng District" );
        
        Room vDistract2=new Room ( "in Wuchang District" );

 
        Room vDowntown= new Room ( "in front of the Yellow Crane Tower" );
        
        Room vSchool1= new Room ( "in Wuhan Qiaokou Elementary School " );
        Room vSchool1e1= new Room ( "in the classroom of your son" );
        
        Room vSchool2= new Room ( "in Wuhan Conservatory of Music" );
        
        Room vPark2 = new Room ( "in Hongshan swimming park" );


        
        Room vSupermarket = new Room ( "in Tesco Supermarket" );
        
        Room vPolice = new Room ( "Next to the Police station" );

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
        System.out.println(this.aCurrentRoom.getLongDescription());
    }//look
    
    /**
     * print not hungry any more message
     */
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more.");    
    }//eat
    
    /**
     * give,choose directions/exits 
     * @param desired direction 
     */
    private void goRoom (final  Command pCom)
    {
        if (!pCom.hasSecondWord()   ) {
            System.out.println( "Go where ?" );
            return;
        }
        //where we go----> go without direction 

        String vDirection = pCom.getSecondWord();
        
        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);
        
        this.aCurrentRoom=vNextRoom;
        
        printLocationInfo(); 
    }//goRoom

    /**
     * print welcome and propose help 
     */
    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        printLocationInfo();
    }//printWelcome

    
    /**
     * print help information
     */
    private void printHelp ()
    {
        System.out.println("You are lost. You are alone. You wander around at the university.");
        System.out.println("");
        System.out.println("Your command words are:");
        aParser.showCommands();
    }  //printHelp

    /**
     * quit or not 
     * @param pCom command that will be executed 
     * @return true to quit or false to continue 
     */
    private boolean quit (final  Command pCom){
        if(!pCom.hasSecondWord()){
            System.out.println("Quit what ??");
        }
        return pCom.hasSecondWord() ;
    }//quit

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
        
        //the command is unknown

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
        }else if(vcomtostring.equals("look"))
        {
            look();
        }
        else if(vcomtostring.equals("eat"))
        {
            eat();
        }

        return vReturn;
        // execute the commands
    }//processCommand
    /**
     * print Location Information
     */

    private void printLocationInfo()
    {
        System.out.println("You are "+this.aCurrentRoom.getDescription());
        System.out.print(this.aCurrentRoom.getExitString());
        System.out.println();
    }//printLocationInfo

} // Game
