import java.time.LocalDate;

import javax.swing.JOptionPane;

import Book.Book;
import User.User;

public class Main {

  public static void customDialog(String title, String msg, int type) {
    if (title == null) {
      title = "Lectores Felices";
    } else {
      title = "Lectores Felices - " + title;
    }

    JOptionPane.showMessageDialog(
        null,
        msg,
        title,
        type);
  }

  public static int customOptionsDialog(String[] options, String title, String msg) {
    if (title == null) {
      title = "Lectores Felices";
    }
    return JOptionPane.showOptionDialog(null, msg, "Lectores Felices - " + title, JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null, options, options[0]);
  }

  public static User userSelectDialog(User[] users, String title, String msg) {
    if (title == null) {
      title = "Lectores Felices";
    }
    return (User) JOptionPane.showInputDialog(null, msg, "Lectores Felices - " + title, JOptionPane.INFORMATION_MESSAGE,
        null, users, users[0]);
  }

  public static Book bookSelectDialog(Book[] books, String title, String msg) {
    if (title == null) {
      title = "Lectores Felices";
    }
    if (books == null || books.length <= 0) {
      customDialog("Error", "No hay Libros Disponibles", 0);
      return null;
    }
    return (Book) JOptionPane.showInputDialog(null, msg, "Lectores Felices - " + title, JOptionPane.INFORMATION_MESSAGE,
        null, books, books[0]);
  }

  public static String menuFormatMsg(User user, Book[] books) {
    StringBuilder msg = new StringBuilder();

    if (user != null) {
      String selectedUser = user.getFullName();

      msg.append("Bienvenido ").append(selectedUser).append("!\n\n");

      if (books.length > 0) {
        msg.append("Libros:\n");
        for (Book book : books) {
          msg.append("Precio: $").append(book.getPrice()).append(" | ");
          msg.append(book.getTitle()).append(" by ");
          msg.append(book.getAuthor()).append(" | ");
          if (book.isAvailable()) {
            msg.append("Disponible").append(" | \n");
          } else {
            msg.append("No Disponible").append(" | ");
            msg.append(book.getRentUser()).append(" | \n");
          }
        }
      } else {
        msg.append("Aún no hay Libros.\n");
      }
    }

    return msg.toString();
  }

  public static String userRentedBooksFormatMsg(User user) {
    Book[] books = user.getRentedBooks();
    StringBuilder msg = new StringBuilder();

    if (user != null) {
      String selectedUser = user.getFullName();

      msg.append("Libros Alquilados: ").append(selectedUser).append("\n\n");

      if (books.length > 0) {
        for (Book book : books) {
          msg.append(book.getTitle()).append(" by ");
          msg.append(book.getAuthor()).append("\n");
        }
      } else {
        msg.append("Aún no hay Libros.\n");
      }
    }

    return msg.toString();
  }

  public static String userRentedBooksHistoryFormatMsg(User user) {
    String[] history = user.getHistory();
    StringBuilder msg = new StringBuilder();

    if (user != null) {
        String selectedUser = user.getFullName();
        msg.append("Historial de Libros Alquilados: ").append(selectedUser).append("\n\n");

        if (history != null && history.length > 0) {
            for (String record : history) {
                msg.append(record).append("\n");
            }
        } else {
            msg.append("Aún no hay historial de libros.\n");
        }
    }

    return msg.toString();
}

  public static Book[] getAvailableBooks(Book[] books) {
    int availableCount = 0;
    for (Book book : books) {
      if (book.isAvailable()) {
        availableCount++;
      }
    }

    Book[] availableBooks = new Book[availableCount];
    int index = 0;

    for (Book book : books) {
      if (book.isAvailable()) {
        availableBooks[index++] = book;
      }
    }

    return availableBooks;
  }

  public static void main(String[] args) {
    User user_1 = new User("Tony Montana", "tony@montana.com");
    User user_2 = new User("John Locke", "john@locke.com");
    User[] users = { user_1, user_2 };

    Book book_1 = new Book("De Cero A Uno", "Peter Thiel", 11000);
    Book book_2 = new Book("1984", "George Orwell", 13000);

    Book book_test = new Book("Test", "Steven H",1234, false, LocalDate.now().minusDays(11), user_1);
    user_1.addRentedBook(book_test);
    
    Book[] books = { book_1, book_2, book_test };

    String[] menuOptions = { "Alquilar", "Devolver", "Mis Libros", "Historial", "Cambiar de Cuenta" };
    int option;
    Book selectedBook = null;

    boolean exitSignIn = false;
    while (!exitSignIn) {
      User loggedUser = userSelectDialog(users, "Iniciar Sesion", "Seleccionar Cuenta");
      if (loggedUser == null) {
        exitSignIn = true;
      } else {
        boolean logOut = false;
        while (!logOut) {
          option = customOptionsDialog(menuOptions, "Tu Cuenta", menuFormatMsg(loggedUser, books));
          Book[] tempBooks;

          switch (menuOptions[option]) {
            case "Alquilar":
              tempBooks = getAvailableBooks(books);
              selectedBook = bookSelectDialog(tempBooks, "Alquilar", "Libros Disponibles");
              if (selectedBook != null) {
                selectedBook.setRentUser(loggedUser);
                loggedUser.addRentedBook(selectedBook);
                customDialog(null, "Libro: '" + selectedBook.toString() + "'\nAlquilado con exito!", 1);
              }
              break;
            case "Devolver":
              tempBooks = loggedUser.getRentedBooks();
              selectedBook = bookSelectDialog(tempBooks, "Devolver", "Mis Libros Alquilados");
              if (selectedBook != null) {
                LocalDate tempRentedDate = selectedBook.getRentedDate();
                selectedBook.setRentUser(null);
                loggedUser.removeRentedBook(selectedBook, tempRentedDate);
                customDialog(null, "Libro: '" + selectedBook.toString() + "'\nDevuelto con exito!", 1);
              }
              break;
            case "Mis Libros":
              tempBooks = loggedUser.getRentedBooks();
              if (tempBooks != null) {
                JOptionPane.showMessageDialog(null, userRentedBooksFormatMsg(loggedUser),
                    "Lectores Felices - Mis Libros", 1);
              } else {
                customDialog("Error", "No hay Libros Disponibles", 0);
              }
              break;
            case "Historial":
              String[] history = loggedUser.getHistory();
              if (history != null) {
                JOptionPane.showMessageDialog(null, userRentedBooksHistoryFormatMsg(loggedUser),
                    "Lectores Felices - Mis Libros", 1);
              } else {
                customDialog("Error", "Aún no hay historial de libros", 0);
              }
              break;
            case "Cambiar de Cuenta":
              logOut = true;
          }
        }
      }
    }
  }
}