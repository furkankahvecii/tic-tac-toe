import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.awt.*;

public class TicTacToe extends Frame implements WindowListener,ActionListener 
{
	private static final long serialVersionUID = 1L;
	private int[][] gameMatrix = new int[3][3];
	private final Font MyFont = new Font(Font.DIALOG, Font.BOLD, 100);
    public List<Button> buttonList = new ArrayList<>();

    public TicTacToe(String title) 
    {
    	super(title);
        setLayout(null);
        addWindowListener(this);
        createButtons(buttonList);
    }
   
    public static void main(String[] args) 
    {
    	startGame();
    }
    
    private static void startGame()
    {
    	TicTacToe myWindow = new TicTacToe("Tic Tac Toe");
    	myWindow.setResizable(false);
        myWindow.setSize(600,600);
        myWindow.setVisible(true);
    }
    
    private void createButtons(List<Button> buttonList)
    {
    	int x = 0,y = 0;

    	for(int i=1;i<=9;i++)
    	{
    		Button btn = new Button();
    		btn.setBounds(x,y,200,200);
    		add(btn);
    		btn.addActionListener(this);
    		x+=200;
    		if(i % 3 ==0) {
    			x=0;
    			y+=200;
    		}
    		buttonList.add(btn);
    	}
    }
    
    public void editButtons(Button button , String label , int i , int j , int decision)
    {
    	Point a = button.getLocation();
    	int buttonX = (int) (a.getX() / 200);
    	int buttonY = (int) (a.getY() / 200);
    	
    	button.setLabel(label);
    	button.setFont(MyFont);
    	gameMatrix[buttonY][buttonX] = decision;
    	button.setEnabled(false);
    }
    
    public void gameEnd(String plainText , String warningText)
    {
    	int rc = JOptionPane.showOptionDialog(null, plainText, warningText,
		        JOptionPane.INFORMATION_MESSAGE, 1, null, new String[] { "Yes", "No"},"");
    	dispose();
		if (rc==1) 
			dispose();
		else 
			startGame();
    }
    
    public void actionPerformed(ActionEvent e) 
    {
    	int size = getButtonCount();
    	editButtons((Button)e.getSource(), size % 2==0 ? "X":"O", 0, 0, size % 2==0 ? 1:2);
 
    	
    	if(checkTheWinner() == true) // we need at least 4 moves to be the winner of the game
    	{	
    		gameEnd(((Button)e.getSource()).getLabel()+" won.Do you want to play again?" , "Game Over!");
    	}
    	else if(getButtonCount() == 0)
    	{
    		gameEnd("DRAW ! Do you want to play again?" , "Game Over!");
    	}
    }
    
    public int getButtonCount()
   	{
    	int count = 0;
   		for(Button btn : buttonList)
   			count = btn.getLabel().equals("") ? count + 1 : count;

   		return count;
   	}
    
    public boolean checkTheWinner()
	{
		for(int i=0;i<gameMatrix.length;i++)
		{
			if(i==0)
			{
				for(int j=0;j<gameMatrix[i].length;j++)
				{
    				if(gameMatrix[i][j] == gameMatrix[i+1][j] && gameMatrix[i+1][j] == gameMatrix[i+2][j] && gameMatrix[i][j]!=0) return true; // vertical probability of winning
				}
				
    			if(gameMatrix[i][0] == gameMatrix[i+1][1] && gameMatrix[i+1][1] == gameMatrix[i+2][2] && gameMatrix[i][0]!=0) return true; // probability of cross winning
    			if(gameMatrix[i][2] == gameMatrix[i+1][1] && gameMatrix[i+1][1] == gameMatrix[i+2][0] && gameMatrix[i][2]!=0) return true; // probability of cross winning
			}
			
			if(gameMatrix[i][0] == gameMatrix[i][1] && gameMatrix[i][1] == gameMatrix[i][2] && gameMatrix[i][0]!=0) return true; // horizontal probability of winning
		}
		
		return false;
	}

    public void windowClosing(WindowEvent e) {
        dispose(); // https://stackoverflow.com/questions/13360430/jframe-dispose-vs-system-exit
        //System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
}
