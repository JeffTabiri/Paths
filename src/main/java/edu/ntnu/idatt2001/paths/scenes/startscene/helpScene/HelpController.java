package edu.ntnu.idatt2001.paths.scenes.startscene.helpScene;

public class HelpController {

  private HelpModel model;

    public HelpController(HelpModel model) {
        this.model = model;
    }

    public void goBackHandler() {
        model.goBackHandler();
    }


}
