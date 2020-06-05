import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class VertexTest {

    @Test
    public void createVertex() {
        Vertex v = new Vertex(10,10, 10);
        Assert.assertNotNull(v);
        Assert.assertFalse(v.wasVisited());
        Assert.assertEquals(10,v.getx());
        Assert.assertEquals(10,v.gety());
    }
    @Test
    public void addNeighbour(){
        Vertex ver=new Vertex(1,1,1);
        ver.addNeighbour(ver);
        Assert.assertEquals(1,ver.getNeighboursNumber());
    }
    @Test
    public void removeNeighbour(){
        Vertex ver=new Vertex(1,1,1);
        ver.addNeighbour(ver);
        ver.removeNeighbour(ver);
        Assert.assertEquals(0,ver.getNeighboursNumber());


    }
    @Test
    public void setCircleCoordinates(){
        Vertex ver=new Vertex(1,1);
        int a=1;
        ver.setCircleCoordinates(1);
        Assert.assertEquals(100,ver.getx());
        Assert.assertEquals(0,ver.gety());
    }
    @Test
    public void hasThisNeighbour(){
        Vertex ver=new Vertex(1,1,1);
        Assert.assertFalse(ver.hasThisNeighbour(ver));
        ver.addNeighbour(ver);
        Assert.assertTrue(ver.hasThisNeighbour(ver));
        ver.removeNeighbour(ver);
        Assert.assertFalse(ver.hasThisNeighbour(ver));

    }
    @Test
    public void createVertex2(){
        Vertex ver = new Vertex(1,1);
        Assert.assertNotNull(ver);
        Assert.assertEquals(100,ver.getx());
        Assert.assertEquals(0,ver.gety());


    }



}