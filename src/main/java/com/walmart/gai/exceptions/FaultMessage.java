


package com.walmart.gai.exceptions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Java class for FaultMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FaultMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="CommonServiceFault" type="{http://www.xmlns.walmartstores.com/HRM/WorkforceAdmin/DataTypes/CommonDataTypes/1.1/}CommonServiceFault" minOccurs="0"/>
 *         &lt;element name="CICSCommonServiceFault" type="{http://www.xmlns.walmartstores.com/HRM/WorkforceAdmin/DataTypes/CommonDataTypes/1.1/}CICSCommonServiceFault" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaultMessage", namespace = "http://www.xmlns.walmartstores.com/HRM/WorkforceAdmin/DataTypes/CommonMessages/1.1/", propOrder = {
    "commonServiceFault",
    "cicsCommonServiceFault"
})
public class FaultMessage {

   // @XmlElement(name = "CommonServiceFault")
   // protected CommonServiceFault commonServiceFault;
   // @XmlElement(name = "CICSCommonServiceFault")
   // protected CICSCommonServiceFault cicsCommonServiceFault;

   

}
