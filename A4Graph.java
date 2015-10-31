import java.util.*;
import java.io.*;

/**
 * Assignment 4 class to render a weighted directed graph as a matrix and return path results.
 * 
 * @author Matthew Staehely
 * @version CSC 143 Winter 15
 */
public class A4Graph
{
    /**
     *  Builds a two-dimensional Array based upon the input file of a user.
     *  
     *  @param filename the name of the file to be converted to an Array.
     *  @return the two-dimensional Array created, null if none is made.
     */
    public static int[][] getMatrix(String filename) throws FileNotFoundException{
        File file = new File(filename);
        int[][] adjacencyMatrix;
        if(!file.canRead()){
            // Rather than throw an exception, informs the user they have not given
            // a valid file name.
            System.out.println("That does not appear to be a valid file name.");
            System.out.println("Please check the file and try again.");
            return null;
        } else {
            Scanner input = new Scanner(file);
            int length;
            if(input.hasNextInt()){                
                length = input.nextInt();
                if(length < 0){
                    System.out.println("Negative matrix size detected.");
                    System.out.println("Please check the file and try again.");
                    return null;
                }
            } else {
                System.out.println("Invalid matrix size detected.");
                System.out.println("Please check the file and try again.");
                return null;
            }
            adjacencyMatrix = new int[length][length];
            input.nextLine();
            while(input.hasNextLine()){
                // Standard nested for-loops to populate members of the Array.
                for(int i = 0; i < adjacencyMatrix.length; i++){
                    String string = input.nextLine().replace(",", " ");
                    Scanner reader = new Scanner(string);
                    for(int j = 0; j < adjacencyMatrix.length; j++){
                        if(!reader.hasNextInt()){
                            System.out.println("Non-integer value detected. Aborting.");
                            return null;
                        }
                        adjacencyMatrix[i][j] = reader.nextInt();
                    }
                }
            }
        }    
        return adjacencyMatrix;
    }
    
    /**
     * User facing method to check an already existing two-dimensional Array for
     * valid paths through the weighted directional graph.
     * 
     * @param adjacencyMatrix the 2D Array to be checked.
     * @param startNode the starting point.
     * @param endNode the ending point.
     * 
     * @return the String representing the path through the Array.
     */
    public static String getPath(int[][] adjacencyMatrix, int startNode, int endNode){
        String visited = "";
        return pathFinder(adjacencyMatrix, visited, startNode, endNode);
    }
    
    /**
     * Recursive method used to trace potential paths through the Array.
     * 
     * @param pathMatrix the 2D Array to be checked.
     * @param visited a String which tracks which nodes have already been checked.
     * @param start the starting point.
     * @param end the ending point.
     * 
     * @return the String represeting the path through the Array.
     * @throws IllegalArgumentException if the Array is null or either start or end 
     *  are negative, or if an array value is negative.
     */
    private static String pathFinder(int[][] pathMatrix, String visited, int start, int end){
        if(pathMatrix == null || start < 0 || end < 0) throw new IllegalArgumentException();

        if(start == end){
            return "" + start; // Reached the end.
        } else {
            visited += start; // Tracks which nodes have been visited by this call.
            for(int i = 0; i < pathMatrix.length; i++){
                if(pathMatrix[start][i] < 0)throw new IllegalArgumentException();
                if(pathMatrix[start][i] > 0 && 
                visited.lastIndexOf(String.valueOf(i)) < 0){
                    // This String is used to ensure that the choice made is a valid one and,
                    // if "no path" is returned then it is not, and the loop continues to
                    // iterate. 
                    String s = pathFinder(pathMatrix, visited, i, end);
                    if(!(s.contains("no path"))){
                        return start + "->" + s; // Potential hit, keep digging.
                    }
                }
            }
        }
        return "no path";
    }
    
    /**
     * Main method, used for testing.
     * 
     * @param args not used.
     */
    public static void main(String[] args) throws FileNotFoundException{
        int[][] matrix;
        matrix = getMatrix("graph1.txt");
        matrix = getMatrix("graph2.txt");
        matrix = getMatrix("graph3.txt");
        matrix = getMatrix("graph4.txt");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}