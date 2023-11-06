package game;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

public class Ejecucion {
    PApplet sketch;

    Ejecucion(PApplet sketch) {
        this.sketch = sketch;
    }

    Random aleatory = new Random();
    int oji = 1;

    public void shuffle(PImage[] array, PImage[] salida) {
        boolean hacer = false;
        int l = 0;

        while (salida[array.length - 1] == null) {
            int x = aleatory.nextInt(array.length);
            for (int k = 0; k < l; k++) {
                if (array[x].equals(salida[k])) {
                    hacer = true;
                }
            }
            if (hacer == false) {
                salida[l] = array[x];
                l++;
            }
            hacer = false;
        }
        oji++;
    }
}
