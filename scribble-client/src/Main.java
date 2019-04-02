import gui.LoginScreen;

        import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jframe = new JFrame("Scribble");
        jframe.setContentPane(new LoginScreen().getPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
    }
}
