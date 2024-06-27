package umu.tds.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import javax.swing.ButtonModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import javax.swing.Box;
import javax.swing.JRadioButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import com.itextpdf.text.DocumentException;
import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
	private JTextField anadirCancionPl;
	private Cancion cancionActual=null;
	
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

		JButton pdfCreador = new JButton("PDF");
		pdfCreador.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(pdfCreador);
		if (Controlador.INSTANCE.comprobarPremium()) {
			masReproducidas.setEnabled(true);
			pdfCreador.setEnabled(true);
		} else {
			masReproducidas.setEnabled(false);
			pdfCreador.setEnabled(false);
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

		JButton stop = new JButton();
		configurarBoton(stop, rutaStop);
		horizontalBox_1.add(stop);

		JButton player = new JButton();
		configurarBoton(player, rutaPlay);
		horizontalBox_1.add(player);

		JButton pause = new JButton();
		configurarBoton(pause, rutaSiguente);
		horizontalBox_1.add(pause);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut);

		JButton anadirCancion = new JButton("Añadir lista");
		horizontalBox_1.add(anadirCancion);
		
		Component verticalStrut_13 = Box.createVerticalStrut(20);
		verticalBox_3.add(verticalStrut_13);
		
		Box verticalBox_8 = Box.createVerticalBox();
		verticalBox_3.add(verticalBox_8);
		
		Box horizontalBox_5 = Box.createHorizontalBox();
		verticalBox_8.add(horizontalBox_5);
		
		JLabel lblNewLabel_2 = new JLabel("PlayList");
		horizontalBox_5.add(lblNewLabel_2);
		
		anadirCancionPl = new JTextField();
		verticalBox_3.add(anadirCancionPl);
		anadirCancionPl.setColumns(10);

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

		Box horizontalBox_20 = Box.createHorizontalBox();
		verticalBox_6.add(horizontalBox_20);

		JButton stop1 = new JButton();
		configurarBoton(stop1, rutaStop);
		horizontalBox_20.add(stop1);

		JButton player1 = new JButton();
		configurarBoton(player1, rutaPlay);
		horizontalBox_20.add(player1);

		JButton pause1 = new JButton();
		configurarBoton(pause1, rutaSiguente);
		horizontalBox_20.add(pause1);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_20.add(horizontalStrut_2);

		JButton eliminarLista = new JButton("Eliminar Lista");
		horizontalBox_20.add(eliminarLista);

		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11, "panel_11");

		Box verticalBox_7 = Box.createVerticalBox();
		panel_11.add(verticalBox_7);

		Component verticalStrut_9 = Box.createVerticalStrut(20);
		verticalBox_7.add(verticalStrut_9);
		DefaultTableModel tableModel2 = new DefaultTableModel(columnNames, 0) {
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
		
		table_2 = new JTable(tableModel2);
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());
		table_2.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
		verticalBox_7.add(table_2);

		Component verticalStrut_10 = Box.createVerticalStrut(20);
		verticalBox_7.add(verticalStrut_10);

		Box horizontalBox_4_1 = Box.createHorizontalBox();
		verticalBox_7.add(horizontalBox_4_1);

		JButton stop2 = new JButton();
		configurarBoton(stop2, rutaStop);
		horizontalBox_4_1.add(stop2);

		JButton player2 = new JButton();
		configurarBoton(player2, rutaPlay);
		horizontalBox_4_1.add(player2);

		JButton pause2 = new JButton();
		pause2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		configurarBoton(pause2, rutaSiguente);
		horizontalBox_4_1.add(pause2);

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
		
		anadirCancion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean contiene = Controlador.INSTANCE.nombrePlayLists().contains(anadirCancionPl.getText());
				int add=0;
				if (!contiene) 
				{
					Controlador.INSTANCE.crearPl(anadirCancionPl.getText());
				}
				for (int row = 0; row < table.getRowCount(); row++) {
					Boolean value = (Boolean) table.getValueAt(row, 3);
	                if (value) {
	                	String[] rowD = new String[tableModel1.getColumnCount()];
	                	for (int i = 0; i < tableModel1.getColumnCount()-1; i++) {
                    		rowD[i] = (String) table.getValueAt(row, i);
                    	}
                    	Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo(rowD[0],rowD[1],rowD[2]);
                    	boolean contieneCancion = Controlador.INSTANCE.cancionesPl(anadirCancionPl.getText()).stream()
                    			.anyMatch(c -> c.getTitulo().equals(ca.getTitulo()) && c.getEstilo().equals(ca.getEstilo()) && c.getInterprete().equals(ca.getInterprete()));
                    	if (!contieneCancion) {
                    		Controlador.INSTANCE.addCancionPl(anadirCancionPl.getText(), ca);
                    		add++;
                    		
                    	}
	                }
	           }
			    if (add == 1) {
            		JOptionPane.showMessageDialog(frmVentanaPrincipal, add + " cancion añadida", "PlayList",
    						JOptionPane.INFORMATION_MESSAGE);
               } else if (add==0) 
			   {
				   JOptionPane.showMessageDialog(frmVentanaPrincipal, "No se han añadido canciones", "PlayList",
   						JOptionPane.INFORMATION_MESSAGE);
			   }else { 
       			JOptionPane.showMessageDialog(frmVentanaPrincipal, add + " canciones añadidas", "PlayList",
   						JOptionPane.INFORMATION_MESSAGE);
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
					String plEliminar = tituloPl.getText();
					for (int i = 0; i < modeloLista.size(); i++) {
	                    if (modeloLista.getElementAt(i).equals(plEliminar)) {
	                    	modeloLista.remove(i);
	                        i--;
	                    }
	                }
				}
				else 
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "PlayList no existente", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Cancion> lista = new ArrayList<>();
				for (int row = 0; row < table.getRowCount(); row++) {
                    Boolean value = (Boolean) table.getValueAt(row, 3);
                    if (value) {
                    	Object[] rowD = new Object[tableModel.getColumnCount()];
                    	for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    		rowD[i] = table.getValueAt(row, i);
                    	}
                    	Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo((String)rowD[0],(String) rowD[1],(String)rowD[2]);
                    	
    		            lista.add(ca);
                    }
                }
				if(lista.size()==0) 
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "Selecciona una cancion para reproducirla", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (lista.size()==1) 
				{	
					Cancion cancionPlay = null;
					for (Cancion ca : lista) 
					{
						cancionPlay=ca;
					}
					if (cancionActual == null) 
					{
						cancionActual=cancionPlay;
						Controlador.INSTANCE.reproducirCancion(cancionPlay);
					}
					else if (cancionActual != cancionPlay) 
					{
						cancionActual=cancionPlay;
						Controlador.INSTANCE.reproducirCancion(cancionPlay);
					}
					else if (cancionActual == cancionPlay) 
					{
						Controlador.INSTANCE.reanudarCancion();
					}	
				}
				else if (lista.size()> 1)
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "Selecciona SOLO una cancion para reproducirla", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		player1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Cancion> lista = new ArrayList<>();
				for (int row = 0; row < table_1.getRowCount(); row++) {
                    Boolean value = (Boolean) table_1.getValueAt(row, 3);
                    if (value) {
                    	Object[] rowD = new Object[tableModel1.getColumnCount()];
                    	for (int i = 0; i < tableModel1.getColumnCount(); i++) {
                    		rowD[i] = table_1.getValueAt(row, i);
                    	}
                    	Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo((String)rowD[0],(String) rowD[1],(String)rowD[2]);
                    	
    		            lista.add(ca);
                    }
                }
				if(lista.size()==0) 
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "Selecciona una cancion para reproducirla", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (lista.size()==1) 
				{	
					Cancion cancionPlay = null;
					for (Cancion ca : lista) 
					{
						cancionPlay=ca;
					}
					if (cancionActual == null) 
					{
						cancionActual=cancionPlay;
						Controlador.INSTANCE.reproducirCancion(cancionPlay);
					}
					else if (cancionActual != cancionPlay) 
					{
						cancionActual=cancionPlay;
						Controlador.INSTANCE.reproducirCancion(cancionPlay);
					}
					else if (cancionActual == cancionPlay) 
					{
						Controlador.INSTANCE.reanudarCancion();
					}	
				}
				else if (lista.size()> 1)
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "Selecciona SOLO una cancion para reproducirla", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		player2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Cancion> lista = new ArrayList<>();
				for (int row = 0; row < table_2.getRowCount(); row++) {
                    Boolean value = (Boolean) table_2.getValueAt(row, 3);
                    if (value) {
                    	Object[] rowD = new Object[tableModel2.getColumnCount()];
                    	for (int i = 0; i < tableModel2.getColumnCount(); i++) {
                    		rowD[i] = table_2.getValueAt(row, i);
                    	}
                    	Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo((String)rowD[0],(String) rowD[1],(String)rowD[2]);
                    	
    		            lista.add(ca);
                    }
                }
				if(lista.size()==0) 
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "Selecciona una cancion para reproducirla", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (lista.size()==1) 
				{	
					Cancion cancionPlay = null;
					for (Cancion ca : lista) 
					{
						cancionPlay=ca;
					}
					if (cancionActual == null) 
					{
						cancionActual=cancionPlay;
						Controlador.INSTANCE.reproducirCancion(cancionPlay);
					}
					else if (cancionActual != cancionPlay) 
					{
						cancionActual=cancionPlay;
						Controlador.INSTANCE.reproducirCancion(cancionPlay);
					}
					else if (cancionActual == cancionPlay) 
					{
						Controlador.INSTANCE.reanudarCancion();
					}	
				}
				else if (lista.size()> 1)
				{
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "Selecciona SOLO una cancion para reproducirla", "Error",
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
		        }else if (titulo.getText().equals("") && estilo.getSelectedItem().equals("-") && !favoritas.isSelected()){
		        	 canciones = Controlador.INSTANCE.getCancionesIn(interprete.getText());
		        }else if (favoritas.isSelected() && titulo.getText().equals("") && interprete.getText().equals("") && estilo.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getFavs();
		        }else if (interprete.getText().equals("") && estilo.getSelectedItem().equals("-") && favoritas.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesTituloPl(titulo.getText());
		        }else if (estilo.getSelectedItem().equals("-") && !favoritas.isSelected()) {
		        	Cancion ca = Controlador.INSTANCE.getCancionesTiIn(titulo.getText(), interprete.getText());
		        	if (ca!=null) {
		        		canciones.add(ca);
		        	}
		        }
		        else if (interprete.getText().equals("") && !favoritas.isSelected() && titulo.getText().equals("")) {
		        	canciones = Controlador.INSTANCE.getCancionesEs(estilo.getSelectedItem().toString());
		        }
		        else if(interprete.getText().equals("") && !favoritas.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesTiEs(titulo.getText(),estilo.getSelectedItem().toString());
		        }else if (titulo.getText().equals("") && !favoritas.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesInEs(interprete.getText(),estilo.getSelectedItem().toString());
		        }else if (!favoritas.isSelected()) {
		        	Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo(titulo.getText(), interprete.getText(),estilo.getSelectedItem().toString());
		        	if (ca!=null) {
		        		canciones.add(ca);
		        	}
		        }else if (favoritas.isSelected() && titulo.getText().equals("") && interprete.getText().equals("") && !estilo.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getCancionesEstiloPl(estilo.getSelectedItem().toString());
		        }else if (favoritas.isSelected() && titulo.getText().equals("") && !interprete.getText().equals("") && estilo.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getCancionesInterpretePl(interprete.getText());
		        }else if (favoritas.isSelected() && titulo.getText().equals("") && !interprete.getText().equals("") && !estilo.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getCancionesInEstiloPl(interprete.getText(),estilo.getSelectedItem().toString());
		        }else if (favoritas.isSelected() && !titulo.getText().equals("") && interprete.getText().equals("") && !estilo.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getCancionesTiEstiloPl(titulo.getText(),estilo.getSelectedItem().toString());
		        }else if (favoritas.isSelected()&& !titulo.getText().equals("") && !interprete.getText().equals("") && estilo.getSelectedItem().equals("-")) {
		        	Cancion ca = Controlador.INSTANCE.getCancionesTiInPl(titulo.getText(), interprete.getText());
		        	if (ca!=null) {
		        		canciones.add(ca);
		        	}
		        }else if (favoritas.isSelected()&& !titulo.getText().equals("") && !interprete.getText().equals("") && !estilo.getSelectedItem().equals("-")) {
		        	Cancion ca = Controlador.INSTANCE.getCancionesTiInEsPl(titulo.getText(), interprete.getText(),estilo.getSelectedItem().toString());
		        	if (ca!=null) {
		        		canciones.add(ca);
		        	}
		        }
		        if(canciones != null) {
			        for (Cancion cancion : canciones) {
			            Object[] rowData = { cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), false };
			            tableModel.addRow(rowData);
			        }
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
		
		pdfCreador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser seleccionado = new JFileChooser();
				seleccionado.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int resultado =seleccionado.showOpenDialog(frmVentanaPrincipal);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					java.io.File carpeta = seleccionado.getSelectedFile();
					String ruta = carpeta.getAbsolutePath();
					try {
						if (!Controlador.INSTANCE.PDF(ruta)) 
						{
							JOptionPane.showMessageDialog(frmVentanaPrincipal, "Directorio no existe o No existen Playlists", "Error",
									JOptionPane.WARNING_MESSAGE);
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (DocumentException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancionActual!=null) {
					Controlador.INSTANCE.pararCancion();
				}
			}
		});
		
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancionActual!=null) {
					Controlador.INSTANCE.pausarCancion();
				}
			}
		});
		
		stop2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancionActual!=null) {
					Controlador.INSTANCE.pararCancion();
				}
			}
		});
		
		pause2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancionActual!=null) {
					Controlador.INSTANCE.pausarCancion();
				}
			}
		});
		
		stop1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancionActual!=null) {
					Controlador.INSTANCE.pararCancion();
				}
			}
		});
		
		pause1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancionActual!=null) {
					Controlador.INSTANCE.pausarCancion();
				}
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
		        }else if (titulo2.getText().equals("") && estilo2.getSelectedItem().equals("-") && !favoritas2.isSelected()){
		        	 canciones = Controlador.INSTANCE.getCancionesIn(interprete2.getText());       
		        }else if (favoritas2.isSelected() && titulo2.getText().equals("") && interprete2.getText().equals("") && estilo2.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getFavs();
		        }else if (interprete2.getText().equals("") && estilo2.getSelectedItem().equals("-") && favoritas2.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesTituloPl(titulo2.getText());
		        }else if (estilo2.getSelectedItem().equals("-") && !favoritas2.isSelected()) {
		        	Cancion ca = Controlador.INSTANCE.getCancionesTiIn(titulo2.getText(), interprete2.getText());
		        	if (ca!=null) {
		        		canciones.add(ca);
		        	}
		        }else if (interprete2.getText().equals("") && !favoritas2.isSelected() && titulo2.getText().equals("")) {
		        	canciones = Controlador.INSTANCE.getCancionesEs(estilo2.getSelectedItem().toString());
		        }else if(interprete2.getText().equals("") && !favoritas2.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesTiEs(titulo2.getText(),estilo2.getSelectedItem().toString());
		        }else if (titulo2.getText().equals("") && !favoritas2.isSelected()) {
		        	canciones = Controlador.INSTANCE.getCancionesInEs(interprete2.getText(),estilo2.getSelectedItem().toString());
		        }else if (!favoritas2.isSelected()) {
		        	Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo(titulo2.getText(), interprete2.getText(),estilo2.getSelectedItem().toString());
		        	if (ca!=null) {
		        		canciones.add(ca);
		        	}
		        }else if (favoritas2.isSelected() && titulo2.getText().equals("") && interprete2.getText().equals("") && !estilo2.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getCancionesEstiloPl(estilo2.getSelectedItem().toString());
		        }else if (favoritas2.isSelected() && titulo2.getText().equals("") && !interprete2.getText().equals("") && estilo2.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getCancionesInterpretePl(interprete2.getText());
		        }else if (favoritas2.isSelected() && titulo2.getText().equals("") && !interprete2.getText().equals("") && !estilo2.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getCancionesInEstiloPl(interprete2.getText(),estilo2.getSelectedItem().toString());
		        }else if (favoritas2.isSelected() && !titulo2.getText().equals("") && interprete2.getText().equals("") && !estilo2.getSelectedItem().equals("-")) {
		        	canciones = Controlador.INSTANCE.getCancionesTiEstiloPl(titulo2.getText(),estilo2.getSelectedItem().toString());
		        }else if (favoritas2.isSelected()&& !titulo2.getText().equals("") && !interprete2.getText().equals("") && estilo2.getSelectedItem().equals("-")) {
		        	Cancion ca = Controlador.INSTANCE.getCancionesTiInPl(titulo2.getText(), interprete2.getText());
		        	if (ca!=null) {
		        		canciones.add(ca);
		        	}
		        }else if (favoritas.isSelected()&& !titulo.getText().equals("") && !interprete.getText().equals("") && !estilo.getSelectedItem().equals("-")) {
		        	Cancion ca = Controlador.INSTANCE.getCancionesTiInEsPl(titulo.getText(), interprete.getText(),estilo.getSelectedItem().toString());
		        	if (ca!=null) {
		        		canciones.add(ca);
		        	}
		        }
		        if(canciones != null) {
			        for (Cancion cancion : canciones) {
			            Object[] rowData = { cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), false };
			            tableModel.addRow(rowData);
			        }
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
		
		recientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_11");
				List<Cancion> canciones = Controlador.INSTANCE.recientes();
				tableModel2.setRowCount(0);
                 if (canciones.size()==0) 
                 {
                 	JOptionPane.showMessageDialog(frmVentanaPrincipal, "No hay canciones recientes", "PlayList",
 							JOptionPane.WARNING_MESSAGE);
                 }
                	for (Cancion cancion : canciones) {
 		           	Object[] rowData = { cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), false };
 		           	tableModel2.addRow(rowData);
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
		            ArrayList<Integer> filasEliminar = new ArrayList<>();

		            for (int row = 0; row < table_1.getRowCount(); row++) {
		                Boolean value = (Boolean) table_1.getValueAt(row, 3);
		                if (value) {
		                    String[] rowD = new String[tableModel1.getColumnCount()];
		                    for (int i = 0; i < tableModel1.getColumnCount() - 1; i++) {
		                        rowD[i] = (String) table_1.getValueAt(row, i);
		                    }
		                    Cancion ca = Controlador.INSTANCE.getCancionTituloInterpreteEstilo(rowD[0], rowD[1], rowD[2]);
		                    Controlador.INSTANCE.eliminarCancionPl(tituloPl.getText(), ca.getId());

		                    filasEliminar.add(row);
		                }
		            }

		            for (int i = filasEliminar.size() - 1; i >= 0; i--) {
		                tableModel1.removeRow(filasEliminar.get(i));
		            }
		        }
		    }
		});


		lista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_11");
				tableModel2.setRowCount(0);
				if (e.getClickCount() == 1) {
                    int index = lista.locationToIndex(e.getPoint());
                    String playlist = lista.getModel().getElementAt(index);
                    List<Cancion> canciones = Controlador.INSTANCE.cancionesPl(playlist);
                    if (canciones.size()==0) 
                    {
                    	JOptionPane.showMessageDialog(frmVentanaPrincipal, "No hay canciones en la playlist", "PlayList",
    							JOptionPane.WARNING_MESSAGE);
                    }
                   	for (Cancion cancion : canciones) {
    		           	Object[] rowData = { cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), false };
    		           	tableModel2.addRow(rowData);
    		        }  
                    
                }
				tableModel2.fireTableDataChanged(); 
			}
		});
		
		masReproducidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_3.getLayout());
				cl.show(panel_3, "panel_11");
				List<Cancion> canciones = Controlador.INSTANCE.getMasRepro();
				tableModel2.setRowCount(0);
                 if (canciones.size()==0) 
                 {
                 	JOptionPane.showMessageDialog(frmVentanaPrincipal, "No hay canciones recientes", "PlayList",
 							JOptionPane.WARNING_MESSAGE);
                 }
                	for (Cancion cancion : canciones) {
 		           	Object[] rowData = { cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), false };
 		           	tableModel2.addRow(rowData);
 		        } 
				
			}
		});

		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!Controlador.INSTANCE.comprobarPremium()) {
					VentanaPremium pr = new VentanaPremium();
					pr.mostrarVentana();
					frmVentanaPrincipal.dispose();
				}
				else {
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "Ya eres Premium", "Error",
							JOptionPane.WARNING_MESSAGE);
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