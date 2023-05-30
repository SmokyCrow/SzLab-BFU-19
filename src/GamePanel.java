import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Game game;
    private Dimension size;
    private BufferedImage[] images;
    private Player selectedPlayer;
    private Element selectedElement;
    private PassiveElement inPipe, outPipe;
    DefaultListModel<Element> neighbours;

    public boolean updateNeeded = false;

    JButton bMove, bStick, bSlip, bPipeTamper, bPumpTamper, bSetIn, bSetOut, bRepair, bLeak;
    JList<Player> playerList;
    JList<Element> elementList;

    public GamePanel(Game game) {
        this.game = game;
        initImages();
        setPanelSize();
        this.setLayout(null);
        initButtons();
        initPlayerList();
        initElementList();
    }

    private void initPlayerList(){
        DefaultListModel<Player> players = new DefaultListModel<>();
        for (int i = 0; i < game.getPlayers().size(); i++){
            players.addElement(game.getPlayers().get(i));
        }
        playerList = new JList<>(players);
        JScrollPane scPlayer = new JScrollPane();
        scPlayer.setViewportView(playerList);
        playerList.setLayoutOrientation(JList.VERTICAL);
        scPlayer.setBounds(245, 420, 60, 25);
        playerList.addMouseListener(playerSelected);
        add(scPlayer);
    }

    private void initElementList(){
        neighbours = new DefaultListModel<>();
        elementList = new JList<>(neighbours);
        JScrollPane scNeighbour = new JScrollPane();
        scNeighbour.setViewportView(elementList);
        elementList.setLayoutOrientation(JList.VERTICAL);
        scNeighbour.setBounds(245, 460, 60, 25);
        elementList.addMouseListener(elementSelected);
        add(scNeighbour);
        updateNeeded = true;
    }

    private MouseListener playerSelected = new MouseAdapter() {
        public void mousePressed(MouseEvent me) {
            neighbours.clear();
            selectedElement = null;
            selectedPlayer = playerList.getSelectedValue();
            for(int i = 0; i < game.getElements().size(); i++){
                if(selectedPlayer.getElement().isNeighbour(game.getElements().get(i))){
                    neighbours.addElement(game.getElements().get(i));
                }
            }
            if(selectedPlayer.toString().startsWith("m")){
                neighbours.addElement(((Mechanic)selectedPlayer).getNewPump());
                neighbours.addElement(((Mechanic)selectedPlayer).getNewPipe());
            }

            updateNeeded = true;
        }
    };

    private MouseListener elementSelected = new MouseAdapter() {
        public void mousePressed(MouseEvent me) {
            selectedElement = elementList.getSelectedValue();
            updateNeeded = true;
        }
    };

    private void initButtons(){
        bMove = new JButton("Move");
        bMove.setBounds(25, 420, 90, 30);
        bMove.addActionListener(move);
        add(bMove);

        bStick = new JButton("Sticky");
        bStick.setBounds(25, 460, 90, 30);
        bStick.addActionListener(makeSticky);
        add(bStick);

        bRepair = new JButton("Repair");
        bRepair.setBounds(25, 500, 90, 30);
        bRepair.addActionListener(repair);
        add(bRepair);

        bLeak = new JButton("Damage");
        bLeak.setBounds(25, 540, 90, 30);
        bLeak.addActionListener(damage);
        add(bLeak);

        bSlip = new JButton("Slippery");
        bSlip.setBounds(130, 420, 90, 30);
        bSlip.addActionListener(makeSlippery);
        add(bSlip);

        bPipeTamper = new JButton("Pipe");
        bPipeTamper.setBounds(130, 460, 90, 30);
        bPipeTamper.addActionListener(pipe);
        add(bPipeTamper);

        bPumpTamper = new JButton("Pump");
        bPumpTamper.setBounds(130, 500, 90, 30);
        bPumpTamper.addActionListener(pump);
        add(bPumpTamper);

        bSetIn = new JButton("Set in");
        bSetIn.setBounds(130, 540, 90, 30);
        bSetIn.addActionListener(setIn);
        add(bSetIn);

        bSetOut = new JButton("Set out");
        bSetOut.setBounds(235, 540, 90, 30);
        bSetOut.addActionListener(setOut);
        add(bSetOut);
    }

    private ActionListener makeSticky = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            game.makeAction(selectedPlayer.toString(), "stick");
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };

    private ActionListener makeSlippery = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            game.makeAction(selectedPlayer.toString(), "slip");
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };

    private ActionListener damage = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            game.damage(selectedPlayer.toString());
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };

    private ActionListener repair = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            game.repair(selectedPlayer.toString());
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };

    private ActionListener move = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            try {
                game.movePlayer(selectedPlayer.toString(), selectedElement.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            neighbours.clear();
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };

    private ActionListener pipe = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            game.pipeUpOrDown(selectedPlayer.toString(), selectedElement.toString());
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };

    private ActionListener setIn = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            inPipe = (PassiveElement) elementList.getSelectedValue();
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };

    private ActionListener setOut = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            outPipe = (PassiveElement) elementList.getSelectedValue();
            game.setPump(selectedPlayer.toString(), inPipe.toString(), outPipe.toString());
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };

    private ActionListener pump = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            game.pumpUpOrDown(selectedPlayer.toString());
            selectedElement = null;
            selectedPlayer = null;
            updateNeeded = true;
        }
    };



    private void initImages(){
        this.images = new BufferedImage[10];
        images[0] = getImage("Saboteur.png");
        images[1] = getImage("Mechanic.png");
        images[2] = getImage("Pump.png");
        images[3] = getImage("Cistern.png");
        images[4] = getImage("Source.png");
        images[5] = getImage("Input.png");
        images[6] = getImage("Output.png");
        images[7] = getImage("Broken.png");
        images[8] = getImage("Selected_Player.png");
        images[9] = getImage("Selected_Active.png");
    }

    public void update(Graphics g){
        drawBackGround(g);
        for (IViewable e: game.getGraphicList()) {
            e.Draw(this, g);
        }
        drawSelectedPlayer(g);
    }

    private void drawSelectedPlayer(Graphics g) {
        g.setColor(new Color(255, 204, 153));
        g.fillRect(400, 410, 150, 180);
        g.fillRect(590, 410, 230, 180);
        g.setColor(Color.black);
        g.drawRect(400, 410, 150, 180);
        g.drawRect(590, 410, 230, 180);
        g.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        g.drawString("Player:", 405, 430);
        g.drawString("Players element: ", 595, 430);
        int offset = 25;
        if(selectedPlayer != null) {
            //Player info
            g.drawImage(images[8], ((IViewable) selectedPlayer).getX(), ((IViewable) selectedPlayer).getY(), null);
            g.drawString(selectedPlayer.toString(), 405, 430 + offset);
            g.drawString("Position: " + selectedPlayer.getElement().toString(), 405, 430 + 2 * offset);
            if(selectedPlayer.getStuck())
                g.drawString("Stuck: true", 405, 430 + 3 * offset);
            else
                g.drawString("Stuck: false", 405, 430 + 3 * offset);
            if(selectedPlayer.toString().contains("m")){
                if(((Mechanic) selectedPlayer).getNewPipe() != null)
                    g.drawString("Has Pipe: true", 405, 430 + 4 * offset);
                else
                    g.drawString("Has Pipe: false", 405, 430 + 4 * offset);
                if(((Mechanic) selectedPlayer).getNewPump() != null)
                    g.drawString("Has Pump: true", 405, 430 + 5 * offset);
                else
                    g.drawString("Has Pump: false", 405, 430 + 5 * offset);
            }
            //Element info
            g.drawString(selectedPlayer.getElement().toString(), 720, 430);
            if(selectedPlayer.getElement().toString().contains("pi")){
                g.drawString("Ends: " + ((PassiveElement) selectedPlayer.getElement()).getE1().toString() +
                        " " + ((PassiveElement) selectedPlayer.getElement()).getE2().toString(), 595, 430 + offset);
                g.drawString("Leaking: " + String.valueOf(selectedPlayer.getElement().broken), 595, 430 + 2 * offset);
                g.drawString("Stick time: " + String.valueOf(((PassiveElement) selectedPlayer.getElement()).getStickTime()), 595, 430 + 3 * offset);
                g.drawString("Slip time: " + String.valueOf(((PassiveElement) selectedPlayer.getElement()).getSlipTime()), 595, 430 + 4 * offset);
                g.drawString("Protect time: " + String.valueOf(((PassiveElement) selectedPlayer.getElement()).getProtectTime()), 595, 430 + 5 * offset);
                g.drawString("Water inside: " + String.valueOf(((PassiveElement) selectedPlayer.getElement()).getLoad()), 595, 430 + 6 * offset);
            }
            else {
                g.drawString("Pipes: ", 595, 430 + offset);
                int i = 0;
                for (PassiveElement p: ((ActiveElement) selectedPlayer.getElement()).getPipes()) {
                    g.drawString(p.toString(), 645 + i * 35, 430 + offset);
                    i++;
                }
                if (selectedPlayer.getElement().toString().contains("pu")) {
                    if(((Pump) selectedPlayer.getElement()).getInPipe() != null)
                        g.drawString("Input: " + ((Pump) selectedPlayer.getElement()).getInPipe().toString(), 595, 430 + 2 * offset);
                    else if(((Pump) selectedPlayer.getElement()).getInPipe() == null)
                        g.drawString("Input: " + "-", 595, 430 + 2 * offset);
                    if(((Pump) selectedPlayer.getElement()).getOutPipe() != null)
                        g.drawString("Output: " + ((Pump) selectedPlayer.getElement()).getOutPipe().toString(), 595, 430 + 3 * offset);
                    else if(((Pump) selectedPlayer.getElement()).getOutPipe() == null)
                        g.drawString("Output: " + "-", 595, 430 + 3 * offset);
                    g.drawString("Water Inside: " + String.valueOf(((Pump) selectedPlayer.getElement()).getWaterInside()), 595, 430 + 4 * offset);
                    g.drawString("Broken: " + String.valueOf(((Pump) selectedPlayer.getElement()).broken), 595, 430 + 5 * offset);
                }
                else if (selectedPlayer.getElement().toString().contains("ci")) {
                    g.drawString("Water Inside: " + String.valueOf(((Cistern) selectedPlayer.getElement()).getWaterInside()), 595, 430 + 2 * offset);
                }
            }
        }
    }

    public void drawBackGround(Graphics g){
        g.setColor(new Color(233, 168, 102));
        g.fillRect(0,0, size.width, size.height - 200);
        g.setColor(new Color(204, 102, 0));
        g.fillRect(0,size.height - 200, size.width, size.height - (size.height - 200));
    }

    public void drawMechanic(int x, int y, Graphics g){
        g.drawImage(images[1], x, y, null);
    }

    public void drawSaboteur(int x, int y, Graphics g){
        g.drawImage(images[0], x, y, null);
    }

    public void drawPump(GPump gp, int x, int y, Graphics g, boolean broken){
        g.drawImage(images[2], x, y, null);
        if(broken){
            g.drawImage(images[7], x, y, null);
        }
        if(selectedElement != null && selectedElement.toString().equals(gp.toString()))
            g.drawImage(images[9], x, y, null);
    }

    public void drawCistern(GCistern gc, int x, int y, Graphics g){
        g.drawImage(images[3], x, y, null);
        if(selectedElement != null && selectedElement.toString().equals(gc.toString()))
            g.drawImage(images[9], x, y, null);
    }

    public void drawSource(GSource gs, int x, int y, Graphics g){
        g.drawImage(images[4], x, y, null);
        if(selectedElement != null && selectedElement.toString().equals(gs.toString()))
            g.drawImage(images[9], x, y, null);
    }

    public void drawPipe(GPipe gp, IViewable e1, IViewable e2, Graphics g, boolean broken, boolean sticky, boolean slippery){
        if(sticky) {
            g.setColor(new Color(0, 133, 0));
        }
        else if(slippery){
            g.setColor(new Color(0, 255, 255));
        }
        else if(selectedElement != null && selectedElement.toString().equals(gp.toString())){
            g.setColor(Color.red);
        }
        else
            g.setColor(new Color(0, 51, 102));
        int thickness = 8;
        int offset = 16;

        if(e1 == null || e2 == null)
            return;
        int x1 = e1.getX()+offset;
        int y1 = e1.getY()+offset;
        int x2 = e2.getX()+offset;
        int y2 = e2.getY()+offset;

        int dX = x2 - x1;
        int dY = y2 - y1;
        // line length
        double lineLength = Math.sqrt(dX * dX + dY * dY);

        double scale = (double)(thickness) / (2 * lineLength);

        // The x,y increments from an endpoint needed to create a rectangle...
        double ddx = -scale * (double)dY;
        double ddy = scale * (double)dX;
        ddx += (ddx > 0) ? 0.5 : -0.5;
        ddy += (ddy > 0) ? 0.5 : -0.5;
        int dx = (int)ddx;
        int dy = (int)ddy;

        // Now we can compute the corner points...
        int xPoints[] = new int[4];
        int yPoints[] = new int[4];

        xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
        xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
        xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
        xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

        g.fillPolygon(xPoints, yPoints, 4);
        if(broken){
            g.drawImage(images[7], gp.getX(), gp.getY(), null);
        }
    }
    private BufferedImage getImage(String name){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private void setPanelSize() {
        size = new Dimension(840, 600);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        update(g);
    }

    public static BufferedImage rotate(BufferedImage img, double degree)
    {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImage = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());

        Graphics2D g2 = newImage.createGraphics();

        g2.rotate(Math.toRadians(degree), width / 2,
                height / 2);
        g2.drawImage(img, null, 0, 0);

        return newImage;
    }
}
