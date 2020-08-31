
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
 *         &lt;element name="PushZipDataAsyncResult" type="{http://eo.edu.ru}ResponsePushData" minOccurs="0"/>
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
    "pushZipDataAsyncResult"
})
@XmlRootElement(name = "PushZipDataAsyncResponse")
public class PushZipDataAsyncResponse {

    @XmlElement(name = "PushZipDataAsyncResult")
    protected ResponsePushData pushZipDataAsyncResult;

    /**
     * Gets the value of the pushZipDataAsyncResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponsePushData }
     *     
     */
    public ResponsePushData getPushZipDataAsyncResult() {
        return pushZipDataAsyncResult;
    }

    /**
     * Sets the value of the pushZipDataAsyncResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponsePushData }
     *     
     */
    public void setPushZipDataAsyncResult(ResponsePushData value) {
        this.pushZipDataAsyncResult = value;
    }

}
