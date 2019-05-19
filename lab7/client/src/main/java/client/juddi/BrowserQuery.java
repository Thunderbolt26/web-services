package client.juddi;

public class BrowserQuery {
    public enum Subject {
        BUSINESS,
        SERVICE,
        WSDL
    }
    private Subject subject;
    private String searchString;

    public BrowserQuery(Subject subject, String searchString) {
        this.subject = subject;
        this.searchString = searchString;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getSearchString() {
        return searchString;
    }
}
