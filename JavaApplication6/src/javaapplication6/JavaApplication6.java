/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author Freddy
 */
public class JavaApplication6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JavaApplication6 ja = new JavaApplication6();
        int[] a = {1,2,3};
        int[] a1 = ja.fix(a);
        System.out.print(a[0]+a[1]+a[2]+ " ");
        System.out.print(a1[0]+a1[1]+a1[2]);
    }
    
    public int[] fix(int[] a){
        a[1] = 3;
        return a;
    }
}
