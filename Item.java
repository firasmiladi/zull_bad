public class Item
{
    private String aDescription;
    private double aPrix;
    private double aPoids;

    public Item(final String pDescription, final double pPrix, final double pPoids)
    {
        this.aDescription = pDescription;
        this.aPrix        = pPrix;
        this.aPoids       = pPoids;
    }

    public String getDescriptionItem(){return this.aDescription;}
    public double getPrixItem(){return this.aPrix;}
    public double getPoidsItem(){return this.aPoids;}

}