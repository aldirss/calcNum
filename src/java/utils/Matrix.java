package utils;

import java.util.ArrayList;

/**
 *
 * @author 
 * Copyright © 2000–2011, Robert Sedgewick and Kevin Wayne. 
Last updated: Sun Aug 2 18:43:37 EDT 2015.
 */

/******************************************************************************
 *  Compilation:  javac Matrix.java
 *  Execution:    java Matrix
 *
 *  A bare-bones immutable data type for M-by-N matrices.
 *
 ******************************************************************************/

final public class Matrix {
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final double[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            System.arraycopy(data[i], 0, this.data[i], 0, N);
    }

    // copy constructor
    public Matrix(Matrix A) { this(A.data); }

    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // create and return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = getData()[i];
        data[i] = getData()[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(getN(), getM());
        for (int i = 0; i < getM(); i++)
            for (int j = 0; j < getN(); j++)
                A.data[j][i] = this.getData()[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.getM() != A.getM() || B.getN() != A.getN()) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(getM(), getN());
        for (int i = 0; i < getM(); i++)
            for (int j = 0; j < getN(); j++)
                C.data[i][j] = A.getData()[i][j] + B.getData()[i][j];
        return C;
    }


    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.getM() != A.getM() || B.getN() != A.getN()) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(getM(), getN());
        for (int i = 0; i < getM(); i++)
            for (int j = 0; j < getN(); j++)
                C.data[i][j] = A.getData()[i][j] - B.getData()[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.getM() != A.getM() || B.getN() != A.getN()) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < getM(); i++)
            for (int j = 0; j < getN(); j++)
                if (A.getData()[i][j] != B.getData()[i][j]) return false;
        return true;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.getN() != B.getM()) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.getM(), B.getN());
        for (int i = 0; i < C.getM(); i++)
            for (int j = 0; j < C.getN(); j++)
                for (int k = 0; k < A.getN(); k++)
                    C.data[i][j] += (A.getData()[i][k] * B.getData()[k][j]);
        return C;
    }


    // return x = A^-1 b, assuming A is square and has full rank
    public Matrix solve(Matrix rhs) {
      return null;
    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < getM(); i++) {
            for (int j = 0; j < getN(); j++) 
                System.out.printf("%9.4f ", getData()[i][j]);
            System.out.println();
        }
    }
    /***Modified by 
     * 
     * Dr. Aldir Sousa - 
     * september 01 2015     * 
     * aldirss@uespi.br
     *      
     * @return out
     ********/
    @Override
    public String toString(){
        String out = "";
        for (int i = 0; i < getM(); i++) {
            out = out +"<p>";
            for (int j = 0; j < getN(); j++) 
                out = out + data[i][j]+" ";
            out = out + "</p>";
        }
        return out;        
    }
    public ArrayList<double[]> toList(){
       ArrayList<double[]> l = new ArrayList<double[]>();
       for (int i = 0; i < M; i++)
           l.add(data[i]);
        return l;
    }

    // test client
    public static void main2(String[] args) {
        double[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };
        Matrix D = new Matrix(d);
        D.show();        
        System.out.println();

        Matrix A = Matrix.random(5, 5);
        A.show(); 
        System.out.println();

        A.swap(1, 2);
        A.show(); 
        System.out.println();

        Matrix B = A.transpose();
        B.show(); 
        System.out.println();

        Matrix C = Matrix.identity(5);
        C.show(); 
        System.out.println();

        A.plus(B).show();
        System.out.println();

        B.times(A).show();
        System.out.println();

        // shouldn't be equal since AB != BA in general    
        System.out.println(A.times(B).eq(B.times(A)));
        System.out.println();

        Matrix b = Matrix.random(5, 1);
        b.show();
        System.out.println();

        Matrix x = A.solve(b);
        x.show();
        System.out.println();

        A.times(x).show();
        
    }

    /**
     * @return the M
     */
    public int getM() {
        return M;
    }

    /**
     * @return the N
     */
    public int getN() {
        return N;
    }

    /**
     * @return the data
     */
    public double[][] getData() {
        return data;
    }
}
 