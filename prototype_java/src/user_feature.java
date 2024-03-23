import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class user_feature extends JPanel{
    App controller;
    protected String name="About me";
    protected std_label goal;
    protected JComboBox<String> goal_box;
    protected std_button set_plan;
    protected std_button remove_plan;
    protected std_label height;
    protected std_label age;
    protected std_label weight;
    protected std_button back;

    protected std_button edit_age;
    protected JTextField input_age;
    protected std_button submit_age;
    protected std_button cancel_age;

    protected std_button edit_height;
    protected JTextField input_height;
    protected std_button submit_height;
    protected std_button cancel_height;

    protected std_button edit_weight;
    protected JTextField input_weight;
    protected std_button submit_weight;
    protected std_button cancel_weight;

    public user_feature(){};
    public user_feature(App controller){


        this.controller=controller;
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel first_part=new JPanel(new GridBagLayout());
        first_part.setBackground(Color.white);
        JPanel second_part=new JPanel(new GridBagLayout());
        second_part.setBackground(Color.white);
        JPanel third_part=new JPanel(new GridBagLayout());
        third_part.setBackground(Color.white);
        JPanel last_part=new JPanel(new GridBagLayout());
        last_part.setBackground(Color.white);
        JPanel forth_part=new JPanel(new GridBagLayout());
        forth_part.setBackground(Color.white);



        GridBagConstraints grid=new GridBagConstraints();

        goal=new std_label("Your current goal: ",17);
        grid.gridx=0;
        grid.gridy=0;
        //grid.anchor=GridBagConstraints.WEST;
        grid.insets=new Insets(10,10,10,10);
        first_part.add(goal,grid);

        String[] goals={"None","High protein", "Low fat", "Low sugar"};

        goal_box=new JComboBox<>(goals);
        goal_box.setSelectedItem(controller.user.goal);
        goal_box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.user.goal=(String) goal_box.getSelectedItem();
                //if renew user document here, since this action event will be performed when compiler is building this page, the writer will create a file for user "NULL"

            }

        });
        goal_box.setFont(new Font("Avenir",Font.PLAIN,14));
        goal_box.setBorder(BorderFactory.createLineBorder(Color.black,1));
        goal_box.setSelectedItem(controller.user.goal);
        grid.gridx=1;
        grid.gridy=0;
        first_part.add(goal_box,grid);

        set_plan=new std_button("Add daily plan",Color.white,Color.gray,140,30,15);
        set_plan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.switch_page("customise");
            }
        });
        grid.gridx=0;
        grid.gridy=1;
        first_part.add(set_plan,grid);

        remove_plan=new std_button("Manage daily plan",Color.white,Color.gray,140,30,15);
        remove_plan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.show_customised_plans.removeAll();
                controller.show_customised_plans.initialise(5,2);
                controller.switch_page("Show plans");

            }
        });
        grid.gridx=1;
        grid.gridy=1;
        first_part.add(remove_plan,grid);

        age=new std_label("",15);
        grid.gridx=0;
        grid.gridy=0;
        second_part.add(age,grid);

        edit_age=new std_button("Edit",Color.white,Color.gray,60,30,15);
        edit_age.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit_age.setVisible(true);
                cancel_age.setVisible(true);
                input_age.setVisible(true);
                edit_age.setVisible(false);
                age.setVisible(false);
            }
        });
        grid.gridx=1;
        grid.gridy=0;
        second_part.add(edit_age,grid);

        input_age=new JTextField(6);
        input_age.setBorder(BorderFactory.createLineBorder(Color.black,1));
        input_age.setFont(new Font("Avenir",Font.PLAIN,17));
        grid.gridx=2;
        grid.gridy=0;
        second_part.add(input_age,grid);
        input_age.setVisible(false);

        submit_age=new std_button("Submit",Color.white,Color.gray,60,30,15);
        submit_age.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean result=reset_age();
                if (!result) {
                    submit_age.setText("");
                }
                else {
                    submit_age.setVisible(false);
                    cancel_age.setVisible(false);
                    input_age.setText("");
                    input_age.setVisible(false);
                    edit_age.setVisible(true);
                    age.setVisible(true);
                }
            }
        });
        grid.gridx=3;
        grid.gridy=0;
        second_part.add(submit_age,grid);
        submit_age.setVisible(false);

        cancel_age=new std_button("Cancel",Color.white,Color.gray,60,30,15);
        cancel_age.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit_age.setVisible(false);
                cancel_age.setVisible(false);
                input_age.setText("");
                input_age.setVisible(false);
                edit_age.setVisible(true);
                age.setVisible(true);
            }
        });
        grid.gridx=4;
        grid.gridy=0;
        second_part.add(cancel_age,grid);
        cancel_age.setVisible(false);




        height=new std_label("",15);
        grid.gridx=0;
        grid.gridy=0;
        third_part.add(height,grid);

        edit_height=new std_button("Edit",Color.white,Color.gray,60,30,15);
        edit_height.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit_height.setVisible(true);
                cancel_height.setVisible(true);
                input_height.setVisible(true);
                edit_height.setVisible(false);
                height.setVisible(false);
            }
        });
        grid.gridx=1;
        grid.gridy=0;
        third_part.add(edit_height,grid);

        input_height=new JTextField(6);
        input_height.setBorder(BorderFactory.createLineBorder(Color.black,1));
        input_height.setFont(new Font("Avenir",Font.PLAIN,17));
        grid.gridx=2;
        grid.gridy=0;
        third_part.add(input_height,grid);
        input_height.setVisible(false);

        submit_height=new std_button("Submit",Color.white,Color.gray,60,30,15);
        submit_height.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean result=reset_height();
                if (!result) {
                    submit_height.setText("");
                }
                else {
                    submit_height.setVisible(false);
                    cancel_height.setVisible(false);
                    input_height.setText("");
                    input_height.setVisible(false);
                    edit_height.setVisible(true);
                    height.setVisible(true);
                }
            }
        });
        grid.gridx=3;
        grid.gridy=0;
        third_part.add(submit_height,grid);
        submit_height.setVisible(false);

        cancel_height=new std_button("Cancel",Color.white,Color.gray,60,30,15);
        cancel_height.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit_height.setVisible(false);
                cancel_height.setVisible(false);
                input_height.setText("");
                input_height.setVisible(false);
                edit_height.setVisible(true);
                height.setVisible(true);
            }
        });
        grid.gridx=4;
        grid.gridy=0;
        third_part.add(cancel_height,grid);
        cancel_height.setVisible(false);


        weight=new std_label("",15);
        grid.gridx=0;
        grid.gridy=0;
        forth_part.add(weight,grid);

        edit_weight=new std_button("Edit",Color.white,Color.gray,60,30,15);
        edit_weight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit_weight.setVisible(true);
                cancel_weight.setVisible(true);
                input_weight.setVisible(true);
                edit_weight.setVisible(false);
                weight.setVisible(false);
            }
        });
        grid.gridx=1;
        grid.gridy=0;
        forth_part.add(edit_weight,grid);

        input_weight=new JTextField(6);
        input_weight.setBorder(BorderFactory.createLineBorder(Color.black,1));
        input_weight.setFont(new Font("Avenir",Font.PLAIN,17));
        grid.gridx=2;
        grid.gridy=0;
        forth_part.add(input_weight,grid);
        input_weight.setVisible(false);

        submit_weight=new std_button("Submit",Color.white,Color.gray,60,30,15);
        submit_weight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean result=reset_weight();
                if (!result) {
                    submit_weight.setText("");
                }
                else {
                    submit_weight.setVisible(false);
                    cancel_weight.setVisible(false);
                    input_weight.setText("");
                    input_weight.setVisible(false);
                    edit_weight.setVisible(true);
                    weight.setVisible(true);
                }
            }
        });
        grid.gridx=3;
        grid.gridy=0;
        forth_part.add(submit_weight,grid);
        submit_weight.setVisible(false);

        cancel_weight=new std_button("Cancel",Color.white,Color.gray,60,30,15);
        cancel_weight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit_weight.setVisible(false);
                cancel_weight.setVisible(false);
                input_weight.setText("");
                input_weight.setVisible(false);
                edit_weight.setVisible(true);
                weight.setVisible(true);
            }
        });
        grid.gridx=4;
        grid.gridy=0;
        forth_part.add(cancel_weight,grid);
        cancel_weight.setVisible(false);


        back=new std_button("Back",Color.white,Color.gray,140,30,15);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.user.write_daily_plan(); // make sure it records changes
                controller.user.set_cap();
                controller.main_page.current_goal.setText("Dietary goal: "+controller.user.goal);
                controller.user.refresh_Goal_advice();
                controller.main_page.advice.setText(controller.user.goal_advice);
                controller.switch_page("Main_page");

            }
        });
        grid.gridx=0;
        grid.gridy=0;

        grid.insets=new Insets(30,0,0,0);
        last_part.add(back,grid);

        second_part.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));
        third_part.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));
        forth_part.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));
        this.add(first_part);
        this.add(second_part);
        this.add(third_part);
        this.add(forth_part);
        this.add(last_part);


    }

    public void refresh_user_info(){
        this.age.setText("Your age: "+controller.user.age);
        this.height.setText("Your height: "+controller.user.height);
        this.weight.setText("Your weight: "+controller.user.weight);
        this.goal_box.setSelectedItem(controller.user.goal);
        controller.main_page.renew_labels();
        controller.main_page.current_goal.setText("Dietary goal: "+controller.user.goal);
    }

    public boolean reset_age(){

        try{
            Integer.parseInt(input_age.getText());
        }
        catch(NumberFormatException error){
            JOptionPane.showMessageDialog(null,"Invalid input!",null,JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int new_age=Integer.parseInt(input_age.getText());

        if (new_age<3 || new_age>150){
            JOptionPane.showMessageDialog(null,"Input out of range!",null,JOptionPane.ERROR_MESSAGE);
            return false;
        }

        controller.user.age=new_age;
        controller.user.re_calculate();
        this.refresh_user_info();

        return true;


    }

    public boolean reset_height(){

        try{
            Integer.parseInt(input_height.getText());
        }
        catch(NumberFormatException error){
            JOptionPane.showMessageDialog(null,"Invalid input!",null,JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int new_height=Integer.parseInt(input_height.getText());

        if (new_height<70 || new_height>250){
            JOptionPane.showMessageDialog(null,"Input out of range!",null,JOptionPane.ERROR_MESSAGE);
            return false;
        }

        controller.user.height=new_height;
        controller.user.re_calculate();
        this.refresh_user_info();

        return true;

    }



    public boolean reset_weight(){

        try{
            Integer.parseInt(input_weight.getText());
        }
        catch(NumberFormatException error){
            JOptionPane.showMessageDialog(null,"Invalid input!",null,JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int new_weight=Integer.parseInt(input_weight.getText());

        if (new_weight>635||new_weight<17){
            JOptionPane.showMessageDialog(null,"Input out of range!",null,JOptionPane.ERROR_MESSAGE);
            return false;
        }

        controller.user.weight=new_weight;
        controller.user.re_calculate();
        this.refresh_user_info();

        return true;

    }






}
