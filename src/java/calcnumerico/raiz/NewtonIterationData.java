/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcnumerico.raiz;

import sun.font.TrueTypeFont;

/**
 *
 * @author ald1r
 */
 public class NewtonIterationData{
   private double low  = 0;
   private double high = 0;
     
   private double xn = 0.0; 
   private double fxn = 0.0;
   
   private double difffxn = 0.0;
   
   private double erro = 0.0;
   private int it;
  
    public NewtonIterationData(double low, double high, double xn, double fxn,double difffxn, double err, int it){
      this.low = low;
      this.high = high;
      this.xn = xn;
      this.fxn = fxn;
      this.difffxn = difffxn;
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
     * @return the xn
     */
    public double getXn() {
        return xn;
    }

    /**
     * @return the fxn
     */
    public double getFxn() {
        return fxn;
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
     * @param xn the xn to set
     */
    public void setXn(double xn) {
        this.xn = xn;
    }

    /**
     * @param fxn the fxn to set
     */
    public void setFxn(double fxn) {
        this.fxn = fxn;
    }

    /**
     * @param erro the erro to set
     */
    public void setErro(double erro) {
        this.erro = erro;
    }

    /**
     * @param it the it to set
     */
    public void setIt(int it) {
        this.it = it;
    }

    /**
     * @return the difffxn
     */
    public double getDifffxn() {
        return difffxn;
    }

    /**
     * @param difffxn the difffxn to set
     */
    public void setDifffxn(double difffxn) {
        this.difffxn = difffxn;
    }

  
}  
