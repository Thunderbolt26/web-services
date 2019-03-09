
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

    private final static QName _GetPersonsByFilterResponse_QNAME = new QName("http://service/", "getPersonsByFilterResponse");
    private final static QName _AddPersonsResponse_QNAME = new QName("http://service/", "addPersonsResponse");
    private final static QName _DeletePersonResponse_QNAME = new QName("http://service/", "deletePersonResponse");
    private final static QName _UpdatePersonResponse_QNAME = new QName("http://service/", "updatePersonResponse");
    private final static QName _AddPersons_QNAME = new QName("http://service/", "addPersons");
    private final static QName _DeletePerson_QNAME = new QName("http://service/", "deletePerson");
    private final static QName _GetPersonsByFilter_QNAME = new QName("http://service/", "getPersonsByFilter");
    private final static QName _UpdatePerson_QNAME = new QName("http://service/", "updatePerson");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPersons }
     * 
     */
    public AddPersons createAddPersons() {
        return new AddPersons();
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link UpdatePersonResponse }
     * 
     */
    public UpdatePersonResponse createUpdatePersonResponse() {
        return new UpdatePersonResponse();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link AddPersonsResponse }
     * 
     */
    public AddPersonsResponse createAddPersonsResponse() {
        return new AddPersonsResponse();
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
     * Create an instance of {@link UpdatePerson }
     * 
     */
    public UpdatePerson createUpdatePerson() {
        return new UpdatePerson();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonsByFilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getPersonsByFilterResponse")
    public JAXBElement<GetPersonsByFilterResponse> createGetPersonsByFilterResponse(GetPersonsByFilterResponse value) {
        return new JAXBElement<GetPersonsByFilterResponse>(_GetPersonsByFilterResponse_QNAME, GetPersonsByFilterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addPersonsResponse")
    public JAXBElement<AddPersonsResponse> createAddPersonsResponse(AddPersonsResponse value) {
        return new JAXBElement<AddPersonsResponse>(_AddPersonsResponse_QNAME, AddPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "updatePersonResponse")
    public JAXBElement<UpdatePersonResponse> createUpdatePersonResponse(UpdatePersonResponse value) {
        return new JAXBElement<UpdatePersonResponse>(_UpdatePersonResponse_QNAME, UpdatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addPersons")
    public JAXBElement<AddPersons> createAddPersons(AddPersons value) {
        return new JAXBElement<AddPersons>(_AddPersons_QNAME, AddPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonsByFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getPersonsByFilter")
    public JAXBElement<GetPersonsByFilter> createGetPersonsByFilter(GetPersonsByFilter value) {
        return new JAXBElement<GetPersonsByFilter>(_GetPersonsByFilter_QNAME, GetPersonsByFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "updatePerson")
    public JAXBElement<UpdatePerson> createUpdatePerson(UpdatePerson value) {
        return new JAXBElement<UpdatePerson>(_UpdatePerson_QNAME, UpdatePerson.class, null, value);
    }

}
