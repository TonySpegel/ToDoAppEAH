package de.eahjena.wi.oop.ToDoApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Birthday extends Item {
    final static String TAG = "Birthday";
    final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    // from superclass
    //name
    //description
    protected Date birthday;
    protected int age;

    //default constructor
    public Birthday() {
    }

    //constructor
    public Birthday(final String name, final String description, final Date birthday) {

        super(name, description);
        this.birthday = birthday;
        this.age = calculateAge();
    }

    // CSV Comma separated value
    @Override
    public String save() {
        // which type we are
        //0
        String output = TAG+";";

        // 1, 2
        // parent data, here Item
        output += super.save();

        //3
        // Ausgabe: "Küche putzen";"Putzmittel nicht vergessen";"05.01.2020 12:43CET";
        if (birthday != null) {
            // output += birthday.toString(); liefert "Wed Sep 23 00:00:00 CEST 2020"
            // liefert
            output += simpleDateFormat.format(birthday);
        } else {
            output += "\"\"";
        }
        // Oneliner alternative: output += birthday != null ? birthday.toString() : "\"\"";
        output += ";"; // The closing ";" for the birthday field
        return output;
    }
    
    final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Override
    void load( String[] tokens ){
        super.load( tokens );
        
        String sBirthday = tokens[3];
        try {
            this.birthday = simpleDateFormat.parse( sBirthday );
            this.age = calculateAge();
        } catch (ParseException e) {
            //fehlerhaftes Datum.
        }
    }
    
    
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.age = calculateAge();
    }

    // format: TT.MM.JJ(JJ)
    public void setBirthday(final String sBirthday) throws InvalidDateException {
        Date myBirthday = new Date();

        try {
            myBirthday = dateFormat.parse(sBirthday);

        } catch (ParseException errorMessage) {
            // Todo: Catch false dates
            // Will print: 'Unparseable date: "32.13.2020"'
            System.err.println(errorMessage.getMessage());
            throw new InvalidDateException();
        }

        this.birthday = myBirthday;
        this.age = calculateAge();
    }


    public int getAge() {
        return this.age;
    }

    private int calculateAge() {
        // If no birthday is set, we obviously can't calculate an age
        if (birthday == null) {
            return 0;
        }

        Instant birthdayInstant = this.birthday.toInstant();
        Instant nowInstant = Instant.now();

        Duration timeDifference = Duration.between(birthdayInstant, nowInstant);

        // Neglecting leap years since no one lives long enough to make a difference here
        return (int) timeDifference.toDays() / 365;
    }


    @Override
    public String display() {

        //TODO Ausgabe
        return this.toString();
    }

}