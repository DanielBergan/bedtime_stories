public class Main {
	public static void main(String[] args) {
		try {
			// Create and load books
			BookLoader loader = new BookLoader();
			loader.loadBooks("books.csv"); // Make sure this matches your file name

			// Create selector and get books
			BookSelector selector = new BookSelector(loader);
			float targetTime = selector.getTargetTimeFromUser();
			selector.selectBooks(targetTime);

			// Show results
			selector.displaySelectedBooks();

		} catch (BookLoadException e) {
			System.out.println("Error loading books: " + e.getMessage());
		}
	}
}
