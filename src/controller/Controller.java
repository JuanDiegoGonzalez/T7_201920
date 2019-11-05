package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.data_structures.Node;
import model.data_structures.Queue;
import model.logic.MVCModelo;
import model.logic.ZonaUBER;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}

	/**
	 * Hilo de ejecución del programa
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while(!fin)
		{
			view.printMenu();

			String in;
			in = lector.next();

			int option;
			try
			{
				option = Integer.parseInt(in);
			}
			catch(NumberFormatException e)
			{
				option = 0;
			}

			switch(option){
			case 1:

				modelo.cargarGrafo();
				System.out.println("Archivos cargados");

				try
				{
					System.out.println("Cantidad de vertices cargados: " + modelo.darMinID());
					System.out.println("Cantidad de arcos cargados: " + modelo.darMaxID()  + "\n---------");
				}
				catch(Exception e)
				{}

				break;

			case 2:

				try
				{
					modelo.x();
					System.out.println("El grafo se guardó en formato JSON\n---------");
				}
				catch (Exception e)
				{
					System.out.println("No se pudo persistir el grafo.\n---------");
				}

			case 3:

				ZonaUBER zona = modelo.consultarZonaPorID(MID);
				if (zona == null)
				{
					System.out.println("No hay una zona con el MOVEMENT ID ingresado.\n---------");
				}
				else
				{
					System.out.println("--------- \nDatos de la zona: \n");

					System.out.println("Nombre: " + zona.darScanombre() + "\nPerímetro: " + (zona.darShape_leng()*100) + " kilómetros\nÁrea: " + (zona.darShape_area()*10000) + " kilómetros cuadrados\nNúmero de puntos: " + zona.darCoordinates().darNumeroElementos() + "\n---------");
				}
				break;

			case 4:

				ZonaUBER zona = modelo.consultarZonaPorID(MID);
				if (zona == null)
				{
					System.out.println("No hay una zona con el MOVEMENT ID ingresado.\n---------");
				}
				else
				{
					System.out.println("--------- \nDatos de la zona: \n");

					System.out.println("Nombre: " + zona.darScanombre() + "\nPerímetro: " + (zona.darShape_leng()*100) + " kilómetros\nÁrea: " + (zona.darShape_area()*10000) + " kilómetros cuadrados\nNúmero de puntos: " + zona.darCoordinates().darNumeroElementos() + "\n---------");
				}
				break;

			case 5:

				ZonaUBER zona = modelo.consultarZonaPorID(MID);
				if (zona == null)
				{
					System.out.println("No hay una zona con el MOVEMENT ID ingresado.\n---------");
				}
				else
				{
					System.out.println("--------- \nDatos de la zona: \n");

					System.out.println("Nombre: " + zona.darScanombre() + "\nPerímetro: " + (zona.darShape_leng()*100) + " kilómetros\nÁrea: " + (zona.darShape_area()*10000) + " kilómetros cuadrados\nNúmero de puntos: " + zona.darCoordinates().darNumeroElementos() + "\n---------");
				}
				break;

			case 6: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}	
}