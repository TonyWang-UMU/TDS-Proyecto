//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.19 at 11:39:10 PM CET 
//


package resultado;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Equipo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Equipo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="goleador" type="{http://www.misresultados.es/tds}Goleador" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="equipo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="goles" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Equipo", propOrder = {
    "goleador"
})
public class Equipo {

    protected List<Goleador> goleador;
    @XmlAttribute(required = true)
    protected String equipo;
    @XmlAttribute(required = true)
    protected int goles;

    /**
     * Gets the value of the goleador property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the goleador property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoleador().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Goleador }
     * 
     * 
     */
    public List<Goleador> getGoleador() {
        if (goleador == null) {
            goleador = new ArrayList<Goleador>();
        }
        return this.goleador;
    }

    /**
     * Gets the value of the equipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipo() {
        return equipo;
    }

    /**
     * Sets the value of the equipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipo(String value) {
        this.equipo = value;
    }

    /**
     * Gets the value of the goles property.
     * 
     */
    public int getGoles() {
        return goles;
    }

    /**
     * Sets the value of the goles property.
     * 
     */
    public void setGoles(int value) {
        this.goles = value;
    }

}
