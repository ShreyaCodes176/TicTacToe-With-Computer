import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeAI extends JFrame{

    private RoundedButton[] cells=new RoundedButton[9];
    private char[] board=new char[9];
    private JLabel statusLabel;
    private boolean gameOver=false;

    public TicTacToeAI(){
        setTitle("Tic Tac Toe");
        setSize(420,520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new GradientPanel());
        setLayout(null);

        initialUI();//sets the base board
        setVisible(true);
    }

    private void initialUI(){

        JLabel title=new JLabel("LETS PLAY TIC-TAC-TOE!!",SwingConstants.CENTER);
        title.setBounds(40,20,340,30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Monospaced",Font.BOLD,18));
        add(title);
        
        //headings
        JLabel info=new JLabel("YOU ARE 'O'   I AM 'X'",SwingConstants.CENTER);
        info.setBounds(40,55,340,20);
        info.setForeground(Color.WHITE);
        info.setFont(new Font("Monospaced",Font.PLAIN,14));
        add(info);
        
        //winner status
        statusLabel=new JLabel("",SwingConstants.CENTER);
        statusLabel.setBounds(40,85,340,20);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Monospaced",Font.BOLD,14));
        add(statusLabel);

        //restart button
        JButton restart=new JButton(".");
        restart.setBounds(360,10,40,40);
        restart.setFont(new Font("Arial",Font.BOLD,18));
        restart.setFocusPainted(false);
        restart.addActionListener(e -> resetGame());
        add(restart);

        int x=60,y=120,idx=0;

        for (int r=0; r < 3; r++){
            for (int c=0; c < 3; c++){
                cells[idx]=new RoundedButton(idx);
                cells[idx].setBounds(x + c * 100,y + r * 100,90,90);
                add(cells[idx]);
                board[idx]=' ';
                idx++;
            }
        }

        //footer info
        JLabel footer=new JLabel("-made by shreya",SwingConstants.RIGHT);
        footer.setBounds(200,430,180,30);
        footer.setForeground(Color.WHITE);
        footer.setFont(new Font("Monospaced",Font.PLAIN,12));
        add(footer);
    }

    private void makeMove(int i,char p){
        board[i]=p;
        cells[i].setText(String.valueOf(p));
    }

    private boolean checkGameEnd(){
        if (checkWinner('O')){
            statusLabel.setText("YOU WIN!!");
            gameOver=true;
            return true;
        }
        if (checkWinner('X')){
            statusLabel.setText("I WIN!!");
            gameOver=true;
            return true;
        }
        if (isBoardFull()){
            statusLabel.setText("IT'S A TIE!!");
            gameOver=true;
            return true;
        }
        return false;
    }

    private void resetGame(){
        for (int i=0; i < 9; i++){
            board[i]=' ';
            cells[i].setText("");
        }
        statusLabel.setText("");
        gameOver=false;
    }

    //AI
    private int bestMove(){
        int bestScore=Integer.MIN_VALUE,move=0;
        for (int i=0; i < 9; i++){
            if (board[i] == ' '){
                board[i]='X';
                int score=minMax(false);
                board[i]=' ';
                if (score > bestScore){
                    bestScore=score;
                    move=i;
                }
            }
        }
        return move;
    }

    private int minMax(boolean isMax){
        if (checkWinner('X')) return 10;
        if (checkWinner('O')) return -10;
        if (isBoardFull()) return 0;

        int best=isMax?Integer.MIN_VALUE:Integer.MAX_VALUE;

        for (int i=0; i < 9; i++){
            if (board[i]==' '){
                board[i]=isMax ? 'X' : 'O';
                best=isMax?Math.max(best,minMax(false)):Math.min(best,minMax(true));
                board[i]=' ';
            }
        }
        return best;
    }

    private boolean checkWinner(char p){
        int[][] win={
               {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
        };//winning situations
        for (int[] w:win)
            if (board[w[0]]==p && board[w[1]]==p && board[w[2]]==p)
                return true;
        return false;
    }

    private boolean isBoardFull(){
        for (char c:board)
            if (c==' ') return false;
        return true;
    }

    class RoundedButton extends JButton{
        int index;

        RoundedButton(int idx){
            index=idx;
            setFont(new Font("Arial",Font.BOLD,48));
            setForeground(new Color(100,220,220));
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);

            addActionListener(e ->{
                if (!gameOver && board[index] == ' '){
                    makeMove(index,'O');
                    if (checkGameEnd()) 
                        return;
                int aiMove=bestMove();
                makeMove(aiMove,'X');
                checkGameEnd();
                }}
                );
        }

        protected void paintComponent(Graphics g){
            Graphics2D g2=(Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(120,50,130));
            g2.fillRoundRect(0,0,getWidth(),getHeight(),25,25);

            g2.setColor(new Color(100,220,220));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1,1,getWidth()-2,getHeight()-2,25,25);

            super.paintComponent(g);
        }
    }

    class GradientPanel extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2=(Graphics2D) g;
            GradientPaint gp=new GradientPaint(0,0,new Color(5,20,50),0,getHeight(),new Color(20,120,130));
            g2.setPaint(gp);
            g2.fillRect(0,0,getWidth(),getHeight());
        }
    }

    public static void main(String[] args){
        new TicTacToeAI();
    }
}
