/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class is used to trait Items .
 * 
 * @author Firas miladi  
 * @version 30/04/2020
 */
public class Item
{
    private String aNoun;
    private String aDescription;
    private double aPrice;
    private double aWeight;
    private boolean aNonPlayer;
    /**
     * Constructor
     * @param pNoun :get Item's noun
     * @param pDescription : get Item's Description .
     * @param pPrice : get Item's Price .
     * @param pWeight : get Item's weight .
     */
    public Item(final String pNoun, final String pDescription, final double pPrice, final double pWeight)
    {
        this(pNoun,pDescription);
        this.aPrice        = pPrice;
        this.aWeight       = pWeight;
        this.aNonPlayer= false;
    }
    
    /**
     * Constructor
     * @param pNoun :get Item's noun
     * @param pDescription : get Item's Description .
     * 
     */
    public Item(final String pNoun, final String pDescription)
    {
        this.aNoun        = pNoun;
        this.aDescription = pDescription;
        this.aNonPlayer   = true;
    }
    
    /**
     * @return if the item is a non-player   .
     */
    public boolean getNonPlayer(){return this.aNonPlayer;}
    
    /**
     * @return get item noun.
     */
    public String getNounItem(){return this.aNoun;}
      
    /**
     * @return get item description for gold and non-player.
     */
    public String getDescriptionItem(){return this.aNoun+" || "+this.aDescription ;}
    
    /**
     * @return get Item's Description .
     */
    public String getDescriptionItemString(){return this.aDescription + " || price : "+ this.aPrice +" Yuan || Weight : "+ this.aWeight +" Kg";}
    
    /**
     * @return get full Item's Description .
     */
   public String getLongDescriptionItem(){return this.aNoun+" || "+ this.getDescriptionItemString();}

    /**
     * @return get Item's price .
     */
    public double getPriceItem(){return this.aPrice;}
    
    /**
     * @return get Item's weight .
     */
    public double getWeightItem(){return this.aWeight;}
    
}