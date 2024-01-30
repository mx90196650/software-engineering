package river;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Graphical interface for the River application
 * 
 * @author Gregory Kulczycki
 */
public class RiverGUI extends JPanel implements MouseListener {

    // ==========================================================
    // Fields (hotspots)
    // ==========================================================

    private final int leftBaseY = 275;
    private final int leftBaseX = 20;
    private final int rightBaseY = 275;
    private final int rightBaseX = 670;
    private final int itemWidth = 50;
    private final int itemHeight = 50;
    private final int leftBoatX = 140;
    private final int rightBoatX = 550;
    private final int boatWidth = 110;
    private final int boatHeight = 50;
    private final int boatY = 275;
    int[] dx = { 0, 60, 0, 60, 0, 60 };
    int[] dy = { 0, 0, -60, -60, -120, -120 };
    int passengerOffset = 0;
    private final Rectangle farmerButtonRect = new Rectangle(295, 120, 100, 30);
    private final Rectangle monsterButtonRect = new Rectangle(410, 120, 100, 30);

    private final Rectangle scoutButtonRect = new Rectangle(525, 120, 100, 30);

    // ==========================================================
    // Private Fields
    // ==========================================================

    private GameEngine engine; // Model
    private final GameEngine[] engines;
    private boolean restart = false;
    private final Map<Item, Rectangle> itemRectangleMap;
    private final Rectangle boatRectangle;

    // ==========================================================
    // Constructor
    // ==========================================================

    public RiverGUI() {

        engines = new GameEngine[]{ new FarmerGameEngine(), new MonsterGameEngine(), new ScoutGameEngine() };
        engine = engines[0];
        itemRectangleMap = new HashMap<>();
        for (Item item: engine.getItems()) {
            int index = item.ordinal();
            itemRectangleMap.put(item, new Rectangle(leftBaseX + dx[index], leftBaseY + dy[index], itemWidth, itemHeight));
        }
        boatRectangle = new Rectangle(leftBoatX, leftBaseY, boatWidth, boatHeight);

        addMouseListener(this);
    }

    // ==========================================================
    // Paint Methods (View)
    // ==========================================================

    Graphics g;
    @Override
    public void paintComponent(Graphics g) {

        this.g = g;
        refreshItemRectangles();
        refreshBoatRectangle();

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        paintObjects();
        String message = "";
        if (engine.gameIsLost()) {
            message = "You Lost!";
            restart = true;
        }
        if (engine.gameIsWon()) {
            message = "You Won!";
            restart = true;
        }
        paintMessage(message);
        if (restart) {
            paintButtons();
        }
    }

    public void refreshItemRectangles() {
        for (Item item : engine.getItems()) {
            Rectangle rectangle = itemRectangleMap.get(item);
            if (engine.getItemLocation(item).isAtStart()) {
                rectangle.x = leftBaseX + dx[item.ordinal()];
                rectangle.y = leftBaseY + dy[item.ordinal()];
            } else if (engine.getItemLocation(item).isAtFinish()) {
                rectangle.x = rightBaseX + dx[item.ordinal()];
                rectangle.y = rightBaseY + dy[item.ordinal()];
            } else {
                int boatX = engine.getBoatLocation().isAtStart() ? leftBoatX : rightBoatX;
                rectangle.x = boatX + passengerOffset;
                rectangle.y = boatY - 60;
                passengerOffset += 60;
            }
        }
        passengerOffset = 0;
    }

    public void refreshBoatRectangle() {
        if (engine.getBoatLocation().isAtStart()) {
            boatRectangle.x = leftBoatX;
        } else {
            boatRectangle.x = rightBoatX;
        }
    }

    public void paintObjects() {
        for (Item item : engine.getItems()) {
            Rectangle rectangle = itemRectangleMap.get(item);
            paintRectangle(rectangle, engine.getItemColor(item));
            paintStringInRectangle(engine.getItemLabel(item), rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        paintRectangle(boatRectangle, Color.ORANGE);
    }

    public void paintStringInRectangle(String str, int x, int y, int width, int height) {
        if (str == null || str.length() == 0) return;
        g.setColor(Color.BLACK);
        int fontSize = (height >= 40) ? 36 : 18;
        g.setFont(new Font("Verdana", Font.BOLD, fontSize));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = x + width / 2 - fm.stringWidth(str) / 2;
        int strYCoord = y + height / 2 + fontSize / 2 - 4;
        g.drawString(str, strXCoord, strYCoord);
    }

    public void paintMessage(String message) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = 400 - fm.stringWidth(message) / 2;
        int strYCoord = 100;
        g.drawString(message, strXCoord, strYCoord);
    }

    public void paintButtons() {
        g.setColor(Color.BLACK);
        paintBorder(farmerButtonRect, 3);
        paintBorder(monsterButtonRect, 3);
        paintBorder(scoutButtonRect, 3);
        paintRectangle(farmerButtonRect, Color.PINK);
        paintStringInRectangle("Farmer", farmerButtonRect.x, farmerButtonRect.y, farmerButtonRect.width,
                farmerButtonRect.height);
        paintRectangle(monsterButtonRect, Color.PINK);
        paintStringInRectangle("Monster",monsterButtonRect.x,monsterButtonRect.y,monsterButtonRect.width,
                monsterButtonRect.height);
        paintRectangle(scoutButtonRect, Color.PINK);
        paintStringInRectangle("Scout",scoutButtonRect.x,scoutButtonRect.y,scoutButtonRect.width,
                scoutButtonRect.height);
    }

    public void paintBorder(Rectangle r, int thickness) {
        g.fillRect(r.x - thickness, r.y - thickness, r.width + (2 * thickness), r.height + (2 * thickness));
    }

    public void paintRectangle(Rectangle r, Color color) {
        g.setColor(color);
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    // ==========================================================
    // Startup Methods
    // ==========================================================

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    private static void createAndShowGUI() {

        // Create and set up the window
        JFrame frame = new JFrame("RiverCrossing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        RiverGUI newContentPane = new RiverGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        // Display the window
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(RiverGUI::createAndShowGUI);
    }

    // ==========================================================
    // MouseListener Methods (Controller)
    // ==========================================================

    @Override
    public void mouseClicked(MouseEvent e) {

        if (restart) {
            if (this.farmerButtonRect.contains(e.getPoint())) {
                engine = engines[0];
            } else if (this.monsterButtonRect.contains(e.getPoint())) {
                engine = engines[1];
            } else if (this.scoutButtonRect.contains(e.getPoint())) {
                engine = engines[2];
            }
            else {
                return;
            }
            engine.resetGame();
            for (Item item: engine.getItems()) {
                int index = item.ordinal();
                itemRectangleMap.put(item, new Rectangle(leftBaseX + dx[index], leftBaseY + dy[index], itemWidth, itemHeight));
            }
            restart = false;
            repaint();
            return;
        }

        // click on item
        for (Item item : engine.getItems()) {
            Rectangle rectangle = itemRectangleMap.get(item);
            if (rectangle.contains(e.getPoint())) {
                if (engine.getItemLocation(item).isOnBoat()) {
                    engine.unloadBoat(item);
                    passengerOffset = (rectangle.x == leftBoatX || rectangle.x == rightBoatX) ? 60 : 0;
                } else {
                    engine.loadBoat(item);
                }
            }
        }

        //click on boat
        if (boatRectangle.contains(e.getPoint())) {
            engine.rowBoat();
        }
        repaint();
    }

    // ----------------------------------------------------------
    // None of these methods will be used
    // ----------------------------------------------------------

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
