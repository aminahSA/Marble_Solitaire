package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * This class represents one possible variation of Marble Solitaire, called English Solitaire.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {

  /**
   * represents all the slots on the board as a list of Pairs.
   */
  public ArrayList<Pair> allSlots;

  /**
   * represents the arm thickness of the board.
   */
  int t;

  /**
   * represents the longest dimension of the board, or number of rows/columns.
   */
  int rowNum;

  /**
   * the row of the empty slot on the board.
   */
  int emptyR;

  /**
   * the column of the empty slot on the board.
   */
  int emptyC;

  /**
   * arm thickness of 3 and the empty slot at the center.
   */
  public EnglishSolitaireModel() {
    this.t = 3;
    this.rowNum = 7;
    this.emptyR = 3;
    this.emptyC = 3;
    this.allSlots = new ArrayList<>();

    for (int r = 0; r < rowNum; r++) {
      for (int c = 0; c < rowNum; c++) {
        this.allSlots.add(new Pair(r, c, t, emptyR, emptyC));
      }
    }
  }

  /**
   * arm thickness of 3 and the empty slot at (sRow, sCol).
   *
   * @param sRow empty row number
   * @param sCol empty column number
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    if ((sRow < 2 && sCol < 2)
            || (sRow < 2 && sCol > 4)
            || (sRow > 4 && sCol < 2)
            || (sRow > 4 && sCol > 4)) {
      throw new IllegalArgumentException("Invalid row, column coordinates.");
    }
    this.t = 3;
    this.rowNum = 7;
    this.emptyR = sRow;
    this.emptyC = sCol;
    this.allSlots = new ArrayList<>();

    for (int r = 0; r < rowNum; r++) {
      for (int c = 0; c < rowNum; c++) {
        this.allSlots.add(new Pair(r, c, t, emptyR, emptyC));
      }
    }
  }

  /**
   * arm thickness as specified and empty slot at the center.
   *
   * @param t arm thickness
   */
  public EnglishSolitaireModel(int t) {

    if (t % 2 == 0) {
      throw new IllegalArgumentException("t must be an odd number.");
    }

    this.t = t;
    this.rowNum = 3 * t - 2;
    this.emptyR = rowNum / 2;
    this.emptyC = rowNum / 2;
    this.allSlots = new ArrayList<>();

    for (int r = 0; r < rowNum; r++) {
      for (int c = 0; c < rowNum; c++) {
        this.allSlots.add(new Pair(r, c, t, emptyR, emptyC));
      }
    }
  }

  /**
   * arm thickness as specified and empty slot at (sRow, sCol).
   *
   * @param t    arm thickness
   * @param sRow empty row
   * @param sCol empty column
   */
  public EnglishSolitaireModel(int t, int sRow, int sCol) {

    if (t % 2 == 0) {
      throw new IllegalArgumentException("t must be an odd number.");
    }

    this.t = t;

    if ((sRow < t - 1 && sCol < t - 1)
            || (sRow < t - 1 && sCol > (2 * t - 2))
            || (sRow > (2 * t - 2) && sCol < t - 1)
            || (sRow > (2 * t - 2) && sCol > (2 * t - 2))) {
      throw new IllegalArgumentException("Invalid coordinates.");
    }

    this.rowNum = 3 * t - 2;
    this.emptyR = sRow;
    this.emptyC = sCol;
    this.allSlots = new ArrayList<>();

    for (int r = 0; r < rowNum; r++) {
      for (int c = 0; c < rowNum; c++) {
        this.allSlots.add(new Pair(r, c, t, emptyR, emptyC));
      }
    }
  }

  /**
   * Moves a marble from the designated slot to another designated slot.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the position of the slots chosen are not valid
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    Pair from = null;
    boolean foundFMatch = false;
    for (Pair p : allSlots) {
      if (p.getSlotRow() == fromRow && p.getSlotCol() == fromCol) {
        from = p;
        foundFMatch = true;
      }
    }
    if (!foundFMatch) {
      throw new IllegalArgumentException("origin cell is not a valid position on this board.");
    }

    Pair q = null;
    boolean foundTMatch = false;
    for (Pair p : allSlots) {
      if (p.getSlotRow() == toRow && p.getSlotCol() == toCol) {
        q = p;
        foundTMatch = true;
      }
    }
    if (!foundTMatch) {
      throw new IllegalArgumentException("destination cell is not a valid position on this board.");
    }

    if (!from.validMove(q, this)) {
      throw new IllegalArgumentException("Not a valid move.");
    }

    int midCol;
    int midRow;

    if (fromCol == toCol) {
      midCol = fromCol;
      if (fromRow < toRow) {
        midRow = fromRow + 1;
      } else {
        midRow = fromRow - 1;
      }
    } else if (fromRow == toRow) {
      midRow = fromRow;
      if (fromCol < toCol) {
        midCol = fromCol + 1;
      } else {
        midCol = fromCol - 1;
      }
    } else {
      throw new IllegalArgumentException("Invalid coordinates.");
    }

    this.updateSlots(fromRow, fromCol, toRow, toCol, midRow, midCol);

  }

  /**
   * updates the states of the given slots.
   *
   * @param fromRow the row number of the position to be moved from
   *                *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                *                (starts at 0)
   */
  private void updateSlots(int fromRow, int fromCol, int toRow, int toCol, int midRow, int midCol) {
    boolean foundF = false;
    boolean foundT = false;
    boolean foundM = false;

    for (Pair p : this.allSlots) {
      if (p.getSlotRow() == fromRow && p.getSlotCol() == fromCol) {
        p.setState(SlotState.Empty);
        foundF = true;
      } else if (p.getSlotRow() == toRow && p.getSlotCol() == toCol) {
        p.setState(SlotState.Marble);
        foundT = true;
      } else if (p.getSlotRow() == midRow && p.getSlotCol() == midCol) {
        p.setState(SlotState.Empty);
        foundM = true;
      }
    }

    if (!foundF || !foundT || !foundM) {
      throw new IllegalArgumentException(
              "One or more cells is not a valid position on this board.");
    }
  }

  /**
   * determines if the game is over based on if there are any valid moves remaining.
   *
   * @return true or false depending on if there are any valid moves left
   */
  @Override
  public boolean isGameOver() {
    int i = 0;
    while (i < this.allSlots.size()) {
      if (this.allSlots.get(i).validMovesLeft(this.allSlots, this)) {
        return false;
      }
      i++;
    }
    return true;
  }

  /**
   * returns the size of the board.
   *
   * @return the size of the board as an int
   */
  @Override
  public int getBoardSize() {
    return this.rowNum;
  }

  /**
   * Gets the state of the given slot.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the given slot
   * @throws IllegalArgumentException if the coordinates are not valid
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {

    if (row < 0 || row >= rowNum || col < 0 || col >= rowNum) {
      throw new IllegalArgumentException("Must input valid coordinates.");
    }
    SlotState toReturn = null;
    boolean foundMatch = false;

    for (Pair p : this.allSlots) {
      if (p.getSlotRow() == row && p.getSlotCol() == col) {
        toReturn = p.getState();
        foundMatch = true;
      }
    }
    if (!foundMatch) {
      throw new IllegalArgumentException("Invalid cell position.");
    }
    return toReturn;
  }

  /**
   * Gets the current score of the game.
   *
   * @return the current score of the game
   */
  @Override
  public int getScore() {
    int amt = 0;
    for (Pair p : this.allSlots) {
      if (p.getState() == SlotState.Marble) {
        amt++;
      }
    }
    return amt;
  }
}
