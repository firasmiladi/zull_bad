import java.util.Stack;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import javax.swing.Timer;  
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

//import java.applet.AudioClip;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class is used to control the game .
 * 
 * @author Firas miladi  
 * @version 30/04/2020
 */
public class GameEngine implements ActionListener
{
    private Parser          aParser;
    private UserInterface   aGui;
    private Stack<Room>     aBackRooms;
    private Player          aPlayer;
    private int             aCount;
    private Timer           atimer;
    private int             aTimeLimit;
    private int             aLife;
    private HashMap<String, Room> aRooms;
    private boolean aTestMode;
    private boolean aLoser;
    private RoomRandomizer aRandom;
    //private AudioClip son;
        

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.aPlayer= new Player ("ghost",5,5);
        this.aRooms = new HashMap<String, Room> ();
        
        this.aLife = 3;
        this.createRooms();
        this.aBackRooms=new Stack<Room>();
        this.aCount= 60; 
        this.aTimeLimit = 300000;
        this.atimer = new Timer(aTimeLimit, this);
        this.atimer.start();
        this.aTestMode=false;
        
    }
    

    /**
     * Actionlistener interface .
     */
    public void actionPerformed(ActionEvent pevent){
        if(pevent.getSource() == this.atimer){
            this.aGui.println("Time is over");
            quit();
            ((Timer)(pevent.getSource())).stop();
        }  
    }

    /**
     * creation of the gui.
     * @param pUserInterface UserInterface
     */
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
        /*SoundEffect welcome = new SoundEffect ("son/welcome.mp3");
        welcome.play();*/
        this.aGui.print( "\n" );
        this.aGui.println( "Attention please !" );
        this.aGui.println( "Wuhan is being attacked by a very dangerous virus . \n" );
        this.aGui.println( "Your mission is to save your family and to go back to your home as quickly as possible ." );
        this.aGui.println( "You have 5 min to." );

        this.aGui.println( "Type "+CommandWord.HELP+" if you need help." );
        this.aGui.print( "\n" );
        this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
        if ( this.aPlayer.getCurrentRoom().getImageName() != null )
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
    }

    
    /**
     * create Rooms
     */
    private void createRooms ()
    {

        Room vHouse= new Room ("at your home", "home.JPG");       
        aRooms.put("home",vHouse);

        Room vPark1 = new Room ( "in the ZiYang park ", "ZiYang_park.JPG" );
        aRooms.put("ziyang_park",vPark1);

        Room vHospital = new Room ("in the Hospital of Wuhan University ", "Hospital.JPG");
        aRooms.put("hospital",vHospital);

        Room vHospitale1 = new Room ("in the surgical operating room on the second floor ", "neuro-surgery-operation.JPG");        
        aRooms.put("surgical_operating_room",vHospitale1);

        Room vLab = new Room ("outside the screening laboratory ", "screening.JPG");
        aRooms.put("laboratory",vLab);

        Room vOffice  = new Room ("in Shanghai Development Bank", "Bank.JPG");
        aRooms.put("bank",vOffice);

        Room vOffice1  = new Room ("in the computing admin office on the second floor ", "office.JPG");
        aRooms.put("office",vOffice1);

        Room vDistract1 = new Room ( "in Xujiapeng District", "Xujiapeng_District.JPG" );
        aRooms.put("xujiapeng_district",vDistract1);

        Room vDistract2=new Room ( "in Wuchang District", "wchangDistrict.JPG" );
        aRooms.put("wuchang_district",vDistract2);

        Room vDowntown= new Room ( "in front of the Yellow Crane Tower", "Tower.JPG" );
        aRooms.put("yellow_crane_tower",vDowntown);

        Room vSchool1= new Room ( "in Wuhan Qiaokou Elementary School ", "School.JPG" );
        aRooms.put("elementary_School",vSchool1);

        Room vSchool1e1= new Room ( "in the classroom of your son", "classroom.JPG" );
        aRooms.put("classroom",vSchool1e1);

        Room vSchool2= new Room ( "in Wuhan Conservatory of Music", "Conservatory.JPG" );
        aRooms.put("music",vSchool2);

        Room vPark2 = new Room ( "in Hongshan swimming park" , "swimming.JPG");
        aRooms.put("swimming_park",vPark2);

        Room vSupermarket = new TransporterRoom ( "in Tesco Supermarket" , "Tesco.JPG", this.aRooms );
        aRooms.put("Supermarket",vSupermarket);

        Room vPolice = new Room ( "Next to the Police station", "Police.JPG" );

        aRooms.put("police_station",vPolice);
        //creation des items
        vOffice.addItemroom(new Item ("cookie","magic cookie",1,0.5));

        vOffice.addItemroom(new Beamer ("beamer","( this item is used for teleportation just load it first!)",1,0.5));

        vOffice.addItemroom(new Item ("gel","antibacterial hydroalcoholic gel",1,0.5));
        vOffice.addItemroom(new Item ("gold","banch of gold on the ground",0,0));

        vPark1.addItemroom(new Item ("wipes","Hand and surface disinfectant wipes",2,1));

        vHouse.addItemroom (new Item ("gold","banch of gold on the ground",0,0));

        vHospital.addItemroom (new Item ("disinfectant","Disinfectant",2,2.5));
        vHospital.addItemroom (new Item ("shields","Protective Face shields",5,3));
        vHospital.addItemroom (new Item ("ffp2","Protective Visor",2,3));
        
        this.aRandom=new RoomRandomizer (new ArrayList<Room>(this.aRooms.values()));
        
        Room vRandom1 = this.aRandom.nextRoom();
        vRandom1.addItemroom (new Item ("Sarah","you find your wife. Save her ! "));
        

        vLab.addItemroom (new Item ("gloves","Latex gloves",3,2));

        vDowntown.addItemroom (new Item ("gold","banch of gold on the ground",0,0));
        vDowntown.addItemroom (new Item ("key","a random magic key",2,0));

        vSchool1.addItemroom (new Item ("masks","Surgical masks for children",1,1));
        
        
        Room vRandom2 = this.aRandom.nextRoom();
        vRandom2.addItemroom (new Item ("Alexa","you find your daughter. Save her ! "));

        
        vSchool2.addItemroom (new Item ("overcoat","Polypropylene overcoat",4,4));

        vPark2.addItemroom (new Item ("gold","banch of on the ground",0,0));

        vSupermarket.addItemroom (new Item ("gold","banch of on the ground",0,0));

        vPolice.addItemroom (new Item ("overshoes","Overshoes ",1,4));
        vPolice.addItemroom(new Item ("shields","Face shields",2,2));

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
        vLab.setDoor("portal",vSchool2,true);

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

        //vPark2.setExits("north",vDowntown); trap door 
        
        vPark2.setExits("east",vSchool2);
        vPark2.setExits("west",vSupermarket);

        vSchool2.setExits("north",vDistract1);
        vSchool2.setExits("west",vPark2);
        vSchool2.setDoor("portal",vLab,true);

        vHospital.setExits("up",vHospitale1);
        vHospitale1.setExits("down",vHospital);

        vOffice.setExits("up",vOffice1);
        vOffice1.setExits("down",vOffice);

        vSchool1.setExits("up",vSchool1e1);
        vSchool1e1.setExits("down",vSchool1);

        //positionnement des sorties
        this.aPlayer.setCurrentRoom(vOffice);
        // lieu initial 
    }//createRooms

    /**
     * print current location 
     */
    private void look()
    {

        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        printCar();
    }//look

    /**
     * print not hungry any more message
     * @param pCommand desired food 
     */
    private void eat(final Command pCommand)
    {
        if ( !pCommand.hasSecondWord() ) {
            this.aGui.println("\n" + "what do you want to eat?" + "\n");
            return;
        }

        String vCommandWord = pCommand.getSecondWord();

        this.aGui.println(this.aPlayer.eat(vCommandWord));    
    }//eat

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     * @param pString user command 
     */
    public void interpretCommand( final String pString) 
    {
        if (this.aCount==0 || this.aLoser){
            this.aGui.boutonvisibility(false, false, false, false, false, false);
            return;
        }
        this.aGui.println( "\n > " + pString );

        Command vCommand = this.aParser.getCommand(pString);
        CommandWord vCommandWord = vCommand.getCommandWord();

        switch ( vCommandWord ){ 
            case HELP: this.printHelp(); 
            break; 
            case GO:this.goRoom( vCommand );
            break; 
            case EAT: this.eat( vCommand ); 
            break; 
            case LOOK: this.look(); 
            break; 
            case QUIT: this.quit(); 
            break; 
            case TEST: this.test(vCommand); 
            break;
            case TAKE: this.take(vCommand); 
            break;
            case BACK:this.Back();
            break;
            case DROP: this.drop(vCommand); 
            break; 
            case ITEMS: this.printInventory(); 
            break;
            case LOAD: this.load(vCommand); 
            break;
            case TELEPORT: this.teleport(); 
            break;
            case LOCK: this.lockDoor(vCommand); 
            break;
            case UNLOCK: this.unlockDoor(vCommand); 
            break;
            case UNKNOWN: this.aGui.println( "I don't know what you mean..." ); 
            break;
            case ALEA: this.alea(vCommand); 
            break;
        }

        if (--this.aCount==0){quit();}
    }

    /**
     * print items in the inventory
     */
    private void printInventory() {
        this.aGui.println("\n items  :\n"+this.aPlayer.getItemListStringforPlayer()+"\n total weight : "   + this.aPlayer.getWeightInventory() +" Kg"        
            +"\n money in your wallet : "   + this.aPlayer.getwallet() +" Yuan \n");
    }
    
    /**
     * print items in the inventory
     */
    private void printCar() {
        this.aGui.println("\n"+this.aPlayer.getNonPlayerlist()+"\n");
        
    }
    

    /**
     * go back
     */
    private void Back(){
        if(aBackRooms.empty()){this.aGui.println("you can't go back");}

        else{
            //
            if (this.aPlayer.getCurrentRoom().isUnlocked(this.aBackRooms.peek())    && this.aPlayer.getCurrentRoom().isExit(this.aBackRooms.peek()))
                {
                 this.aPlayer.setCurrentRoom(this.aBackRooms.pop());
                 this.printLocationInfo();
                }   
                else{this.aGui.println("you can't go back");}
            }
    }
    

    /**
     * take item
     * @param pCommand item 's noun
     */
    private void take(final Command pCommand) {
        if ( !pCommand.hasSecondWord() ) {
            this.aGui.println("\n" + "what do you want to take?" + "\n");
            return;
        }
        String vCommandWord = pCommand.getSecondWord();
        Item vItem = this.aPlayer.getCurrentRoom().getRoomItemlist().getItemList(vCommandWord);
        if(vItem==null){
            this.aGui.println("\n" + "there is an item called " +vCommandWord+ "\n");
            return;
        }
        
        if (vCommandWord.equals("gold")){
            String vCommand = pCommand.getSecondWord();
            this.aGui.println("\n"+this.aPlayer.takeString(vCommand));
            this.aGui.println("\n"+this.aPlayer.getCurrentRoom().getRoomItemlist().getItemListStringRoom());
            return ;
          }
        if(!vItem.getNonPlayer()){
                if(vItem.getWeightItem()+this.aPlayer.getWeightInventory()<=this.aPlayer.getOneRepMax() && vItem.getPriceItem()<=this.aPlayer.getwallet()) {
                    String vCommand = pCommand.getSecondWord();
                    this.aGui.println("\n"+this.aPlayer.takeString(vCommand));
                    this.aGui.println("\n"+this.aPlayer.getCurrentRoom().getRoomItemlist().getItemListStringRoom());
        
                }
                else if(vItem.getWeightItem()+this.aPlayer.getWeightInventory()<=this.aPlayer.getOneRepMax() && vItem.getPriceItem()>this.aPlayer.getwallet()) {
                    this.aGui.println("it's too expensive man!\n you don't have enough money to pick this item");
                    return;
        
                }
        
                else if(vItem.getWeightItem()+this.aPlayer.getWeightInventory()>this.aPlayer.getOneRepMax() && vItem.getPriceItem()<=this.aPlayer.getwallet()) {
                    this.aGui.println("it's too heavy man!\n you don't have enough power to pick this item");
                    return;
        
                }
                else if(vItem.getWeightItem()+this.aPlayer.getWeightInventory()>this.aPlayer.getOneRepMax() && vItem.getPriceItem()>this.aPlayer.getwallet()) {
                    this.aGui.println("it's too heavy and too expensive man!\n Try to pick another item");
                    return;
        
                }
        }
        else if (vItem.getNonPlayer()){
            this.aPlayer.takeString(vCommandWord);

            printCar();
        }
    }

    /**
     * drop item
     * @param pCommand desired item to drop
     */
    private void drop(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("\n What do you want to drop? \n");
            return;
        }
        String vCommand = pCommand.getSecondWord();
        this.aGui.println(this.aPlayer.dropString(vCommand));
        this.aGui.println(this.aPlayer.getCurrentRoom().getRoomItemlist().getItemListStringRoom());
    }

    /**
     * give,choose directions/exits 
     * @param pCommand desired direction 
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
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit( vDirection );
        Room nextDoor = aPlayer.getCurrentRoom().getDoor(vDirection);
        
        
        if (nextDoor != null)
        {
            if (aPlayer.getCurrentRoom().getLocked(vDirection))
            {
                this.aGui.println("The door is locked! \n You need to unlocked it \n First you need to find the key !");
                return;
            }
            else
            {
                
                this.aBackRooms.push(this.aPlayer.getCurrentRoom());
                aPlayer.setCurrentRoom(nextDoor);

                //this.aGui.println(aPlayer.getCurrentRoom().getLongDescription());
                this.printLocationInfo();   
                if ( this.aPlayer.getCurrentRoom().getImageName() != null )
                {
                    this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
                    return;
                }
            }

        }
        else if ( vNextRoom != null )
        {
            this.aBackRooms.push(this.aPlayer.getCurrentRoom()); 
            this.aPlayer.setCurrentRoom(vNextRoom);
             if(this.aPlayer.getCurrentRoom()==this.aRooms.get("home")&&this.aPlayer.checkWinner())
             { this.aGui.println("Congruatulation! \n you are a Winner! \n you return to your home safe and you saved your family! ");
                 //return;
        }   else if(this.aPlayer.getCurrentRoom()==this.aRooms.get("home")&&!this.aPlayer.checkWinner()&&this.aLife>0)
        { 
          this.aGui.boutonvisibility(false, true, false, false, false, false);
            this.aGui.println("No!\n you're in your home while your family is in danger \n you can't abandon Your Family \n you have just "+this.aLife+" chance to rescue your family and return to your home safe" );
          this.aLife--;
          //return;
        }   else if(this.aPlayer.getCurrentRoom()==this.aRooms.get("home")&&!this.aPlayer.checkWinner()&& this.aLife==0)
        { 
          this.aGui.println("You lost! \n you return to your house and you did abandon Your Family!" );
          quit();
          this.aLoser=true;
          //return;
        }else printLocationInfo();
            
            
            if ( this.aPlayer.getCurrentRoom().getImageName() != null )
                this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );

        }
    }
    
    

    /**
     * lock Door
     * @param pCommand item 's noun
     */
    private void lockDoor(Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("Lock what?");
            return;
        }

        String vDesiredDoor = pCommand.getSecondWord();

        if (aPlayer.checkKeyDoor())
        {    
            aPlayer.getCurrentRoom().getActualDoor(vDesiredDoor).lock();
            this.aGui.println("Door locked");
        }
        else
        {
            this.aGui.println("You don't have a key!");
        }
    }

    /**
     * unlock Door
     * @param pCommand item 's noun
     */
    private void unlockDoor(Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("Unlock what?");
            return;
        }

        String vDesiredDoor = pCommand.getSecondWord();

        if (aPlayer.checkKeyDoor())
        {    
            aPlayer.getCurrentRoom().getActualDoor(vDesiredDoor).unlock();
            this.aGui.println("Door unlocked");
        }
        else
        {
            this.aGui.println("You don't have a key!");
        }

    }

    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     */
    private void quit()
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
        this.aGui.println( "Your command words are: ");
        this.aGui.println(this.aParser.showCommands());
    }

    /**
     * print Location Information
     */

    private void printLocationInfo()
    {
        this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription());
        printCar();
        if(this.aPlayer.getCurrentRoom().equals(aRooms.get("Supermarket"))){this.aGui.boutonvisibility(false,false,true,true,false,false);}
        else{this.aGui.boutonvisibility(this.aPlayer.getCurrentRoom());}
        
        
        if ( this.aPlayer.getCurrentRoom().getImageName() != null )
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
    }//printLocationInfo

    /**
     * test document 
     * @param pCommand desired document to open
     */
    private void test(Command pCommand){
        if(!pCommand.hasSecondWord()){
            this.aGui.println("What file do you want to use? ");
            return;
        }else{
            String vFichier = pCommand.getSecondWord();
            Scanner vScan = new Scanner(this.getClass().getClassLoader().getResourceAsStream("./"+vFichier+".txt") ); 
            String vSecond = vScan.nextLine();
            this.aTestMode=true;
            
            do{
                interpretCommand(vSecond);
                vSecond=vScan.nextLine();
            }while(vScan.hasNextLine());                              
        }
        this.aTestMode=false;

    }  

    /**
     * set beamer.
     * @param pCommand desired room 
     */
    private void load(final Command pCommand){
        if(!pCommand.hasSecondWord()){
            this.aGui.println("\n" + "What do you want to load ?");
            return;
        }
        this.aGui.println(this.aPlayer.load(pCommand));
    }

    /**
     * teleport.
     */
    private void teleport (){
        Item vItem =this.aPlayer.getPlayerItemlist().getItemList("beamer");

        if (vItem == null) {
            this.aGui.println("\n You don't own a beamer");
        }
        else{
            Beamer vbeamer=(Beamer) vItem;
            if(!vbeamer.isLoaded()){
                this.aGui.println("The teleporter wasn't loaded.");
            }else{
                this.aPlayer.setCurrentRoom(vbeamer.getSavedRoom());
                this.aGui.println("\n you have been teleported to "+this.aPlayer.getCurrentRoom().getLongDescription());
                this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
            }
        }

    }
    
    /**
     * choose the destination (TEST MODE ONLY)
     * @param pCommand room name
     */
    private void alea (final Command pCommand){
        TransporterRoom vTRoom = (TransporterRoom) this.aRooms.get("Supermarket");
        
        if(pCommand.hasSecondWord()){
            if (this.aTestMode){vTRoom.setAlea(pCommand.getSecondWord());}
            else{this.aGui.println("you can not use this command if you are not on test mode");}
        }else{
            if (this.aTestMode){vTRoom.setAlea(null);}
            else{this.aGui.println("you can not use this command if you are not on test mode");}
        }
    }
}
// GameEngine

