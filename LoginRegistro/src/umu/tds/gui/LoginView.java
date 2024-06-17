package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import umu.tds.controlador.Controlador;
import umu.tds.git.LoginGitHub;

import javax.swing.border.EmptyBorder;

public class LoginView {

	private JFrame frmLogin;
	private JTextField textUsuario;
	private JPasswordField textPassword;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	public void mostrarVentana() {
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}
	
	/********************************************************************** 
	 * Procurar organizar la creación de una ventana en varios métodos
	 * con el fin de facilitar su comprensión. Esta clase muestra un ejemplo
	 **********************************************************************/
	
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login AppMusic");
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout());

		crearPanelTitulo();
		crearPanelLogin();

		frmLogin.setResizable(false);
		frmLogin.pack();
	}

	private void crearPanelTitulo() {
		JPanel panel_Norte = new JPanel();
		frmLogin.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

		JLabel lblTitulo = new JLabel("AppMusic");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setForeground(Color.DARK_GRAY);
		panel_Norte.add(lblTitulo);
	}

	private void crearPanelLogin() {
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(10, 10, 10, 10));
		frmLogin.getContentPane().add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(new BorderLayout(0, 0));

		panelLogin.add(crearPanelUsuarioPassw(), BorderLayout.NORTH);
		panelLogin.add(crearPanelBotones(), BorderLayout.SOUTH);
	}

	private JPanel crearPanelUsuarioPassw() {
		JPanel panelCampos = new JPanel();
		panelCampos.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));

		// Panel Campo Login
		JPanel panelCampoUsuario = new JPanel();
		panelCampos.add(panelCampoUsuario);
		panelCampoUsuario.setLayout(new BorderLayout(0, 0));

		JLabel lblUsuario = new JLabel("Usuario: ");
		panelCampoUsuario.add(lblUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textUsuario = new JTextField();
		panelCampoUsuario.add(textUsuario, BorderLayout.EAST);
		textUsuario.setColumns(15);

		// Panel Campo Password
		JPanel panelCampoPassword = new JPanel();
		panelCampos.add(panelCampoPassword);
		panelCampoPassword.setLayout(new BorderLayout(0, 0));

		JLabel lblPassword = new JLabel("Contrase\u00F1a: ");
		panelCampoPassword.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textPassword = new JPasswordField();
		panelCampoPassword.add(textPassword, BorderLayout.EAST);
		textPassword.setColumns(15);
		
		return panelCampos;
	}

	private JPanel crearPanelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new EmptyBorder(5, 0, 5, 0));
		panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel panelBotonesLoginRegistro = new JPanel();
		panelBotones.add(panelBotonesLoginRegistro, BorderLayout.WEST);
		
		JButton btnLogin = new JButton("Login");
		panelBotonesLoginRegistro.add(btnLogin);

		JButton btnRegistro = new JButton("Registro");
		panelBotonesLoginRegistro.add(btnRegistro);
		
		JCheckBox chckGit = new JCheckBox("con GitHub");
		panelBotonesLoginRegistro.add(chckGit);

		JPanel panelBotonSalir = new JPanel();
		panelBotones.add(panelBotonSalir, BorderLayout.EAST);

		JButton btnSalir = new JButton("Salir");
		panelBotonSalir.add(btnSalir);
		
		
		
		addManejadorBotonLogin(btnLogin,chckGit);
		addManejadorBotonRegistro(btnRegistro);
		addManejadorBotonSalir(btnSalir);
		
		
		return panelBotones;
	}

	private void addManejadorBotonSalir(JButton btnSalir) {
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				System.exit(0);
			}
		});
	}

	private void addManejadorBotonRegistro(JButton btnRegistro) {
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroView registro = new RegistroView (frmLogin);
				registro.setLocationRelativeTo(frmLogin);                              	
				registro.setVisible(true);
				frmLogin.dispose();
			}
		});
	}

	private void addManejadorBotonLogin(JButton btnLogin,final JCheckBox chckGit) {
		
		chckGit.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				textPassword.setText("");
				textPassword.setEnabled(!chckGit.isSelected());
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckGit.isSelected()) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.addChoosableFileFilter(new FileFilter() {
						public String getDescription() {
							return "GitHub Properties File (*.properties)";
						}

						public boolean accept(File f) {
							if (f.isDirectory()) {
								return true;
							} else {
								return f.getName().toLowerCase().endsWith(".properties");
							}
						}
					});
					fileChooser.setAcceptAllFileFilterUsed(false);
					File workingDirectory = new File(System.getProperty("user.dir"));
					fileChooser.setCurrentDirectory(workingDirectory);
					int result = fileChooser.showOpenDialog(frmLogin);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						System.out.println("Selected file: " + selectedFile.getAbsolutePath());
						if (LoginGitHub.INSTANCE.verificar(textUsuario.getText(), selectedFile.getAbsolutePath()) && Controlador.INSTANCE.loginUsuarioGit(textUsuario.getText())) {
							JOptionPane.showMessageDialog(frmLogin, "Login Correcto", "Login",
									JOptionPane.INFORMATION_MESSAGE);
							VentanaPrincipal principal = new VentanaPrincipal();
							principal.mostrarVentana();
							frmLogin.dispose();		
						} else {
							JOptionPane.showMessageDialog(frmLogin, "Login Fallido", "Login", JOptionPane.WARNING_MESSAGE);
						}
					}
				} else {
					boolean login = Controlador.INSTANCE.loginUsuario(
							textUsuario.getText(),
							new String(textPassword.getPassword()));

					if (login) {
						VentanaPrincipal principal = new VentanaPrincipal();
						principal.mostrarVentana();
						frmLogin.dispose();
					} else
						JOptionPane.showMessageDialog(frmLogin, "Nombre de usuario o contraseña no valido",
								"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
