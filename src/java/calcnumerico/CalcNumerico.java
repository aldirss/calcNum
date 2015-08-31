/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcnumerico;

import calcnumerico.raiz.Bissecao;
import expr.Expr;
import expr.Parser;
import expr.SyntaxException;
import expr.Variable;

/**
 *
 * @author ald1r
 */
public class CalcNumerico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Expr expr;
	try {
	    expr = Parser.parse("x^2 - 3"); 
	} catch (SyntaxException e) {
	    System.err.println(e.explain());
	    return;
	}
        double low  = 1;
	double high = 2;
	double step = 0.001;

        Bissecao bis = new Bissecao(null, low, high, step);
        System.out.println("f(x) = "+expr+"\n x0 = "+ bis.raiz(expr, low, high));
        bis.printHistory();
        
        
	
	Variable x = Variable.make("x");
	for (double xval = low; xval <= high; xval += step) {
	    x.setValue(xval);
	    //System.out.println(expr.value());
	}
    }
    
}
