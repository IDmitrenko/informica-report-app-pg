
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
 *         &lt;element name="PushZipDataResult" type="{http://eo.edu.ru}ResponsePushData" minOccurs="0"/>
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
    "pushZipDataResult"
})
@XmlRootElement(name = "PushZipDataResponse")
public class PushZipDataResponse {

    @XmlElement(name = "PushZipDataResult")
    protected ResponsePushData pushZipDataResult;

    /**
     * Gets the value of the pushZipDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponsePushData }
     *     
     */
    public ResponsePushData getPushZipDataResult() {
        return pushZipDataResult;
    }

    /**
     * Sets the value of the pushZipDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponsePushData }
     *     
     */
    public void setPushZipDataResult(ResponsePushData value) {
        this.pushZipDataResult = value;
    }

}
