package nbradham.sbr;

import java.awt.AWTException;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

final class Racer {

	private static final short DEF_DB2X = 754;

	private final Robot r;

	private short db1x = 656, db1y = 1020, dsubY = 1094;
	private int diff = DEF_DB2X - db1x;

	private Racer() throws AWTException {
		r = new Robot();
	}

	private void start() {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Race Destroyer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new GridLayout(0, 2));
			frame.add(new JLabel("128 Bit X: ", SwingConstants.RIGHT));
			JTextField b1x = new JTextField(Short.toString(db1x), 3), b1y = new JTextField(Short.toString(db1y), 3),
					b2x = new JTextField(String.valueOf(DEF_DB2X), 3), subY = new JTextField(Short.toString(dsubY), 3),
					val = new JTextField("0", 3);
			b1x.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					db1x = Short.parseShort(b1x.getText());
				}
			});
			b1y.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					db1y = Short.parseShort(b1y.getText());
				}
			});
			b2x.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					diff = Short.parseShort(b2x.getText()) - db1x;
				}
			});
			subY.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					dsubY = Short.parseShort(subY.getText());
				}
			});
			frame.add(b1x);
			frame.add(new JLabel("128 Bit Y: ", SwingConstants.RIGHT));
			frame.add(b1y);
			frame.add(new JLabel("64 Bit X: ", SwingConstants.RIGHT));
			frame.add(b2x);
			frame.add(new JLabel("Submit Y: ", SwingConstants.RIGHT));
			frame.add(subY);
			frame.add(new JLabel("Value: ", SwingConstants.RIGHT));
			val.requestFocus();
			val.selectAll();
			frame.add(val);
			frame.add(new JLabel("Send to Game: ", SwingConstants.RIGHT));
			JButton go = new JButton("Submit");
			go.addActionListener(e -> {
				short i = Short.parseShort(val.getText());
				for (byte n = 7; n > -1; --n) {
					if ((i >> n & 1) == 1) {
						r.mouseMove(db1x + diff * (7 - n), db1y);
						try {
							Thread.sleep(1);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						click();
					}
				}
				r.mouseMove(db1x + diff * 4, dsubY);
				click();
				val.requestFocus();
				val.selectAll();
			});
			frame.add(go);
			frame.getRootPane().setDefaultButton(go);
			frame.pack();
			frame.setSize(frame.getWidth() + 10, frame.getHeight());
			frame.setVisible(true);
		});
	}

	private void click() {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	public static void main(String[] args) throws AWTException {
		new Racer().start();
	}
}