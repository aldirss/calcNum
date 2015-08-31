/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import calcnumerico.raiz.Bissecao;
import calcnumerico.raiz.BissecaoIterationData;
import expr.Expr;
import expr.Parser;
import expr.SyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

 

/**
 *
 * @author ald1r
 */
@ManagedBean
@SessionScoped
public class BissecaoBean {
   private final static List<String> heads = Arrays.asList("N","a","b","x","f(x)","Erro");

    /**
     * @return the heads
     */
    public static List<String> getHeads() {
        return heads;
    }
    Bissecao b;
    private  Expr expr = null;
   
   
   private ArrayList<BissecaoIterationData> raizList;
           
   private String f = null;
   private double low  = 0;
   private double high = 0;
   private double step = 0; 
   private double eps = 0.001;
   private double result;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /**
     * @return the f
     */
    public String getF() {
        return f;
    }

    /**
     * @return the low
     */
    public double getLow() {
        return low;
    }

    /**
     * @return the high
     */
    public double getHigh() {
        return high;
    }

    /**
     * @return the step
     */
    public double getStep() {
        return step;
    }

    /**
     * @param f the f to set
     */
    public void setF(String f) {
        this.f = f;
    }

    /**
     * @param low the low to set
     */
    public void setLow(double low) {
        this.low = low;
    }

    /**
     * @param high the high to set
     */
    public void setHigh(double high) {
        this.high = high;
    }

    /**
     * @param step the step to set
     */
    public void setStep(double step) {
        this.step = step;
    }
    public void raiz(){
        b = new Bissecao();
        Bissecao.EPS = this.eps;
        Expr expr = null;
	try {
	    expr = Parser.parse(f); 
	} catch (SyntaxException e) {
	    System.err.println(e.explain());	    
	}
        if (step == 0)
            step = 0.001;        
        result = b.raiz(expr, low, high);
    }

    /**
     * @return the raizList
     */
    public ArrayList<BissecaoIterationData> getRaizList() {
        if (b == null )return null ; //b = new Bissecao();
        return b.getRaizHist();
    }

    /**
     * @return the result
     */
    public double getResult() {
        return result;
    }

    /**
     * @return the eps
     */
    public double getEps() {
        return eps;
    }

    /**
     * @param eps the eps to set
     */
    public void setEps(double eps) {
        this.eps = eps;
    }
    
    
}
