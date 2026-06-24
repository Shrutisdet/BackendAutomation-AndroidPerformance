import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TraceProcessorRunner {

    
    public static void main(String[] args) throws IOException {

        String traceProcessorPath =
                "processor/trace_processor_shell.exe";

        File traceFolder = new File("traces");

        File[] traceFiles = traceFolder.listFiles(
                (dir, name) -> name.toLowerCase().endsWith(".pftrace"));

        String[] sqlFiles = {
                "cpu.sql",
                "memory.sql",
                "fps.sql",
                "thermalnew.sql"
        };

        String[] metrics = {
                "cpu",
                "memory",
                "fps",
                "thermalnew"
        };

        String timestamp =
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String csvPath =
                "reports/PerformanceReport_" + timestamp + ".csv";


        for (File trace : traceFiles) {

            String traceFilePath = trace.getAbsolutePath();

            CsvGenerator.getTraceFilName(csvPath,traceFilePath);


            for (int i = 0; i < sqlFiles.length; i++) {

                String sqlFilePath =
                        QueryReader.readSqlQuery("queries/" + sqlFiles[i]);

                String sqlQueryResult =
                        PerfettoExecutor.perfettoExecute(
                                traceProcessorPath,
                                traceFilePath,
                                sqlFilePath
                        );

                CsvGenerator.appendResult(
                        csvPath,
                        metrics[i],
                        sqlQueryResult
                );
            }
        }
    }
}
