
import junit.framework.TestCase;

/**
 * Example of a JUnit test class for the A4Graph project.
 * This class is intended to demonstrate how JUnit tests can be written.
 * It does not perform comprehensive testing.
 * 
 * This test class is written using JUnit3 syntax.  Be aware that you may see 
 * JUnit4 syntax in other references.  You may use either in your projects.
 * 
 * @author CSC 143 vo
 * @version W'15
 */
public class SampleJUnitTestClassForA4Graph extends TestCase {
  
  // master test matrix [static variables are OK in a test class]
  private static final int[][] master = 
  { {0,0,5,4,0,0,0},
    {3,0,0,9,0,0,0},
    {2,0,0,0,0,4,0},
    {0,0,0,0,0,8,2},
    {1,0,2,0,0,7,0},
    {0,0,0,0,0,0,0},
    {0,2,0,0,0,0,0},
  };
  
  // working test matrix [instance variables are OK in a test class]
  private int[][] matrix;
  
  // return a copy of the master matrix array [private helper/utility methods are OK in a test class]
  private int[][] getMatrixCopy(int[][] orig) {
    int[][] result = new int[orig.length][orig[0].length];
    for (int i = 0; i < orig.length; i++)
      for (int j = 0; j < orig[0].length; j++)
        result[i][j] = orig[i][j];
    return result;
  }
  
  // get a fresh copy of the master matrix before each test method is called
  protected void setUp() {
    matrix = getMatrixCopy(master);
  }
  
  // getPath method basic test for correct path using examples from instructions  
  public void testGetPathGood() {
    String result = A4Graph.getPath(matrix, 1, 1); // same start and target
    assertEquals("test for correct path (1,1)", "1", result);
    result = A4Graph.getPath(matrix, 6, 1); // only one possible path
    assertEquals("test for correct path (6,1)", "6->1", result);
  }
  
  // getPath method basic test for when there is no path 
  public void testGetPathBlocked() {
    String result = A4Graph.getPath(matrix, 1, 4); // no path
    assertEquals("test for no path (1,4)", "no path", result);
  }
  
  // getPath method second test -- multiple paths possible 
  public void testGetPathAlternate() {
    String result = A4Graph.getPath(matrix, 1, 3); // no path
    assertTrue("test for correct path (actual="+result+")", 
               result.equals("1->3") || result.equals("1->0->3"));  
  }
  
  // getPath method test for proper exception thrown when a bad parameter is used 
  public void testGetPathBadParams() {
    try {
      String result = A4Graph.getPath(matrix, 3, -1);
      // should not get here
      fail("expected IllegalArugmentException not thrown");
    } catch (IllegalArgumentException e) {
      // no action -- this is the expected result
    } catch (Exception e) {
      fail("incorrect exception thrown (expected IllegalArugmentException): " + e.getClass());
    }
  }
  
  // getPath method test for proper exception thrown when the array parameter is null 
  public void testGetPathNullParameter() {
    try {
      String result = A4Graph.getPath(null, 1, 2);
      // should not get here
      fail("expected IllegalArugmentException not thrown");
    } catch (IllegalArgumentException e) {
      // no action -- this is the expected result
    } catch (Exception e) {
      fail("incorrect exception thrown: (expected IllegalArugmentException)" + e.getClass());
    }
  }
  
  // getMatrix "non-test" -- simply verifies that the method is present with the correct method signature 
  public void testMatrixBasic() {
    try {
      int[][] result = A4Graph.getMatrix("graph1.txt"); 
      // gets here if the sample data file is 
      System.out.println(java.util.Arrays.deepToString(result));
    } catch (Exception e) {
      // gets here if there is any problem runtime problem
      System.out.println(e);
    }
  }
  
}