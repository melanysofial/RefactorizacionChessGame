package com.directi.training.codesmells.smelly.pieces;

import com.directi.training.codesmells.smelly.Color;
import com.directi.training.codesmells.smelly.Position;

public abstract class Piece
{
    private Color _color;

    public Piece(Color color)
    {
        _color = color;
    }

    public Color getColor()
    {
        return _color;
    }

    // Replaced switch-case with polymorphism: each concrete Piece implements its own movement rules
    public abstract boolean isValidMove(Position from, Position to);
}
