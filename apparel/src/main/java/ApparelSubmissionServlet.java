import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/submitApparel")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ApparelSubmissionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Directory to store uploaded files
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form data
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String apparelType = request.getParameter("apparelType");
        String apparelCondition = request.getParameter("apparel_condition");
        String location = request.getParameter("location");

        // Handle the image file upload
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // Save the uploaded file to a folder
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String filePath = uploadPath + File.separator + fileName;
        try (InputStream fileContent = filePart.getInputStream();
             FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        // Store data in the database
        String jdbcUrl = "jdbc:mysql://localhost:3306/apparel_db?useSSL=false&serverTimezone=UTC";
        
        String dbUser = "root"; // Use your MySQL username
        String dbPass = "Lucky"; // Use your MySQL password
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

            String sql = "INSERT INTO apparel (username, email, apparel_type, apparel_condition, location, image_path) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, apparelType);
            statement.setString(4, apparelCondition);
            statement.setString(5, location);
            statement.setString(6, UPLOAD_DIR + "/" + fileName);

            statement.executeUpdate();
            connection.close();

            response.getWriter().println("Apparel submission successful!");
            
        }
        catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Apparel submission failed."+e);
        }
    }
}
