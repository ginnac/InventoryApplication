package project.inventoryapp.controllers;

/**This is the Outsourced class. This class extends the part class. */
public class Outsourced extends Part{

    private String companyName;
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        setCompanyName(companyName);
    }

    /** This method gets the companyName value.
     @return companyName. */
    public String getCompanyName() {
        return companyName;
    }

    /** This method sets the companyName value.
     @param companyName the company name to set. */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
