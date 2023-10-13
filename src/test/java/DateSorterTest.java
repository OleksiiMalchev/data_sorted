import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.test.DateSorter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DateSorterTest {
    private DateSorter dateSorter;

    @BeforeEach
    public void setUp() {
        dateSorter = new DateSorter();
    }

    @Test
    public void testSortDatesWithEmptyList() {
        List<LocalDate> unsortedDates = new ArrayList<>();
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        assertTrue(sortedDates.isEmpty());
    }

    @Test
    public void testSortDatesWithSingleDate() {
        List<LocalDate> unsortedDates = new ArrayList<>();
        LocalDate date = LocalDate.of(2023, 1, 1);
        unsortedDates.add(date);
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        assertEquals(date, sortedDates.iterator().next());
    }

    @Test
    public void testSortDatesWithAllDatesContainingR() {
        List<LocalDate> unsortedDates = new ArrayList<>();
        unsortedDates.add(LocalDate.of(2023, 3, 1));
        unsortedDates.add(LocalDate.of(2023, 1, 15));
        unsortedDates.add(LocalDate.of(2023, 12, 20));
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        // Dates are expected to be sorted in reverse order of addition.
        Collections.reverse(unsortedDates);
        assertArrayEquals(unsortedDates.toArray(), sortedDates.toArray());
    }

    @Test
    public void testSortDatesWithAllDatesNotContainingR() {
        List<LocalDate> unsortedDates = new ArrayList<>();
        unsortedDates.add(LocalDate.of(2023, 4, 1));
        unsortedDates.add(LocalDate.of(2023, 6, 15));
        unsortedDates.add(LocalDate.of(2023, 2, 20));
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        // Dates are expected to be sorted in order of addition.
        assertArrayEquals(unsortedDates.toArray(), sortedDates.toArray());
    }

    @Test
    public void testSortDatesWithMixedDates() {
        List<LocalDate> unsortedDates = new ArrayList<>();
        unsortedDates.add(LocalDate.of(2021, 1, 1));  // Date with 'r'
        unsortedDates.add(LocalDate.of(2022, 5, 1));  // Date without 'r'
        unsortedDates.add(LocalDate.of(2023, 2, 1));  // Date with 'r'
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        // Dates with 'r' are expected to come before dates without 'r'.
        assertArrayEquals(new LocalDate[]{LocalDate.of(2021, 1, 1),
                LocalDate.of(2023, 2, 1), LocalDate.of(2022, 5, 1)},
                sortedDates.toArray());
    }

    @Test
    public void testSortDatesWithMultipleDates() {
        List<LocalDate> unsortedDates = new ArrayList<>();
        unsortedDates.add(LocalDate.of(2004, 7, 1));
        unsortedDates.add(LocalDate.of(2005, 1, 2));
        unsortedDates.add(LocalDate.of(2007, 1, 1));
        unsortedDates.add(LocalDate.of(2032, 5, 3));

        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        Collection<LocalDate> expectedDates = new ArrayList<>();
        expectedDates.add(LocalDate.of(2005, 1, 2));
        expectedDates.add(LocalDate.of(2007, 1, 1));
        expectedDates.add(LocalDate.of(2032, 5, 3));
        expectedDates.add(LocalDate.of(2004, 7, 1));


        // Check that the dates are sorted correctly.
        assertArrayEquals(expectedDates.toArray(), sortedDates.toArray());
    }
}
