package Application;

import javax.swing.SwingUtilities;

import view.AppFrame;

public class AppDriver {

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new AppFrame();
			}
		});

	}

}
