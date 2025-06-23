package consoleui.repository;

import consoleui.entity.ConsoleUiMessage;
import consoleui.entity.UIActionResult;

public interface ConsoleUiRepository {
    void displayWelcomeMessage();

    void displayInitialMessage();
    UIActionResult displayMessageFromUserInput(ConsoleUiMessage selectedMessage);

    void displayExitMessage();

    void showGameMenu();

    void displayErrorMessage();
}
