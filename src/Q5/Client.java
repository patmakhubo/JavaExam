package Q5;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public final class Client extends JFrame implements ActionListener {

    public Client() {
        super("PIHE Registration App");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createPanel();
        this.getContentPane().add(myPanel);
    }
    //required components 
    JComboBox cboDegree = new JComboBox();
    JButton btnRegister;
    JTextField txtID = new JTextField();
    JTextField txtName = new JTextField();
    JTextField txtSurname = new JTextField();
    JTextField txtAge = new JTextField();
    JTextField txtCell = new JTextField();
    JTextField txtDegree = new JTextField();
    JLabel labeltop = new JLabel("PIHE Registration App v1.0");
    JLabel labeltop2 = new JLabel("Developer: T.P Makhubo");
    JLabel lblIDNumber = new JLabel("ID Number:");
    JLabel lblName = new JLabel("First Name:");
    JLabel lblSurname = new JLabel("Surname:");
    JLabel lblAge = new JLabel("Age:");
    JLabel lblCell = new JLabel("Cell Number:");
    JLabel lblDegree = new JLabel("Select Degree:");
    JLabel result = new JLabel();
    JPanel myPanel = new JPanel();
    //create Panel 

    public void createPanel() {
        myPanel.setLayout(new GridLayout(8, 2));
        //symbol 
        String degrees[] = {
            "BSC IT",
            "BSc Computer Science",
            "Higher Certificate in IT",
            "BSc Biomedicine",
            "Bachelor of Commerce",
            "Bachelor of Arts"
        };
        cboDegree = new JComboBox(degrees);
        ((JLabel) cboDegree.getRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        btnRegister = new JButton("Register");
        //adding components to panel 
        myPanel.add(labeltop);
        myPanel.add(labeltop2);
        myPanel.add(lblIDNumber);
        myPanel.add(txtID);
        myPanel.add(lblName);
        myPanel.add(txtName);
        myPanel.add(lblSurname);
        myPanel.add(txtSurname);
        myPanel.add(lblAge);
        myPanel.add(txtAge);
        myPanel.add(lblCell);
        myPanel.add(txtCell);
        myPanel.add(lblDegree);
        myPanel.add(cboDegree);
        myPanel.add(btnRegister);

        add(myPanel);
        btnRegister.addActionListener((ActionListener) this);
    }

    public static void main(String args[]) throws RemoteException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Client c = new Client();
                c.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //handling empty textfields 
        if ("".equals(txtID.getText())
                || "".equals(txtName.getText())
                || "".equals(txtAge.getText())
                || "".equals(txtCell.getText())
                || "".equals(txtSurname.getText())) {
            JOptionPane.showMessageDialog(null, "One or more fields are empty",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String degree = cboDegree.getSelectedItem().toString();
            int id = Integer.parseInt(txtID.getText());
            String name = txtName.getText();
            String surname = txtSurname.getText();
            int age = Integer.parseInt(txtAge.getText());
            int cell = Integer.parseInt(txtCell.getText());
            try {
                //locating remote object and registering it to RMI 
                Registry reg = LocateRegistry.getRegistry();
                ConnectInterface cal = (ConnectInterface) reg.lookup("Hello");
                cal.insert(id, name, surname, age, cell, degree);
                JOptionPane.showMessageDialog(null,
                        "Student has been registered successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception f) {
                System.out.println("Exception: " + f);
            }
        }
    }
}
