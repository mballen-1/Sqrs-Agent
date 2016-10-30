/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.learn.unsupervised.agglomerative.rica;

/**
 *
 * @author jgomez
 */
public class PyramidalMinMaxDistance implements MinMaxDistance {
    @Override
    public double compute( int N, int d ){
        return 2.0*Math.sqrt(d/3.0)/Math.pow(N,1.0/d);
    }
}
