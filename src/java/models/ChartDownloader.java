package models;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *
 * @author Abdur Rahman
 */
public class ChartDownloader extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = "/path/to/save/image/"; // Update with the actual path to save the image

        // Create a directory if it doesn't exist
        File directory = new File(savePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Get the image data from the request
        InputStream inputStream = request.getInputStream();

        // Create a unique file name for the image
        String fileName = "chart_" + System.currentTimeMillis() + ".png";
        String filePath = savePath + fileName;

        // Write the image data to a file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // Close the input stream
        inputStream.close();

        // Send response indicating successful download
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Image downloaded successfully as " + fileName);
    }
}
