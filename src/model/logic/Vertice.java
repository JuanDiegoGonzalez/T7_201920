package model.logic;

public class Vertice
{
	private int id;

	private double longitud, latitud;

	private int MOVEMENT_ID;

	public Vertice(int pId, double pLongitud, double pLatitud, int pMOVEMENT_ID)
	{
		id = pId;
		longitud = pLongitud;
		latitud = pLatitud;
		MOVEMENT_ID = pMOVEMENT_ID;
	}
	
	public int darId()
	{
		return id;
	}
	
	public double darLongitud()
	{
		return longitud;
	}
	
	public double darLatitud()
	{
		return latitud;
	}
	
	public int darMID()
	{
		return MOVEMENT_ID;
	}
}