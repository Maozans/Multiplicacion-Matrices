/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Miguel Ochoa
 */
public class MultiplicacionMatrices {

    public static void main(String[] args) {
        ArrayList<int[][]> listaMatrices = new ArrayList<>();
        int[][] resultado;
        recuperaMatricesDeArchivo(listaMatrices);
//        for(int[][] m : listaMatrices){
//            imprimeMatriz(m);
//        }
        resultado = multiplicaArrayDeMatrices(listaMatrices);
        imprimeMatriz(resultado);
    }

    static void imprimeMatriz(int[][] m) {
        String linea;
        for (int i = 0; i < m.length; i++) {
            linea = "";
            for (int j = 0; j < m[i].length; j++) {
                linea = linea.concat(String.valueOf(m[i][j]) + "\t");
            }
            System.out.println(linea);
        }
    }

    static void recuperaMatricesDeArchivo(ArrayList<int[][]> listaMatrices) {
        String linea;
        StringTokenizer tokenizer;
        int numFilas;
        int numColumnas;
        int[][] matriz;
        File archivo = new File("matrices.txt");
        try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {
            while (br.readLine() != null) {
                linea = br.readLine();
                tokenizer = new StringTokenizer(linea, "://x");
                tokenizer.nextToken();
                numFilas = Integer.parseInt(tokenizer.nextToken());
                numColumnas = Integer.parseInt(tokenizer.nextToken());
                matriz = new int[numFilas][numColumnas];
                for (int i = 0; i < numFilas; i++) {
                    for (int j = 0; j < numColumnas; j++) {
                        linea = br.readLine();
                        tokenizer = new StringTokenizer(linea, ":");
                        tokenizer.nextToken();
                        matriz[i][j] = Integer.parseInt(tokenizer.nextToken());
                    }
                }
                listaMatrices.add(matriz);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Hubo un problema I/O");
        }
    }

    static int[][] getMatrizIdentidad(int tam) {
        int[][] matrizId = new int[tam][tam];
        for (int i = 0; i < tam; i++) {
            matrizId[i][i] = 1;
        }
        return matrizId;
    }

    static int[][] multiplicaMatrices(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = 0;
                for(int x=0;x<m2.length;x++){
                        res[i][j] += m1[i][x] * m2[x][j]; 
                }
            }
        }
        return res;
    }

    private static int[][] multiplicaArrayDeMatrices(ArrayList<int[][]> listaMatrices) {
        int tamMatrizId;
        tamMatrizId = listaMatrices.get(0).length;
        int[][] matrizAux = getMatrizIdentidad(tamMatrizId);
        for (int i = 0; i < listaMatrices.size(); i++) {
            matrizAux = multiplicaMatrices(matrizAux, listaMatrices.get(i));
        }
        return matrizAux;
    }
}
