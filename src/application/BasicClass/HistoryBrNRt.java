package application.BasicClass;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HistoryBrNRt {

    private String pathDataFile;


    public HistoryBrNRt(String initialString) {
        this.pathDataFile = initialString;
    }

    // Phương thức để lưu chuỗi vào file
    public void saveStringToFile(String data) {
        try (FileWriter fileWriter = new FileWriter(this.pathDataFile, true)) { 
            fileWriter.write(data + "\n");
            System.out.println("Lưu chuỗi vào file thành công.");
        } catch (IOException e) {
            System.out.println("Có lỗi khi lưu chuỗi vào file: " + e.getMessage());
        }
    }


    // Phương thức để đọc toàn bộ nội dung từ file và trả về dưới dạng một chuỗi duy nhất
    public String load() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.pathDataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); 
            }
        } catch (IOException e) {
            System.out.println("Có lỗi khi đọc file: " + e.getMessage());
        }
        return content.toString(); 
    }

    public List<String> readAllLinesFromFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.pathDataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Có lỗi khi đọc file: " + e.getMessage());
        }
        return lines;
    }
        public static boolean validateAccount(List<Account> accounts, String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
