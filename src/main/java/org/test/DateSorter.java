package org.test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * <p>
 * package sample;
 * <p>
 * import java.time.LocalDate;
 * import java.util.Collection;
 * import java.util.List;
 * <p>
 * /**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * Implement in single class. Don't chane constructor and signature method.
 */
public class DateSorter {

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        // sorting dates
        unsortedDates.sort((prevDate, nextDate) -> {
            String prevMonth = prevDate.getMonth().toString().toLowerCase();
            String nextMonth = nextDate.getMonth().toString().toLowerCase();
            return prevMonth.contains("r") && !nextMonth.contains("r") ? -1 : // prevDate with 'r' comes before nextDate without 'r'.
                    !prevMonth.contains("r") && nextMonth.contains("r") ? 1 : // nextDate with 'r' comes before prevDate without 'r'.
                            !prevMonth.contains("r") && !nextMonth.contains("r") ? nextDate.compareTo(prevDate) : 0; // If both dates are without 'r', sort by date.
        });
        return unsortedDates;
    }
}