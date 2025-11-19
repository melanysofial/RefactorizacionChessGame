package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;

public class Rook extends Piece
{
    public Rook(Color color)
    {
        super(color);
    }

    @Override
    public String toString()
    {
        return "r";
    }

    @Override
    public boolean isValidMove(Position from, Position to)
    {
        return from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
    }
}
