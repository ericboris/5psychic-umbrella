/**
 * Maintain data about a fitness tracker
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class Wearable {
    /** rank                the consumer ranking of the wearable */
    private int rank;
    /** name                the name of the wearable */
    private String name;
    /** price               the price of the wearable */
    private double price;
    /** bodyLocation        the location on the body the wearable is worn */
    private String bodyLocation;
    /** category            the style category of the wearable */
    private String category;
    /** coName              the company name that makes the wearable */
    private String coName;
    /** coUrl               the company website url */
    private String coUrl;
    /** coLocation          the company world location */
    private String coLocation;
    /** coCity              the company city */
    private String coCity;
    /** coUsState           if made in the US, the company state */
    private String coUsState;
    /** coCountry           the company country */
    private String coCountry;
    
    /**
     * construct a wearable based on a string of data
     *
     * @param   dataFields      an array of data
     * @param   headerFields    and array of headers to split the data on     
     */
    public Wearable(String[] dataFields, String[] headerFields) {
        if (dataFields == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        
        for (int field = 0; field < headerFields.length; field++) {
            String fieldName = headerFields[field];
            switch (fieldName) {
                case "Ranking"                    : this.rank = Integer.parseInt(dataFields[field]);    break;
                case "Name"                       : this.name = dataFields[field];                      break;
                case "Price"                      : this.price = Double.parseDouble(dataFields[field]); break;
                case "Body.Location"              : this.bodyLocation = dataFields[field];              break;
                case "Category"                   : this.category = dataFields[field];                  break;
                case "Company.Name"               : this.coName = dataFields[field];                    break;
                case "Company.URL"                : this.coUrl = dataFields[field];                     break;
                case "Company...Mapping.Location" : this.coLocation = dataFields[field];                break;
                case "Company...City"             : this.coCity = dataFields[field];                    break;
                case "Company...U.S..State"       : this.coUsState = dataFields[field];                 break;
                case "Company...Country"          : this.coCountry = dataFields[field];                 break;
            }
        }
    }
    
    /**
     * get the consumer ranking
     * 
     * @return              the ranking
     */
    public int getRank() {
        return rank;
    }
    
    /**
     * get the name
     * 
     * @return              the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * get the price
     * 
     * @return              the price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * get the body location
     * 
     * @return              the location on the body worn
     */
    public String getBodyLocation() {
        return bodyLocation;
    }
    
    /**
     * get the category
     * 
     * @return              the style category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * get the company name
     * 
     * @return              the company name
     */
    public String getCoName() {
        return coName;
    }
    
    /**
     * get the company url
     * 
     * @return              the company url
     */
    public String getCoUrl() {
        return coUrl;
    }
    
    /**
     * get the company global location
     * 
     * @return              the company global location
     */
    public String getCoLocation() {
        return coLocation;
    }
    
    /**
     * get the company city
     * 
     * @return              the company city
     */
    public String getCoCity() {
        return coCity;
    }
    
    /**
     * get the company US state
     * 
     * @return              the company US state
     */
    public String getCoUsState() {
        return coUsState;
    }
    
    /**
     * get the company country
     * 
     * @return              the company country
     */
    public String getCoCountry() {
        return coCountry;
    }
    
    public String toString() {
        return rank + "\t" + name + "\t" + price + "\t" + coName;
    }
}
