import java.util.HashMap;
import java.util.Set;
public class Player{
    private Room          aCurrentRoom;
    private String        aName;
    private double        aOneRepMax;
    private double        aMoney;
    private ItemList      aCatalogue;
    private double        aWeightInventory ;
    
    /**
     * Constructor
     * @param pName : get player's Name  .
     * @param pMoney : get player's Money .
     * @param pWeight : get player's One Rep Max .
     */
    public Player(String pName,double pMoney,double pWeight){
        this.aName = pName; 
        this.aMoney=pMoney;
        this.aOneRepMax = pWeight;        
        this.aCatalogue = new ItemList();
        this.aWeightInventory = 0.0;
    }
    
    /**
     * @return get money in the wallet.
     */
    public double getwallet(){
        return this.aMoney;
    }
    
    /**
     * print the items in the HachMap(player)
     * @return String with the items from the HachMap.
     */
    public String getItemListStringforPlayer()
    {
        return this.aCatalogue.getItemListStringPlayer();
    }
    
    /**
     * @return get player's One Rep Max.
     */
    public double getOneRepMax(){
        return this.aOneRepMax;
    }
    
    /**
     * @return get player's Current Room.
     */
    public Room getCurrentRoom(){
        return this.aCurrentRoom;
    }
    
    /**
     * set Current Room.
     * @param pRoom desired room 
     */
    public void setCurrentRoom(Room pRoom){
        this.aCurrentRoom=pRoom;
    }
    
    
    /**
     * @return weight of the Inventory.
     */
    public double getWeightInventory(){
        return aWeightInventory;
    }
    
    /**
     * @return get ItemList .
     */
    public ItemList getPlayerItemlist()
    {
        return this.aCatalogue;
    }//getRoomItemlist
    
    /**
     * @return has the key or not 
     */
    public boolean checkKeyDoor()
    {
        Set<String> vkeys = aCatalogue.getItemListhash().keySet();
        for(String item : vkeys)
            if (item.equals("key"))
                return true;
        return false;
    }
    
    /**
     * double player's One Rep Max.
     */
    public void doubleForce () { 
        this.aOneRepMax=this.aOneRepMax*2;
    }
    
    /**
     * print not hungry any more message
     * @param pNoun :get food noun
     * @return String what did he eat  .
     */
    public String eat(final String pNoun)
    {
        Item vItemdrop = this.aCatalogue.getItemList(pNoun);
        
        if(vItemdrop!=null){
            if(pNoun.equals("cookie")){
                this.doubleForce();
                this.removeItemEatableInventory(pNoun);
                return "You have eaten now a super cookie you are stronger than ever. keep up champ !."; 
            }
        }else{return "You have not a "+ pNoun +" in your inventory keep searching ! ";}
        
        return "You have eaten now and you are not hungry any more.";    
    }//eat

    
    /**
     * add item to your inventory
     * @param pNoun Item noun
     * @param pItem Item 
     */
    public void addItemInventory (final String pNoun,final Item pItem) { 
        if(pNoun.equals("gold")){
            this.aMoney=this.aMoney+2;
       }
        else{
            this.aCatalogue.addItemToTheList(pNoun,pItem); 
            this.aWeightInventory = this.aWeightInventory+pItem.getWeightItem();
            this.aMoney = this.aMoney-pItem.getPriceItem();
       }
    }
    
    /**
     * remove Eatable item  from your inventory
     * @param pNounItem Item noun
     */
    public void removeItemEatableInventory (final String pNounItem) { 
        Item vItemdrop = this.aCatalogue.getItemList(pNounItem);
        this.aCatalogue.removeItemFromTheList(pNounItem);
        this.aWeightInventory= this.aWeightInventory-vItemdrop.getWeightItem();
    }
    
    /**
     * remove item from your inventory
     * @param pNounItem Item noun
     */
    public void removeItemInventory (final String pNounItem) { 
        Item vItemdrop = this.aCatalogue.getItemList(pNounItem);
        this.aCatalogue.removeItemFromTheList(pNounItem);
        this.aWeightInventory= this.aWeightInventory-vItemdrop.getWeightItem();
        this.aMoney= this.aMoney + vItemdrop.getPriceItem();
    }
     
    /**
     * @param pCommand item 's noun
     * @return String of items that has been token .
     */
    public String takeString(String pCommand){
        Item vItemRoom = this.getCurrentRoom().getRoomItemlist().getItemList(pCommand);
        if (vItemRoom == null) {
            return "\n there is no such as this thing in this room ";
            
        }else {
            if (pCommand.equals("gold")){
        
            this.addItemInventory(pCommand,vItemRoom);
            this.getCurrentRoom().getRoomItemlist().removeItemFromTheList(pCommand);
            return "You added a " + vItemRoom.getDescriptionItem() + " to your wallet  ";
            
           } 
           else {
           this.addItemInventory(pCommand,vItemRoom);
            this.getCurrentRoom().getRoomItemlist().removeItemFromTheList(pCommand);
            return "You added a " + vItemRoom.getDescriptionItem() + " to your inventory  ";
          }
       }
    }
    
    /**
     * @param pCommand item 's noun
     * @return String of items that has been drop in .
     */
    public String dropString (String pCommand) {
        Item vItemdrop = this.aCatalogue.getItemList(pCommand);
        if (vItemdrop == null) {
            return "\n You don't own this item";
        }
        else {
            this.removeItemInventory(pCommand);
            this.getCurrentRoom().getRoomItemlist().addItemToTheList(vItemdrop.getNounItem(),vItemdrop);
            return "You threw a " + vItemdrop.getDescriptionItem() + " from your inventory ";
        }
    }
    
    
    /**
     * @param pCommand item 's noun
     * @return String beamer loaded .
     */
    public String load(final Command pCommand){
       Room vRoomLoad = this.getCurrentRoom(); 
       Item vItem = this.getPlayerItemlist().getItemList("beamer");
       if(pCommand.getSecondWord().equals("beamer")){
              if(vItem == null){return "\n" + "You don't have the teleporter with you.";} 
              else{
               Beamer vbeamer=(Beamer) vItem;
               vbeamer.setSavedRoom(vRoomLoad);
               vbeamer.setLoaded(true);
               return "you just load the beamer";
              }
              
       }else{
                return "you can't charge this item";
       }
       
    }
    
    
    
}