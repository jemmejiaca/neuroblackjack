package entities;

import java.util.ArrayList;

public class Jugador 
{
	private ArrayList<Carta> jugada;

	public Jugador()
	{
		jugada = new ArrayList<>();
	}
	
	public Jugador(ArrayList<Carta> jugada) 
	{
		this.jugada = jugada;
	}

	public ArrayList<Carta> getJugada() 
	{
		return jugada;
	}

	public void setJugada(ArrayList<Carta> jugada) 
	{
		this.jugada = jugada;
	}
	
	public void pedirCarta(Carta card)
	{
		if(cartasEnMano()<5)
		{
			jugada.add(card);
		}
		else
		{
			System.out.println("No se pueden recoger mas cartas");
		}
	}
	
	public void tirarMano()
	{
		jugada.clear();
	}
	
	public int[] mostarCarta(int index)
	{
		int tmp[] = new int[2];
		tmp[0] = mostrarPalo(index);
		tmp[1] = mostrarValor(index);
		return tmp;
	}
	
	public int cartasEnMano()
	{
		return jugada.size();
	}
	
	public int obtenerTotal()
	{
		int acum=0,puntos=0;
		for(Carta carta : jugada) 
		{
			if(carta.getValor()>=10)
			{
				puntos = 10;
				acum += puntos;
			}
			else if(carta.getValor()==1)
			{
				puntos = 11;
				acum += puntos;
				if(acum>21)
				{
					puntos = 1;
					acum += puntos-11;
				}
			}
			else
			{
				puntos = carta.getValor();
				acum += puntos;
			}
		}
		
		if(acum>21 && asEnLaMano())
		{
			acum=0;
			puntos=0;
			for (Carta carta : jugada) 
			{
				if(carta.getValor()>=10)
				{
					puntos = 10;
					acum += puntos;
				}
				else if(carta.getValor()==1)
				{
					puntos = 1;
					acum += puntos;
				}
				else
				{
					puntos = carta.getValor();
					acum += puntos;
				}
			}
		}
		
		return acum;
	}
	
	public boolean blackjack()
	{
		if(cartasEnMano()==2 && obtenerTotal()==21)
		{
			if(jugada.get(0).getValor()>=10 || jugada.get(0).getValor()==1)
			{
				return true;
			}
		}
		return false;
	}
	
	private int mostrarPalo(int index)
	{
		return jugada.get(index).getPalo();
	}
	
	private int mostrarValor(int index)
	{
		return jugada.get(index).getValor();
	}
	
	private boolean asEnLaMano()
	{
		for(Carta carta : jugada)
		{
			if(carta.getValor()==1)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() 
	{
		return "Mano [jugada=" + jugada + "]";
	}

}
