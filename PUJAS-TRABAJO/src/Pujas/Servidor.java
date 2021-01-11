package Pujas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (ServerSocket server = new ServerSocket(8089)){
			System.out.println("Servidor de pujas conectado");
			SistemaPujas SistemaPujas = new SistemaPujas();
			while(true) {
				try (Socket cliente = server.accept(); 
						BufferedReader br = new BufferedReader (new InputStreamReader(cliente.getInputStream()));
						Writer w = new OutputStreamWriter(cliente.getOutputStream())) {
							
							String linea;
							if ((linea = br.readLine()) == null) {
								w.write("ERROR: Usted no ha escrito nada en la aplicación de pujas\r\n");
								w.flush();
							}
							
							String partes[] = linea.split(" ");
							String accion = partes[0];
							
							
							if (!accion.equals("GET") && !accion.equals("PUT") && !accion.equals("PRODUCTOS")) {
								w.write("ERROR: No ha realizado la acción con GET para pujar producto o con PUT para vender producto o PRODUCTOS\r\n");
								w.flush();
							}
							//caso PUT (almacenar productos con su valor en el sistema)
							if (accion.equals("PUT")) {
								if(SistemaPujas.contieneProducto(partes[1]) == true) {
									w.write("El producto que quiere añadir ya existe, debe cambiarle de nombre si es distinto\r\n");
									w.flush();
								}else {
									SistemaPujas.añadeProducto(partes[1],Double.parseDouble(partes[2]), partes[3]);
									w.write("Usted a añadido el producto con la puja mínima en nuestro sistema\r\n");
									w.flush();
								}
							//caso PRODUCTOS (mostrarProductos)	
							} else if (accion.equals("PRODUCTOS")){
								w.write(SistemaPujas.getProductosValor());
								w.flush();
							} else if (accion.equals("GET")) { //caso GET (poder apostar productos)
								String respuesta;
								if(SistemaPujas.contieneProducto(partes[1]) == true)	{
									if (Double.parseDouble(partes[2]) > SistemaPujas.getPrecio(partes[1])) {
										SistemaPujas.setProducto(partes[1], Double.parseDouble(partes[2]), partes[3]);
										respuesta = "Su puja es la más alta hasta el momento";
									}else {
										respuesta = "No ha introducido una puja superior a la del sistema para ese producto";
									}
								}else {
									respuesta = "El producto por el que quiere apostar no existe";
								}
								w.write(respuesta + "\r\n");
								w.flush();
							}
						} catch(IOException e) {
							e.printStackTrace();
						} catch(NumberFormatException e) {
							e.printStackTrace();
							System.out.println("Usted no ha introducido una cantidad como número, vuelva a escribirlo correctamente");
						}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
