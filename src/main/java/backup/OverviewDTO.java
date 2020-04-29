
package backup;

import java.util.ArrayList;


public class OverviewDTO {
    ArrayList<overviewSectionDTO> overviewSections;

    public OverviewDTO() {
    }

    public OverviewDTO(ArrayList<overviewSectionDTO> overviewSections) {
        this.overviewSections = overviewSections;
    }

    public ArrayList<overviewSectionDTO> getOverviewSections() {
        return overviewSections;
    }

    public void setOverviewSections(ArrayList<overviewSectionDTO> overviewSections) {
        this.overviewSections = overviewSections;
    }

    @Override
    public String toString() {
        return "OverviewDTO{" + "overviewSections=" + overviewSections + '}';
    }
    
    
    
}
