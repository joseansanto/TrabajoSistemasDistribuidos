package Pujas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientePujador {

	public static void main(String[] args) {
		// Cliente que observa los productos en los que se puede pujar con su precio actual, teniendo la
		// posibilidad de poder pujar por ellos. La estructura para pujar es la siguiente:
		// GET nombreProducto pujaDelPujador nombrePujador
		
		Boolean seguir = false;
		
		while(!seguir) {
			System.out.println("Bienvenido a nuestro Sistema de Pujas, si desea salir escriba QUIT, si desea seguir pulse Intro, le encantará: " );
			Scanner sc0 = new Scanner(System.in);
			String salir = sc0.nextLine();
			
			if(salir.equalsIgnoreCase("QUIT")) {
				seguir = true;
			} else {
				
				System.out.println("Si quieres ver todos los productos con sus últimas pujas, escriba PRODUCTOS, si quiere pujar escriba GET: ");
				Scanner sc1 = new Scanner(System.in);
				String querimiento = sc1.nextLine();
		
				try (Socket socket = new Socket("localhost", 8089);
						DataInputStream insocket = new DataInputStream (socket.getInputStream());
						DataOutputStream outsocket = new DataOutputStream (socket.getOutputStream())){
			
					String consulta;
					if (querimiento.equalsIgnoreCase("PRODUCTOS")) {
						consulta = "PRODUCTOS"+"\r\n";
						outsocket.writeBytes(consulta);
						outsocket.flush();
				
						System.out.println(insocket.readLine());
					}else if (querimiento.equalsIgnoreCase("GET")) {
						System.out.println("Escriba el nombre del producto por el que quiera pujar: ");
						Scanner sc2 = new Scanner(System.in);
						String nombreProducto = sc2.nextLine();
				
						System.out.println("Escriba la cantidad en euros que quiere pujar por ese producto: ");
						Scanner sc3 = new Scanner(System.in);
						String cantidad = sc3.nextLine();
				
						System.out.println("Escriba su nombre: ");
						Scanner sc4 = new Scanner(System.in);
						String nombrePujador = sc4.nextLine();
				
						consulta = "GET "+nombreProducto+ " "+cantidad+ " "+nombrePujador+"\r\n";
						outsocket.writeBytes(consulta);
						outsocket.flush();
				
						System.out.println(insocket.readLine());
					}
			
		
					outsocket.close();
					insocket.close();
				}catch (UnknownHostException e) {
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
