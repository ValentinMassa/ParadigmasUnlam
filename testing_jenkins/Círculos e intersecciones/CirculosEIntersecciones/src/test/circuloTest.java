package test;

import org.junit.*;
import geometria.*;

public class circuloTest {
    @Test
    public void Test(){
        
    }
    public static void main(String[] args) {
        // Test 1: Intersección positiva
        Circulo c1 = new Circulo(new Punto(0, 0), 1);
        Circulo c2 = new Circulo(new Punto(1, 1), 1.5);
        System.out.println("Test 1: " + (c1.interseccion(c2) ? "PASSED" : "FAILED"));

        // Test 2: Intersección negativa
        Circulo c3 = new Circulo(new Punto(10, 10), 1);
        System.out.println("Test 2: " + (!c1.interseccion(c3) ? "PASSED" : "FAILED"));

        // Test 3: Circulos tangentes
        Circulo c4 = new Circulo(new Punto(2, 0), 1);
        System.out.println("Test 3 (Tangentes): " + (c1.interseccion(c4) ? "PASSED" : "FAILED"));

        // Test 4: Circulos coincidentes
        Circulo c5 = new Circulo(new Punto(0, 0), 1);
        System.out.println("Test 4 (Coincidentes): " + (c1.interseccion(c5) ? "PASSED" : "FAILED"));

        // Test 5: Circulo dentro de otro
        Circulo c6 = new Circulo(new Punto(0.5, 0), 0.4);
        System.out.println("Test 5 (Uno dentro de otro): " + (c1.interseccion(c6) ? "PASSED" : "FAILED"));
    }
}
