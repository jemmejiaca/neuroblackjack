package entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RNA 
{
	private static float funcionActivacion = 0.0f;
	private static float error = 0.0f;
	private static float factorDeAprendizaje = 0.5f;
	private float pesos[];
	private static float entradas[][] = 
		{
			{7,2,-1},
			{8,3,-1},
			{9,4,-1},
			{10,5,-1},
			{11,6,-1},
			{12,7,-1},
			{12,5,-1},
			{13,3,-1},
			{13,10,-1},
			{14,2,-1},
			{14,7,-1},
			{15,3,-1},
			{15,8,-1},
			{16,4,-1},
			{16,9,-1},
			{17,5,-1},
			{17,11,-1}
		};
	private static float salidas[] = {1,1,1,1,1,1,-1,-1,1,-1,1,-1,1,-1,1,-1,1};
	
	public RNA() 
	{
		pesos = new float[3];
		//pesos[0] = random(-2.0f,2.0f);
		//pesos[1] = random(-2.0f,2.0f);
		//pesos[2] = random(-2.0f,2.0f);
		pesos[0] = 1.4f;
		pesos[1] = -1.4f;
		pesos[2] = -0.4f;
	}
	
	public void aprender() {
		int contador = 0;
		/*
		 * recorremos las entradas y se le van pasando a la funcionActivacion()
		 * esta funcion nos regresa la salida para dichas entradas. por ejemplo
		 * si elegimos la compuerta OR y se le manda la entradas: x1 = 1 , x2 =
		 * 0 la salida que nos deberia de regresa la funcionActivacion() es 1
		 * esta salida se le manda al metodo error este verifica si hay o no
		 * error
		 */
		for (int i = 0; i <= entradas[0].length; i++) 
		{
			System.out.println("ITERACION " + contador + ":");
			float funcionActivacion = funcionActivacion(entradas[i]);
			System.out.println("activacion: " + funcionActivacion);
			float error = error(salidas[i]);
			System.out.println("Error: " + error);
			if (error == 0f) {
				// Entra aqui si no hay error
				System.out.println("--------------------------------------");
				contador++;
			} else {
				// Si hay error, recalcula los pesos
				calculaPesos(entradas[i], salidas[i]);
				/*
				 * ponemos i=-1 para que empiece a sacar la funcion de
				 * activacion desde el inicio con los nuevos pesos
				 */
				i = -1;
				contador = 0;
			}
		}
		System.out.println("La red ya esta entrenada");
	}
	
	private void calculaPesos(float[] fs, float f) 
	{
		if (error != 0) 
		{
			for (int i = 0; i < fs.length; i++) {
				System.out.println(pesos[i] + " + (2 * .5) * " + f + " * " + fs[i]);
				this.pesos[i] = pesos[i] + (2.0f * .5f) * (f * fs[i]);
				String val = this.pesos[i] + "";
				BigDecimal big = new BigDecimal(val);
				big = big.setScale(2, RoundingMode.HALF_UP);
				funcionActivacion = big.floatValue();
				System.out.println("AHORA LOS PESOS CAMBIARON A :" + this.pesos[i]);
			}
		}
	}

	public float funcionActivacion(float[] entradas)
	{
		funcionActivacion = 0;
		for(int i = 0; i < entradas.length; i++)
		{       
            // se multiplica cada peso por cada entrada y se suma
            funcionActivacion += pesos[i] * entradas[i];
           
            //redondeamos a 2 decimales el valor de la funcion activacion
            String val = funcionActivacion+"";
            BigDecimal big = new BigDecimal(val);
            big = big.setScale(2, RoundingMode.HALF_UP);    
            funcionActivacion = big.floatValue();
            System.out.println("Multiplicacion");
            System.out.println("w"+i+" * "+"x "+i);
            System.out.println(pesos[i] +"*" +entradas[i]);
           
        }
        System.out.println("y = "+funcionActivacion);
        //se determina el valor de la salida
        //acá se puede usar función sigmoide
        if (funcionActivacion >= 0)
        {
        	funcionActivacion = 1;
        }
        else if(funcionActivacion < 0)
        {
        	funcionActivacion = -1;
        }
        return funcionActivacion;
        //return sigmoide(funcionActivacion);
	}
	
	public float sigmoide(float in)
	{
		return (float) (1/(1+Math.exp(-in)));
	}
	
	public float error(float salidaDeseada)
	{
		System.out.println("Salida deseada - salida");
		error = salidaDeseada - funcionActivacion;
		System.out.println(salidaDeseada+" - "+funcionActivacion);
        return error;
    }
	
	public float testing(float[] entradasPrueba) 
	{
		float result;
		funcionActivacion = 0.0f;
		System.out.println("----------PROBANDO EL PERCEPTRON ---------");
		for (int i = 0; i <= 2; i++) {
			funcionActivacion += pesos[i] * entradasPrueba[i];
			String val = funcionActivacion + "";
			BigDecimal big = new BigDecimal(val);
			big = big.setScale(2, RoundingMode.HALF_UP);
			funcionActivacion = big.floatValue();
			System.out.println("Multiplicacion");
			System.out.println("w" + i + " * " + "x " + i);
			System.out.println(pesos[i] + "*" + entradasPrueba[i]);
		}
		System.out.println("y = " + funcionActivacion);
		if (funcionActivacion >= 0)
		{
			funcionActivacion = 1;
		}
		else if (funcionActivacion < 0)
		{
			funcionActivacion = -1;
		}
		result = funcionActivacion;
		return result;
	}
	
	private float random(float min , float max)
	{
		return min + (float)(Math.random() * (max - min + 1));
	}

	public float[] getPesos() {
		return pesos;
	}

	public void setPesos(float[] pesos) {
		this.pesos = pesos;
	}

}
