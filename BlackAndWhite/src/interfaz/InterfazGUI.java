package interfaz;

import controlador.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica de usuario (GUI) para el juego
 * BlackAndWhite.
 *
 * <p>
 * Permite al usuario ingresar el estado inicial del tablero (P) y una cadena de
 * búsqueda (Q) para ejecutar una búsqueda de solución a través del controlador
 * {@code Controlador}.
 * </p>
 *
 * <p>
 * La interfaz incluye validación de entradas, ejecución de la búsqueda y
 * visualización de los pasos de solución.</p>
 *
 * @author Alexandra Tinjaca
 */
public class InterfazGUI extends JFrame {

    // Campos y variables de entrada
    private JTextField[] camposP = new JTextField[7];
    private JTextField[] camposQ = new JTextField[7];
    private int[] valoresP = new int[7];
    private int[] valoresQ = new int[7];
    private String sss;
    private Controlador ctrl;
    private int p1, p2, p3, p4, p5, p6, p7;

    // Panel para mostrar el resultado
    private JPanel panelResultado;

    public InterfazGUI(Controlador ctrl) {
        setTitle("Ingreso de Números");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.ctrl = ctrl;
        // Paneles de ingreso
        JPanel panelP = crearPanel(camposP, "Panel P (p1 a p7)");
        JPanel panelQ = crearPanel(camposQ, "Panel Q (q1 a q7)");

        // Botón de acción
        JButton botonGuardar = new JButton("Guardar y Mostrar Resultado");
        botonGuardar.addActionListener(e -> {
            if (guardarValores()) {
                mostrarPanelResultado();
            }
        });

        // Panel principal con los inputs
        JPanel panelEntrada = new JPanel(new GridLayout(3, 1));
        panelEntrada.add(panelP);
        panelEntrada.add(panelQ);
        panelEntrada.add(botonGuardar);

        add(panelEntrada, BorderLayout.NORTH);

        // Panel de resultado (inicialmente vacío)
        panelResultado = new JPanel();
        panelResultado.setBorder(BorderFactory.createTitledBorder("Resultado sss"));
        add(panelResultado, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Crea un panel de ingreso de texto para capturar valores del estado del
     * juego.
     *
     * <p>
     * Este panel incluye 7 campos de texto numerados que aceptan solo los
     * valores 1, 2 o 3.</p>
     *
     * @param campos Arreglo donde se almacenarán las referencias a los campos
     * de texto.
     * @param titulo Título que se mostrará en el borde del panel.
     * @return Panel Swing configurado con los campos de entrada y título.
     */
    private JPanel crearPanel(JTextField[] campos, String titulo) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.setLayout(new GridLayout(1, 7, 5, 5));
        for (int i = 0; i < 7; i++) {
            JTextField campo = new JTextField(1);
            campo.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!(c == '1' || c == '2' || c == '3') || campo.getText().length() >= 1) {
                        e.consume();
                    }
                }
            });
            campos[i] = campo;
            panel.add(campo);
        }
        return panel;
    }

    /**
     * Obtiene y valida los valores ingresados por el usuario en los campos de
     * texto.
     *
     * <p>
     * Extrae los valores del panel P para definir el estado inicial del juego,
     * y construye la cadena {@code sss} a partir del panel Q.</p>
     *
     * <p>
     * Si la validación es exitosa, invoca el método {@code crearBusqueda} del
     * controlador.</p>
     *
     * @return {@code true} si todos los valores fueron ingresados
     * correctamente; {@code false} si algún campo está incompleto o tiene
     * valores inválidos.
     */
    private boolean guardarValores() {
        try {
            // Guardar valores del primer panel en variables p1...p7
            p1 = Integer.parseInt(camposP[0].getText());
            p2 = Integer.parseInt(camposP[1].getText());
            p3 = Integer.parseInt(camposP[2].getText());
            p4 = Integer.parseInt(camposP[3].getText());
            p5 = Integer.parseInt(camposP[4].getText());
            p6 = Integer.parseInt(camposP[5].getText());
            p7 = Integer.parseInt(camposP[6].getText());

            // Construir el string sss con los valores del segundo panel
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                int valorQ = Integer.parseInt(camposQ[i].getText());
                sb.append(valorQ);
                if (i < 6) {
                    sb.append("");
                }
            }
            sss = sb.toString();
            System.out.println("sss= " + sss);
            this.ctrl.crearBusqueda(p1, p2, p3, p4, p5, p6, p7, sss);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Completa todas las casillas con valores entre 1 y 3.");
            return false;
        }
    }

    /**
     * Muestra en el panel de resultados los pasos de la solución encontrada por
     * el algoritmo.
     *
     * <p>
     * Ejecuta las acciones correspondientes en el objeto {@code BlackAndWhite}
     * a medida que recorre la lista de pasos obtenida desde
     * {@code SearchStateSpaces.solve()}.</p>
     *
     * <p>
     * La solución se muestra en un {@code JTextArea} dentro del panel central
     * de la interfaz.</p>
     */
    private void mostrarPanelResultado() {
        panelResultado.removeAll(); // limpiar contenido anterior
        ArrayList<String> solucion = ctrl.getSss().solve();
        JTextArea area = new JTextArea();
        area.setEditable(false);
        System.out.println("Estado inicial: " + ctrl.getBlackAndWhite().state());
        if (solucion != null && !solucion.isEmpty()) {
            System.out.println("Solución encontrada en " + solucion.size() + " pasos:");
            for (int i = 0; i < solucion.size(); i++) {
                String accion = solucion.get(i).split("::")[0].replaceAll("[^0-9]", "");
                if (!accion.isEmpty()) {
                    ctrl.getBlackAndWhite().action(Integer.parseInt(accion));
                }
                area.append("Paso " + (i + 1) + ": " + solucion.get(i) + "\n");
                System.out.println("Paso " + (i + 1) + ": " + solucion.get(i));
                System.out.println(ctrl.getBlackAndWhite().imprimirConBAW().toString());
            }
        } else {
            System.out.println("No se encontró solución.");
        }

        panelResultado.add(area);
        panelResultado.revalidate();
        panelResultado.repaint();
        pack();
    }

}
