package tm;

/**
 * Enum for giving names to tape directions.
 * @author Andy Kempf
 */
public enum TMDirection
{
    RIGHT,
    LEFT;

    /**
     * Parse L, R string into tape direction enum values
     * @param direction String representation of tape direction ("L" & "R")
     * @return Enum value corresponding to String tape direction.
     */
    public static TMDirection parseDirection(String direction)
    {
        if (direction.equals("L")) return LEFT;
        if (direction.equals("R")) return RIGHT;

        throw new IllegalArgumentException("Invalid direction: " + direction);
    }
}