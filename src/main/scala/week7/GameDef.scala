package week7

import scala.language.postfixOps

/**
  * This trait represents the layout and building blocks of the game
  */
trait GameDef {

  /**
    * The terrain is represented as a function from positions to
    * booleans. The function returns `true` for every position that
    * is inside the terrain.
    *
    * As explained in the documentation of class `Pos`, the `row` axis
    * is the vertical one and increases from top to bottom.
    */
  type Terrain = Pos => Boolean

  /**
    * The position where the block is located initially.
    *
    * This value is left abstract, it will be defined in concrete
    * instances of the game.
    */
  val startPos: Pos

  /**
    * The target position where the block has to go.
    * This value is left abstract.
    */
  val goal: Pos

  /**
    * The terrain of this game. This value is left abstract.
    */
  val terrain: Terrain

  /**
    * This function returns the block at the start position of
    * the game.
    */
  def startBlock: Block = Block(startPos, startPos)

  /**
    * In Bloxorz, we can move left, right, Up or down.
    * These moves are encoded as case objects.
    */
  sealed abstract class Move

  /**
    * The case class `Pos` encodes positions in the terrain.
    *
    * IMPORTANT NOTE
    * - The `row` coordinate denotes the position on the vertical axis
    * - The `col` coordinate is used for the horizontal axis
    * - The coordinates increase when moving down and right
    *
    * Illustration:
    *
    * 0 1 2 3   <- row axis
    * 0 o o o o
    * 1 o o o o
    * 2 o # o o    # is at position Pos(2, 1)
    * 3 o o o o
    *
    * ^^
    * |
    *
    * col axis
    */
  case class Pos(row: Int, col: Int) {

    /** The position obtained by changing the `x` coordinate by `d` */
    def dRow(d: Int): Pos = copy(row = row + d)

    /** The position obtained by changing the `y` coordinate by `d` */
    def dCol(d: Int): Pos = copy(col = col + d)
  }

  /**
    * A block is represented by the position of the two cubes that
    * it consists of. We make sure that `b1` is lexicographically
    * smaller than `b2`.
    */
  case class Block(b1: Pos, b2: Pos) {

    // checks the requirement mentioned above
    require(
      b1.row <= b2.row && b1.col <= b2.col,
      "Invalid block position: b1=" + b1 + ", b2=" + b2
    )

    /**
      * Returns the list of positions reachable from the current block
      * which are inside the terrain.
      */
    def legalNeighbors: List[(Block, Move)] = neighbors filter (_._1 isLegal)

    /**
      * Returns the list of blocks that can be obtained by moving
      * the current block, together with the corresponding move.
      */
    def neighbors: List[(Block, Move)] =
      List((left, Left), (right, Right), (up, Up), (down, Down))

    /** The block obtained by moving left */
    def left: Block =
      if (isStanding) dCol(-2, -1)
      else if (b1.row == b2.row) dCol(-1, -2)
      else dCol(-1, -1)

    /** The block obtained by moving right */
    def right: Block =
      if (isStanding) dCol(1, 2)
      else if (b1.row == b2.row) dCol(2, 1)
      else dCol(1, 1)

    /**
      * Returns a block where the `col` coordinates of `b1` and `b2` are
      * changed by `d1` and `d2`, respectively.
      */
    def dCol(d1: Int, d2: Int): Block = Block(b1.dCol(d1), b2.dCol(d2))

    /** The block obtained by moving up */
    def up: Block =
      if (isStanding) dRow(-2, -1)
      else if (b1.row == b2.row) dRow(-1, -1)
      else dRow(-1, -2)

    /** The block obtained by moving down */
    def down: Block =
      if (isStanding) dRow(1, 2)
      else if (b1.row == b2.row) dRow(1, 1)
      else dRow(2, 1)

    /**
      * Returns `true` if the block is standing.
      */
    def isStanding: Boolean = b1 == b2

    /**
      * Returns a block where the `row` coordinates of `b1` and `b2` are
      * changed by `d1` and `d2`, respectively.
      */
    def dRow(d1: Int, d2: Int): Block = Block(b1.dRow(d1), b2.dRow(d2))

    /**
      * Returns `true` if the block is entirely inside the terrain.
      */
    def isLegal: Boolean = terrain(b1) && terrain(b2)

  }

  case object Left extends Move
  case object Right extends Move
  case object Up extends Move
  case object Down extends Move

}
