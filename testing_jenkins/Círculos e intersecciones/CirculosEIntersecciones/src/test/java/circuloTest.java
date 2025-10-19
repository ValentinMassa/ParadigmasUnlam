package test;

import org.junit.*;
import geometria.*;
import static org.junit.Assert.*;

public class circuloTest {

    @Test
    public void testInterseccionCirculosQueSeIntersectan() {
        Circulo c1 = new Circulo(new Punto(0, 0), 2);
        Circulo c2 = new Circulo(new Punto(1, 1), 1.5);
        assertTrue("Los círculos deberían intersectarse", c1.interseccion(c2));
    }

    @Test
    public void testInterseccionCirculosQueNoSeIntersectan() {
        Circulo c1 = new Circulo(new Punto(0, 0), 2);
        Circulo c3 = new Circulo(new Punto(10, 10), 1);
        assertFalse("Los círculos no deberían intersectarse", c1.interseccion(c3));
    }

    @Test
    public void testInterseccionCirculosTangentes() {
        Circulo c1 = new Circulo(new Punto(0, 0), 2);
        Circulo c4 = new Circulo(new Punto(4, 0), 2);
        assertTrue("Los círculos tangentes deberían intersectarse", c1.interseccion(c4));
    }

    @Test
    public void testInterseccionCirculosCoincidentes() {
        Circulo c1 = new Circulo(new Punto(0, 0), 2);
        Circulo c5 = new Circulo(new Punto(0, 0), 2);
        assertTrue("Los círculos coincidentes deberían intersectarse", c1.interseccion(c5));
    }

    @Test
    public void testInterseccionCirculosDentroDeOtro() {
        Circulo c1 = new Circulo(new Punto(0, 0), 2);
        Circulo c6 = new Circulo(new Punto(0.5, 0), 0.4);
        assertTrue("Un círculo dentro de otro debería considerarse intersección", c1.interseccion(c6));
    }
}
