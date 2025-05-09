package commands.factories;

import commands.Command;

import java.util.List;

public abstract class AbstractCommandsFactory {
    public abstract List<Command> getCommands();
}
