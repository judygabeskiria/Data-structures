/******************************************************************************
 *  Compilation:  javac Graph.java        
 *  Execution:    java Graph input.txt
 *  Dependencies: Bag.java Stack.java In.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                http://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                http://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java Graph tinyG.txt
 *  13 vertices, 13 edges 
 *  0: 6 2 1 5 
 *  1: 0 
 *  2: 0 
 *  3: 5 4 
 *  4: 5 6 3 
 *  5: 3 4 0 
 *  6: 0 4 
 *  7: 8 
 *  8: 7 
 *  9: 11 10 12 
 *  10: 9 
 *  11: 9 12 
 *  12: 11 9 
 *
 *  % java Graph mediumG.txt
 *  250 vertices, 1273 edges 
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15 
 *  1: 220 203 200 194 189 164 150 130 107 72 
 *  2: 141 110 108 86 79 51 42 18 14 
 *  ...
 *  
 ******************************************************************************/

import java.util.NoSuchElementException;
//import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.algs4.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *  The {@code Graph} class represents an undirected graph of vertices
 *  named 0 through <em>V</em> - 1.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  By convention, a self-loop <em>v</em>-<em>v</em> appears in the
 *  adjacency list of <em>v</em> twice and contributes two to the degree
 *  of <em>v</em>.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which 
 *  is a vertex-indexed array of {@link Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the vertices adjacent to a given vertex, which takes
 *  time proportional to the number of such vertices.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
 
 
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    
    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
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
    public Graph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w); 
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        } 
        
    }


    /**
     * Initializes a new graph that is a deep copy of {@code G}.
     *
     * @param  G the graph to copy
     */
    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
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


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
//////
public static class GraphProperties {
 
 
 //If v was visited, marked[v]=-1, if it is root =0, else it will be positive integer which is shortest path length to root.
 private int[] marked;
 // e is eccentricity of a vertex
 private int[] e;
 // p is used to store predecessor of every vertex. 
 private int[] p;
 
 private Graph myGraph;
 private int center; 
 
 public GraphProperties(Graph G){
  e = new int[G.V()];
  p = new int[G.V()];
  marked = new int[G.V()];
  this.myGraph = G;
  
  
  for(int i=0;i<myGraph.V();i++){
   breathFirstSearch(i,e);
  }
 }
 
 public int eccentricity(int v){
  return e[v];
 }
 
 
 // The diameter of a graph= max e of vertices of a graph
 
 public int diameter(){
  int max = Integer.MIN_VALUE;
  for(int i=0;i<e.length;i++){
   if(e[i] > max){
    max = e[i];
   }
  }
  return max;
 }
 
 // The radius of a graph is= min e
 //center->vertex with e=radius()
 public int radius(){
  int min = Integer.MAX_VALUE;
  for(int i=0;i<e.length;i++){
   if(e[i] < min){
    min = e[i];
    center = i;
   }
  }
  return min;
 }
 
 
 
 // The eccentricity of a vertex v is the the length of the shortest path from that vertex to the farthest vertex from v 
 
 private void breathFirstSearch(int v,int[] e){
  
  // none of the vertices is marked in the beginning
  for(int i=0;i<myGraph.V();i++){
   marked[i] = -1;
  }
  // we search through the linked list
  Queue<Integer> q = new LinkedList<Integer>();
  int eccen = 0;
  marked[v] = 0;
  q.add(v);
  while(!q.isEmpty()){
   int u = q.poll(); //removes first element
   for(Integer w: myGraph.adj(u)){
    if(marked[w] <0){
     marked[w] = marked[u] +1;
     eccen = marked[w];
     // w's parent u was found.
     p[w] = u;
     q.add(w);
    }
    }
   }
  
  e[v] = eccen;
 }
 
 
 public int getCenter() {
  return center;
 }}
/////
 public static void main(String[] args) {
     In in = new In(args[0]);
 // File file = new File("d:\\tinyG6.txt");
 // try {
  // Scanner in = new Scanner(file);
   Graph G = new Graph(in);
   System.out.println(G);
   GraphProperties gp = new GraphProperties(G);
   System.out.println("The diameter is: " + gp.diameter());
   System.out.println("The radius is: " +gp.radius());
   System.out.println("The center is vertex No." + gp.getCenter());
   
  //}
  //catch(FileNotFoundException e){
  // e.printStackTrace();
  //}
}

/////

  
 

}
///////


