package Pujas;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SistemaPujas {

	private Map<String, Double> productoValor = new HashMap<String, Double>(100);//maximo 100 productos
	private String propietario;
	private String pujador;
	
	
	//Alamacena en el mapa de SistemaPujas un producto con su puja inicial, también asigna el propietario de ese producto
	public void añadeProducto (String producto, Double precio, String prop) {
		productoValor.put(producto, precio);
		this.propietario = prop;
	}
	
	//Devuelve el precio de la puja más alta de un producto almacenado en SistemaPujas
	public Double getPrecio(String producto) {
		return (Double) productoValor.get(producto);
	}
	
	//Sirve cuando la puja es superior, en ese caso:
	//Elimina del mapa la tupla key valor de ese producto y lo vuelve a alamacenar con la nueva puja más alta, cambiando el pujador
	public void setProducto(String producto, Double cantidad, String pujador) {
		productoValor.remove(producto);
		productoValor.put(producto, cantidad);
		this.pujador = pujador;
	}
	
	//Devuelvo true si ese producto con ese nombre está en nuestro sistema y falso en caso contrario
	public Boolean contieneProducto(String nombre) {
		return productoValor.containsKey(nombre);
	}
	
	/* Este método, no se porque no me funcionaba y me salía solo el último producto que añadía
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
	
	//Devuelve el nombre y el valor de la puja más alta de todos los productos almacenados en el SistemaPujas
	public String getProductosValor() {
		String productosValor ="";
		for(Map.Entry<String, Double> entry : productoValor.entrySet()) {
			productosValor = productosValor + "Producto: " + entry.getKey() + " -> " + "Valor: " + entry.getValue()+"   ";
		}
		return productosValor;
	}
}
