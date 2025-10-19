import geometria.Circulo;
import geometria.Punto;

public class App {
    public static void main(String[] args) throws Exception {
        
        Circulo c1 = new Circulo(new Punto(0, 0), 1);
        Circulo c2 = new Circulo(new Punto(1, 1), 1.5);
        System.out.println(c1.interseccion(c2)); // true
    
    }
}
