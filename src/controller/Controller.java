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

				modelo.cargarArchivoZonas();
				System.out.println("Archivos cargados");
				System.out.println("Numero actual de zonas: " + modelo.darTamanoZonas());
				try
				{
					System.out.println("Valor mínimo de MOVEMENT ID: " + modelo.darMinID());
					System.out.println("Valor máximo de MOVEMENT ID: " + modelo.darMaxID()  + "\n---------");
				}
				catch(Exception e)
				{}

				break;

			case 2:

				int MID;
				try
				{
					System.out.println("--------- \nDar MOVEMENT ID a buscar: ");
					MID = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar valores numéricos\n---------");
					break;
				}

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

			case 3:
				
				int minimo;
				int maximo;
				try
				{
					System.out.println("--------- \nDar MOVEMENT ID mínimo a buscar: ");
					minimo = lector.nextInt();
					System.out.println("--------- \nDar MOVEMENT ID máximo a buscar: ");
					maximo = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar valores numéricos\n---------");
					break;
				}

				Queue<ZonaUBER> zonas = modelo.consultarZonasRango(minimo, maximo);

				if (zonas == null)
				{
					System.out.println("No hay ninguna zona en el rango de MOVEMENT ID ingresado.\n---------");
				}
				else
				{
					System.out.println("--------- \nDatos de las zonas: \n");
					int contador = 1;
					while(zonas.hasNext())
					{
						System.out.println("--------- \nDato " + contador + ":\n");
						contador++;
						ZonaUBER actual = zonas.next();
						System.out.println("MOVEMENT ID: " + actual.darMID() + "\nNombre: " + actual.darScanombre() + "\nPerímetro: " + (actual.darShape_leng()*100) + " kilómetros\nÁrea: " + (actual.darShape_area()*10000) + " kilómetros cuadrados\nNúmero de puntos: " + actual.darCoordinates().darNumeroElementos() + "\n---------");
					}
				}
				break;

			case 4: 
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