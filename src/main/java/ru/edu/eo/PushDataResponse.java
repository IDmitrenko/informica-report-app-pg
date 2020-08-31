
package ru.edu.eo;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PushDataResult" type="{http://eo.edu.ru}ResponsePushData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pushDataResult"
})
@XmlRootElement(name = "PushDataResponse")
public class PushDataResponse {

    @XmlElement(name = "PushDataResult")
    protected ResponsePushData pushDataResult;

    /**
     * Gets the value of the pushDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponsePushData }
     *     
     */
    public ResponsePushData getPushDataResult() {
        return pushDataResult;
    }

    /**
     * Sets the value of the pushDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponsePushData }
     *     
     */
    public void setPushDataResult(ResponsePushData value) {
        this.pushDataResult = value;
    }

}
