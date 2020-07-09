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
        final ToDo newEntry = new ToDo("Katze füttern", "Katzenfutter kaufen", null, "ich", STATE_OPEN);
        final ToDo secondEntry = new ToDo("Aufräumen", "Zimmer aufräumen", null, "WG Kollege", STATE_OPEN);
        final ToDo thirdEntry = new ToDo("Einkaufen", "Ist genug Bier da?", null, "Trinkwart", STATE_OPEN);
        final Birthday aBirthday = new Birthday("Michael Stepping", "Er mag 'Mon Chéri'", null);

        // Add items to itemList
        itemList.add(newEntry);
        itemList.add(secondEntry);
        itemList.add(thirdEntry);
        itemList.add(aBirthday);
        // End demo


        /**
         * Infinite loop to keep asking for user input
         */
        while (true) {
            System.out.println("\nBitte geben Sie einen Buchstaben ein: ");

            String choice = "";
            Scanner usrInput = new Scanner(System.in);
            choice = usrInput.nextLine();

            System.out.println("User's choice: '" + choice + "'");

            // Eingaben auswerten
            switch (choice) {

                // help
                case "h":
                    printHelp();
                    break;

                // Create ToDo-Task
                case "t": {
                    createToDoItem(usrInput);
                    break;
                }

                // Create Birthday
                case "b": {
                    createBirthdayItem(usrInput);
                    break;
                }

                case "e":
                default:
                    // Benutzerende
                    // leave switch, not yet the while loop
                    break;
            }

            // print the complete list
            final String displayAllItems = printList(itemList);
            System.out.println(displayAllItems);

            // leave application
            if ("e".equals(choice))
                break;
        }

        // end of application
        saveItemsToDisk();
    }
    /* end of main */


    private static void createToDoItem(Scanner usrInput) {
        System.out.println("Bitte geben Sie folgende Daten ein:");

        ToDo todo = new ToDo();

        // name
        System.out.println("Name des toDo's");
        String name = usrInput.nextLine();
        todo.setName(name);

        // description
        System.out.println("Beschreibung");
        String description = usrInput.nextLine();
        todo.setDescription(description);

        // deadline: Date
        System.out.println("Zu erledigen bis:");
        String deadline = usrInput.nextLine();
        try {
            todo.setDeadline(deadline);
        } catch (final InvalidDateException e) {
            e.printStackTrace();
        }

        // owner
        System.out.println("Bearbeiter");
        String owner = usrInput.nextLine();
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

    private static void createBirthdayItem(Scanner usrInput) {
        System.out.println("Bitte geben Sie folgende Daten ein:");

        Birthday birthday = new Birthday();

        // name
        System.out.println("Geburtstagskind: ");
        String name = usrInput.nextLine();
        birthday.setName(name);

        // description
        System.out.println("Geburtstagswünsche: ");
        final String description = usrInput.nextLine();
        birthday.setDescription(description);

        // birthday: Date
        System.out.println("Geburtstag: ");
        final String sBirthday = usrInput.nextLine();
        try {
            birthday.setBirthday(sBirthday);
        } catch (final InvalidDateException e) {
            e.printStackTrace();
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
        PrintWriter outDatei = null;
        try {
            outDatei = new PrintWriter(new FileWriter(FILENAME_DATASTORAGE));

            String output = "";
            for (final Item item : itemList) {
                // objektorientierte Nutzung
                // sammele alle einzelnen Rückgaben in einem großen String
                // Ausgabe: "Küche putzen";"Putzmittel nicht vergessen";"05.01.2020 12:43CET"; \n
                // Jeder einzelne Datensatz wird mit einem Zeilenumbruch beendet
                output += item.save() + "\n";
            }
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

//                String l;
//                while ((l = inDatei.readLine()) != null) {
//                    (l);
//                }
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
        String output = "";

        // Gehe durch alle Objekte Item durch und sage den Objekten, sie sollen sich anzeigen.
        for (final Item item : itemList) {
            // objektorientierte Nutzung
            // sammele alle einzelnen Rückgaben in einem großen String
            output += item.display() + "\n";
        }
        return output;
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
        System.out.println("b: create a birthday");
        System.out.println("t: create a ToDo Item");
        System.out.println("e: exit");
    }

}
