package aed;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

public class testsPropios {

    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;
    BestEffort sistema;
    LibretaCiudades libretaCiudades;
    LibretaTraslados libretaTraslados;

    @BeforeEach
    void init(){
        cantCiudades = 7;
        listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 100, 10),
            new Traslado(2, 0, 1, 400, 20),
            new Traslado(3, 3, 4, 500, 50),
            new Traslado(4, 4, 3, 500, 11),
            new Traslado(5, 1, 0, 1000, 40),
            new Traslado(6, 1, 0, 1000, 41),
            new Traslado(7, 6, 3, 2000, 42)
        };
        sistema = new BestEffort(cantCiudades, listaTraslados);
        libretaCiudades = new LibretaCiudades(cantCiudades);
        libretaTraslados = new LibretaTraslados(listaTraslados);
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " + e1 + " en el arreglo " + s2.toString());
        }
    }

    // Test para BestEffort

    @Test
    void testDespacharMasAntiguos() {
        int[] despachados = sistema.despacharMasAntiguos(3);
        assertArrayEquals(new int[] {1, 4, 2}, despachados, "Los traslados más antiguos no se despacharon en el orden esperado.");
    }

    @Test
    void testGananciaPromedioPorDespacho() {
        sistema.despacharMasRedituables(3);
        assertEquals(1333, sistema.gananciaPromedioPorTraslado(), "La ganancia promedio por traslado no es la esperada.");
    }

    // Tests para LibretaCiudades
    @Test
    void testSumarGananciaActualizaHeapSuperavit() {
        libretaCiudades.sumarGanancia(2, 1000);
        assertEquals(2, libretaCiudades.ciudadMayorSuperavit(), "La ciudad con mayor superávit no fue actualizada correctamente.");
    }

    @Test
    void testListaCiudadesMayorGanancia() {
        libretaCiudades.sumarGanancia(0, 500);
        libretaCiudades.sumarGanancia(1, 500);
        ArrayList<Integer> esperado = new ArrayList<>(Arrays.asList(0, 1));
        assertSetEquals(esperado, libretaCiudades.listaCiudadesMayorGanancia());
    }

    // Tests para LibretaTraslados
    @Test
    void testAgregarTraslado() {
        Traslado nuevoTraslado = new Traslado(8, 2, 5, 600, 60);
        libretaTraslados.agregarTraslado(nuevoTraslado);
        assertEquals(8, libretaTraslados.cantTraslados(), "La cantidad de traslados no se incrementó correctamente al agregar.");
    }

    @Test
    void testDespacharAntiguoActualizaCantidad() {
        int cantAntes = libretaTraslados.cantTraslados();
        libretaTraslados.despacharAntiguo();
        assertEquals(cantAntes - 1, libretaTraslados.cantTraslados(), "La cantidad de traslados no se decrementó correctamente al despachar el más antiguo.");
    }

    @Test
    void testDespacharRedituableActualizaCantidad() {
        int cantAntes = libretaTraslados.cantTraslados();
        libretaTraslados.despacharRedituable();
        assertEquals(cantAntes - 1, libretaTraslados.cantTraslados(), "La cantidad de traslados no se decrementó correctamente al despachar el más redituable.");
    }


    @Test
    void testDespachoYActualizacionGananciaPromedio() {
        sistema.despacharMasRedituables(2);
        assertEquals(1500, sistema.gananciaPromedioPorTraslado(), "La ganancia promedio no se calculó correctamente.");
        sistema.despacharMasRedituables(1);
        assertEquals(1333, sistema.gananciaPromedioPorTraslado(), "La ganancia promedio no se actualizó correctamente después del despacho.");
    }
}

