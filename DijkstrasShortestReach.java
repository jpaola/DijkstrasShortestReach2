/*
 * Given an undirected graph consisting of N vertices and M edges (Vertices are
 * labelled 1 to N). Find the length of the shortest path from start position
 * (Node s) to all of the other nodes in the graph. The length of each edge in
 * the graph is 6. If a node is unreachable, the distance is assumed as -1.

 * INPUT: (STDIN) The first line contains T (1<= T <=10), denoting the number of 
 * test cases. First line of each test case has two integers N (2<= N <=3000), 
 * number of nodes in the graph and M (1<= M <=(N (N - 1))/2), the number of 
 * edges in the graph. The next M lines each consist of three space separated 
 * integers x, y, z where x and y denote the two nodes between which the edge
 * exists, z denotes the length of edge between these corresponding nodes. The
 * last line of a test case has an integer s, denoting the starting position
 * (1<= x, y, s <=N), 1<= z <=350.
 *
 * OUTPUT: For each of T test cases, the program outputs a single line consisting
 * of N-1 space separated integers, denoting the length of shortest distances of
 * the N-1 nodes from starting position s, and -1 for unreachable nodes.
 */
package ShortestPath;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Paola Jiron
 */
public class DijkstrasShortestReach
{
    public static final int INFINITY = Integer.MAX_VALUE;
    
    public static void main(String args[])
    {
        // var's used
        int T;  /* # of test cases*/
        int N;  /* # of vertices */
        int M;  /* # of edges */
        int S;  /* Source point */
        int x, y, z;  /* the vertices */

        ArrayList<Integer>[] adjList;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] weights;
        int[][] weightsArr;

        Scanner stdin = new Scanner(System.in); /* read from STDIN */

        T = stdin.nextInt();    /* get the number of test cases*/

        /* for each test case accept it's number of vertices and edges */
        for (int i = 0; i < T; i++)
        {
            N = stdin.nextInt();
            M = stdin.nextInt();

            adjList = new ArrayList[N + 1];
            
            /* instantiate 2D array with # of vertices by # of vertices */
            weightsArr = new int[N+1][N+1];

            /* initialize adjacency list with size N + 1*/
            for ( int j = 0; j < adjList.length; j++)
            {
                adjList[j] = new ArrayList<>();
            }
            
            for(int j = 0; j < weightsArr.length; ++j)
                for(int k = 0; k < weightsArr[i].length; ++k)
                    weightsArr[j][k] = INFINITY;    // initialize all vertices to infinity
            

            /* for each edge there are 2 vertices */
            for (int j = 0; j < M; ++j)
            {
                /* parse them */
                x = stdin.nextInt();
                y = stdin.nextInt();
                z = stdin.nextInt();

                /* add x to y's list and add y to x's list */
                adjList[x].add(y);
                adjList[y].add(x);  
                
                /* get weight between x and y (indicated by z-value) */
                
                if (weightsArr[x][y] > z)
                {
                    weightsArr[x][y] = z;
                    weightsArr[y][x] = z;
                }
                
            }

            /* add vertices to the Queue and check */

            S = stdin.nextInt();   /* get source vertex */

            /* keeps track on visited vertices and path weights */
            weights = new int[adjList.length];

            /* non-visited nodes are initialized to -1 */
            for (int j = 0; j < weights.length; j++)
            {
                weights[j] = -1;
            }
            weights[S] = 0;       /* S is 0 */
            pq.add(S);            /* add the start node to the Queue */

            /* while the Queue is not Empty */
            while (!pq.isEmpty())
            {
                int tempNode = pq.remove();

                /* for each neighbor of tempNode */
                for (int neighbor : adjList[tempNode])
                {
                    /* Check if we visited that neighbor... */
                    if (weights[neighbor] == -1 || weights[tempNode] + weightsArr[neighbor][tempNode] < weights[neighbor])
                    {
                        /* if not initialize cost and add it to the Queue */
                        weights[neighbor] = weights[tempNode] + weightsArr[neighbor][tempNode];
                        pq.add(neighbor); 
                    }
                   
                }
            }
            /* display the shortest path (minimum weight) for N-1 vertices */
            for (int j = 1; j < weights.length; ++j)
            {
                if (weights[j] != 0)
                {
                    System.out.print(weights[j] + " ");
                }
            }
            System.out.println();
        }
    }
} // end of shortest path class definitions