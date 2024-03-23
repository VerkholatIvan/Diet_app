import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class sign_up_page extends JPanel {
    public String name="Sign_up_page";

    protected App controller;


    protected JPasswordField input_password;

    protected JPasswordField sec_input_password;

    protected JTextField input_name;
//    private String password;
//    private String re_password;

    protected std_button next;
    protected std_button back;


    public sign_up_page() {}

    public sign_up_page(App controller){

        this.controller=controller;

        setLayout(new GridBagLayout());
        GridBagConstraints grid=new GridBagConstraints();

        setBackground(Color.WHITE);

        std_label username=new std_label("Username (5-16 Characters, No symbols):",17);

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

        std_label password=new std_label("Set password (8-16 Characters):",17);

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

        std_label repeat_password=new std_label("Re-enter password:",17);

        grid.gridx=0;
        grid.gridy=2;
        add(repeat_password,grid);

        sec_input_password=new JPasswordField(10);
        sec_input_password.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        sec_input_password.setFont(new Font("Avenir",Font.PLAIN,17));
        sec_input_password.setEchoChar('*');
        grid.gridx=1;
        grid.gridy=2;
        grid.insets=new Insets(10,10,10,10);
        add(sec_input_password,grid);

        next=new std_button("Next",Color.white,Color.gray,115,20,14);
        next.addActionListener(controller.handler);
        grid.gridx=0;
        grid.gridy=3;
        grid.insets=new Insets(25,10,10,10);
        add(next,grid);

        back=new std_button("Back",Color.white,Color.gray,115,20,14);
        back.addActionListener(controller.handler);

        grid.gridx=1;
        grid.gridy=3;
        grid.insets=new Insets(25,10,10,10);
        add(back,grid);



    }


}
