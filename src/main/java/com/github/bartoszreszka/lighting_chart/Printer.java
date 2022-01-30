package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class Printer implements Printable {

    final Component component;

    public Printer(Component component) {
        this.component = component;
        try {
            printChart(this.component);
        } catch (PrinterException pe) {
            pe.printStackTrace();
        }
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Dimension dimension = component.getSize();
        double componentHeight = dimension.getHeight();
        double componentWidth = dimension.getWidth();

        double pHeight = pageFormat.getImageableHeight();
        double pWidth = pageFormat.getImageableWidth();

        double pXStart = pageFormat.getImageableX();
        double pYStart = pageFormat.getImageableY();

        double xRatio = pWidth / componentWidth;
        double yRatio = pHeight / componentHeight;

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        component.printAll(g2);

        return Printable.PAGE_EXISTS;
    }

    void printChart (Component component) throws PrinterException {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);
        Paper paper = new Paper();
//        paper.setSize(594, 846);
        paper.setSize(400, 600);
        preformat.setPaper(paper);
//        PageFormat postformat = pjob.pageDialog(preformat);
//        if (preformat != postformat) {
            pjob.setPrintable(this, preformat); // Change to postformat if pageDialog() is used.
            if (pjob.printDialog()) {
                try {
                    pjob.print();
                } catch (PrinterAbortException e) {
                } catch (PrinterException e) {
                    JOptionPane.showMessageDialog(
                            new JDialog(),
                            e.toString() + "\n" + e.getMessage(),
                            "Błąd drukowania.",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
//        }
    }
}
