package main.java.view;


import main.java.controller.Controller;
import main.java.model.GameCategory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FilterActionListener implements ActionListener to create a custom function for actionPerformed().
 * If a filter is selected, the given category is added to the filter search list and when the
 * filter is unselected, the given category is removed from the filter search list
 */

public class FilterActionListener implements ActionListener {
    private final GameCategory category;

    private final JCheckBox filterCheckbox;


    public FilterActionListener(GameCategory category, JCheckBox checkbox) {
        this.category = category;
        filterCheckbox = checkbox;
    }

    public void actionPerformed(ActionEvent e) {
        if (filterCheckbox.isSelected()) {
            Controller.getInstance().addCategoryFilter(category.toString());
            System.out.println(category + " selected.");
        }
        else {
            Controller.getInstance().removeCategoryFilter(category.toString());
            System.out.println(category + " unselected.");
        }
    }

}
// need to test
