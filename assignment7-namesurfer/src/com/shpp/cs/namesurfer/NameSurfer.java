package com.shpp.cs.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    JTextField nameField = new JTextField(20);
    JButton graphButton = new JButton("Graph");
    JButton clearButton = new JButton("Clear");

    private NameSurferGraph graph;

    NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(new JLabel("Name: "), NORTH);
        add(nameField, NORTH);
        nameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphButtonPressed();
            }
        });
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        addActionListeners();

        graph = new NameSurferGraph();
        add(graph);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == graphButton) {
            graphButtonPressed();
        }
        if (e.getSource() == clearButton) {
            clearButtonPressed();
        }
    }

    /**
     * Finding entry in database and drawing the graph.
     * If not exist print error message.
     */
    private void graphButtonPressed() {
        if (dataBase.findEntry(nameField.getText()) != null) {
            graph.addEntry(dataBase.findEntry(nameField.getText()));
            graph.update();
        } else {
            System.out.println("No such name in database: ");
        }
    }

    /**
     * Clear graphs by calling method from other class.
     */
    private void clearButtonPressed() {
        graph.clear();
    }
}
