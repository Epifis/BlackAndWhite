package mundo;

import gfutria.Logic;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase {@code BlackAndWhite} representa una secuencia de 7 posiciones que
 * pueden contener fichas blancas (W), negras (B) o vacías (-). Se puede cambiar
 * el contenido de dos posiciones adyacentes o cercanas bajo ciertas reglas.
 *
 * <p>
 * Extiende la clase {@code Logic}, permitiendo que se utilice dentro de una
 * lógica de juego o motor genérico.</p>
 *
 * <p>
 * Valores posibles para las posiciones:</p>
 * <ul>
 * <li>1: Ficha negra (B)</li>
 * <li>2: Ficha blanca (W)</li>
 * <li>3: Vacío (-)</li>
 * </ul>
 *
 * @author Alexandra Tinjaca
 */
public class BlackAndWhite extends Logic {

    private int p1, p2, p3, p4, p5, p6, p7;

    /**
     * Constructor por defecto. Inicializa la secuencia con el patrón: BBB-WWW
     */
    public BlackAndWhite() {
        p1 = 1;
        p2 = 1;
        p3 = 1;
        p4 = 3;
        p5 = 2;
        p6 = 2;
        p7 = 2;
    }

    /**
     * Constructor personalizado para establecer manualmente los valores
     * iniciales de las 7 posiciones.
     *
     * @param p1 Valor de la posición 1 (1=B, 2=W, 3=-)
     * @param p2 Valor de la posición 2
     * @param p3 Valor de la posición 3
     * @param p4 Valor de la posición 4
     * @param p5 Valor de la posición 5
     * @param p6 Valor de la posición 6
     * @param p7 Valor de la posición 7
     */
    public BlackAndWhite(int p1, int p2, int p3, int p4, int p5, int p6, int p7) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
    }

    /**
     * Intenta intercambiar los valores entre las posiciones {@code i} y
     * {@code j}, siempre que una de ellas sea un espacio vacío (valor 3), estén
     * a una distancia máxima de 2 y sean diferentes entre sí.
     *
     * @param i índice de la primera posición (entre 1 y 7)
     * @param j índice de la segunda posición (entre 1 y 7)
     */
    public void cambiarPx(int i, int j) {
        // Validaciones de i y j
        if (i < 1 || i > 7 || j < 1 || j > 7) {
            System.out.println("Error: i y j deben estar entre 1 y 7.");
            return;
        }
        if (Math.abs(i - j) > 2) {
            System.out.println("Error: La diferencia entre i y j no puede ser mayor a 2.");
            return;
        }
        if (i == j) {
            System.out.println("Error: i y j deben ser diferentes.");
            return;
        }

        try {
            // Obtener los campos correspondientes a p+i y p+j
            Field fieldI = this.getClass().getDeclaredField("p" + i);
            Field fieldJ = this.getClass().getDeclaredField("p" + j);

            fieldI.setAccessible(true);
            fieldJ.setAccessible(true);

            // Obtener valores actuales
            int valorI = (int) fieldI.get(this);
            int valorJ = (int) fieldJ.get(this);

            // Nueva verificación: Solo se ejecuta si i o j valen 3
            if (valorI == 3) {
                fieldI.set(this, valorJ);
                fieldJ.set(this, 3);
                System.out.println("Cambio aplicado: p" + i + " = " + valorJ + ", p" + j + " = 3");
            } else if (valorJ == 3) {
                fieldJ.set(this, valorI);
                fieldI.set(this, 3);
                System.out.println("Cambio aplicado: p" + j + " = " + valorI + ", p" + i + " = 3");
            } else {
                System.out.println("No se realizó ningún cambio, ya que ni i ni j valen 3.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve una representación en cadena de las 7 posiciones usando los
     * símbolos B, W y -.
     *
     * @return cadena con los caracteres representativos de cada posición
     */
    public String imprimirConBAW() {
        return "" + ((p1 == 3) ? ("-") : ((p1 == 1) ? "B" : "W")) + "" + ((p2 == 3) ? ("-") : ((p2 == 1) ? "B" : "W")) + ""
                + ((p3 == 3) ? ("-") : ((p3 == 1) ? "B" : "W")) + ""
                + ((p4 == 3) ? ("-") : ((p4 == 1) ? "B" : "W")) + ""
                + ((p5 == 3) ? ("-") : ((p5 == 1) ? "B" : "W")) + ""
                + ((p6 == 3) ? ("-") : ((p6 == 1) ? "B" : "W")) + "" + ((p7 == 3) ? ("-") : ((p7 == 1) ? "B" : "W"));
    }

    /**
     * Crea una copia del objeto actual a partir de otro objeto
     * {@code BlackAndWhite}.
     *
     * @param logic objeto a clonar
     * @return nueva instancia de {@code BlackAndWhite} con los mismos valores
     * de estado
     */
    @Override
    public Logic cloneObject(Logic logic) {
        BlackAndWhite obj = (BlackAndWhite) logic;
        BlackAndWhite clone = new BlackAndWhite();
        clone.p1 = obj.p1;
        clone.p2 = obj.p2;
        clone.p3 = obj.p3;
        clone.p4 = obj.p4;
        clone.p5 = obj.p5;
        clone.p6 = obj.p6;
        clone.p7 = obj.p7;
        return clone;
    }

    /**
     * Devuelve el estado interno de las posiciones como una cadena de números
     * del 1 al 3.
     *
     * @return estado numérico de las posiciones
     */
    @Override
    public String state() {
        return "" + p1 + "" + p2 + "" + p3 + "" + p4 + "" + p5 + "" + p6 + "" + p7;
    }

    /**
     * Ejecuta una acción predeterminada identificada por un número entero. Cada
     * número representa un movimiento entre dos posiciones.
     *
     * @param i número de acción a ejecutar (entre 1 y 14)
     */
    @Override
    public void action(int i) {
        switch (i) {
            case 1:
                cambiarPx(1, 2);
                break;
            case 2:
                cambiarPx(2, 1);
                break;
            case 3:
                cambiarPx(3, 1);
                break;
            case 4:
                cambiarPx(1, 3);
                break;
            case 5:
                cambiarPx(2, 3);
                break;
            case 6:
                cambiarPx(3, 2);
                break;
            case 7:
                cambiarPx(3, 4);
                break;
            case 8:
                cambiarPx(4, 2);
                break;
            case 9:
                cambiarPx(5, 3);
                break;
            case 10:
                cambiarPx(4, 5);
                break;
            case 11:
                cambiarPx(6, 4);
                break;
            case 12:
                cambiarPx(5, 6);
                break;
            case 13:
                cambiarPx(7, 5);
                break;
            case 14:
                cambiarPx(6, 7);
                break;
            default:
                System.out.println("Valor de i fuera de rango.");
        }
    }
}
