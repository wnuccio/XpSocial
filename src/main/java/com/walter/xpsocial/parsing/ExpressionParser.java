package com.walter.xpsocial.parsing;

import com.walter.xpsocial.console.Command;
import com.walter.xpsocial.console.Following;
import com.walter.xpsocial.console.Parser;
import com.walter.xpsocial.console.Posting;
import com.walter.xpsocial.console.Reading;
import com.walter.xpsocial.console.Wall;
import java.util.ArrayList;
import java.util.List;

public final class ExpressionParser implements Parser {

    private final static String username_ = "\\w+";
    private final static String space_ = "\\s+";
    private final static String post_ = "->";
    private final static String follows_ = "follows";
    private final static String wall_ = "wall";
    private final static String message_ = "(.*)[^\\s*]";

    private final List<CommandFactory> factories;

    public interface CommandFactory {
        Command createCommand(String row);
    }
    
    void register(CommandFactory factory) {
        this.factories.add(factory);
    }

    public CommandBuilder addCommmand() {
        return new CommandBuilder(this);
    }
    
    public ExpressionParser() {
        factories = new ArrayList<>();
        
        addCommmand()
                .fromExpression(username_)
                .buildCommand(Reading::new)
                .withTokens(0);
        addCommmand()
                .fromExpression(username_, post_, message_)
                .buildCommand(Posting::new)
                .withTokens(0, 2);
        addCommmand()
                .fromExpression(username_, follows_, username_)
                .buildCommand(Following::new)
                .withTokens(0, 2);
        addCommmand()
                .fromExpression(username_, wall_)
                .buildCommand(Wall::new)
                .withTokens(0);
    }

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
