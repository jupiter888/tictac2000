
package com.example.expansion888.tictac2000;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import java.util.Random;

        import static com.example.expansion888.tictac2000.R.id.button1;
        import static com.example.expansion888.tictac2000.R.id.button2;
        import static com.example.expansion888.tictac2000.R.id.button3;
        import static com.example.expansion888.tictac2000.R.id.button4;
        import static com.example.expansion888.tictac2000.R.id.button5;
        import static com.example.expansion888.tictac2000.R.id.button6;
        import static com.example.expansion888.tictac2000.R.id.button7;
        import static com.example.expansion888.tictac2000.R.id.button8;
        import static com.example.expansion888.tictac2000.R.id.button9;
        import static com.example.expansion888.tictac2000.R.id.textGameStatus;

public class MainActivity extends AppCompatActivity  {

    // Player X, O
    enum Player { X, O }

    // List of Players
    static Player[] players = new Player[] {Player.X, Player.O};

    // Current Player
    Player currentPlayer;

    // Game Over
    Boolean gameOver = true;

    // Game Grid
    Button gameGrid[][] = new Button[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid[0][0] = (Button) findViewById(button1);
        gameGrid[1][0] = (Button) findViewById(button2);
        gameGrid[2][0] = (Button) findViewById(button3);
        gameGrid[0][1] = (Button) findViewById(button4);
        gameGrid[1][1] = (Button) findViewById(button5);
        gameGrid[2][1] = (Button) findViewById(button6);
        gameGrid[0][2] = (Button) findViewById(button7);
        gameGrid[1][2] = (Button) findViewById(button8);
        gameGrid[2][2] = (Button) findViewById(button9);
    }


    public void updateStatus(String text) {
        TextView tGS = (TextView) findViewById(textGameStatus);
        tGS.setText(text);
    }

    public boolean t(int x, int y) {
        Button piece = gameGrid[x][y];
        if(piece.getTag() != null) {
            return(true);
        }else{
            return(false);
        }
    }

    // Test Board Location
    public boolean tp(int x, int y, Player player) {
        Button piece = gameGrid[x][y];
        if(piece.getTag() == player) {
            return(true);
        }else{
            return(false);
        }
    }

    public void endGame(Player w) {
        if(w == Player.X) {
            gameOver = true;
            updateStatus("Player X is the winner!");
        }
        if(w == Player.O) {
            gameOver = true;
            updateStatus("Player O is the winner!");
        }
    }

    public void testForTie() {
        if(t(0,0) && t(0,1) && t(0,2) &&
                t(1,0) && t(1,1) && t(1,2) &&
                t(2,0) && t(2,1) && t(2,2)) {
            gameOver = true;
            updateStatus("Game is a tie!");
        }
    }

    public void testForWinner() {
        for(Player cp : players) {
            if((tp(0,0,cp) && tp(1,0,cp) && tp(2,0,cp)) ||
                    (tp(0,1,cp) && tp(1,1,cp) && tp(2,1,cp)) ||
                    (tp(0,2,cp) && tp(1,2,cp) && tp(2,2,cp)) ||
                    (tp(0,0,cp) && tp(0,1,cp) && tp(0,2,cp)) ||
                    (tp(1,0,cp) && tp(1,1,cp) && tp(1,2,cp)) ||
                    (tp(2,0,cp) && tp(2,1,cp) && tp(2,2,cp)) ||
                    (tp(0,0,cp) && tp(1,1,cp) && tp(2,2,cp)) ||
                    (tp(2,0,cp) && tp(1,1,cp) && tp(0,2,cp))) { endGame(cp); }
        }
    }

    public void buttonOnClick(View v) {

        // Is the game still running?
        if(!gameOver){

            // Assign current button to placeholder as a Button object
            Button button=(Button) v;

            // Is the button blank?
            if(button.getTag() == null) {
                switch(currentPlayer) {
                    case X:
                        button.setText("X");
                        button.setTag(Player.X);
                        currentPlayer = Player.O;
                        updateStatus("Player O, it's your turn");
                        break;
                    case O:
                        button.setText("O");
                        button.setTag(Player.O);
                        currentPlayer = Player.X;
                        updateStatus("Player X, it's your turn!");
                        break;
                }
                testForWinner();
                testForTie();
            }
        }
    }

    public void buttonNewGameOnClick(View v) {
        for(int x = 0; x<3; x++) {
            for(int y = 0; y<3; y++) {
                gameGrid[x][y].setTag(null);
                gameGrid[x][y].setText(" ");
            }
        }
        gameOver = false;
        int rnd = new Random().nextInt(players.length);
        currentPlayer = players[rnd];
        switch (currentPlayer){
            case X:
                updateStatus("Player X, it's your turn!");
                break;
            case O:
                updateStatus("Player O, it's your turn!");
                break;
        }
    }

}