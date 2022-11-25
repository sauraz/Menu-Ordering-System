package org.menu;

public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor();
        try {
            processor.processInput();
        } catch (Exception e) {
            System.out.println("Unable to process: " + e.getMessage());
        }
    }
}