/**
 * @author Rivera Garcia Ignacio
 * @author Garcia Zarraga Angelica Lizbeth
 * @date 18 Mayo 2019
 * Implementacion de Heaps minimos y maximos.
 */
package practica8;
import java.util.Comparator;
/**
 * Clase que implementa montículos mínimos y montículos máximos con enteros.
 */
public class Monticulo{

    private int[] elementos;
    private Comparator<Integer> comparador;
    private int ultimoIndice;
    /**
     * Constructor de un Monticulo vacio, con el tamaño del arreglo en 100 y
     * ultimoIndice en -1
     */
    public Monticulo(){
		int[] array = new int[100];
		elementos = array;
		ultimoIndice = -1;
		comparador = (x,y) -> x-y;
    }
    /**
     * Constructor de Monticulo apartir de un comparator.
     * @param Comparator<Integer> comparador
     */
    public Monticulo(Comparator<Integer> comparador){
		int[] array = new int[100];
		elementos = array;
		ultimoIndice = -1;
		this.comparador = comparador;
    }
    /**
     * Constructor de un Monticulo apartir de un arreglo de enteros.
     * @param int[] elementos
     */
    public Monticulo(int[] elementos){
    	ultimoIndice = -1;
		comparador = (x,y) -> x-y;

    	int[] mainheap = new int[elementos.length];
    	this.elementos = mainheap;
    	for(int i=0;i<elementos.length;i++){
    		agrega(elementos[i]);
    	}
    }
    /**
     * Constructor de un Monticulo apartir de un arreglo de enteros y
     * un comparator.
     * @param int[] elementos, Comparator<Integer> comparador
     */
    public Monticulo(int[] elementos, Comparator<Integer> comparador){
		this.elementos = elementos;
		ultimoIndice = -1;
		this.comparador = comparador;
    }
    /**
     * elimina, elimina la raiz (primer elemento) de nuestro Monticulo.
     * @return int
     */
    public int elimina(){
		if(elementos[0] == 0){
			throw new IllegalStateException("El Monticulo esta vacio.");
		}
		int res = elementos[0];
		int nuevaRaiz = elementos[ultimoIndice];
		elementos[0] = nuevaRaiz;
		elementos[ultimoIndice] = 0;
		ultimoIndice--;
		reacomodoArriba();
		return res;
    }
    /**
     * contiene nos indica se un elemento esta o no en nuestro
     * montículo.
     * @param int elemento
     * @return boolean
     */
    public boolean contiene(int elemento){
		for(int i=0;i<=ultimoIndice;i++){
			if(elementos[i] == elemento){
				return true;
			}
		}
		return false;
    }
    /**
     * esVacio nos indica si nuestro monticulo no tiene elementos.
     * @return boolean
     */
    public boolean esVacio(){
		for(int i=0;i<=ultimoIndice;i++){
			if(elementos[i] != 0){
				return false;
			}else{
				continue;
			}
		}
		return true;
    }
    /**
     * getTamano nos regresa el tamaño de nuestro
     * Monticulo.
     * @return int
     */
    public int getTamano(){
		return elementos.length;
    }
    /**
     * agrega, agrega un elemento "i" a nuestro monticulo
     * y reacomoda.
     * @param int elemento a agregar
     */
    public void agrega(int i){
    	ultimoIndice++;
		if(elementos[ultimoIndice] != 0){
			traslada();
			elementos[ultimoIndice+1] = i;
			ultimoIndice = ultimoIndice+1;
			reacomodoSort(ultimoIndice);
		}
		elementos[ultimoIndice] = i;
		reacomodoSort(ultimoIndice);
    }
    //METODOS AUXILIARES.

    /**
     * reacomodoSort metodo auxiliar que reacomoda del indice dado
     * hacia la raiz.
     * @param int indice
     */
    public void reacomodoSort(int indice){
    	int padre =(indice-1)/2;
    	int supp = 0;
    	supp = indice;
    	if(supp == 0){
    		return;
    	}
    	while(padre != 0){

    		if( comparador.compare(elementos[padre],elementos[supp]) > 0){
    			int ePadre = elementos[padre];
    			int eHijo = elementos[supp];

    			elementos[padre] = eHijo;
    			elementos[supp] = ePadre;
    		}
    		supp = padre;
    		padre = (supp-1)/2;
    	}

    	if( comparador.compare(elementos[padre],elementos[supp]) > 0){
    		int ePadre = elementos[padre];
    		int eHijo = elementos[supp];

    		elementos[padre] = eHijo;
    		elementos[supp] = ePadre;
    	}
    }
    /**
     * hijoMenor nos regresa el hijo de menor valor
     * de un elemento en nuestro monticulo.
     * @pram int indice
     * @return int
     */
    public int hijoMenor(int indice){
    	int indxHI = 2*indice+1;
    	int indxHD = 2*indice+2;

    	if(elementos[indxHD] == 0 && elementos[indxHI] == 0){

    	}

    	if(comparador.compare(elementos[indxHD],elementos[indxHI])>0){
    		return indxHD;
    	}else{
    		return indxHI;
    	}
    }
    /**
     * reacomodoArriba reacomoda de la raiz para el final de nuestro
     * monticulo.
     */
    public void reacomodoArriba(){
     	int supp = 0;
    	while(2*supp+1<elementos.length){

    		if( (comparador.compare(elementos[supp],elementos[hijoMenor(supp)]) > 0) && (elementos[hijoMenor(supp)] != 0)){
    			int ePadre = elementos[supp];
    			int eHijo = elementos[hijoMenor(supp)];

    			elementos[supp] = eHijo;
    			elementos[hijoMenor(supp)] = ePadre;

    			supp = hijoMenor(supp);
    			continue;
    		}
    		break;
    	}
    }
    /**
     * Metodo toString que le da un formato a nuestro
     * monticulo
     * @return String
     */
    @Override
    public String toString(){
    	String cad = "[";
  		if(esVacio()){
  			return "[]";
  		}
  		cad = cad + elementos[0];
    	for(int i=1;i<=ultimoIndice;i++){
    		cad = cad + ", " + elementos[i];
    	}
    	return cad + "]";
    }
    /**
     * traslada convierte nuestro arreglo de enteros en uno con el
     * doble de tamaño que tenia.
     */
    public void traslada(){
    	int[] nuevoElementos = new int[elementos.length*2];
    	for(int i =0;i<=ultimoIndice;i++){
    		nuevoElementos[i]=elementos[i]; 
    	}
    	elementos = nuevoElementos;
    }

     //metodo principal de nuestro programa.
    public static void main (String[] args){
    	int[] array = {3,73,12,1,5,9,2,34,21,7};

    	Monticulo demo = new Monticulo(array);
    	System.out.println(demo);
    }
}