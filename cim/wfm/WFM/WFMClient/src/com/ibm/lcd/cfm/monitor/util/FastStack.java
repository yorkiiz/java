
package com.ibm.lcd.cfm.monitor.util;

import java.util.ArrayList;
import java.util.EmptyStackException;


public class FastStack extends ArrayList {

	private static final long serialVersionUID = 5458999253999811055L;

	public FastStack() {
        super();
    }

    public Object push(Object item) {
        add(item);
        return item;
    }

    public Object pop() throws EmptyStackException {
        if (size() == 0)
            throw new EmptyStackException();
        return (remove(size() - 1));
    }

    public Object peek() throws EmptyStackException {
        if (size() == 0)
            throw new EmptyStackException();
        return (get(size() - 1));
    }

    public boolean empty() {
        return isEmpty();
    }

    public int search(Object o) {
        int i = lastIndexOf(o);
        return ((i >= 0) ? (size() - i + 1) : -1);
    }

}
