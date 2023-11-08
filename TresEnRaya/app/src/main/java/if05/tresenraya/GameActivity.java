package if05.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    static TableLayout tableLayout;
    private int player1;
    private int player2;
    boolean winGame;
    boolean loseGame;
    boolean tablas;

    static String userName;

    int[][] tablero ;
    TextView etUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadComponents();
        pintarTablero();


    }

    private void loadComponents() {
        player1 = 1;
        player2 = 2;
        tableLayout = findViewById(R.id.tlBoard);

        userName = getIntent().getExtras().get("userName").toString();
        etUsuario = findViewById(R.id.tvPlayer);
        etUsuario.setText(userName);
        tablero = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        winGame=false;
        loseGame=false;
        tablas=false;

    }


    private void turnoIA() {

        Random rng = new Random();
        int x, y;
        do {
            x = rng.nextInt(3);
            y = rng.nextInt(3);
        } while (tablero[x][y] != 0);

        tablero[x][y] = player2;
    }

    private void pintarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {

                String cellTag = "c" + i + j;
                ImageView cellView = tableLayout.findViewWithTag(cellTag);
                if (tablero[i][j] == 0) {
                    cellView.setBackgroundColor(Color.BLACK);
                } else if (tablero[i][j] == player1) {
                    cellView.setBackground(getDrawable(R.drawable.xletter));
                } else if (tablero[i][j] == player2) {
                    cellView.setBackground(getDrawable(R.drawable.neon_circle));
                }

            }
        }
    }
    private void gameOver(){
        // Crear una referencia a la vista raíz de tu actividad
        View rootView = getWindow().getDecorView().getRootView();

        // Crear un bitmap de la vista raíz
        rootView.setDrawingCacheEnabled(true);
        Bitmap screenshot = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        // Guardar el bitmap en un archivo temporal
        try {
            File screenshotFile = new File(getExternalCacheDir(), "screenshot.png");
            FileOutputStream outputStream = new FileOutputStream(screenshotFile);
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

            // Ahora puedes agregar la ruta del archivo temporal al intent
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("userName", userName);
            intent.putExtra("winGame", winGame);
            intent.putExtra("loseGame", loseGame);
            intent.putExtra("tablas", tablas);
            intent.putExtra("screenshotPath", screenshotFile.getAbsolutePath());

            // Iniciar la actividad GameOverActivity
            startActivity(intent);
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar errores aquí
        }

    }
    private boolean winCondition(int player) {
        // Verifica líneas horizontales
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == player && tablero[i][1] == player && tablero[i][2] == player) {
                return true;
            }
        }
        // Verifica líneas verticales
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] == player && tablero[1][j] == player && tablero[2][j] == player) {
                return true;
            }
        }
        // Verifica diagonales
        if (tablero[0][0] == player && tablero[1][1] == player && tablero[2][2] == player) {
            return true;
        }

        if (tablero[0][2] == player && tablero[1][1] == player && tablero[2][0] == player) {
            return true;
        }
        return false;
    }
    private boolean drawCondition(){
        int contador=0;
        //Recorremos el array bidimensional para recorrer el tablero en buscas de casillas libres
        for (int i =0;i<tablero.length;i++){
            for (int j = 0;j<tablero[i].length;j++){
                if (tablero[i][j]==0){
                    //Si hay alguna casilla libre sumamos el contador
                    contador++;
                }
            }
        }
        //Si no existe casilla libre y el player 1 y el player 2 no han ganado devuelve true
        if (contador==0 && !loseGame && !winGame){
            return true;
        }
        return false;
    }

    public void clickCelda(View view) {
        String tag = (String) view.getTag();


        if ("c00".equals(tag)) {
            if (tablero[0][0] != player2) { //Si en la posición no esta el player 2
                tablero[0][0] = player1; //Pintar el player 1
                pintarTablero(); //Actualizamos el tablero
                winGame = winCondition(player1); //Verificamos si hemos ganado
                if (!winGame) { // Si no hemos ganado
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                     gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                   gameOver();
                }
            }
           tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
            gameOver();
            }
        }
        if ("c01".equals(tag)) {
            if (tablero[0][1] != player2) {
                tablero[0][1] = player1;
                pintarTablero();
                winGame = winCondition(player1);
                if (!winGame) {
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                        gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                   gameOver();
                }
            }

            tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
             gameOver();

            }
        }
        if ("c02".equals(tag)) {
            if (tablero[0][2] != player2) {
                tablero[0][2] = player1;
                pintarTablero();
                winGame = winCondition(player1);
                if (!winGame) {
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                       gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                  gameOver();
                }
            }

            tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
              gameOver();
            }
        }
        if ("c10".equals(tag)) {
            if (tablero[1][0] != player2) {
                tablero[1][0] = player1;
                pintarTablero();
                winGame = winCondition(player1);
                if (!winGame) {
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                       gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                   gameOver();
                }
            }

            tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
              gameOver();
            }
        }
        if ("c11".equals(tag)) {
            if (tablero[1][1] != player2) {
                tablero[1][1] = player1;
                pintarTablero();
                winGame = winCondition(player1);
                if (!winGame) {
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                        gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                   gameOver();
                }

            }
            tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
               gameOver();
            }
        }
        if ("c12".equals(tag)) {
            if (tablero[1][2] != player2) {
                tablero[1][2] = player1;
                pintarTablero();
                winGame = winCondition(player1);
                if (!winGame) {
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                      gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                   gameOver();
                }
            }

            tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
                gameOver();
            }
        }
        if ("c20".equals(tag)) {
            if (tablero[2][0] != player2) {
                tablero[2][0] = player1;
                pintarTablero();
                winGame = winCondition(player1);
                if (!winGame) {
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                        gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                    gameOver();
                }
            }

            tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
               gameOver();
            }
        }
        if ("c21".equals(tag)) {
            if (tablero[2][1] != player2) {
                tablero[2][1] = player1;
                pintarTablero();
                winGame = winCondition(player1);
                if (!winGame) {
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                        gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                   gameOver();
                }
            }

            tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
               gameOver();
            }
        }
        if ("c22".equals(tag)) {
            if (tablero[2][2] != player2) {
                tablero[2][2] = player1;
                pintarTablero();
                winGame = winCondition(player1);
                if (!winGame) {
                    turnoIA();
                    pintarTablero();
                    loseGame = winCondition(player2);
                    if (loseGame) {
                        Toast.makeText(this, "HAS PERIDOD", Toast.LENGTH_SHORT).show();
                        gameOver();
                    }
                } else {
                    Toast.makeText(this, "HAS GANADO", Toast.LENGTH_SHORT).show();
                    gameOver();
                }
            }

            tablas =  drawCondition();
            if(tablas){
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
                gameOver();
            }
        }


    }
}