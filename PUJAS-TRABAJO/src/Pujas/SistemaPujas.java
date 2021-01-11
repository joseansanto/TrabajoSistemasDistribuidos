package Pujas;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SistemaPujas {

	private Map<String, Double> productoValor = new HashMap<String, Double>(100);//maximo 100 productos
	private String propietario;
	private String pujador;
	
	
	//Alamacena en el mapa de SistemaPujas un producto con su puja inicial, tambi�n asigna el propietario de ese producto
	public void a�adeProducto (String producto, Double precio, String prop) {
		productoValor.put(producto, precio);
		this.propietario = prop;
	}
	
	//Devuelve el precio de la puja m�s alta de un producto almacenado en SistemaPujas
	public Double getPrecio(String producto) {
		return (Double) productoValor.get(producto);
	}
	
	//Sirve cuando la puja es superior, en ese caso:
	//Elimina del mapa la tupla key valor de ese producto y lo vuelve a alamacenar con la nueva puja m�s alta, cambiando el pujador
	public void setProducto(String producto, Double cantidad, String pujador) {
		productoValor.remove(producto);
		productoValor.put(producto, cantidad);
		this.pujador = pujador;
	}
	
	//Devuelvo true si ese producto con ese nombre est� en nuestro sistema y falso en caso contrario
	public Boolean contieneProducto(String nombre) {
		return productoValor.containsKey(nombre);
	}
	
	/* Este m�todo, no se porque no me funcionaba y me sal�a solo el �ltimo producto que a�ad�a
	 * 
	 * public String getProductosValor() {
		Iterator ite = productoValor.entrySet().iterator();
		String productosValor = "";
		while(ite.hasNext()) {
			Map.Entry e = (Map.Entry) ite.next();
			productosValor = productosValor + e.getKey() + " -> "+e.getValue()+ "\r\n";
		}
		return productosValor;
	}*/
	
	//Devuelve el nombre y el valor de la puja m�s alta de todos los productos almacenados en el SistemaPujas
	public String getProductosValor() {
		String productosValor ="";
		for(Map.Entry<String, Double> entry : productoValor.entrySet()) {
			productosValor = productosValor + "Producto: " + entry.getKey() + " -> " + "Valor: " + entry.getValue()+"   ";
		}
		return productosValor;
	}
}
