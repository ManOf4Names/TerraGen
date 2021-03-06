import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Menu extends JPanel {
    JButton newToken;
    JButton removeToken;
    JButton mainMenu;
    Box.Filler filler1;
    Box.Filler filler2;
    Color labelTextColor = Color.YELLOW;
    Color labelBGColor = Color.BLACK;

    public Menu() {
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(0,0,200,TerraGen.window.getHeight());
        setMaximumSize(new Dimension(200,TerraGen.window.getHeight()));

        buildDefaultMenu();
    }

    public void buildDefaultMenu() {

        JLabel title = new JLabel("Menu");
        title.setForeground(labelTextColor);
        title.setBackground(labelBGColor);
        title.setOpaque(true);
        this.add(title);

        // whitespace
        filler1 = new Box.Filler(new Dimension(50,50), new Dimension(100, 20), new Dimension(100, 50));
        this.add(filler1);

        // button to add a token to the field
        newToken = addTokenButton();
        newToken.setVisible(true);
        this.add(newToken);

        // button to remove a token from the field
        removeToken = deleteTokenButton();
        removeToken.setVisible(true);
        this.add(removeToken);


        mainMenu = new JButton("Main Menu");
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TerraGen.window.mainScreen();
            }
        });
        // whitespace
        filler2 = new Box.Filler(new Dimension(50,50), new Dimension(100, 20), new Dimension(100, 50));
        this.add(filler2);
        this.add(mainMenu);

    }

    private JButton addTokenButton() {
        newToken = new JButton("Add Token");
        newToken.addActionListener(e1 -> {
            AtomicInteger x = new AtomicInteger(1);
            AtomicInteger y = new AtomicInteger(1);
            this.remove(this.newToken);
            this.remove(this.removeToken);
            this.remove(filler2);
            this.remove(mainMenu);

            AtomicInteger tokenSize = new AtomicInteger(1);
            AtomicReference<String> setC = new AtomicReference<>();

            // panel for the adding token components
            JPanel addTokenPanel = new JPanel();
            addTokenPanel.setLayout(new BoxLayout(addTokenPanel, BoxLayout.Y_AXIS));

            JLabel tokenScaleLabel = new JLabel("Choose Token Scale: " + tokenSize);
            tokenScaleLabel.setForeground(labelTextColor);
            tokenScaleLabel.setBackground(labelBGColor);
            tokenScaleLabel.setOpaque(true);

            // slider for deciding token scale
            JSlider tokenScale = new JSlider(1,2,1);
            tokenScale.addChangeListener(e2 -> {
                tokenSize.set(tokenScale.getValue());
                tokenScaleLabel.setText("Choose Token Scale: " + tokenSize);
            });
            tokenScale.setOpaque(false);
            tokenScale.setPreferredSize(new Dimension(5,5));

            // Field for setting token color
            JLabel tokenCol = new JLabel("Token Color: ");
            tokenCol.setForeground(labelTextColor);
            tokenCol.setBackground(labelBGColor);
            tokenCol.setOpaque(true);
            JTextField setCol = new JTextField(10); // User input field to set token color
            setCol.addActionListener(e3 -> {
                String col = setCol.getText();
                setC.set(col);
                tokenCol.setText("Token Color: " + setC);
            });

            // whitespace
            Box.Filler filler0 = new Box.Filler(new Dimension(50,20), new Dimension(50, 20), new Dimension(50, 20));

            // Fields for determining coordinates
            JLabel coordinatesLabel = new JLabel("coordinates: (" + x + ", " + y + ")");
            coordinatesLabel.setForeground(labelTextColor);
            coordinatesLabel.setBackground(labelBGColor);
            coordinatesLabel.setOpaque(true);
            JLabel xLabel = new JLabel("x");
            xLabel.setForeground(labelTextColor);
            xLabel.setBackground(labelBGColor);
            xLabel.setOpaque(true);
            JLabel yLabel = new JLabel("y");
            yLabel.setForeground(labelTextColor);
            yLabel.setBackground(labelBGColor);
            yLabel.setOpaque(true);
            JButton decreaseX = new JButton("-"); // button to decrease xCoordinate
            decreaseX.addActionListener(e4 -> {
                if (x.get() > 1) {
                    x.set(x.get() - 1);
                    coordinatesLabel.setText("coordinates: (" + x + ", " + y + ")");
                }
            });
            JButton increaseX = new JButton("+"); // button to increase xCoordinate
            increaseX.addActionListener(e4 -> {
                if (x.get() < GridSlider.getGridScale()) {
                    x.set(x.get() + 1);
                    coordinatesLabel.setText("coordinates: (" + x + ", " + y + ")");
                }
            });
            JButton decreaseY = new JButton("-"); // button to decrease yCoordinate
            decreaseY.addActionListener(e4 -> {
                if (y.get() > 1) {
                    y.set(y.get() - 1);
                    coordinatesLabel.setText("coordinates: (" + x + ", " + y + ")");
                }
            });
            JButton increaseY = new JButton("+"); // button to increase yCoordinate
            increaseY.addActionListener(e4 -> {
                if (y.get() < GridSlider.getGridScale()) {
                    y.set(y.get() + 1);
                    coordinatesLabel.setText("coordinates: (" + x + ", " + y + ")");
                }
            });

            // Token Color Selection Panel
            JPanel colorPanel = new JPanel();
            colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
            colorPanel.setMaximumSize(new Dimension(200, 50));
            JPanel setColorPanel = new JPanel();
            setColorPanel.setMaximumSize(new Dimension(200, 50));
            setColorPanel.add(setCol);
            setColorPanel.setOpaque(false);
            colorPanel.setOpaque(false);

            // Token Coordinates Panel
            JPanel coordinatesPanel = new JPanel();
            coordinatesPanel.setLayout(new BoxLayout(coordinatesPanel, BoxLayout.Y_AXIS));
            coordinatesPanel.setMaximumSize(new Dimension(200,100));
            coordinatesPanel.setOpaque(false);

            // Token X-Coordinates Panel
            JPanel xCoordinatePanel = new JPanel();
            xCoordinatePanel.add(decreaseX);
            xCoordinatePanel.add(xLabel);
            xCoordinatePanel.add(increaseX);
            coordinatesPanel.add(xCoordinatePanel);
            xCoordinatePanel.setOpaque(false);

            // Token Y-Coordinates Panel
            JPanel yCoordinatePanel = new JPanel();
            yCoordinatePanel.add(decreaseY);
            yCoordinatePanel.add(yLabel);
            yCoordinatePanel.add(increaseY);
            coordinatesPanel.add(yCoordinatePanel);
            yCoordinatePanel.setOpaque(false);

            //Finalization of Token
            JButton finalizeToken = new JButton("Finalize token");

            finalizeToken.addActionListener(e5 -> {
                // Checks to see if the color is updated, if not, assigns the string stored in setCol.
                if (setC.toString().equals("null")) {
                    // Get token hex color string
                    String c = setCol.getText();

                    // Add the starting '#' symbol if not present
                    if (!c.startsWith("#"))
                        c = "#" + c;

                    setC.set(c);
                }

                this.remove(finalizeToken);
                addTokenPanel.remove(tokenScaleLabel);
                addTokenPanel.remove(tokenScale);
                addTokenPanel.remove(tokenCol);
                addTokenPanel.remove(setColorPanel);
                addTokenPanel.remove(filler0);
                addTokenPanel.remove(coordinatesLabel);
                addTokenPanel.remove(coordinatesPanel);

                var t = TerraGen.window.game.addToken(x.intValue() - 1, y.intValue() - 1, setC.toString());
                t.setScale(tokenSize.get());

                this.add(newToken);
                this.add(removeToken);
                // whitespace
                this.add(filler2);
                this.add(mainMenu);
                TerraGen.window.pack();
            });

            addTokenPanel.add(tokenScaleLabel);
            addTokenPanel.add(tokenScale);
            addTokenPanel.add(tokenCol);
            addTokenPanel.add(setColorPanel);
            addTokenPanel.add(filler2);
            addTokenPanel.add(coordinatesLabel);
            addTokenPanel.add(coordinatesPanel);
            addTokenPanel.setOpaque(false);

            this.add(addTokenPanel);
            this.add(finalizeToken);
            TerraGen.window.pack();
        });

        return newToken;
    }

    private JButton deleteTokenButton() {
        final Token[] selected = {null};
        AtomicInteger index = new AtomicInteger();
        AtomicReference<ArrayList<JButton>> buttons = new AtomicReference<>(new ArrayList<>());

        AtomicReference<JButton> back = new AtomicReference<>(new JButton());

        JButton deleteToken = new JButton("Delete Token");
        deleteToken.addActionListener(e -> {
            this.remove(this.newToken);
            this.remove(this.removeToken);
            this.remove(mainMenu);
            TerraGen.window.pack();

            AtomicBoolean selectionMade = new AtomicBoolean(false);
            int i = 1;
            for (Token token : TerraGen.window.game.getMap().tokens) {
                JButton tokenButton = new JButton("Token " + i);
                int finalI = i;
                tokenButton.addActionListener(e1 -> {
                    selected[0] = token;
                    index.set(finalI - 1);
                    selectionMade.set(true);

                    //set all other tokens "selected" tag as false
                    for (Token token1 : TerraGen.window.game.getMap().tokens) {
                        token1.selected = false;
                    }

                    selected[0].selected = true;
                    TerraGen.window.repaint();

                });
                buttons.get().add(tokenButton);
                i++;
            }
            for (JButton button : buttons.get()) {
                this.add(button);
            }

            JButton remove = new JButton("Delete selected tile");
            remove.addActionListener(e2 -> {
                if (selectionMade.get()) {
                    TerraGen.window.game.getMap().tokens.remove(index.get());
                    try {
                        TerraGen.window.getClient().pushGameChange(new
                                NetworkContainer(NetworkType.DELETETOKEN, index.get()));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                for (JButton button : buttons.get()) {
                    this.remove(button);
                }
                this.remove(remove);
                this.remove(back.get());

                buttons.set(new ArrayList<>());

                this.add(this.newToken);
                this.add(this.removeToken);
                this.add(filler2);
                this.add(mainMenu);
                TerraGen.window.repaint();
            });

            back.set(new JButton("back"));
            back.get().addActionListener(e3 -> {
                for (JButton button : buttons.get()) {
                    this.remove(button);
                }
                buttons.set(new ArrayList<>());

                //set all other tokens "selected" tag as false
                for (Token token1 : TerraGen.window.game.getMap().tokens) {
                    token1.selected = false;
                }

                this.remove(remove);
                this.remove(back.get());
                this.add(this.newToken);
                this.add(this.removeToken);
                this.add(filler2);
                this.add(mainMenu);
                TerraGen.window.pack();
            });

            this.add(remove);
            this.add(back.get());
            TerraGen.window.pack();
        });

        return deleteToken;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bg = null;
        /* Attempts to set the background image */
        try {
            bg = ImageIO.read(new File("src/backgrounds/menuBackground.png")); //TerraGen/src/backgrounds/menuBackground.png
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bg, 0, 0, null);
    }
}
