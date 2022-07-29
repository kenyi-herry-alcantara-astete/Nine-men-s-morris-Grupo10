package sprint3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel extends JFrame {


    private JButton jugarButton;
    private JPanel firstPanel;
    private JTextField Nombre1;
    private JTextField Nombre2;
    public  JCheckBox jugador2CheckBox ;
    public  JCheckBox jugador1CheckBox1;
    private JLabel LabelNamePlayer1;
    private JLabel LabelNamePlayer2;
    private JPanel ifOtherPersonPanel;
    private JButton siguienteButtonPeoples;
    private JButton siguienteButtonComputer;
    private JButton BtnOtraPersonaButton;
    private JButton BtnComputadoraButton;
    private JPanel withWhoPanel;
    private JPanel inComputerPanel;
    private JPanel turnPanel;
    private JTextField computerOppnentName;
    private JPanel selectDifficultyPanel;
    private JButton basicButton;
    private JButton estandarButton;
    private JButton expertButton;

    // "otherPerson or computer","namePlayer1","namePlayer2,"turn","difficulty"
    public String[] configuration = {"","","","",""};

    public FirstPanel() {
        //Caracteristicas de la ventana que se va abrir

        setContentPane(firstPanel);
        //setSize(600,4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250,100,720,520);
        setBounds(250,100,820,620);
        setVisible(true);

        ifOtherPersonPanel.setVisible(false);
        inComputerPanel.setVisible(false);
        selectDifficultyPanel.setVisible(false);
        turnPanel.setVisible(false);



        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                MainFrame obj = new MainFrame();
                obj.setVisible(true);

                obj.player1.name = configuration[1];
                obj.player2.name = configuration[2];
                obj.player1.type = "left";
                obj.player2.type = "right";

                if (configuration[3].equals("player1")){
                    obj.player1.turn = "uno";
                    obj.player2.turn = "dos";
                }

                if (configuration[3].equals("player2")){
                    obj.player2.turn = "uno";
                    obj.player1.turn = "dos";
                }

                if (configuration[0].equals("withTheComputer")){
                    obj.isPlayerAComputer = true;
                    obj.player2.name = "computadora";
                    obj.modeDifficulty = configuration[4];
                }
                if (configuration[0].equals("withOtherPerson")){
                    obj.isPlayerAComputer = false;
                }
                obj.setNameInTheUI();
                obj.showTurnInUI();
                System.out.println(configuration[0]+configuration[1]+configuration[2]+configuration[3]+configuration[4]);

                dispose();

                obj.firstArctionWheTheComputerIsFirst();

            }
        });

        BtnOtraPersonaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration[0] = "withOtherPerson";
                withWhoPanel.setVisible(false);
                ifOtherPersonPanel.setVisible(true);
            }
        });
        BtnComputadoraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration[0] = "withTheComputer";
                withWhoPanel.setVisible(false);
                inComputerPanel.setVisible(true);
            }
        });
        siguienteButtonPeoples.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                configuration[1] = Nombre1.getText();
                configuration[2] = Nombre2.getText();
                ifOtherPersonPanel.setVisible(false);
                turnPanel.setVisible(true);
            }
        });
        siguienteButtonComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration[1] = computerOppnentName.getText();
                configuration[2] = "Computer";
                inComputerPanel.setVisible(false);
                selectDifficultyPanel.setVisible(true);
            }
        });
        jugador1CheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration[3] = "player1";
            }
        });
        jugador2CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration[3] = "player2";
            }
        });
        basicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration[4] = "basic";
                selectDifficultyPanel.setVisible(false);
                turnPanel.setVisible(true);
            }
        });
        estandarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration[4] = "estandar";
                selectDifficultyPanel.setVisible(false);
                turnPanel.setVisible(true);
            }
        });
        expertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration[4] = "expert";
                selectDifficultyPanel.setVisible(false);
                turnPanel.setVisible(true);
            }
        });
    }
    public String getNamePlayer(int n){
        if(n==1) {
           return Nombre1.getText();
        }
        if(n==2) {
           return Nombre2.getText();
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
