package de.eahjena.wi.oop.ToDoApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Repräsentiert ein simples ToDo.
 * Dieses erweitert die Klasse Item.
 */
public class ToDo extends Item {
    /**
     * Konstanten in Java:              final static
     * In PHP, C, C++, JavaScript:      const
     * <p>
     * Die folgenden Konstanten repräsentieren den Zustand,
     * den ein Item haben kann. Folgendes Konstrukt wird
     * auch als Enum bezeichnet.
     */
    public final static int STATE_OPEN = 0;
    public final static int STATE_ASSIGNED = 1;
    public final static int STATE_DRINKMORE_BEER = 2;
    public final static int STATE_DONE = 9;
    final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    protected Date deadline;
    protected String owner;
    protected int state;

    // default constructor
    public ToDo() {
    }

    // Constructor
    public ToDo(final String name, final String description, Date deadline, String owner, int state) {
        super(description, name);

        // Properties von hier in ToDo
        this.deadline = deadline;
        this.owner = owner;
        this.state = state;
    }

    /**
     * Methods:
     * ==================================
     */


    public Date getDeadline() {
        return deadline;
    }


    // format: TT.MM.JJ(JJ)
    public void setDeadline(final String deadlineString) throws InvalidDateException {
        Date myDeadline = new Date();

        //convert String to Date
        try {
            myDeadline = dateFormat.parse(deadlineString);

        } catch (ParseException errorMessage) {
            // Todo: Catch false dates
            // Will print: 'Unparseable date: "32.13.2020"'
            System.err.println(errorMessage.getMessage());

            throw new InvalidDateException();
        }

        this.deadline = myDeadline;
    }

    public void setDeadline(final Date deadline) {
        this.deadline = deadline;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) throws InvalidStateException {
        // is the parameter a valid state?
        if (state == STATE_OPEN || state == STATE_ASSIGNED || state == STATE_DRINKMORE_BEER || state == STATE_DONE)
            this.state = state;
        else
            throw (new InvalidStateException(Integer.toString(state)));
    }

    @Override
    public String display() {
        return this.toString();
    }

    @Override
    public String toString() {
        // do not forget the output of the parent class
        final String superText = super.toString();
        final String text = superText + "ToDo{" +
                "deadline=" + deadline +
                ", owner='" + owner + '\'' +
                ", state=" + state +
                '}';
        return text;
    }
}
