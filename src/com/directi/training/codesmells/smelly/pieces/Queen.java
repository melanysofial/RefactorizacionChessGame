package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;

public class Queen extends Piece
{
    public Queen(Color color)
    {
        super(color);
    }

    @Override
    public String toString()
    {
        return "q";
    }

    @Override
    public boolean isValidMove(Position from, Position to)
    {
        return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn())
                || from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
    }
}
