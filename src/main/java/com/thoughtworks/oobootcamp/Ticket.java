package com.thoughtworks.oobootcamp;

import java.util.UUID;

public class Ticket {
    private UUID randomUUID;

    public Ticket() {
        this.randomUUID = UUID.randomUUID();
    }
}
