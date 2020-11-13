import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FormMonorail {

    private JFrame frame;
    private PanelMonorail panel;
    private ITransport train;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormMonorail window = new FormMonorail();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FormMonorail() {
        initialize();
    }

    public void SetMonorail(ITransport monorail) {
        Random rnd = new Random();
        this.train = monorail;
        monorail.SetPosition(rnd.nextInt(50) + 50, rnd.nextInt(50) + 50, 1185, 485);
        panel = new PanelMonorail(train);
        panel.setBounds(0, 40, 1185, 485);
        frame.getContentPane().add(panel);
        panel.repaint();
    }

    public void setVisible() {
        frame.setVisible(true);
    }

    private void initialize() {

        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FormDepot.setEnabled();
            }
        });
        frame.setBounds(100, 100, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton buttonRight = new JButton(new ImageIcon(((new ImageIcon("Point\\Right.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)))));
        buttonRight.setContentAreaFilled(false);
        buttonRight.setFocusPainted(false);
        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                train.MoveTransport(Direction.Right);
                panel.repaint();
            }
        });
        buttonRight.setBounds(1112, 589, 50, 50);
        frame.getContentPane().add(buttonRight);

        JButton buttonDown = new JButton(new ImageIcon(((new ImageIcon("Point\\Down.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)))));
        buttonDown.setContentAreaFilled(false);
        buttonDown.setFocusPainted(false);
        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                train.MoveTransport(Direction.Down);
                panel.repaint();
            }
        });
        buttonDown.setBounds(1052, 589, 50, 50);
        frame.getContentPane().add(buttonDown);

        JButton buttonUp = new JButton(new ImageIcon(((new ImageIcon("Point\\Up.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)))));
        buttonUp.setContentAreaFilled(false);
        buttonUp.setFocusPainted(false);
        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                train.MoveTransport(Direction.Up);
                panel.repaint();
            }
        });
        buttonUp.setBounds(1052, 528, 50, 50);
        frame.getContentPane().add(buttonUp);

        JButton buttonLeft = new JButton(new ImageIcon(((new ImageIcon("Point\\Left.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)))));
        buttonLeft.setContentAreaFilled(false);
        buttonLeft.setFocusPainted(false);
        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                train.MoveTransport(Direction.Left);
                panel.repaint();
            }
        });
        buttonLeft.setBounds(992, 589, 50, 50);
        frame.getContentPane().add(buttonLeft);

        JButton buttonCreateTrain = new JButton("Create Train");
        buttonCreateTrain.setFocusPainted(false);
        buttonCreateTrain.setContentAreaFilled(false);
        buttonCreateTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                train = new Train((int)(150 + Math.random() * 150), (float)(1500 + Math.random() * 500), Color.WHITE);
                train.SetPosition((int)(50 + Math.random() * 50), (int)(50 + Math.random() * 50), 1200, 700);
                panel = new PanelMonorail(train);
                panel.setBounds(0, 40, 1185, 485);
                frame.getContentPane().add(panel);
                panel.repaint();
            }
        });
        buttonCreateTrain.setBounds(20, 11, 130, 25);
        frame.getContentPane().add(buttonCreateTrain);

        JButton btnCreateMonorail = new JButton("Create Monorail");
        btnCreateMonorail.setFocusPainted(false);
        btnCreateMonorail.setContentAreaFilled(false);
        btnCreateMonorail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                train = new Monorail((int)(150 + Math.random() * 150), (float)(1500 + Math.random() * 500), Color.WHITE, Color.RED, true, true, true);
                train.SetPosition((int)(50 + Math.random() * 50), (int)(50 + Math.random() * 50), 1200, 700);
                panel = new PanelMonorail(train);
                panel.setBounds(0, 40, 1185, 485);
                frame.getContentPane().add(panel);
                panel.repaint();
            }
        });
        btnCreateMonorail.setBounds(171, 11, 130, 25);
        frame.getContentPane().add(btnCreateMonorail);
    }
}