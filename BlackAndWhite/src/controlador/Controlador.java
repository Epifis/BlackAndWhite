package controlador;

import gfutria.SearchStateSpaces;
import java.util.ArrayList;
import mundo.BlackAndWhite;

public class Controlador {

    private BlackAndWhite blackAndWhite;
    private SearchStateSpaces sss;

    public Controlador() {

    }

    /**
     * Crea e inicializa una búsqueda sobre un estado personalizado del juego
     * BlackAndWhite.
     *
     * <p>
     * Este método configura un nuevo objeto {@code BlackAndWhite} con los
     * valores iniciales proporcionados y lo enlaza con una instancia de
     * {@code SearchStateSpaces}, que se encargará de realizar la búsqueda en el
     * espacio de estados.</p>
     *
     * @param p1 Valor de la posición 1 del estado inicial (1=B, 2=W, 3=-)
     * @param p2 Valor de la posición 2 del estado inicial
     * @param p3 Valor de la posición 3 del estado inicial
     * @param p4 Valor de la posición 4 del estado inicial
     * @param p5 Valor de la posición 5 del estado inicial
     * @param p6 Valor de la posición 6 del estado inicial
     * @param p7 Valor de la posición 7 del estado inicial
     * @param sss Nombre o tipo de estrategia de búsqueda (por ejemplo, "BFS",
     * "DFS", "A*", etc.)
     */
    public void crearBusqueda(int p1, int p2, int p3, int p4, int p5, int p6, int p7, String sss) {
        this.blackAndWhite = new BlackAndWhite(p1, p2, p3, p4, p5, p6, p7);
        this.sss = new SearchStateSpaces(sss, this.blackAndWhite, 30);
        System.out.println("Estado inicial: " + blackAndWhite.state());
    }

    public BlackAndWhite getBlackAndWhite() {
        return blackAndWhite;
    }

    public SearchStateSpaces getSss() {
        return sss;
    }

}
