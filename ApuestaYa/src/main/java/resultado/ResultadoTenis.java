//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.19 at 11:39:10 PM CET 
//


package resultado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="jugador1" type="{http://www.misresultados.es/tds}JugadorTenis"/>
 *         &lt;element name="jugador2" type="{http://www.misresultados.es/tds}JugadorTenis"/>
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
    "jugador1",
    "jugador2"
})
@XmlRootElement(name = "resultado_tenis")
public class ResultadoTenis {

    @XmlElement(required = true)
    protected JugadorTenis jugador1;
    @XmlElement(required = true)
    protected JugadorTenis jugador2;

    /**
     * Gets the value of the jugador1 property.
     * 
     * @return
     *     possible object is
     *     {@link JugadorTenis }
     *     
     */
    public JugadorTenis getJugador1() {
        return jugador1;
    }

    /**
     * Sets the value of the jugador1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorTenis }
     *     
     */
    public void setJugador1(JugadorTenis value) {
        this.jugador1 = value;
    }

    /**
     * Gets the value of the jugador2 property.
     * 
     * @return
     *     possible object is
     *     {@link JugadorTenis }
     *     
     */
    public JugadorTenis getJugador2() {
        return jugador2;
    }

    /**
     * Sets the value of the jugador2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorTenis }
     *     
     */
    public void setJugador2(JugadorTenis value) {
        this.jugador2 = value;
    }

}
