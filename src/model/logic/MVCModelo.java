package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.data_structures.Graph;
import model.data_structures.Haversine;

/**
 * Definicion del modelo del mundo
 */
public class MVCModelo{

	/**
	 * Atributos del modelo del mundo
	 */
	private Graph<Integer, Vertice> grafo;

	/**
	 * Constructor del modelo del mundo
	 */
	public MVCModelo()
	{
		grafo = new Graph<>(250000);
	}

	public void cargarGrafo() throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("data/bogota_vertices.txt")));
		String linea = br.readLine();
		linea = br.readLine();

		while(linea != null)
		{
			String[] datos = linea.split(";");

			if(!datos.equals(""))
			{
				Vertice nuevo = new Vertice(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]), Double.parseDouble(datos[2]), Integer.parseInt(datos[3]));

				grafo.addVertex(Integer.parseInt(datos[0]), nuevo);
			}

			linea = br.readLine();
		}
		br.close();

		br = new BufferedReader(new FileReader(new File("data/bogota_arcos.txt")));
		linea = br.readLine();

		while(linea != null)
		{
			String[] datos = linea.split(" ");

			for (int i = 1; i < datos.length; i++)
			{
				grafo.addEdge(Integer.parseInt(datos[0]), Integer.parseInt(datos[i]), 0);
			}

			linea = br.readLine();
		}
		br.close();
	}

	//----------------------------
	//METODOS
	//----------------------------

	public int darNumeroVertices()
	{
		return grafo.V();
	}

	public int darNumeroArcos()
	{
		return grafo.E();
	}

	public void escribirJSON()
	{
		JSONArray listaVertices = new JSONArray();

		for (int i = 0; i < grafo.size(); i++)
		{
			Vertice actual = grafo.getInfoVertex(i);

			if(actual != null)
			{
				JSONObject datosVertice = new JSONObject();
				datosVertice.put("id", actual.darId());
				datosVertice.put("longitud", actual.darLongitud());
				datosVertice.put("latitud", actual.darLatitud());
				datosVertice.put("MOVEMENT_ID", actual.darMID());

				JSONObject vertice = new JSONObject(); 
				vertice.put("vertice", datosVertice);

				listaVertices.add(vertice);
				System.out.println(actual.darId());
			}
		}

		try (FileWriter file1 = new FileWriter(new File("data/vertices.json"))) {

			file1.write(listaVertices.toJSONString());
			file1.flush();

		} catch (IOException e)
		{e.printStackTrace();}

		JSONArray listaArcos = new JSONArray();
		
		for (int i = 0; i < grafo.arcos.darTamano(); i++)
		{
			Arco actual = grafo.arcos.darElemento(i);

			JSONObject datosArco = new JSONObject();
			datosArco.put("origen", actual.darOrigen());
			datosArco.put("destino", actual.darDest());
			datosArco.put("costo", actual.darCosto());

			JSONObject arco = new JSONObject(); 
			arco.put("arco", datosArco);

			listaArcos.add(arco);
			System.out.println(i);
		}

		try (FileWriter file2 = new FileWriter(new File("data/arcos.json"))) {

			file2.write(listaArcos.toJSONString());
			file2.flush();

		} catch (IOException e)
		{e.printStackTrace();}		
	}
}