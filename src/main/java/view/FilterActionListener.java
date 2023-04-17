package main.java.view;


import main.java.controller.Controller;
import main.java.model.GameCategory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterActionListener implements ActionListener {
    private GameCategory category;

    private JCheckBox filterCheckbox;


    public FilterActionListener(GameCategory category, JCheckBox checkbox) {
        this.category = category;
        filterCheckbox = checkbox;
    }

    public void actionPerformed(ActionEvent e) {
        if (filterCheckbox.isSelected()) {
            Controller.getInstance().addCategoryFilter(category.name());
            System.out.println(category.name() + " selected.");
        }
        else {
            Controller.getInstance().removeCategoryFilter(category.name());
            System.out.println(category.name() + " unselected.");
        }
    }

}
// need to test
