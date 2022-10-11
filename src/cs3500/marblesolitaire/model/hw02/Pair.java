package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * This class represents a slot with a pair of coordinates and a state.
 */
public class Pair {
  /**
   * the row of the slot.
   */

  int slotRow;

  /**
   * the column of the slot.
   */
  int slotCol;

  /**
   * the slotstate of the slot.
   */
  MarbleSolitaireModelState.SlotState state;

  /**
   *Represents a slot on the board.
   *
   * @param r the designated row
   * @param c the designated column
   * @param t the arm thickness of the associated board
   * @param emptyR the empty row of the associated board
   * @param emptyC the empty column of the associated board
   */
  public Pair(int r, int c, int t, int emptyR, int emptyC) {
    this.slotRow = r;
    this.slotCol = c;

    if ((this.slotRow < t - 1 && this.slotCol < t - 1)
            || (this.slotRow < t - 1 && this.slotCol > (2 * t - 2))
            || (this.slotRow > (2 * t - 2) && this.slotCol < t - 1)
            || (this.slotRow > (2 * t - 2) && this.slotCol > (2 * t - 2))) {
      this.state = SlotState.Invalid;
    } else if (this.slotRow == emptyR && this.slotCol == emptyC) {
      this.state = SlotState.Empty;
    } else {
      this.state = SlotState.Marble;
    }
  }

  /**
   * Gets the state of the slot.
   *
   * @return the state of the slot
   */
  public SlotState getState() {
    return state;
  }

  /**
   * Sets the state of the slot.
   *
   * @param newState the new state of the slot
   */
  public void setState(SlotState newState) {
    this.state = newState;
  }

  /**
   * gets the row of the slot.
   *
   * @return the row of the slot
   */
  public int getSlotRow() {
    return this.slotRow;
  }

  /**
   * gets the column of the slot.
   *
   * @return the new column of the slot
   */
  public int getSlotCol() {
    return this.slotCol;
  }

  /**
   * determines if there are any valid moves from this slot.
   *
   * @param allSlots the list of all slots on the given board
   * @return whether there are any valid moves from this slot
   */
  public boolean validMovesLeft(ArrayList<Pair> allSlots, EnglishSolitaireModel board) {

    if (this.state == SlotState.Invalid || this.state == SlotState.Empty) {
      return false;
    }

    int i = 0;
    while (i < allSlots.size()) {
      if (this.validMove(allSlots.get(i), board)) {
        return true;
      }
      i++;
    }
    return false;
  }

  /**
   * Determines if there is a valid move from here to there.
   *
   * @param p the other given slot
   * @param board the applicable game board
   * @return whether there is a valid move from this slot to that slot
   */
  public boolean validMove(Pair p, EnglishSolitaireModel board) {

    if (p.getState() == SlotState.Invalid || p.getState() == SlotState.Marble) {
      return false; // the destination slot cannot be invalid or occupied

    } else if (state == SlotState.Invalid || state == SlotState.Empty) {
      return false; // this slot cannot be invalid or empty
    }

    if (p.getSlotCol() == this.slotCol
            && (p.getSlotRow() == this.slotRow - 2 || p.getSlotRow() == this.slotRow + 2)) {

      return this.marbleBetween(p, board);

    } else if (p.getSlotRow() == this.slotRow
            && (p.getSlotCol() == this.slotCol - 2 || p.getSlotCol() == this.slotCol + 2)) {

      return this.marbleBetween(p, board);
    }
    return false;
  }

  /**
   * determines if there is a marble between this slot and that slot.
   *
   * @param p the other slot
   * @return whether there is a marble between this slot and the given slot
   */
  private boolean marbleBetween(Pair p, EnglishSolitaireModel board) {

    if (p.getSlotRow() < this.slotRow) {
      return board.getSlotAt(this.slotRow - 1, this.slotCol) == SlotState.Marble;
    }

    else if (p.getSlotRow() > this.slotRow) {
      return board.getSlotAt(this.slotRow + 1, this.slotCol) == SlotState.Marble;
    }

    if (p.getSlotCol() < this.slotCol) {
      return board.getSlotAt(this.slotRow, this.slotCol - 1) == SlotState.Marble;
    }

    else if (p.getSlotCol() > this.slotCol) {
      return board.getSlotAt(this.slotRow, this.slotCol + 1) == SlotState.Marble;
    }

    return false;
  }
}
