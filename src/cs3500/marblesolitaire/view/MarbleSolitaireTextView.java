package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * this class represents a type of view for the marble solitaire game, where the board is
 * represented as a string.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  /**
   * the model state to be used.
   */
  MarbleSolitaireModelState model;

  public MarbleSolitaireTextView(MarbleSolitaireModelState m) {
    this.model = m;
  }

  /**
   * represents the state of the game as a string.
   *
   * @return a view of the current game board as a string
   */
  public String toString() {
    int size = model.getBoardSize();
    int t = (size + 2) / 3;
    String start = "";

    if (model.getBoardSize() == 1) {
      return "_";
    }

    for (int r = 0; r < size; r++) {

      for (int c = 0; c < size; c++) {

        //end of marbles for top and bottom rows
        if ((c == size - t) && ((r > (2 * t) - 2) || (r < t - 1))) {

          if (model.getSlotAt(r, c) == SlotState.Marble) {
            start = start + "O";
          } else if (model.getSlotAt(r, c) == SlotState.Empty) {
            start = start + "_";
          }
        }

        //very end of a row
        else if (c == size - 1) {
          switch (model.getSlotAt(r, c)) {
            case Invalid:
              if (r == size - 1) {
                start = start + "";
              } else {
                start = start + "\n";
              }
              break;
            case Marble:
              start = start + "O\n";
              break;
            default:
              start = start + "_\n";
          }
        }

        //not the end of a row
        else if (c < size - 1
                && !((c == size - t) && ((r > (2 * t) - 2) || (r < t - 1)))) {
          switch (model.getSlotAt(r, c)) {
            case Invalid:
              if (c > size - t) {
                start = start + "";
              } else {
                start = start + "  ";
              }
              break;
            case Marble:
              start = start + "O ";
              break;
            default:
              start = start + "_ ";
          }
        }
      }
    }
    return start;
  }

}
