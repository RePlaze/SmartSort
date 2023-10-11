import nazenov.Task1Impl;
import nazenov.Task1ImplTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase {
    private Task1ImplTestClass task1implTestClass;
    private List<String[]> rows;

    @BeforeEach
    public void setUp() {
        task1implTestClass = new Task1ImplTestClass();
        rows = new ArrayList<>();
    }

    @Test
    public void testSortWithNulls() {
        rows.add( new String[]{"5", "Apple"} );
        rows.add( new String[]{"1", "Banana"} );
        rows.add( new String[]{"2", null} );
        rows.add( new String[]{"9", "banana123", "5"} );
        rows.add( new String[]{"6", "apple1banana2", "6"} );

        task1implTestClass.sort( rows, 1 );

        List<String[]> expected = Arrays.asList(
                new String[]{"5", "null"},
                new String[]{"1", "Apple"},
                new String[]{"2", "Banana"},
                new String[]{"9", "apple1banana2", "5"},
                new String[]{"6", "banana123", "6"}
        );
        for (int i = 0; i < rows.size(); i++)
            assertArrayEquals( expected.get( i ), rows.get( i ) );
    }

    @Test
    public void testSortColumnOutOfRange() {
        rows.add( new String[]{"5", "Apple"} );
        rows.add( new String[]{"1", "Banana"} );

        assertThrows( ArrayIndexOutOfBoundsException.class, () ->
                task1implTestClass.sort( rows, 2 ) );
    }

    @Test
    public void testSortWithEmptyCells() {
        rows.add( new String[]{"5", "Apple"} );
        rows.add( new String[]{"1", "Banana"} );
        rows.add( new String[]{"2", ""} );

        task1implTestClass.sort( rows, 1 );

        List<String[]> expected = Arrays.asList(
                new String[]{"5", ""},
                new String[]{"1", "Apple"},
                new String[]{"2", "Banana"}
        );
        for (int i = 0; i < rows.size(); i++)
            assertArrayEquals( expected.get( i ), rows.get( i ) );
    }

    @Test
    public void testSortWithEqualValues() {
        rows.add( new String[]{"5", "Banana"} );
        rows.add( new String[]{"1", "Banana"} );

        task1implTestClass.sort( rows, 1 );

        List<String[]> expected = Arrays.asList(
                new String[]{"5", "Banana"},
                new String[]{"1", "Banana"}
        );
        for (int i = 0; i < rows.size(); i++)
            assertArrayEquals( expected.get( i ), rows.get( i ) );
    }

    @Test
    public void testAllColumn1() {
        rows.add( new String[]{"", "apple", "1"} );
        rows.add( new String[]{null, "banana", "2"} );
        rows.add( new String[]{"2", "", "3"} );
        rows.add( new String[]{"1", "apple123", "4"} );
        rows.add( new String[]{"9", "banana123", "5"} );
        rows.add( new String[]{"6", "apple1banana2", "6"} );

        task1implTestClass.sort( rows, 1 );

        List<String[]> expected = Arrays.asList(
                new String[]{"", "", "1"},
                new String[]{null, "apple", "2"},
                new String[]{"2", "banana", "3"},
                new String[]{"1", "apple1banana2", "4"},
                new String[]{"9", "apple123", "5"},
                new String[]{"6", "banana123", "6"}
        );
        for (int i = 0; i < rows.size(); i++)
            assertArrayEquals( expected.get( i ), rows.get( i ) );
    }

    @Test
    public void testAllColumn0() {
        rows.add( new String[]{"", "apple", "1"} );
        rows.add( new String[]{null, "banana", "2"} );
        rows.add( new String[]{"2", "", "3"} );
        rows.add( new String[]{"1", "apple123", "4"} );
        rows.add( new String[]{"6pizza9", "banana123", "5"} );
        rows.add( new String[]{"9", "apple1banana2", "6"} );

        task1implTestClass.sort( rows, 0 );

        List<String[]> expected = Arrays.asList(
                new String[]{"null", "apple", "1"},
                new String[]{"", "banana", "2"},
                new String[]{"1", "", "3"},
                new String[]{"2", "apple123", "4"},
                new String[]{"9", "banana123", "5"},
                new String[]{"6pizza9", "apple1banana2", "6"}
                );
        for (int i = 0; i < rows.size(); i++)
            assertArrayEquals( expected.get( i ), rows.get( i ) );
    }
}
