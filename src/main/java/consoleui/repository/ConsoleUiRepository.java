package consoleui.repository;

import consoleui.entity.ConsoleUiMessage;

public interface ConsoleUiRepository {
    void displayWelcomeMessage();

    void displayInitialMessage();
    boolean displayMessageFromUserInput(ConsoleUiMessage selectedMessage);

    void displayExitMessage();
}
