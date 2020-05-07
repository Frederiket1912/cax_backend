package dtos.hoteldto;

public class HotelSearchDTO {

    String starturl = "https://hotels4.p.rapidapi.com/properties/list?currency=";
    String currency = "USD&";
    String locale = "locale=en_US&";
    String sortOrder = "sortOrder=PRICE&";
    String destinationplace = "destinationId=1506246&";
    String pageNumber = "pageNumber=1&";
    String checkIn;
    String checkOut;
    String pageSize = "&pageSize=30";
    String adults1;

    public HotelSearchDTO() {
    }

    public HotelSearchDTO(String checkIn, String checkOut, String adults1) {
        this.checkIn = "checkIn=" +checkIn;
        this.checkOut = "&checkOut=" + checkOut;
        this.adults1 = "&adults1="+ adults1;
    }

    public String getStarturl() {
        return starturl;
    }

    public void setStarturl(String starturl) {
        this.starturl = starturl;
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

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getDestinationplace() {
        return destinationplace;
    }

    public void setDestinationplace(String destinationplace) {
        this.destinationplace = destinationplace;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getAdults1() {
        return adults1;
    }

    public void setAdults1(String adults1) {
        this.adults1 = adults1;
    }

    @Override
    public String toString() {
        return starturl + currency +  locale  + sortOrder + destinationplace + pageNumber + checkIn + checkOut + pageSize + adults1;
    }
    
    

}
