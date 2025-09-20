package com.example.qrcode;

public class Coords {
    private int x;
    private int y;
    private int skippedX;
    private int skippedY;
    private int size;

    public Coords(int size) {
        x = size - 2;
        y = size - 2;
        skippedX = 1;
        skippedY = 1;
        this.size = size;
    }

    public void moveLeft() {
        x--;
    }

    public void moveTopRight() {
        x++;
        y--;
    }

    public void moveBottomRight() {
        x++;
        y++;
    }

    public void update() {
        
        if (x % 4 == 1 || x % 4 == 3 || (x % 4 == 0 && y == size - 2) || (x % 4 == 2 && y == 0)) {
            moveLeft();
        } else if (x % 4 == 0) {
            moveBottomRight();
        } else if (x % 4 == 2) {
            moveTopRight();
        }

        if (x + skippedX == Constants.ALIGN_BLOCK_L.length - 1) {
            skippedX = 0;
        }
        if (y + skippedY == Constants.ALIGN_BLOCK_L.length - 1 && x % 4 == 3) {
            skippedY = 0;
        } else if (y == Constants.ALIGN_BLOCK_L.length - 1 && x % 4 == 1) {
            skippedY = 1;
        }
    }

    @Override
    public String toString() {
        return String.format("coords = (%d, %d), skippedX = %d, skippedY = %d", x + skippedX, y + skippedY, skippedX, skippedY);
    }

    public int x() {
        return x + skippedX;
    }

    public int y() {
        return y + skippedY;
    }
}
