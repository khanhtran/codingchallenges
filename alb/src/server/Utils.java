package server;

import java.io.*;
import java.util.Date;

public class Utils {
    public static String readRequest(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder requestBuilder = new StringBuilder();
            String line;
            while (!(line = br.readLine()).isBlank()) {
                requestBuilder.append(line + "\r\n");
            }

            return requestBuilder.toString();
    }

    public static void writeOKResponse(OutputStream out, String content) {
        PrintWriter pw = new PrintWriter(out);
        pw.println("HTTP/1.1 200 OK");
        pw.println(new Date());
        pw.println();
        pw.println(content);
        pw.flush();
        pw.close();
    }
}
