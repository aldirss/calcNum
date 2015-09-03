/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcnumerico.sistemasLineares;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utils.Matrix;

/**
 *
 * @author ald1r
 */
public class Gauss {

    private static final double ZERO = 1.0E-7;

    private String msg;
    private final Matrix A;
    private final Matrix b;
    private Matrix r;
    private Matrix x;

    public Gauss(Matrix Aa, Matrix rhs) {
        // create copies of the data
        A = new Matrix(Aa);
        b = new Matrix(rhs);
    }

    public void solve() throws RuntimeException {
        // int M, N; 
        if (A.getM() != A.getN() || b.getM() != A.getN() || b.getN() != 1) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double pivot, m;

        for (int i = 0; i < A.getM(); i++) {
            // singular
            //if (A.getData()[i][i] == 0.0) {
           //     throw new RuntimeException("Matrix is singular.");
           // }
            pivot = A.getData()[i][i];
            for (int k = 1 + i; k < A.getM(); k++) {
                m = A.getData()[k][i] / pivot;
                for (int j = i + 1; j < A.getM(); j++) {
                    A.getData()[k][j] -= m * A.getData()[i][j];
                }
                A.getData()[k][i] = 0.0;
                b.getData()[k][0] -= m * b.getData()[i][0];
            }
        }
        
        // back substitution
        x = new Matrix(A.getM(), 1);
        int n = A.getM() - 1;
        x.getData()[n][0] = b.getData()[n][0] / A.getData()[n][n];

        double s;
        for (int i = n - 1; i >= 0; i--) {
            s = 0.0;
            for (int j = i + 1; j < A.getM(); j++) {
                s += x.getData()[j][0] * A.getData()[i][j];
            }
            x.getData()[i][0] = b.getData()[i][0] - s;
            if (Math.abs(A.getData()[i][i]) <= Gauss.ZERO) {
                if (x.getData()[i][0] <= Gauss.ZERO) {
                    msg = "Sistema indeterminado";
                } else {
                    msg = "Sistema incompatível";
                }
                throw new RuntimeException(getMsg());
            } else {
                x.getData()[i][0] = x.getData()[i][0] / A.getData()[i][i];
            }
        }
        System.out.println("Soluao");
        x.show();
        r = b.minus(A.times(x));
        System.out.println("Residuo");
        r.show();
    }

    private void convert() {
        ArrayList<double[]> drows = new ArrayList<double[]>();
        double dr[];
        String sA = "  8.7 3 9.3 11\n"
                + "24.5 -8.8    11.5 -45.1\n"
                + "52.3 -84        -23.5 11.4\n"
                + "21    -81 -13.2 21.5   ";
        sA = sA.trim();
        String[] rows = sA.split("\n");
        int size = rows[0].split(" +").length;
        int m=0;
        for (String row : rows) {
            String[] el = row.split(" +");

            dr = new double[el.length];
            m = 0;
            for (int i = 0; i < dr.length; i++) {
                if (!el[i].isEmpty()) {
                    dr[i] = Double.valueOf(el[i]);
                    m++; 
                }
            }
            drows.add(dr);
            System.out.println("");
        }
        // for (int i =0 ; i<drows.size(); i++)
        double[][] linhas = new double[m][m];
        for (int i = 0; i < m; i++){
              linhas[i] =  drows.get(i);
         
             for (int j = 0; j < m; j++){
               System.out.print(linhas[i][j]+"  ");
            }
            System.out.println("  ");
        }
    }

    public static void main(String[] args) {
        double[][] mA = {{8.7, 3, 9.3, 11}, {24.5, -8.8, 11.5, -45.1}, {52.3, -84, -23.5, 11.4}, {21, -81, -13.2, 21.5}}; //{{2, 3, -1}, {4, 4, -3}, {2, -3, 1}};
        Matrix lA = new Matrix(mA);
        lA.show();
        System.out.println();

        double mb[][] = new double[4][1];
        /*mb[0][0] = 5;
         mb[1][0] = 3;
         mb[2][0] = -1;*/
        mb[0][0] = 16.4;
        mb[1][0] = -49.7;
        mb[2][0] = -80.8;
        mb[3][0] = -106.3;

        Matrix lb = new Matrix(mb);
        lb.show();
        System.out.println();

        Gauss ga = new Gauss(lA, lb);
        ga.solve();

        ga.convert();

    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @return the A
     */
    public Matrix getA() {
        return A;
    }

    /**
     * @return the b
     */
    public Matrix getB() {
        return b;
    }

    /**
     * @return the r
     */
    public Matrix getR() {
        return r;
    }

    /**
     * @return the x
     */
    public Matrix getX() {
        return x;
    }

}
