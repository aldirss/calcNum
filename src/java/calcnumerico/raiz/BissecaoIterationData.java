/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcnumerico.raiz;

/**
 *
 * @author ald1r
 */
 public class BissecaoIterationData{
   private double low  = 0;
   private double high = 0;
     
   private double x0 = 0.0; 
   private double fx0 = 0.0;
   private double erro = 0.0;
   private int it;
   public BissecaoIterationData(double low, double high, double x0, double fx0, double err, int it){
      this.low = low;
      this.high = high;
      this.x0 = x0;
      this.fx0 = fx0;
      this.erro = err;
      this.it = it;
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
     * @return the x0
     */
    public double getX0() {
        return x0;
    }

    /**
     * @return the fx0
     */
    public double getFx0() {
        return fx0;
    }

    /**
     * @return the erro
     */
    public double getErro() {
        return erro;
    }

    /**
     * @return the it
     */
    public int getIt() {
        return it;
    }

    /**
     * @param it the it to set
     */
    public void setIt(int it) {
        this.it = it;
    }

    /**
     * @param erro the erro to set
     */
    public void setErro(double erro) {
        this.erro = erro;
    }
}  
