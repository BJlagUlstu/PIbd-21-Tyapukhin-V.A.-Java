import java.awt.EventQueue;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import javax.swing.JButton;

public class FormTrainConfig {

	private JFrame frame;
	private PanelTrainConfig panelOutputTransport;
	private IDoor doorType;
	private Color mainColor = Color.WHITE;
	private Color dopColor = Color.WHITE;
	private int pickDoor = 0;
	
	Vehicle train = null;

	private Expression event;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTrainConfig window = new FormTrainConfig();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormTrainConfig() {
		initialize();
	}
	
    public void setVisible() {
        frame.setVisible(true);
    }

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 340);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FormDepot.setEnabled();
            }
        });
		frame.getContentPane().setLayout(null);
		
		JSpinner NumberOfDoorSpinner = new JSpinner();
		
		JPanel panelTypeTrain = new JPanel();
		panelTypeTrain.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTypeTrain.setBounds(10, 10, 120, 100);
		frame.getContentPane().add(panelTypeTrain);
		panelTypeTrain.setLayout(null);
		frame.getContentPane().add(panelTypeTrain);
		
		JLabel labelTypeTransport = new JLabel("Type transport");
		labelTypeTransport.setBounds(10, 0, 90, 20);
		panelTypeTrain.add(labelTypeTransport);
		
		JLabel labelTypeTrain = new JLabel("Train", SwingConstants.CENTER);
		labelTypeTrain.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelTypeTrain.setTransferHandler(new TransferHandler("text"));
		labelTypeTrain.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	JComponent comp = (JComponent) evt.getSource();
	        	TransferHandler th = comp.getTransferHandler();
	        	th.exportAsDrag(comp, evt, TransferHandler.COPY);
	        }
	      });
		labelTypeTrain.setDropTarget(null);
		labelTypeTrain.setBounds(20, 35, 80, 20);
		panelTypeTrain.add(labelTypeTrain);
	    
		JLabel labelTypeMonorail = new JLabel("Monorail", SwingConstants.CENTER);
		labelTypeMonorail.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelTypeMonorail.setTransferHandler(new TransferHandler("text"));
		labelTypeMonorail.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	JComponent comp = (JComponent) evt.getSource();
	        	TransferHandler th = comp.getTransferHandler();
	        	th.exportAsDrag(comp, evt, TransferHandler.COPY);
	        }
	      });
		labelTypeMonorail.setDropTarget(null);
		labelTypeMonorail.setBounds(20, 65, 80, 20);
		panelTypeTrain.add(labelTypeMonorail);
	    
	    JPanel panelOptions = new JPanel();
	    panelOptions.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panelOptions.setBounds(10, 135, 445, 150);
	    frame.getContentPane().add(panelOptions);
	    panelOptions.setLayout(null);
	    
	    JLabel labelOptions = new JLabel("Options");
	    labelOptions.setBounds(10, 0, 45, 20);
	    panelOptions.add(labelOptions);
	    
	    JSpinner MaxSpeedSpinner = new JSpinner();
        MaxSpeedSpinner.setModel(new SpinnerNumberModel(100, 100, 1000, 1));
        MaxSpeedSpinner.setBounds(115, 50, 75, 20);
        panelOptions.add(MaxSpeedSpinner);

        JSpinner WeightSpinner = new JSpinner();
        WeightSpinner.setModel(new SpinnerNumberModel(100, 100, 1000, 1));
        WeightSpinner.setBounds(115, 100, 75, 20);
        panelOptions.add(WeightSpinner);
        
        JLabel labelMaxSpeed = new JLabel("Max speed");
        labelMaxSpeed.setBounds(22, 50, 70, 20);
        panelOptions.add(labelMaxSpeed);
        
        JLabel labelWeight = new JLabel("Weight");
        labelWeight.setBounds(22, 100, 50, 20);
        panelOptions.add(labelWeight);
        
        JCheckBox SportLineCheckBox = new JCheckBox("Sport Line");
        SportLineCheckBox.setFocusPainted(false);
        SportLineCheckBox.setBounds(250, 45, 90, 20);
        panelOptions.add(SportLineCheckBox);
        
        JCheckBox BottomMonorailCheckBox = new JCheckBox("Bottom Monorail");
        BottomMonorailCheckBox.setFocusPainted(false);
        BottomMonorailCheckBox.setBounds(250, 75, 120, 20);
        panelOptions.add(BottomMonorailCheckBox);
        
        JCheckBox HeadlightsCheckBox = new JCheckBox("Headlights");
        HeadlightsCheckBox.setFocusPainted(false);
        HeadlightsCheckBox.setBounds(250, 105, 85, 20);
        panelOptions.add(HeadlightsCheckBox);
	    
	    panelOutputTransport = new PanelTrainConfig();
	    panelOutputTransport.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panelOutputTransport.setBounds(155, 10, 300, 100);
	    frame.getContentPane().add(panelOutputTransport);
	    panelOutputTransport.setLayout(null);
	    
        MouseAdapter configListener = new Drag();
	    
	    JLabel LabelConfigTypeTransports = new JLabel();
	    LabelConfigTypeTransports.setBounds(155, 10, 300, 100);
        LabelConfigTypeTransports.setTransferHandler(new TransferHandler("text"));
        PropertyChangeListener propertyChangeListener = propertyChangeEvent -> {
            if (LabelConfigTypeTransports.getText().equals("Train")) {
            	train = new Train((int) MaxSpeedSpinner.getValue(), (int) WeightSpinner.getValue(), Color.WHITE);
                panelOutputTransport.setTransport(train);
            } else if (LabelConfigTypeTransports.getText().equals("Monorail")) {
            	train = new Monorail((int) MaxSpeedSpinner.getValue(), (int) WeightSpinner.getValue(), Color.WHITE, Color.BLACK, SportLineCheckBox.isSelected(), HeadlightsCheckBox.isSelected(), BottomMonorailCheckBox.isSelected());
    			panelOutputTransport.setTransport(train);
    			((Monorail) train).setCountDoor((int)NumberOfDoorSpinner.getValue() - 1);
            }
    		if(train instanceof Monorail && doorType != null) {
    			((Monorail) train).setDoorType(doorType);
    		}
        	panelOutputTransport.repaint();
            LabelConfigTypeTransports.setText("");
        };
        LabelConfigTypeTransports.addPropertyChangeListener("text", propertyChangeListener);
        LabelConfigTypeTransports.addMouseListener(configListener);
        frame.getContentPane().add(LabelConfigTypeTransports);
	    
	    JPanel panelColors = new JPanel();
	    panelColors.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panelColors.setBounds(475, 10, 230, 225);
	    frame.getContentPane().add(panelColors);
	    panelColors.setLayout(null);
	    
	    JLabel labelColors = new JLabel("Colors");
	    labelColors.setBounds(10, 0, 45, 20);
	    panelColors.add(labelColors);
	    
	    JLabel labelMainColor = new JLabel("Main color", SwingConstants.CENTER);
	    labelMainColor.setBorder(new LineBorder(new Color(0, 0, 0)));
	    labelMainColor.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	        	if(train instanceof Vehicle && mainColor != null) {
	        		train.setMainColor(mainColor);
	        	}
	        	panelOutputTransport.repaint();
	        }
	    });
	    labelMainColor.setBounds(25, 35, 80, 20);
	    panelColors.add(labelMainColor);
	    
	    JLabel labelAdditionalColor = new JLabel("Add. color", SwingConstants.CENTER);
	    labelAdditionalColor.setBorder(new LineBorder(new Color(0, 0, 0)));
	    labelAdditionalColor.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	        	 if (train instanceof Monorail && mainColor != null) {
	        		 ((Monorail) train).setDopColor(mainColor);
	        	 }
	        	 panelOutputTransport.repaint();
	        }
	    });
	    labelAdditionalColor.setBounds(125, 35, 80, 20);
	    panelColors.add(labelAdditionalColor);
	    
	    JLabel RedLabel = new JLabel();
	    RedLabel.setOpaque(true);
	    RedLabel.setBackground(Color.RED);
	    RedLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    RedLabel.setTransferHandler(new TransferHandler("text"));
	    RedLabel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	labelColor_MouseDown(evt, RedLabel);
	        }
	      });
	    RedLabel.setDropTarget(null);
	    RedLabel.setBounds(25, 100, 30, 30);
	    panelColors.add(RedLabel);
	    
	    JLabel YellowLabel = new JLabel();
	    YellowLabel.setOpaque(true);
	    YellowLabel.setBackground(Color.YELLOW);
	    YellowLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    YellowLabel.setTransferHandler(new TransferHandler("text"));
	    YellowLabel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	labelColor_MouseDown(evt, YellowLabel);
	        }
	      });
	    YellowLabel.setDropTarget(null);
	    YellowLabel.setBounds(75, 100, 30, 30);
	    panelColors.add(YellowLabel);
	    
	    JLabel BlackLabel = new JLabel();	    
	    BlackLabel.setOpaque(true);
	    BlackLabel.setBackground(Color.BLACK);
	    BlackLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    BlackLabel.setTransferHandler(new TransferHandler("text"));
	    BlackLabel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	labelColor_MouseDown(evt, BlackLabel);
	        }
	      });
	    BlackLabel.setDropTarget(null);
	    BlackLabel.setBounds(126, 100, 30, 30);
	    panelColors.add(BlackLabel);
	    
	    JLabel WhiteLabel = new JLabel();	    
	    WhiteLabel.setOpaque(true);
	    WhiteLabel.setBackground(Color.WHITE);
	    WhiteLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    WhiteLabel.setTransferHandler(new TransferHandler("text"));
	    WhiteLabel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	labelColor_MouseDown(evt, WhiteLabel);
	        }
	      });
	    WhiteLabel.setDropTarget(null);
	    WhiteLabel.setBounds(175, 100, 30, 30);
	    panelColors.add(WhiteLabel);
	    
	    JLabel GreyLabel = new JLabel();	    
	    GreyLabel.setOpaque(true);
	    GreyLabel.setBackground(Color.GRAY);
	    GreyLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    GreyLabel.setTransferHandler(new TransferHandler("text"));
	    GreyLabel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	labelColor_MouseDown(evt, GreyLabel);
	        }
	      });
	    GreyLabel.setDropTarget(null);
	    GreyLabel.setBounds(25, 150, 30, 30);
	    panelColors.add(GreyLabel);
	    
	    JLabel OrangeLabel = new JLabel();
	    OrangeLabel.setOpaque(true);
	    OrangeLabel.setBackground(Color.ORANGE);	    
	    OrangeLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    OrangeLabel.setTransferHandler(new TransferHandler("text"));
	    OrangeLabel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	labelColor_MouseDown(evt, OrangeLabel);
	        }
	      });
	    OrangeLabel.setDropTarget(null);
	    OrangeLabel.setBounds(75, 150, 30, 30);
	    panelColors.add(OrangeLabel);
	    
	    JLabel GreenLabel = new JLabel();	    
	    GreenLabel.setOpaque(true);
	    GreenLabel.setBackground(Color.GREEN);
	    GreenLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    GreenLabel.setTransferHandler(new TransferHandler("text"));
	    GreenLabel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	labelColor_MouseDown(evt, GreenLabel);
	        }
	      });
	    GreenLabel.setDropTarget(null);
	    GreenLabel.setBounds(125, 150, 30, 30);
	    panelColors.add(GreenLabel);
	    
	    JLabel BlueLabel = new JLabel();	    
	    BlueLabel.setOpaque(true);
	    BlueLabel.setBackground(Color.BLUE);
	    BlueLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    BlueLabel.setTransferHandler(new TransferHandler("text"));
	    BlueLabel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	labelColor_MouseDown(evt, BlueLabel);
	        }
	      });
	    BlueLabel.setDropTarget(null);
	    BlueLabel.setBounds(175, 150, 30, 30);
	    panelColors.add(BlueLabel);
	    
	    JButton buttonAdd = new JButton("Add");
	    buttonAdd.setFocusPainted(false);
	    buttonAdd.setContentAreaFilled(false);
	    buttonAdd.addActionListener(e -> {
	    	event.AddTrain(train);
	    	FormDepot.setEnabled();
	    	frame.dispose();
        });
	    buttonAdd.setBounds(485, 255, 90, 25);
	    frame.getContentPane().add(buttonAdd);
	    
	    JButton buttonCancel = new JButton("Cancel");
	    buttonCancel.setFocusPainted(false);
	    buttonCancel.setContentAreaFilled(false);
	    buttonCancel.addActionListener(e -> {
	    	FormDepot.setEnabled();
	    	frame.dispose();
        });
	    buttonCancel.setBounds(600, 255, 90, 25);
	    frame.getContentPane().add(buttonCancel);
	    
	    JPanel panelDoorTypes = new JPanel();
	    panelDoorTypes.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panelDoorTypes.setBounds(715, 10, 105, 225);
	    frame.getContentPane().add(panelDoorTypes);
	    panelDoorTypes.setLayout(null);
	    
	    JLabel labelDoorTypes = new JLabel("Door Types");
	    labelDoorTypes.setBounds(10, 0, 65, 20);
	    panelDoorTypes.add(labelDoorTypes);
	    
	    JLabel labelDoorTypeUsual = new JLabel("Usual", SwingConstants.CENTER);
	    labelDoorTypeUsual.setBorder(new LineBorder(new Color(0, 0, 0)));
	    labelDoorTypeUsual.setTransferHandler(new TransferHandler("text"));
	    labelDoorTypeUsual.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	JComponent comp = (JComponent) evt.getSource();
	        	TransferHandler th = comp.getTransferHandler();
	        	th.exportAsDrag(comp, evt, TransferHandler.COPY);
	        	doorType = new MonorailDoor(dopColor, pickDoor);
	        }
	      });
	    labelDoorTypeUsual.setDropTarget(null);
	    labelDoorTypeUsual.setBounds(10, 40, 85, 20);
	    panelDoorTypes.add(labelDoorTypeUsual);
	    
	    JLabel labelDoorSubtypeOne = new JLabel("Subtype 1", SwingConstants.CENTER);
	    labelDoorSubtypeOne.setBorder(new LineBorder(new Color(0, 0, 0)));
	    labelDoorSubtypeOne.setTransferHandler(new TransferHandler("text"));
	    labelDoorSubtypeOne.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	JComponent comp = (JComponent) evt.getSource();
	        	TransferHandler th = comp.getTransferHandler();
	        	th.exportAsDrag(comp, evt, TransferHandler.COPY);
	        	doorType = new MonorailDoorTypeOne(dopColor, pickDoor);
	        }
	      });
	    labelDoorSubtypeOne.setDropTarget(null);
	    labelDoorSubtypeOne.setBounds(10, 90, 85, 20);
	    panelDoorTypes.add(labelDoorSubtypeOne);
	    
	    JLabel labelDoorSubtypeTwo = new JLabel("Subtype 2", SwingConstants.CENTER);
	    labelDoorSubtypeTwo.setBorder(new LineBorder(new Color(0, 0, 0)));
	    labelDoorSubtypeTwo.setTransferHandler(new TransferHandler("text"));
	    labelDoorSubtypeTwo.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	        	JComponent comp = (JComponent) evt.getSource();
	        	TransferHandler th = comp.getTransferHandler();
	        	th.exportAsDrag(comp, evt, TransferHandler.COPY);
	        	doorType = new MonorailDoorTypeTwo(dopColor, pickDoor);
	        }
	      });
	    labelDoorSubtypeTwo.setDropTarget(null);
	    labelDoorSubtypeTwo.setBounds(10, 140, 85, 20);
	    panelDoorTypes.add(labelDoorSubtypeTwo);
	    
	    NumberOfDoorSpinner.setModel(new SpinnerNumberModel(1, 1, 3, 1));
	    NumberOfDoorSpinner.addChangeListener(new ChangeListener() {      

			@Override
			public void stateChanged(ChangeEvent e) {
			    if (train instanceof Monorail) {
			    	((Monorail) train).setCountDoor((int)NumberOfDoorSpinner.getValue() - 1);
			    	panelOutputTransport.repaint();
			    }
			}
	    });
	    NumberOfDoorSpinner.setBounds(10, 190, 85, 20);
	    panelDoorTypes.add(NumberOfDoorSpinner);
	}
	
	public void labelColor_MouseDown(MouseEvent evt, JLabel labelColor) {
    	JComponent comp = (JComponent) evt.getSource();
    	TransferHandler th = comp.getTransferHandler();
    	th.exportAsDrag(comp, evt, TransferHandler.COPY);
        mainColor = labelColor.getBackground();
    }

	public void AddEvent(Expression event) {
		this.event = event;
	}
}