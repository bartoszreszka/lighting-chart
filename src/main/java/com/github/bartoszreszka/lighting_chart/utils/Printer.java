package com.github.bartoszreszka.lighting_chart.utils;

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
        component.print(g2);

        return Printable.PAGE_EXISTS;
    }

    void printChart(Component component) throws PrinterException {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);
        Paper paper = preformat.getPaper();
        paper.setSize(594, 846);
        paper.setImageableArea(25, 25, 544, 796);
        preformat.setPaper(paper);
        preformat = pjob.validatePage(preformat);
        pjob.setPrintable(this, preformat);
        if (pjob.printDialog()) {
            try {
                pjob.setJobName("Grafik oswietlenia");
                pjob.print();
            } catch (PrinterAbortException e) { // No action needed.
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(
                        new JDialog(),
                        e.getMessage(),
                        "Błąd drukowania.",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
