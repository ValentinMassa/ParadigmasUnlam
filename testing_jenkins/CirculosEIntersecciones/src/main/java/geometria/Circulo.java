package geometria;

public class Circulo {
    private Punto punto;
    private double radio;

    public Circulo(Punto punto, double radio) {
        this.punto = punto;
        this.radio = radio;
    }

    public static double calcularDistanciaEntreCirculos(Circulo c1, Circulo c2) {
        return (Math.sqrt( 
                    Math.pow( (c1.punto.getX() - c2.punto.getX()),2) + 
                    Math.pow( (c1.punto.getY() - c2.punto.getY()),2)
                ));
    }

    public double getRadio() {
        return this.radio;
    }

    public boolean interseccion(Circulo otroCirculo) {
        return (Circulo.calcularDistanciaEntreCirculos(this, otroCirculo) <= (radio + otroCirculo.getRadio()));
    }
}
