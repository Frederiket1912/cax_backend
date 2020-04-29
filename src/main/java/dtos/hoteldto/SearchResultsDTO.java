package dtos.hoteldto;

import java.util.ArrayList;


class SearchResultsDTO {
    ArrayList<ResultsDTO> results;

    public SearchResultsDTO() {
    }

    public SearchResultsDTO(ArrayList<ResultsDTO> results) {
        this.results = results;
    }

    public ArrayList<ResultsDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SearchResultsDTO{" + "results=" + results + '}';
    }
    
    
}
