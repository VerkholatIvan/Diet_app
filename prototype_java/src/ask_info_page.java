import javax.swing.*;
import java.awt.*;

public class ask_info_page extends JPanel {
    String name="Ask_info_page";
    protected App controller;


    protected JRadioButton gender_male;
    protected JRadioButton gender_female;
    protected JTextField enter_age;
    protected JTextField enter_height;
    protected JTextField enter_weight;
    protected std_button Signup;
    protected std_button cancel;

    public ask_info_page(){};
    public ask_info_page(App controller){

        this.controller=controller;
        setLayout(new GridBagLayout());
        GridBagConstraints page_grid=new GridBagConstraints();
        setBackground(Color.WHITE);



        JPanel container_title=new JPanel(new BorderLayout());
        container_title.setBackground(Color.WHITE);

        std_label more=new std_label("Tell us a bit more about you:",17);
        more.setFont(new Font("Avenir",Font.ITALIC,17));
        container_title.add(more,BorderLayout.CENTER);
        page_grid.gridx=0;
        page_grid.gridy=0;
        page_grid.insets=new Insets(10,10,10,150);
        add(container_title,page_grid);


        JPanel container=new JPanel(new GridBagLayout());
        GridBagConstraints grid=new GridBagConstraints();
        container.setBackground(Color.WHITE);

        page_grid.gridx=0;
        page_grid.gridy=1;
        page_grid.insets=new Insets(40,10,10,10);
        add(container,page_grid);



        std_label gender=new std_label("Your biological Sex:",15);

        grid.gridx=0;
        grid.gridy=1;
        grid.insets=new Insets(45,10,10,10);
        container.add(gender,grid);

        gender_male=new JRadioButton("Male");
        gender_male.setFont(new Font("Avenir",Font.PLAIN,15));
        grid.gridx=1;
        grid.gridy=1;
        grid.insets=new Insets(45,0,10,3);
        container.add(gender_male,grid);



        gender_female=new JRadioButton("Female");
        gender_female.setFont(new Font("Avenir",Font.PLAIN,15));
        grid.gridx=2;
        grid.gridy=1;
        grid.insets=new Insets(45,3,10,10);
        container.add(gender_female,grid);

        ButtonGroup choose_gender=new ButtonGroup();
        choose_gender.add(gender_male);
        choose_gender.add(gender_female);

        std_label age=new std_label("Your age:",15);
        grid.gridx=0;
        grid.gridy=2;
        grid.insets=new Insets(10,10,10,10);
        container.add(age,grid);

        enter_age=new JTextField(5);
        enter_age.setBorder(BorderFactory.createLineBorder(Color.black,1));
        enter_age.setFont(new Font("Avenir",Font.PLAIN,15));
        grid.gridx=1;
        grid.gridy=2;
        grid.insets=new Insets(10,10,10,10);
        container.add(enter_age,grid);


        std_label Height=new std_label("Your Height (CM):",15);

        grid.gridx=0;
        grid.gridy=3;
        grid.insets=new Insets(10,10,10,10);
        container.add(Height,grid);

        enter_height=new JTextField(5);
        enter_height.setBorder(BorderFactory.createLineBorder(Color.black,1));
        enter_height.setFont(new Font("Avenir",Font.PLAIN,15));
        grid.gridx=1;
        grid.gridy=3;
        grid.insets=new Insets(10,10,10,10);
        container.add(enter_height,grid);

        std_label weight=new std_label("Your Weight (KG):",15);
        weight.setFont(new Font("Avenir",Font.PLAIN,15));
        grid.gridx=0;
        grid.gridy=4;
        grid.insets=new Insets(10,10,10,10);
        container.add(weight,grid);

        enter_weight=new JTextField(5);
        enter_weight.setBorder(BorderFactory.createLineBorder(Color.black,1));
        enter_weight.setFont(new Font("Avenir",Font.PLAIN,15));
        grid.gridx=1;
        grid.gridy=4;
        grid.insets=new Insets(10,10,10,10);
        container.add(enter_weight,grid);

        Signup=new std_button("Sign up",Color.white,Color.gray,115,20,14);
        Signup.addActionListener(controller.handler);
        grid.gridx=0;
        grid.gridy=5;
        grid.insets=new Insets(40,10,10,10);
        container.add(Signup,grid);

        cancel=new std_button("Back",Color.white,Color.gray,115,20,14);
        cancel.addActionListener(controller.handler);

        grid.gridx=1;
        grid.gridy=5;
        grid.insets=new Insets(40,10,10,10);
        container.add(cancel,grid);



    }

}
