import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FormMonorail {

	private JFrame frame;
	private Monorail monorail;
	private PanelMonorail panel;

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

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton buttonRight = new JButton(new ImageIcon(((new ImageIcon(
				"Point\\Right.png").getImage()
	            .getScaledInstance(40, 40,
	                    java.awt.Image.SCALE_SMOOTH)))));
		buttonRight.setContentAreaFilled(false);
		buttonRight.setFocusPainted(false);
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monorail.MoveTransport(Direction.Right);
				panel.repaint();
			}
		});
		buttonRight.setBounds(1112, 589, 50, 50);
		frame.getContentPane().add(buttonRight);

		JButton buttonDown = new JButton(new ImageIcon(((new ImageIcon(
				"Point\\Down.png").getImage()
	            .getScaledInstance(40, 40,
	                    java.awt.Image.SCALE_SMOOTH)))));
		buttonDown.setContentAreaFilled(false);
		buttonDown.setFocusPainted(false);
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monorail.MoveTransport(Direction.Down);
				panel.repaint();
			}
		});
		buttonDown.setBounds(1052, 589, 50, 50);
		frame.getContentPane().add(buttonDown);
		
		JButton buttonUp = new JButton(new ImageIcon(((new ImageIcon(
				"Point\\Up.png").getImage()
	            .getScaledInstance(40, 40,
	                    java.awt.Image.SCALE_SMOOTH)))));
		buttonUp.setContentAreaFilled(false);
		buttonUp.setFocusPainted(false);
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monorail.MoveTransport(Direction.Up);
				panel.repaint();
			}
		});
		buttonUp.setBounds(1052, 528, 50, 50);
		frame.getContentPane().add(buttonUp);

		JButton buttonLeft = new JButton(new ImageIcon(((new ImageIcon(
				"Point\\Left.png").getImage()
	            .getScaledInstance(40, 40,
	                    java.awt.Image.SCALE_SMOOTH)))));
		buttonLeft.setContentAreaFilled(false);
		buttonLeft.setFocusPainted(false);
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monorail.MoveTransport(Direction.Left);
				panel.repaint();
			}
		});
		buttonLeft.setBounds(992, 589, 50, 50);
		frame.getContentPane().add(buttonLeft);

		JButton buttonCreate = new JButton("Create");
		buttonCreate.setFocusPainted(false);
		buttonCreate.setContentAreaFilled(false);
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monorail = new Monorail((int)(150 + Math.random() * 150), (float)(1500 + Math.random() * 500), Color.WHITE,
						Color.CYAN, true, true, true);
				monorail.SetPosition((int)(50 + Math.random() * 50), (int)(50 + Math.random() * 50), 1185, 485);
				panel = new PanelMonorail(monorail);
				panel.setBounds(0, 40, 1185, 485);
				frame.getContentPane().add(panel);
				panel.repaint();
			}
		});
		buttonCreate.setBounds(10, 11, 90, 25);
		frame.getContentPane().add(buttonCreate);
	}
}
