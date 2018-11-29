package aleatorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBotones extends JPanel implements ActionListener{
	
	public static final String R="resolver";
	public static final String REPETIR="resolver";
	
	JButton resolver;
	JButton repetir ; 
	
	Principal tv;
	
	public PanelBotones(Principal tv)
	{
		this.tv = tv;
		resolver = new JButton("resolver");
		resolver.setActionCommand(R);
		resolver.addActionListener(this);

		repetir = new JButton("repetir");
		repetir.setActionCommand(REPETIR);
		repetir.addActionListener(this);
		
		add(resolver);
//		add(repetir); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ev = e.getActionCommand();
		if(ev.equals(R))
		{
			tv.resolver();
//		}
//		if (ev.equals(REPETIR)) {
//			
//			tv = new Principal(); 
//		
		}
		
		
	}

}
