import java.applet.*;
import java.awt.*;

public class ScratchpadApplet extends Applet {
  
  Button clearButton;
  Scratchpad scratchpad;
  
  public void init() {
    // create GUI components
    clearButton = new Button("clear");
    scratchpad = new Scratchpad();
    // layout GUI components in applet
    setLayout(new BorderLayout());
    add("South", clearButton);
    add("Center", scratchpad);
  }

  public boolean action(Event evt, Object arg){
    if (evt.target == clearButton) {
      scratchpad.clear(); // clear canvas
    }
    return true;
  }

}

