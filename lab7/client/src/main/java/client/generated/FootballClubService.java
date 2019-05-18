
package client.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "FootballClubService", targetNamespace = "http://service/", wsdlLocation = "http://localhost:8090/FootballClubService?wsdl")
public class FootballClubService
    extends Service
{

    private final static URL FOOTBALLCLUBSERVICE_WSDL_LOCATION;
    private final static WebServiceException FOOTBALLCLUBSERVICE_EXCEPTION;
    private final static QName FOOTBALLCLUBSERVICE_QNAME = new QName("http://service/", "FootballClubService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8090/FootballClubService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FOOTBALLCLUBSERVICE_WSDL_LOCATION = url;
        FOOTBALLCLUBSERVICE_EXCEPTION = e;
    }

    public FootballClubService() {
        super(__getWsdlLocation(), FOOTBALLCLUBSERVICE_QNAME);
    }

    public FootballClubService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FOOTBALLCLUBSERVICE_QNAME, features);
    }

    public FootballClubService(URL wsdlLocation) {
        super(wsdlLocation, FOOTBALLCLUBSERVICE_QNAME);
    }

    public FootballClubService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FOOTBALLCLUBSERVICE_QNAME, features);
    }

    public FootballClubService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FootballClubService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FootballClubWebService
     */
    @WebEndpoint(name = "FootballClubWebServicePort")
    public FootballClubWebService getFootballClubWebServicePort() {
        return super.getPort(new QName("http://service/", "FootballClubWebServicePort"), FootballClubWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FootballClubWebService
     */
    @WebEndpoint(name = "FootballClubWebServicePort")
    public FootballClubWebService getFootballClubWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service/", "FootballClubWebServicePort"), FootballClubWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FOOTBALLCLUBSERVICE_EXCEPTION!= null) {
            throw FOOTBALLCLUBSERVICE_EXCEPTION;
        }
        return FOOTBALLCLUBSERVICE_WSDL_LOCATION;
    }

}
