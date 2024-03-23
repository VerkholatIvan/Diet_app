import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

public class App extends JFrame{

    protected List<Food>dataset;

    protected ArrayList<daily_plan> standard;
    protected ArrayList<daily_plan> high_protein;
    protected  ArrayList<daily_plan> low_fat;
    protected  ArrayList<daily_plan> low_sugar;


    protected CardLayout pages=new CardLayout();
    protected JPanel cards= new JPanel(pages);

    protected User user=new User(this);

    protected welcome_page welcome;
    protected login_page login_page;
    protected sign_up_page signUpPage;
    protected ask_info_page ask_info_page;
    protected main_page main_page;
    protected customise_daily_plan customise_page;
    protected user_feature about_me_page;
    protected JScrollPane main_page_scroll;
    protected show_plans show_customised_plans;
    protected JScrollPane  show_customised_plans_scroll;
    protected JScrollPane show_report_scroll;
    protected show_report_page report_page;

    protected buttonHandler handler;




    public void initialise_pages(){

        handler=new buttonHandler(this);
        welcome=new welcome_page(this);
        login_page=new login_page(this);
        signUpPage=new sign_up_page(this);
        ask_info_page=new ask_info_page(this);
        main_page=new main_page(this);
        main_page_scroll=new JScrollPane(main_page);
        customise_page=new customise_daily_plan(this);
        about_me_page=new user_feature(this);
        show_customised_plans=new show_plans(this);
        show_customised_plans_scroll=new JScrollPane(show_customised_plans);
        report_page=new show_report_page(this);
        show_report_scroll=new JScrollPane(report_page);




        cards.add(welcome,welcome.name);
        cards.add(login_page,login_page.name);
        cards.add(signUpPage,signUpPage.name);
        cards.add(ask_info_page, ask_info_page.name);
        cards.add(main_page_scroll,main_page.name);
        cards.add(customise_page,customise_page.name);
        cards.add(about_me_page,about_me_page.name);
        cards.add(show_customised_plans_scroll,show_customised_plans.name);
        cards.add(show_report_scroll,report_page.name);



    }

    public void initialise_plans(){

        Vector<String>standard_plans=new Vector<>(extractPlanData("standard.txt"));
        Vector<String>high_protein_plans=new Vector<>(extractPlanData("high_protein.txt"));
        Vector<String>low_fat_plans=new Vector<>(extractPlanData("low_fat.txt"));
        Vector<String>low_sugar_plans=new Vector<>(extractPlanData("low_sugar.txt"));

        for (daily_plan i:analyser(standard_plans)){
            daily_plan temp=new daily_plan();
            temp.copy(i);
            this.standard.add(temp);

        }

        for (daily_plan i:analyser(high_protein_plans)){
            daily_plan temp=new daily_plan();
            temp.copy(i);
            this.high_protein.add(temp);


        }

        for (daily_plan i:analyser(low_fat_plans)){
            daily_plan temp=new daily_plan();
            temp.copy(i);
            this.low_fat.add(temp);

        }

        for (daily_plan i:analyser(low_sugar_plans)){
            daily_plan temp=new daily_plan();
            temp.copy(i);
            this.low_sugar.add(temp);

        }



    }

    public Vector<String> extractPlanData(String filename){

        Vector<String>info=new Vector<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("preset_plans/"+filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                info.add(line.trim());
            }


        } catch (IOException e) {
            System.out.println("Fail to access file!");
        }


        return info;
    }

    public ArrayList<daily_plan> analyser (Vector<String>info){

        ArrayList<daily_plan>result=new ArrayList<>();


        for (int i=0;i<info.size();i++){

            boolean ampersand=false;
            StringBuilder index= new StringBuilder();
            StringBuilder portion=new StringBuilder();
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
                    Food temp=this.dataset.get(Integer.parseInt(index.toString()));


                    temp.set_intake(Double.parseDouble(String.valueOf(portion)));




                    if (slash_counter==1) {
                        breakfast.add(temp);

                    } else if (slash_counter==2) {
                        lunch.add(temp);

                    } else if (slash_counter==3) {
                        dinner.add(temp);
                    } else if (slash_counter==4) {
                        snack.add(temp);
                    }

                    index = new StringBuilder();
                    portion= new StringBuilder();

                    continue;

                }

                if (j=='/' ){
                    slash_counter++;
                    continue;
                }


                if (!ampersand){
                    index.append(j);
                }

                else {

                    portion.append(j);

                }


            }

            // record all the food in the daily plan
            daily_plan temp_daily=new daily_plan(breakfast,lunch,dinner,snack);
            result.add(temp_daily);

        }

        return result;
    }




    public void update_welcome_message(){
        main_page.setWelcome_message();
    }



    public void switch_page(String name){
        pages.show(cards,name);
    }

    public App(User a){this.user=a;};

    public App(){

        dataset = new ArrayList<>();
        String line="";
        try (BufferedReader b = new BufferedReader(new FileReader("ABBREV.csv"))) {
            line=b.readLine(); //skip the title
            while ((line = b.readLine()) != null) {
                String[] row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int i=0;i<row.length;i++){
                    if (row[i].isEmpty()){
                        row[i]="0";
                    }
                }

                Food temp=new Food(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12]);
                dataset.add(temp);
            }
        } catch (IOException e) {
            System.out.println("Read error!");
        }

        this.standard=new ArrayList<>();
        this.high_protein=new ArrayList<>();
        this.low_fat=new ArrayList<>();
        this.low_sugar=new ArrayList<>();

        setSize(1200,800);
        initialise_pages();
        initialise_plans();
        add(cards);
        setVisible(true);
    }

    public boolean check_name_repeat(String username){

        try (BufferedReader reader = new BufferedReader(new FileReader("user_info/"+username+".txt"))) {
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    public void user_Log_out(){
        user=new User(this);
        this.main_page.advice.setText(this.user.goal_advice);
    }
    public void user_login(String username){
        user.login(username,this);
    }

    public void set_User_account(String username,String password){
        user.set_name_password(username,password);
    }

    public void set_user_info(String gender,double height,double weight,int age){
        user.set_info(gender,height,weight,age);
    }

    public void user_write_register(){
        user.write();
    }

    public String get_name(){
        return user.getUsername();
    }

    public void user_write_daily_intake(){user.write_daily_intake();}



    public static void main(String[] args) {
        App app=new App();
    }




}





