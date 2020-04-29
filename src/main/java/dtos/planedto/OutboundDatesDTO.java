package dtos.planedto;

import java.util.ArrayList;

class OutboundDatesDTO {
    int Price;
    ArrayList<Integer> QuoteIds;

    public OutboundDatesDTO() {
    }

    public OutboundDatesDTO(int Price, ArrayList<Integer> QuoteIds) {
        this.Price = Price;
        this.QuoteIds = QuoteIds;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public ArrayList<Integer> getQuoteIds() {
        return QuoteIds;
    }

    public void setQuoteIds(ArrayList<Integer> QuoteIds) {
        this.QuoteIds = QuoteIds;
    }

    @Override
    public String toString() {
        return "OutboundDatesDTO{" + "Price=" + Price + ", QuoteIds=" + QuoteIds + '}';
    }

    
    
    
}
