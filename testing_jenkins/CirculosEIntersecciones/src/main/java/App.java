import geometria.*;

public class App {
    public static void main(String[] args) {
        Circulo c1 = new Circulo(new Punto(0, 0), 2);
        Circulo c2 = new Circulo(new Punto(1, 1), 1.5);
        
        System.out.println("Test de intersección de círculos");
        System.out.println("Círculo 1: centro(0,0) radio=2");
        System.out.println("Círculo 2: centro(1,1) radio=1.5");
        System.out.println("¿Se intersectan? " + c1.interseccion(c2));
    }
}
