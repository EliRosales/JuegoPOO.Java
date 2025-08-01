/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegopoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL; //Ruta a la imagen de los personajes 

// Importamos nuestras clases personalizadas
import personajes.*;

/**
 *
 * @author ab189
 */
public class JuegoPOO extends JFrame {
    // Componentes de la interfaz
    private JComboBox<String> comboJugador1, comboJugador2;
    private JButton btnIniciar, btnAtacar;
    private JTextArea areaBatalla;
    private JLabel ImagenJugador1, ImagenJugador2;
    
    // Objetos de los personajes
    private Personaje jugador1, jugador2;

    // Control del turno
    private boolean turnoJugador1 = true;

    // Constructor del juego
    public JuegoPOO() {
        // Configuración básica del JFrame
        setTitle("Arena de Clases - Batalla POO");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Menús desplegables para elegir personajes
        comboJugador1 = new JComboBox<>(new String[]{"Guerrero", "Mago", "Arquero", "Hechizero", "Toallin"});
        comboJugador2 = new JComboBox<>(new String[]{"Guerrero", "Mago", "Arquero", "Hechizero", "Toallin"});

        // Botones para iniciar el juego y atacar
        btnIniciar = new JButton("Iniciar Batalla");
        btnAtacar = new JButton("Atacar");
        btnAtacar.setEnabled(false); // Desactivado al inicio

        // Área de texto para mostrar el combate
        
        areaBatalla = new JTextArea(40, 40);
        areaBatalla.setEditable(false); // Solo lectura
        
        //imagenes 
        ImagenJugador1 = new JLabel();
        ImagenJugador2 = new JLabel();
        
        add(ImagenJugador1);
        add(ImagenJugador2);

        // Agregamos todos los componentes a la ventana
        add(new JLabel("Jugador 1:"));
        add(comboJugador1);
        add(new JLabel("Jugador 2:"));
        add(comboJugador2);
        add(btnIniciar);
        add(btnAtacar);
        add(new JScrollPane(areaBatalla)); // Scroll para el área de texto

        // Acción al hacer clic en "Iniciar Batalla"
        btnIniciar.addActionListener(e -> iniciarBatalla());

        // Acción al hacer clic en "Atacar"
        btnAtacar.addActionListener(e -> realizarAtaque());

        setVisible(true); // Mostrar la ventana
    }

    // Inicializa los personajes y habilita el botón de ataque
    private void iniciarBatalla() {
        jugador1 = crearPersonaje(comboJugador1.getSelectedItem().toString(), "Jugador 1");
        jugador2 = crearPersonaje(comboJugador2.getSelectedItem().toString(), "Jugador 2");
        
         ImageIcon Icono1 = null;
    ImageIcon icono2 = null;

    try {
        // Intentar cargar la imagen de Jugador 1
        URL url1 = getClass().getResource("/" + jugador1.getRutaImagen());
        if (url1 != null) {
            Icono1 = new ImageIcon(url1);
            // Muestra la URL Donde se encontro la imagen 
            System.out.println("URL encontrada para Jugador 1: " + url1);
        } else {
            System.err.println("ERROR: No se encontró la URL para la imagen de Jugador 1: /" + jugador1.getRutaImagen());
            // Si no se encuentra como recurso de classpath, intenta cargarla como archivo directo
            Icono1 = new ImageIcon(jugador1.getRutaImagen());
            System.out.println("Intentando cargar la imagen de Jugador 1 como archivo directo: " + jugador1.getRutaImagen());
        }
        
        // Intentar cargar la imagen de Jugador 2 
        URL url2 = getClass().getResource("/" + jugador2.getRutaImagen());
        if (url2 != null) {
            icono2 = new ImageIcon(url2);
            System.out.println("URL encontrada para Jugador 2: " + url2);
        } else {
            System.err.println("ERROR: No se encontró la URL para la imagen de Jugador 2: /" + jugador2.getRutaImagen());
            icono2 = new ImageIcon(jugador2.getRutaImagen());
            System.out.println("Intentando cargar la imagen de Jugador 2 como archivo directo: " + jugador2.getRutaImagen());
        }

    } catch (Exception e) {
        System.err.println("Error general al cargar las imágenes: " + e.getMessage());
        e.printStackTrace();
    }

    // Verificar si los iconos se cargaron correctamente antes de escalarlos y asignarlos
    if (Icono1 != null && Icono1.getImageLoadStatus() == MediaTracker.COMPLETE) {
        Image Imagen1 = Icono1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImagenJugador1.setIcon(new ImageIcon(Imagen1));
    } else {
        // Si la imagen no se cargó, asegúrate de que el JLabel esté vacío
        ImagenJugador1.setIcon(null);
        System.err.println("La imagen de Jugador 1 no se pudo cargar o es inválida.");
    }

    if (icono2 != null && icono2.getImageLoadStatus() == MediaTracker.COMPLETE) {
        Image Imagen2 = icono2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImagenJugador2.setIcon(new ImageIcon(Imagen2));
    } else {
        ImagenJugador2.setIcon(null);
        System.err.println("La imagen de Jugador 2 no se pudo cargar o es inválida.");
    }
    
    // Muestra el registro de la batalla
    areaBatalla.setText("¡Batalla iniciada!\nTurno del Jugador 1\n");
    areaBatalla.append("Jugador 1: " + jugador1.getNombre() + "\n");
    areaBatalla.append("Jugador 2: " + jugador2.getNombre() + "\n");

       
        btnAtacar.setEnabled(true);
    }

    // Maneja el turno de ataque entre los jugadores
    private void realizarAtaque() {
        if (turnoJugador1) {
            jugador1.atacar(jugador2); // Jugador 1 ataca a 2
            areaBatalla.append("Jugador 1 atacó a Jugador 2. Vida restante: " + jugador2.getVida() + "\n");
        } else {
            jugador2.atacar(jugador1); // Jugador 2 ataca a 1
            areaBatalla.append("Jugador 2 atacó a Jugador 1. Vida restante: " + jugador1.getVida() + "\n");
        }

        // Si alguno pierde toda la vida, termina el juego
        if (!jugador1.estaVivo() || !jugador2.estaVivo()) {
            String ganador = jugador1.estaVivo() ? "Jugador 1" : "Jugador 2";
            areaBatalla.append("¡Fin del juego! Ganador: " + ganador + "\n");
            btnAtacar.setEnabled(false); // Desactivar ataques
        }

        turnoJugador1 = !turnoJugador1; // Cambiar de turno
    }

    // Crea un personaje según el nombre del tipo seleccionado
    private Personaje crearPersonaje(String tipo, String nombre) {
        switch (tipo) {
            case "Guerrero": return new Guerrero(nombre);
            case "Mago": return new Mago(nombre);
            case "Arquero": return new Arquero(nombre);
            case "Hechizero": return new Hechizero(nombre);
            case "Toallin": return new Toallin(nombre);
            
            default: return null;
        }
    }

    // Método main que inicia todo
    public static void main(String[] args) {
        
        
        new JuegoPOO(); // Crear la ventana
    }
    
}
