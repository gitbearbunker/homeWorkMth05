import org.example.PhoneBook;
import org.junit.jupiter.api.*;
import java.io.*;


public class PhoneBookTest {

    PhoneBook book;

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void set() {
        book = new PhoneBook();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void clean() {
        book = null;
        System.setOut(null);
    }

    @Test
    public void addTest() {
        //arrange
        String name = "Vasya";
        String number = "8 800 555 55 55";

        String name1 = "Petya";
        String number1 = "8 800 555 55 55";

        int expectedResult = 2;
        //act
        book.add(name, number);
        int result = book.add(name1, number1);

        //assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void addTestSameName() {
        //arrange
        String name = "Vasya";
        String number = "8 800 555 55 55";

        String name1 = "Petya";
        String number1 = "8 800 555 55 55";

        int expectedResult = 1;
        //act
        book.add(name, number);
        int result = book.add(name1, number1);

        //assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void findByNumberTrueTest() {
        //arrange
        String name = "Vasya";
        String number = "8 800 555 55 55";
        String expectedResult = "Vasya";

        //act
        book.add(name, number);
        String result = book.findByNumber(number);

        //assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void findByNumberFalseTest() {
        //arrange
        String name = "Vasya";
        String number = "8 800 555 55 55";

        //act
        book.add(name, number);
        String result = book.findByNumber("8 800 555 55 57");

        //assert
        Assertions.assertEquals(null, result);
    }

    @Test
    public void findByNameTrueTest() {
        //arrange
        String name = "Vasya";
        String number = "8 800 555 55 55";

        String name1 = "Petya";
        String number1 = "8 800 555 55 55";

        String expectedResult = "8 800 555 55 55";
        //act
        book.add(name, number);
        book.add(name1, number1);

        String result = book.findByName("Petya");

        //assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void findByNameFalseTest() {
        //arrange
        String name = "Vasya";
        String number = "8 800 555 55 55";

        String name1 = "Petya";
        String number1 = "8 800 555 55 55";

        String expectedResult = null;
        //act
        book.add(name, number);
        book.add(name1, number1);

        String result = book.findByName("Petya");

        //assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void printAllNamesTest() {
        //arrange
        String name = "Ab";
        String number = "8 800 555 55 55";

        String name1 = "Aab";
        String number1 = "8 800 555 55 55";

        String name2 = "Aaab";
        String number2 = "8 800 555 55 55";

        String name3 = "B";
        String number3 = "8 800 555 55 55";

        String expectedResult = "Aaab\r\nAab\r\nAb\r\nB\r\n";

        //act
        book.add(name, number);
        book.add(name1, number1);
        book.add(name2, number2);
        book.add(name3, number3);

        book.printAllNames();

        //assert
        Assertions.assertEquals(expectedResult, output.toString());

    }

}