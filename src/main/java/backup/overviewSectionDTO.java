
package backup;



class overviewSectionDTO {
    private String title;

    public overviewSectionDTO() {
    }

    public overviewSectionDTO(String title) {
        this.title = title;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public String toString() {
        return "overviewSectionDTO{" + "title=" + title + '}';
    }
    
    
}
