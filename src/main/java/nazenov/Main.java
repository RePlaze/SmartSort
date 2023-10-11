package nazenov;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int columnIndex = 1;

        // Create sample data
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"", "apple", "1"});
        rows.add(new String[]{null, "banana", "2"});
        rows.add(new String[]{"2", "", "3"});
        rows.add(new String[]{"1", "apple123", "4"});
        rows.add(new String[]{"9", "banana123", "5"});
        rows.add(new String[]{"6", "apple1banana2", "6"});

//        List<String[]> rows = new ArrayList<>();
//        rows.add(new String[]{"5", "Apple"});
//        rows.add(new String[]{"1", "Banana"});
//        rows.add(new String[]{"2", null});
//        rows.add(new String[]{"9", "banana123", "5"});
//        rows.add(new String[]{"6", "apple1banana2", "6"});

        List<String> columnData = rows.stream()
                .map(row -> row.length > columnIndex ? row[columnIndex] : "")
                .sorted(new CustomComparator())
                .collect(Collectors.toList());

        IntStream.range(0, rows.size()).forEach(i -> {
            if (i < columnData.size())
                rows.get( i )[columnIndex] = Objects.requireNonNullElse( columnData.get(i), "null" );
        });


        System.out.println(columnData);
        for (String[] row : rows)
            System.out.println( Arrays.toString( row ) );
    }

    public static class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            if (s1 == null && s2 == null) return 0;
            if (s1 == null) return -1;
            if (s2 == null) return 1;

            boolean isDigit1 = s1.matches(".*\\d.*");
            boolean isDigit2 = s2.matches(".*\\d.*");

            if (!isDigit1 && !isDigit2)
                return s1.compareTo(s2);
            if (!isDigit1)
                return -1;
            if (!isDigit2)
                return 1;

            int digitValue1 = Integer.parseInt(s1.replaceAll("\\D", ""));
            int digitValue2 = Integer.parseInt(s2.replaceAll("\\D", ""));
            return Integer.compare(digitValue1, digitValue2);
        }
    }
}