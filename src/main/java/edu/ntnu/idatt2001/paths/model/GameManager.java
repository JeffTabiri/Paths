package edu.ntnu.idatt2001.paths.model;

public class GameManager {

  Game game;

  AchievementList achievementList = AchievementList.getInstance();

  Boolean isPlayerAlive;

  Boolean isGameWon;

  Passage currentPassage;



    public GameManager(Game game) {
      currentPassage = game.getStory().getOpeningPassage();
      this.game = game;
    }

    public Passage getCurrentPassage() {
      return currentPassage;
    }


    public boolean getIsPlayerAlive() {

      isPlayerAlive = game.getPlayer().getHealth() >= 0;

      return isPlayerAlive;

    }

    public boolean isGameWon() {
      return isGameWon;
    }

    public void setCurrentPassage(Passage passage) {
      this.currentPassage = passage;
    }

  public Boolean getGameWon() {
    return isGameWon;
  }

  public Game getGame() {
    return game;
  }


  public void setGame(Game game) {
    this.game = game;
  }


}
