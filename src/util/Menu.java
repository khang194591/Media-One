package util;

import io.IOFile;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static final Scanner scanner = new Scanner(System.in);
    static final Warehouse warehouse = new Warehouse();

    static public void showProductMenu(Class tClass) {
        int option = 0;
        Store store = tClass.equals(Book.class) ? warehouse.getBookStore() : tClass.equals(MusicDisc.class) ? warehouse.getMusicDiscStore() : warehouse.getMovieDiscStore();
        List<Product> list = store.getList();
        while (option != 7) {
            System.out.println("\n\n----------\n\n");
            System.out.println("1. Show All");
            System.out.println("2. Add");
            System.out.println("3. Find by name");
            System.out.println("4. Find by index");
            System.out.println("5. Find and delete by name");
            System.out.println("6. Find and delete by index");
            System.out.println("7. Return");
            System.out.print("Select: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1 -> {
                    if (list == null || list.size() == 0) {
                        System.out.println("Empty, please add more!!!");
                    } else {
                        printTable(tClass, list);
                    }
                    System.out.print("Please enter something to continue: ");
                    scanner.nextLine();
                }
                case 2 ->
                        list.add(tClass.equals(Book.class) ? new Book(scanner) : tClass.equals(MusicDisc.class) ? new MusicDisc(scanner) : new MovieDisc(scanner));
                case 3 -> {
                    System.out.print("Name: ");
                    List<Product> result = store.find(scanner.nextLine().trim());
                    printTable(tClass, result);
                    System.out.print("Please enter something to continue: ");
                    scanner.nextLine();
                }
                case 4 -> {
                    System.out.println("Index: ");
                    List<Product> result = new ArrayList<>();
                    Product temp = store.find(Integer.parseInt(scanner.nextLine().trim()));
                    if (temp != null) {
                        result.add(temp);
                    }
                    printTable(tClass, result);
                    System.out.print("Please enter something to continue: ");
                    scanner.nextLine();
                }
                case 5 -> {
                    System.out.print("Name: ");
                    List<Product> result = store.find(scanner.nextLine().trim());
                    printTable(tClass, result);
                    System.out.print("Confirm delete(Y/n): ");
                    if (!scanner.nextLine().toLowerCase().trim().equals("n")) {
                        result.forEach(store::delete);
                    }
                }
                case 6 -> {
                    System.out.println("Index: ");
                    List<Product> result = new ArrayList<>();
                    Product temp = store.find(Integer.parseInt(scanner.nextLine().trim()));
                    if (temp != null) {
                        result.add(temp);
                    }
                    printTable(tClass, result);
                    System.out.print("Confirm delete(Y/n): ");
                    if (!scanner.nextLine().toLowerCase().trim().equals("n")) {
                        result.forEach(store::delete);
                    }
                }
                default -> System.out.println("Invalid option!!!");
            }
        }
    }

    static public void showMainMenu() {
        int option = 0;
        while (option != 4) {
            System.out.println("\n\n----------\n\n");
            System.out.println("1. Warehouse");
            System.out.println("4. Exit");
            System.out.print("Select: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                option = 0;
            }
            switch (option) {
                case 1 -> {
                    while (option != 4) {
                        System.out.println("\n\n----------\n\n");
                        System.out.println("1. Book");
                        System.out.println("2. Movie Disc");
                        System.out.println("3. Music Disc");
                        System.out.println("4. Return");
                        System.out.print("Select: ");
                        try {
                            option = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                            option = 0;
                        }
                        switch (option) {
                            case 1 -> Menu.showProductMenu(Book.class);
                            case 2 -> Menu.showProductMenu(MovieDisc.class);
                            case 3 -> Menu.showProductMenu(MusicDisc.class);
                            case 4 -> {
                            }
                            default -> System.out.println("Invalid option!!!");
                        }
                    }
                    option = 0;
                }
                case 2 -> System.out.println("2 In development");
                case 3 -> System.out.println("3 In development");
                case 4 -> {
                    try {
                        IOFile.writeToFile("data/book.txt", warehouse.getBookStore().getList());
                        IOFile.writeToFile("data/musicDisc.txt", warehouse.getMusicDiscStore().getList());
                        IOFile.writeToFile("data/movieDisc.txt", warehouse.getMovieDiscStore().getList());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Fail to saved data");
                    }
                }
                default -> System.out.println("Invalid option!!!");
            }
        }
    }

    private static void printTable(Class tClass, List<Product> result) {
        if (result == null || result.size() == 0) {
            System.out.println("Empty, please add more!!!");
            return;
        }
        if (tClass.equals(Book.class)) {
            System.out.printf("| %5s | %40s | %40s | %10s | %10s | %10s | %20s | %20s |\n", "index", "code", "name", "quantity", "import", "sell", "publisher", "author");
        } else if (tClass.equals(MusicDisc.class)) {
            System.out.printf("| %5s | %40s | %40s | %10s | %10s | %10s | %20s | %20s |\n", "index", "code", "name", "quantity", "import", "sell", "producer", "singer");
        } else {
            System.out.printf("| %5s | %40s | %40s | %10s | %10s | %10s | %20s | %20s |\n", "index", "code", "name", "quantity", "import", "sell", "producer", "director");
        }
        int index = 0;
        for (Object item : result) {
            System.out.printf("| %5d |%s\n", index++, item);
        }
    }
}
