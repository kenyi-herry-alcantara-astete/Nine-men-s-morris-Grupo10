import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel extends JFrame{

    private JButton jugarButton;
    private JPanel firstPanel;
    private JTextField TextField;

    public FirstPanel() {
        //Caracteristicas de la ventana que se va abrir
        setSize(300,300);
        setContentPane(firstPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250,100,720,520);

       //funcion del button
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainFrame obj = new MainFrame(); //Se crea el objeto de la ventana donde se va jugar para que se pueda mostrar dicha ventana
                dispose();//con este comando se cierra la ventana
            }
        });
        setVisible(true);//mostrar la venta FirstPanel
    }

    public JPanel getFirstPanel(){
        return  firstPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
