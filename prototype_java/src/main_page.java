import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Vector;

public class main_page extends JPanel {
    String name="Main_page";
    protected App controller;
    protected double intake_per_day;

    protected JPanel first_part;
    protected JPanel food_added;
    protected JPanel third_part;
    protected JPanel report_part;

    protected std_label welcome_message;

    protected std_button check_feature;
    protected std_label current_goal;
    protected std_label advice;
    protected std_label calories;

    protected std_label protein;
    protected std_label fat;
    protected std_label sugar;

    protected double calories_counter=0;
    protected double protein_counter=0;
    protected double fat_counter=0;
    protected double sugar_counter=0;



    protected std_label breakfast;
    protected std_label lunch;
    protected std_label dinner;
    protected std_label snack;

    protected std_label breakfast_content;
    protected std_label lunch_content;
    protected std_label dinner_content;
    protected std_label snack_content;


    protected JTextField food_intake;

   protected JTextField portion_;

    protected JComboBox<String>box;

    protected ArrayList<Food> breakfast_=new ArrayList<>();
    protected ArrayList<Food>lunch_=new ArrayList<>();
    protected ArrayList<Food>dinner_=new ArrayList<>();
    protected ArrayList<Food>snack_=new ArrayList<>();

    protected double weekly_intake;
    protected double average_intake;


    protected double average;

    protected std_button log_out;
    protected std_button search;

    protected std_button add_;
    protected std_button submit;



    public main_page(){}


    public main_page(App controller){ // main page menu



        this.controller=controller;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.white);

        first_part=new JPanel();
        first_part.setLayout(new GridBagLayout());
        GridBagConstraints grid=new GridBagConstraints();
        first_part.setBackground(Color.white);

        JPanel nutrition_info=new JPanel(new GridBagLayout());
        nutrition_info.setBackground(Color.white);

        JPanel label_part=new JPanel(new GridBagLayout());
        label_part.setBackground(Color.white);



        food_added=new JPanel();
        food_added.setLayout(new GridBagLayout());
        food_added.setBackground(Color.gray);
        GridBagConstraints grid_food=new GridBagConstraints();

        third_part=new JPanel();
        third_part.setLayout(new GridBagLayout());
        third_part.setBackground(Color.white);

        report_part=new JPanel();
        report_part.setLayout(new GridBagLayout());
        report_part.setBackground(Color.white);



        welcome_message=new std_label("Welcome, ",17);
        grid.gridx=0;
        grid.gridy=0;
        //grid.anchor=GridBagConstraints.WEST;
        grid.insets=new Insets(10,10,10,10);
        first_part.add(welcome_message,grid);

        log_out=new std_button("Log out",Color.white,Color.gray,115,20,14);
        log_out.addActionListener(controller.handler);
        grid.gridx=1;
        grid.gridy=0;
        grid.insets=new Insets(10,10,10,10);
        first_part.add(log_out,grid);

        check_feature=new std_button("About me",Color.white,Color.gray,115,20,14);
        check_feature.addActionListener(e -> {
            controller.about_me_page.refresh_user_info();
            controller.switch_page("About me");

        });
        grid.gridx=2;
        grid.gridy=0;
        grid.insets=new Insets(10,10,10,10);
        first_part.add(check_feature,grid);

        current_goal=new std_label("Dietary goal: "+controller.user.goal,15);
        grid.gridx=3;
        grid.gridy=0;
        //grid.anchor=GridBagConstraints.WEST;
        grid.insets=new Insets(10,10,10,10);
        first_part.add(current_goal,grid);





        calories=new std_label("Energy:",14);
        grid.gridx=0;
        grid.gridy=0;
        grid.insets=new Insets(30,10,10,10);
        label_part.add(calories,grid);

        protein=new std_label("Protein:",14);
        grid.gridx=1;
        label_part.add(protein,grid);

        fat=new std_label("Fat:",14);
        grid.gridx=2;
        label_part.add(fat,grid);

        sugar=new std_label("Sugar",14);
        grid.gridx=3;
        label_part.add(sugar,grid);


        advice=new std_label("",17);
        grid.gridx=0;
        grid.gridy=0;
        nutrition_info.add(advice,grid);



        breakfast_content=new std_label("Breakfast:",12);
        grid_food.gridx=0;
        grid_food.gridy=0;
        grid_food.insets=new Insets(10,10,10,10);
        grid_food.anchor=GridBagConstraints.LINE_START;
        food_added.add(breakfast_content,grid_food);



        lunch_content=new std_label("Lunch:",12);
        grid_food.gridx=0;
        grid_food.gridy=1;
        grid_food.insets=new Insets(10,10,10,10);
        food_added.add(lunch_content,grid_food);



        dinner_content=new std_label("Dinner:",12);
        grid_food.gridx=0;
        grid_food.gridy=2;
        grid_food.insets=new Insets(10,10,10,10);
        food_added.add(dinner_content,grid_food);

        snack_content=new std_label("Snacks:",12);
        grid_food.gridx=0;
        grid_food.gridy=3;
        grid_food.insets=new Insets(10,10,10,10);
        food_added.add(snack_content,grid_food);




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
        grid.insets=new Insets(30,0,10,10);
        third_part.add(food_intake,grid);


        search=new std_button("Search",Color.white,Color.gray,150,20,15);
        search.addActionListener(controller.handler);
        grid.gridx=2;
        grid.gridy=0;
        grid.insets=new Insets(30,0,10,10);
        third_part.add(search,grid);



        std_label portion=new std_label("Portion (g):",17);
        grid.gridx=3;
        grid.gridy=0;
        grid.insets=new Insets(30,10,10,10);
        third_part.add(portion,grid);


        portion_=new JTextField(5);
        portion_.setBorder(BorderFactory.createLineBorder(Color.black,1));
        portion_.setFont(new Font("Avenir",Font.PLAIN,17));
        grid.gridx=4;
        grid.gridy=0;
        grid.insets=new Insets(30,10,10,10);
        third_part.add(portion_,grid);



        String[]options={"Breakfast","Lunch","Dinner","Snacks"};
        box=new JComboBox<>(options);
        box.setFont(new Font("Avenir",Font.PLAIN,17));
        box.setBorder(BorderFactory.createLineBorder(Color.black,1));
        grid.gridx=5;
        grid.gridy=0;
        grid.insets=new Insets(30,10,10,10);
        third_part.add(box,grid);


        add_=new std_button("Add",Color.white,Color.gray,150,20,15);
        grid.gridx=0;
        grid.gridy=1;
        grid.insets=new Insets(10,10,30,10);
        add_.addActionListener(controller.handler);
        third_part.add(add_,grid);

        std_button remove_=new std_button("Clear",Color.white,Color.gray,150,20,15);
        remove_.addActionListener(e->remove());
        grid.gridx=1;
        grid.gridy=1;
        grid.insets=new Insets(10,10,30,10);
        third_part.add(remove_,grid);

        std_button choose_preset=new std_button("Recommendation plans",Color.white,Color.gray,150,20,13);
        choose_preset.addActionListener(e -> {
            int operation=0;

            if (controller.user.goal.equals("None")) operation=1;
            if (controller.user.goal.equals("High protein")) operation=2;
            if (controller.user.goal.equals("Low fat")) operation=3;
            if (controller.user.goal.equals("Low sugar")) operation=4;

            controller.show_customised_plans.removeAll();
            controller.show_customised_plans.initialise(operation,1);
            controller.switch_page("Show plans");

        });
        grid.gridx=0;
        grid.gridy=2;
        grid.insets=new Insets(10,10,10,10);
        third_part.add(choose_preset,grid);

        std_button choose_customised=new std_button("Customised plans",Color.white,Color.gray,150,20,15);
        choose_customised.addActionListener(e -> {
            controller.show_customised_plans.removeAll();
            controller.show_customised_plans.initialise(5,1);
            controller.switch_page("Show plans");

        });
        grid.gridx=1;
        grid.gridy=2;
        grid.insets=new Insets(10,10,10,10);
        third_part.add(choose_customised,grid);




        submit=new std_button("Submit for today",Color.white,Color.gray,150,20,15);
        submit.addActionListener(controller.handler);
        grid.gridx=0;
        grid.gridy=3;
        grid.insets=new Insets(10,10,10,10);
        third_part.add(submit,grid);

        std_button report=new std_button("Show report",Color.white,Color.gray,150,20,15);
        report.addActionListener(e -> {

            controller.report_page.removeAll();
            controller.report_page.initialise();
            controller.switch_page("report page");

        });
        grid.gridy=0;
        report_part.add(report);



        add(first_part);
        add(Box.createRigidArea(new Dimension(1000,30))); // add a gap
        add(nutrition_info);
        add(label_part);

        add(Box.createRigidArea(new Dimension(1000,10))); // add a gap
        add(food_added);
        add(Box.createRigidArea(new Dimension(1000,10))); // add a gap
        add(third_part);
        add(Box.createRigidArea(new Dimension(1000,30))); // add a gap
        add(report_part);






    }

    public void setWelcome_message(){
        welcome_message.setText("Welcome, "+controller.get_name());
    }
    //public void setCaloriesLabel(){calories.setText("Energy: 0/"+Integer.toString((int)(controller.getBMR()*1.5))+" kcal");}


    public void remove(){ // clear the history of the current day

        breakfast_content.setText("Breakfast:");
        lunch_content.setText("Lunch:");
        dinner_content.setText("Dinner:");
        snack_content.setText("Snacks:");

        //calories.setText("Energy: 0/"+Integer.toString((int)(controller.getBMR()*1.5))+" kcal");

        food_intake.setText("");

        intake_per_day=0;
        weekly_intake=0;
        average_intake=0;
        portion_.setText("");

        this.calories_counter=0;
        this.protein_counter=0;
        this.fat_counter=0;
        this.sugar_counter=0;

        this.renew_labels();

        this.breakfast_.clear();
        this.lunch_.clear();
        this.dinner_.clear();
        this.snack_.clear();

    }

    public void renew_labels(){
        calories.setText("Energy: "+String.format("%.2f",this.calories_counter)+" / "+controller.user.bmr*1.2+" kcal");
        protein.setText("Protein: "+String.format("%.2f",this.protein_counter)+"g");
        fat.setText("Fat: "+String.format("%.2f",this.fat_counter)+"g");
        sugar.setText("Sugar: "+String.format("%.2f",this.sugar_counter)+"g");

    }



    public void add_food(Food food_to_add,String m){


        String f=food_to_add.name;



        this.calories_counter+=(double)(food_to_add.Energy)/100*food_to_add.intake_amount;

        //System.out.println(food_to_add.name+":"+(double)(food_to_add.Energy)/100*food_to_add.intake_amount+"kcal, "+food_to_add.intake_amount);
        //System.out.println(food_to_add.index+" " +food_to_add.name+ " "+food_to_add.Energy+ " "+food_to_add.intake_amount);

        this.protein_counter+=food_to_add.Protein/100*food_to_add.intake_amount;
        this.fat_counter+=food_to_add.Lipid/100*food_to_add.intake_amount;
        this.sugar_counter+=food_to_add.Sugar/100*food_to_add.intake_amount;

        if (m.equals("Breakfast")){

            String temp_str= this.breakfast_content.getText()+f+","+food_to_add.intake_amount+"g. ";
            this.breakfast_content.setText(temp_str);

            this.breakfast_.add(food_to_add);

        }

        else if (m.equals("Lunch")){

            String temp_str= this.lunch_content.getText()+f+","+food_to_add.intake_amount+"g. ";
            this.lunch_content.setText(temp_str);

            this.lunch_.add(food_to_add);
        }

        else if (m.equals("Dinner")){
            String temp_str= this.dinner_content.getText()+f+","+food_to_add.intake_amount+"g. ";
            this.dinner_content.setText(temp_str);

            controller.main_page.dinner_.add(food_to_add);
        }
        else if (m.equals("Snacks")){
            String temp_str= this.snack_content.getText()+f+","+food_to_add.intake_amount+"g. ";
            this.snack_content.setText(temp_str);

            this.snack_.add(food_to_add);
        }


        double intakePerDay= food_to_add.intake_amount*food_to_add.Energy/100;

        BigDecimal bd=new BigDecimal(intakePerDay);
        bd=bd.setScale(2, RoundingMode.HALF_UP);
        double daily_intake=bd.doubleValue(); // round to 2 decimal points

        this.intake_per_day+=daily_intake;


        this.renew_labels();
        this.food_intake.setText("");
        this.portion_.setText("");

    }





}