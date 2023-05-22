package edu.ntnu.idatt2001.paths.filehandling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.paths.model.Game;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.Story;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * The type File game handler test.
 */
class FileGameHandlerTest {
  static FileGameHandler fileGameHandler;
  Game game;

  @BeforeEach
  void setUp() {
    Player player = new PlayerBuilder("Jake").health(100).build();

    Passage passage = new Passage("Test", "Test");

    Story story = new Story("TestStory", passage);

    game = new Game(player, story);
  }

  @BeforeAll
  static void beforeAll() {
    fileGameHandler = new FileGameHandler();
  }

  @Test
  void saveGame() {
    assertDoesNotThrow(() -> fileGameHandler.saveGame(game, game.getStory().getOpeningPassage()));
  }

  @Test
  void loadGame() throws IOException {
    fileGameHandler.saveGame(game, game.getStory().getOpeningPassage());
    Game expectedgame = game;
    Game actualGame = fileGameHandler.loadGame(fileGameHandler.getUniqueId());

    assertEquals(expectedgame.getPlayer().getName(), actualGame.getPlayer().getName());
    assertEquals(expectedgame.getStory().getTitle(), actualGame.getStory().getTitle());
    assertEquals(expectedgame.getStory().getOpeningPassage().getTitle(),
            actualGame.getStory().getOpeningPassage().getTitle());
  }
}