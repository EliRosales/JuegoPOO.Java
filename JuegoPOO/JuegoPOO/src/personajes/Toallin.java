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
public class Toallin extends Personaje {

    public Toallin(String nombre) {
        super(nombre, 200, 100); // Vida: 70, Ataque: 30
        this.rutaImagen = "Imagenes/Toallin.png"; //Asigna la ruta de la Imagen del Toallin 
    }
    

    // Cada clase tiene su propia versión de atacar (Polimorfismo)
    @Override
    public void atacar(Personaje enemigo) {
        System.out.println(nombre + " lanza Mega Bareto.");
        enemigo.recibirDanio(ataque);
    }
}

