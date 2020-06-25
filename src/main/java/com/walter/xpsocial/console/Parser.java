package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Command;

public interface Parser {

    public Command parse(String row);

}
