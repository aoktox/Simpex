package id.prasetiyo.simplex;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by meliafitriawati on 5/24/2016.
 */
public class Simplex {

    private int var;
    private int x, y;
    private int solusi;
    private int zKecil = 0;
    private int index = 0;

    private double[][] a;
    private int solusi1[];
    private ArrayList nilaiSolusi;
    DecimalFormat df;

    public Simplex(int x, int y, int var, double[][] a) {
        this.x = x;
        this.y = y;
        this.var = var;
        this.a = a;
        this.solusi = var + 1;
        nilaiSolusi = new ArrayList();
        solusi1 = new int[solusi];
        ccc();
        df = new DecimalFormat("#.##");
    }

    public void nol(){
        zKecil = 0;
        index = 0;
    }

    public void ccc(){
        for(int o = 0; o < solusi1.length; o++){
            solusi1[o] = 0;
        }
    }

    public void carizMinimum(){
        for(int j = 1; j <= var; j++){
            if(j < var){
                if(a[0][j] <= a[0][j+1]){
                    zKecil = j;
                }else{
                    zKecil = j+1;
                }
            }
        }
    }

    public void cariIndeks(){
        for(int k = 1; k < x; k++){
            if(a[k][zKecil] != 0){
                a[k][y-1] = a[k][y-2]/a[k][zKecil];
            }else{
                a[k][y-1] = 0;
            }
        }
        for(int l = 1; l < x-1; l++){
            if(a[l][y-1] <= a[l+1][y-1] && a[l][y-1] != 0){
                index = l;
            }else{
                index = l+1;
            }
        }
        //solusi1[var - 1] = index;
        nilaiSolusi.add(index);
    }

    public void hitung(){
        for(int m = 0; m < x; m++){
            double c = (double) a[m][zKecil];
            for(int n = 0; n < y-1; n++){
                double d = (double) a[m][n] - (c * a[index][n]);
                if(m == index){
                    continue;
                }
                a[m][n] = (double) d;

            }
            System.out.println(" ");
        }

    }

    public void hitungt(){
        double b = (double) a[index][zKecil];
        for(int n = 0; n < y-1; n++){
            a[index][n] = (a[index][n]/b);
        }
    }

    public String outputTabel(){
        String s = "";
        for(int i = 0; i< x; i++){
            for(int j = 0; j< y; j++){
                s = s + "\t\t" + df.format(a[i][j]);
            }
            s = s + "\n";
        }
        return s;
    }

    public String output(){
        String s1 = "";

        for(int w = 1; w<=var; w++){
            int no = (int) nilaiSolusi.get(nilaiSolusi.size()-(w+1));
            s1 = s1 + "Nilai x" +w+ " : "+a[no][y-2]+ "\n";
            //s1 = s1 + nilaiSolusi.size() +" "+ a[(int) nilaiSolusi.get(0)][y-2]  +" "+  a[(int) nilaiSolusi.get(1)][y-2]  +" "+ a[(int) nilaiSolusi.get(2)][y-2]  + "\n";
        }

        s1 = s1 + "Nilai Z : "+a[0][y-2];
        return s1;
    }
}
