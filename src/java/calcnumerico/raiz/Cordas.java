/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcnumerico.raiz;

import expr.Expr;
import expr.Variable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ald1r
 */
public class Cordas {
     private  Expr expr = null;
   
     
   private double low  = 0;
   private double high = 0;
   private double step = 0; 
   public static double EPS = 0.0001;
   private ArrayList<CordasIterationData> raizHist;
   
    /**
     * @return the EPS
     */
    public static double getEPS() {
        return EPS;
    }

    /**
     * @param aEPS the EPS to set
     */
    public static void setEPS(double aEPS) {
        EPS = aEPS;
    }
      
    public Cordas(Expr expr,   double low, double high, double step) {
        this.expr = expr;
         
        this.low = low;
        this.high = high;
        this.step = step;
        raizHist = new ArrayList<CordasIterationData>();
    }
    public Cordas() {
        raizHist = new ArrayList<CordasIterationData>();
    }
    public double raiz(Expr expr,  double low, double high){
         int it=0; 
         double fa,fb,fx0,fxn,x0,xn,erro;
         Variable x = Variable.make("x");
          x0 = 0;
          xn = 1;
          fx0 = 0;
          fxn = 1;
          erro = fxn - fx0;
        while (erro > EPS) {
          x.setValue(low);
          fa = expr.value();
          
          x.setValue(high);
          fb = expr.value();
          
          xn = low - (high - low)*fa/(fb - fa);
          x.setValue(xn);
          fxn = expr.value();
          
          erro = fxn - fx0;
          erro = erro>0?erro:-1*erro;
          fx0 = fxn;
          raizHist.add(new CordasIterationData(low, high, xn, fxn,  erro, it));
          it++;
          if (fxn*fa < 0)
              high = xn;
          else
              low = xn;              
        }
          CordasIterationData bid = raizHist.get(0);
          bid.setErro(-1.0);
         return xn;
    }

    /**
     * @return the raizHist
     */
    public ArrayList<CordasIterationData> getRaizHist() {
      
        return raizHist;
    }
    public void printHistory(){
      CordasIterationData it;
        Iterator<CordasIterationData> iterator = raizHist.iterator(); 
      int i = 0;
      System.out.println("N  |   a   |    b    |   x0    |   Fx0  | Erro ");
      while (iterator.hasNext()) 
      {  
         it = iterator.next();         
         System.out.println(i+"  |  "+it.getLow()+"  |  "+it.getHigh()+"   |  "+it.getXn()+"   |  "+it.getFxn() +"  |  "+it.getErro()); 
         i++;
      }
   }

    /**
     * @return the expr
     */
    public Expr getExpr() {
        return expr;
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
     * @param expr the expr to set
     */
    public void setExpr(Expr expr) {
        this.expr = expr;
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
}
