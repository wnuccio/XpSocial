package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Command;
import com.walter.xpsocial.domain.Social;
import java.io.PrintStream;
import java.util.Scanner;

public class Console {

    private final Parser parser;
    private final Social social;
    
    private final PrintStream outputStream;
    private final Scanner scanner;

    public Console(Parser parser, Social social) {
        this.parser = parser;
        this.social = social;
        this.scanner = new Scanner(System.in);
        this.outputStream = System.out;
    }
    
    public boolean readAndProcessInput() {
        printPrompt();
        String row = readFromInputStream();
        if (isExit(row)) return true;
        
        String output = processInputRow(row);
        printOutput(output);
        return false;
    }

    private static boolean isExit(String row) {
        return "exit".equals(row);
    }

    String processInputRow(String row) {
        Command command = parser.parse(row);
        String output = command.execute(social);
        return output;
    }
        
    private String readFromInputStream() {
        String row = scanner.nextLine();
        return row;
    }

    private void printOutput(String output) {
        if (output.isEmpty()) return;
        
        outputStream.println(output);
    }

    private void printPrompt() {
        outputStream.print("> ");
        outputStream.flush();
    }

    public void printWelcome() {
        outputStream.println("---------------------------");
        outputStream.println("-- XpSocial 1.0 - 06/2020 -");
        outputStream.println("------  Walter Nuccio -----");
        outputStream.println("---('exit' to terminate)---");
        outputStream.println("---------------------------");
    }
}
