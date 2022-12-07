package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;

public class AesVisual {

	private JFrame frame;
	private JTextField textKey;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AesVisual window = new AesVisual();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AesVisual() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Press Load File...");
			}
		});
		btnLoadFile.setBounds(22, 32, 89, 23);
		frame.getContentPane().add(btnLoadFile);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(128, 36, 46, 14);
		frame.getContentPane().add(lblStatus);
		
		JRadioButton rdbtnCifrar = new JRadioButton("Cifrar");
		rdbtnCifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Press Cifrar...");
			}
		});
		buttonGroup.add(rdbtnCifrar);
		rdbtnCifrar.setBounds(22, 76, 109, 23);
		frame.getContentPane().add(rdbtnCifrar);
		
		JRadioButton rdbtnDecifrar = new JRadioButton("Decifrar");
		rdbtnDecifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Press Decifrar...");
			}
		});
		buttonGroup.add(rdbtnDecifrar);
		rdbtnDecifrar.setBounds(22, 110, 109, 23);
		frame.getContentPane().add(rdbtnDecifrar);
		
		textKey = new JTextField();
		textKey.setBounds(22, 158, 152, 20);
		frame.getContentPane().add(textKey);
		textKey.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Llave: ");
		lblNewLabel.setBounds(22, 140, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(217, 32, 185, 146);
		frame.getContentPane().add(textArea);
		
		JButton btnExport2File = new JButton("Export");
		btnExport2File.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Press Export...");
			}
		});
		btnExport2File.setBounds(270, 189, 89, 23);
		frame.getContentPane().add(btnExport2File);
		
		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Press Go...");
			}
		});
		btnGo.setBounds(58, 189, 89, 23);
		frame.getContentPane().add(btnGo);
	}
}
