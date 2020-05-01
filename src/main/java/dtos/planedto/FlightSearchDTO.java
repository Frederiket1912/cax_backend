package dtos.planedto;

public class FlightSearchDTO {

    String starturl = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsedates/v1.0/";
    String country = "US/";
    String currency = "USD/";
    String locale = "en-US/";
    String destinationplace;
    String originplace;
    String outbounddate;
    String inbounddate;

    public FlightSearchDTO() {
    }

    public FlightSearchDTO(String destinationplace, String originplace, String outbounddate, String inbounddate) {
        this.destinationplace = destinationplace;
        this.originplace = originplace ;
        this.outbounddate = outbounddate;
        this.inbounddate = inbounddate;
    }

    public String getStarturl() {
        return starturl;
    }

    public void setStarturl(String starturl) {
        this.starturl = starturl;
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

    public String getDestinationplace() {
        return destinationplace;
    }

    public void setDestinationplace(String destinationplace) {
        this.destinationplace = destinationplace;
    }

    public String getOriginplace() {
        return originplace;
    }

    public void setOriginplace(String originplace) {
        this.originplace = originplace;
    }

    public String getOutbounddate() {
        return outbounddate;
    }

    public void setOutbounddate(String outbounddate) {
        this.outbounddate = outbounddate;
    }

    public String getInbounddate() {
        return inbounddate;
    }

    public void setInbounddate(String inbounddate) {
        this.inbounddate = inbounddate;
    }
    
    

    @Override
    public String toString() {
        return starturl + country + currency + locale + destinationplace + "/" + originplace + "/" +outbounddate + "/" + inbounddate;
    }

}
