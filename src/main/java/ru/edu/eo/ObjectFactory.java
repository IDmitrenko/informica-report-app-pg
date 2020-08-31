
package ru.edu.eo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.edu.eo package. 
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

    private final static QName _Authentication_QNAME = new QName("http://eo.edu.ru", "Authentication");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.edu.eo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PushDataResponse }
     * 
     */
    public PushDataResponse createPushDataResponse() {
        return new PushDataResponse();
    }

    /**
     * Create an instance of {@link ResponsePushData }
     * 
     */
    public ResponsePushData createResponsePushData() {
        return new ResponsePushData();
    }

    /**
     * Create an instance of {@link PushZipDataAsync }
     * 
     */
    public PushZipDataAsync createPushZipDataAsync() {
        return new PushZipDataAsync();
    }

    /**
     * Create an instance of {@link Authentication }
     * 
     */
    public Authentication createAuthentication() {
        return new Authentication();
    }

    /**
     * Create an instance of {@link PushZipData }
     * 
     */
    public PushZipData createPushZipData() {
        return new PushZipData();
    }

    /**
     * Create an instance of {@link PushData }
     * 
     */
    public PushData createPushData() {
        return new PushData();
    }

    /**
     * Create an instance of {@link PushZipDataResponse }
     * 
     */
    public PushZipDataResponse createPushZipDataResponse() {
        return new PushZipDataResponse();
    }

    /**
     * Create an instance of {@link PushZipDataAsyncResponse }
     * 
     */
    public PushZipDataAsyncResponse createPushZipDataAsyncResponse() {
        return new PushZipDataAsyncResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authentication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eo.edu.ru", name = "Authentication")
    public JAXBElement<Authentication> createAuthentication(Authentication value) {
        return new JAXBElement<Authentication>(_Authentication_QNAME, Authentication.class, null, value);
    }

}
