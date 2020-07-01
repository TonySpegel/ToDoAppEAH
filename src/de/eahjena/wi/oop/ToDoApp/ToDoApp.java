package de.eahjena.wi.oop.ToDoApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static de.eahjena.wi.oop.ToDoApp.ToDo.STATE_OPEN;

public class ToDoApp {

    final static String TAG = "ToDoApp";

    // + itemList: List
    public static List<Item> itemList = new ArrayList<Item>(); //LinkedList

    // main programm, beginning of life
    public static void main(String[] args) {

        //load list of Items


        /* ------------------- DEMO ----------------- */
        final ToDo newEntry = new ToDo("Katze füttern", "Katzenfutter kaufen", null, "ich", STATE_OPEN);
        final ToDo secondEntry = new ToDo("Aufräumen",  "Zimmer aufräumen", null, "WG Kollege", STATE_OPEN);
        final ToDo thirdEntry = new ToDo("Einkaufen",  "Ist genug Bier da?", null, "Trinkwart", STATE_OPEN);
        final Birthday aBirthday = new Birthday( "Michael Stepping", "Er mag 'Mon Chéri'", null );

        newEntry.name Katze füttern
        newEntry.deadline null

        // Add items to itemList
        itemList.add(newEntry);
        itemList.add(secondEntry);
        itemList.add(thirdEntry);
        itemList.add(aBirthday);
        /* ------------------- DEMO ----------------- */



        // ask user for input
        while (true) {

            // Benutzereingaben abfragen
            //String choice = "";
            String choice = new String("");

            System.out.println("\nBitte geben Sie einen Buchstaben ein: 'h' für Hilfe.");
            //Scanner
            // a) neuere Methode
            Scanner usrInput = new Scanner(System.in);
            choice = usrInput.nextLine();
            //int squareLength = usr_input.nextInt();
            // b) alte Methode
            /*
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String eingabe = br.readLine();
            */

            System.out.println("User's choice: '" + choice + "'");

            // Eingaben auswerten
            switch (choice) {

                // help
                case "h":
                    printHelp();
                    break;

                //Create ToDo-Task
                case "t": {
                    createToDoItem(usrInput);
                    break;
                }

                //Create ToDo-Task
                case "b": {
                    createBirthdayItem(usrInput);
                    break;
                }

                case "e":
                case "":
                default: // ????
                    // Benutzerende
                    break;


            }

            // print the complete list
            final String displayAllItems = printList( itemList );
            System.out.println( displayAllItems );


        }
    }

    private static void createToDoItem(Scanner usrInput) {
        System.out.println("Bitte geben Sie folgende Daten ein:");

        ToDo todo = new ToDo();

        // name
        System.out.println("Name des toDo's");
        String name = usrInput.nextLine();      //TODO nur ein Buchstabe????
        todo.setName(name);

        // description
        System.out.println("Beschreibung");
        String description = usrInput.nextLine();      //TODO nur ein Buchstabe????
        todo.setDescription(description);

        // deadline: Date
        System.out.println("Zu erledigen bis:");
        String deadline = usrInput.nextLine();      //TODO nur ein Buchstabe????
        try {
            todo.setDeadline(deadline);
        } catch (final InvalidDateException e) {
            e.printStackTrace();
        }

        // owner
        System.out.println("Bearbeiter");
        String owner = usrInput.nextLine();      //TODO nur ein Buchstabe????
        todo.setOwner(owner);

        // state: enum
        try {
            todo.setState(STATE_OPEN);
        } catch (Exception e) {
            ;
        }

        // alternative: erst alle Eingaben validieren und dann das Objekt erzeugen
        // ToDo todo1 = new ToDo( description, name, null /*deadline*/, owner, STATE_OPEN);

        System.out.println( todo.toString() );

        //add Item to List
        itemList.add( todo );
    }

    private static void createBirthdayItem(Scanner usrInput) {
        System.out.println("Bitte geben Sie folgende Daten ein:");

        Birthday birthday = new Birthday();

        // name
        System.out.println("Geburtstagskind: ");
        String name = usrInput.nextLine();      //TODO nur ein Buchstabe????
        birthday.setName(name);

        // description
        System.out.println("Geburtstagswünsche: ");
        final String description = usrInput.nextLine();      //TODO nur ein Buchstabe????
        birthday.setDescription(description);

        // birthday: Date
        System.out.println("Geburtstag: ");
        final String sBirthday = usrInput.nextLine();      //TODO nur ein Buchstabe????
        try {
            birthday.setBirthday( sBirthday );
        } catch (final InvalidDateException e) {
            e.printStackTrace();
        }

        // alternative: erst alle Eingaben validieren und dann das Objekt erzeugen
        // Birthday todo1 = new Birthday( description, name, null /*birthday*/);

        System.out.println( birthday.toString() );

        //add Item to List
        itemList.add( birthday );



    }

    /*
    + main()
    + saveList()
    + loadList()
    + printHelp()
     */


    //save list
    public void saveList(){

    }

    // load list
    public void loadList() {

    }

    public static void printHelp() {
        // Ausgabe der Tastaturkommandos
        System.out.println("h: help");
        System.out.println("b: create a birthday");
        System.out.println("t: create a ToDo Item");
        System.out.println("e: exit");

        // ...

    }

    // Print items in itemList:
    // prozeduraler Aufruf!
    public static String printList(final List<Item> itemList ) {

        String output = "";

        // Gehe durch alle Objekte Item durch und sage den Objekten, sie sollen sich anzeigen.
        for (final Item item : itemList) {
            // objektorientierte Nutzung
            // sammele alle einzelnen Rückgaben in einem großen String
            output += item.display() + "\n";
        }
        return output;
    }

}
