package com.walmart.gai.exceptions;

import javax.xml.ws.WebFault;


@WebFault(name = "ServiceFault", targetNamespace = "http://www.xmlns.walmartstores.com/HRM/WorkforceAdmin/wsdl/AssocIdentifierInfoIntf/1.0/")
public class ServiceException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FaultMessage faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ServiceException(String message, FaultMessage faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param message
     * @param cause
     */
    public ServiceException(String message, FaultMessage faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.associdentifier.FaultMessage
     */
    public FaultMessage getFaultInfo() {
        return faultInfo;
    }

}
