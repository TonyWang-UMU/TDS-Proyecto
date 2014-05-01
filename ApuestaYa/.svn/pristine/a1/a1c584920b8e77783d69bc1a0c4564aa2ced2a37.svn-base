package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	// el nombre del usuario unico
	private String nombreUsuario;
	private int codigo;
	private List<Transaccion> listaTransacciones;
	private List<ApuestaRealizada> apuestasRealizadas;
	private String email;
	private String nombre;
	private String apellidos;
	private String contraseña;
	private Date fechaNac;
	private String preguntaPrivada;
	private String respuesta;
	private double saldo;

	// en el guion dice que tambien tenemos un pago por defecto
	private FormaPagoIngreso ingresoPorDefecto;
	private FormaPagoCobro cobroPorDefecto;

	public Usuario(String nombreUsuario, String email, String nombre,
			String apellidos, String contraseña, Date fechaNac,
			String preguntaPrivada, String respuesta) {
		this.nombreUsuario = nombreUsuario;
		this.listaTransacciones = new LinkedList<Transaccion>();
		this.apuestasRealizadas = new LinkedList<ApuestaRealizada>();
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
		this.fechaNac = fechaNac;
		this.preguntaPrivada = preguntaPrivada;
		this.respuesta = respuesta;

		cobroPorDefecto = new Paypal();
		ingresoPorDefecto = new Paypal();
	}

	/**
	 * Metodos implementados para poder asignarle el tipo de pago correcto al
	 * recuperar un usuario de la BBDD
	 * 
	 * @param formaIngreso
	 *            El metodo de ingreso que tiene el usuario
	 */
	public void comprobarIngreso(String formaIngreso) {
		if (formaIngreso.equals("Tarjeta"))
			setIngresoPorDefecto(new Tarjeta());
		else if (formaIngreso.equals("SMS"))
			setIngresoPorDefecto(new SMS());
		else
			setIngresoPorDefecto(new Paypal());
	}

	/**
	 * Metodos implementados para poder asignarle el tipo de cobro correcto al
	 * recuperar un usuario de la BBDD
	 * 
	 * @param formaCobro
	 *            El metodo de cobro que tiene el usuario
	 */

	public void comprobarCobro(String formaCobro) {
		if (formaCobro.equals("Transferencia"))
			setCobroPorDefecto(new Transferencia());
		else
			setCobroPorDefecto(new Paypal());

	}

	public void addTransaccion(Transaccion transaccion) {
		this.listaTransacciones.add(transaccion);
	}

	public void addApuesta(ApuestaRealizada apuesta) {
		this.apuestasRealizadas.add(apuesta);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public FormaPagoCobro getCobroPorDefecto() {
		return cobroPorDefecto;
	}

	public void setCobroPorDefecto(FormaPagoCobro cobroPorDefecto) {
		this.cobroPorDefecto = cobroPorDefecto;
	}

	public FormaPagoIngreso getIngresoPorDefecto() {
		return ingresoPorDefecto;
	}

	public void setIngresoPorDefecto(FormaPagoIngreso ingresoPorDefecto) {
		this.ingresoPorDefecto = ingresoPorDefecto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public List<Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}

	public List<ApuestaRealizada> getApuestasRealizadas() {
		return apuestasRealizadas;
	}

	public String getPreguntaPrivada() {
		return preguntaPrivada;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setListaTransacciones(List<Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setApuestasRealizadas(List<ApuestaRealizada> apuestasRealizadas) {
		this.apuestasRealizadas = apuestasRealizadas;
	}

	public void setPreguntaPrivada(String preguntaPrivada) {
		this.preguntaPrivada = preguntaPrivada;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
