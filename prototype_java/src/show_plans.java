import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class show_plans extends JPanel {

    protected App controller;
    protected String name="Show plans";
    protected ArrayList<daily_plan>plans;

    public show_plans(){};
    public show_plans(App controller){
        this.controller=controller;
    }

    public void initialise(int plan_topic,int operation) {
        // plan topic 1 stands for None (standard), 2 stands for high protein, 3 stands for low fat, 4 stands for low sugar, 5 stands for customised plan
        // operation 1 means select mode, 2 means manage mode.



        this.plans = new ArrayList<>();

        if (plan_topic == 1) plans = new ArrayList<>(controller.standard);
        else if (plan_topic == 2) plans = new ArrayList<>(controller.high_protein);
        else if (plan_topic == 3) plans = new ArrayList<>(controller.low_fat);
        else if (plan_topic == 4) plans = new ArrayList<>(controller.low_sugar);
        else if (plan_topic == 5) plans = new ArrayList<>(controller.user.customised_combo);



        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);
        GridBagConstraints grid_ = new GridBagConstraints();


        if (plans.isEmpty()) {

            std_label empty = new std_label("You have no daily plan yet.", 17);
            JPanel wrapper = new JPanel(new GridBagLayout());
            GridBagConstraints grid = new GridBagConstraints();
            wrapper.setBackground(Color.white);
            grid.gridx = 0;
            grid.gridy = 0;
            wrapper.add(empty, grid);

            std_button back = new std_button("Back", Color.white, Color.gray, 140, 30, 15);
            back.addActionListener(e -> {
                if (operation==1)controller.switch_page("Main_page");
                else controller.switch_page("About me");

            });
            grid.gridy = 1;
            wrapper.add(back, grid);
            grid_.gridx = 0;
            grid_.gridy = 0;
            this.add(wrapper, grid_);
        }

        else {

            int y_counter = 1;
            grid_.anchor=GridBagConstraints.LINE_START;
            grid_.gridx=0;
            grid_.gridy=0;
            this.add(Box.createRigidArea(new Dimension(100,50)),grid_);

            for (daily_plan i : plans) { // goes into each plan


                JPanel temp = new JPanel(new GridBagLayout());
                GridBagConstraints grid = new GridBagConstraints();
                grid.anchor = GridBagConstraints.LINE_START;
                temp.setBackground(Color.WHITE);




                std_label show_breakfast = new std_label("Breakfast: " + i.string_converter(i.breakfast), 12);
                grid.gridx = 0;
                grid.gridy = 0;
                temp.add(show_breakfast, grid);

                std_label show_lunch = new std_label("Lunch: " + i.string_converter(i.lunch), 12);
                grid.gridy = 1;
                temp.add(show_lunch, grid);

                std_label show_dinner = new std_label("Dinner: " + i.string_converter(i.dinner), 12);
                grid.gridy = 2;
                grid.insets = new Insets(0, 0, 0, 10);
                temp.add(show_dinner, grid);

                std_label show_snack = new std_label("Snack: " + i.string_converter(i.snack), 12);
                grid.gridy = 3;
                temp.add(show_snack, grid);

                String info = "Total calories: " + String.format("%.2f", i.calories) + "Kcal, " + "Total protein: " + String.format("%.2f", i.protein) + "g, " + "Total fat: " + String.format("%.2f", i.fat) + "g, " + "Total sugar: " + String.format("%.2f", i.sugar) + "g.";

                std_label total_info = new std_label(info, 12);
                grid.gridy = 4;
                temp.add(total_info, grid);


                if (operation == 1) {

                    std_button select = new std_button("Select", Color.white, Color.gray, 140, 30, 15);

                    select.addActionListener(e -> {
                        controller.main_page.remove();

                        for (Food j:i.breakfast){

                            controller.main_page.add_food(j,"Breakfast");

                        }
                        for (Food j:i.lunch){
                            controller.main_page.add_food(j,"Lunch");

                        }
                        for (Food j:i.dinner){
                            controller.main_page.add_food(j,"Dinner");

                        }
                        for (Food j:i.snack){
                            controller.main_page.add_food(j,"Snacks");
                        }

                        //System.out.println(example);

                        controller.main_page.renew_labels();

                        controller.switch_page("Main_page");


                    });
                    grid.gridx = 0;
                    grid.gridy = 5;
                    temp.add(select, grid);
                } else {

                    std_button remove = new std_button("Remove", Color.white, Color.gray, 140, 30, 15);
                    int index=y_counter-1;
                    remove.addActionListener(e -> {

                        controller.user.remove_plan(index);
                        JOptionPane.showMessageDialog(null, "Daily plan removed!", null, JOptionPane.INFORMATION_MESSAGE);
                        controller.switch_page("About me");

                    });

                    grid.gridx = 0;
                    grid.gridy = 5;
                    temp.add(remove, grid);

                }

                temp.setMaximumSize(new Dimension(temp.getPreferredSize()));

                grid_.gridx = 0;
                grid_.gridy = y_counter;
                grid_.insets = new Insets(0, 0, 40, 0);
                this.add(temp, grid_);

                y_counter++;


            }

            JPanel wrapper = new JPanel(new GridBagLayout());
            GridBagConstraints grid = new GridBagConstraints();
            wrapper.setBackground(Color.white);

            std_button back = new std_button("Cancel", Color.white, Color.gray, 140, 30, 15);
            back.addActionListener(e -> {
                if(operation==1) controller.switch_page("Main_page");
                else controller.switch_page("About me");

            });

            grid.gridx = 0;
            grid.gridy = 0;

            wrapper.add(back, grid);
            grid.gridy = 1;
            wrapper.add(Box.createRigidArea(new Dimension(100,50)),grid); // add a gap


            grid_.gridx = 0;
            grid_.gridy = y_counter + 1;
            grid_.anchor=GridBagConstraints.CENTER;
            grid_.insets=new Insets(20,0,0,0);
            this.add(wrapper, grid_);



        }

    }


}
