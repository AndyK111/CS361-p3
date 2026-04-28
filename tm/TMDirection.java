package tm;

public enum TMDirection
{
    RIGHT,
    LEFT;

    public static TMDirection parseDirection(String direction)
    {
        if (direction.equals("L")) return LEFT;
        if (direction.equals("R")) return RIGHT;

        throw new IllegalArgumentException("Invalid direction: " + direction);
    }
}