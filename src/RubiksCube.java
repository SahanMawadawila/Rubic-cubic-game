import java.util.Random;
import java.util.Scanner;
public class RubiksCube {
    private CubicPiece[][][] cube;

    public RubiksCube() {
        cube= new CubicPiece[6][3][3];
        /*
        0 plane is the back plane
        1 plane is the Left plane
        2 plane is the bottom plane
        3 plane is the right plane
        4 plane is the top plane
        5 plane is the front plane
        * */
        cube[0] = new CubicPiece[][] {
                { new CubicPiece('R'), new CubicPiece('R'), new CubicPiece('R') },
                { new CubicPiece('R'), new CubicPiece('R'), new CubicPiece('R') },
                { new CubicPiece('R'), new CubicPiece('R'), new CubicPiece('R') }
        };
        cube[1] = new CubicPiece[][] {
                { new CubicPiece('Y'), new CubicPiece('Y'), new CubicPiece('Y') },
                { new CubicPiece('Y'), new CubicPiece('Y'), new CubicPiece('Y') },
                { new CubicPiece('Y'), new CubicPiece('Y'), new CubicPiece('Y') }
        };
        cube[2] = new CubicPiece[][] {
                { new CubicPiece('G'), new CubicPiece('G'), new CubicPiece('G') },
                { new CubicPiece('G'), new CubicPiece('G'), new CubicPiece('G') },
                { new CubicPiece('G'), new CubicPiece('G'), new CubicPiece('G') }
        };

        cube[3] = new CubicPiece[][] {
                { new CubicPiece('B'), new CubicPiece('B'), new CubicPiece('B') },
                { new CubicPiece('B'), new CubicPiece('B'), new CubicPiece('B') },
                { new CubicPiece('B'), new CubicPiece('B'), new CubicPiece('B') }
        };

        cube[4] = new CubicPiece[][] {
                { new CubicPiece('W'), new CubicPiece('W'), new CubicPiece('W') },
                { new CubicPiece('W'), new CubicPiece('W'), new CubicPiece('W') },
                { new CubicPiece('W'), new CubicPiece('W'), new CubicPiece('W') }
        };
        cube[5] = new CubicPiece[][] {
                { new CubicPiece('O'), new CubicPiece('O'), new CubicPiece('O') },
                { new CubicPiece('O'), new CubicPiece('O'), new CubicPiece('O') },
                { new CubicPiece('O'), new CubicPiece('O'), new CubicPiece('O') }
        };
    }

    //this function is used to display the cube side by side
    public void display(){
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.print("Back plane  |  Left plane  |  Bottom plane |   Right plane   |   Top plane   |   Front plane\n");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <=5; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(cube[j][i][k].getColor() + " ");
                }
                System.out.print("           ");
            }
            System.out.println();
            System.out.println();
        }

    }
    //this function is used to shuffle the cube
    public void shuffle(){
        //generating random number between 30 and 50
        Random random = new Random();
        int randomNumber = random.nextInt(21) + 30;
        for(int i=0;i<randomNumber;i++){
            //generating random number between 0 and 11
            int randomNum = random.nextInt(12);
            if(randomNum==0){
                rotateLayer("U+");
            }
            else if(randomNum==1){
                rotateLayer("U-");
            }
            else if(randomNum==2){
                rotateLayer("L+");
            }
            else if(randomNum==3){
                rotateLayer("L-");
            }
            else if(randomNum==4){
                rotateLayer("R+");
            }
            else if(randomNum==5){
                rotateLayer("R-");
            }
            else if(randomNum==6){
                rotateLayer("B+");
            }
            else if(randomNum==7){
                rotateLayer("B-");
            }
            else if(randomNum==8){
                rotateLayer("F+");
            }
            else if(randomNum==9){
                rotateLayer("F-");
            }
            else if(randomNum==10){
                rotateLayer("D+");
            }
            else if(randomNum==11){
                rotateLayer("D-");
            }
        }
    }


    //this function is used to rotate the plan clockwise
    public void rotatePlanClockWise(int plane){
        char temp;
        char temp2;
        temp = cube[plane][0][1].getColor();
        temp2 = cube[plane][1][2].getColor();
        cube[plane][0][1].setColor(cube[plane][1][0].getColor());
        cube[plane][1][2].setColor(temp);
        temp = cube[plane][2][1].getColor();
        cube[plane][2][1].setColor(temp2);
        cube[plane][1][0].setColor(temp);

        temp = cube[plane][0][0].getColor();
        cube[plane][0][0].setColor(cube[plane][2][0].getColor());
        temp2= cube[plane][0][2].getColor();
        cube[plane][0][2].setColor(temp);
        temp = cube[plane][2][2].getColor();
        cube[plane][2][2].setColor(temp2);
        cube[plane][2][0].setColor(temp);
    }

    //this function is used to rotate the plan anti-clockwise
    public void rotatePlanAntiClockWise(int plane){
        char temp;
        char temp2;
        temp = cube[plane][0][1].getColor();
        temp2 = cube[plane][1][0].getColor();
        cube[plane][0][1].setColor(cube[plane][1][2].getColor());
        cube[plane][1][0].setColor(temp);
        temp = cube[plane][2][1].getColor();
        cube[plane][2][1].setColor(temp2);
        cube[plane][1][2].setColor(temp);

        temp = cube[plane][0][0].getColor();
        cube[plane][0][0].setColor(cube[plane][0][2].getColor());
        temp2= cube[plane][2][0].getColor();
        cube[plane][2][0].setColor(temp);
        temp = cube[plane][2][2].getColor();
        cube[plane][2][2].setColor(temp2);
        cube[plane][0][2].setColor(temp);
    }

   //this function is used to rotate the layer
    public void rotateLayer(String command){

        //temporarily storing the rows to exchange when rotating
        CubicPiece[] tempRow = new CubicPiece[3];
        CubicPiece[] tempRow2 = new CubicPiece[3];
        CubicPiece[] tempRow3 = new CubicPiece[3];


        //temporarily storing the columns to exchange when rotating
        CubicPiece[] tempColumn = new CubicPiece[3];
        CubicPiece[] tempColumn2 = new CubicPiece[3];
        CubicPiece[] tempColumn3 = new CubicPiece[3];
        CubicPiece[] tempColumn4 = new CubicPiece[3];


        //rotation of top plane clockwise and rotation of bottom plane aniticlockwise can be done by the same function
        if(command.equals("U+") || command.equals("B-")){
            //selecting top plane or the botttom plane
            int row;
            int plane;
            if(command.equals("U+")){
                row=0;
                plane=4;
            }
            else{
                row=2;
                plane=2;
            }
            //changing other plans except top and bottom
            tempRow = cube[1][row];
            cube[1][row] = cube[5][row];
            tempRow2=cube[0][row];
            cube[0][row] = tempRow;
            tempRow3 = cube[3][row];
            cube[3][row] = tempRow2;
            cube[5][row] = tempRow3;

            //exchanging top plane or bottom plane
            if(command.equals("U+")){
                rotatePlanClockWise(plane);
            }
            else {
                rotatePlanAntiClockWise(plane);
            }


        }
        else if(command.equals("U-") || command.equals("B+")) {
            //selecting top plane or the botttom plane
            int row;
            int plane;
            if(command.equals("U-")){
                row=0;
                plane=4;
            }
            else{
                row=2;
                plane=2;
            }
            //changing other plans except top and bottom
            tempRow = cube[1][row];
            cube[1][row] = cube[0][row];
            tempRow2 = cube[5][row];
            cube[5][row] = tempRow;
            tempRow3 = cube[3][row];
            cube[3][row] = tempRow2;
            cube[0][row] = tempRow3;

            //exchanging top plane
            if(command.equals("U-")){
                rotatePlanAntiClockWise(plane);
            }
            else {
                rotatePlanClockWise(plane);
            }

        } else if (command.equals("R-") || command.equals("L+")) {
            //selecting right plane or the left plane
            int column;
            int plane;
            if(command.equals("L+")){
                column=0;
                plane=1;
            }
            else{
                column=2;
                plane=3;
            }
            //changing other plans except right and left
            for(int i=0;i<3;i++){
                tempColumn[i]=cube[0][i][column];
                tempColumn2[i]=cube[4][i][column];
                tempColumn3[i]=cube[5][i][column];
                tempColumn4[i]=cube[2][i][column];
            }
            for(int i=0;i<3;i++){
                cube[0][i][column]=tempColumn4[i];
                cube[4][i][column]=tempColumn[i];
                cube[5][i][column]=tempColumn2[i];
                cube[2][i][column]=tempColumn3[i];
            }

            //exchanging right plane or left plane
            if(command.equals("L+")){
                rotatePlanClockWise(plane);
            }
            else {
                rotatePlanAntiClockWise(plane);
            }
        }
        else if (command.equals("R+") || command.equals("L-")) {
            //selecting right plane or the left plane
            int column;
            int plane;
            if(command.equals("L-")){
                column=0;
                plane=1;
            }
            else{
                column=2;
                plane=3;
            }
            //changing other plans except right and left
            for(int i=0;i<3;i++){
                tempColumn[i]=cube[0][i][column];
                tempColumn2[i]=cube[4][i][column];
                tempColumn3[i]=cube[5][i][column];
                tempColumn4[i]=cube[2][i][column];
            }
            for(int i=0;i<3;i++){
                cube[0][i][column]=tempColumn2[i];
                cube[4][i][column]=tempColumn3[i];
                cube[5][i][column]=tempColumn4[i];
                cube[2][i][column]=tempColumn[i];
            }

            //exchanging right or left plane
            if (command.equals("L-")) {
                rotatePlanAntiClockWise(plane);
            }
            else {
                rotatePlanClockWise(plane);
            }
        }
        else if (command.equals("F-")) { //changing front plane
            for(int i=0;i<3;i++){
                tempColumn[i]= cube[1][i][2];
                tempColumn2[i]= cube[3][i][0];
            }
            tempRow=cube[4][2];
            tempRow2=cube[2][2];
            for(int i=0;i<3;i++){
                cube[1][i][2]=tempRow[i];
                cube[3][i][0]=tempRow2[i];
            }
            cube[4][2]=tempColumn2;
            cube[2][2]=tempColumn;

            int plane=5;
            //exchanging top plane
            rotatePlanAntiClockWise(plane);
        }
        else if (command.equals("F+")) {
            for(int i=0;i<3;i++){
                tempColumn[i]= cube[1][i][2];
                tempColumn2[i]= cube[3][i][0];
            }
            tempRow=cube[4][2];
            tempRow2=cube[2][2];
            for(int i=0;i<3;i++){
                cube[1][i][2]=tempRow2[i];
                cube[3][i][0]=tempRow[i];
            }
            cube[4][2]=tempColumn;
            cube[2][2]=tempColumn2;

            int plane=5;
            //exchanging top plane
            rotatePlanClockWise(plane);

        }

    }

    //check if the cube is solved
    public boolean isSolved() {
        for (int i = 0; i < 6; i++) {
            char c = cube[i][0][0].getColor();

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (cube[i][j][k].getColor() != c) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}
