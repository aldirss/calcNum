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
public class Newton {
   private  Expr expr = null;
   private Expr diffExpr = null;
     
   private double low  = 0;
   private double high = 0;
   private double step = 0; 
   public static double EPS = 0.0001;
   private ArrayList<NewtonIterationData> raizHist;
   
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
      
    public Newton(Expr expr , Expr diffExpr,   double low, double high, double step) {
        this.diffExpr = diffExpr;
        this.expr = expr;
         
        this.low = low;
        this.high = high;
        this.step = step;
        raizHist = new ArrayList<NewtonIterationData>();
    }
    public Newton() {
        this.diffExpr = null;
        raizHist = new ArrayList<NewtonIterationData>();
        
    }
    public double raiz(Expr expr, Expr diffExpr,  double low, double high) throws Exception{
         int it=0; 
         double fx0,fx,diffFx, xn,x0,erro;
         Variable x = Variable.make("x");
          x.setValue(high);
          fx = expr.value();
          diffFx = diffExpr.value();
          if (fx*diffFx > 0 )
              x0 = high;
          else{
             x.setValue(low);
             fx = expr.value();
             diffFx = diffExpr.value();
             x0 = low;
             if (fx*diffFx <= 0 )
                 throw new Exception("Não há raizes no intervalo.");
          }
          
          fx0 = 0;
          erro = 1+fx - fx0;
          erro = erro>0?erro:-1*erro;        
          xn = x0;
          while (erro > EPS) {
             raizHist.add(new NewtonIterationData(low, high, xn, fx,diffFx ,erro, it));
             fx0 = fx;
             x0 = xn;
             xn = x0 - fx/diffFx;
             x.setValue(xn);
             fx = expr.value();
             x.setValue(xn);
             diffFx = diffExpr.value();
             erro = fx - fx0;
             erro = erro>0?erro:-1*erro;        
             it++;      
             if (it >= 100) break;
        }
         // NewtonIterationData bid = raizHist.get(0);
         // bid.setErro(-1.0);
         return xn;
    }

    /**
     * @return the raizHist
     */
    public ArrayList<NewtonIterationData> getRaizHist() {
      
        return raizHist;
    }
    public void printHistory(){
      NewtonIterationData it;
        Iterator<NewtonIterationData> iterator = raizHist.iterator(); 
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
