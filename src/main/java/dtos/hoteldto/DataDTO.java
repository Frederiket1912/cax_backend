package dtos.hoteldto;


class DataDTO {
    BodyDTO body;
    private boolean failed;

    public DataDTO() {
    }

    public DataDTO(BodyDTO body) {
        this.body = body;
    }

    public BodyDTO getBody() {
        return body;
    }

    public void setBody(BodyDTO body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "DataDTO{" + "body=" + body + '}';
    }
      
    
}
