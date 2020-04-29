package backup;


class BrandsDTO {
    private String formattedRating;

    public BrandsDTO() {
    }

    public BrandsDTO(String formattedRating) {
        this.formattedRating = formattedRating;
    }

    public String getFormattedRating() {
        return formattedRating;
    }

    public void setFormattedRating(String formattedRating) {
        this.formattedRating = formattedRating;
    }

    @Override
    public String toString() {
        return "BrandsDTO{" + "formattedRating=" + formattedRating + '}';
    }
    
    
}
