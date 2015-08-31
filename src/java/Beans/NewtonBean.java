/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import calcnumerico.raiz.Bissecao;
import calcnumerico.raiz.BissecaoIterationData;
import calcnumerico.raiz.Cordas;
import calcnumerico.raiz.CordasIterationData;
import calcnumerico.raiz.Newton;
import calcnumerico.raiz.NewtonIterationData;
import expr.Expr;
import expr.Parser;
import expr.SyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

 

/**
 *
 * @author ald1r
 */
@ManagedBean
@SessionScoped
public class NewtonBean {
   private final static List<String> heads = Arrays.asList("N","a","b","x","f(x)","Erro");

    /**
     * @return the heads
     */
    public static List<String> getHeads() {
        return heads;
    }
    private  Newton b;
    private  Expr expr = null;
    private  Expr DiffExpr = null            ;
   
   
   private ArrayList<BissecaoIterationData> raizList;
           
   private String f = "x^2-2";
   private String diffF = "2*x"; 
   
   
   private double low  = 0;
   private double high = 2;
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
   

    /**
     * @return the raizList
     */
    public ArrayList<NewtonIterationData> getRaizList() throws Exception  {
        if (b == null ){
           raiz();
        }
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

    /**
     *
     * @param context
     * @param component
     * @param value
     */
    public void validateExpr(FacesContext context, UIComponent component, java.lang.Object value) {
            String exp = (String) value;
            
           // String clubNameFromComp = (String) newtonBean.getSubmittedValue();
	     try {
               expr = Parser.parse(exp); 
        } catch (SyntaxException e) {
            String msg = e.getMessage();
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));            
        }            
     }
    
     public void raiz() throws SyntaxException, Exception  {
        b = new Newton();
        Bissecao.EPS = this.eps;
       // Expr expr = null;
	    expr = Parser.parse(f); 
	
        Expr diffExpr = null;
	diffExpr = Parser.parse(diffF); 
	  
        if (step == 0)
            step = 0.001;        
        result = b.raiz(expr,diffExpr, low, high);
    }

    /**
     * @return the diffF
     */
    public String getDiffF() {
        return diffF;
    }

    /**
     * @param diffF the diffF to set
     */
    public void setDiffF(String diffF) {
        this.diffF = diffF;
    }
}
