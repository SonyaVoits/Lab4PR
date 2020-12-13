package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("D:\\Теорія прийняття рішень\\matrixlab_4.txt")));
                StringBuffer sb = new StringBuffer();
        String input = new String();

        DecimalFormat df = new DecimalFormat("###.#"); //Округлення числа

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            sb.append(input);
        }
        String source = sb.toString();
        String str[] = source.split(" ");

        int c = 7;
        int r = 5;
        String[] param = new String[r];
        double[] value = new double[r];
        int[][] bal = new int[r][c];
        String[] obj = new String[r];
        String[] abc = {"А", "Б", "В", "Г", "Д", "Е"};

        for (int i = 2, j=0; i < c; i++, j++) {
            obj[j] = str[i];
        }

        int k = c;
        for (int i = 0; i < r; i++) {
            param[i] = str[k];
            k = k + c;
        }

        k = c+1;
        for (int i = 0; i < r; i++) {
            value[i] = Double.parseDouble(str[k]);
            k = k + c;
        }

        k = c+2;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                bal[i][j] = Integer.parseInt(str[k]);
                if (k == 13 || k == 20 || k == 27 || k == 34)
                    k = k + 3;
                else
                    k = k + 1;
            }
        }
        System.out.println();

        System.out.print("Параметр \t| Вага"+"\t");
        for (int i = 0; i < r; i++) {
            System.out.print(" | "+abc[i]);
        }
        System.out.println();

        for (int i = 0; i < r; i++) {
            if (param[i].length()>11 )
                System.out.print(param[i] + "\t");
            else
            if (param[i].length()==6 )
                System.out.print(param[i] + "\t\t\t");
            else
                System.out.print(param[i] + "\t\t");
            if ( value[i] == 0.2 || value[i] == 0.3|| value[i] == 0.1)
                System.out.print(value[i]+"    ");
            else System.out.print(value[i]+"   ");
            for (int j = 0; j < r; j++) {
                if (bal[i][j]==10)
                    System.out.print(bal[i][j] + "  ");
                else
                    System.out.print(bal[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();


        //System.out.println(df.format(PI));

        double rez[][] = multi(r, value, bal);      //Матриця Оцінка*Вага
        double sums[] = sum(r, rez);
        double[] max = getMax(rez, r);

        System.out.println("Результат:");

        System.out.print("Параметр \t| Вага | ");
        for (int i = 0; i < r; i++) {
            System.out.print("\t"+abc[i]+"\t|");
        }
        System.out.print("  Max ");
        System.out.println();

        for (int i = 0; i < r; i++) {
            if (param[i].length()>11 )
                System.out.print(param[i] + "\t");
            else
            if (param[i].length()==6 )
                System.out.print(param[i] + "\t\t\t");
            else
                System.out.print(param[i] + "\t\t");
            if ( value[i] == 0.2 || value[i] == 0.3|| value[i] == 0.1)
                System.out.print(value[i]+"    ");
            else System.out.print(value[i]+"   ");
            for (int j = 0; j < r; j++) {
                System.out.print("\t"+ df.format(rez[i][j]) + "\t");
            }
            System.out.print("|  " + df.format(max[i]) + " ");
            System.out.println();
        }
        System.out.print("Суми\t\t\t  |   ");
        for (int i = 0; i < r; i++) {
            System.out.print("\t" + df.format(sums[i]) + "\t");
        }
        System.out.println();

        int best=0;
        for(int i=0;i < r-1;i++) {
            if (max[i] > max[i+1])
                best = i;
            else
                best = i+1;
        }
        System.out.println();
        System.out.println("Результат: Найкращий варіант "+ abc[best-4] + " - " +obj[best-4] );

    }

    public static double[][] multi(int r, double[] value, int bal[][]) {      //Обчислення матриці Оцінка*Вага
        double[][] res = new double[r][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                res[i][j] = value[i] * bal[i][j];
            }
        }
        return  res;
    }

    public static double[] sum(int r, double arr[][]){        //Обчислення сум параметрів
        double[] res = new double[r];
        for (int i = 0; i < r; i++) {
            res[i] =0;
            for (int j = 0; j < r; j++) {
                res[i] = res[i] + arr[j][i];
            }
        }
        return res;
    }

    public static double[] getMax(double[][] arr, int r){       //Обчислення максимальних значень рядків
        double[] maxValue = new double[r];
        for(int i=0;i < r;i++) {
            maxValue[i] = 0;
            for (int j = 0; j < r; j++){
                if (arr[i][j] > maxValue[i]) {
                    maxValue[i] = arr[i][j];
                }
            }
        }
        return maxValue;
    }

}
