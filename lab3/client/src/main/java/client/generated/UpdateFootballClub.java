
package client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateFootballClub complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateFootballClub">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="footballClubId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="footballClubName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="footballClubCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="footballClubCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="footballClubAge" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateFootballClub", propOrder = {
    "footballClubId",
    "footballClubName",
    "footballClubCountry",
    "footballClubCity",
    "footballClubAge"
})
public class UpdateFootballClub {

    protected Integer footballClubId;
    protected String footballClubName;
    protected String footballClubCountry;
    protected String footballClubCity;
    protected Integer footballClubAge;

    /**
     * Gets the value of the footballClubId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFootballClubId() {
        return footballClubId;
    }

    /**
     * Sets the value of the footballClubId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFootballClubId(Integer value) {
        this.footballClubId = value;
    }

    /**
     * Gets the value of the footballClubName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFootballClubName() {
        return footballClubName;
    }

    /**
     * Sets the value of the footballClubName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFootballClubName(String value) {
        this.footballClubName = value;
    }

    /**
     * Gets the value of the footballClubCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFootballClubCountry() {
        return footballClubCountry;
    }

    /**
     * Sets the value of the footballClubCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFootballClubCountry(String value) {
        this.footballClubCountry = value;
    }

    /**
     * Gets the value of the footballClubCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFootballClubCity() {
        return footballClubCity;
    }

    /**
     * Sets the value of the footballClubCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFootballClubCity(String value) {
        this.footballClubCity = value;
    }

    /**
     * Gets the value of the footballClubAge property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFootballClubAge() {
        return footballClubAge;
    }

    /**
     * Sets the value of the footballClubAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFootballClubAge(Integer value) {
        this.footballClubAge = value;
    }

}
