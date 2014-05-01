package resultado;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class CargadorResultados {

	public static Resultado cargarResultados(String datos) {

		JAXBContext jc;
		Resultado resultado = null;
		try {
			jc = JAXBContext.newInstance("resultado");
			Unmarshaller u = jc.createUnmarshaller();
			File file = new File(datos);
			resultado = (Resultado) u.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}	
		return resultado;
	}

}
