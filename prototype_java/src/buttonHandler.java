import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidClassException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

public class buttonHandler implements ActionListener {

    protected App controller;

    buttonHandler(){};

    buttonHandler(App a){
        this.controller=a;
    }

    public void actionPerformed(ActionEvent e){

        if (e.getSource()==controller.welcome.sign_up){ // 'sign up' button in welcome page
            this.controller.switch_page("Sign_up_page");
        }

        else if (e.getSource()==controller.welcome.login){ // 'login' button in welcome page
            this.controller.switch_page("Login_page");
        }

        else if (e.getSource()==controller.login_page.confirm){ // 'confirm' button in login page

            if (!controller.check_name_repeat(controller.login_page.input_name.getText())){
                JOptionPane.showMessageDialog(null,"Username not found!",null,JOptionPane.ERROR_MESSAGE);
                return;
            };

            String username=controller.login_page.input_name.getText();



            try (BufferedReader reader = new BufferedReader(new FileReader("user_info/"+username+".txt"))) {
                String line=new String();

                for (int i=0;i<2;i++){
                    line = reader.readLine();
                }
                String password=new String(controller.login_page.input_password.getPassword());

                if (!password.equals(line)){
                    JOptionPane.showMessageDialog(null,"Incorrect password!",null,JOptionPane.ERROR_MESSAGE);
                    return;

                }

                else{
                    controller.user_login(username);
                    controller.update_welcome_message();
                    controller.main_page.current_goal.setText("Dietary goal: "+controller.user.goal);
                    controller.main_page.advice.setText(controller.user.goal_advice);
                    controller.about_me_page.goal_box.setSelectedItem(controller.user.goal);
                    controller.main_page.renew_labels();

                    controller.login_page.input_name.setText("");
                    controller.login_page.input_password.setText("");
                    controller.switch_page("Main_page");
                }


            } catch (IOException error) {
                System.out.println("Fail to access file!");
            }

        }

        else if(e.getSource()==controller.login_page.cancel){ // 'back' button in login page
            controller.login_page.input_name.setText("");
            controller.login_page.input_password.setText("");
            controller.switch_page("Welcome_page");
        }

        else if (e.getSource()==controller.signUpPage.next){ // 'next' button in sign up page
            //check length
            String username=controller.signUpPage.input_name.getText();
            if (username.length()>16||username.length()<5){
                JOptionPane.showMessageDialog(null,"Invalid username length!",null,JOptionPane.ERROR_MESSAGE);

                controller.signUpPage.input_name.repaint();
                return;
            }



            // check if username contains symbol
            for (int i=0;i<username.length();i++){

                if (((int)username.charAt(i)>=32&&(int)username.charAt(i)<=47)||((int)username.charAt(i)>=58&&(int)username.charAt(i)<=64)||((int)username.charAt(i)>=91&&(int)username.charAt(i)<=96)||((int)username.charAt(i)>=123&&(int)username.charAt(i)<=126)){
                    JOptionPane.showMessageDialog(null,"Username shouldn't contain symbol!",null,JOptionPane.ERROR_MESSAGE);

                    controller.signUpPage.input_name.repaint();
                    return;
                }
            }

            //check if username repeat:

            if (controller.check_name_repeat(username)){
                JOptionPane.showMessageDialog(null,"Username Occupied!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }


            char[] password=controller.signUpPage.input_password.getPassword(); // getpassword returns char array

            //check password length
            if (password.length<8||password.length>16){
                JOptionPane.showMessageDialog(null,"Invalid password length!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }



            //check if re-enter password match
            char[]password2=controller.signUpPage.sec_input_password.getPassword();

            if (!Arrays.equals(password2,password)){
                JOptionPane.showMessageDialog(null,"Password not match!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }
            String password_=new String(password);

            controller.signUpPage.input_name.setText("");
            controller.signUpPage.input_password.setText("");
            controller.signUpPage.sec_input_password.setText("");

            controller.set_User_account(username,password_);
            controller.switch_page("Ask_info_page");

        }

        else if (e.getSource()==controller.signUpPage.back){
            controller.signUpPage.input_name.setText("");
            controller.signUpPage.input_password.setText("");
            controller.signUpPage.sec_input_password.setText("");
            controller.switch_page("Welcome_page");
        }

        else if (e.getSource()==controller.ask_info_page.Signup){ // 'sign up' button in ask info page
            String gender;

            // get gender
            if (!controller.ask_info_page.gender_male.isSelected() && !controller.ask_info_page.gender_female.isSelected()){
                JOptionPane.showMessageDialog(null,"Please select your biological gender!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            else if (controller.ask_info_page.gender_male.isSelected()){
                gender="Male";

            }

            else {
                gender="Female";

            }


            //check age
            int age;
            try{
                age=Integer.parseInt(controller.ask_info_page.enter_age.getText());
            }
            catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null,"Invalid input!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }


            //check height
            double height;

            try{
                height=Double.parseDouble(controller.ask_info_page.enter_height.getText());
            }
            catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null,"Invalid height input!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (height>250||height<70){
                JOptionPane.showMessageDialog(null,"Height out of range!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }



            //check weight
            double weight;
            try{
                weight=Double.parseDouble(controller.ask_info_page.enter_weight.getText());
            }
            catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null,"Invalid weight input!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (weight>635||weight<17){ // Till April 2023 the world record of heaviest human is 635kg (Jon Brower Minnoch). 17kg is roughly the weight of a 4-5 year old child.
                JOptionPane.showMessageDialog(null,"Weight out of range!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }


            controller.ask_info_page.gender_male.setSelected(false);
            controller.ask_info_page.gender_female.setSelected(false);
            controller.ask_info_page.enter_age.setText("");
            controller.ask_info_page.enter_height.setText("");
            controller.ask_info_page.enter_weight.setText("");

            controller.set_user_info(gender,height,weight,age);
            controller.user_write_register();
            controller.update_welcome_message();
            controller.main_page.renew_labels();
            controller.switch_page("Main_page");

        }

        else if (e.getSource()==controller.ask_info_page.cancel){ // 'back' button in main page

            controller.ask_info_page.gender_male.setSelected(false);
            controller.ask_info_page.gender_female.setSelected(false);
            controller.ask_info_page.enter_age.setText("");
            controller.ask_info_page.enter_height.setText("");
            controller.ask_info_page.enter_weight.setText("");
            controller.user_Log_out();
            controller.switch_page("Welcome_page");
        }

        else if(e.getSource()==controller.main_page.log_out){ // 'log out' button in main page
            controller.user_Log_out();
            controller.switch_page("Welcome_page");

            controller.main_page.remove();
        }

        else if (e.getSource()==controller.main_page.search){ // 'search' button in main page

            ArrayList<String> food_match=new ArrayList<>();
            if (!controller.main_page.food_intake.getText().isEmpty()) {

                String keyword=controller.main_page.food_intake.getText().toUpperCase();

                for (Food i : controller.dataset) {

                    if (i.name.contains(keyword)) {
                        food_match.add(i.name);
                    }
                }

                if (food_match.isEmpty()){
                    JOptionPane.showMessageDialog(null,"No match result!",null,JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[]food_array=food_match.toArray(new String[0]);
                JList<String>foodList=new JList<>(food_array);

                JOptionPane.showMessageDialog(
                        null,
                        new JScrollPane(foodList),
                        "Choose from one of the matches:",
                        JOptionPane.PLAIN_MESSAGE);

                controller.main_page.food_intake.setText(foodList.getSelectedValue());

            }

            else {
                JOptionPane.showMessageDialog(null,"Empty keyword!",null,JOptionPane.ERROR_MESSAGE);

            }

        }

        else if (e.getSource()==controller.main_page.add_){ // 'add' button in main page

            String f=controller.main_page.food_intake.getText(); // food name
            String p=controller.main_page.portion_.getText(); // food portion
            String m=(String)controller.main_page.box.getSelectedItem(); // food in which meal

            if (f.isEmpty()){
                JOptionPane.showMessageDialog(null,"You didn't select any food!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (p.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please enter portion of intake!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(p);
            }
            catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null,"Invalid portion input!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Double.parseDouble(p)<1){
                JOptionPane.showMessageDialog(null,"Invalid portion input!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            Food food_to_add=new Food();

            for (Food i:controller.dataset){

                if (i.name.equals(f.trim())){

                    food_to_add.copy(i);

                }
            }

            food_to_add.set_intake(Double.parseDouble(p)); // store how many grams of intake
            controller.main_page.add_food(food_to_add,m);


        }

        else if (e.getSource()==controller.main_page.submit){ // 'submit' button in main page

            if (controller.main_page.breakfast_.isEmpty()&&controller.main_page.lunch_.isEmpty()&&controller.main_page.dinner_.isEmpty()&&controller.main_page.snack_.isEmpty()){
                JOptionPane.showMessageDialog(null,"Its empty!",null,JOptionPane.ERROR_MESSAGE);
                controller.main_page.remove();
                return;
            }

            controller.user.breakfast_.addAll(controller.main_page.breakfast_);
            controller.user.lunch_.addAll(controller.main_page.lunch_);
            controller.user.dinner_.addAll(controller.main_page.dinner_);
            controller.user.snack_.addAll(controller.main_page.snack_);


            controller.user_write_daily_intake();
            JOptionPane.showMessageDialog(null, "Successfully added!", null, JOptionPane.INFORMATION_MESSAGE);
            controller.main_page.remove();
            controller.user.initialise_foods();

        }

        else if (e.getSource()==controller.customise_page.search){ // search button in customise daily plan page

            ArrayList<String> food_match=new ArrayList<>();
            if (!controller.customise_page.food_intake.getText().isEmpty()) {

                String keyword=controller.customise_page.food_intake.getText().toUpperCase();

                for (Food i : controller.dataset) {

                    if (i.name.contains(keyword)) {
                        food_match.add(i.name);
                    }
                }

                if (food_match.isEmpty()){
                    JOptionPane.showMessageDialog(null,"No match result!",null,JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[]food_array=food_match.toArray(new String[0]);
                JList<String>foodList=new JList<>(food_array);

                JOptionPane.showMessageDialog(
                        null,
                        new JScrollPane(foodList),
                        "Choose from one of the matches:",
                        JOptionPane.PLAIN_MESSAGE);

                controller.customise_page.food_intake.setText(foodList.getSelectedValue());

            }

            else {
                JOptionPane.showMessageDialog(null,"Empty keyword!",null,JOptionPane.ERROR_MESSAGE);

            }
        }

        else if (e.getSource()==controller.customise_page.add_){ // add button in customise daily plan page

            String f=controller.customise_page.food_intake.getText(); // food name
            String m=(String)controller.customise_page.box.getSelectedItem(); // food in which meal
            String p=controller.customise_page.portion_.getText(); // food portion

            if (f.isEmpty()){
                JOptionPane.showMessageDialog(null,"You didn't select any food!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (p.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please enter portion of intake!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(p);
            }
            catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null,"Invalid portion input!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Double.parseDouble(p)<1){
                JOptionPane.showMessageDialog(null,"Invalid portion input!",null,JOptionPane.ERROR_MESSAGE);
                return;
            }

            Food food_to_add=new Food();

            for (Food i:controller.dataset){

                if (i.name.equals(f.trim())){
                    food_to_add.copy(i);
                }
            }

            food_to_add.set_intake(Double.parseDouble(p)); // store how many grams of intake

            if (m.equals("Breakfast")){

                String temp_str=controller.customise_page.breakfast_content.getText()+f+","+p+"g. ";
                controller.customise_page.breakfast_content.setText(temp_str);

                controller.customise_page.breakfast_.add(food_to_add);

            }

            else if (m.equals("Lunch")){

                String temp_str=controller.customise_page.lunch_content.getText()+f+","+p+"g. ";
                controller.customise_page.lunch_content.setText(temp_str);

                controller.customise_page.lunch_.add(food_to_add);
            }

            else if (m.equals("Dinner")){
                String temp_str=controller.customise_page.dinner_content.getText()+f+","+p+"g. ";
                controller.customise_page.dinner_content.setText(temp_str);

                controller.customise_page.dinner_.add(food_to_add);
            }
            else if (m.equals("Snacks")){
                String temp_str=controller.customise_page.snack_content.getText()+f+","+p+"g. ";
                controller.customise_page.snack_content.setText(temp_str);

                controller.customise_page.snack_.add(food_to_add);
            }


            double intakePerDay= food_to_add.intake_amount*food_to_add.Energy/100;
            BigDecimal bd=new BigDecimal(intakePerDay);
            bd=bd.setScale(2, RoundingMode.HALF_UP);
            double daily_intake=bd.doubleValue(); // round to 2 decimal points
            controller.customise_page.calories_value+=daily_intake;
            controller.customise_page.calories.setText("Total calories: "+String.format("%.2f", controller.customise_page.calories_value)+"kcal");

            intakePerDay= food_to_add.intake_amount*food_to_add.Protein/100;
            bd=new BigDecimal(intakePerDay);
            bd=bd.setScale(2, RoundingMode.HALF_UP);
            daily_intake=bd.doubleValue(); // round to 2 decimal points
            controller.customise_page.protein_value+=daily_intake;
            controller.customise_page.protein.setText("Total protein: "+String.format("%.2f", controller.customise_page.protein_value)+"g");

            intakePerDay= food_to_add.intake_amount*food_to_add.Lipid/100;
            bd=new BigDecimal(intakePerDay);
            bd=bd.setScale(2, RoundingMode.HALF_UP);
            daily_intake=bd.doubleValue(); // round to 2 decimal points
            controller.customise_page.fat_value+=daily_intake;
            controller.customise_page.fat.setText("Total fat: "+String.format("%.2f", controller.customise_page.fat_value)+"g");

            intakePerDay= food_to_add.intake_amount*food_to_add.Sugar/100;
            bd=new BigDecimal(intakePerDay);
            bd=bd.setScale(2, RoundingMode.HALF_UP);
            daily_intake=bd.doubleValue(); // round to 2 decimal points
            controller.customise_page.sugar_value+=daily_intake;
            controller.customise_page.sugar.setText("Total sugar: "+String.format("%.2f", controller.customise_page.sugar_value)+"g");


            controller.customise_page.food_intake.setText("");
            controller.customise_page.portion_.setText("");

        }

        else if (e.getSource()==controller.customise_page.submit){  // set plan button in customise page

            if (controller.customise_page.breakfast_.isEmpty() && controller.customise_page.lunch_.isEmpty() && controller.customise_page.dinner_.isEmpty() && controller.customise_page.snack_.isEmpty()){
                JOptionPane.showMessageDialog(null,"Its empty!",null,JOptionPane.ERROR_MESSAGE);
                controller.customise_page.remove_food();
                return;
            }


            ArrayList<Food> temp1 = new ArrayList<>(controller.customise_page.breakfast_);
            ArrayList<Food> temp2 = new ArrayList<>(controller.customise_page.lunch_);
            ArrayList<Food> temp3 = new ArrayList<>(controller.customise_page.dinner_);
            ArrayList<Food> temp4 = new ArrayList<>(controller.customise_page.snack_);

            daily_plan temp_daily= new daily_plan(temp1,temp2,temp3,temp4);

            controller.user.customised_combo.add(temp_daily);
            controller.user.write_daily_plan();
            JOptionPane.showMessageDialog(null, "Daily plan successfully added!", null, JOptionPane.INFORMATION_MESSAGE);
            controller.customise_page.remove_food();

        }



    }

}
