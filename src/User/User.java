package User;

import java.time.LocalDate;
import java.time.Period;

import Book.Book;

public class User {
  private double penalty = 1000;

  private String fullName;
  private String email;

  private Book[] rentedBooks;
  private String[] history;

  public User(String fullName, String email) {
    this.fullName = fullName;
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public String[] getHistory() {
    return history;
  }

  @Override
  public String toString() {
    return email;
  }

  private void setHistory(Book book, String action, LocalDate bookRentedDate) {
    String record = "";

    switch (action.toLowerCase()) {
      case "rented":
        record = String.format("%s by %s | %s on %s till %s",
            book.getTitle(),
            book.getAuthor(),
            action,
            bookRentedDate,
            bookRentedDate.plusDays(7));
        break;
      case "returned":
        LocalDate expireDate = bookRentedDate.plusDays(7);
        if (LocalDate.now().isAfter(expireDate)) {
          Period period = Period.between(expireDate, LocalDate.now());
          int daysAfter = period.getDays();
          double penalty = daysAfter * this.penalty;
          record = String.format("%s by %s | %s on %s, %d days after, penalty: $%.2f",
              book.getTitle(),
              book.getAuthor(),
              action,
              LocalDate.now(),
              daysAfter,
              penalty);
        } else {
          record = String.format("%s by %s | %s on %s, rented date %s",
              book.getTitle(),
              book.getAuthor(),
              action,
              LocalDate.now(),
              bookRentedDate);
        }
        break;
      default:
        return;
    }

    if (history == null) {
      history = new String[1];
      history[0] = record;
    } else {
      String[] updatedHistory = new String[history.length + 1];
      for (int i = 0; i < history.length; i++) {
        updatedHistory[i] = history[i];
      }
      updatedHistory[history.length] = record;
      history = updatedHistory;
    }
  }

  public void addRentedBook(Book newBook) {
    if (this.rentedBooks == null) {
      this.rentedBooks = new Book[1];
      this.rentedBooks[0] = newBook;
    } else {
      Book[] combinedBooks = new Book[this.rentedBooks.length + 1];

      for (int i = 0; i < this.rentedBooks.length; i++) {
        combinedBooks[i] = this.rentedBooks[i];
      }
      combinedBooks[this.rentedBooks.length] = newBook;
      this.rentedBooks = combinedBooks;
    }
    setHistory(newBook, "rented", newBook.getRentedDate());
  }

  public Book[] getRentedBooks() {
    return rentedBooks;
  }

  public void removeRentedBook(Book bookToRemove, LocalDate bookRentedDate) {
    if (this.rentedBooks == null || this.rentedBooks.length == 0) {
      return;
    }

    int indexToRemove = -1;
    for (int i = 0; i < this.rentedBooks.length; i++) {
      if (this.rentedBooks[i].equals(bookToRemove)) {
        indexToRemove = i;
        break;
      }
    }

    if (indexToRemove == -1) {
      return;
    }

    Book[] updatedBooks = new Book[this.rentedBooks.length - 1];
    
    for (int i = 0; i < indexToRemove; i++) {
      updatedBooks[i] = this.rentedBooks[i];
    }
    for (int i = indexToRemove + 1; i < this.rentedBooks.length; i++) {
      updatedBooks[i - 1] = this.rentedBooks[i];
    }
    this.rentedBooks = updatedBooks;
    setHistory(bookToRemove, "returned", bookRentedDate);
  }

}
