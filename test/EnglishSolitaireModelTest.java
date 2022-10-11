import org.junit.Test;


import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.Pair;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * To test the EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {

  @Test
  public void testConstructor() {
    MarbleSolitaireModel m = new EnglishSolitaireModel();
    assertEquals(7, m.getBoardSize());
    assertEquals(32, m.getScore());
    assertEquals(false, m.isGameOver());

    MarbleSolitaireModel m1 = new EnglishSolitaireModel(0, 2);
    assertEquals(32, m1.getScore());
    assertEquals(false, m1.isGameOver());

    MarbleSolitaireModel m2 = new EnglishSolitaireModel(5, 3);
    assertEquals(32, m2.getScore());
    assertEquals(false, m2.isGameOver());

    MarbleSolitaireModel m5 = new EnglishSolitaireModel(3, 3);
    assertEquals(32, m5.getScore());
    assertEquals(false, m5.isGameOver());

    MarbleSolitaireModel m3 = new EnglishSolitaireModel(5, 6, 6);
    assertEquals(13, m3.getBoardSize());
    assertEquals(104, m3.getScore());
    assertEquals(false, m3.isGameOver());

    MarbleSolitaireModel m8 = new EnglishSolitaireModel(19, 18, 18);

    MarbleSolitaireModel m9 = new EnglishSolitaireModel(1);
    assertEquals(0, m9.getScore());

    MarbleSolitaireModel m6 = new EnglishSolitaireModel(5);
    assertEquals(104, m6.getScore());

    MarbleSolitaireModel m7 = new EnglishSolitaireModel(7);
    assertEquals(216, m7.getScore());

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(16, 29, 29));

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(14, 7, 7));

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(4));

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(5, 1, 1));

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(3, 1, 6));

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(5, 10, 3));

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(0, 1, 1));

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(0));

    //test new models custom empty cells
    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(5, 8));

    assertThrows(IllegalArgumentException.class,
        () -> new EnglishSolitaireModel(0, 0));

    MarbleSolitaireModel m10 = new EnglishSolitaireModel(3, 3);
    assertEquals(32, m10.getScore());
    MarbleSolitaireModel m11 = new EnglishSolitaireModel(3, 6);
    assertEquals(32, m11.getScore());
    MarbleSolitaireModel m12 = new EnglishSolitaireModel(6, 4);
    assertEquals(32, m12.getScore());
    MarbleSolitaireModel m13 = new EnglishSolitaireModel(2, 2);
    assertEquals(32, m13.getScore());
    MarbleSolitaireModel m14 = new EnglishSolitaireModel(4, 6);
    assertEquals(32, m14.getScore());

    //test new model custom thickness and empty cell
    MarbleSolitaireModel m15 = new EnglishSolitaireModel(1, 0, 0);
    assertEquals(0, m15.getScore());
    MarbleSolitaireModel m16 = new EnglishSolitaireModel(5, 12, 8);
    assertEquals(104, m16.getScore());
    MarbleSolitaireModel m17 = new EnglishSolitaireModel(7, 7, 9);
    assertEquals(216, m17.getScore());
    MarbleSolitaireModel m18 = new EnglishSolitaireModel(9, 11, 13);
    assertEquals(368, m18.getScore());

    //test views for these models
    MarbleSolitaireView view = new MarbleSolitaireTextView(m14);
    System.out.println(view.toString());
  }


  @Test
  public void testMove() {

    //move from (5, 3) to (3, 3) on a standard board
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    assertEquals(32, model.getScore());
    assertEquals(model.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    // System.out.println(view.toString());
    model.move(5, 3, 3, 3);
    assertEquals(model.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    //System.out.println(view.toString());
    assertEquals(31, model.getScore());
    assertEquals(false, model.isGameOver());


    //move from (3, 5) to (3, 3) on a standard board
    EnglishSolitaireModel model1 = new EnglishSolitaireModel();
    assertEquals(32, model1.getScore());
    assertEquals(model1.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    model1.move(3, 5, 3, 3);
    assertEquals(model1.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(31, model1.getScore());
    assertEquals(false, model1.isGameOver());


    //move from (0, 4) to (0, 2) on a (0, 2) empty slot board t=3
    EnglishSolitaireModel model4 = new EnglishSolitaireModel(0, 2);
    MarbleSolitaireTextView view4 = new MarbleSolitaireTextView(model4);
    assertEquals(32, model4.getScore());
    assertEquals(model4.getSlotAt(0, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model4.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Empty);
    //System.out.println(view4.toString());
    model4.move(0, 4, 0, 2);
    assertEquals(model4.getSlotAt(0, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model4.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Marble);
    //System.out.println(view4.toString());
    assertEquals(31, model4.getScore());
    assertEquals(false, model4.isGameOver());


    // move from 3, 0 to 3, 3 <-- invalid move
    MarbleSolitaireModel model5 = new EnglishSolitaireModel();
    assertThrows(IllegalArgumentException.class,
        () -> model5.move(3, 0, 3, 3));
    assertThrows(IllegalArgumentException.class,
        () -> model5.move(4, 4, 4, 4));


    //move from (1, 8) to (0, 8) <-- invalid move
    MarbleSolitaireModel model2 = new EnglishSolitaireModel(5, 0, 8);
    assertThrows(IllegalArgumentException.class,
        () -> model2.move(1, 8, 0, 8));
  }


  @Test
  public void isGameOver() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    assertEquals(false, model.isGameOver());
    model.move(5, 3, 3, 3);
    assertEquals(false, model.isGameOver());


    EnglishSolitaireModel m1 = new EnglishSolitaireModel(3);
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(m1);
    //System.out.println(view2.toString());

    for (Pair p : m1.allSlots) {
      p.setState(MarbleSolitaireModelState.SlotState.Empty);
    }

    //System.out.println(view2.toString());
    assertEquals(true, m1.isGameOver());
    assertEquals(0, m1.getScore());
    assertEquals(49, m1.allSlots.size());

    EnglishSolitaireModel m2 = new EnglishSolitaireModel(7);
    assertEquals(false, m2.isGameOver());

    MarbleSolitaireModel m3 = new EnglishSolitaireModel(9, 11, 13);
    assertEquals(false, m3.isGameOver());


    EnglishSolitaireModel aModel = new EnglishSolitaireModel();
    assertEquals(false, aModel.isGameOver());
    playGame(aModel);
    assertEquals(true, aModel.isGameOver());

  }

  @Test
  public void getBoardSize() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    assertEquals(7, model.getBoardSize());

    MarbleSolitaireModel m2 = new EnglishSolitaireModel(0, 2);
    assertEquals(7, m2.getBoardSize());
  }

  @Test
  public void testToString() {
    MarbleSolitaireModel m = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(m);
    String expected = "    O O O\n" + "    O O O\n"
            + "O O O O O O O\n" + "O O O _ O O O\n" + "O O O O O O O\n"
            + "    O O O\n" + "    O O O";
    assertEquals(expected, view.toString());

    MarbleSolitaireModel m1 = new EnglishSolitaireModel(5);
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(m1);
    String expected1 = "        O O O O O\n" + "        O O O O O\n"
            + "        O O O O O\n" + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n" + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n" + "        O O O O O\n" + "        O O O O O\n"
            + "        O O O O O";
    assertEquals(expected1, view1.toString());

    MarbleSolitaireModel m2 = new EnglishSolitaireModel(1, 0, 0);
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(m2);
    assertEquals("_", view2.toString());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(0, 0));

    EnglishSolitaireModel m3 = new EnglishSolitaireModel(3, 4, 3);
    MarbleSolitaireView view3 = new MarbleSolitaireTextView(m3);
    System.out.println(view3.toString());
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m3.getSlotAt(4, 4));
    for (Pair p : m3.allSlots) {
      p.setState(MarbleSolitaireModelState.SlotState.Empty);
    }
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m3.getSlotAt(2, 1));
  }

  @Test
  public void getSlotAt() {
    MarbleSolitaireModel m1 = new EnglishSolitaireModel(3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m1.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m1.getSlotAt(3, 3));
    assertThrows(IllegalArgumentException.class, () -> m1.getSlotAt(-1, 0));

    MarbleSolitaireModel m11 = new EnglishSolitaireModel(3, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m11.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m11.getSlotAt(3, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m11.getSlotAt(0, 0));

    MarbleSolitaireModel m12 = new EnglishSolitaireModel(6, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m12.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m12.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m12.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m12.getSlotAt(5, 5));

    MarbleSolitaireModel m15 = new EnglishSolitaireModel(1, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m15.getSlotAt(0, 0));

    MarbleSolitaireModel m16 = new EnglishSolitaireModel(5, 12, 8);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m16.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m16.getSlotAt(12, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m16.getSlotAt(9, 3));

    MarbleSolitaireModel m17 = new EnglishSolitaireModel(7, 7, 9);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m17.getSlotAt(12, 13));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m17.getSlotAt(7, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m17.getSlotAt(5, 2));

    MarbleSolitaireModel m18 = new EnglishSolitaireModel(9, 11, 13);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m18.getSlotAt(15, 13));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m18.getSlotAt(11, 13));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m18.getSlotAt(7, 7));
  }

  @Test
  public void getScore() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    assertEquals(model.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    System.out.println(view.toString());
    assertEquals(32, model.getScore());
    model.move(5, 3, 3, 3);
    assertEquals(31, model.getScore());
    assertEquals(model.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    System.out.println(view.toString());
  }

  @Test
  public void testValidMovesLeft() {
    EnglishSolitaireModel m = new EnglishSolitaireModel();
    Pair p = m.allSlots.get(10);
    assertEquals(true, p.validMovesLeft(m.allSlots, m));
  }

  @Test
  public void testValidMove() {
    EnglishSolitaireModel m = new EnglishSolitaireModel();
    Pair p = m.allSlots.get(10);
    assertEquals(true, p.validMove(m.allSlots.get(24), m));

    EnglishSolitaireModel m1 = new EnglishSolitaireModel(6, 4);
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(m1);
    Pair p1 = m1.allSlots.get(44);
    //System.out.println(view1.toString());
    assertEquals(true, p1.validMove(m1.allSlots.get(46), m1));
    assertEquals(true, p1.validMovesLeft(m1.allSlots, m1));


    EnglishSolitaireModel m2 = new EnglishSolitaireModel(5);
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(m2);
    //System.out.println(view2.toString());
    Pair p2 = m2.allSlots.get(6);
    assertEquals(false, p2.validMovesLeft(m2.allSlots, m2));
    Pair p3 = m2.allSlots.get(72);
    assertEquals(false, p3.validMovesLeft(m2.allSlots, m2));

    Pair p4 = m2.allSlots.get(84); //empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, p4.getState());
    Pair p5 = m2.allSlots.get(86); //marble
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, p5.getState());
    assertEquals(true, p5.validMovesLeft(m2.allSlots, m2));
    assertEquals(true, p5.validMove(p4, m2));
    assertEquals(false, p4.validMove(p5, m2));
    assertEquals(false, p4.validMovesLeft(m2.allSlots, m2));
    assertEquals(false, p4.validMove(p4, m2));

    Pair p6 = m2.allSlots.get(139);
    assertEquals(false, p6.validMovesLeft(m2.allSlots, m2));

    Pair p7 = m2.allSlots.get(168);
    //System.out.println(p7.getState());
    assertEquals(false, p7.validMove(p2, m2));
    assertEquals(false, p7.validMove(p7, m2));
    assertEquals(false, p7.validMovesLeft(m2.allSlots, m2));

    Pair p8 = new Pair(5, 0, 3, 3, 3);
    Pair p9 = new Pair(3, 0, 3, 3, 3);
    assertEquals(false, p8.validMove(p9, m));
    assertEquals(false, p9.validMove(p8, m));

  }

  /**
   * Plays through the game.
   *
   * @param m the model
   */
  public void playGame(EnglishSolitaireModel m) {
    MarbleSolitaireTextView v = new MarbleSolitaireTextView(m);
    System.out.println(v.toString()); //print the game before any moves

    for (Pair p : m.allSlots) {
      if (!m.isGameOver()) {
        if (p.validMovesLeft(m.allSlots, m)) {
          for (Pair j : m.allSlots) {
            if (p.validMove(j, m)) {
              System.out.println("moving from (" + p.getSlotRow() + ", " + p.getSlotCol() + ") to ("
                      + j.getSlotRow() + ", " + j.getSlotCol() + ")\n");
              m.move(p.getSlotRow(), p.getSlotCol(), j.getSlotRow(), j.getSlotCol());
              System.out.println(v.toString() + "\n");
            }
          }
        }
      }
    }
    if (!m.isGameOver()) {
      playGame(m);
    }
  }
}
