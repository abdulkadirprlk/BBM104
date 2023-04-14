

import java.io.*;
import java.util.ArrayList;


public class Main {//I created 8 global variables to access them from all functions.
    static ArrayList<String[]> board = new ArrayList<>();
    static ArrayList<String> moves = new ArrayList<>();
    static int point = 0;
    static boolean isGameOver = false;
    static int whiteBallRow = 0;
    static int whiteBallColumn = 0;
    static String initialBoard = "";//boardKeeper simply keeps the initial board in order to print it to the output file.
    static ArrayList<String> playedMoves = new ArrayList<String>();

    public static void main(String[] args) {

        readBoardFile(args[0]);
        readMoveFile(args[1]);
        play();
        writeOutputFile();
    }

    public static void readBoardFile(String fileName) {//In the board variable, the rows are arrays.

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = reader.readLine()) != null) {
                board.add(line.split(" "));
                initialBoard += line;
                initialBoard += "\n";
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readMoveFile(String fileName) {//moves is an ArrayList.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String[] lineArray = reader.readLine().split(" ");
            for (String move : lineArray) {
                moves.add(move);
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeOutputFile() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            writer.write("Game Board:\n");//printing the game board
            writer.write(initialBoard);
            writer.newLine();

            writer.write("Your movement is:\n");//printing played moves
            for (String i : playedMoves) {
                writer.write(i + " ");
            }
            writer.newLine();
            writer.newLine();

            writer.write("Your output is:\n");//printing the output
            for (String[] row : board) {
                for (String cell : row) {
                    writer.write(cell + " ");
                }
                writer.newLine();
            }
            writer.newLine();

            if (isGameOver) {//if ball falls into the hole.
                writer.write("Game over!\n");
            }

            writer.write(String.format("Score: %d", point));//printing the score

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void moveToTheLeft() {
        if (whiteBallColumn < 0) {//in order not to get indexOutOfBoundsException
            whiteBallColumn += board.get(0).length;
        }
        if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("B")) {//for the color of black
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point -= 5;//collision with the black ball
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("G")) {//for the color of gray
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "G";
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("Y")) {//for the color of yellow
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point += 5;//collision with the yellow ball
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("R")) {//for the color of red
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point += 10;
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("P")) {//for the color of pink
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "P";
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("O")) {//for the color of orange
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "O";
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("D")) {//for the color of dark blue
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "D";
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("L")) {//for the color of light blue
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "L";
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("F")) {//for the color of fuchsia
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "F";
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("N")) {//for the color of navy blue
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "N";
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("H")) {//ball falls into the hole and game is over
            board.get(whiteBallRow)[whiteBallColumn] = " ";//ball disappears
            isGameOver = true;
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("W")) {//collision with the wall
            moveToTheRight();//collision with the ball means move opposite direction.
        } else if (board.get(whiteBallRow)[whiteBallColumn - 1].equals("X")) {
            board.get(whiteBallRow)[whiteBallColumn - 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
        }

    }

    public static void moveToTheRight() {
        if (whiteBallColumn + 1 >= board.get(0).length) {//in order not to get indexOutOfBoundsException
            whiteBallColumn -= board.get(0).length;
        }
        if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("B")) {//for the color of black
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point -= 5;//collision with the black ball
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("G")) {//for the color of gray
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "G";
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("Y")) {//for the color of yellow
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point += 5;//collision with the yellow ball
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("R")) {//for the color of red
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point += 10;
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("P")) {//for the color of pink
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "P";
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("O")) {//for the color of orange
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "O";
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("D")) {//for the color of dark blue
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "D";
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("L")) {//for the color of light blue
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "L";
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("F")) {//for the color of fuchsia
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "F";
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("N")) {//for the color of navy blue
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "N";
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("H")) {//ball falls into the hole and game is over
            board.get(whiteBallRow)[whiteBallColumn] = " ";//ball disappears
            isGameOver = true;
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("W")) {//collision with the wall
            moveToTheLeft();
        } else if (board.get(whiteBallRow)[whiteBallColumn + 1].equals("X")) {
            board.get(whiteBallRow)[whiteBallColumn + 1] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
        }
    }

    public static void moveToTheUp() {
        if (whiteBallRow < 0) {//in order not to get indexOutOfBoundsException
            whiteBallRow += board.size();
        }
        if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("B")) {//for the color of black
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point -= 5;//collision with the black ball
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("G")) {//for the color of gray
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "G";
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("Y")) {//for the color of yellow
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point += 5;//collision with the yellow ball
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("R")) {//for the color of red
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point += 10;
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("P")) {//for the color of pink
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "P";
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("O")) {//for the color of orange
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "O";
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("D")) {//for the color of dark blue
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "D";
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("L")) {//for the color of light blue
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "L";
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("F")) {//for the color of fuchsia
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "F";
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("N")) {//for the color of navy blue
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "N";
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("H")) {//ball falls into the hole and game is over
            board.get(whiteBallRow)[whiteBallColumn] = " ";//ball disappears
            isGameOver = true;
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("W")) {//collision with the wall
            moveToTheDown();
        } else if (board.get(whiteBallRow - 1)[whiteBallColumn].equals("X")) {
            board.get(whiteBallRow - 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
        }

    }

    public static void moveToTheDown() {
        if (whiteBallRow + 1 >= board.size()) {//in order not to get indexOutOfBoundsException
            whiteBallRow -= board.size();
        }
        if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("B")) {//for the color of black
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point -= 5;//collision with the black ball
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("G")) {//for the color of gray
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "G";
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("Y")) {//for the color of yellow
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point += 5;//collision with the yellow ball
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("R")) {//for the color of red
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
            point += 10;
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("P")) {//for the color of pink
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "P";
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("O")) {//for the color of orange
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "O";
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("D")) {//for the color of dark blue
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "D";
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("L")) {//for the color of light blue
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "L";
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("F")) {//for the color of fuchsia
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "F";
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("N")) {//for the color of navy blue
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "N";
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("H")) {//ball falls into the hole and game is over
            board.get(whiteBallRow)[whiteBallColumn] = " ";//ball disappears
            isGameOver = true;
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("W")) {//collision with the wall
            moveToTheUp();
        } else if (board.get(whiteBallRow + 1)[whiteBallColumn].equals("X")) {
            board.get(whiteBallRow + 1)[whiteBallColumn] = "*";//swapping operation
            board.get(whiteBallRow)[whiteBallColumn] = "X";
        }

    }

    public static void play() {//game is playing here.
        for (String move : moves) {//iterates over all moves
            //at the beginning of the every move, the for loop below determines the location of the white ball.
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.get(i).length; j++) {
                    if (board.get(i)[j].equals("*")) {
                        whiteBallRow = i;
                        whiteBallColumn = j;
                    }
                }
            }
            if (isGameOver) {
                break;
            } else if (move.equals("L")) {//move to the LEFT
                moveToTheLeft();
                playedMoves.add("L");
            } else if (move.equals("R")) {//move to the RIGHT
                moveToTheRight();
                playedMoves.add("R");
            } else if (move.equals("U")) {//move to the UP
                moveToTheUp();
                playedMoves.add("U");
            } else if (move.equals("D")) {//move to the DOWN
                moveToTheDown();
                playedMoves.add("D");
            }
        }
    }
}