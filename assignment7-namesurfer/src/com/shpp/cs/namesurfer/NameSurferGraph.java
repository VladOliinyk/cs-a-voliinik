package com.shpp.cs.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private ArrayList<NameSurferEntry> entryArrayList = new ArrayList<NameSurferEntry>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entryArrayList = new ArrayList<NameSurferEntry>();
        update();
        System.out.println("clear()");
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entryArrayList.add(entry);
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        addGrid();
        addGraphs();
    }

    /**
     * Adding grid to the screen.
     */
    private void addGrid() {
        addColumns();
        addHorizontalLines();
    }

    /**
     * Adding vertical lines to the screen.
     * Adding years to the bottom of the screen.
     */
    private void addColumns() {
        for (int i = 0; i < NDECADES; i++) {
            int startX = i * (getWidth() / NDECADES);
            int startY = 0;
            int enxX = i * (getWidth() / NDECADES);
            int endY = getHeight();

            /* vertical lines */
            GLine line = new GLine(startX, startY, enxX, endY);
            this.add(line);

            /* years */
            GLabel year = new GLabel(String.valueOf(START_DECADE + (10 * i)));
            year.setFont(new Font("Default", 0, GRAPH_MARGIN_SIZE - 1));
            this.add(year, startX, endY);
        }
    }

    /**
     * Adding horizontal lines to the screen.
     */
    private void addHorizontalLines() {
        /* top line */
        GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        this.add(topLine);

        /* bottom line */
        GLine botLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        this.add(botLine);
    }

    /**
     * Adding all graphs from stack (from entryArrayList) to the screen by calling addCurrentGraph(..) method.
     * In cycle changing colors of graphs.
     */
    private void addGraphs() {
        Color color[] = {Color.BLUE, Color.RED, Color.MAGENTA, Color.ORANGE};
        int colorNumber;
        for (int i = 0; i < entryArrayList.size(); i++) {
            NameSurferEntry entry = entryArrayList.get(i);
            colorNumber = i;
            while (colorNumber >= color.length) {
                colorNumber -= color.length;
            }
            addCurrentGraph(entry, color[colorNumber]);
        }
    }

    /**
     * Finding the real Y coordinate of rank.
     *
     * @param entry The line of data of entry.
     * @param color The color which need to draw a graph.
     */
    private void addCurrentGraph(NameSurferEntry entry, Color color) {
        int lineStartX;
        int lineStartY;
        int lineEndX;
        int lineEndY;

        lineStartX = 0;
        lineStartY = findYCoordFromRank(entry.getRank(0));

        String rankString = "*";
        if (entry.getRank(0) != 0) {
            rankString = String.valueOf(entry.getRank(0));
        }
        GLabel name = new GLabel(entry.getName() + " " + rankString);
        name.setColor(color);
        add(name, lineStartX, lineStartY);

        for (int i = 1; i < NDECADES; i++) {
            //* calculating current position of point *//*
            lineEndX = i * (getWidth() / NDECADES);
            lineEndY = findYCoordFromRank(entry.getRank(i));

            //* finding, creating and adding the NAME *//*
            rankString = "*";
            if (entry.getRank(i) != 0) {
                rankString = String.valueOf(entry.getRank(i));
            }
            name = new GLabel(entry.getName() + " " + rankString);
            name.setColor(color);
            add(name, lineEndX, lineEndY);

            //* creating and adding the line from 'start' pos to 'end' (current) position *//*
            GLine line = new GLine(lineStartX, lineStartY, lineEndX, lineEndY);
            line.setColor(color);
            add(line);

            //* changing 'start' position to current and completing current iteration *//*
            lineStartX = lineEndX;
            lineStartY = lineEndY;
        }
    }

    /**
     * Finding the real Y coordinate of rank.
     *
     * @param rank The rank.
     * @return The real Y coordinate.
     */
    private int findYCoordFromRank(int rank) {
        if (rank == 0) {
            return getHeight() - GRAPH_MARGIN_SIZE;
        } else {
            return GRAPH_MARGIN_SIZE + (rank * (getHeight() - GRAPH_MARGIN_SIZE * 2)) / MAX_RANK;
        }
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
