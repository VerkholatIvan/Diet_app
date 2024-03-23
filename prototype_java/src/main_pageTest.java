import org.junit.jupiter.api.Test;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;

class main_pageTest {

    @Test
    public void testRemove() {
        App controller = new App();
        main_page page = new main_page(controller);
        Food food = new Food();


        page.breakfast_content.setText("Sample Breakfast");
        page.lunch_content.setText("Sample Lunch");
        page.dinner_content.setText("Sample Dinner");
        page.snack_content.setText("Sample Snacks");
        page.food_intake.setText("Sample Food Intake");

        page.intake_per_day = 100;
        page.weekly_intake = 200;
        page.average_intake = 50;
        page.portion_.setText("Sample Portion");

        page.calories_counter = 500;
        page.protein_counter = 20;
        page.fat_counter = 15;
        page.sugar_counter = 30;

        page.breakfast_.add(0, food);
        page.lunch_.add(0, food);
        page.dinner_.add(0, food);
        page.snack_.add(0, food);

        page.remove();

        assertEquals("Breakfast:", page.breakfast_content.getText());
        assertEquals("Lunch:", page.lunch_content.getText());
        assertEquals("Dinner:", page.dinner_content.getText());
        assertEquals("Snacks:", page.snack_content.getText());
        assertEquals("", page.food_intake.getText());
        assertEquals(0, page.intake_per_day);
        assertEquals(0, page.weekly_intake);
        assertEquals(0, page.average_intake);
        assertEquals("", page.portion_.getText());
        assertEquals(0, page.calories_counter);
        assertEquals(0, page.protein_counter);
        assertEquals(0, page.fat_counter);
        assertEquals(0, page.sugar_counter);
        assertTrue(page.breakfast_.isEmpty());
        assertTrue(page.lunch_.isEmpty());
        assertTrue(page.dinner_.isEmpty());
        assertTrue(page.snack_.isEmpty());
    }

    @Test
    public void testSetWelcomeMessage() {
        // Create an instance of the class containing the setWelcome_message() method
        App controller = new App();
        main_page page = new main_page(controller);


        page.welcome_message.setText("Welcome, " + controller.get_name());

    }
}