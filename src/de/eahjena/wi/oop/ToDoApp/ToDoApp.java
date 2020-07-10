package de.eahjena.wi.oop.ToDoApp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static de.eahjena.wi.oop.ToDoApp.ToDo.STATE_OPEN;

public class ToDoApp {

    final static String TAG = "ToDoApp";

    /**
     * Holds items
     */
    public static ArrayList<Item> itemList = new ArrayList<>();

    /**
     * Filename for storing items
     */
    public static String FILENAME_DATASTORAGE = "TodoApp.csv";

    /**
     * Main method. App entry
     * ============================================
     */
    public static void main(String[] args) {
        printWelcome();
        printHelp();
        
        loadItemsFromDisk();
        
        /**
         * Begin demo
         * ============================================
         */
        /*
        final ToDo newEntry = new ToDo("Katze füttern", "Katzenfutter kaufen", null, "ich", STATE_OPEN);
        final ToDo secondEntry = new ToDo("Aufräumen", "Zimmer aufräumen", null, "WG Kollege", STATE_OPEN);
        final ToDo thirdEntry = new ToDo("Einkaufen", "Ist genug Bier da?", null, "Trinkwart", STATE_OPEN);
        final Birthday aBirthday = new Birthday("Michael Stepping", "Er mag 'Mon Chéri'", null);
        // Add items to itemList
        itemList.add(newEntry);
        itemList.add(secondEntry);
        itemList.add(thirdEntry);
        itemList.add(aBirthday);
        **/
        // End demo
    
        // we need only one instance
        final Scanner userInput = new Scanner(System.in);
    
    
        /**
         * Infinite loop to keep asking for user input
         */
        while (true) {
            System.out.println("\nBitte geben Sie einen Buchstaben ein: ");

            final String choice = userInput.nextLine();
            System.out.println("User's choice: '" + choice + "'");

            // Eingaben auswerten
            switch (choice) {
                // help
                case "h" -> printHelp();
                // Create ToDo-Task
                case "t" -> createToDoItem(userInput);
                // Create Birthday
                case "b" -> createBirthdayItem(userInput);
                // Delete Entry
                case "d" -> deleteEntry(userInput);
                // Auusgabe Liste
                case "l"-> {
                    final String entries = printList(itemList);
                    System.out.println(entries);
                }
                // case "e"
                default -> {
                    // Benutzerende
                    // Only exiting switch statement.
                    // Loop exit is checked later
                }
            }

            // leave application
            //trick: "e" is a string, which is present. (choice) could be null.
            if ("e".equals(choice))
                break;
        }

        // end of application
        saveItemsToDisk();
    }/* end of main */

    private static void deleteEntry(Scanner userInput) {
        System.out.println("Alle Einträge:");
        for (int i = 0; i < itemList.size(); i++) {
            Item currentItem = itemList.get(i);
            System.out.println(i + ") " + currentItem.getType() + " -> " + currentItem.getName());
        }
        System.out.println("Welcher Eintrag soll gelöscht werden?");
        int userSelection = userInput.nextInt();
        // Remove enter from keyboard buffer
        userInput.nextLine();

        try {
            itemList.remove(userSelection);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Die Eingabe war fehlerhaft. Bitte versuchen Sie es nochmal");
        }
    }

    private static void createToDoItem(Scanner userInput) {
        System.out.println("Bitte geben Sie folgende Daten ein:");

        ToDo todo = new ToDo();

        // name
        System.out.println("Name des toDo's");
        String name = userInput.nextLine();
        todo.setName(name);

        // description
        System.out.println("Beschreibung");
        String description = userInput.nextLine();
        todo.setDescription(description);

        // deadline: Date
        System.out.println("Zu erledigen bis:");
        String deadline = userInput.nextLine();
        try {
            todo.setDeadline(deadline);
        } catch (final InvalidDateException e) {
            e.printStackTrace();
        }

        // owner
        System.out.println("Bearbeiter");
        String owner = userInput.nextLine();
        todo.setOwner(owner);

        // state: enum
        try {
            todo.setState(STATE_OPEN);
        } catch (Exception e) {
        }

        // alternative: erst alle Eingaben validieren und dann das Objekt erzeugen
        // ToDo todo1 = new ToDo( description, name, null /*deadline*/, owner, STATE_OPEN);

        System.out.println(todo.toString());

        // Add item to List
        itemList.add(todo);
    }

    private static void createBirthdayItem(Scanner userInput) {
        System.out.println("Bitte geben Sie folgende Daten ein:");

        Birthday birthday = new Birthday();

        // name
        System.out.println("Geburtstagskind: ");
        String name = userInput.nextLine();
        birthday.setName(name);

        // description
        System.out.println("Geburtstagswünsche: ");
        final String description = userInput.nextLine();
        birthday.setDescription(description);

        // birthday: Date
        System.out.println("Geburtstag: ");
        final String sBirthday = userInput.nextLine();
        try {
            birthday.setBirthday(sBirthday);
        } catch (final InvalidDateException e) {
            //e.printStackTrace();
            System.out.println("Falsches Datum");
        }

        // alternative: erst alle Eingaben validieren und dann das Objekt erzeugen
        // Birthday todo1 = new Birthday( description, name, null /*birthday*/);

        System.out.println(birthday.toString());

        // add birthday to itemList
        itemList.add(birthday);
    }

    // save list
    private static void saveItemsToDisk() {
        /* ********************************** */
        // save list of items
        String output = "";
        for (final Item item : itemList) {
            // objektorientierte Nutzung
            // sammele alle einzelnen Rückgaben in einem großen String
            // Ausgabe: "Küche putzen";"Putzmittel nicht vergessen";"05.01.2020 12:43CET"; \n
            // Jeder einzelne Datensatz wird mit einem Zeilenumbruch beendet
            output += item.save() + "\n";
        }

        PrintWriter outDatei = null;
        try {
            outDatei = new PrintWriter(new FileWriter(FILENAME_DATASTORAGE));
            // now we iterated through all objects and collected the output
            // write all information finally to the file
            outDatei.println(output);
        } catch (final IOException exception) {
            System.out.println("Error in writing to file " + FILENAME_DATASTORAGE + ". Permisions?");

        } finally {
            if (outDatei != null) outDatei.close();
        }
        // end save list of items
        /* ********************************** */
    }

    // load list
    private static void loadItemsFromDisk() {
        /* ********************************** */
        //load list of Items from a CSV file

        BufferedReader inDatei = null;

        try {
            inDatei = new BufferedReader(new FileReader(FILENAME_DATASTORAGE));

            String sCSVLineForObject;
            // Zeilenweise die Eingabedatei einlesen
            while ((sCSVLineForObject = inDatei.readLine()) != null) {
                // Jede Zeile ist ein Objekt.
                
                // leere Zeilen überspringen
                if ( ! sCSVLineForObject.isBlank() ) {
                    // wir müssen hier das erste Element auslesen, damit wir wissen, welches Objekt wir instanziieren müssen.
                    // Eine kleine Factory.
                    Item item = Item.create(sCSVLineForObject);
    
                    itemList.add(item);
                }
            }
        } catch (IOException ioException) {
            System.out.println("Konnte Datei " + FILENAME_DATASTORAGE + " nicht öffnen. Keine Objekte geladen.");
        } finally {
            if (inDatei != null) try {
                inDatei.close();
            } catch (IOException e) {
            }
        }
    }
    // end load list of items
    /* ********************************** */

    // Print items in itemList:
    // prozeduraler Aufruf!
    public static String printList(final List<Item> itemList) {
        // String used to print all items in itemList
        StringBuilder output = new StringBuilder();

        // Gehe durch alle Objekte Item durch und sage den Objekten, sie sollen sich anzeigen.
        for (final Item item : itemList) {
            // objektorientierte Nutzung
            // sammele alle einzelnen Rückgaben in einem großen String
            output.append(item.display()).append("\n");
        }
        return output.toString();
    }

    /**
     * Prints welcome message on app start up
     */
    public static void printWelcome() {
        System.out.println("+---------- ToDoApp EAH ----------+");
        System.out.println("|   Manage things and birthdays   |");
        System.out.println("+---------------------------------+");
    }

    /**
     * Prints available commands
     */
    public static void printHelp() {
        // Ausgabe der Tastaturkommandos
        System.out.println("h: help");
        System.out.println("l: list all entries");
        System.out.println("b: create a birthday");
        System.out.println("t: create a ToDo Item");
        System.out.println("d: delete an entry");
        System.out.println("e: exit");
    }

}
