import java.util.ArrayList;

public class daily_plan {

    protected ArrayList<Food>breakfast=new ArrayList<>();
    protected ArrayList<Food>lunch=new ArrayList<>();
    protected ArrayList<Food>dinner=new ArrayList<>();
    protected ArrayList<Food>snack=new ArrayList<>();

    protected double calories=0;
    protected double protein=0;
    protected double fat=0;
    protected double sugar=0;

    public daily_plan(){};
    public daily_plan(ArrayList<Food>breakfast, ArrayList<Food>lunch, ArrayList<Food>dinner,ArrayList<Food>snack){


        for (Food i:breakfast){
            Food temp=new Food();
            temp.copy(i);
            this.breakfast.add(temp);

        }

        for (Food i:lunch){

            Food temp=new Food();
            temp.copy(i);
            this.lunch.add(temp);

        }

        for (Food i:dinner){
            Food temp=new Food();
            temp.copy(i);
            this.dinner.add(temp);

        }

        for (Food i:snack){
            Food temp=new Food();
            temp.copy(i);
            this.snack.add(temp);

        }



        this.initialise_values();

    }

    public void calculate(ArrayList<Food>meal){

        if (!meal.isEmpty()) {

            for (Food i : meal) {

                calories += ((double) (i.Energy)*i.intake_amount)/100;
                protein += i.Protein/100*i.intake_amount;
                fat += i.Lipid/100*i.intake_amount;
                sugar += i.Sugar/100*i.intake_amount;
            }
        }

    }

    public void initialise_values(){

        calculate(breakfast);
        calculate(lunch);
        calculate(dinner);
        calculate(snack);

    }

    String string_converter(ArrayList<Food>temp) {

        if (temp.isEmpty()) return "None";


        String result="";

        for (Food i:temp){
            result+=i.name+", "+i.intake_amount+"g. Energy:"+i.Energy+" total energy:"+String.format("%.2f",(double)(i.Energy)/100*i.intake_amount)+"\n";

        }
        return result;

    }

    ArrayList<Food>getBreakfast() {

        return breakfast;

    }
    ArrayList<Food>getLunch() {

        return lunch;

    }

    ArrayList<Food>getDinner() {

        return dinner;

    }

    ArrayList<Food>getSnack() {

        return snack;

    }

    void copy(daily_plan plan){

        for (Food i:plan.breakfast){
            Food temp=new Food();
            temp.copy(i);
            this.breakfast.add(temp);

        }

        for (Food i:plan.lunch){

            Food temp=new Food();
            temp.copy(i);
            this.lunch.add(temp);

        }

        for (Food i:plan.dinner){
            Food temp=new Food();
            temp.copy(i);
            this.dinner.add(temp);

        }

        for (Food i:plan.snack){
            Food temp=new Food();
            temp.copy(i);
            this.snack.add(temp);

        }

        this.calories= plan.calories;
        this.protein= plan.protein;
        this.fat=plan.fat;
        this.sugar=plan.sugar;
    }




}
