
package backup;


class PriceDTO {
    private String current;
    private String old;

    public PriceDTO(String current, String old) {
        this.current = current;
        this.old = old;
    }

    public PriceDTO() {
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    @Override
    public String toString() {
        return "PriceDTO{" + "current=" + current + ", old=" + old + '}';
    }
    
    
}
