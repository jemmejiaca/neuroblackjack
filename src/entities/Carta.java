package entities;

public class Carta implements Comparable<Carta>
{
	final static int CORAZONES = 0;
	final static int DIAMANTES = 1;
	final static int TREBOL = 2;
	final static int PICAS = 3;
	
	final static int AS = 1;
	final static int JACK = 11;
	final static int QUEEN = 12;
	final static int KING = 13;
	
	private int palo;
	private int valor;
	
	public Carta() {}
	
	public Carta(int palo, int valor) 
	{
		this.palo = palo;
		this.valor = valor;
	}

	public int getPalo() 
	{
		return palo;
	}

	public void setPalo(int palo) 
	{
		this.palo = palo;
	}

	public int getValor() 
	{
		return valor;
	}

	public void setValor(int valor) 
	{
		this.valor = valor;
	}
	
	private String equivalencia(int pal, int val)
	{
		String s1,s2;
		switch(pal)
		{
			case TREBOL: s1 = "Trebol"; break;
			case CORAZONES: s1 = "Corazones"; break;
			case DIAMANTES: s1 = "Diamantes"; break; 
			case PICAS: s1 = "Picas"; break;
			default: s1 = "??"; break;
		}
		
		switch(val) 
		{
			case 1: s2 = "As"; break;
			case 2: s2 = "2"; break;
			case 3: s2 = "3"; break;
			case 4: s2 = "4"; break;
			case 5: s2 = "5"; break;
			case 6: s2 = "6"; break;
			case 7: s2 = "7"; break;
			case 8: s2 = "8"; break;
			case 9: s2 = "9"; break;
			case 10: s2 = "10"; break;
			case 11: s2 = "J"; break;
			case 12: s2 = "Q"; break;
			case 13: s2 = "K"; break;
			default: s2 = "??"; break;
		}
		return s2+" de "+s1;
	}
	
	@Override
	public int compareTo(Carta o) 
	{
		if(this.palo<o.palo)
		{		
			return -1;
		}
		else if(this.palo == o.palo)
		{
			if(this.valor<o.valor)
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
		else
		{
			return 1;
		}
	}

	@Override
	public String toString() 
	{
		return "Carta [" + equivalencia(palo,valor) + "]";
	}

}
