import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel extends JFrame {

    private JButton jugarButton;
    private JPanel firstPanel;
    private JTextField textNombre;
    private JTextField miNombre;
    public  JCheckBox jugador2CheckBox ;
    public  JCheckBox jugador1CheckBox1;
    private JLabel LabelNamePlayer1;
    private JLabel LabelNamePlayer2;

    public FirstPanel() {
        //Caracteristicas de la ventana que se va abrir

        setContentPane(firstPanel);
        //setSize(600,4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250,100,720,520);
        setBounds(250,100,820,620);
        setVisible(true);



        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre1 , nombre2;
                nombre1 = getNamePlayer(1); //textNombre.getText();
                nombre2 = getNamePlayer(2);//miNombre.getText();
                MainFrame obj = new MainFrame();
                //obj.namePlayerLeft.setText(nombre1);
                //obj.namePlayerRight.setText(nombre2);
                dispose();


            }
        });

    }
    public String getNamePlayer(int n){
        if(n==1) {
           return textNombre.getText();
        }
        if(n==2) {
           return miNombre.getText();
        }
            return " ";
    }


    public JPanel getFirstPanel(){
        return  firstPanel;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
