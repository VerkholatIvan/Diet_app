import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class customise_daily_planTest {

    @Test
    public void testRemoveFood() {
        // Create an instance of the class containing the remove_food() method
        App controller = new App();
        var plan = new customise_daily_plan(controller);
        Food food = new Food();

        // Set up initial state with some sample data
        plan.calories_value = 500; // Sample value
        plan.calories.setText("Total calories: 500");

        plan.protein_value = 20; // Sample value
        plan.protein.setText("Total protein: 20");

        plan.fat_value = 15; // Sample value
        plan.fat.setText("Total fat: 15");

        plan.sugar_value = 30; // Sample value
        plan.sugar.setText("Total sugar: 30");

        plan.breakfast_content.setText("Sample Breakfast");
        plan.lunch_content.setText("Sample Lunch");
        plan.dinner_content.setText("Sample Dinner");
        plan.snack_content.setText("Sample Snack");

        plan.breakfast_.add(0,food);
        plan.lunch_.add(0,food);
        plan.dinner_.add(0,food);
        plan.snack_.add(0,food);

        plan.food_intake.setText("Sample Food Intake");
        plan.portion_.setText("Sample Portion");

        // Call the method you want to test
        plan.remove_food();

        // Verify that the fields and components are reset to their initial states
        assertEquals(0, plan.calories_value);
        assertEquals("Total calories: None", plan.calories.getText());

        assertEquals(0, plan.protein_value);
        assertEquals("Total protein: None", plan.protein.getText());

        assertEquals(0, plan.fat_value);
        assertEquals("Total fat: None", plan.fat.getText());

        assertEquals(0, plan.sugar_value);
        assertEquals("Total sugar: None", plan.sugar.getText());

        assertEquals("Breakfast:", plan.breakfast_content.getText());
        assertEquals("Lunch:", plan.lunch_content.getText());
        assertEquals("Dinner", plan.dinner_content.getText());
        assertEquals("Snack:", plan.snack_content.getText());

        assertTrue(plan.breakfast_.isEmpty());
        assertTrue(plan.lunch_.isEmpty());
        assertTrue(plan.dinner_.isEmpty());
        assertTrue(plan.snack_.isEmpty());

        assertEquals("", plan.food_intake.getText());
        assertEquals("", plan.portion_.getText());
    }
}