import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BookSelector {
    private ArrayList<Book> selectedBooks;
    private BookLoader bookLoader;
    private Random random;
    private Scanner scanner;
    private final float TOLERANCE = 2.0f;

    public BookSelector(BookLoader bookLoader) {
	this.bookLoader = bookLoader;
	this.selectedBooks = new ArrayList<>();
	this.random = new Random();
	this.scanner = new Scanner(System.in);
    }
    
public float getTargetTimeFromUser() {
    float targetTime = 0;
    boolean validInput = false;
    
    while (!validInput) {
        System.out.print("Enter target reading time in minutes: ");
        if (scanner.hasNextFloat()) {
            targetTime = scanner.nextFloat();
            if (targetTime > 0) {
                validInput = true;
            } else {
                System.out.println("Please enter a positive number.");
            }
        } else {
            System.out.println("Please enter a valid number.");
            scanner.next(); 
        }
    }
    return targetTime;
}

public ArrayList<Book> selectBooks(float targetTime) {
    ArrayList<Book> availableBooks = new ArrayList<>(bookLoader.getBooks());
    float currentTotalTime = 0;
    
    while (currentTotalTime < (targetTime - TOLERANCE) && !availableBooks.isEmpty()) {
        // Get random index
        int randomIndex = random.nextInt(availableBooks.size());
        Book selectedBook = availableBooks.get(randomIndex);
        
        // Check if adding this book keeps us within target time + tolerance
        if (currentTotalTime + selectedBook.getLength() <= targetTime + TOLERANCE) {
            selectedBooks.add(selectedBook);
            currentTotalTime += selectedBook.getLength();
            // Remove book to prevent selecting it again
            availableBooks.remove(randomIndex);
        } else {
            // Remove book as it's too long for remaining time
            availableBooks.remove(randomIndex);
        }
    }
    
    return selectedBooks;
}

public void displaySelectedBooks() {
    float totalTime = 0;
    System.out.println("\nSelected Books:");
    for (Book book : selectedBooks) {
        System.out.println("- " + book.getTitle() + " (" + book.getLength() + " minutes)");
        totalTime += book.getLength();
    }
    System.out.println("\nTotal reading time: " + totalTime + " minutes");
}
}
