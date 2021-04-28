
public class Item
{
    private String aNoun;
    private String aDescription;
    private double aPrice;
    private double aWeight;
    
    /**
     * Constructor
     * @param pNoun :get Item's noun
     * @param pDescription : get Item's Description .
     * @param pPrice : get Item's Price .
     * @param pWeight : get Item's weight .
     */
    public Item(final String pNoun, final String pDescription, final double pPrice, final double pWeight)
    {
        this.aNoun        = pNoun;
        this.aDescription = pDescription;
        this.aPrice        = pPrice;
        this.aWeight       = pWeight;
    }
    
    /**
     * @return get item noun.
     */
    public String getNounItem(){return this.aNoun;}
      
    /**
     * @return get item description for gold.
     */
    public String getDescriptionItem(){return this.aNoun+" || "+this.aDescription ;}
    
    /**
     * @return get Item's Description .
     */
    public String getDescriptionItemString(){return this.aDescription + " || price : "+ this.aPrice +" Yuan || Weight : "+ this.aWeight +" Kg";}
    
    /**
     * @return get full Item's Description .
     */
   public String getLongDescriptionItem(){return this.aNoun+" || "+this.aDescription + " || price : "+ this.aPrice +" Yuan || Weight : "+ this.aWeight +" Kg";}

    /**
     * @return get Item's price .
     */
    public double getPriceItem(){return this.aPrice;}
    
    /**
     * @return get Item's weight .
     */
    public double getWeightItem(){return this.aWeight;}
    
}