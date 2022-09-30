package solent.oodev.utils;

import org.junit.jupiter.api.Test;
import solent.oodev.utils.UserInputUtils;

import static org.junit.jupiter.api.Assertions.*;

class UserInputUtilsTest {

    @Test
    void removeSpecialCharacters() {
        // the method removes the special characters from user input
        assertEquals("h1Test. Special charactersh2!", UserInputUtils.removeSpecialCharacters("<h1>Test. Special characters</h2>#$!"));

    }

    @Test
    void capitalizeWords() {
        // the method trims, removes double spaces and capitalizes each word of the user input
        // for the purposes of comparison and searching

        assertEquals("Tesr Gttest Proba", UserInputUtils.capitalizeWords(" tesr   gTtest  pROba  "));
    }

    @Test
    void prepareTitleForSqlQuery() {

        // Prepare sql statement for containing search instead of full match
        assertEquals("description LIKE \"%Microsoft%\" AND description LIKE \"%XBox%\"",
                UserInputUtils.prepareForSqlQuery("description","Microsoft XBox"));
    }
}