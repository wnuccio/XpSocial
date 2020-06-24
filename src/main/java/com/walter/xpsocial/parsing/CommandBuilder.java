package com.walter.xpsocial.parsing;

import com.walter.xpsocial.console.Command;
import com.walter.xpsocial.parsing.ExpressionParser.CommandFactory;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

class CommandBuilder {

    private final ExpressionParser parser;
    private List<String> regexpr;

    private Supplier<Command> commandConstructor0;
    private Function<String, Command> commandConstructor1;
    private BiFunction<String, String, Command> commandConstructor2;
    private int[] tokenIndexes;

    CommandBuilder(ExpressionParser parser) {
        this.parser = parser;
    }

    CommandBuilder fromRow(String... regexpr) {
        this.regexpr = Arrays.asList(regexpr);
        return this;
    }

    CommandBuilder is(Function<String, Command> commandConstructor) {
        this.commandConstructor1 = commandConstructor;
        return this;
    }

    CommandBuilder is(BiFunction<String, String, Command> commandConstructor) {
        this.commandConstructor2 = commandConstructor;
        return this;
    }

    CommandBuilder is(Supplier<Command> commandConstructor) {
        this.commandConstructor0 = commandConstructor;
        return this;
    }

    void withTokens(int... tokenIndexes) {
        this.tokenIndexes = tokenIndexes;

        CommandFactory factory = row -> {
            ExpressionExtractor extractor = new ExpressionExtractor();
            String[] tokens = extractor
                    .from(row)
                    .extract(regexpr.toArray(new String[regexpr.size()]));
            if (extractor.hasError()) {
                return Command.NULL_COMMAND;
            }
            
            int tokenNumber = tokenIndexes.length;
            if (tokenNumber == 0) {
                return commandConstructor0.get();
            } else if (tokenNumber == 1) {
                return commandConstructor1.apply(tokens[tokenIndexes[0]]);
            } else if (tokenNumber == 2) {
                return commandConstructor2.apply(
                        tokens[tokenIndexes[0]], 
                        tokens[tokenIndexes[1]]);
            } else {
                return Command.NULL_COMMAND;
            }
        };
        
        this.parser.register(factory);
    }

    static ExpressionParser.CommandFactory commandFactory(String[] regexpr, Function<String[], Command> commandConstructor) {
        ExpressionParser.CommandFactory factory = row -> {
            ExpressionExtractor extractor = new ExpressionExtractor();

            String[] tokens = extractor
                    .from(row)
                    .extract(regexpr);

            if (extractor.hasError()) {
                return Command.NULL_COMMAND;
            }

            Command result = commandConstructor.apply(tokens);
            return result;
        };
        return factory;
    }
}
