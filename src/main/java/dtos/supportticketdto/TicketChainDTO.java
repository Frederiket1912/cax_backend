package dtos.supportticketdto;

public class TicketChainDTO {
    private String subject;
    private String comment;

    public TicketChainDTO() {
    }

    public TicketChainDTO(int id, String subject, String comment) {
        this.subject = subject;
        this.comment = comment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
    
}
