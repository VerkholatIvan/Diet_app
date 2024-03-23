import java.util.Objects;

public class Food {

    protected int index;
    protected int NDB_No;
    protected String name;
    protected double water;
    protected int Energy;
    protected double Protein;
    protected double Lipid;
    protected double Ash;
    protected double Carbohydrate;
    protected double Fiber;
    protected double Sugar;
    protected double Calcium_mg;
    protected double Iron_mg;
    protected double intake_amount;

    Food(){}
    Food(String index, String NDB_NO, String name, String water, String Energy, String Protein, String Lipid, String Ash, String Carbohydrate, String Fiber, String Sugar,String Calcium_mg,String Iron_mg){

        this.index=Integer.parseInt(index);
        this.NDB_No=Integer.parseInt(NDB_NO);
        this.name=name;
        this.water=Double.parseDouble(water);
        this.Energy=Integer.parseInt(Energy);
        this.Protein=Double.parseDouble(Protein);
        this.Lipid=Double.parseDouble(Lipid);
        this.Ash=Double.parseDouble(Ash);
        this.Carbohydrate=Double.parseDouble(Carbohydrate);
        this.Fiber=Double.parseDouble(Fiber);
        this.Sugar=Double.parseDouble(Sugar);
        this.Calcium_mg=Double.parseDouble(Calcium_mg);
        this.Iron_mg=Double.parseDouble(Iron_mg);

    }

    public void set_intake(double amount){
        this.intake_amount=amount;
    }

    public void copy(Food f){ // in java everything is reference, for example Food a = b, if you change 'a' you will indeed change b, thats why we need this method
        this.index=f.index;
        this.NDB_No=f.NDB_No;
        this.name=f.name;
        this.water=f.water;
        this.Energy=f.Energy;
        this.Protein=f.Protein;
        this.Lipid=f.Lipid;
        this.Ash=f.Ash;
        this.Carbohydrate=f.Carbohydrate;
        this.Fiber=f.Fiber;
        this.Sugar=f.Sugar;
        this.Calcium_mg=f.Calcium_mg;
        this.Iron_mg=f.Iron_mg;
        this.intake_amount= f.intake_amount;
    }

    // logic to compare if two foods are equal
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return food.index==index &&
                Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

}
