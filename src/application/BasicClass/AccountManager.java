package application.BasicClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountManager {
    private String filePath;

    public AccountManager(String filePath) {
        this.filePath = filePath;
    }

    // Thêm một tài khoản mới và lưu vào file
    public void addAccount(Account account) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(account.getUsername() + "," + account.getPassword() + "," + account.getName() + "," + account.getId());
        }
    }

    // Xóa tài khoản dựa trên username
    public void removeAccount(String username) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(username)) {
                    lines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Tải danh sách tài khoản từ file
    public List<Account> loadAccounts() throws IOException {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0];
                    String password = parts[1];
                    String name = parts[2];
                    String id = parts[3];
                    accounts.add(new Account(username, password, name, id));
                }
            }
        }
        return accounts;
    }

    // Tìm kiếm tài khoản theo username
    public Account searchAccountByUsername(String username) throws IOException {
        List<Account> accounts = loadAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return account;
            }
        }
        return null; // Trả về null nếu không tìm thấy tài khoản nào
    }
    

    public boolean authenticate(String username, String password) throws IOException {
        List<Account> accounts = loadAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
   
}
