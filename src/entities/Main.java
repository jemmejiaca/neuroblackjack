package entities;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;

import view.Window;

public class Main 
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
        		{
        			JFrame.setDefaultLookAndFeelDecorated(true);
        			UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
        		}
        		catch(Exception e)
        		{
        			e.printStackTrace();
        		}
        		
				
				try 
				{
					Window frame = new Window();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
