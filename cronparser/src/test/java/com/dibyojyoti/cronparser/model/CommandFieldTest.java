package com.dibyojyoti.cronparser.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandFieldTest {

    @Test
    public void testGetCommand() {
        // Setup: Create a CommandField with a specific command
        String expectedCommand = "/usr/bin/find";
        CommandField commandField = new CommandField(expectedCommand);

        // Action: Retrieve the command using getCommand
        String actualCommand = commandField.getCommand();

        // Assertion: Check that the returned command is the same as what was set
        assertEquals(expectedCommand, actualCommand, "The returned command should match the initialized command.");
    }
}
