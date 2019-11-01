package model.logic;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.data_structures.Queue;
import model.data_structures.RedBlackBST;

/**
 * Definicion del modelo del mundo
 */
public class MVCModelo{

	/**
	 * Atributos del modelo del mundo
	 */
	private RedBlackBST<Integer, ZonaUBER> zonas;


	/**
	 * Constructor del modelo del mundo
	 */
	public MVCModelo()
	{
		zonas = new RedBlackBST<>();
	}

	public void cargarArchivoZonas()
	{
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("data/bogota_cadastral.json"))
		{
			Object obj = jsonParser.parse(reader);

			JSONObject archivo = (JSONObject) obj;
			JSONArray array = (JSONArray) archivo.get("features");

			for(int i = 0; i < array.size(); i++)
			{
				JSONObject actual = (JSONObject) array.get(i);

				JSONObject geometry = (JSONObject) actual.get("geometry");
				Object[] coordinates1 = ((JSONArray) (((JSONArray) ((Object[]) (((JSONArray) geometry.get("coordinates")).toArray()))[0]).toArray())[0]).toArray();

				Queue<double[]> coordenadas = new Queue<>();

				for(int j = 0; j < coordinates1.length; j++)
				{
					double[] act = {(Double) ((JSONArray) coordinates1[j]).toArray()[0], (Double) ((JSONArray) coordinates1[j]).toArray()[1]};
					coordenadas.enqueue(act);
				}				

				JSONObject properties = (JSONObject) actual.get("properties");

				ZonaUBER nuevo = new ZonaUBER((String)geometry.get("type"), coordenadas, ((Long)properties.get("cartodb_id")).intValue(), (String)properties.get("scacodigo"), ((Long)properties.get("scatipo")).intValue(), (String)properties.get("scanombre"), (double)properties.get("shape_leng"), (double)properties.get("shape_area"), Integer.parseInt((String)properties.get("MOVEMENT_ID")), (String)properties.get("DISPLAY_NAME"));
				zonas.put(nuevo.darMID(), nuevo);
			}
		}
		catch (Exception e)
		{e.printStackTrace();}
	}

	//
	//METODOS
	//
	/**
	 * Retorna el número de elementos en el modelo
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoZonas()
	{
		return zonas.size();
	}

	public int darMinID() throws NumberFormatException, Exception
	{
		return zonas.min();
	}

	public int darMaxID() throws NumberFormatException, Exception
	{
		return zonas.max();
	}

	public ZonaUBER consultarZonaPorID(int p)
	{
		return zonas.get(p);
	}

	public Queue consultarZonasRango(int min, int max)
	{		
		if(zonas.get(min)!= null && zonas.get(max) != null )
		{
			return (Queue) zonas.valuesInRange(min, max);
		}
		else
		{
			return null;
		}
	}	
}