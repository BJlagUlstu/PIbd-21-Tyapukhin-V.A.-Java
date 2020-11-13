import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormDepot {

    private static JFrame frame;
    private PanelDepot panelDepot;
    private JPanel colorPanel;
    private Depot <ITransport, IDoor> depot;
    private JTextField textFieldNumberPlace;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormDepot window = new FormDepot();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FormDepot() {
        initialize();
        depot = new Depot <ITransport, IDoor> (1185, 605);
        panelDepot = new PanelDepot(depot);
        panelDepot.setBounds(0, 20, 1035, 605);
        frame.getContentPane().add(panelDepot);
    }
    
    public static void setEnabled() {
    	frame.setEnabled(true);
    }

    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 1250, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton buttonParkTrain = new JButton("Park Train");
        buttonParkTrain.setFocusPainted(false);
        buttonParkTrain.setContentAreaFilled(false);
        buttonParkTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color mainColor = JColorChooser.showDialog(colorPanel, "Panel colors", Color.GREEN);
                if(mainColor != null) {
                	var train = new Train(200, 1750, mainColor);
                    if (depot.operatorAdd(train)) {
                        panelDepot.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Парковка переполнена", "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        buttonParkTrain.setBounds(1072, 62, 130, 25);
        frame.getContentPane().add(buttonParkTrain);

        JButton btnParkMonorail = new JButton("Park Monorail");
        btnParkMonorail.setFocusPainted(false);
        btnParkMonorail.setContentAreaFilled(false);
        btnParkMonorail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Color mainColor = JColorChooser.showDialog(colorPanel, "Panel colors", Color.GREEN);
            	if(mainColor != null) {
                    Color dopColor = JColorChooser.showDialog(colorPanel, "Panel colors", Color.GREEN);
                    if(dopColor != null) {
                        var train = new Monorail(200, 1750, mainColor, dopColor, true, true, true);
                        if (depot.operatorAdd(train)) {
                        	panelDepot.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Парковка переполнена", "Warning!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });
        btnParkMonorail.setBounds(1072, 116, 130, 25);
        frame.getContentPane().add(btnParkMonorail);

        JPanel groupPanelPickUp = new JPanel();
        groupPanelPickUp.setBounds(1076, 175, 130, 150);
        groupPanelPickUp.setBorder(new LineBorder(new Color(0, 0, 0)));
        frame.getContentPane().add(groupPanelPickUp);
        groupPanelPickUp.setLayout(null);

        JLabel lblPickUpTrain = new JLabel("Pick up train");
        lblPickUpTrain.setBounds(20, 11, 84, 14);
        groupPanelPickUp.add(lblPickUpTrain);

        JLabel lblPlace = new JLabel("Place:");
        lblPlace.setBounds(10, 49, 39, 14);
        groupPanelPickUp.add(lblPlace);

        textFieldNumberPlace = new JTextField();
        textFieldNumberPlace.setBounds(66, 46, 54, 20);
        groupPanelPickUp.add(textFieldNumberPlace);
        textFieldNumberPlace.setColumns(10);

        JButton btnPickUp = new JButton("Pick up");
        btnPickUp.setFocusPainted(false);
        btnPickUp.setContentAreaFilled(false);
        btnPickUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!textFieldNumberPlace.getText().equals("")) {
                        var train = depot.operatorSub(Integer.parseInt(textFieldNumberPlace.getText()));
                        if (train != null) {
                            FormMonorail form = new FormMonorail();
                            form.SetMonorail(train);
                            frame.setEnabled(false);
                            form.setVisible();
                            panelDepot.repaint();
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Некорректный запрос", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnPickUp.setBounds(20, 100, 84, 25);
        groupPanelPickUp.add(btnPickUp);
    }
}