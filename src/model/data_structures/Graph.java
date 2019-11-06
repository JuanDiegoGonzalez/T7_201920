package model.data_structures;

import java.util.NoSuchElementException;

import model.logic.Arco;
import model.logic.Vertice;

/** 
 * Implementación tomada de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
 * Consultado el 04/11/19
 * Disponible en https://algs4.cs.princeton.edu/code/
 */
public class Graph<K,V> implements IGraph<K, V>
{
	private static final String NEWLINE = System.getProperty("line.separator");

	private int V;
	private int E;
	public boolean[] Marked;
	private Bag<V>[] adj;
	private  ArregloDinamico<Arco> arcos;

	/**
	 * Initializes an empty graph with {@code V} vertices and 0 edges.
	 * param V the number of vertices
	 *
	 * @param  V number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public Graph(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		arcos = new ArregloDinamico<>(1);
		this.V = V;
		this.E = 0;
		adj = new Bag[250000];
		for (int v = 0; v < 250000; v++) {
			adj[v] = new Bag<V>();
		}
	}

	/**  
	 * Initializes a graph from the specified input stream.
	 * The format is the number of vertices <em>V</em>,
	 * followed by the number of edges <em>E</em>,
	 * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
	 *
	 * @param  in the input stream
	 * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
	 * @throws IllegalArgumentException if the number of vertices or edges is negative
	 * @throws IllegalArgumentException if the input stream is in the wrong format
	 */
	public Graph(Integer in) {
		try {
			this.V = in;
			if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
			adj = (Bag<V>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<V>();
			}
			int E = in;
			if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
			for (int i = 0; i < E; i++) {
				int v = in;
				int w = in;
				validateVertex(v);
				validateVertex(w);
				//addEdge(v, w); 
			}
		}
		catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor", e);
		}
	}

	/**
	 * Returns the number of vertices in this graph.
	 * @return the number of vertices in this graph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in this graph.
	 * @return the number of edges in this graph
	 */
	public int E() {
		return E;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v > V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V));
	}

	/**
	 * Returns the vertices adjacent to vertex {@code v}.
	 * @param  v the vertex
	 * @return the vertices adjacent to vertex {@code v}, as an iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<V> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	@Override
	public void addEdge(K idVertexIni, K idVertexFin, double cost) {
		validateVertex((int) idVertexIni);
		validateVertex((int) idVertexFin);
		E++;
		System.out.println(idVertexIni);

		System.out.println(adj[(int) idVertexIni].size());
		System.out.println(adj[(int) idVertexFin].size());

		if(adj[(int) idVertexIni].size() != 0 && adj[(int) idVertexFin].size() != 0)
		{
			Vertice nuevo1 = (Vertice) adj[(int) idVertexFin].iterator().next();
			Vertice nuevo2 = (Vertice) adj[(int) idVertexIni].iterator().next();


			adj[(int) idVertexIni].add((V) new Vertice((int)idVertexFin, nuevo1.darLongitud(), nuevo1.darLongitud(), nuevo1.darMID()));
			adj[(int) idVertexFin].add((V) new Vertice((int)idVertexIni, nuevo2.darLongitud(), nuevo2.darLongitud(), nuevo2.darMID()));			System.out.println(arcos.darTamano() + "\n----------------");

			arcos.agregar(new Arco((Integer)idVertexIni, (Integer)idVertexFin, cost));
		}
	}

	@Override
	public V getInfoVertex(K idVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfoVertex(K idVertex, V infoVertex) {
		// TODO Auto-generated method stub

	}
	double a ; 
	@Override
	public double getCostArc(K idVertexIni, K idVertexFin) {
		// TODO Auto-generated method stub
		return a;
	}

	@Override
	public void setCostArc(K idVertexIni, K idVertexFin, double cost) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addVertex(K idVertex, V infoVertex) {
		// TODO Auto-generated method stub
		adj[(int) idVertex].add(infoVertex);

		if((int) idVertex > V)
		{
			V = (int) idVertex;
		}
	}

	@Override
	public Iterable<K> adj(K idVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void uncheck() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dfs(int s) {
		NonrecursiveDFS x = new NonrecursiveDFS(this, (int) s);
		Marked = x.marked;

	}

	@Override
	public int cc() {

		int count = 0; 
		for (int v = 0; v < V(); v++) {
			if (!Marked[v]) {
				dfs( v);
				count++;
			}
		}
		return count; 
	}

	@Override
	public Iterable<K> getCC(K idVertex) {
		// TODO Auto-generated method stub
		return null;
	}
}