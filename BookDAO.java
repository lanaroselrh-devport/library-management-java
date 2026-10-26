import java.sql.*;

public class BookDAO {

    public void addBook(String title, String author) {
        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, author);
            ps.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewBooks() {
        String sql = "SELECT * FROM books";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            printBooks(rs);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchBook(String keyword) {
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            printBooks(rs);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void issueBook(int id) {
        String sql = "UPDATE books SET available = FALSE WHERE id = ? AND available = TRUE";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book not found or already issued.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void returnBook(int id) {
        String sql = "UPDATE books SET available = TRUE WHERE id = ? AND available = FALSE";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book not found or was not issued.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printBooks(ResultSet rs) throws SQLException {
        System.out.println("ID | Title | Author | Available");
        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.println(rs.getInt("id") + " | " + rs.getString("title")
                    + " | " + rs.getString("author") + " | " + rs.getBoolean("available"));
        }
        if (!found) {
            System.out.println("No books found.");
        }
    }
}