import javax.swing.*;
import java.awt.*;

public class welcome_page extends JPanel {

    public String name="Welcome_page";

    protected App controller;

    protected std_button sign_up;
    protected std_button login;


    public welcome_page(){};
    public welcome_page(App controller){

        this.controller=controller;

        setLayout(new BorderLayout());
        setBackground(Color.white);
        JPanel container=new JPanel(new GridBagLayout());
        container.setBackground(Color.white);
        GridBagConstraints grid=new GridBagConstraints();
        grid.anchor=GridBagConstraints.WEST;


        login=new std_button("Login",Color.white,Color.GRAY,160,60,17);
        login.addActionListener(controller.handler);

        grid.gridx=0;
        grid.gridy=0;
        grid.insets=new Insets(10,300,10,10);


        ImageIcon background=new ImageIcon("background.jpg");
        Image background_pic=background.getImage();//turn it into image and edit size
        Image newBg=background_pic.getScaledInstance(400,800,Image.SCALE_SMOOTH);
        ImageIcon newBackground=new ImageIcon(newBg);

        JLabel bgpic=new JLabel(newBackground);
        add(bgpic,BorderLayout.EAST);


        container.add(login,grid);

        sign_up=new std_button("Sign up",Color.white,Color.gray,160,60,17);
        sign_up.addActionListener(controller.handler);

        grid.gridx=0;
        grid.gridy=1;
        grid.insets=new Insets(20,300,10,10);
        container.add(sign_up,grid);
        add(container,BorderLayout.WEST);

    }




}