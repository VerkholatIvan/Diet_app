import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class customise_daily_plan extends JPanel {

    String name="customise";
    protected App controller;

    protected std_label welcome_message;
    protected std_label calories;
    protected std_label fat;
    protected std_label sugar;
    protected std_label protein;

    protected double calories_value=0;
    protected double fat_value=0;
    protected double sugar_value=0;
    protected double protein_value=0;

    protected JPanel first_part;
    protected JPanel food_added;
    protected JPanel third_part;



    protected std_label breakfast;
    protected std_label lunch;
    protected std_label dinner;
    protected std_label snack;

    protected std_label breakfast_content;
    protected std_label lunch_content;
    protected std_label dinner_content;
    protected std_label snack_content;
    protected JTextField portion_;
    protected JComboBox<String> box;

    protected JTextField food_intake;

    protected std_button search;
    protected std_button add_;
    protected std_button cancel;
    protected std_button remove_;
    protected std_button submit;

    protected ArrayList<Food> breakfast_=new ArrayList<>();
    protected ArrayList<Food>lunch_=new ArrayList<>();
    protected ArrayList<Food>dinner_=new ArrayList<>();
    protected ArrayList<Food>snack_=new ArrayList<>();

    public customise_daily_plan(){};
    public customise_daily_plan(App controller){

        this.controller=controller;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.white);

        GridBagConstraints grid=new GridBagConstraints();

        first_part=new JPanel();
        first_part.setBackground(Color.white);
        first_part.setLayout(new GridBagLayout());

        food_added=new JPanel();
        food_added.setLayout(new GridBagLayout());
        food_added.setBackground(Color.gray);

        third_part=new JPanel();
        third_part.setLayout(new GridBagLayout());
        third_part.setBackground(Color.white);

        JPanel nutrition_info=new JPanel(new GridBagLayout());
        nutrition_info.setBackground(Color.white);

        welcome_message=new std_label("Customise your daily plan:",20);
        grid.gridx=0;
        grid.gridy=0;
        grid.insets=new Insets(10,10,10,10);
        first_part.add(welcome_message,grid);

        calories=new std_label("Total calories: None",12);
        grid.gridx=0;
        grid.gridy=0;
        nutrition_info.add(calories,grid);

        fat=new std_label("Total fat: None",12);
        grid.gridx=1;
        grid.gridy=0;
        nutrition_info.add(fat,grid);

        sugar=new std_label("Total sugar: None",12);
        grid.gridx=2;
        grid.gridy=0;
        nutrition_info.add(sugar,grid);

        protein=new std_label("Total protein: None",12);
        grid.gridx=3;
        grid.gridy=0;
       nutrition_info.add(protein,grid);


        breakfast_content=new std_label("Breakfast:",12);
        grid.gridx=0;
        grid.gridy=0;
        grid.anchor=GridBagConstraints.LINE_START;
        food_added.add(breakfast_content,grid);



        lunch_content=new std_label("Lunch:",12);
        grid.gridx=0;
        grid.gridy=1;
        food_added.add(lunch_content,grid);



        dinner_content=new std_label("Dinner:",12);
        grid.gridx=0;
        grid.gridy=2;
        food_added.add(dinner_content,grid);

        snack_content=new std_label("Snacks:",12);
        grid.gridx=0;
        grid.gridy=3;
        food_added.add(snack_content,grid);


        std_label food=new std_label("Food:",17);
        grid.gridx=0;
        grid.gridy=0;
        grid.anchor=GridBagConstraints.CENTER;

        grid.insets=new Insets(30,10,10,0);
        third_part.add(food,grid);

        food_intake=new JTextField(10);
        food_intake.setBorder(BorderFactory.createLineBorder(Color.black,1));
        food_intake.setFont(new Font("Avenir",Font.PLAIN,12));

        grid.gridx=1;
        grid.gridy=0;
        grid.fill = GridBagConstraints.HORIZONTAL;
        third_part.add(food_intake,grid);


        search=new std_button("Search",Color.white,Color.gray,150,20,15);
        search.addActionListener(controller.handler);
        grid.gridx=2;
        grid.gridy=0;
        third_part.add(search,grid);



        std_label portion=new std_label("Portion (g):",17);
        grid.gridx=3;
        grid.gridy=0;
        third_part.add(portion,grid);


        portion_=new JTextField(5);
        portion_.setBorder(BorderFactory.createLineBorder(Color.black,1));
        portion_.setFont(new Font("Avenir",Font.PLAIN,17));
        grid.gridx=4;
        grid.gridy=0;
        third_part.add(portion_,grid);



        String[]options={"Breakfast","Lunch","Dinner","Snacks"};
        box=new JComboBox<>(options);
        box.setFont(new Font("Avenir",Font.PLAIN,17));
        box.setBorder(BorderFactory.createLineBorder(Color.black,1));
        grid.gridx=5;
        grid.gridy=0;
        third_part.add(box,grid);


        add_=new std_button("Add",Color.white,Color.gray,150,20,15);
        add_.addActionListener(controller.handler);
        grid.gridx=0;
        grid.gridy=1;
        third_part.add(add_,grid);

        remove_=new std_button("Clear",Color.white,Color.gray,150,20,15);
        remove_.addActionListener(e->remove_food());
        grid.gridx=1;
        grid.gridy=1;
        third_part.add(remove_,grid);

        submit=new std_button("Set daily plan",Color.white,Color.gray,150,20,15);
        submit.addActionListener(controller.handler);
        grid.gridx=0;
        grid.gridy=2;
        grid.insets=new Insets(10,10,10,0);
        third_part.add(submit,grid);

        cancel=new std_button("Back",Color.white,Color.gray,120,20,15);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove_food();
                controller.switch_page("About me");
            }
        });
        grid.gridx=1;
        grid.gridy=2;
        third_part.add(cancel,grid);

        this.add(first_part);
        this.add(nutrition_info);
        this.add(food_added);
        this.add(third_part);

    }

    public void remove_food(){

        calories_value=0;
        calories.setText("Total calories: None");
        protein_value=0;
        protein.setText("Total protein: None");
        fat_value=0;
        fat.setText("Total fat: None");
        sugar_value=0;
        sugar.setText("Total sugar: None");
        breakfast_content.setText("Breakfast:");
        lunch_content.setText("Lunch:");
        dinner_content.setText("Dinner");
        snack_content.setText("Snack:");
        breakfast_.clear();
        lunch_.clear();
        dinner_.clear();
        snack_.clear();
        food_intake.setText("");
        portion_.setText("");

    }



}
