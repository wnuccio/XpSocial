package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Social;
import java.io.IOException;
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
    
    String input(String row) {
        Command command = parser.parse(row);
        String output = command.execute(social);
        
        return output;
    }

    public void run() throws IOException {
        printWelcome();
        
        while(true) {
            printPrompt();
            String row = readInput();
            if("exit".equals(row)) break;
            String output = input(row);
            printOutput(output);
        }
    }

    private String readInput() {
        String row = scanner.nextLine();
        return row;
    }

    private void printOutput(String output) {
        if (! output.isEmpty()) {
            outputStream.println(output);
        }
    }

    private void printPrompt() {
        outputStream.print("> ");
        outputStream.flush();
    }

    private void printWelcome() {
        outputStream.println("---------------------------");
        outputStream.println("-- XpSocial 1.0 - 06/2020 -");
        outputStream.println("------  Walter Nuccio -----");
        outputStream.println("---('exit' to terminate)---");
        outputStream.println("---------------------------");
    }
}
