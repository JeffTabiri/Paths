package edu.ntnu.idatt2001.paths.utility;

public enum GameStates {
  MAIN_MENU("/audio/backgroundMusic/mainMenu.mp3"),
  OPTION_MENU(null);

  private String value;
  private GameStates(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }





}

