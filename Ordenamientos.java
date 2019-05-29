/**
 * @author Rivera Garcia Ignacio
 * @author Garcia Zarraga Angelica Lizbeth
 * @date 18 Mayo 2019
 * Implementacion de Heaps minimos y maximos.
 */
package practica8;
import java.util.Iterator;
/**
 * Clase con varios métodos para ordenar arreglos y listas.
 */
public class Ordenamientos{
    /**
     * heapSort realiza un heapSort sobre un arreglo de enteros
     * @param int[] a
     */
    public static void heapSort(int[] a){
    	Monticulo nuevo = new Monticulo(a);
    	int[] ordenado = new int[a.length];
		for(int i = 0;i<a.length;i++){
			int elemento = nuevo.elimina();
            System.out.println(elemento);
			ordenado[i] = elemento;
    	}
    	impArr(ordenado);
    }
    /**
     * imArr imprime nuestro arrreglo.
     * @param int[] odr
     */
    public static void impArr(int[] odr){
    	String cad = "[" + odr[0];
    	for(int i = 1;i<odr.length;i++){
    		cad = cad + ", " + odr[i];
    	}
    	System.out.println(cad + "]");
    }

    public static void main (String[] args){
        int[] array = {6,4,1,5,7,3,2};
        heapSort(array);
    }
}
