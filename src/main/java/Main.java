import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static JFrame frame;
    static MainFrame myNineFrame;

    //Start Game
    public static void main(String[] args) {
        frame = new JFrame("Nines men's morris");
        myNineFrame = new MainFrame();
        addMenuBar(frame);
        frame.setContentPane(myNineFrame.getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(250,100,820,620);
        frame.setVisible(true);
    }

    private static void addMenuBar(JFrame frame){
        // Barra de Menú
        JMenuBar menuBar = new JMenuBar();

        // Menú
        JMenu menu = new JMenu("Menu");
        JMenu help = new JMenu("Ayuda");

        // Items del menú "Menu"
        JMenuItem playAgain = new JMenuItem("Jugar de Nuevo");
        JMenuItem showResults = new JMenuItem("Mostrar Resultados");
        JMenuItem quit = new JMenuItem("Salir");

        // Item del menú "ayuda"
        JMenuItem tutorial = new JMenuItem("Tutorial");

        // Iconos del menú "Menu"
        ImageIcon imgPlayAgain = new ImageIcon("src/main/resources/Image/imgPlayAgain.png");
        ImageIcon imgShowResults = new ImageIcon("src/main/resources/Image/imgShowResults.png");
        ImageIcon imgQuit = new ImageIcon("src/main/resources/Image/imgQuit.png");
        playAgain.setIcon(imgPlayAgain);
        showResults.setIcon(imgShowResults);
        quit.setIcon(imgQuit);

        // Iconos del menu "Ayuda"
        ImageIcon imgTutorial = new ImageIcon("src/main/resources/Image/imgTutorial.png");
        tutorial.setIcon(imgTutorial);

        // Evento para reinciar el juego
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myNineFrame.restart();
            }
        });

        // Evento para salir del juego
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Eventos para ver tutorial
        tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.dma.fi.upm.es/recursos/aplicaciones/matematicas_recreativas/web/nine_mens_morris/reglas.html"));
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Agragando items al menú "Ayuda"
        help.add(tutorial);

        // Agregando items al menu "Menú"
        menu.add(playAgain);
        menu.add(showResults);
        menu.add(quit);

        // Agregando menús(menu y ayuda) a la barra de menú
        menuBar.add(menu);
        menuBar.add(help);

        // Agregando la barra de menú a la ventana
        frame.setJMenuBar(menuBar);
    }

}
