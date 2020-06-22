package com.walter.xpsocial.domain;

import java.time.LocalDateTime;

public class ClockMock implements Clock {

    private int counter;
    private LocalDateTime[] times;

    public ClockMock doReturn(LocalDateTime... values) {
        times = values;
        counter = 0;
        return this;
    }

    @Override
    public LocalDateTime currentTime() {
        LocalDateTime result = times[counter];
        if (counter < times.length-1) {
            counter++;
        }
        return result;
    }
};
