
package com.jaxws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jaxws package. 
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

    private final static QName _GetDescByScoreResponse_QNAME = new QName("http://jaxws.com/", "getDescByScoreResponse");
    private final static QName _GetDescByScore_QNAME = new QName("http://jaxws.com/", "getDescByScore");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDescByScoreResponse }
     * 
     */
    public GetDescByScoreResponse createGetDescByScoreResponse() {
        return new GetDescByScoreResponse();
    }

    /**
     * Create an instance of {@link GetDescByScore }
     * 
     */
    public GetDescByScore createGetDescByScore() {
        return new GetDescByScore();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDescByScoreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.com/", name = "getDescByScoreResponse")
    public JAXBElement<GetDescByScoreResponse> createGetDescByScoreResponse(GetDescByScoreResponse value) {
        return new JAXBElement<GetDescByScoreResponse>(_GetDescByScoreResponse_QNAME, GetDescByScoreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDescByScore }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.com/", name = "getDescByScore")
    public JAXBElement<GetDescByScore> createGetDescByScore(GetDescByScore value) {
        return new JAXBElement<GetDescByScore>(_GetDescByScore_QNAME, GetDescByScore.class, null, value);
    }

}
