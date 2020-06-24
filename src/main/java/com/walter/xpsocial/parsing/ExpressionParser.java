package com.walter.xpsocial.parsing;

import com.walter.xpsocial.console.Command;
import com.walter.xpsocial.console.Following;
import com.walter.xpsocial.console.Parser;
import com.walter.xpsocial.console.Posting;
import com.walter.xpsocial.console.Reading;
import com.walter.xpsocial.console.Wall;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class ExpressionParser implements Parser {

    private final static String username_ = "\\w+";
    private final static String space_ = "\\s+";
    private final static String post_ = "->";
    private final static String follows_ = "follows";
    private final static String wall_ = "wall";
    private final static String message_ = ".*";

    private final List<CommandFactory> factories;

    public interface CommandFactory {
        Command createCommand(String row);
    }
    
    public void register(CommandFactory factory) {
        this.factories.add(factory);
    }

    public void registerCommand(
            Function<String[], Command> commandConstructor,
            String... regexpr) {
        
        CommandFactory factory = CommandBuilder.commandFactory(regexpr, commandConstructor);
                
        register(factory);
    }

    private CommandBuilder commmand() {
        return new CommandBuilder(this);
    }
    
    public ExpressionParser() {
        factories = new ArrayList<>();
        
        commmand()
                .fromRow(username_)
                .is(Reading::new)
                .withTokens(0);
        commmand()
                .fromRow(username_, post_, message_)
                .is(Posting::new)
                .withTokens(0, 2);
        commmand()
                .fromRow(username_, follows_, message_)
                .is(Following::new)
                .withTokens(0, 2);
        commmand()
                .fromRow(username_, wall_)
                .is(Wall::new)
                .withTokens(0);
    }

//    public ExpressionParser() {
//        factories = new ArrayList<>();
//        
//        registerCommand(
//                tokens -> new Reading(tokens[0]),
//                username_
//        );
//        registerCommand(
//                tokens -> new Posting(tokens[0], tokens[2]),
//                username_, post_, message_
//        );
//        registerCommand(
//                tokens -> new Following(tokens[0], tokens[2]),
//                username_, follows_, username_
//        );
//        registerCommand(
//                tokens -> new Wall(tokens[0]),
//                username_, wall_
//        );
//    }
//
    @Override
    public Command parse(String inputRow) {
        String row = inputRow != null ? inputRow : "";

        for (CommandFactory factory : factories) {
            Command command = factory.createCommand(row);
            if (command != Command.NULL_COMMAND) 
                return command;
        }
        
        return Command.NULL_COMMAND;
    }
}
