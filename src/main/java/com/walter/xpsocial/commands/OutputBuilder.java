package com.walter.xpsocial.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OutputBuilder<T> {

    private final List<T> items;
    private final List<Function<T, String>> appenders;
    private boolean endLine;

    public OutputBuilder() {
        items = new ArrayList<>();
        appenders = new ArrayList<>();
        this.endLine = false;
    }

    public static <T> OutputBuilder<T> forEach(List<T> items) {
        OutputBuilder<T> builder = new OutputBuilder<>();
        builder.items.addAll(items);
        return builder;
    }

    OutputBuilder<T> append(Function<T, String> appender) {
        this.appenders.add(appender);
        return this;
    }
    
    OutputBuilder<T> append(String s) {
        append(item -> s);
        return this;
    }

    OutputBuilder<T> endLine() {
        this.endLine = true;
        return this;
    }

    String doBuild() {
        StringBuilder b = new StringBuilder();
        items.forEach(item -> {
            applyAppenders(item, b);
            if (endLine) b.append("\n");
        });

        String result = b.toString();
        if (endLine) {
            result = removeLastEndline(result);
        }
        return result;
    }

    private String removeLastEndline(String result) {
        if (result.length() >= 1) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    private void applyAppenders(T item, StringBuilder b) {
        this.appenders.forEach(appender -> {
            String string = appender.apply(item);
            b.append(string);
        });
    }

}
