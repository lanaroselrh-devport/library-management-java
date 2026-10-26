import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO dao = new BookDAO();
        int choice = 0;
        do {
            System.out.println("\n1. Add Book\n2. View Books\n3. Search Book\n4. Issue Book\n5. Return Book\n6. Exit");
            System.out.print("Enter choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 6.");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    dao.addBook(title, author);
                    break;
                case 2:
                    dao.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter title or author to search: ");
                    dao.searchBook(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Enter book ID to issue: ");
                    try {
                        dao.issueBook(Integer.parseInt(sc.nextLine().trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;
                case 5:
                    System.out.print("Enter book ID to return: ");
                    try {
                        dao.returnBook(Integer.parseInt(sc.nextLine().trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
        sc.close();
    }
}