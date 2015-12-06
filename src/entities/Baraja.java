package entities;

import java.util.Collections;
import java.util.LinkedList;

public class Baraja 
{
	private LinkedList<Carta> barajaDeCartas;
	
	public Baraja()
	{
		barajaDeCartas = new LinkedList<>();
		//Carta cartaAux;
		
		for(int palo=0;palo<=3;palo++) 
		{
			for(int valor=1;valor<=13;valor++) 
			{
				//System.out.print(String.valueOf(palo)+","+String.valueOf(valor));
				//cartaAux = new Carta(palo,valor);
				//System.out.print(cartaAux);
				//System.out.println(String.valueOf(cartaAux.getPalo())+" y tengo "+String.valueOf(cartaAux.getValor()));
				barajaDeCartas.add(new Carta(palo,valor));
			}
		}
	}
	
	/**
	 * Usar este método solo cuando la clase
	 * esté instanciada
	 * */
	public void barajar() 
	{
		for(int i = 51;i>0;i--) 
		{
			int rand = (int)(Math.random()*(i+1));
			Carta temp = barajaDeCartas.get(i);
			barajaDeCartas.add(i, barajaDeCartas.get(rand));
			barajaDeCartas.remove(i+1);
			barajaDeCartas.add(rand, temp);
			barajaDeCartas.remove(rand+1);
		}
	}
	
	/**
	 * Función creada para realizar testing
	 */
	public void barajarConElitismo()
	{
		barajaDeCartas.add(new Carta(1,3));
		barajaDeCartas.add(new Carta(1,2));
		barajaDeCartas.add(new Carta(3,2));
		barajaDeCartas.add(new Carta(2,4));
		barajaDeCartas.add(new Carta(0,2));
		barajaDeCartas.add(new Carta(2,2));
		barajaDeCartas.remove(0);
		barajaDeCartas.remove(0);
		barajaDeCartas.remove(0);
		barajaDeCartas.remove(0);
		barajaDeCartas.remove(0);
		barajaDeCartas.remove(0);
	}
	
	/**
	 * Devuele el número de cartas
	 * presentes actualmente en la baraja
	 * @return número de cartas en baraja
	 */
	public int enLaBaraja()
	{
		return barajaDeCartas.size();
	}
	
	public void organizarBaraja()
	{
		Collections.sort(barajaDeCartas);
	}
	
	public Carta obtenerCarta() 
	{
		if (enLaBaraja() == 0)
		{
			barajar();
		}
		return barajaDeCartas.removeLast();
	}

	@Override
	public String toString() 
	{
		return "Baraja [barajaDeCartas=" + barajaDeCartas + "]";
	}
	
	

}
