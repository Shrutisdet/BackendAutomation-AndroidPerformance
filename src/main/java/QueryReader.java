import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QueryReader {

    public static String readSqlQuery(String sqlFilePath) throws IOException {

        String query = Files.readString(Paths.get(sqlFilePath));

        return query;
    }
}
