
package backup;

class ImagesDTO {
    private String caption;
    private String thumbnailUrl;
    private String fullSizeUrl;

    public ImagesDTO() {
    }

    public ImagesDTO(String caption, String thumbnailUrl, String fullSizeUrl) {
        this.caption = caption;
        this.thumbnailUrl = thumbnailUrl;
        this.fullSizeUrl = fullSizeUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getFullSizeUrl() {
        return fullSizeUrl;
    }

    public void setFullSizeUrl(String fullSizeUrl) {
        this.fullSizeUrl = fullSizeUrl;
    }

    @Override
    public String toString() {
        return "ImagesDTO{" + "caption=" + caption + ", thumbnailUrl=" + thumbnailUrl + ", fullSizeUrl=" + fullSizeUrl + '}';
    }
    
    

    
    
}
