package game;

import java.util.Random;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;

public class GameSketch extends PApplet {

    App music = new App();
    Ejecucion Ejecucion = new Ejecucion(this);
    Random random = new Random();

    @Override
    public void settings() {
        size(1280, 720);
    }

    int nuvelpist = 1, nuevel = 1, x = 0, sw = 1, posicytex = 28, tamlet = 13, vidas = 3, tiempo, ancho = 70, altura = 150,
    anchobono = 60, alturabono = 87, i, n = 0, c1 = 0, tiempo2, tiempo3 = 1, auxiliartiempo, escenas = 1, auxiliar,
    consumir = 0, m, posiclineametay = -83, pausa = 0, subscena = 1, tiempo4, tiempoauxiliar, posicxi = -150, posicxf = 1430, 
    nivelesmodolibre, posicyi = -150, posicyf = 870;

    float resumenvel, resumenvelpist, y = 0, velpist = 1, velocidad = 1, dificultad = (float) 0.2,
            dificultad2 = (float) 0.2, posicymap = -720, posicymap2 = -720, mapay = -1440, posicionusux = 600,
            posicionusuy = 520, posicionchoquex, posicionchoquey, posicioncomerx, posicioncomery,
            posicactx = posicxf, posicymodlibre = 0, posicacty = posicyi, posicymapmodlibre = -720, 
            posicymapa2modlibre = 0, posicybono;

    Clip sonidoinicio, nivel1, nivel2, nivel3, sonidovictory, sonidocreditos;

    PImage fondo, mapa1, mapa2, perdiste, crash, corazon, lineameta, victoria,
            creditos1, menudepausa, unavidamas, unavidamenos, usuario,
            bolsa, cambiomapa, mapamodolibre, botonjugar, botonml, botoncreditos, titulo;

    PFont font;

    boolean llave = true, vervida = true, llavesalir = true, movimiento = true, llavesonidoinicio,
            capturarvelclibre = true, llavesondperdida = true, sonidolobby = true, actsonido1 = true, actsonido2 = true,
            actsonido3 = true, actsonidovictory = true, contador = true, conf = false,
            play = false, cambiosubescena, mapas = false, almacenarsond = true, llavetiempo = true, llavecontinuar = true, 
            capturtiempo = true, lanzamientoml = false, llavereanudar = true;

    String p = "p";
    String a = "a";
    String d = "d";
    String w = "w";
    

    char pause = p.charAt(0);
    char tcA = a.charAt(0);
    char tcD = d.charAt(0);
    char tcW = w.charAt(0);
    

    String Sonidoinicio = "/sonidos/Sonidollobby.wav", Nivel1 = "/sonidos/Nivel1.wav", Nivel2 = "/sonidos/Nivel2.wav",
            Nivel3 = "/sonidos/Nivel3.wav", Sonidovictory = "/sonidos/Sonidovictoria.wav",
            Sonidocreditos = "/sonidos/Sonidocreditos.wav";

    String[] bonos = { "../imagen/bonovida1.png", "../imagen/bonovida2.png", "../imagen/bonovida3.png" };
    String[] autos_abajo = { "../imagen/usuario2.png", "../imagen/usuario3.png", "../imagen/enemigo.png",
            "../imagen/Carrorival1.png",
            "../imagen/Carrorival4.png" };
    String[] autos_arriba = { "../imagen/usuario5.png", "../imagen/usuario6.png", "../imagen/usuario4.png",
            "../imagen/Carrorival2.png",
            "../imagen/Carrorival3.png" };

    PImage[] carro_arriba = new PImage[autos_arriba.length];
    PImage[] carro_arriba_shuffle = new PImage[autos_arriba.length];
    PImage[] carros_abajo = new PImage[autos_abajo.length];
    PImage[] carros_abajo_shuffle = new PImage[autos_abajo.length];
    PImage[] comida = new PImage[bonos.length];
    PImage[] comida_shuffle = new PImage[bonos.length];
    boolean[] comer = new boolean[bonos.length];
    boolean[] boleeanarray = { false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, };

    int[] contadores = new int[15];

    float[] vehiculosderecha = { random(600, 700), random(600, 700), random(600, 700), random(600, 700),
            random(600, 700) };
    float[] vehiculosizquierda = { random(350, 527), random(350, 527), random(350, 527), random(350, 527),
            random(350, 527) };
    float[] ordenadascomida = { random(310, 745), random(310, 745), random(310, 745) };
    float[] abcisa = { -160, -160, -160, -160, -160, -160, -160, -160, -160, -160, -160 };
    float[] abcisa1 = { -160, -160, -160, -160, -160, -160, -160, -160, -160, -160, -160 };
    float[] abcisa2 = { -90, -90, -90, -90, -90, -90, -90, -90, -90, -90, -90, -90, -90, -90, -90 };

    @Override
    public void setup() {
        this.cargararchivos();
        frameRate(30);
    }

    @Override
    public void draw() {
        tiempo = millis() / 1000;
        tiempo4 = millis();
        switch (escenas) {
            case 1:
                this.fondo();
                break;
            case 2:
                if (play) {
                    image(usuario, posicionusux, posicionusuy);
                }
                if (capturtiempo) {
                    auxiliar = tiempo;
                    capturtiempo = false;
                }
                tiempo2 = tiempo - auxiliar;
                if (contador) {
                    image(fondo, x, y);
                    textFont(font);
                    fill(255, 255, 255);
                    textSize(120);
                    text(3 - tiempo2, 600, 360);
                }
                if (tiempo2 > 3) {
                    if (movimiento == true) {
                        tiempo3 = tiempo2 - 3 - auxiliar;
                    }
                    contador = false;
                    this.juego();
                }
                break;
            case 3:
                this.modolibre();
                break;
            case 4:
                this.creditos();

        }
    }

    // Metodo para cargar los archivos a utilizar
    public void cargararchivos() {
        fondo = loadImage("imagen/fondo.png");
        crash = loadImage("imagen/choques.png");
        usuario = loadImage("imagen/usuario1.png");
        crash.resize(200, 200);
        corazon = loadImage("imagen/corazon.png");
        corazon.resize(200, 200);
        mapa1 = loadImage("imagen/mapa1.jpg");
        mapa2 = loadImage("imagen/mapa2.jpg");
        bolsa = loadImage("imagen/bolsa.png");
        bolsa.resize(200, 200);
        perdiste = loadImage("imagen/gameover.png");
        victoria = loadImage("imagen/victoria.png");
        lineameta = loadImage("imagen/meta.png");
        menudepausa = loadImage("imagen/menupausa.png");
        unavidamas = loadImage("imagen/+1.png");
        unavidamenos = loadImage("imagen/-1.png");
        cambiomapa = loadImage("imagen/selecmapa.jpg");
        botonjugar = loadImage("imagen/Botonjugar.png");
        botonml = loadImage("imagen/Botonmodolibre.png");
        botoncreditos = loadImage("imagen/Botoncreditos.png");
        titulo = loadImage("imagen/Titulo.png");
        creditos1 = loadImage("imagen/creditos1.png");
        font = createFont("textplay.ttf", tamlet);


        for (int i = 0; i < autos_arriba.length; i++) {
            carro_arriba_shuffle[i] = (loadImage(autos_arriba[i]));

        }

        for (int j = 0; j < autos_abajo.length; j++) {
            carros_abajo_shuffle[j] = (loadImage(autos_abajo[j]));

        }

        for (int i = 0; i < bonos.length; i++) {
            comida_shuffle[i] = (loadImage(bonos[i]));
            comida_shuffle[i].resize(60, 87);
        }

        Ejecucion.shuffle(carros_abajo_shuffle, carros_abajo);
        Ejecucion.shuffle(carro_arriba_shuffle, carro_arriba);
        Ejecucion.shuffle(comida_shuffle, comida);

        // audio pantalla principal
        try {
            InputStream audioSrc = getClass().getResourceAsStream(Sonidoinicio);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            sonidoinicio = AudioSystem.getClip();
            sonidoinicio.open(audioStream);
        } catch (Exception exception) {
            System.out.println("ERROR");
        }

        // Sonido nivel 1
        try {
            InputStream audioSrc = getClass().getResourceAsStream(Nivel1);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            nivel1 = AudioSystem.getClip();
            nivel1.open(audioStream);
        } catch (Exception exception) {
            System.out.println("ERROR");
        }

        // Sonido nivel 2
        try {
            InputStream audioSrc = getClass().getResourceAsStream(Nivel2);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            nivel2 = AudioSystem.getClip();
            nivel2.open(audioStream);
        } catch (Exception exception) {
            System.out.println("ERROR");
        }

        // Sonido nivel 3
        try {
            InputStream audioSrc = getClass().getResourceAsStream(Nivel3);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            nivel3 = AudioSystem.getClip();
            nivel3.open(audioStream);
        } catch (Exception exception) {
            System.out.println("ERROR");
        }

        // Sonido victoria
        try {
            InputStream audioSrc = getClass().getResourceAsStream(Sonidovictory);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            sonidovictory = AudioSystem.getClip();
            sonidovictory.open(audioStream);
        } catch (Exception exception) {
            System.out.println("ERROR");
        }

        // Sonido creditos
        try {
            InputStream audioSrc = getClass().getResourceAsStream(Sonidocreditos);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            sonidocreditos = AudioSystem.getClip();
            sonidocreditos.open(audioStream);
        } catch (Exception exception) {
            System.out.println("ERROR");
        }

    }

    // Metodo para entrar a la pantalla principal
    public void fondo() {
        background(fondo);
        image(titulo, 90, 50);
        image(botonjugar, 500, 200);
        image(botonml, 500, 300);
        image(botoncreditos, 500, 400);
        this.mouseClicked();

    }

    // Metodo para empezar el modo carrera
    public void juego() {
        if (!conf) {
            m = 0;
        }
        vervida = true;
        // Control de la velocidad, aumenta cada 20 seg.
        if (tiempo3 % 20 == 0) {
            velocidad = velocidad + dificultad;
            velpist = velpist + dificultad;
        }
        if (tiempo3 == 50) {
            sw = 2;
        }
        if (sw == 1) {
            if (almacenarsond) {
                if (actsonido1 == true) {
                    nivel1.setFramePosition(0);
                    nivel1.start();
                    actsonido1 = false;
                }
            }
            image(mapa1, x, y);
            image(mapa1, x, posicymap);
            y = y + velpist;
            posicymap = posicymap + velpist;
            if (y > 720) {
                y = 0;
                posicymap = -720;
            }
            image(mapa1, x, posicymap);
            if (tiempo3 >= 25) {
                posicymap += velpist;
            }
        } else if (sw == 2) {
            nivel1.stop();
            if (almacenarsond) {
                if (actsonido2 == true) {
                    nivel2.setFramePosition(0);
                    nivel2.start();
                    actsonido2 = false;
                }
            }
            if (llave) {
                y = 0;
                llave = false;
            }
            y = y + velpist;
            image(mapa2, x, y);
            image(mapa2, x, posicymap2);
            posicymap2 = posicymap2 + velpist;
            if (y > 720) {
                y = 0;
                posicymap2 = -720;
            }
        }
        if (tiempo3 > 98) {
            if (posiclineametay < posicionusuy) {
                posiclineametay += velpist;
            } else {
                velpist = 0;
                velocidad = 0;
            }
            image(lineameta, 320, posiclineametay);
        }
        this.textocarrera();
        this.choques();
        this.generarcarros();
        this.generarcomida();
        this.atraparcomida();
        image(usuario, posicionusux, posicionusuy);

        if (vidas == 0) {
            if (vervida == true) {

                velpist = 0;
                velocidad = 0;
                movimiento = false;
                image(perdiste, 350, 0);
                nivel1.stop();
                nivel2.stop();
                nivel3.stop();
                llavecontinuar = true;
                llavesalir = true;
                this.mouseClicked();
            }
        }
        // Para desplegar pausa
        if (pausa == 0) {
            resumenvel = velocidad;
            resumenvelpist = velpist;
        }
        this.mouseClicked();

        if (pausa == 1) {
            image(menudepausa, 30, 30);
            switch (sw) {
                case 1:
                    nivel1.stop();
                    break;
                case 2:
                    nivel2.stop();
                    break;

            }
            movimiento = false;
            velpist = 0;
            velocidad = 0;
            llavesalir = true;
            llavecontinuar = true;
            llavetiempo = true;
        }
        // Se reanuda el juego
        if (pausa == 3) {
            switch (sw) {
                case 1:
                    nivel1.loop(100);
                    break;
                case 2:
                    nivel2.loop(100);
                    break;

            }
            if (llavetiempo) {
                tiempoauxiliar = tiempo2 - tiempo3 - 3;
                llavetiempo = false;
            }
            movimiento = true;
            velocidad = resumenvel;
            velpist = resumenvelpist;
            pausa = 0;
        }
        // El usuario gana
        if (tiempo3 == 100) {
            movimiento = false;
            image(victoria, 494, 17);
            if (actsonidovictory) {
                sonidovictory.setFramePosition(0);
                sonidovictory.loop(100);
                actsonidovictory = false;
            }
            llavesalir = true;
            llavecontinuar = true;
        }
    }

    // Modo libre
    public void modolibre() {
        this.mouseClicked();
        if (lanzamientoml) {
            if (tiempo3 % 20 == 0) {
                velocidad = velocidad + (float) 0.2;
                velpist = velpist + (float) 0.2;
            }
            image(usuario, posicionusux, posicionusuy);
            if (capturtiempo) {
                auxiliar = tiempo;
                capturtiempo = false;
            }
            tiempo2 = tiempo - auxiliar;
            if (contador) {
                image(mapamodolibre, x, y);
                image(usuario, posicionusux, posicionusuy);
                filter(BLUR, 5);
                textFont(font);
                fill(255, 255, 255);
                textSize(120);
                text(3 - tiempo2, 600, 360);
            }
            if (tiempo2 > 3) {
                if (movimiento == true) {
                    tiempo3 = tiempo2 - 3 - auxiliartiempo;
                }
                contador = false;
                switch (nivelesmodolibre) {
                    case 1:
                        if (almacenarsond) {
                            if (actsonido1 == true) {
                                nivel1.setFramePosition(0);
                                nivel1.loop(100);
                                actsonido1 = false;
                            }
                        }
                        image(mapamodolibre, x, posicymodlibre);
                        image(mapamodolibre, x, posicymapmodlibre);
                        posicymodlibre = posicymodlibre + velpist;
                        posicymapmodlibre = posicymapmodlibre + velpist;
                        if (posicymodlibre >= 720) {
                            posicymodlibre = 0;
                            posicymapmodlibre = -720;
                        }
                        image(usuario, posicionusux, posicionusuy);
                        break;
                    case 2:
                        if (almacenarsond) {
                            if (actsonido2 == true) {
                                nivel2.setFramePosition(0);
                                nivel2.loop(100);
                                actsonido2 = false;
                            }
                        }
                        image(mapamodolibre, x, posicymodlibre);
                        image(mapamodolibre, x, posicymapmodlibre);
                        posicymodlibre = posicymodlibre + velpist;
                        posicymapmodlibre = posicymapmodlibre + velpist;
                        if (posicymodlibre >= 720) {
                            posicymodlibre = 0;
                            posicymapmodlibre = -720;
                        }
                        image(usuario, posicionusux, posicionusuy);
                        break;
                }
                this.choques();
                this.textomodolibre();
                this.generarcarros();
                this.generarcomida();
                this.atraparcomida();
                image(usuario, posicionusux, posicionusuy);
            }
        }
        // Perdida de las 3 vidas.
        if (vidas == 0) {
            if (vervida == true) {
                velpist = 0;
                velocidad = 0;
                movimiento = false;
                image(perdiste, 350, 0);
                nivel1.stop();
                nivel2.stop();
                nivel3.stop();
                llavecontinuar = true;
                llavesalir = true;
                this.mouseClicked();
            }
        }
        if (pause == 0) {
            resumenvel = velocidad;
            resumenvelpist = velpist;
        }
        if (pause == 1) {
            image(menudepausa, 380, 84);
            movimiento = false;
            velpist = 0;
            velocidad = 0;
            llavesalir = true;
            llavecontinuar = true;
            llavetiempo = true;
            llavereanudar = true;
            switch (nivelesmodolibre) {
                case 1:
                    nivel1.stop();
                    break;
                case 2:
                    nivel2.stop();
                    break;
                case 3:
                    nivel3.stop();
                    break;
            }

        }
        if (pause == 3) {
            switch (nivelesmodolibre) {
                case 1:
                    nivel1.loop(100);
                    break;
                case 2:
                    nivel2.loop(100);
                    break;
                case 3:
                    nivel3.loop(100);
                    break;
            }
            if (llavetiempo) {
                auxiliartiempo = tiempo2 - tiempo3 - 3;
                llavetiempo = false;
            }
            movimiento = true;
            velocidad = resumenvel;
            velpist = resumenvelpist;
            pausa = 0;

        }
    }

    // Metodo para generar los autos rivales
    public void generarcarros() {
        image(carros_abajo[0], vehiculosizquierda[0], abcisa[0]);
        abcisa[0] = abcisa[0] + velocidad;

        if (abcisa[0] > 144) {
            image(carro_arriba[0], vehiculosderecha[0], abcisa1[0]);
            abcisa1[0] = abcisa1[0] + velocidad;
        }

        if (abcisa1[0] > 144) {
            image(carros_abajo[1], vehiculosizquierda[1], abcisa[1]);
            abcisa[1] = abcisa[1] + velocidad;
        }
        if (abcisa[1] > 144) {
            image(carro_arriba[1], vehiculosderecha[1], abcisa1[1]);
            abcisa1[1] = abcisa1[1] + velocidad;
        }
        if (abcisa1[1] > 144) {
            image(carros_abajo[2], vehiculosizquierda[2], abcisa[2]);
            abcisa[2] = abcisa[2] + velocidad;
        }
        if (abcisa[2] > 144) {
            image(carro_arriba[2], vehiculosderecha[2], abcisa1[2]);
            abcisa1[2] = abcisa1[2] + velocidad;
        }
        if (abcisa1[2] > 144) {
            image(carros_abajo[3], vehiculosizquierda[3], abcisa[3]);
            abcisa[3] = abcisa[3] + velocidad;
        }
        if (abcisa[3] > 144) {
            image(carro_arriba[3], vehiculosderecha[3], abcisa1[3]);
            abcisa1[3] = abcisa1[3] + velocidad;
        }
        if (abcisa1[3] > 144) {
            image(carros_abajo[4], vehiculosizquierda[4], abcisa[4]);
            abcisa[4] = abcisa[4] + velocidad;
        }
        if (abcisa[4] > 144) {
            image(carro_arriba[4], vehiculosderecha[4], abcisa1[4]);
            abcisa1[4] = abcisa1[4] + velocidad;
        }
        if (abcisa1[4] > 720) {
            Ejecucion.shuffle(carros_abajo_shuffle, carros_abajo);
            Ejecucion.shuffle(carro_arriba_shuffle, carro_arriba);
            for (int i = 0; i < vehiculosderecha.length; i++) {
                vehiculosderecha[i] = random(600, 920);
                vehiculosizquierda[i] = random(275, 520);
            }
            for (int i = 0; i < abcisa.length; i++) {
                abcisa1[i] = -160;
                abcisa[i] = -160;
            }
        }
    }

    // Método para generar bonos
    public void generarcomida() {
        image(comida[0], ordenadascomida[0], abcisa[0]);
        abcisa[0] = abcisa[0] + velocidad;

        if (abcisa[0] > 144) {
            image(comida[0], ordenadascomida[0], posicybono = abcisa1[0]);
            abcisa1[0] = abcisa1[0] + velocidad;
        }

        if (abcisa1[0] > 144) {
            image(comida[1], ordenadascomida[1], posicybono = abcisa[1]);
            abcisa[1] = abcisa[1] + velocidad;
        }
        if (abcisa[1] > 144) {
            image(comida[1], ordenadascomida[1], posicybono = abcisa1[1]);
            abcisa1[1] = abcisa1[1] + velocidad;
        }
        if (abcisa1[1] > 144) {
            image(comida[2], ordenadascomida[2], posicybono = abcisa[2]);
            abcisa[2] = abcisa[2] + velocidad;
        }
        if (abcisa[2] > 144) {
            image(comida[2], ordenadascomida[2], posicybono = abcisa1[2]);
            abcisa1[2] = abcisa1[2] + velocidad;
        }
        this.validacion();
    }

    // Método choques de autos
    public void choques() {
        for (int h = 0; h < autos_arriba.length; h++) {
            if (posicionusux > vehiculosizquierda[h] + ancho - 10) {

            } else if (posicionusux + ancho < vehiculosizquierda[h] + 10) {

            } else if (posicionusux > abcisa[h] + altura - 10) {

            } else if (posicionusuy + altura < abcisa[h] + 10) {

            } else {
                if (vidas > 0) {
                    vidas = vidas - 1;
                }
                image(unavidamenos, 1123, 14);
                posicioncomerx = ordenadascomida[h];
                posicioncomery = abcisa[h];
                if (posicionchoquey <= 558) {
                    image(crash, posicionchoquex, posicionchoquey);
                } else if (posicionchoquey > 558) {
                    image(crash, posicionchoquex, posicionchoquey - 100);
                }
                abcisa[h] = 720;
                posicionusux = 600;
            }
            if (posicionusux > vehiculosderecha[h] + ancho - 10) {

            } else if (posicionusux + ancho < vehiculosderecha[h] + 10) {

            } else if (posicionusuy > abcisa1[h] + altura - 10) {

            } else if (posicionusuy + altura < abcisa1[h] + 10) {

            } else {
                vidas = vidas - 1;
                posicionchoquex = vehiculosderecha[h];
                posicionchoquey = abcisa1[h];
                if (posicionchoquey <= 558) {
                    image(crash, posicionchoquex, posicionchoquey);
                } else if (posicionchoquey > 558) {
                    image(crash, posicionchoquex, posicionchoquey - 100);
                }
                abcisa1[h] = 720;
                posicionusux = 600;
            }
        }
    }

    // Método para atrapar comida
    public void atraparcomida() {
        for (int h = 0; h < comida.length; h++) {
            if (posicionusux > ordenadascomida[h] + anchobono - 10) {

            } else if (posicionusux + anchobono < ordenadascomida[h] + 10) {

            } else if (posicionusux > abcisa[h] + alturabono - 10) {

            } else if (posicionusuy + alturabono < abcisa[h] + 10) {

            } else {
                if (keyCode == UP || key == tcW) {
                    if (vidas > 0) {
                        consumir = consumir + 1;
                        image(bolsa, posicioncomerx, posicioncomery - 100);
                    }
                }
                if (consumir % 4 == 0) {
                    vidas = vidas + 1;
                    consumir = 0;
                    image(unavidamas, 1123, 14);
                }
                posicioncomerx = ordenadascomida[h];
                posicioncomery = abcisa[h];
                if (posicioncomery <= 558) {
                    image(bolsa, posicioncomerx, posicioncomery);
                } else if (posicioncomery > 558) {
                    image(crash, posicioncomerx, posicioncomery - 100);
                }
                abcisa[h] = 720;
            }
            if (posicionusux > ordenadascomida[h] + anchobono - 10) {

            } else if (posicionusux + anchobono < ordenadascomida[h] + 10) {

            } else if (posicionusuy > abcisa1[h] + alturabono - 10) {

            } else if (posicionusuy + alturabono < abcisa1[h] + 10) {

            } else {
                if (keyCode == UP || key == tcW) {
                    if (vidas > 0) {
                        consumir = consumir + 1;
                        image(bolsa, posicioncomerx, posicioncomery - 100);
                    }
                }
                if (consumir % 4 == 0) {
                    vidas = vidas + 1;
                    consumir = 0;
                    image(unavidamas, 1123, 14);
                }
                posicioncomerx = ordenadascomida[h];
                posicioncomery = abcisa1[h];
                if (posicioncomery <= 558) {
                    image(bolsa, posicioncomerx, posicioncomery);
                } else if (posicioncomery > 558) {
                    image(bolsa, posicioncomerx, posicioncomery - 100);
                }
                abcisa1[h] = 720;
            }
        }

    }

    // Método que muestra el texto en el modo clásico

    public void textocarrera() {

        corazon.resize(24, 23);

        image(corazon, 1092, posicytex - 19);
        textFont(font);
        fill(0, 0, 0);
        text(vidas + "P", 1055, posicytex);
    }

    // Método que muestra el texto en el modo libre o infinito
    public void textomodolibre() {
        corazon.resize(24, 23);
        image(corazon, 1098, posicytex - 19);
        textFont(font);
        fill(0, 0, 0);
        text(vidas + "P", 1055, posicytex);
    }

    // Método para el movimiento y utilización de bonos

    @Override
    public void keyPressed() {
        if (movimiento == true) {
            if (keyPressed == true) {
                if (keyCode == RIGHT || key == tcD) {
                    if (posicionusux < 750) {
                        posicionusux = posicionusux + 50;
                    }
                }
                if (keyCode == LEFT || key == tcA) {
                    if (posicionusux > 300) {
                        posicionusux = posicionusux - 50;
                    }
                }
                if (keyCode == UP || key == tcW) {
                    if (posicionusuy == posicybono) {
                        consumir = consumir + 1;
                        if (consumir % 3 == 0) {
                            vidas++;
                            image(unavidamas, 1123, 14);
                        }
                        image(bolsa, posicionusux, posicionusuy);

                    }
                }
                if (key == pause) {
                    pausa = 1;
                }

            }
        }
    }

    // Método para evitar choques entre los carros y los bonos
    public void validacion() {
        for (int h = 0; h < ordenadascomida.length; h++) {
            c1 = 0;
            for (int i = 0; i < vehiculosderecha.length; i++) {
                if (ordenadascomida[h] + 30 > vehiculosizquierda[i] + ancho) {
                    c1 += 1;
                } else if (ordenadascomida[h] + anchobono + 30 < vehiculosizquierda[i]) {
                    c1 += 1;
                } else if (abcisa2[h] + 30 > abcisa[i] + altura) {
                    c1 += 1;
                } else if (abcisa2[h] + alturabono + 30 < abcisa[i]) {
                    c1 += 1;
                } else {
                }
                if (ordenadascomida[h] + 30 > vehiculosderecha[i] + ancho) {
                    c1 += 1;
                } else if (ordenadascomida[h] + anchobono + 30 < vehiculosderecha[i]) {
                    c1 += 1;
                } else if (abcisa2[h] + 30 > abcisa1[i] + altura) {
                    c1 += 1;
                } else if (abcisa2[h] + alturabono + 30 < abcisa1[i]) {
                    c1 += 1;
                } else {
                }
            }
            contadores[h] = c1;
        }
    }

    // Método para hacer uso del mouse
    public void mouseClicked() {
        if (mousePressed == true) {
            if (mouseButton == LEFT) {
                if (escenas == 1) {
                    // Lanza el modo de juego normal
                    if (mouseX > 500 && mouseX < 500 + 250) {
                        if (mouseY > 280 && mouseY < 280 + 50) {
                            escenas = 2;
                            velocidad = nuevel;
                            velpist = nuvelpist;
                            pausa = 0;
                        }
                    }
                    // Laza el modo de juego libre
                    if (mouseX > 500 && mouseX < 500 + 250) {
                        if (mouseY > 380 && mouseY < 380 + 50) {
                            escenas = 3;
                            if (capturarvelclibre) {
                                velocidad = nuevel;
                                velpist = nuvelpist;
                                capturarvelclibre = false;
                                pausa = 0;
                            }
                        }
                    }
                    // lanza los creditos
                    if (mouseX > 500 && mouseX < 500 + 250) {
                        if (mouseY > 480 && mouseY < 480 + 50) {
                            escenas = 4;
                            subscena = 1;
                        }
                    }
                }
                if (escenas == 2) {
                    if (mouseX > 1098 && mouseX < 1223) {
                        if (mouseY > 639 && mouseY < 690) {
                            play = true;
                        }
                    }
                    if (vidas == 0 || tiempo3 == 100) {
                        if (movimiento == false) {
                            if (mouseX > 538 && mouseX < 620) {
                                if (mouseY > 330 && mouseY < 420) {
                                    if (llavesalir) {
                                        this.salida();
                                        llavesalir = false;
                                    }
                                }
                            }
                        }
                        if (mouseX > 600 && mouseX < 658) {
                            if (mouseY > 330 && mouseY < 420) {
                                if (llavecontinuar) {
                                    this.nuevointento();
                                    llavecontinuar = false;
                                }
                            }
                        }
                    }
                    if (pausa == 1) {
                        if (mouseX > 578 && mouseX < 690) {
                            if (mouseY > 375 && mouseY < 475) {
                                if (llavecontinuar) {
                                    pausa = 3;
                                }

                            }
                        }
                        /*
                         * if (mouseX > 642 && mouseX < 750) {
                         * if (mouseY > 378 && mouseY < 471) {
                         * pausa = 0;
                         * llavecontinuar = false;
                         * this.salida();
                         * 
                         * }
                         * }
                         */
                    }
                }
                if (escenas == 3) {
                    if (!lanzamientoml) {
                        image(cambiomapa, 0, 0);
                        if (mouseX > 26 && mouseX < 556) {
                            if (mouseY > 225 && mouseY < 526) {
                                mapas = true;
                                mapamodolibre = mapa1;
                                nivelesmodolibre = 1;
                                lanzamientoml = true;
                            }
                        }
                        if (mouseX > 666 && mouseX < 1200) {
                            if (mouseY > 225 && mouseY < 526) {
                                mapamodolibre = mapa2;
                                mapas = true;
                                nivelesmodolibre = 2;
                                lanzamientoml = true;
                            }
                        }
                        if (vidas == 0) {
                            if (movimiento == false) {
                                if (mouseX > 540 && mouseX < 620) {
                                    if (mouseY > 330 && mouseY < 420) {
                                        if (llavesalir) {
                                            this.salidamodolibre();
                                            llavesalir = false;
                                        }
                                    }
                                }
                            }
                            if (mouseX > 660 && mouseX < 740) {
                                if (mouseY > 330 && mouseY < 420) {
                                    if (llavecontinuar) {
                                        this.salidamodolibre();
                                        llavecontinuar = false;
                                    }
                                }
                            }
                        }
                        if (pausa == 1) {
                            if (mouseX > 481 && mouseX < 572) {
                                if (mouseY > 214 && mouseY < 306) {
                                    if (llavecontinuar) {
                                        this.salidamodolibre();
                                        pausa = 0;
                                        llavecontinuar = false;
                                    }
                                }
                            }
                            if (mouseX > 621 && mouseX < 712) {
                                if (mouseY > 214 && mouseY < 306) {
                                    if (llavesalir) {
                                        this.salidamodolibre();
                                        pausa = 0;
                                        llavesalir = false;
                                    }
                                }
                            }
                            if (mouseX > 491 && mouseX < 789) {
                                if (mouseY > 323 && mouseY < 384) {
                                    if (llavereanudar) {
                                        pausa = 3;
                                        llavereanudar = false;
                                    }
                                }
                            }
                        }
                    }
                    if (escenas == 4) {
                        if (mousePressed) {
                            escenas = 1;
                        }
                        if (subscena == 1) {
                            if (mousePressed) {
                                subscena = 2;
                                cambiosubescena = false;

                            }
                        }
                        if (subscena == 2) {
                            if (mouseButton == RIGHT) {
                                subscena = 1;
                            }
                            if (mouseX > 1092 && mouseX < 1237) {
                                if (mouseY > 34 && mouseY < 89) {
                                    escenas = 1;
                                    sonidocreditos.stop();
                                    sonidolobby = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Método para visualizar los creditos
    public void creditos() {
        switch (subscena) {
            case 1:
                image(creditos1, 0, 0);
                break;
        }
        this.mouseClicked();
    }

    // Salir del modo carrera

    public void salida() {
        escenas = 1;
        this.nuevointento();
        play = false;
        sonidolobby = true;
    }

    // Salir del modo libre o infinito

    public void salidamodolibre() {
        capturarvelclibre = true;
        sonidolobby = true;
        escenas = 1;
        this.nuevointentomodolibre();

    }

    // Método para reiniciar una carrera en el modo carrera

    public void nuevointento() {

        velpist = nuvelpist;
        velocidad = nuevel;
        y = 0;
        posicymap = -720;
        posicymap2 = -1440;
        posicionusux = 600;
        posicionusuy = 520;
        posiclineametay = -83;
        vidas = 3;
        posicxi = -150;
        posicxf = 1430;
        posicyi = -150;
        posicyf = 870;
        posicactx = posicxf;
        posicacty = posicyi;
        movimiento = true;
        llavesonidoinicio = true;
        sonidolobby = true;
        llavesondperdida = true;
        actsonido1 = true;
        actsonido3 = true;
        actsonido1 = true;
        contador = true;
        actsonidovictory = true;
        capturtiempo = true;
        llave = true;
        play = true;
        auxiliartiempo = 0;
        nivel1.stop();
        nivel2.stop();
        nivel3.stop();

        Ejecucion.shuffle(carros_abajo_shuffle, carros_abajo);
        Ejecucion.shuffle(carro_arriba_shuffle, carro_arriba);
        Ejecucion.shuffle(comida_shuffle, comida);

        sonidovictory.stop();
        sw = 1;
        for (int i = 0; i < vehiculosderecha.length; i++) {
            vehiculosderecha[i] = random(600, 920);
            vehiculosizquierda[i] = random(275, 520);
        }
        for (int i = 0; i < abcisa.length; i++) {
            abcisa1[i] = -160;
            abcisa[i] = -160;
        }
        for (int i = 0; i < abcisa2.length; i++) {
            abcisa2[i] = -395;
            ordenadascomida[i] = random(310, 745);
        }
    }

    // Método para reiniciar una carrera en el modo libre

    public void nuevointentomodolibre() {
        velpist = 1;
        velocidad = 1;
        posicymodlibre = 0;
        auxiliartiempo = 0;
        posicymapmodlibre = -720;
        posicymapa2modlibre = 0;
        posicionusux = 600;
        posicionusuy = 520;
        vidas = 3;
        movimiento = true;
        llavesonidoinicio = true;
        sonidolobby = true;
        llavesondperdida = true;
        actsonido1 = true;
        actsonido2 = true;
        actsonido3 = true;
        contador = true;
        capturtiempo = true;
        llave = true;
        llavetiempo = true;
        lanzamientoml = true;
        nivel1.stop();
        nivel2.stop();
        nivel3.stop();

        Ejecucion.shuffle(carros_abajo_shuffle, carros_abajo);
        Ejecucion.shuffle(carro_arriba_shuffle, carro_arriba);
        Ejecucion.shuffle(comida_shuffle, comida);

        for (i = 0; i < vehiculosderecha.length; i++) {
            vehiculosderecha[i] = random(600, 920);
            vehiculosizquierda[i] = random(275, 520);
        }
        for (int i = 0; i < abcisa.length; i++) {
            abcisa1[i] = -160;
            abcisa[i] = -160;
        }
        for (int i = 0; i < abcisa2.length; i++) {
            abcisa2[i] = -395;
            ordenadascomida[i] = random(310, 750);
        }
    }

    // Correr el juego

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }

}
