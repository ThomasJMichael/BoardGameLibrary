package main.java.view;


import main.java.controller.Controller;
import main.java.model.GameCategory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static main.java.model.GameCategory.*;

public class filterActionListener implements ActionListener {
    private GameCategory category;


    public filterActionListener(GameCategory category) {
        this.category = category;
    }

    public void actionPerformed(ActionEvent e) {
        AbstractButton abstractButton = (AbstractButton) e.getSource();
        if (abstractButton.getModel().isSelected()) {
            Controller.getInstance().addCategoryFilter(category.toString());
        }
        if (!abstractButton.getModel().isSelected()) {
            Controller.getInstance().removeCategoryFilter(category.toString());
        }
    }

}
// need to test
