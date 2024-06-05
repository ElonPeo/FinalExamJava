package application.BasicClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
	public class Library {
		private String filePath;
	
		public Library(String filePath) {
			this.filePath = filePath;
		}
	
		public void addBook(Book book) throws IOException {
			try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
				if (book instanceof EBook) {
					// Handle EBook
					EBook eBook = (EBook) book;
					writer.println(eBook.getTitle() + "," + eBook.getAuthor() + "," + eBook.getYearPublished() + "," +
									eBook.getFileFormat() + "," + eBook.getFileSize());
				} else {
					// Handle regular Book
					writer.println(book.getTitle() + "," + book.getAuthor() + "," + book.getYearPublished());
				}
			}
		}
	
		
		public void removeBook(String title) throws IOException {
			List<String> lines = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts.length >= 1 && parts[0].equals(title)) {
						// Skip this line (do not add to lines list)
					} else {
						lines.add(line);
					}
				}
			}
	
			// Rewrite the file with updated content (without the removed book)
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
				for (String line : lines) {
					writer.write(line);
					writer.newLine();
				}
			}
		}
	
		public List<Book> loadBooks() throws IOException {
			List<Book> books = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts.length >= 3) {
						String title = parts[0];
						String author = parts[1];
						int yearPublished = Integer.parseInt(parts[2]);
						
						if (parts.length == 3) {
							// Regular Book
							books.add(new Book(title, author, yearPublished));
						} else if (parts.length == 5) {
							// EBook
							String fileFormat = parts[3];
							double fileSize = Double.parseDouble(parts[4]);
							books.add(new EBook(title, author, yearPublished, fileFormat, fileSize));
						}
					}
				}
			}
			return books;
		} 
		
		public Optional<Book> searchBookByTitle(String title) throws IOException {
			List<Book> books = loadBooks(); 
			for (Book book : books) {
				if (book.getTitle().equalsIgnoreCase(title)) {
					return Optional.of(book); 
				}
			}
			return Optional.empty(); 
		}



		public void editBook(String title, Book updatedBook) throws IOException {
    List<String> lines = new ArrayList<>();
    boolean isUpdated = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 1 && parts[0].equals(title)) {
                // Replace with updated information
                if (updatedBook instanceof EBook) {
                    // Handle updated EBook
                    EBook eBook = (EBook) updatedBook;
                    line = eBook.getTitle() + "," + eBook.getAuthor() + "," + eBook.getYearPublished() + "," +
                           eBook.getFileFormat() + "," + eBook.getFileSize();
                } else {
                    // Handle updated regular Book
                    line = updatedBook.getTitle() + "," + updatedBook.getAuthor() + "," + updatedBook.getYearPublished();
                }
                isUpdated = true;
            }
            lines.add(line);
        }
    }


    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
    }

    
    if (!isUpdated) {
        throw new IOException("Book not found with title: " + title);
    }
}



}