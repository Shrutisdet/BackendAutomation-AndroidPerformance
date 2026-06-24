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

            writer.write(".q");
            writer.newLine();

            writer.flush();
        }

        String value = "";

        // Read result
//        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;

            while ((line = reader.readLine()) != null) {
//                output.append(line).append("\n");

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith(">")) {
                    continue;
                }

                if (line.contains("Query executed")) {
                    continue;
                }
                if (line.contains("CTRL-D")) {
                    continue;
                }
                if (line.matches(("[-\\s]+"))) {
                    continue;
                }

                if (line.contains("process_name")
                        || line.contains("cpu_time_sec")
                        || line.contains("peak_memory_mb")
                        || line.contains("total_frames")
                        || line.contains("fps")
                        || line.contains("thermal_conditioning")) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts.length > 0) {
                    value = parts[parts.length - 1];
                }

            }
        }
        return value;
    }



        // Read result
//        StringBuilder output = new StringBuilder();
//
//        try (BufferedReader reader = new BufferedReader(
//                new InputStreamReader(process.getInputStream()))) {
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                output.append(line).append("\n");
//            }
//        }
//        String sqlQueryResult = output.toString();
//        return sqlQueryResult;
//    }
}