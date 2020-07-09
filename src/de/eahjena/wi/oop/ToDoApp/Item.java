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
        for(String token : tokens)
        {
            //Print all tokens
            System.out.println(token);
            
            // je nach ObjectType instanziieren;
            item = new Birthday();
            item = new ToDo();
        
        }
        

        
        
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
