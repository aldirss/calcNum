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


public class Bissecao {
   private  Expr expr = null;
   private double low  = 0;
   private double high = 0;
   private double step = 0; 
   public static double EPS = 0.0001;
   private ArrayList<BissecaoIterationData> raizHist;
   
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
    
    

   
    public Bissecao(Expr expr, double low, double high, double step) {
        this.expr = expr;
        this.low = low;
        this.high = high;
        this.step = step;
        raizHist = new ArrayList<BissecaoIterationData>();
    }
    public Bissecao() {
        raizHist = new ArrayList<BissecaoIterationData>();
    }
    public double raiz(Expr expr, double low, double high){
        int it; 
        double fx0,fa,x0,xa;
         Variable x = Variable.make("x");
         x.setValue(low);
         fa = expr.value();         
         xa = Integer.MAX_VALUE;
         x0 = (low+high)/2.0;
         it = 0;
         while(Math.abs(x0-xa) > getEPS() ){
             x.setValue(x0);
	     fx0 = expr.value();
             raizHist.add(new BissecaoIterationData(low, high, x0,fx0,x0-xa,it));             
     
             if (fa*fx0 < 0)
                 high = x0;
             else{
                 low = x0;            
                 x.setValue(low);
                 fa = expr.value();
             }
             xa = x0;
             x0 = (low+high)/2.0;
             it++;
         } 
          BissecaoIterationData bid = raizHist.get(0);
          bid.setErro(-1.0);
         return x0;
    }

    /**
     * @return the raizHist
     */
    public ArrayList<BissecaoIterationData> getRaizHist() {
      
        return raizHist;
    }
    public void printHistory(){
      BissecaoIterationData it;
        Iterator<BissecaoIterationData> iterator = raizHist.iterator(); 
      int i = 0;
      System.out.println("N  |   a   |    b    |   x0    |   Fx0  | Erro ");
      while (iterator.hasNext()) 
      {  
         it = iterator.next();         
         System.out.println(i+"  |  "+it.getLow()+"  |  "+it.getHigh()+"   |  "+it.getX0()+"   |  "+it.getFx0() +"  |  "+it.getErro()); 
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
