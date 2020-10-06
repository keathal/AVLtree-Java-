package com.company;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TreeGUI extends JFrame {
    private JPanel contentPane;
    public Tree node;
    public DrawTree drawer;

    /**
     * Create the frame.
     */
    public TreeGUI(Tree node) {
        super("Binary Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(1, 2,0,0));
        drawer = new DrawTree(node);
        contentPane.add(drawer);
        setContentPane(contentPane);
        this.node = node;
        setVisible(true);
//
//        JPanel grid = new JPanel(new GridLayout(3,2,5,5));
//        JButton b_balance = new JButton("Balance");
//        b_balance.addActionListener(new balanceAction());
//        JButton b_add = new JButton("Add");
//        JButton b_delete = new JButton("Delete");
//        JTextField t_add = new JFormattedTextField();
//        JTextField t_delete = new JFormattedTextField();
//        grid.add(t_delete);
//        grid.add(b_delete);
//        grid.add(t_add);
//        grid.add(b_add);
//        grid.add(b_balance);
//        contentPane.add(grid);
    }

    class balanceAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawer = new DrawTree(node.balance(node));
            contentPane.add(drawer, 0);
            contentPane.setComponentZOrder(drawer, 0);
            //node= node.balance(node);
        }
    }
}



class DrawTree extends JPanel {

    public Tree node;
    public static ArrayList listX = new ArrayList();
    public static ArrayList listY = new ArrayList();


    public DrawTree(Tree node) {
        this.node = node;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        g.setFont(new Font("Tahoma", Font.BOLD, 18));
        DrawTree(g, 0, getWidth(), 0, getHeight() / node.getHeight(node), node);
        listX.clear();
        listY.clear();
    }

    public Point DrawTree(Graphics g, int StartWidth, int EndWidth, int StartHeight, int Level, Tree node)
    {
        String data = String.valueOf(node.getData());
        g.setFont(new Font("Tahoma", Font.BOLD, 18));
        FontMetrics fm = g.getFontMetrics();
        int dataWidth = fm.stringWidth(data);

        // Calculate position to draw text string
        Point textPos = new Point((StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);
        g.drawString(data, textPos.x, textPos.y);

        if (node.getLeft() != null) {
            Point child1 = DrawTree(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.getLeft());
            // Draw line from this node to child node
            drawLine(g, textPos, child1);
        }
        if (node.getRight() != null) {
            Point child2 = DrawTree(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.getRight());
            // Draw line from this node to child node
            drawLine(g, textPos, child2);
        }
        // Return position for parent to use
        return textPos;
    }

    public void drawLine(Graphics g, Point p1, Point p2)
    {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}