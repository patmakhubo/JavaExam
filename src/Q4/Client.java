package Q4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public final class Client extends JFrame implements ActionListener {

    private DataOutputStream dos;
    private final JLabel lblTitle;
    private final JTextField txtMessage;
    private final JTextArea taMessageDisplay;
    private final JButton btnSend;
    private final JButton btnExit;

    public Client() throws IOException {
        lblTitle = new JLabel("Client Chat");
        txtMessage = new JTextField(15);
        taMessageDisplay = new JTextArea(15, 20);
        btnSend = new JButton("Send");
        btnExit = new JButton("Exit");
        taMessageDisplay.setEditable(false);

        Box topComponents = Box.createHorizontalBox();
        topComponents.add(lblTitle);
        topComponents.add(Box.createHorizontalGlue());

        Box centreLeftComponents = Box.createHorizontalBox();
        centreLeftComponents.add(txtMessage);
        centreLeftComponents.add(Box.createHorizontalStrut(5));
        centreLeftComponents.add(btnSend);
        centreLeftComponents.add(Box.createHorizontalGlue());
        Box centreRightComponents = Box.createHorizontalBox();
        centreRightComponents.add(new JScrollPane(taMessageDisplay));

        JPanel centreComponents = new JPanel(new FlowLayout());
        centreComponents.add(centreLeftComponents);
        centreComponents.add(centreRightComponents);

        Box bottomComponents = Box.createHorizontalBox();
        bottomComponents.add(btnExit);

        Box groupComponents = Box.createVerticalBox();
        groupComponents.add(Box.createVerticalStrut(10));
        groupComponents.add(topComponents);
        groupComponents.add(Box.createVerticalStrut(10));
        groupComponents.add(centreComponents);
        groupComponents.add(Box.createVerticalStrut(10));
        groupComponents.add(bottomComponents);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(groupComponents);
        btnExit.addActionListener(this);
        btnSend.addActionListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectToServer();
            }
        }).start();

    }

    public void connectToServer() {
        try {
            Socket mysocket = new Socket("localhost", 9000);
            DataInputStream dis = new DataInputStream(mysocket.getInputStream());
            dos = new DataOutputStream(mysocket.getOutputStream());
            while (true) {
                String msg = dis.readUTF();
                Date time = new Date();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                String timestamp = df.format(time);
                String sb = taMessageDisplay.getText() + timestamp + " Server: " + msg;
                taMessageDisplay.setText(sb + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btnSend == e.getSource()) {
            try {
                dos.writeUTF(txtMessage.getText());
                Date time = new Date();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                String timestamp = df.format(time);
                String sb = taMessageDisplay.getText() + timestamp + " Client: " + txtMessage.getText();
                taMessageDisplay.setText(sb + "\n");
                txtMessage.setText("");
            } catch (IOException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
        if (btnExit == e.getSource()) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Client server = new Client();
                    server.setTitle("Client Chat");
                    server.setSize(600, 400);
                    server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    server.setVisible(true);
                    server.setResizable(false);
                } catch (IOException ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        });
    }
}
