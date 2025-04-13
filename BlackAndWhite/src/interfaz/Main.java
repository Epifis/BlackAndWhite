/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import controlador.Controlador;

/**
 * Se	dispone	de	3	fichas	negras,	3	fichas	blancas	y	una	casilla	vacía,	en	la
 * posición	inicial: BBB-WWW La	casilla	vacía	esta	representada	por	un	guion
 * “-“. El objetivo consiste en	colocar	todas	las fichas blancas a la izquierda
 * de las fichas negras, independientemente de la posición de la casilla vacía.
 * Para	ello, los movimientos permitidos	son	los	siguientes: 
 * • Una ficha puede moverse	a	una	celda	adyacente	vacía. 
 * o BBB-WWW	ß BB-BWWW 
 * o BBB-WWW	à BBBW-WW
 * • Una ficha	puede	desplazarse	a	una	celda	vacía	saltando	dos fichas	como
 * máximo. o BBB-WWWß B-BBWWW o BBB-WWW	à BBBWW-W
 */
 //1=B;2=W;3=-
public class Main {
    public static void main(String[] args){
        InterfazGUI interfazApp = new InterfazGUI(new Controlador());
        interfazApp.setVisible(true);
    }
    
}
