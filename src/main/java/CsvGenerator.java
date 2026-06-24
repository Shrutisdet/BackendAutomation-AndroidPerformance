//import java.io.FileWriter;
//import java.io.IOException;
//
//public class CsvGenerator {
//    public static void writeMetrics(
//            String traceFile,
//            String cpu,
//            String memory,
//            String thermal,
//            String fps) {
//
//        try {
//
//            FileWriter writer = new FileWriter("reports/metricsCollector.csv", true);
//
//            writer.write("TraceFile,CPU,Memory,Thermal,FPS\n");
//
//            writer.write(
//                    traceFile + "," +
//                            cpu + "," +
//                            memory + "," +
//                            thermal + "," +
//                            fps + "\n"
//            );
//
//            writer.close();
//
//            System.out.println("Data written successfully.");
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        }
//    }
//
//}


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class CsvGenerator {

    public static void appendResult(
            String csvPath,
            String metricName,
            String result) throws IOException {


        BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter(csvPath, true));

        writer.write(metricName + "-->" + result);
        writer.newLine();

        writer.close();
    }

    public static void getTraceFilName(String csvPath,String traceFilePath) throws IOException {
        String traceFileName = Paths.get(traceFilePath)
                .getFileName()
                .toString()
                .replace(".pftrace", "");
        BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter(csvPath, true));

        writer.write("====="+traceFileName+"=======");
        writer.newLine();

        writer.close();
    }
}
