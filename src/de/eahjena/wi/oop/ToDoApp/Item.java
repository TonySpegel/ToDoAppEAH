package de.eahjena.wi.oop.ToDoApp;


// superclass for all Items like Birthday or ToDo
public class Item {

    final static String TAG = "Item";
    //Delimiter used in CSV file
    final static String DELIMITER = ";";
    
    /* Attributes */
    protected String name;
    protected String description;

    // default constructor
    public Item() {
        this.description = "description";
        this.name = "name";
    }

    public Item(final String description, final String name) {
        this.description = description;
        this.name = name;
    }

    
    
    
    // Factory pattern
    static Item create( String CSVLineOfObject ) {
        
        // parse the line into parameters
        // first columns is the name of the object
    
    
        Item item = null;
        
        //Get all tokens available in line
        String[] tokens = CSVLineOfObject.split(DELIMITER);
        // first columns is the name of the object
        //später for(String token : tokens)
        switch (tokens[0])
        {
            // je nach ObjectType instanziieren;
            // bessser TAG als "Birthday"
            case Birthday.TAG->{
                item = new Birthday( );
            }
            // bessser TAG als "To_Do"
            case ToDo.TAG->{
                item = new ToDo( );
            }
        }
        item.load( tokens );
        
        return item;
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

    // Methods to generate a CSV string
    // Eine virtuelle Methode! sollte abgeleitet werden.
    public String save() {
        String output = "";

        // CSV Comma separated value
        // Ausgabe: "Küche putzen";"Putzmittel nicht vergessen";
        output += this.name + ";";
        output += this.description + ";";

        return output;
    }

    // override in subclasses
    void load( String[] tokens ){
        //1
        this.name = tokens[1];
        this.description = tokens[2];
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
