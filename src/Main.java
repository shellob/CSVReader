import com.opencsv.exceptions.CsvValidationException;

import java.util.List;
public class Main {
    public static void main(String[] args) throws CsvValidationException {
        String csvFilePath = "foreign_names.csv";
        List<Person> persons = CSVReadertest.readPersonsFromCSV(csvFilePath);

        persons.forEach(System.out::println);
    }
}
