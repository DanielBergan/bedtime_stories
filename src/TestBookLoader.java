public class TestBookLoader {
	public static void main(String[] args) {
		BookLoader loader = new BookLoader();

		try {
			loader.loadBooks("books.csv"); // Replace with your actual CSV filename

			System.out.println("Successfully loaded books:");
			for (Book book : loader.getBooks()) {
				System.out.println("Title: " + book.getTitle() +
						", Length: " + book.getLength() + " minutes");
			}

		} catch (BookLoadException e) {
			System.err.println("Failed to load books: " + e.getMessage());
		}
	}
}
