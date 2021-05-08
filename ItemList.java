import java.util.HashMap;
import java.util.Set;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class is used to trait inventories .
 * 
 * @author Firas miladi  
 * @version 30/04/2020
 */
public class ItemList
{   
    private HashMap<String, Item> aInventory;
    
    /**
     * Constructor
     */
    public ItemList () {
        this.aInventory = new HashMap<String,Item>();
    }
    
    /**
     * get the items in the HachMap
     * @param pItem :get Item's noun
     * @return the items from the HachMap.
     */
    public Item getItemList(final String pItem)
    {   
        return this.aInventory.get(pItem);
    }
    
    /**
    * @return HachMap of the items.
     */
    public HashMap<String, Item> getItemListhash()
    {   
        return this.aInventory;
    }
    
    
    /**
    * @return the size of the HachMap .
     */
    public int getItemListsize()
    {   
        return this.aInventory.size();
    }
    
    /**
    * @return if an item is here or not. 
    * @param pNom the item name
     */
    public boolean isExistValue(final String pNom)
    {   
        return this.aInventory.containsKey(pNom);
    }
    
    /**
     * print the items in the HachMap (player)
     * @return String with the items from the HachMap.
     */
     public String getItemListStringPlayer()
    {
         if (this.aInventory.size()==0){
           return "there is no items in your inventory";
        }else{
            StringBuilder returnString = new StringBuilder("you have in your inventory ");
            
            for ( String vS : aInventory.keySet() )
                returnString.append( " " + vS );
            return returnString.toString();
        }
    }
    
    
    /**
     * print the items in the HachMap(Room)
     * @return String with the items from the HachMap.
     */
    public String getItemListStringRoom()
    {
         if (this.aInventory.size()==0){
           return "There is no items in the room";
        }else{
            StringBuilder returnString = new StringBuilder("In this room, there is ");
            
            for ( String vS : aInventory.keySet() ){
                if (vS.equals("gold")){
                    String vI= this.getItemList(vS).getDescriptionItem();
                    returnString.append( " \n" + vI );
                } else if (vS.equals("Sarah")||vS.equals("Alexa")){
                    String vI= this.getItemList(vS).getDescriptionItem();
                    returnString.append( " \n" + vI );
                }else{
                String vI= this.getItemList(vS).getLongDescriptionItem();
                returnString.append( " \n" + vI );
            }
            }
            return returnString.toString();
        }
    }
    
    
    /**
     * get the items's weight .
     * @return weight of the items from the HachMap.
     */
    public double getItemListWeight()
    {   
        double vItemWeight=0.0;
        Set<String> vKeys = this.aInventory.keySet();
        for(String vItemName : vKeys){
            Item vObject = this.getItemList(vItemName);
            vItemWeight=+vObject.getWeightItem();
        }
        return vItemWeight;
    }
    
    /**
     * add items .
     * @param pItemString item name
     * @param pItem item 
     */
    public void addItemToTheList (final String pItemString,final Item pItem) { 
        this.aInventory.put(pItemString,pItem);
    }
    
    /**
     * remove items .
     * @param pNounItem item name
     */
    public void removeItemFromTheList (final String pNounItem) {
        this.aInventory.remove(pNounItem);
    }
} // ItemList
