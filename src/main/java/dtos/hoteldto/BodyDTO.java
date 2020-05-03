package dtos.hoteldto;


public class BodyDTO {
    SearchResultsDTO searchResults;

    public BodyDTO() {
    }

    public BodyDTO(SearchResultsDTO searchResults) {
        this.searchResults = searchResults;
    }

    public SearchResultsDTO getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(SearchResultsDTO searchResults) {
        this.searchResults = searchResults;
    }

    @Override
    public String toString() {
        return "BodyDTO{" + "searchResults=" + searchResults + '}';
    }
    
    
    
    
    
}
