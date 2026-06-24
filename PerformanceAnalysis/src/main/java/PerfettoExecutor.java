import java.io.*;

public class PerfettoExecutor {

    public static String perfettoExecute(String traceProcessorPath, String traceFilePath, String sqlQuery) throws IOException {

        // Launch trace processor
        ProcessBuilder pb = new ProcessBuilder(
                traceProcessorPath,
                traceFilePath
        );

        Process process = pb.start();

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(process.getOutputStream()))) {

            writer.write(sqlQuery);
            writer.newLine();
            writer.flush();
        }

        // Read result
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }
        String sqlQueryResult = output.toString();
        return sqlQueryResult;
    }
}