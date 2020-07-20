package com.example.lionortigor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    enum Players{
        ONE,TWO,NO
    }
    Players currentPlayer = Players.ONE;
    Players[] playerChoices =new Players[9];
    GridLayout gridLayout;

    int[] a = {0,1,2};
    int[] b = {3,4,5};
    int[] c = {6,7,8};
    int[] d = {0,3,6};
    int[] e = {1,4,7};
    int[] f = {2,5,8};
    int[] g = {0,4,8};
    int[] h = {2,4,6};
    int[][] arrayOfPlayers = {a,b,c,d,e,f,g,h};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoices[0] = Players.NO;
        playerChoices[1] = Players.NO;
        playerChoices[2] = Players.NO;
        playerChoices[3] = Players.NO;
        playerChoices[4] = Players.NO;
        playerChoices[5] = Players.NO;
        playerChoices[6] = Players.NO;
        playerChoices[7] = Players.NO;
        playerChoices[8] = Players.NO;
        gridLayout = findViewById(R.id.gridLayout);



    }
    public void clickedBtn(View imageView){
        ImageView tappedBtn =(ImageView) imageView;
        tappedBtn.setTranslationX(-2000);
        tappedBtn.animate().alpha(1);
        int tiTag = Integer.parseInt(tappedBtn.getTag().toString());


        playerChoices[tiTag] = currentPlayer;


        if(currentPlayer == Players.ONE){
            tappedBtn.setImageResource(R.drawable.lion);
            currentPlayer = Players.TWO;
        }else if(currentPlayer == Players.TWO){
            tappedBtn.setImageResource(R.drawable.tiger);
            currentPlayer = Players.ONE;
        }
        tappedBtn.animate().translationXBy(2000).rotation(3600).setDuration(1500);
        if(playerChoices[0] != Players.NO &&
                playerChoices[1] != Players.NO &&
                playerChoices[2] != Players.NO &&
                playerChoices[3] != Players.NO &&
                playerChoices[4] != Players.NO &&
                playerChoices[5] != Players.NO &&
                playerChoices[6] != Players.NO &&
                playerChoices[7] != Players.NO &&
                playerChoices[8] != Players.NO){

            alert(Players.NO);
            return;

        }

        for(int[] winner : arrayOfPlayers){
            if(playerChoices[winner[0]] == playerChoices[winner[1]]
                    && playerChoices[winner[1]] == playerChoices[winner[2]] && playerChoices[winner[0]] != Players.NO){
                //Toast.makeText(this,"PLAYER "+playerChoices[winner[0]]+" IS THE WINNER",Toast.LENGTH_LONG).show();
                alert(playerChoices[winner[0]]);
            }

        }

    }
    public void alert(Players ab){
        AlertDialog.Builder alertB = new AlertDialog.Builder(this);
        alertB.setTitle("Congratulations");
        if(ab.equals(Players.NO)){
            alertB.setMessage("Draw");
        }else{
            alertB.setMessage("PLAYER "+ab+" IS THE WINNER");
        }
        alertB.setCancelable(false);
        alertB.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                playerChoices[0] = Players.NO;
                playerChoices[1] = Players.NO;
                playerChoices[2] = Players.NO;
                playerChoices[3] = Players.NO;
                playerChoices[4] = Players.NO;
                playerChoices[5] = Players.NO;
                playerChoices[6] = Players.NO;
                playerChoices[7] = Players.NO;
                playerChoices[8] = Players.NO;
                currentPlayer = Players.ONE;
                for(int index =0;index<gridLayout.getChildCount();index++){
                    ImageView ax =(ImageView) gridLayout.getChildAt(index);
                    ax.setImageDrawable(null);
                    ax.animate().alpha(0.2f).rotation(360).setDuration(1000);
                    dialogInterface.dismiss();
                  //  Toast.makeText(MainActivity.this,"forLoop",Toast.LENGTH_SHORT).show();
                }


            }
        });
        alertB.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();

            }
        });
        alertB.create().show();
    }
}