package no;

import java.util.List;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 29/07/2021
 */
public class Gui {

    private Gui gui;

    private static List<String> boxes;

    public Gui getInstance() {
        if (gui == null) {
            gui = new Gui();
        }
        return gui;
    }

}

