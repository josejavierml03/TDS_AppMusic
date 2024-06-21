package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import umu.tds.controlador.Controlador;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

public class RegistroView extends JDialog {

	//private JFrame frmRegistroView;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblFechaNacimiento;
	private JLabel lblEmail;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblPasswordChk;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordChk;
	private JButton btnRegistrar;
	private JButton btnCancelar;

	private JLabel lblNombreError;
	private JLabel lblApellidosError;
	private JLabel lblFechaNacimientoError;
	private JLabel lblEmailError;
	private JLabel lblUsuarioError;
	private JLabel lblPasswordError;
	private JPanel panelCampoNombre;
	private JPanel panel;
	private JPanel panelCampoApellidos;
	private JPanel panelCamposEmail;
	private JPanel panelCamposUsuario;
	private JPanel panelCamposFechaNacimiento;
	private JDateChooser dateChooser;

	public RegistroView(JFrame owner){
		super(owner, "Registro Usuario", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.crearPanelRegistro();
	}

	private void crearPanelRegistro() {
		this.getContentPane().setLayout(new BorderLayout());

		JPanel datosPersonales = new JPanel();
		this.getContentPane().add(datosPersonales);
		datosPersonales.setBorder(new TitledBorder(null, "Datos de Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		datosPersonales.setLayout(new BoxLayout(datosPersonales, BoxLayout.Y_AXIS));

		datosPersonales.add(creaLineaNombre());
		datosPersonales.add(crearLineaApellidos());
		datosPersonales.add(crearLineaEmail());
		datosPersonales.add(crearLineaUsuario());
		datosPersonales.add(crearLineaPassword());
		datosPersonales.add(crearLineaFechaNacimiento());
		
		this.crearPanelBotones();

		this.ocultarErrores();

		this.revalidate();
		this.pack();
	}

	private JPanel creaLineaNombre() {
		JPanel lineaNombre = new JPanel();
		lineaNombre.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaNombre.setLayout(new BorderLayout(0, 0));
		
		panelCampoNombre = new JPanel();
		lineaNombre.add(panelCampoNombre, BorderLayout.CENTER);
		
		lblNombre = new JLabel("Nombre: ", JLabel.RIGHT);
		panelCampoNombre.add(lblNombre);
		fixedSize(lblNombre, 75, 20);
		txtNombre = new JTextField();
		panelCampoNombre.add(txtNombre);
		fixedSize(txtNombre, 270, 20);
		
		lblNombreError = new JLabel("El nombre es obligatorio", SwingConstants.CENTER);
		lineaNombre.add(lblNombreError, BorderLayout.SOUTH);
		fixedSize(lblNombreError, 224, 15);
		lblNombreError.setForeground(Color.RED);
		
		return lineaNombre;
	}

	private JPanel crearLineaApellidos() {
		JPanel lineaApellidos = new JPanel();
		lineaApellidos.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaApellidos.setLayout(new BorderLayout(0, 0));
		
		panelCampoApellidos = new JPanel();
		lineaApellidos.add(panelCampoApellidos);
		
		lblApellidos = new JLabel("Apellidos: ", JLabel.RIGHT);
		panelCampoApellidos.add(lblApellidos);
		fixedSize(lblApellidos, 75, 20);
		txtApellidos = new JTextField();
		panelCampoApellidos.add(txtApellidos);
		fixedSize(txtApellidos, 270, 20);

		
		lblApellidosError = new JLabel("Los apellidos son obligatorios", SwingConstants.CENTER);
		lineaApellidos.add(lblApellidosError, BorderLayout.SOUTH);
		fixedSize(lblApellidosError, 255, 15);
		lblApellidosError.setForeground(Color.RED);
		
		return lineaApellidos;
	}

	private JPanel crearLineaEmail() {
		JPanel lineaEmail = new JPanel();
		lineaEmail.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaEmail.setLayout(new BorderLayout(0, 0));
		
		panelCamposEmail = new JPanel();
		lineaEmail.add(panelCamposEmail, BorderLayout.CENTER);
		
		lblEmail = new JLabel("Email: ", JLabel.RIGHT);
		panelCamposEmail.add(lblEmail);
		fixedSize(lblEmail, 75, 20);
		txtEmail = new JTextField();
		panelCamposEmail.add(txtEmail);
		fixedSize(txtEmail, 270, 20);
		lblEmailError = new JLabel("El Email es obligatorio", SwingConstants.CENTER);
		fixedSize(lblEmailError, 150, 15);
		lblEmailError.setForeground(Color.RED);
		lineaEmail.add(lblEmailError, BorderLayout.SOUTH);
		
		return lineaEmail;
	}

	private JPanel crearLineaUsuario() {
		JPanel lineaUsuario = new JPanel();
		lineaUsuario.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaUsuario.setLayout(new BorderLayout(0, 0));
		
		panelCamposUsuario = new JPanel();
		lineaUsuario.add(panelCamposUsuario, BorderLayout.CENTER);
		
		lblUsuario = new JLabel("Usuario: ", JLabel.RIGHT);
		panelCamposUsuario.add(lblUsuario);
		fixedSize(lblUsuario, 75, 20);
		txtUsuario = new JTextField();
		panelCamposUsuario.add(txtUsuario);
		fixedSize(txtUsuario, 270, 20);
		lblUsuarioError = new JLabel("El usuario ya existe", SwingConstants.CENTER);
		fixedSize(lblUsuarioError, 150, 15);
		lblUsuarioError.setForeground(Color.RED);
		lineaUsuario.add(lblUsuarioError, BorderLayout.SOUTH);
		
		return lineaUsuario;
	}

	private JPanel crearLineaPassword() {
		JPanel lineaPassword = new JPanel();
		lineaPassword.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lineaPassword.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		lineaPassword.add(panel, BorderLayout.CENTER);
		
		lblPassword = new JLabel("Password: ", JLabel.RIGHT);
		panel.add(lblPassword);
		fixedSize(lblPassword, 75, 20);
		txtPassword = new JPasswordField();
		panel.add(txtPassword);
		fixedSize(txtPassword, 100, 20);
		lblPasswordChk = new JLabel("Otra vez:", JLabel.RIGHT);
		panel.add(lblPasswordChk);
		fixedSize(lblPasswordChk, 60, 20);
		txtPasswordChk = new JPasswordField();
		panel.add(txtPasswordChk);
		fixedSize(txtPasswordChk, 100, 20);

		lblPasswordError = new JLabel("Error al introducir las contrase�as", JLabel.CENTER);
		lineaPassword.add(lblPasswordError, BorderLayout.SOUTH);
		lblPasswordError.setForeground(Color.RED);
		
		return lineaPassword;
	}

	private JPanel crearLineaFechaNacimiento() {
	    JPanel lineaFechaNacimiento = new JPanel();
	    lineaFechaNacimiento.setAlignmentX(JLabel.LEFT_ALIGNMENT);
	    lineaFechaNacimiento.setLayout(new BorderLayout(0, 0));
	    
	    panelCamposFechaNacimiento = new JPanel();
	    lineaFechaNacimiento.add(panelCamposFechaNacimiento, BorderLayout.CENTER);
	    
	    lblFechaNacimiento = new JLabel("Fecha de Nacimiento: ", JLabel.RIGHT);
	    panelCamposFechaNacimiento.add(lblFechaNacimiento);
	    fixedSize(lblFechaNacimiento, 130, 20);
	    
	    dateChooser = new JDateChooser();
	    fixedSize(dateChooser, 260, 20); 
	    panelCamposFechaNacimiento.add(dateChooser);
	    
	    lblFechaNacimientoError = new JLabel("Introduce la fecha de nacimiento", SwingConstants.CENTER);
	    fixedSize(lblFechaNacimientoError, 150, 15);
	    lblFechaNacimientoError.setForeground(Color.RED);
	    lineaFechaNacimiento.add(lblFechaNacimientoError, BorderLayout.SOUTH);
	    
	    return lineaFechaNacimiento;
	}



	private void crearPanelBotones() {
		JPanel lineaBotones = new JPanel(); 
		this.getContentPane().add(lineaBotones, BorderLayout.SOUTH);
		lineaBotones.setBorder(new EmptyBorder(5, 0, 0, 0));
		lineaBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnRegistrar = new JButton("Registrar");
		lineaBotones.add(btnRegistrar);
		
		btnCancelar = new JButton("Cancelar");
		lineaBotones.add(btnCancelar);

		this.crearManejadorBotonRegistrar();
		this.crearManejadorBotonCancelar();
	}

	private void crearManejadorBotonRegistrar() {
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean OK = false;
				OK = checkFields();
				if (OK) {
					boolean registrado = false;
					registrado = Controlador.INSTANCE.registrarUsuario(
							txtNombre.getText(),
							txtApellidos.getText(), 
							txtEmail.getText(), 
							txtUsuario.getText(),
							new String(txtPassword.getPassword()),
							dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault())
                            .toLocalDate()
					);
				
					if (registrado) {
						JOptionPane.showMessageDialog(RegistroView.this, "Usuario registrado correctamente.", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
						
						LoginView loginView = new LoginView();
						loginView.mostrarVentana();
						RegistroView.this.dispose();
					} else {
						JOptionPane.showMessageDialog(RegistroView.this, "No se ha podido llevar a cabo el registro.\n",
								"Registro", JOptionPane.ERROR_MESSAGE);
						RegistroView.this.setTitle("Login Gestor Eventos");
					}
				}
			}
		});
	}

	private void crearManejadorBotonCancelar() {
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView loginView = new LoginView();
				loginView.mostrarVentana();
				RegistroView.this.dispose();
			}
		});
	}

	/**
	 * Comprueba que los campos de registro están bien
	 */
	private boolean checkFields() {
		boolean salida = true;
		/* borrar todos los errores en pantalla */
		ocultarErrores();
		if (txtNombre.getText().trim().isEmpty()) {
			lblNombreError.setVisible(true);
			lblNombre.setForeground(Color.RED);
			txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtApellidos.getText().trim().isEmpty()) {
			lblApellidosError.setVisible(true);
			lblApellidos.setForeground(Color.RED);
			txtApellidos.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtEmail.getText().trim().isEmpty()) {
			lblEmailError.setVisible(true);
			lblEmail.setForeground(Color.RED);
			txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtUsuario.getText().trim().isEmpty()) {
			lblUsuarioError.setText("El usuario es obligatorio");
			lblUsuarioError.setVisible(true);
			lblUsuario.setForeground(Color.RED);
			txtUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		String password = new String(txtPassword.getPassword());
		String password2 = new String(txtPasswordChk.getPassword());
		if (password.isEmpty()) {
			lblPasswordError.setText("El password no puede estar vacio");
			lblPasswordError.setVisible(true);
			lblPassword.setForeground(Color.RED);
			txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (password2.isEmpty()) {
			lblPasswordError.setText("El password no puede estar vacio");
			lblPasswordError.setVisible(true);
			lblPasswordChk.setForeground(Color.RED);
			txtPasswordChk.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (!password.equals(password2)) {
			lblPasswordError.setText("Los dos passwords no coinciden");
			lblPasswordError.setVisible(true);
			lblPassword.setForeground(Color.RED);
			lblPasswordChk.setForeground(Color.RED);
			txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			txtPasswordChk.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		/* Comprobar que no exista otro usuario con igual login */
		if (!lblUsuarioError.getText().isEmpty() && Controlador.INSTANCE.esUsuarioRegistrado(txtUsuario.getText())) {
			lblUsuarioError.setText("Ya existe ese usuario");
			lblUsuarioError.setVisible(true);
			lblUsuario.setForeground(Color.RED);
			txtUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (dateChooser.getDateFormatString().isEmpty()) {
			lblFechaNacimientoError.setVisible(true);
			lblFechaNacimiento.setForeground(Color.RED);
			dateChooser.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}

		this.revalidate();
		this.pack();
		
		return salida;
	}

	/**
	 * Oculta todos los errores que pueda haber en la pantalla
	 */
	private void ocultarErrores() {
		lblNombreError.setVisible(false);
		lblApellidosError.setVisible(false);
		lblFechaNacimientoError.setVisible(false);
		lblEmailError.setVisible(false);
		lblUsuarioError.setVisible(false);
		lblPasswordError.setVisible(false);
		lblFechaNacimientoError.setVisible(false);
		
		Border border = new JTextField().getBorder();
		txtNombre.setBorder(border);
		txtApellidos.setBorder(border);
		txtEmail.setBorder(border);
		txtUsuario.setBorder(border);
		txtPassword.setBorder(border);
		txtPasswordChk.setBorder(border);
		txtPassword.setBorder(border);
		txtPasswordChk.setBorder(border);
		txtUsuario.setBorder(border);
		
		lblNombre.setForeground(Color.BLACK);
		lblApellidos.setForeground(Color.BLACK);
		lblEmail.setForeground(Color.BLACK);
		lblUsuario.setForeground(Color.BLACK);
		lblPassword.setForeground(Color.BLACK);
		lblPasswordChk.setForeground(Color.BLACK);
		lblFechaNacimiento.setForeground(Color.BLACK);
	}

	/**
	 * Fija el tamaño de un componente
	 */
	private void fixedSize(JComponent o, int x, int y) {
		Dimension d = new Dimension(x, y);
		o.setMinimumSize(d);
		o.setMaximumSize(d);
		o.setPreferredSize(d);
	}
}