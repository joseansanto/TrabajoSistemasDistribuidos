package Pujas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;


public class ClientePropietario {

	public static void main(String[] args) {
		// Cliente que almacena productos en el sistema de pujas, con la puja mínima que el considera
		// para su producto. El protocolo que voy a utilizar para almacenar es el siguiente:
		// PUT nombreDelProducto  pujaMínima(Inicial)  nombrePropietario
		
		System.out.println("Escriba el nombre del producto: ");
		Scanner sc1 = new Scanner(System.in);
		String nombreProducto  = sc1.nextLine();
		
		System.out.println("Escriba la puja mínima o inicial a la que quiere vender su producto: ");
		Scanner sc2 = new Scanner(System.in);
		String pujaMinima = sc2.nextLine();
		
		System.out.println("Escriba su nombre como propietario: ");
		Scanner sc3 = new Scanner(System.in);
		String nombrePropietario = sc3.nextLine();
		
		try (Socket socket = new Socket("localhost", 8089);
				DataInputStream insocket = new DataInputStream(socket.getInputStream());
				DataOutputStream outsocket = new DataOutputStream(socket.getOutputStream())){
			
			String almacenar = "PUT "+nombreProducto+ " "+pujaMinima+ " "+nombrePropietario+"\r\n";
			outsocket.writeBytes(almacenar);
			outsocket.flush();
			
			String respuesta = insocket.readLine();
			System.out.println(respuesta);
		
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
