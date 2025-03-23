import java.util.ArrayList; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class testing{
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Enter 1 to List all available books in the book store.");
                    System.out.println("\nE-Book Reader Menu:");
                    System.out.println("1. List Available Books");
                    System.out.println("2. List Purchased Books");
                    System.out.println("3. Purchase a Book");
                    System.out.println("4. Read a Book");
                    System.out.println("5. Search in a Book");
                    System.out.println("6. Display Top Words in a Book");
                    System.out.println("7. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
    }}