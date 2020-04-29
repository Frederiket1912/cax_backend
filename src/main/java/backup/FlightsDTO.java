package backup;


public class FlightsDTO {
    
    private String country;
    private String currency = "USD";
    private String locale = "en-US";
    private String orginPlace;
    private String destinationPlace;
    private String outboundPartialDate;
    private String inboundPartialDate;
    private String apiKey = "TO ADD";

    public FlightsDTO() {
    }

    public FlightsDTO(String country, String orginPlace, String destinationPlace, String outboundPartialDate, String inboundPartialDate) {
        this.country = country;
        this.orginPlace = orginPlace;
        this.destinationPlace = destinationPlace;
        this.outboundPartialDate = outboundPartialDate;
        this.inboundPartialDate = inboundPartialDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getOrginPlace() {
        return orginPlace;
    }

    public void setOrginPlace(String orginPlace) {
        this.orginPlace = orginPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public String getOutboundPartialDate() {
        return outboundPartialDate;
    }

    public void setOutboundPartialDate(String outboundPartialDate) {
        this.outboundPartialDate = outboundPartialDate;
    }

    public String getInboundPartialDate() {
        return inboundPartialDate;
    }

    public void setInboundPartialDate(String inboundPartialDate) {
        this.inboundPartialDate = inboundPartialDate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "FlightsDTO{" + "country=" + country + ", currency=" + currency + ", locale=" + locale + ", orginPlace=" + orginPlace + ", destinationPlace=" + destinationPlace + ", outboundPartialDate=" + outboundPartialDate + ", inboundPartialDate=" + inboundPartialDate + ", apiKey=" + apiKey + '}';
    }

    
}
