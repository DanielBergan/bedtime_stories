import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class BookLoader {
	private ArrayList<Book> books;

	public BookLoader() {
		books = new ArrayList<>();
	}

	public void loadBooks(String filename) throws BookLoadException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        int lineNumber = 0;  // Keep track of line numbers for error messages
        
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            
            // Check for empty lines
            if (line.trim().isEmpty()) {
                continue;  // Skip empty lines
            }
            
            String[] parts = line.split(",");
            // Check for correct number of fields
            if (parts.length != 2) {
                throw new BookLoadException("Invalid format: expected 2 fields, got " + parts.length, lineNumber);
            }
            
            // Check for empty title
            if (parts[0].trim().isEmpty()) {
                throw new BookLoadException("Empty title", lineNumber);
            }
            
            try {
                float length = Float.parseFloat(parts[1].trim());
                // Check for negative length
                if (length < 0) {
                    throw new BookLoadException("Negative length not allowed: " + length, lineNumber);
                }
                Book book = new Book(parts[0], length);
                books.add(book);
            } catch (NumberFormatException e) {
                throw new BookLoadException("Invalid length format: " + parts[1], lineNumber);
            }
        }
        reader.close();
    } catch (IOException e) {
        throw new BookLoadException("Error reading file: " + e.getMessage());
    }
}

	public ArrayList<Book> getBooks() {
		return books;
	}
}
