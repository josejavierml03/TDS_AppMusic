package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import java.awt.CardLayout;
import javax.swing.Box;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import umu.tds.controlador.Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class VentanaPrincipal {

private JFrame frmVentanaPrincipal;
private JTextField textField_4;
private JTextField textField_5;
private JTable table;
private JTextField textField;
private JTextField textField_1;
private JTextField txtTitulo;
private JTable table_1;
private JTable table_2;

public static int anchoDeseadoBoton = 30;
public static int altoDeseadoBoton = 40;
public static String rutaPlay = "/umu/tds/imagenes/play.png";
public static String rutaStop = "/umu/tds/imagenes/stop.png";
public static String rutaSiguente = "/umu/tds/imagenes/siguente.png";
	
	public VentanaPrincipal() {
		initialize();
	}


	public void mostrarVentana() {
		frmVentanaPrincipal.setLocationRelativeTo(null);
		frmVentanaPrincipal.setVisible(true);
	}
	
	public JFrame getFrame() {
		return frmVentanaPrincipal;
	}
	
    private void configurarBoton(JButton boton, String rutaImagen) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(anchoDeseadoBoton, altoDeseadoBoton));
        boton.setIcon(new ImageIcon(VentanaPrincipal.class.getResource(rutaImagen)));
        
        ButtonModel model = boton.getModel();
        model.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (model.isPressed() && model.isArmed()) {
                    boton.setBorderPainted(true);
                } else {
                    boton.setBorderPainted(false);
                }
            }
        });
    }

	public void initialize() {
		
		frmVentanaPrincipal = new JFrame();
		frmVentanaPrincipal.setMinimumSize(new Dimension(600, 500));
		frmVentanaPrincipal.setTitle("AppMusic - Ventana Principal");
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmVentanaPrincipal.pack();
		frmVentanaPrincipal.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmVentanaPrincipal.getContentPane().add(panel, BorderLayout.WEST);
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		verticalBox.add(btnNewButton);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		JButton btnNewButton_1 = new JButton("Gestion PlayLists");
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(btnNewButton_1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);
		
		JButton btnNewButton_2 = new JButton("Recientes");
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(btnNewButton_2);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);
		
		JButton btnNewButton_3 = new JButton("Mis PlayLists");
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(btnNewButton_3);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);
		
		JButton btnNewButton_16 = new JButton("Mas Reproducidas");
		btnNewButton_16.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		verticalBox.add(btnNewButton_16);
		
		Component verticalStrut_11 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_11);
		
		JButton btnNewButton_17 = new JButton("PDF");
		btnNewButton_17.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(btnNewButton_17);
		if (Controlador.INSTANCE.comprobarPremium()) {
			btnNewButton_16.setEnabled(true);
			btnNewButton_17.setEnabled(true);
		}
		else {
			btnNewButton_16.setEnabled(false);
			btnNewButton_17.setEnabled(false);
		}
		
		Component verticalStrut_12 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_12);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox.add(verticalBox_2);
		
		final JPanel panel_7 = new JPanel();
		verticalBox_2.add(panel_7);
		panel_7.setLayout(new CardLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8, "panel_8");
		
		final JPanel panel_9 = new JPanel();
		panel_7.add(panel_9, "panel_9");
		
		Box verticalBox_5 = Box.createVerticalBox();
		verticalBox_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.add(verticalBox_5,BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Listas");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_5.add(lblNewLabel_1);
		//Ejemplo 
		String[] elementos = {"Lista 1", "Lista 2", "Lista 3"};

        // Crear un modelo de lista
        DefaultListModel<String> modeloLista = new DefaultListModel<String>();
        for (String elemento : elementos) {
            modeloLista.addElement(elemento);
        }
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		verticalBox_5.add(verticalStrut_5);
		JList<String> lista = new JList<String>(modeloLista);
		lista.setBackground(UIManager.getColor("Button.background"));
		lista.setSelectionBackground(new Color(192, 192, 192));
		lista.setOpaque(false);
		
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		verticalBox_5.add(lista);
		
		Component rigidArea = Box.createRigidArea(new Dimension(130, 130));
		verticalBox_5.add(rigidArea);
		
		JPanel panel_1 = new JPanel();
		frmVentanaPrincipal.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Bienvenido, usuario.");
		panel_2.add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("Logout");
		
		panel_2.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Premium");
		
		panel_2.add(btnNewButton_5);
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new CardLayout(0, 0));
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, "panel_6");
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, "panel_4");
		
		Box verticalBox_1 = Box.createVerticalBox();
		panel_4.add(verticalBox_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_2);
		
		Box verticalBox_1_1_1 = Box.createVerticalBox();
		horizontalBox_2.add(verticalBox_1_1_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		verticalBox_1_1_1.add(textField);
		
		Component verticalStrut_3_1_1 = Box.createVerticalStrut(20);
		verticalBox_1_1_1.add(verticalStrut_3_1_1);
		
		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Favoritas");
		rdbtnNewRadioButton_1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_1_1_1.add(rdbtnNewRadioButton_1_1);
		
		Box verticalBox_2_1_1 = Box.createVerticalBox();
		horizontalBox_2.add(verticalBox_2_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		verticalBox_2_1_1.add(textField_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		verticalBox_2_1_1.add(comboBox_1_1);
		
		JButton btnNewButton_6_1_1 = new JButton("Buscar");
		btnNewButton_6_1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_2_1_1.add(btnNewButton_6_1_1);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, "panel_5");
		
		Box verticalBox_3 = Box.createVerticalBox();
		panel_5.add(verticalBox_3);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox_3.add(horizontalBox);
		
		Box verticalBox_1_1 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		verticalBox_1_1.add(textField_4);
		
		Component verticalStrut_3_1 = Box.createVerticalStrut(20);
		verticalBox_1_1.add(verticalStrut_3_1);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Favoritas");
		rdbtnNewRadioButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_1_1.add(rdbtnNewRadioButton_1);
		
		Box verticalBox_2_1 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_2_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		verticalBox_2_1.add(textField_5);
		
		JComboBox comboBox_1 = new JComboBox();
		verticalBox_2_1.add(comboBox_1);
		
		JButton btnNewButton_6_1 = new JButton("Buscar");
		btnNewButton_6_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_2_1.add(btnNewButton_6_1);
		
		Component verticalStrut_4 = Box.createVerticalStrut(40);
		verticalStrut_4.setPreferredSize(new Dimension(0, 25));
		verticalStrut_4.setMinimumSize(new Dimension(0, 25));
		verticalBox_3.add(verticalStrut_4);
		
		Box verticalBox_4 = Box.createVerticalBox();
		verticalBox_3.add(verticalBox_4);
		String[] columnNames = {"Título", "Intérprete", "Estilo", ""}; 
        Object[][] data = {
            {"Canción 1", "Artista 1", "Estilo 1", false},
            {"Canción 2", "Artista 2", "Estilo 2", true},
            {"Canción 3", "Artista 3", "Estilo 3", false},
           
        };
  
        table = new JTable(data, columnNames);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        
        table.getColumnModel().getColumn(3).setCellRenderer(new RadioButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new RadioButtonEditor(new JCheckBox()));
		verticalBox_4.add(table);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox_4.add(horizontalBox_1);
		
		JButton btnNewButton_9 = new JButton();
		configurarBoton(btnNewButton_9, rutaStop);
		horizontalBox_1.add(btnNewButton_9);
		
		JButton btnNewButton_7 = new JButton();
		configurarBoton(btnNewButton_7, rutaPlay);
		horizontalBox_1.add(btnNewButton_7);
		
		JButton btnNewButton_10 = new JButton();
		configurarBoton(btnNewButton_10, rutaSiguente);
		horizontalBox_1.add(btnNewButton_10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut);
		
		JButton btnNewButton_8 = new JButton("Añadir lista");
		horizontalBox_1.add(btnNewButton_8);
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10, "panel_10");
		
		Box verticalBox_6 = Box.createVerticalBox();
		panel_10.add(verticalBox_6);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("Titulo");
		txtTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_6.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		Component verticalStrut_6 = Box.createVerticalStrut(20);
		verticalBox_6.add(verticalStrut_6);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox_6.add(horizontalBox_3);
		
		JButton btnNewButton_6 = new JButton("Crear");
		horizontalBox_3.add(btnNewButton_6);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_1);
		
		JButton btnNewButton_11 = new JButton("Eliminar");
		horizontalBox_3.add(btnNewButton_11);
		
		Component verticalStrut_7 = Box.createVerticalStrut(20);
		verticalBox_6.add(verticalStrut_7);
		
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		verticalBox_6.add(table_1);
		
		Component verticalStrut_8 = Box.createVerticalStrut(20);
		verticalBox_6.add(verticalStrut_8);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox_6.add(horizontalBox_4);
		
		JButton btnNewButton_12 = new JButton();
        configurarBoton(btnNewButton_12, rutaStop);
        horizontalBox_4.add(btnNewButton_12);

	    JButton btnNewButton_14 = new JButton();
        configurarBoton(btnNewButton_14, rutaPlay);
        horizontalBox_4.add(btnNewButton_14);

	    JButton btnNewButton_15 = new JButton();
        configurarBoton(btnNewButton_15, rutaSiguente);
        horizontalBox_4.add(btnNewButton_15);
        
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_2);
		
		JButton btnNewButton_13 = new JButton("Eliminar Lista");
		horizontalBox_4.add(btnNewButton_13);
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11, "panel_11");
		
		Box verticalBox_7 = Box.createVerticalBox();
		panel_11.add(verticalBox_7);
		
		Component verticalStrut_9 = Box.createVerticalStrut(20);
		verticalBox_7.add(verticalStrut_9);
		
		table_2 = new JTable();
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table_2.setSurrendersFocusOnKeystroke(true);
		verticalBox_7.add(table_2);
		
		Component verticalStrut_10 = Box.createVerticalStrut(20);
		verticalBox_7.add(verticalStrut_10);
		
		Box horizontalBox_4_1 = Box.createHorizontalBox();
		verticalBox_7.add(horizontalBox_4_1);
		
		JButton btnNewButton_12_1 = new JButton();
		configurarBoton(btnNewButton_12_1, rutaStop);
		horizontalBox_4_1.add(btnNewButton_12_1);
		
		JButton btnNewButton_14_1 = new JButton();
		configurarBoton(btnNewButton_14_1, rutaPlay);
		horizontalBox_4_1.add(btnNewButton_14_1);
		
		JButton btnNewButton_15_1 = new JButton();
		configurarBoton(btnNewButton_15_1, rutaSiguente);
		horizontalBox_4_1.add(btnNewButton_15_1);

		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_4");
			}
		});
		btnNewButton_6_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_5");
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!panel_9.isVisible()) {
					CardLayout cl = (CardLayout) (panel_7.getLayout());
					cl.show(panel_7, "panel_9");
				}
				else {
					CardLayout cl = (CardLayout) (panel_7.getLayout());
					cl.show(panel_7, "panel_8");
				}
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_10");
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_11");
			}
		});
		
		lista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_11");
			}
		});
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!Controlador.INSTANCE.comprobarPremium()) 
				{
					VentanaPremium pr = new VentanaPremium();
					pr.mostrarVentana();
					frmVentanaPrincipal.dispose();
				}
				
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView pr = new LoginView();
				pr.mostrarVentana();
				frmVentanaPrincipal.dispose();
			}
		});
		frmVentanaPrincipal.pack();
        frmVentanaPrincipal.setLocationRelativeTo(null);
	}
	
	static class RadioButtonEditor extends DefaultCellEditor {
        private static final long serialVersionUID = 1L;

        public RadioButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }
        
       
       public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof Boolean) {
                JRadioButton radioButton = new JRadioButton();
                radioButton.setSelected((Boolean) value);
                return radioButton;
            }
            return super.getTableCellEditorComponent(table, value, isSelected, row, column);
       }
	
	
	
	}
	 static class RadioButtonRenderer extends DefaultTableCellRenderer {
	        private static final long serialVersionUID = 1L;

	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            if (value instanceof Boolean) {
	                JRadioButton radioButton = new JRadioButton();
	                radioButton.setSelected((Boolean) value);
	                radioButton.setHorizontalAlignment(SwingConstants.CENTER);
	                return radioButton;
	            }
	            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        }
	    }
}