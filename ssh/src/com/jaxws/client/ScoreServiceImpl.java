
package com.jaxws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ScoreServiceImpl", targetNamespace = "http://jaxws.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ScoreServiceImpl {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getDescByScore", targetNamespace = "http://jaxws.com/", className = "com.jaxws.GetDescByScore")
    @ResponseWrapper(localName = "getDescByScoreResponse", targetNamespace = "http://jaxws.com/", className = "com.jaxws.GetDescByScoreResponse")
    @Action(input = "http://jaxws.com/ScoreServiceImpl/getDescByScoreRequest", output = "http://jaxws.com/ScoreServiceImpl/getDescByScoreResponse")
    public String getDescByScore(@WebParam(name = "arg0", targetNamespace = "") int arg0);

}
