package KMEANS;

import CSV.CSV;
import Table.Table;
import Table.TableWithLabels;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class KMEANSTest {
    private static Table dataTable;
    private static KMEANS kmeans;
    private static KMEANS failure;
    private static List<List<Double>> centroids;

    private static List<Double> c1;
    private static List<Double> c2;
    private static List<Double> c3;

    @BeforeAll
    static void initAll() throws FileNotFoundException{
        // crear tabla de pruebas
        CSV reader = new CSV();
        dataTable = (Table) reader.readTableWithLabels("src/Files/irisTest.csv");

        c1 = Arrays.asList(5.1,3.5,1.4,0.2);
        c2 = Arrays.asList(4.7,3.2,1.3,0.2);
        c3 = Arrays.asList(7.0,3.2,4.7,1.4);

        failure = new KMEANS(1000, 10, 1);
    }

    @BeforeEach
    void init(){
        // crear objetos
        kmeans= new KMEANS(3,10,1);
    }

    @Test
    void createRandomCentroids() {

        centroids = kmeans.createRandomCentroids(dataTable);

        assertEquals(c1, centroids.get(0));
        assertEquals(c2, centroids.get(1));
        assertEquals(c3, centroids.get(2));
    }

    @Test
    void estimate() {

        assertThrows(IllegalStateException.class, () -> failure.train(dataTable));

        kmeans.train(dataTable);
        assertEquals("Cluster 0",kmeans.estimate(c1));
        assertEquals("Cluster 1", kmeans.estimate(c2));
        assertEquals("Cluster 2", kmeans.estimate(c3));

    }


}