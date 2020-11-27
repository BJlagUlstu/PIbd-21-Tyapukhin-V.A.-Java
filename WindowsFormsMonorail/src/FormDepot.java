import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FormDepot {

    private static JFrame frame;
    private PanelDepot panelDepot;
    private JTextField textFieldNumberPlace;
    private DepotCollection depotCollection;
    private JList<String> listDepot;
    private final DefaultListModel<String> listDepotModel = new DefaultListModel<String>();
    private Queue<Vehicle> takenTransport = new ArrayDeque<>();

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
        depotCollection = new DepotCollection (1185, 605);
    }
    
    public static void setEnabled() {
    	frame.setEnabled(true);
    }
    
    public void setVisible() {
        frame.setVisible(true);
    }

    private void initialize() {
 
        frame = new JFrame();
        frame.setBounds(100, 100, 1250, 700);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        panelDepot = new PanelDepot();
        panelDepot.setBounds(0, 20, 1035, 605);
        frame.getContentPane().add(panelDepot);
        
        JLabel lblDepots = new JLabel("Depots:");
        lblDepots.setBounds(1116, 31, 46, 14);
        frame.getContentPane().add(lblDepots);
        
        JTextField textFieldDepots = new JTextField();
        textFieldDepots.setBounds(1076, 56, 130, 20);
        frame.getContentPane().add(textFieldDepots);
        textFieldDepots.setColumns(10);
        
        JButton btnAddDepot = new JButton("Add depot");
        btnAddDepot.setFocusPainted(false);
        btnAddDepot.setContentAreaFilled(false);
        btnAddDepot.addActionListener(e -> {
            if (textFieldDepots.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "Введите название автовокзала", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            } else {
                depotCollection.addDepot(textFieldDepots.getText());
                ReloadLevels();
            }
        });
        btnAddDepot.setBounds(1076, 87, 130, 25);
        frame.getContentPane().add(btnAddDepot);
        
        listDepot = new JList<String>(listDepotModel);
        listDepot.setBounds(1076, 123, 130, 130);
        listDepot.addListSelectionListener(e -> {
            if (listDepot.getSelectedIndex() > -1) {
            	panelDepot.setDepot(depotCollection.get(listDepotModel.get(listDepot.getSelectedIndex())));
            	panelDepot.repaint();
            }
        });
        frame.getContentPane().add(listDepot);
        
        JButton btnDeleteDepot = new JButton("Delete depot");
        btnDeleteDepot.setFocusPainted(false);
        btnDeleteDepot.setContentAreaFilled(false);
        btnDeleteDepot.addActionListener(e -> {
            if (listDepot.getSelectedIndex() > -1) {
                if (JOptionPane.showConfirmDialog(frame, "Удалить автовокзал " + listDepotModel.get(listDepot.getSelectedIndex()) + "?", "Удаление", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    depotCollection.delDepot(listDepotModel.get(listDepot.getSelectedIndex()));
                    ReloadLevels();
                }
            }
        });
        btnDeleteDepot.setBounds(1076, 274, 130, 25);
        frame.getContentPane().add(btnDeleteDepot);

        JButton buttonParkTrain = new JButton("Park Transport");
        buttonParkTrain.setFocusPainted(false);
        buttonParkTrain.setContentAreaFilled(false);
        buttonParkTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FormTrainConfig formTrainConfig = new FormTrainConfig();
                formTrainConfig.AddEvent(AddTrain);
                frame.setEnabled(false);
            	formTrainConfig.setVisible();
            }
        });
        buttonParkTrain.setBounds(1076, 350, 130, 25);
        frame.getContentPane().add(buttonParkTrain);

        JPanel groupPanelPickUp = new JPanel();
        groupPanelPickUp.setBounds(1076, 420, 130, 150);
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
                        Vehicle train = depotCollection.get(listDepotModel.get(listDepot.getSelectedIndex())).operatorSub(Integer.parseInt(textFieldNumberPlace.getText()));
                        if (train != null) {
                            takenTransport.add(train);
                        }
                        panelDepot.repaint();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Некорректный запрос", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnPickUp.setBounds(20, 100, 84, 25);
        groupPanelPickUp.add(btnPickUp);
        
        JButton btnLastTransport = new JButton("Last transport");
        btnLastTransport.setFocusPainted(false);
        btnLastTransport.setContentAreaFilled(false);
        btnLastTransport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (takenTransport.size() == 0) {
                	JOptionPane.showMessageDialog(null, "Очередь на выезд пуста", "Warning!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                FormMonorail form = new FormMonorail();
                form.SetMonorail(takenTransport.poll());
                frame.setEnabled(false);
                form.setVisible();
            }
        });
        btnLastTransport.setBounds(1076, 580, 130, 25);
        frame.getContentPane().add(btnLastTransport);
        
        JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));   
		menuBar.add(fileMenu);
		
		JMenuItem saveFile = new JMenuItem("Save");
        saveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JFileChooser openFile = new JFileChooser();
                if (openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = openFile.getSelectedFile().getPath();
                    try {
                        if (depotCollection.SaveData(path)) {
                            JOptionPane.showMessageDialog(null, "Сохранение успешно завершено", "Результат", JOptionPane.WARNING_MESSAGE);
                        } 
                    } catch (IOException e) {
                    	JOptionPane.showMessageDialog(null, "Не удалось сохранить", "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
		fileMenu.add(saveFile);
		
		JMenuItem uploadFile = new JMenuItem("Upload");
        uploadFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JFileChooser openFile = new JFileChooser();
                if (openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = openFile.getSelectedFile().getPath();
                    try {
                        if (depotCollection.LoadData(path)) {
                            ReloadLevels();
                            JOptionPane.showMessageDialog(null, "Загрузка успешно завершена", "Результат", JOptionPane.WARNING_MESSAGE);
                        } 
                    } catch (IOException e) {
                    	JOptionPane.showMessageDialog(null, "Не удалось загрузить", "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        fileMenu.addSeparator(); 
        fileMenu.add(uploadFile);
        
        JMenuItem saveOnlyOneDepot = new JMenuItem("Save only one depot");
        saveOnlyOneDepot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JFileChooser openFile = new JFileChooser();
                if (openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = openFile.getSelectedFile().getPath();
                    try {
                        if (depotCollection.SaveOnlyOneData(path, listDepotModel.get(listDepot.getSelectedIndex()))) {
                            JOptionPane.showMessageDialog(null, "Сохранение успешно завершено", "Результат", JOptionPane.WARNING_MESSAGE);
                        } 
                    } catch (IOException e) {
                    	JOptionPane.showMessageDialog(null, "Не удалось сохранить", "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        fileMenu.addSeparator(); 
		fileMenu.add(saveOnlyOneDepot);
		
		JMenuItem uploadOnlyOneDepot = new JMenuItem("Upload only one depot");
		uploadOnlyOneDepot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JFileChooser openFile = new JFileChooser();
                if (openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = openFile.getSelectedFile().getPath();
                    try {
                        if (depotCollection.LoadOnlyOneData(path)) {
                            ReloadLevels();
                            JOptionPane.showMessageDialog(null, "Загрузка успешно завершена", "Результат", JOptionPane.WARNING_MESSAGE);
                        } 
                    } catch (IOException e) {
                    	JOptionPane.showMessageDialog(null, "Не удалось загрузить", "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        fileMenu.addSeparator(); 
        fileMenu.add(uploadOnlyOneDepot);
    }
    
    private void ReloadLevels() {
        int index = listDepot.getSelectedIndex();
        listDepot.setSelectedIndex(-1);
        listDepotModel.clear();
        
        for (int i = 0; i < depotCollection.keys().length; i++) {
        	listDepotModel.addElement(depotCollection.keys()[i]);
        }
        
        if (listDepotModel.size() > 0 && (index == -1 || index >= listDepotModel.size())) {
        	listDepot.setSelectedIndex(0);
        } else if (listDepotModel.size() > 0 && index > -1 && index < listDepotModel.size()) {
        	listDepot.setSelectedIndex(index);
        }
    }
    
    Expression AddTrain = (train) -> {
        if (train != null && listDepot.getSelectedIndex() > -1) {
            if (depotCollection.get(listDepotModel.get(listDepot.getSelectedIndex())).operatorAdd(train)) {
            	panelDepot.repaint();
            }
            else {
            	JOptionPane.showMessageDialog(null, "Парковка переполнена", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        }
    };
}

interface Expression {
	
	public void AddTrain(Vehicle train);
}