
package client.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client.generated package. 
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

    private final static QName _AddFootballClubResponse_QNAME = new QName("http://service/", "addFootballClubResponse");
    private final static QName _DefaultException_QNAME = new QName("http://service/", "DefaultException");
    private final static QName _DeleteFootballClubResponse_QNAME = new QName("http://service/", "deleteFootballClubResponse");
    private final static QName _GetFootballClubsByFilter_QNAME = new QName("http://service/", "getFootballClubsByFilter");
    private final static QName _DataNotFoundException_QNAME = new QName("http://service/", "DataNotFoundException");
    private final static QName _FormatException_QNAME = new QName("http://service/", "FormatException");
    private final static QName _DeleteFootballClub_QNAME = new QName("http://service/", "deleteFootballClub");
    private final static QName _GetFootballClubsByFilterResponse_QNAME = new QName("http://service/", "getFootballClubsByFilterResponse");
    private final static QName _AddFootballClub_QNAME = new QName("http://service/", "addFootballClub");
    private final static QName _UpdateFootballClub_QNAME = new QName("http://service/", "updateFootballClub");
    private final static QName _UpdateFootballClubResponse_QNAME = new QName("http://service/", "updateFootballClubResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FootballClubServiceFault }
     * 
     */
    public FootballClubServiceFault createFootballClubServiceFault() {
        return new FootballClubServiceFault();
    }

    /**
     * Create an instance of {@link GetFootballClubsByFilter }
     * 
     */
    public GetFootballClubsByFilter createGetFootballClubsByFilter() {
        return new GetFootballClubsByFilter();
    }

    /**
     * Create an instance of {@link DeleteFootballClubResponse }
     * 
     */
    public DeleteFootballClubResponse createDeleteFootballClubResponse() {
        return new DeleteFootballClubResponse();
    }

    /**
     * Create an instance of {@link AddFootballClubResponse }
     * 
     */
    public AddFootballClubResponse createAddFootballClubResponse() {
        return new AddFootballClubResponse();
    }

    /**
     * Create an instance of {@link UpdateFootballClubResponse }
     * 
     */
    public UpdateFootballClubResponse createUpdateFootballClubResponse() {
        return new UpdateFootballClubResponse();
    }

    /**
     * Create an instance of {@link AddFootballClub }
     * 
     */
    public AddFootballClub createAddFootballClub() {
        return new AddFootballClub();
    }

    /**
     * Create an instance of {@link UpdateFootballClub }
     * 
     */
    public UpdateFootballClub createUpdateFootballClub() {
        return new UpdateFootballClub();
    }

    /**
     * Create an instance of {@link DeleteFootballClub }
     * 
     */
    public DeleteFootballClub createDeleteFootballClub() {
        return new DeleteFootballClub();
    }

    /**
     * Create an instance of {@link GetFootballClubsByFilterResponse }
     * 
     */
    public GetFootballClubsByFilterResponse createGetFootballClubsByFilterResponse() {
        return new GetFootballClubsByFilterResponse();
    }

    /**
     * Create an instance of {@link FootballClub }
     * 
     */
    public FootballClub createFootballClub() {
        return new FootballClub();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFootballClubResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addFootballClubResponse")
    public JAXBElement<AddFootballClubResponse> createAddFootballClubResponse(AddFootballClubResponse value) {
        return new JAXBElement<AddFootballClubResponse>(_AddFootballClubResponse_QNAME, AddFootballClubResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FootballClubServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "DefaultException")
    public JAXBElement<FootballClubServiceFault> createDefaultException(FootballClubServiceFault value) {
        return new JAXBElement<FootballClubServiceFault>(_DefaultException_QNAME, FootballClubServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFootballClubResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "deleteFootballClubResponse")
    public JAXBElement<DeleteFootballClubResponse> createDeleteFootballClubResponse(DeleteFootballClubResponse value) {
        return new JAXBElement<DeleteFootballClubResponse>(_DeleteFootballClubResponse_QNAME, DeleteFootballClubResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFootballClubsByFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getFootballClubsByFilter")
    public JAXBElement<GetFootballClubsByFilter> createGetFootballClubsByFilter(GetFootballClubsByFilter value) {
        return new JAXBElement<GetFootballClubsByFilter>(_GetFootballClubsByFilter_QNAME, GetFootballClubsByFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FootballClubServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "DataNotFoundException")
    public JAXBElement<FootballClubServiceFault> createDataNotFoundException(FootballClubServiceFault value) {
        return new JAXBElement<FootballClubServiceFault>(_DataNotFoundException_QNAME, FootballClubServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FootballClubServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "FormatException")
    public JAXBElement<FootballClubServiceFault> createFormatException(FootballClubServiceFault value) {
        return new JAXBElement<FootballClubServiceFault>(_FormatException_QNAME, FootballClubServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFootballClub }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "deleteFootballClub")
    public JAXBElement<DeleteFootballClub> createDeleteFootballClub(DeleteFootballClub value) {
        return new JAXBElement<DeleteFootballClub>(_DeleteFootballClub_QNAME, DeleteFootballClub.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFootballClubsByFilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getFootballClubsByFilterResponse")
    public JAXBElement<GetFootballClubsByFilterResponse> createGetFootballClubsByFilterResponse(GetFootballClubsByFilterResponse value) {
        return new JAXBElement<GetFootballClubsByFilterResponse>(_GetFootballClubsByFilterResponse_QNAME, GetFootballClubsByFilterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFootballClub }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addFootballClub")
    public JAXBElement<AddFootballClub> createAddFootballClub(AddFootballClub value) {
        return new JAXBElement<AddFootballClub>(_AddFootballClub_QNAME, AddFootballClub.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateFootballClub }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "updateFootballClub")
    public JAXBElement<UpdateFootballClub> createUpdateFootballClub(UpdateFootballClub value) {
        return new JAXBElement<UpdateFootballClub>(_UpdateFootballClub_QNAME, UpdateFootballClub.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateFootballClubResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "updateFootballClubResponse")
    public JAXBElement<UpdateFootballClubResponse> createUpdateFootballClubResponse(UpdateFootballClubResponse value) {
        return new JAXBElement<UpdateFootballClubResponse>(_UpdateFootballClubResponse_QNAME, UpdateFootballClubResponse.class, null, value);
    }

}
