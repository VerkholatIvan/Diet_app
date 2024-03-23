import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class User {

    protected String username;
    protected App controller;
    protected String password;

    //private String id;
    protected double height;
    protected double weight;
    protected String gender;

    protected int age;

    protected String goal;
    protected ArrayList<Food> breakfast_=new ArrayList<>();
    protected ArrayList<Food>lunch_=new ArrayList<>();
    protected ArrayList<Food>dinner_=new ArrayList<>();
    protected ArrayList<Food>snack_=new ArrayList<>();
    protected ArrayList<Food> history_breakfast_=new ArrayList<>();
    protected ArrayList<Food> history_lunch_=new ArrayList<>();
    protected ArrayList<Food> history_dinner_=new ArrayList<>();
    protected ArrayList<Food> history_snack_=new ArrayList<>();
    protected ArrayList<ArrayList<Food>>food_history_daily=new ArrayList<>(); // stores food eaten each day
    protected ArrayList<daily_plan>customised_combo=new ArrayList<>(); // stores daily plans

    protected int day_count;
    protected int bmr;
    protected int cap_sugar=Integer.MAX_VALUE;
    protected int cap_fat=Integer.MAX_VALUE;
    protected int min_protein=Integer.MIN_VALUE;

    protected String goal_advice;



    User(){};


    User(App controller){
        this.controller=controller;

    }


    public void set_name_password(String name,String password){
        this.username=name;
        this.password=password;
    }


    public void set_info(String gender, double height,double weight,int age){
        this.gender=gender;
        this.height=height;
        this.weight=weight;
        this.age=age;
        this.goal="None";
        this.getBMR();
        controller.about_me_page.refresh_user_info();


    }

    public String getUsername(){
        return username;
    }

    public Vector<String> extractData(String filename){

        Vector<String>info=new Vector<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                info.add(line.trim());
            }


        } catch (IOException e) {
            System.out.println("Fail to access file!");
        }


        return info;
    }


    public void login(String username,App controller){

        Vector<String>info=extractData("user_info/"+username+".txt");

        this.controller=controller;
        this.username=info.get(0);
        this.password=info.get(1);
        this.gender=info.get(2);
        this.height=Double.parseDouble(info.get(3));
        this.weight=Double.parseDouble(info.get(4));
        this.age=Integer.parseInt(info.get(5));
        this.initialise_foods();
        this.initialise_goal_and_plan();

        this.getBMR();
        this.set_cap();
        this.refresh_Goal_advice();




    }

    public void write(){

//        this.id=generate_id();

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("user_info/"+username+".txt"))) {
            buffer.write(username+"\n"+password+"\n"+gender+"\n"+height+"\n"+weight+"\n"+age+"\n"); // according to this order, we can directly read from this file. From the 7th line are foods

        } catch (IOException e) {
            System.out.println("fail writing to file");
        }

        // create empty file for user customised combo
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("user_info/"+username+"_goalAndPlan.txt"))) {
            buffer.write(this.goal+"\n");

        } catch (IOException e) {
            System.out.println("fail writing to file");
        }


        //******* below are codes for write username to a txt file which stores all usernames. But I consider it not necessarily. *******

//        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("user_info/users.txt",true))) {
//            buffer.write(username);
//            System.out.println("written!");//just for test
//        } catch (IOException e) {
//            System.out.println("fail writing to file");
//        }



    }

//************* Below are codes that generates unique 6 digits ID for users but I think its not necessary as well. *******

//    public String generate_id(){ // Don't know if we need ID because username should be unique as well
//
//        Vector<String>users=new Vector<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("user_info/users.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                users.add(line);
//            }
//        } catch (IOException e) {
//            System.out.println("Fail to access file!");
//        }
//
//        while (true) {
//
//            char[] arr = new char[6];
//            Random num = new Random();
//            for (int i = 0; i < 6; i++) {
//                int temp = num.nextInt(10) ;
//                arr[i] = (char) ('0'+temp);
//
//            }
//
//            String id_ = new String(arr);
//
//            boolean repeat=false;
//
//            for (int i=0;i<users.size();i++){
//
//                if(users.get(i)==id_){
//                    repeat=true;
//                    break;
//                }
//
//            }
//            if (repeat==false){
//
//                System.out.println(id_);//just for test
//                return id_;
//            }
//        }
//
//    }

    public void rewrite_info(){

        Vector<String>history=new Vector<>(extractData("user_info/"+username+".txt"));

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("user_info/"+username+".txt"))) {
            buffer.write(username+"\n"+password+"\n"+gender+"\n"+height+"\n"+weight+"\n"+age+"\n"); // according to this order, we can directly read from this file. From the 7th line are foods
            for (int i=6;i<history.size();i++){
                buffer.write(history.get(i)+"\n");
            }

        } catch (IOException e) {
            System.out.println("fail writing to file");
        }

    }

    public void write_daily_plan(){

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("user_info/"+username+"_goalAndPlan.txt"))) {

            // create empty file here
            buffer.write(this.goal+"\n");

            if (customised_combo.isEmpty()){
                return;
            }

            for (daily_plan i:customised_combo){

                String daily_plan="";
                for (Food j:i.breakfast){ // each meal


                    daily_plan+=j.index;
                    daily_plan+='&';
                    daily_plan+=j.intake_amount;
                    daily_plan+=","; // separate each food


                }
                daily_plan+='/'; //separate each meal

                for (Food j:i.lunch){ // each meal


                    daily_plan+=j.index;
                    daily_plan+='&';
                    daily_plan+=j.intake_amount;
                    daily_plan+=","; // separate each food


                }

                daily_plan+='/'; //separate each meal

                for (Food j:i.dinner){ // each meal


                    daily_plan+=j.index;
                    daily_plan+='&';
                    daily_plan+=j.intake_amount;
                    daily_plan+=","; // separate each food


                }
                daily_plan+='/'; //separate each meal

                for (Food j:i.snack){ // each meal


                    daily_plan+=j.index;
                    daily_plan+='&';
                    daily_plan+=j.intake_amount;
                    daily_plan+=","; // separate each food


                }
                daily_plan+='/'; //separate each meal

                daily_plan+="\n";
                buffer.write(daily_plan);

            }


        } catch (IOException e) {
            System.out.println("fail writing to file");
        }
    }

    public void write_daily_intake(){


        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("user_info/"+username+".txt",true))) {
            String temp=new String();

            for (Food i:breakfast_){

                temp+=i.index;
                temp+='&';
                temp+=i.intake_amount;
                temp+=","; // separate each food

            }
            temp+="/"; // separate each meal
            for (Food i:lunch_){

                temp+=i.index;
                temp+='&';
                temp+=i.intake_amount;
                temp+=",";

            }

            temp+="/";

            for (Food i:dinner_){

                temp+=i.index;
                temp+='&';
                temp+=i.intake_amount;
                temp+=",";

            }
            temp+="/";

            for (Food i:snack_){

                temp+=i.index;
                temp+='&';
                temp+=i.intake_amount;
                temp+=",";

            }

            buffer.write(temp+"\n"); //From the 7th line are foods
            System.out.println("written");//just for test
            breakfast_.clear();
            lunch_.clear();
            dinner_.clear();
            snack_.clear();


        } catch (IOException e) {
            System.out.println("fail writing to file");
        }
    }

    public void getBMR(){
        int BMR=(int)(weight*10+height*6.25-age*5);
        if (gender.equals("Female")){
            BMR-=161;
            this.bmr=BMR;
        }
        else{
            BMR+=5;
            this.bmr=BMR;
        }

    }

    public void initialise_goal_and_plan(){

        Vector<String>info=new Vector<>(this.extractData("user_info/"+username+"_goalAndPlan.txt"));
        this.goal=info.get(0);

        for (int i=1;i<info.size();i++){

            boolean ampersand=false;
            String index="";
            String portion="";
            int slash_counter=1;

            ArrayList<Food>breakfast=new ArrayList<>();
            ArrayList<Food>lunch=new ArrayList<>();
            ArrayList<Food>dinner=new ArrayList<>();
            ArrayList<Food>snack=new ArrayList<>();

            for (char j: info.get(i).toCharArray()){

                if (j=='&'){
                    ampersand=true;
                    continue;
                }

                if (j==','){

                    ampersand=false;
                    Food temp=controller.dataset.get(Integer.parseInt(index));
                    temp.set_intake(Double.parseDouble(portion));

                    if (slash_counter==1) {
                        breakfast.add(temp);

                    } else if (slash_counter==2) {
                        lunch.add(temp);

                    } else if (slash_counter==3) {
                        dinner.add(temp);
                    } else if (slash_counter==4) {
                        snack.add(temp);
                    }

                    index="";
                    portion="";

                    continue;

                }

                if (j=='/' ){
                    slash_counter++;
                    continue;
                }


                if (!ampersand){
                    index+=j;
                }
                else {
                    portion+=j;
                }


            }

            // record all the food in the daily plan
            daily_plan temp=new daily_plan(breakfast,lunch,dinner,snack);
            this.customised_combo.add(temp);

        }



    }


    public void initialise_foods() {

        if (!history_breakfast_.isEmpty()) {
            history_breakfast_.clear();
        }

        if (!history_lunch_.isEmpty()) {
            history_lunch_.clear();
        }

        if (!history_dinner_.isEmpty()) {
            history_dinner_.clear();
        }
        if (!history_snack_.isEmpty()) {
            history_snack_.clear();
        }
        if (!food_history_daily.isEmpty()){
            food_history_daily.clear();
        }
        day_count=0;


        Vector<String>info=extractData("user_info/"+username+".txt");

        if (info.isEmpty()){
            return;
        }

        if (info.size()<7){
            return;
        }


        for (int i=6;i<info.size();i++){ // starts from line 7, read in information of foods

            boolean ampersand=false;
            String index="";
            String portion="";
            int slash_counter=1;
            ArrayList<Food>temp_list=new ArrayList<>();// which stores the foods by each day

            for (char j: info.get(i).toCharArray()){

                if (j=='&'){
                    ampersand=true;
                    continue;
                }

                if (j==','){
                    ampersand=false;
                    Food temp=controller.dataset.get(Integer.parseInt(index));
                    temp.set_intake(Double.parseDouble(portion));
                    temp_list.add(temp);

                    if (slash_counter==1) {
                        this. history_breakfast_.add(temp);
                    } else if (slash_counter==2) {
                        this. history_lunch_.add(temp);
                    } else if (slash_counter==3) {
                        this. history_dinner_.add(temp);
                    } else if (slash_counter==4) {
                        this. history_snack_.add(temp);
                    }

                    index="";
                    portion="";

                    continue;

                }

                if (j=='/' ){
                    slash_counter++;
                    continue;
                }


                if (!ampersand){
                    index+=j;
                }
                else {
                    portion+=j;
                }


            }

            food_history_daily.add(temp_list); // record all the foods eaten by day

            day_count++;
        }

    }

    public void remove_plan(int index){
        customised_combo.remove(index);
        this.write_daily_plan();
    }

    public double total_energy(int day){
        double sum=0;
        int counter=1;
        for (ArrayList <Food>i:food_history_daily){

            for (Food j:i){
                sum+=j.Energy;
            }
            if (counter==day)break;
            counter++;


        }
        return sum;
    }
    public double total_protein(int day){
        double sum=0;
        int counter=1;
        for (ArrayList <Food>i:food_history_daily){
            for (Food j:i){
                sum+=j.Protein;
            }
            if (counter==day)break;
            counter++;

        }
        return sum;
    }

    public double total_fat(int day){
        double sum=0;
        int counter=0;
        for (ArrayList <Food>i:food_history_daily){
            for (Food j:i){
                sum+=j.Lipid;
            }
            if (counter==day)break;
            counter++;
        }
        return sum;
    }

    public double total_sugar(int day){
        double sum=0;
        int counter=0;
        for (ArrayList <Food>i:food_history_daily){
            for (Food j:i){
                sum+=j.Sugar;
            }
            if (counter==day)break;
            counter++;
        }
        return sum;
    }

    public void set_cap(){

        cap_sugar=Integer.MAX_VALUE;
        cap_fat=Integer.MAX_VALUE;
        min_protein=Integer.MIN_VALUE;

        if (Objects.equals(this.goal, "Low sugar")) cap_sugar=(int)(this.bmr/10/4);

        if (Objects.equals(this.goal, "High protein")) min_protein=(int)(this.weight*1.7);

        if (Objects.equals(this.goal, "Low fat")) cap_fat=(int)(this.bmr*0.3/9);

    }

    public Vector<String> evaluate(int day){

        if (day>day_count) day=day_count;


        String calories_evaluate="";
        String protein_evaluate="";
        String sugar_evaluate="";
        String fat_evaluate="";

        double cal=this.total_energy(day)/day;
        double protein=this.total_protein(day)/day;
        double fat=this.total_fat(day)/day;
        double sugar=this.total_sugar(day)/day;

        if (cal>bmr*1.5){
            double gap=cal-bmr*1.5;
            calories_evaluate="You consumed too much calories, exceeded "+String.format("%.2f", gap)+"kcal in average per day. ";

        }

        else if (cal<bmr){
            double gap=bmr-cal;
            System.out.println(cal);
            System.out.println(bmr);
            calories_evaluate="You calories intake is too low, you need "+String.format("%.2f", gap)+"kcal more in average per day. ";

        }



        if (protein<min_protein){
            double gap=min_protein-protein;
            protein_evaluate="You didn't have enough protein,  in average you should intake "+String.format("%.2f", gap)+"g more per day. ";

        }

        if (fat>cap_fat){
            double gap=fat-cap_fat;
            fat_evaluate="You consumed too much fat, exceeded "+String.format("%.2f", gap)+"g in average per day. ";

        }

        if (sugar>cap_sugar){
            double gap=sugar-cap_sugar;
            sugar_evaluate="You consumed too much sugar, exceeded "+String.format("%.2f", gap)+"g in average per day. ";

        }

        Vector<String>result=new Vector<>();
        if (!calories_evaluate.isEmpty()) result.add(calories_evaluate);
        if (!protein_evaluate.isEmpty()) result.add(protein_evaluate);
        if (!fat_evaluate.isEmpty()) result.add(fat_evaluate);
        if (!sugar_evaluate.isEmpty()) result.add(sugar_evaluate);



        if (result.isEmpty()){
            result.add("Your nutritional intake is normal at the moment.");
        }


        return result;


    }

    public void re_calculate(){ // after changing user info

        this.rewrite_info();
        this.getBMR();
        this.set_cap();

    }

    public void refresh_Goal_advice(){

        this.goal_advice="";
        if (this.goal.equals("High protein")) this.goal_advice="Your recommended daily protein intake is at least "+this.min_protein+"g. ";
        if (this.goal.equals("Low sugar")) this.goal_advice="Your recommended daily sugar intake should not exceed "+this.cap_sugar+"g ";
        if (this.goal.equals("Low fat")) this.goal_advice="Your recommended daily fat intake should not exceed "+this.cap_fat+"g ";

    }



}
