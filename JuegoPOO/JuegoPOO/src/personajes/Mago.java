/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personajes;

/**
 *
 * @author ab189
 */
// Subclase Mago que también hereda de Personaje
public class Mago extends Personaje {

    public Mago(String nombre) {
        super(nombre, 70, 30); // Vida: 70, Ataque: 30
        this.rutaImagen = "Imagenes/Mago.png"; //Asigna la ruta de la imagen del Mago
    }
    
    // Cada clase tiene su propia versión de atacar (Polimorfismo)
    @Override
    public void atacar(Personaje enemigo) {
        System.out.println(nombre + " lanza una bola de fuego.");
        enemigo.recibirDanio(ataque);
    }
}
