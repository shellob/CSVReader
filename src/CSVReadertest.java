import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReadertest {
    public static List<Person> readPersonsFromCSV(String csvFilePath) {
        List<Person> persons = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFilePath))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(';')
                        .build())
                .build()) {

            String[] nextLine;
            reader.readNext(); // skip the header row

            while ((nextLine = reader.readNext()) != null) {
                try {
                    int id = Integer.parseInt(nextLine[0]);
                    String name = nextLine[1];
                    String gender = nextLine[2];
                    LocalDate birthDate = LocalDate.parse(nextLine[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    String divisionName = nextLine[4];
                    double salary = Double.parseDouble(nextLine[5]);

                    Division division = new Division(id, divisionName);
                    Person person = new Person(id, name, gender, division, salary, birthDate);
                    persons.add(person);

                } catch (NumberFormatException e) {
                    System.err.println("Некорректное значение зарплаты: " + nextLine[5]);
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return persons;
    }
}
