package visual;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import algoritmo.AES;
import utilitarios.FileUtilities;

import java.awt.TextArea;
import java.awt.Button;
import java.awt.Color;

public class AesVisual {

	private JFrame frame;
	private JTextField textKey;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private FileUtilities tools = new FileUtilities();
	private AES aes = new AES();
	private String contenido = "";
	private boolean esCifrado = false;
	private boolean esDescifrado = false;

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

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(128, 36, 46, 14);
		frame.getContentPane().add(lblStatus);

		textKey = new JTextField();
		textKey.setBounds(22, 158, 152, 20);
		frame.getContentPane().add(textKey);
		textKey.setColumns(10);

		JLabel lblNewLabel = new JLabel("Llave: ");
		lblNewLabel.setBounds(22, 140, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(191, 32, 221, 146);
		frame.getContentPane().add(textArea);

		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Press Load File...");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
				final JFileChooser fc = new JFileChooser();
				fc.setFileFilter(filter);
				int seleccion = fc.showOpenDialog(btnLoadFile);
				//System.out.println("Press Load File...seleccion: "+seleccion);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					// Aquí debemos abrir y leer el fichero.
					//System.out.println("Press Load File...path: "+fichero.getAbsolutePath());
					contenido = tools.muestraContenido(fichero.getAbsolutePath());
					lblStatus.setText(" OK");
					lblStatus.setOpaque(true);
					lblStatus.setForeground(Color.WHITE);
					lblStatus.setBackground(Color.GREEN);
				}
			}
		});
		btnLoadFile.setBounds(22, 32, 89, 23);
		frame.getContentPane().add(btnLoadFile);

		JRadioButton rdbtnCifrar = new JRadioButton("Cifrar");
		rdbtnCifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Press Cifrar...");
				esCifrado = true;
				//System.out.println("Press Cifrar...esCifrado:" + esCifrado);
				esDescifrado = false;
				//System.out.println("Press Cifrar...esDescifrado:" + esDescifrado);

			}
		});
		buttonGroup.add(rdbtnCifrar);
		rdbtnCifrar.setBounds(22, 76, 109, 23);
		frame.getContentPane().add(rdbtnCifrar);

		JRadioButton rdbtnDecifrar = new JRadioButton("Decifrar");
		rdbtnDecifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Press Decifrar...");
				esDescifrado = true;
				//System.out.println("Press Decifrar...esDescifrado:" + esDescifrado);
				esCifrado = false;
				//System.out.println("Press Decifrar...esCifrado:" + esCifrado);
			}
		});
		buttonGroup.add(rdbtnDecifrar);
		rdbtnDecifrar.setBounds(22, 110, 109, 23);
		frame.getContentPane().add(rdbtnDecifrar);

		JButton btnExport2File = new JButton("Export");
		btnExport2File.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Press Export...");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
				final JFileChooser fc = new JFileChooser();
				ArrayList<String> pantalla = new ArrayList<String>();
				fc.setFileFilter(filter);
				int seleccion = fc.showSaveDialog(btnLoadFile);
				boolean respuesta = false;
				//System.out.println("Press Load File...seleccion: "+seleccion);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					// Aquí debemos abrir y leer el fichero.
					//System.out.println("Press Export...path: "+fichero.getAbsolutePath()+".txt");
					pantalla.add(textArea.getText());
					respuesta = tools.crearArchivo(fichero.getAbsolutePath()+".txt", pantalla);
				}
			}
		});
		btnExport2File.setBounds(191, 189, 89, 23);
		frame.getContentPane().add(btnExport2File);

		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Press Go...");
				String Key = textKey.getText();
				//System.out.println("Press Go...Key:" + Key);
				//System.out.println("Press Go...esCifrado:" + esCifrado);
				//System.out.println("Press Go...esDescifrado:" + esDescifrado);
				if (contenido.length() == 0) {
					JOptionPane.showMessageDialog(null, "No ha seleccionado un archivo", "Archivo vacío",
							JOptionPane.WARNING_MESSAGE);
				} else if (!esCifrado & !esDescifrado) {
					JOptionPane.showMessageDialog(null, "No hay seleccionado una opcion:\n Cifrar o Descifrar",
							"Opcion vacía", JOptionPane.WARNING_MESSAGE);
				} else if (Key.length() == 0) {
					JOptionPane.showMessageDialog(null, "La llave está vacia", "Llave vacía",
							JOptionPane.WARNING_MESSAGE);
				} else {
					//System.out.println("Press Go...habilitar Export: true");
					btnExport2File.setEnabled(true);
					aes.KEY(Key);
					if (esCifrado)
						textArea.setText(aes.cifrado(contenido));

					if (esDescifrado)
						textArea.setText(aes.decifrado(contenido));
				}

			}
		});
		btnGo.setBounds(58, 189, 89, 23);
		frame.getContentPane().add(btnGo);

		JButton btnClean = new JButton("Clear");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Press Clean...");
				contenido = "";
				lblStatus.setOpaque(false);
				lblStatus.setForeground(Color.BLACK);
				lblStatus.setText("Status");
				esCifrado = false;
				rdbtnCifrar.setSelected(false);
				esDescifrado = false;
				rdbtnDecifrar.setSelected(false);
				textArea.setText("");
				textKey.setText("");
				btnExport2File.setEnabled(false);
			}
		});
		btnClean.setBounds(323, 189, 89, 23);
		frame.getContentPane().add(btnClean);
	}
}
