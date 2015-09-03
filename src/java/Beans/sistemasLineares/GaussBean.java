/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans.sistemasLineares;

import calcnumerico.sistemasLineares.Gauss;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.Matrix;

/**
 *
 * @author ald1r
 */
@ManagedBean
@SessionScoped
public class GaussBean {
    private String msgMatrizATria;
    private String msgMatrizb;
    
    
    private Gauss gauss;
    private List<List<Double>> triu = null; //A matriz triangularized
    private Matrix A = null;
    private Matrix b = null;
    private String sA = null;
    private String sb = null;
    private int colNum = 0;
    private ArrayList<double[]> sx = null;
    private ArrayList<double[]> sr = null;

    public void solve() throws Exception {
        convertA();
        convertB();
        try {
            gauss = new Gauss(A, getB());
            gauss.solve();
            sx = gauss.getX().toList();
            sr = gauss.getR().toList();
            b = gauss.getB();
            A = gauss.getA();

        } catch (Exception e) {
            throw e;
        }
    }

    private void convertA() {
        ArrayList<double[]> alrows = new ArrayList<double[]>();
        double dr[];
        /* String sA = "  8.7 3 9.3 11\n"
         + "24.5 -8.8    11.5 -45.1\n"
         + "52.3 -84        -23.5 11.4\n"
         + "21    -81 -13.2 21.5   ";
         */
        if (getsA() == null) {
            throw new NullPointerException("Matriz nula");
        }
        setsA(getsA().trim());
        String[] rows = getsA().split("\n");
        int size = rows[0].split(" +").length;
        int m = 0;
        for (String row : rows) {
            String[] el = row.split(" +");

            dr = new double[el.length];
            m = 0;
            for (int i = 0; i < dr.length; i++) {
                if (!el[i].isEmpty()) {
                    dr[m] = Double.valueOf(el[i]);
                    m++;
                }
            }
            alrows.add(dr);
            System.out.println("");
        }
        double[][] Amatrix = new double[m][m];
        for (int i = 0; i < m; i++) {
            Amatrix[i] = alrows.get(i);
        }
        A = new Matrix(Amatrix);

    }

    private void convertB() {
        /**
         * **************************************
         */
        int m;
        if (getSb() == null) {
            throw new NullPointerException("Matriz nula");
        }

        String[] rhs = getSb().split("\n");
        setSb(getSb().trim());
        List<Double> alrhs = new ArrayList<Double>();
        m = 0;
        for (String rh : rhs) {
            if (!rh.isEmpty()) {
                alrhs.add(Double.valueOf(rh));
                m++;
            }
        }
        double bb[][] = new double[m][1];
        for (int i = 0; i < m; i++) {
            bb[i][0] = alrhs.get(i);
        }
        setB(new Matrix(bb));
    }

    /**
     * @param sA the sA to set
     */
    public void setsA(String sA) {
        this.sA = sA;

    }

    /**
     * @param sb the sb to set
     */
    public void setSb(String sb) {
        this.sb = sb;
    }

    /**
     * @return the sx
     */
    public ArrayList<double[]> getSx() {
        return sx;
    }

    /**
     * @return the sr
     */
    public ArrayList<double[]> getSr() {
        return sr;
    }

    /**
     * @return the sA
     */
    public String getsA() {
        return sA;
    }

    /**
     * @return the sb
     */
    public String getSb() {
        return sb;
    }

    /**
     * @return the triu
     */
    public List<List<Double>> getTriu() {
         triu = new ArrayList<List<Double>>();
        if (A == null) {
            setColNum(0);
            return triu;
        }
        double[][] mat = A.getData();
        setColNum(A.getM());
       

        for (int i = 0; i < A.getM(); i++) {
            //this._Matrix.add(new ArrayList<Float>());
    //this._Matrix.add(new ArrayList<Float>());
    
            triu.add( new ArrayList<Double>() );
            for (int j = 0; j < A.getN(); j++) {
                triu.get(i).add(mat[i][j]);
            }
        }
        return triu;
    }

    /**
     * @return the b
     */
    public Matrix getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(Matrix b) {
        this.b = b;
    }

    /**
     * @return the colNum
     */
    public int getColNum() {
        //colNum = gauss.getA().getM();
        return colNum;
    }

    /**
     * @param colNum the colNum to set
     */
    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    /**
     * @return the magMatrizATria
     */
    public String getMsgMatrizATria() {
        if (A != null) 
            msgMatrizATria = "Matriz A triangularizada";
        else msgMatrizATria="";
        return msgMatrizATria;
    }

    /**
     * @return the msgMatrizb
     */
    public String getMsgMatrizb() {
        if (A != null) 
            msgMatrizb = "Vetor b após triangularização";
        else msgMatrizb="";
        
        return msgMatrizb;
    }

}
