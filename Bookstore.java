//program acts like an ebook reader
import java.util.ArrayList; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Bookstore{
    //takes in inputs from users to carry out the commands.
    public static void main(String[] args) throws IOException {
            Inventory bookStore = new Inventory();
            bookStore.addBooks();

            Library userLibrary = new Library();
            boolean flag = true;
            Scanner scanner = new Scanner(System.in);
                while (flag) {
                    System.out.println("Type 1 to list all available books in the bookstore");
                    System.out.println("Type 2 to purchase a book");
                    System.out.println("Type 3 to list all books you have purchased");
                    System.out.println("Type 4 to read a book in your library");
                    System.out.println("Type 5 to find lines with a specified string in a book");
                    System.out.println("Type 6 to find the top 10 most frequent words in a book");
                    int pick = scanner.nextInt();
                    scanner.nextLine();

                    switch (pick) {
                        case 1:
                            ArrayList<Book> availableBooks = bookStore.getAvailableBooks();
                            System.out.println("Here are the books available to buy");
                            for (Book books : availableBooks) {
                                System.out.println(books.getTitle());
                            }
                            break;
                        case 2:
                            System.out.print("Type the title of the book to buy: ");
                            String purchaseTitle = scanner.nextLine();
                            Book bookToPurchase = Inventory.getBookByTitle(purchaseTitle);
                            if (bookToPurchase != null) {
                                userLibrary.buy(bookToPurchase);
                                System.out.println("Book purchased correctly");
                            } else {
                                System.out.println("Book not present");
                            }
                            break;
                        case 3:
                            ArrayList<Book> purchasedBooks = userLibrary.getLibraryBooks();
                            System.out.println("Here are the purchased Books");
                            for (Book book : purchasedBooks) {
                                System.out.println(book.getTitle());
                            }
                            break;
                        case 4:
                            System.out.print("Type the title of the book to read: ");
                            String readTitle = scanner.nextLine();
                            Book bookToRead = userLibrary.getBook(readTitle);
                            if (bookToRead != null) {
                                int pageNumber = 0;
                                while (pageNumber!=-1) {
                                    ArrayList<String> page = bookToRead.getPage(pageNumber);
                                    if (page.isEmpty()) {
                                        System.out.println("There are no more pages");
                                        break;
                                    }
                                    for (String line : page) {
                                        System.out.println(line);
                                    }
                                    System.out.print("Type next for next page, type get to get a specific page, and anything else to exit:");
                                    String command = scanner.nextLine();
                                    if (command.equals("next")) {
                                        pageNumber++;
                                    } else if (command.equals("get")) {
                                        System.out.print("Type the page number to go to: ");
                                        pageNumber = scanner.nextInt() - 1;
                                        scanner.nextLine();
                                    } else {
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("Book not found in your library.");
                            }
                            break;
                        case 5:
                            System.out.print("Type the title of the book to search in ");
                            String searchTitle = "docs//"+scanner.nextLine()+".txt";
                            System.out.print("Type the string to search for ");
                            String searchString = scanner.nextLine();
                            Book.bookSearch(searchTitle, searchString);
                        break;
                        case 6:
                            System.out.print("Type the title of the book to find top words in ");
                            String topWordTitle = "docs//"+scanner.nextLine()+".txt";
                            Book.topWords(topWordTitle);
                            break;
                        default:
                            System.out.println("Invalid input please try again.");
                    }

                }
                scanner.close();
        }
//Has methods and stores data about books.
static class Book{
    private String title;
    private ArrayList<String> data;
public Book(String title, String filePath) throws IOException {
        this.title = title;
        this.data = getData(filePath);
    }
    //gets all the written lines in a book
    private static ArrayList<String> getData(String file) throws IOException {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        }catch(java.io.FileNotFoundException e){
            System.out.println("File not found");
        }
        return data;
    }
    //gets the title of a book
  public String getTitle() {
        return title;
    }
    //gets a specific page of the book
    public ArrayList<String> getPage(int pageNumber) {
        int first = pageNumber * 20;
        int last = Math.min(first + 20, data.size());
        ArrayList<String> page = new ArrayList<>();
        for (int i = first; i < last; i++) {
        page.add(data.get(i));
    }
    return page;
    }
    //Print all the lines in a book that match a specified string
    public static void bookSearch(String searchTitle, String key) throws IOException{
        ArrayList<String> Book = getData(searchTitle);
        for (String line : Book){
            if (line.contains(key)){
                System.out.println(line);
            }
        }
    

    }
    //finds and prints top 10 words in a book
    public static void topWords(String file) throws IOException{
        ArrayList <String> frequencyWords = new ArrayList<String>();
        String[] stopWords = {"i","me","my","myself","we","our","ours","ourselves","you","your","yours","yourself","yourselves","he","him","his","himself","she","her","hers","herself","it","its","itself","they","them","their","theirs","themselves","what","which","who","whom","this","that","these","those","am","is","are","was","were","be","been","being","have","has","had","having","do","does","did","doing","a","an","the","and","but","if","or","because","as","until","while","of","at","by","for","with","about","against","between","into","through","during","before","after","above","below","to","from","up","down", "in","out","on","off", "over","under","again","further","then","once","here","there","when","where","why","how","all","any","both","each","few","more","most","other","some","such","no","nor","not","only","own","same","so","than","too","very", "s","t","can","will","just","don","should","now"};
        
        
        ArrayList <String> data=getData(file);


        for (String line : data) {
        String[] words = line.split(" ");
        for (String word : words) {
            // add trim and lowercase to reduce errors
            word = word.replaceAll("[!-,.:;?/{}*&^%$#@`|+=]", "").trim().toLowerCase();
            if (!word.isEmpty() && !contains(stopWords, word)) {
                if (!containsArrayList(frequencyWords, word)) {
                    frequencyWords.add(word);
                }
            }
        }
    }

    int[] frequency = new int[frequencyWords.size()];
        for (String line: data ){
        String[] words= line.split(" ");
        for (String word: words){
            word = word.replaceAll("[!-,.:;?/{}*&^%$#@`|+=]", "").trim().toLowerCase();
        if (!word.isEmpty() && !contains(stopWords, word)){
        if(!containsArrayList(frequencyWords, word)){
        frequencyWords.add(word);
        int index = frequencyWords.indexOf(word);
        frequency[index]= 1;
        }else{
        int index = frequencyWords.indexOf(word);
        frequency[index]+= 1;
        }
    }
        }
    }
    for (int i = 0; i < frequency.length; i++)
		{
			for (int j = i + 1; j < frequency.length; j++)
			{
				if(frequency[i] < frequency[j])
				{
					int temp = frequency[i];
					frequency[i] = frequency[j];
					frequency[j] = temp;
                    String tempFrequency = frequencyWords.get(i);
                    frequencyWords.set(i, frequencyWords.get(j));
                    frequencyWords.set(j, tempFrequency );
				}		
			}
		}
        try{
        for (int x=0; x<10; x++){
            System.out.println(frequencyWords.get(x)+":"+ frequency[x]);}
            }catch(java.lang.IndexOutOfBoundsException e){
                System.out.println("Index out of bounds");
        }
}
public static boolean contains(String[] arrays, String value) {
    for (String element : arrays) {
        if (element.equals(value)) {
            return true;
        }
    }
    return false;
}
public static boolean containsArrayList(ArrayList<String> frequencyWords, String value) {
    for (String element : frequencyWords) {
        if (element.equals(value)) {
            return true;
        }
    }
    return false;
}
}
//Stores all the books in the users library
static class Library{
    private final ArrayList<Book> purchased;
    public Library() {
    this.purchased = new ArrayList<>();
    }
    //buys a book
    public void buy(Book book) {
        purchased.add(book);
    }
    //returns a list of books in the users library
    public ArrayList<Book> getLibraryBooks() {
        return purchased;
    }
    //returns book in the users library
    public Book getBook(String title) {
        for (Book book : purchased) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

}
//Stores all available books in the bookstore
static class Inventory{
    private static ArrayList<Book> availableBooks;
    //adds all available books and stores them
    public Inventory() {
        availableBooks = new ArrayList<>();
    }
    public void addBooks() throws IOException {
    Book book1 = new Book("Wales", "docs\\Wales.txt");
    availableBooks.add(book1);
    Book book2 = new Book("Dracula", "docs\\Dracula.txt");
    availableBooks.add(book2);
    Book book3 = new Book("Thrasymachus", "docs\\Thrasymachus.txt");
    availableBooks.add(book3);
    Book book4 = new Book("The Merry Five", "docs\\The Merry Five.txt");
    availableBooks.add(book4);
    Book book5 = new Book("Lucia in London", "docs\\Lucia in London.txt");
    availableBooks.add(book5);
    }
    //returns a list of all available books
    public ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }
    //returns a book corresponding to a given title
    public static Book getBookByTitle(String title) {
        for (Book book : availableBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }else if ((book.getTitle().toLowerCase()).contains(title.toLowerCase())){
                return book;
            }
            }
        
        return null;
}
    }
}



