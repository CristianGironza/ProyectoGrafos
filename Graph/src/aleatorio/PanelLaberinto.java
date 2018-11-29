package aleatorio;

import javax.swing.*;
import java.awt.*;

public class PanelLaberinto extends JPanel{

	int[][] lab;
	JLabel[][] jl;
	JLabel aux;
	
	public PanelLaberinto(int[][] lab)
	{
		setLayout(new GridLayout(lab.length,lab[0].length));
		this.lab=lab;
		jl = new JLabel[lab.length][lab[0].length];
		llenar();
	}
	
	public void resolver(int[][] r)
	{
		lab=r;
		for (int i = 0; i < jl.length; i++) {
			for (int j = 0; j < jl[0].length; j++) {
				jl[i][j].setText(String.valueOf(lab[i][j]));
				if (jl[i][j].getText().equals("4")) {
					jl[i][j].setForeground(Color.orange);
					jl[i][j].setBackground(Color.orange);
				}
				
				if(jl[i][j].getText().equals("2"))
				{
					jl[i][j].setForeground(Color.GREEN);
					jl[i][j].setBackground(Color.GREEN);
				}
				
				
				
			}
			
		}
		//llenar();
		revalidate();
		repaint();
	}
	
	public void llenar()
	{
		for (int i = 0; i < jl.length; i++) {
			for (int j = 0; j < jl[0].length; j++) {
				//System.out.println(String.valueOf(lab[i][j]));
				aux=new JLabel(String.valueOf(lab[i][j]));
				aux.setOpaque(true);
				if(aux.getText().equals("3")) {
					aux.setForeground(Color.WHITE);
					aux.setBackground(Color.WHITE);
				}
				
				if(aux.getText().equals("1"))
				{
					aux.setForeground(Color.BLUE);
					aux.setBackground(Color.BLUE);
				}
				jl[i][j]=aux;
				add(jl[i][j]);
				
			}
		}
	
	}
}
