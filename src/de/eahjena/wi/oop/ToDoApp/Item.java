package de.eahjena.wi.oop.ToDoApp;


// superclass for all Items like Birthday or ToDo
public class Item {

    final static String TAG = "Item";

    /* Attributes */
    protected String description;
    protected String name;

    // default constructor
    public Item() {
        this.description = "description";
        this.name = "name";
    }

    public Item(final String description, final String name) {
        this.description = description;
        this.name = name;
    }

    /* Methods */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    // Methods to display the content in the proper way for the terminal
    // Eine virtuelle Methode! sollte abgeleitet werden.
    /* (C++, PHP) virtual */
    public String display() {

        // Diese Vaterklasse soll nie eine Ausgabe machen.
        new AssertionError( false );
        return null;
    }


    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
