
package nazenov;

import java.util.Comparator;
import java.util.List;

// ⚠️This is a non-singleton copy of Task1Impl, used for testing purposes.⚠️
public class Task1ImplTestClass implements IStringRowsListSorter {
    @Override
    public void sort(List<String[]> rows, int columnIndex) {
        List<String[]> originalRows = List.copyOf(rows); // (Create a stable copy)

        rows.sort((row1, row2) -> {
            String s1 = (row1.length > columnIndex) ? row1[columnIndex] : "";
            String s2 = (row2.length > columnIndex) ? row2[columnIndex] : "";

            int result = new CustomComparator().compare(s1, s2);
            if (result == 0) // (Preserve original order for equal elements)
                return originalRows.indexOf(row1) - originalRows.indexOf(row2);

            return result;
        });
    }

    private static class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            if (s1 == null && s2 == null) return 0;
            if (s1 == null) return -1;
            if (s2 == null) return 1;

            boolean isDigit1 = s1.matches(".*\\d.*");
            boolean isDigit2 = s2.matches(".*\\d.*");

            if (!isDigit1 && !isDigit2)
                return s1.compareTo(s2);
            if (!isDigit1) return -1;
            if (!isDigit2) return 1;

            int digitValue1 = Integer.parseInt(s1.replaceAll("\\D", ""));
            int digitValue2 = Integer.parseInt(s2.replaceAll("\\D", ""));
            return Integer.compare(digitValue1, digitValue2);
        }
    }
}

