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
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

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
import javax.swing.table.TableCellRenderer;

import org.kohsuke.github.GHCommit.File;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import pulsador.IEncendidoListener;
import pulsador.Luz;

public class VentanaPrincipal implements IEncendidoListener {

	private JFrame frmVentanaPrincipal;
	private JTextField interprete2;
	private JTextField titulo2;
	private JTable table;
	private JTextField interprete;
	private JTextField titulo;
	private JTextField tituloPl;
	private JTable table_1;
	private JTable table_2;

	public static int anchoDeseadoBoton = 30;
	public static int altoDeseadoBoton = 40;
	public static String rutaPlay = "/umu/tds/imagenes/play.png";
	public static String rutaStop = "/umu/tds/imagenes/stop.png";
	public static String rutaSiguente = "/umu/tds/imagenes/siguente.png";
	private DefaultTableModel tableModel;
	private Luz luz;

	public VentanaPrincipal() {
		initialize();
	}

	public void enteradoCambioEncendido(EventObject arg0) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(frmVentanaPrincipal);
		java.io.File archivo = fileChooser.getSelectedFile();
		if (archivo == null)
			JOptionPane.showMessageDialog(frmVentanaPrincipal, "Fichero no disponible", "Error",
					JOptionPane.WARNING_MESSAGE);
		else {
			if (archivo.getName().toLowerCase().endsWith(".xml")) {
				Controlador.INSTANCE.cargarCanciones(archivo.getPath());
			}
		}
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

		JButton buscarInicial = new JButton("Buscar");
		buscarInicial.setAlignmentX(Component.CENTER_ALIGNMENT);

		verticalBox.add(buscarInicial);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);

		JButton gestionPl = new JButton("Gestion PlayLists");
		gestionPl.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(gestionPl);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);

		JButton recientes = new JButton("Recientes");
		recientes.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(recientes);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);

		JButton misPlayList = new JButton("Mis PlayLists");
		misPlayList.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(misPlayList);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);

		JButton masReproducidas = new JButton("Mas Reproducidas");
		masReproducidas.setAlignmentX(Component.CENTER_ALIGNMENT);

		verticalBox.add(masReproducidas);

		Component verticalStrut_11 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_11);

		JButton btnNewButton_17 = new JButton("PDF");
		btnNewButton_17.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(btnNewButton_17);
		if (Controlador.INSTANCE.comprobarPremium()) {
			masReproducidas.setEnabled(true);
			btnNewButton_17.setEnabled(true);
		} else {
			masReproducidas.setEnabled(false);
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
		panel_9.add(verticalBox_5, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("Listas");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_5.add(lblNewLabel_1);
	
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		verticalBox_5.add(verticalStrut_5);
		DefaultListModel<String> modeloLista = new DefaultListModel<String>();
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

		luz = new Luz();
		panel_2.add(luz);

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

		interprete = new JTextField();
		interprete.setColumns(10);

		verticalBox_1_1_1.add(interprete);

		Component verticalStrut_3_1_1 = Box.createVerticalStrut(20);
		verticalBox_1_1_1.add(verticalStrut_3_1_1);

		JRadioButton favoritas = new JRadioButton("Favoritas");
		favoritas.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_1_1_1.add(favoritas);

		Box verticalBox_2_1_1 = Box.createVerticalBox();
		horizontalBox_2.add(verticalBox_2_1_1);

		titulo = new JTextField();
		titulo.setColumns(10);
		verticalBox_2_1_1.add(titulo);

		JComboBox<String> estilo = new JComboBox<>();
		verticalBox_2_1_1.add(estilo);

		JButton buscar = new JButton("Buscar");
		buscar.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_2_1_1.add(buscar);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, "panel_5");

		Box verticalBox_3 = Box.createVerticalBox();
		panel_5.add(verticalBox_3);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox_3.add(horizontalBox);

		Box verticalBox_1_1 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_1_1);

		interprete2 = new JTextField();
		interprete2.setColumns(10);
		verticalBox_1_1.add(interprete2);

		Component verticalStrut_3_1 = Box.createVerticalStrut(20);
		verticalBox_1_1.add(verticalStrut_3_1);

		JRadioButton favoritas2 = new JRadioButton("Favoritas");
		favoritas2.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_1_1.add(favoritas2);

		Box verticalBox_2_1 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_2_1);

		titulo2 = new JTextField();
		titulo2.setColumns(10);
		verticalBox_2_1.add(titulo2);

		JComboBox<String> estilo2 = new JComboBox<>();
		verticalBox_2_1.add(estilo2);
		

		JButton buscar2 = new JButton("Buscar");
		buscar2.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_2_1.add(buscar2);

		Component verticalStrut_4 = Box.createVerticalStrut(40);
		verticalStrut_4.setPreferredSize(new Dimension(0, 25));
		verticalStrut_4.setMinimumSize(new Dimension(0, 25));
		verticalBox_3.add(verticalStrut_4);

		Box verticalBox_4 = Box.createVerticalBox();
		verticalBox_3.add(verticalBox_4);
		String[] columnNames = { "Título", "Intérprete", "Estilo", "Favorita" };
		tableModel = new DefaultTableModel(columnNames, 0) {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		        if (columnIndex == 3) {
		            return Boolean.class;
		        }
		        return String.class;
		    }
		};
		table = new JTable(tableModel);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		table.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
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

		tituloPl = new JTextField();
		tituloPl.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_6.add(tituloPl);
		tituloPl.setColumns(10);

		Component verticalStrut_6 = Box.createVerticalStrut(20);
		verticalBox_6.add(verticalStrut_6);

		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox_6.add(horizontalBox_3);

		JButton crearPl = new JButton("Crear");
		horizontalBox_3.add(crearPl);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_1);

		JButton eliminarPl = new JButton("Eliminar");
		horizontalBox_3.add(eliminarPl);

		Component verticalStrut_7 = Box.createVerticalStrut(20);
		verticalBox_6.add(verticalStrut_7);
		DefaultTableModel tableModel1 = new DefaultTableModel(columnNames, 0) {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		        if (columnIndex == 3) {
		            return Boolean.class;
		        }
		        return String.class;
		    }
		};
		table_1 = new JTable(tableModel1);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		table_1.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());
		table_1.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
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

		JButton eliminarLista = new JButton("Eliminar Lista");
		horizontalBox_4.add(eliminarLista);

		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11, "panel_11");

		Box verticalBox_7 = Box.createVerticalBox();
		panel_11.add(verticalBox_7);

		Component verticalStrut_9 = Box.createVerticalStrut(20);
		verticalBox_7.add(verticalStrut_9);

		
		table_2 = new JTable();
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
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

		buscarInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_4");
				titulo.setText("");
				interprete.setText("");
				estilo.removeAllItems();
				estilo2.removeAllItems();
				HashSet<String> estilos = Controlador.INSTANCE.estilos();
				estilo.addItem("-");
				estilo2.addItem("-");
				for (String es : estilos) {
				    estilo.addItem(es);
				    estilo2.addItem(es);
				}
				
			}
		});
		
		crearPl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean creada= false;
				if (!tituloPl.getText().equals("")) 
				{
					creada = Controlador.INSTANCE.crearPl(tituloPl.getText());
				}
				if (creada) 
				{
					for (int row = 0; row < table_1.getRowCount(); row++) {
	                    String[] rowD = new String[tableModel1.getColumnCount()];
	                    for (int i = 0; i < tableModel1.getColumnCount()-1; i++) {
	                    		rowD[i] = (String) table_1.getValueAt(row, i);
	                    	}
	                    	Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo(rowD[0],rowD[1],rowD[2]);
	                    	Controlador.INSTANCE.addCancionPl(tituloPl.getText(), ca);
	                    }
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "PlayList Creada", "PlayList",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else if (tituloPl.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "Introduce el nombre de la PlayList", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					tableModel1.setRowCount(0);
					List<Cancion> lista = Controlador.INSTANCE.cancionesPl(tituloPl.getText());
					for(Cancion ca : lista) {
						Object[] rowData = { ca.getTitulo(), ca.getInterprete(), ca.getEstilo(), false };
						tableModel1.addRow(rowData);
		            }
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "PlayList ya existente", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				
                
			}
		});
		eliminarPl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean eliminada= false;
				if (!tituloPl.getText().equals("")) 
				{
					eliminada = Controlador.INSTANCE.eliminarPl(tituloPl.getText());
				}
				if (eliminada) 
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "PlayList Eliminada", "PlayList",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else 
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "PlayList no existente", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			
			}
		});
		
		buscar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) (panel_3.getLayout());
		        cl.show(panel_3, "panel_5");
		        tableModel.setRowCount(0);
		        List<Cancion> canciones = new LinkedList<Cancion>();
		        if (interprete.getText().equals("") && titulo.getText().equals("") && estilo.getSelectedItem().equals("-") && !favoritas.isSelected()) {
		            canciones = Controlador.INSTANCE.getAllCanciones();
		        }else if (interprete.getText().equals("") && estilo.getSelectedItem().equals("-") && !favoritas.isSelected()) {
		            canciones = Controlador.INSTANCE.getCancionesTitulo(titulo.getText());
		        }else if (interprete.getText().equals("") && estilo2.getSelectedItem().equals("-") && favoritas.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesTituloPl(titulo.getText());
		        }

		        for (Cancion cancion : canciones) {
		            Object[] rowData = { cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), false };
		            tableModel.addRow(rowData);
		        }
		        
		        if (!titulo.getText().equals("")) 
		        {
		        	titulo2.setText(titulo.getText());
		        }
		        if (!interprete.getText().equals("")) 
		        {
		        	interprete2.setText(interprete.getText());
		        }
		        tableModel.fireTableDataChanged();
		    }
		});
		
		buscar2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	tableModel.setRowCount(0);
		        List<Cancion> canciones = new LinkedList<Cancion>();
		        if (interprete2.getText().equals("") && titulo2.getText().equals("") && estilo2.getSelectedItem().equals("-") && !favoritas2.isSelected()) {
		            canciones = Controlador.INSTANCE.getAllCanciones();
		        } else if (interprete2.getText().equals("") && estilo2.getSelectedItem().equals("-")&& !favoritas2.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesTitulo(titulo2.getText());
		        } else if (interprete2.getText().equals("") && estilo2.getSelectedItem().equals("-") && favoritas2.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesTituloPl(titulo2.getText());
		        }
		        
		        for (Cancion cancion : canciones) {
		            Object[] rowData = { cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), false };
		            tableModel.addRow(rowData);
		        }
		        
		        tableModel.fireTableDataChanged();
		    }
		});


		misPlayList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!panel_9.isVisible()) {
					CardLayout cl = (CardLayout) (panel_7.getLayout());
					cl.show(panel_7, "panel_9");
				} else {
					CardLayout cl = (CardLayout) (panel_7.getLayout());
					cl.show(panel_7, "panel_8");
				}
				if (modeloLista != null) {
				    modeloLista.removeAllElements();
				}
				List <String> listaPl = Controlador.INSTANCE.nombrePlayLists();
				
				for (String elemento : listaPl) {
					modeloLista.addElement(elemento);
				}
				
			}
		});

		gestionPl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_10");
				tableModel1.setRowCount(0);
				LinkedList<Object[]> lista = new LinkedList<Object[]>();
				for (int row = 0; row < table.getRowCount(); row++) {
                    Boolean value = (Boolean) table.getValueAt(row, 3);
                    if (value) {
                    	Object[] rowD = new Object[tableModel.getColumnCount()];
                    	for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    		rowD[i] = table.getValueAt(row, i);
                    	}
                    	Object[] rowData = { rowD[0], rowD[1], rowD[2], false };
                    	
    		            lista.add(rowData);
                    }
                }
				
				tableModel.setRowCount(0);
				
				for (Object[] o : lista) {
					tableModel1.addRow(o);	
				}
				 
		        tableModel1.fireTableDataChanged(); 
			}
		});
		
		eliminarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tituloPl.getText().equals("")) {
					for (int row = 0; row < table_1.getRowCount(); row++) {
						Boolean value = (Boolean) table_1.getValueAt(row, 3);
                    	if (value) {
                    		String[] rowD = new String[tableModel1.getColumnCount()];
                    		for (int i = 0; i < tableModel1.getColumnCount()-1; i++) {
                    			rowD[i] = (String) table_1.getValueAt(row, i);
                    		}
                    		Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo(rowD[0],rowD[1],rowD[2]);
                    		Controlador.INSTANCE.eliminarCancionPl(tituloPl.getText(), ca.getId());
                    	}
					}	
				}
			}
		});

		recientes.addActionListener(new ActionListener() {
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

				if (!Controlador.INSTANCE.comprobarPremium()) {
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
		luz.addEncendidoListener(this);
		frmVentanaPrincipal.pack();
		frmVentanaPrincipal.setLocationRelativeTo(null);

	}

	static class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		CheckBoxRenderer() {
            setHorizontalAlignment(JCheckBox.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (Boolean) value);
            return this;
        }
    }

}