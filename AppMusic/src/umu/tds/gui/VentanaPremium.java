package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import umu.tds.controlador.Controlador;

import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPremium {

    private JFrame frmVentanaPrincipal;

    public VentanaPremium() {
        initialize();
    }

    public void mostrarVentana() {
        frmVentanaPrincipal.setLocationRelativeTo(null);
        frmVentanaPrincipal.setVisible(true);
    }

    public JFrame getFrame() {
        return frmVentanaPrincipal;
    }

    public void initialize() {

        frmVentanaPrincipal = new JFrame();
        frmVentanaPrincipal.setMinimumSize(new Dimension(600, 200));
        frmVentanaPrincipal.setTitle("AppMusic - Ventana Premium");
        frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frmVentanaPrincipal.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frmVentanaPrincipal.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        Box verticalBox = Box.createVerticalBox();
        panel.add(verticalBox, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel("Convertir en Premium");
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        verticalBox.add(lblNewLabel);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalBox.add(verticalStrut);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox.add(horizontalBox);

        JLabel lblNewLabel_1 = new JLabel("Precio:");
        horizontalBox.add(lblNewLabel_1);

        Box horizontalBox_1 = Box.createHorizontalBox();
        horizontalBox_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox.add(horizontalBox_1);

        JButton btnNewButton = new JButton("Pagar");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Controlador.INSTANCE.usuarioPremium();
        		VentanaPrincipal pr = new VentanaPrincipal();
				pr.mostrarVentana();
				frmVentanaPrincipal.dispose();
        	}
        });
        horizontalBox_1.add(btnNewButton);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        horizontalBox_1.add(horizontalStrut_1);

        JButton btnNewButton_1 = new JButton("Cancelar");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VentanaPrincipal pr = new VentanaPrincipal();
				pr.mostrarVentana();
				frmVentanaPrincipal.dispose();
        	}
        });
        horizontalBox_1.add(btnNewButton_1);

        // Centrar la ventana en la pantalla
        frmVentanaPrincipal.pack();
        frmVentanaPrincipal.setLocationRelativeTo(null);
    }

   
}

