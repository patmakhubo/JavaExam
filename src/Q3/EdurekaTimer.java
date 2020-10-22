package Q3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public final class EdurekaTimer extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel lblTime;
    private JTextField txtTime;
    private JButton btnStart, btnStop;
    private Stopwatch sw;
    private Thread t1;

    public void createPanel() {
        this.panel = new JPanel();
        this.lblTime = new JLabel("Time (in seconds)");
        this.txtTime = new JTextField(10);
        this.btnStart = new JButton("Start Timer");
        this.btnStop = new JButton("Stop Timer");
        this.t1 = null;
        this.sw = new Stopwatch(t1);

        txtTime.setEditable(false);
        txtTime.setText("0");

        Box topComponents = Box.createHorizontalBox();
        topComponents.add(lblTime);
        topComponents.add(Box.createHorizontalStrut(10));
        topComponents.add(txtTime);

        Box bottomComponents = Box.createHorizontalBox();
        bottomComponents.add(btnStart);
        bottomComponents.add(Box.createHorizontalStrut(5));
        bottomComponents.add(btnStop);

        Box groupComponents = Box.createVerticalBox();
        groupComponents.add(Box.createVerticalStrut(10));
        groupComponents.add(topComponents);
        groupComponents.add(Box.createVerticalStrut(10));
        groupComponents.add(bottomComponents);
        this.panel.add(groupComponents);

        btnStart.addActionListener(this);
        btnStop.addActionListener(this);
    }

    public EdurekaTimer() {
        super("Edureka Timer");
        this.setVisible(true);
        this.setSize(300, 125);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.createPanel();
        this.getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btnStart == e.getSource()) {
            sw.isTicking(true);
        }
        if (btnStop == e.getSource()) {
            sw.isTicking(false);
        }
    }

    private final class Stopwatch implements Runnable {

        private Thread t1;
        private volatile boolean ticking = false;

        public Stopwatch(Thread t1) {
            this.t1 = t1;
        }

        @Override
        public void run() {
            synchronized (this) {
                while (ticking) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("Failed to sleep");
                    }
                    if (ticking) {
                        int time = Integer.parseInt(txtTime.getText().trim()) + 1;
                        txtTime.setText(time + "");
                    }
                }
            }
        }

        public void isTicking(boolean ticking) {
            this.ticking = ticking;
            if (ticking) {
                if (t1 == null) {
                    this.t1 = new Thread(sw);
                    t1.start();
                } else {
                    t1 = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EdurekaTimer et = new EdurekaTimer();
            }
        });
    }
}
