import java.io.IOException;

public class TraceProcessorRunner {

    public static void main(String[] args) throws IOException {

        String traceProcessorPath =
                "processor/trace_processor_shell.exe";

        String traceFilePath =
                "traces/android-202652-16550-withSDK.pftrace";


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
        String csvPath = "reports/performance_report.csv";
        for (int i = 0; i < sqlFiles.length; i++) {

            String sqlFilePath =
                    QueryReader.readSqlQuery("queries/" + sqlFiles[i]);

            String sqlQueryResult =
                    PerfettoExecutor.perfettoExecute(
                            traceProcessorPath,traceFilePath,
                            sqlFilePath
                    );

            CsvGenerator.appendResult(
                    csvPath,
                    metrics[i],      // matching metric name
                    sqlQueryResult
            );
        }

//        String[] sqlFiles    = {
//                "cpu.sql",
//                "memory.sql",
//                "fps.sql",
//                "thermalnew.sql"
//        };
//        String[] metric = {
//                "cpu",
//                "memory",
//                "fps",
//                "thermalnew"
//        };
//        for (String sqlFile : sqlFiles) {
//            String sqlFilePath = QueryReader.readSqlQuery("queries/" +sqlFile);
//            String sqlQueryResult = PerfettoExecutor.perfettoExecute(
//                    traceFilePath,
//                    traceProcessorPath,
//                    sqlFilePath
//            );
//            String csvPath = "reports/performance_report.csv";
//            for (String metricName : metric)
//            CsvGenerator.appendResult(csvPath,metricName,sqlQueryResult);
//        }
//
//
//    }
    }
}
