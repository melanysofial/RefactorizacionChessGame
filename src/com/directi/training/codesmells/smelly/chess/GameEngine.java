package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;
import com.directi.training.codesmells.smelly.pieces.*;

public class GameEngine {
    private final ChessBoard _chessBoard;
    private final Player _player1, _player2;
    private Player _currentPlayer;

    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();

    public GameEngine(Player player1, Player player2)
    {
        _chessBoard = new ChessBoard();
        _player1 = player1;
        _player2 = player2;
        resetBoard();
    }

    public void initGame()
    {
        if (_currentPlayer == null || _player1.getColor() == Color.BLACK) {
            _currentPlayer = _player1;
            _player1.setColor(Color.WHITE);
            _player2.setColor(Color.BLACK);
        } else {
            _currentPlayer = _player2;
            _player1.setColor(Color.BLACK);
            _player2.setColor(Color.WHITE);
        }

        System.out.println("\nGame initialized");
        System.out.println("Player " + _player1.getName() + " has Color " + _player1.getColor());
        System.out.println("Player " + _player2.getName() + " has Color " + _player2.getColor());
        System.out.println("");

        resetBoard();
        System.out.println(_chessBoard);
    }

    public void startGame()
    {
        while (true) {
            System.out.println(
                    "Next move is of " + _currentPlayer.getName() +
                            " [" + _currentPlayer.getColor() + "]"
            );

            Position from = inputHandler.askPosition("Enter position (row col) of piece to move: ");
            Position to = inputHandler.askPosition("Enter destination position: ");

            Move move = new Move(from, to);

            if (isValidMove(move)) {
                makeMove(move);
            } else {
                System.out.println("Invalid move!");
            }
        }
    }


    public void resetBoard()
    {
        initializeMainPieces(7, Color.WHITE);
        initializePawns(6, Color.WHITE);

        initializeMainPieces(0, Color.BLACK);
        initializePawns(1, Color.BLACK);

        _chessBoard._kingDead = false;
    }

    private void initializeMainPieces(int row, Color color)
    {
        _chessBoard.getBoard()[row][0].setPiece(new Rook(color));
        _chessBoard.getBoard()[row][1].setPiece(new Knight(color));
        _chessBoard.getBoard()[row][2].setPiece(new Bishop(color));
        _chessBoard.getBoard()[row][3].setPiece(new King(color));
        _chessBoard.getBoard()[row][4].setPiece(new Queen(color));
        _chessBoard.getBoard()[row][5].setPiece(new Bishop(color));
        _chessBoard.getBoard()[row][6].setPiece(new Knight(color));
        _chessBoard.getBoard()[row][7].setPiece(new Rook(color));
    }

    private void initializePawns(int row, Color color)
    {
        for (int col = 0; col < 8; col++) {
            _chessBoard.getBoard()[row][col].setPiece(new Pawn(color));
        }
    }


    private void endGame()
    {
        System.out.println("Game Ended");
        Player winner = _currentPlayer;
        winner.increase();
        System.out.println("WINNER - " + winner + "\n\n");
    }

    private Player getOtherPlayer()
    {
        return _player1 == _currentPlayer ? _player2 : _player1;
    }

    public void makeMove(Move move)
    {
        _chessBoard.movePiece(
                move.getFrom().getRow(), move.getFrom().getColumn(),
                move.getTo().getRow(), move.getTo().getColumn()
        );

        System.out.println("");
        System.out.println(_chessBoard);

        if (_chessBoard.isKingDead()) {
            endGame();
            initGame();
        } else {
            _currentPlayer = getOtherPlayer();
        }
    }

    public boolean isValidMove(Move move)
    {
        return isPlayerMovingItsOwnColoredPiece(move.getFrom())
                && _chessBoard.isValidMove(
                move.getFrom().getRow(),
                move.getFrom().getColumn(),
                move.getTo().getRow(),
                move.getTo().getColumn()
        );
    }

    private boolean isPlayerMovingItsOwnColoredPiece(Position from) {
        return !_chessBoard.isEmpty(from)
                && _chessBoard.getPiece(from).getColor() == _currentPlayer.getColor();
    }

    public ChessBoard getChessBoard()
    {
        return _chessBoard;
    }
}