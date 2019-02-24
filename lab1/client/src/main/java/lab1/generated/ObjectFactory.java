
package lab1.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the lab1.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPersonsByFilter_QNAME = new QName("http://lab1/", "getPersonsByFilter");
    private final static QName _GetPersonsByFilterResponse_QNAME = new QName("http://lab1/", "getPersonsByFilterResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: lab1.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPersonsByFilterResponse }
     * 
     */
    public GetPersonsByFilterResponse createGetPersonsByFilterResponse() {
        return new GetPersonsByFilterResponse();
    }

    /**
     * Create an instance of {@link GetPersonsByFilter }
     * 
     */
    public GetPersonsByFilter createGetPersonsByFilter() {
        return new GetPersonsByFilter();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonsByFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1/", name = "getPersonsByFilter")
    public JAXBElement<GetPersonsByFilter> createGetPersonsByFilter(GetPersonsByFilter value) {
        return new JAXBElement<GetPersonsByFilter>(_GetPersonsByFilter_QNAME, GetPersonsByFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonsByFilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1/", name = "getPersonsByFilterResponse")
    public JAXBElement<GetPersonsByFilterResponse> createGetPersonsByFilterResponse(GetPersonsByFilterResponse value) {
        return new JAXBElement<GetPersonsByFilterResponse>(_GetPersonsByFilterResponse_QNAME, GetPersonsByFilterResponse.class, null, value);
    }

}
