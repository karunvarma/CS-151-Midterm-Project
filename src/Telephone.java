import java.awt.* ;
import java.awt.event.* ;
import javax.swing.* ;

/**
 * Presents a phone GUI for the voicemail system.
 */
public class Telephone
{
	private ControlHub hub ;
	private final JTextArea speakerField ;
	
	/**
	 * Constructs a telephone with a speaker, keypad, and microphone.
	 */
	public Telephone()
	{
		JPanel speakerPanel = new JPanel() ;
		speakerPanel.setLayout(new BorderLayout()) ;
		speakerPanel.add(new JLabel("Speaker:"), BorderLayout.NORTH) ;
		speakerField = new JTextArea(10, 25) ;
		speakerPanel.add(speakerField, BorderLayout.CENTER) ;
		
		String keyLabels = "123456789*0#" ;
		JPanel keyPanel = new JPanel() ;
		keyPanel.setLayout(new GridLayout(4, 3)) ;
		for (int i = 0; i < keyLabels.length(); i++)
		{
			final String label = keyLabels.substring(i, i + 1) ;
			JButton keyButton = new JButton(label) ;
			keyPanel.add(keyButton) ;
			keyButton.addActionListener(new 
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						hub.dial(label) ;
					}
				}) ;
		}
		
		final JTextArea microphoneField = new JTextArea(10,25) ;
		
		JButton speechButton = new JButton("Send speech") ;
		speechButton.addActionListener(new 
			ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					hub.record(microphoneField.getText()) ;
					microphoneField.setText("") ;
				}
			}) ;
		
		JButton hangupButton = new JButton("Hangup") ;
		hangupButton.addActionListener(new 
			ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					hub.hangUp() ;
				}
			}) ;
		
		JPanel buttonPanel = new JPanel() ;
		buttonPanel.add(speechButton) ;
		buttonPanel.add(hangupButton) ;
		
		JPanel microphonePanel = new JPanel() ;
		microphonePanel.setLayout(new BorderLayout()) ;
		microphonePanel.add(new JLabel("Microphone:"), BorderLayout.NORTH) ;
		microphonePanel.add(microphoneField, BorderLayout.CENTER) ;
		microphonePanel.add(buttonPanel, BorderLayout.SOUTH) ;
		
		JFrame frame = new JFrame() ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		frame.add(speakerPanel, BorderLayout.NORTH) ;
		frame.add(keyPanel, BorderLayout.CENTER) ;
		frame.add(microphonePanel, BorderLayout.SOUTH) ;
		
		frame.pack() ;
		frame.setVisible(true) ;
	}
	
	/**
	 * Speak to the user.
	 * @param output the voice output
	 */
	public void speak(String output)
	{
		speakerField.setText(output) ;
	}
	
	/**
	 * Assigns ControlHub input to ControlHub field
	 * @param h the ControlHub object
	 */
	public void startSystem(ControlHub h)
	{
		hub = h ;
	}
}
