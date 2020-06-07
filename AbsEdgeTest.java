
import org.junit.Test;
import static org.junit.Assert.*;


public class AbsEdgeTest  {
    
         
    @Test
    public void testGetu() {
       
     Vertex u= new Vertex(0,1);
     Vertex v= new Vertex(1,2);
     Edge e1=new Edge (u,v);
       
     Vertex expResult = u;
     Vertex result = e1.getu();
     assertEquals(expResult, result);
           
    }
  
    @Test
    public void testGetv() {
     Vertex u= new Vertex(0,1);
     Vertex v= new Vertex(1,2);
     Edge e1=new Edge (u,v);
       
      
      Vertex expResult =v;
      Vertex result = e1.getv();
      assertEquals(expResult, result);
       
    }

    
    @Test
    public void testVisit() {
        Vertex u= new Vertex(0,1);
        Vertex v= new Vertex(1,2);
        Edge e1=new Edge (u,v);
        e1.visit();
        assertTrue(e1.wasvisited);
             
    }

   
    @Test
    public void testUnvisit() {
          Vertex u= new Vertex(0,1);
        Vertex v= new Vertex(1,2);
        Edge e1=new Edge (u,v);
        e1.unvisit();
        assertFalse(e1.wasvisited);
    }

    
    @Test
    public void testWasVisited() {
        Vertex u= new Vertex(0,1);
        Vertex v= new Vertex(1,2);
        Edge e1=new Edge (u,v);
        e1.unvisit();
        boolean expResult = false;
        boolean result = e1.wasVisited();
        assertEquals(expResult, result);
      
    }
    
}

