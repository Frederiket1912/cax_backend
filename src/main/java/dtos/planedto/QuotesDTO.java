package dtos.planedto;


public class QuotesDTO {
    int QuoteId;
    int MinPrice;
    boolean Direct;
    OutboundLegDTO OutboundLeg;
    InboundLegDTO InboundLeg;

    public QuotesDTO() {
    }

    public QuotesDTO(int QuoteId, int MinPrice, boolean Direct, OutboundLegDTO OutboundLeg, InboundLegDTO InboundLeg) {
        this.QuoteId = QuoteId;
        this.MinPrice = MinPrice;
        this.Direct = Direct;
        this.OutboundLeg = OutboundLeg;
        this.InboundLeg = InboundLeg;
    }

    public int getQuoteId() {
        return QuoteId;
    }

    public void setQuoteId(int QuoteId) {
        this.QuoteId = QuoteId;
    }

    public int getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(int MinPrice) {
        this.MinPrice = MinPrice;
    }

    public boolean isDirect() {
        return Direct;
    }

    public void setDirect(boolean Direct) {
        this.Direct = Direct;
    }

    public OutboundLegDTO getOutboundLeg() {
        return OutboundLeg;
    }

    public void setOutboundLeg(OutboundLegDTO OutboundLeg) {
        this.OutboundLeg = OutboundLeg;
    }

    public InboundLegDTO getInboundLeg() {
        return InboundLeg;
    }

    public void setInboundLeg(InboundLegDTO InboundLeg) {
        this.InboundLeg = InboundLeg;
    }

    @Override
    public String toString() {
        return "QuotesDTO{" + "QuoteId=" + QuoteId + ", MinPrice=" + MinPrice + ", Direct=" + Direct + ", OutboundLeg=" + OutboundLeg + ", InboundLeg=" + InboundLeg + '}';
    }
    
    
}
