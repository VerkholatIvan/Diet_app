import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class login_page extends JPanel {

    public String name="Login_page";

    protected App controller;

    protected JTextField input_name;
    protected JPasswordField input_password;
    protected std_button confirm;
    protected std_button cancel;



    public login_page(){};
    public login_page(App controller){


        this.controller=controller;
        setLayout(new GridBagLayout());
        GridBagConstraints grid=new GridBagConstraints();

        setBackground(Color.WHITE);

        std_label username=new std_label("Username:",17);
        grid.gridx=0;
        grid.gridy=0;
        grid.insets=new Insets(10,10,10,10);
        add(username,grid);

        input_name=new JTextField(10);
        input_name.setBorder(BorderFactory.createLineBorder(Color.black,1));
        input_name.setFont(new Font("Avenir",Font.PLAIN,17));
        grid.gridx=1;
        grid.gridy=0;
        grid.insets=new Insets(10,10,10,10);
        add(input_name,grid);

        std_label password=new std_label("Password:",17);

        grid.gridx=0;
        grid.gridy=1;
        add(password,grid);


        input_password=new JPasswordField(10);
        input_password.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        input_password.setFont(new Font("Avenir",Font.PLAIN,17));
        input_password.setEchoChar('*');
        grid.gridx=1;
        grid.gridy=1;
        grid.insets=new Insets(10,10,10,10);
        add(input_password,grid);

        confirm=new std_button("Confirm",Color.white,Color.gray,115,20,14);
        confirm.addActionListener(controller.handler);
        grid.gridx=0;
        grid.gridy=2;
        grid.insets=new Insets(40,50,10,10);
        add(confirm,grid);

        cancel=new std_button("Back",Color.white,Color.gray,115,20,14);
        cancel.addActionListener(controller.handler);
        grid.gridx=1;
        grid.gridy=2;
        grid.insets=new Insets(40,30,10,10);
        add(cancel,grid);



    }
}