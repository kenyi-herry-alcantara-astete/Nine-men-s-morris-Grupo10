import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowShowResults extends JDialog {
    private JPanel contentPane;
    private JTable table;
    private JButton btnReturn;

    private String player1;
    private String player2;

    private int victories1;
    private int victories2;

    public WindowShowResults(JFrame parent, boolean modal, String player1, String player2, int victories1, int victories2 ) {
        super(parent, modal);
        this.player1 = player1;
        this.player2 = player2;
        this.victories1 = victories1;
        this.victories2 = victories2;
        initTable();
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void initTable(){

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("victorias " + player1);
        model.addColumn("victorias " + player2);
        String[] victories = {String.valueOf(victories1), String.valueOf(victories2)};
        model.addRow(victories);
//        table.setSize(250, 150);
        table.setModel(model);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
    }
}
/*
    String[] columnNames = {"First Name", "Last Name"};
    String[][] data = {{"Kathy", "Smith"}};
        table = new JTable(data, columnNames);*/