import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class daily_planTest {

    @Test
    public void testDailyPlanConstructor() {
        Food food = new Food();
        ArrayList<Food> breakfast = new ArrayList<>();

        breakfast.add(food);

        ArrayList<Food> lunch = new ArrayList<>();
        breakfast.add(food);

        ArrayList<Food> dinner = new ArrayList<>();
        breakfast.add(food);

        ArrayList<Food> snack = new ArrayList<>();
        breakfast.add(food);

        daily_plan plan = new daily_plan(breakfast, lunch, dinner, snack);

        assertEquals(breakfast, plan.getBreakfast());
        assertEquals(lunch, plan.getLunch());
        assertEquals(dinner, plan.getDinner());
        assertEquals(snack, plan.getSnack());
    }
}