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
 *         &lt;element ref="{http://www.misresultados.es/tds}resultado_futbol" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.misresultados.es/tds}resultado_tenis" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.misresultados.es/tds}resultado_f1" maxOccurs="unbounded" minOccurs="0"/>
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
    "resultadoFutbol",
    "resultadoTenis",
    "resultadoF1"
})
@XmlRootElement(name = "resultado")
public class Resultado {

    @XmlElement(name = "resultado_futbol")
    protected List<ResultadoFutbol> resultadoFutbol;
    @XmlElement(name = "resultado_tenis")
    protected List<ResultadoTenis> resultadoTenis;
    @XmlElement(name = "resultado_f1")
    protected List<ResultadoF1> resultadoF1;

    /**
     * Gets the value of the resultadoFutbol property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultadoFutbol property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultadoFutbol().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultadoFutbol }
     * 
     * 
     */
    public List<ResultadoFutbol> getResultadoFutbol() {
        if (resultadoFutbol == null) {
            resultadoFutbol = new ArrayList<ResultadoFutbol>();
        }
        return this.resultadoFutbol;
    }

    /**
     * Gets the value of the resultadoTenis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultadoTenis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultadoTenis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultadoTenis }
     * 
     * 
     */
    public List<ResultadoTenis> getResultadoTenis() {
        if (resultadoTenis == null) {
            resultadoTenis = new ArrayList<ResultadoTenis>();
        }
        return this.resultadoTenis;
    }

    /**
     * Gets the value of the resultadoF1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultadoF1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultadoF1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultadoF1 }
     * 
     * 
     */
    public List<ResultadoF1> getResultadoF1() {
        if (resultadoF1 == null) {
            resultadoF1 = new ArrayList<ResultadoF1>();
        }
        return this.resultadoF1;
    }

}
