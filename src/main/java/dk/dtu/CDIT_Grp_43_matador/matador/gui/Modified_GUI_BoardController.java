package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import gui_codebehind.GUI_Center;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public final class Modified_GUI_BoardController {
    private String userInput = null;
    private GUI_Board board;
    private static volatile Random rand = null;

    public static Random rand() {
        if (rand == null) {
            Class var0 = gui_codebehind.GUI_BoardController.class;
            synchronized(gui_codebehind.GUI_BoardController.class) {
                if (rand == null) {
                    rand = new Random();
                }
            }
        }

        return rand;
    }

    public Modified_GUI_BoardController(GUI_Field[] fields) {
        this.board = new GUI_Board(fields);
    }

    public void showMessage(String msg) {
        final CountDownLatch latch = new CountDownLatch(1);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Modified_GUI_BoardController.this.board.clearInputPanel();
                latch.countDown();
            }
        });
        okButton.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    Modified_GUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }

            }

            public void keyPressed(KeyEvent e) {
            }
        });
        this.board.getUserInput(msg, new Component[]{okButton});
        this.getFocus(okButton);

        try {
            latch.await();
        } catch (InterruptedException var5) {
            var5.printStackTrace();
        }

    }

    private void getFocus(JButton okButton) {
        try {
            Thread.sleep(100L);
            okButton.requestFocusInWindow();
        } catch (InterruptedException var3) {
            ;
        }

    }

    public String getUserString(String msg) {
        final CountDownLatch latch = new CountDownLatch(1);
        final JTextField tf = new JTextField(20);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Modified_GUI_BoardController.this.userInput = tf.getText();
                Modified_GUI_BoardController.this.board.clearInputPanel();
                latch.countDown();
            }
        });
        tf.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    Modified_GUI_BoardController.this.userInput = tf.getText();
                    Modified_GUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }

            }

            public void keyPressed(KeyEvent e) {
            }
        });
        okButton.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    Modified_GUI_BoardController.this.userInput = tf.getText();
                    Modified_GUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }

            }

            public void keyPressed(KeyEvent e) {
            }
        });
        this.board.getUserInput(msg, new Component[]{tf, okButton});
        tf.requestFocusInWindow();

        try {
            latch.await();
            return this.userInput;
        } catch (InterruptedException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public int getUserInteger(String msg, final int min, final int max) {
        if (min >= 0 && max >= 1 && max > min) {
            if ("".equals(msg)) {
                return -1;
            } else {
                final CountDownLatch latch = new CountDownLatch(1);
                final JButton okButton = new JButton("OK");
                okButton.setEnabled(false);
                final JTextField tf = new JTextField(20);
                tf.setHorizontalAlignment(4);
                tf.addKeyListener(new KeyListener() {
                    public void keyTyped(KeyEvent ke) {
                    }

                    public void keyReleased(KeyEvent ke) {
                        if (ke.getKeyCode() == 10) {
                            Modified_GUI_BoardController.this.userInput = tf.getText();
                            Modified_GUI_BoardController.this.board.clearInputPanel();
                            latch.countDown();
                        }

                        String input = tf.getText() + ke.getKeyChar();
                        String output = "";

                        int val;
                        for(val = 0; val < input.toCharArray().length - 1; ++val) {
                            char c = input.toCharArray()[val];
                            if (c >= '0' && c <= '9' && val < 9) {
                                output = output + c;
                            }
                        }

                        tf.setText(output);
                        boolean var7 = true;

                        try {
                            val = Integer.parseInt(output);
                            if (min <= val && val <= max) {
                                tf.setForeground(Color.BLACK);
                            } else {
                                tf.setForeground(Color.RED);
                            }
                        } catch (Exception var6) {
                            tf.setForeground(Color.RED);
                        }

                        okButton.setEnabled(tf.getForeground().equals(Color.BLACK));
                    }

                    public void keyPressed(KeyEvent ke) {
                    }
                });
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Modified_GUI_BoardController.this.userInput = tf.getText();
                        Modified_GUI_BoardController.this.board.clearInputPanel();
                        latch.countDown();
                    }
                });
                this.board.getUserInput(msg, new Component[]{tf, okButton});

                try {
                    latch.await();
                    return Integer.parseInt(this.userInput);
                } catch (InterruptedException var8) {
                    var8.printStackTrace();
                    return -1;
                } catch (NumberFormatException var9) {
                    var9.printStackTrace();
                    return -1;
                }
            }
        } else {
            return -1;
        }
    }

    public String getUserButtonPressed(String msg, String... buttonTexts) {
        if (buttonTexts != null && buttonTexts.length >= 1) {
            final CountDownLatch latch = new CountDownLatch(1);
            JButton[] buttons = new JButton[buttonTexts.length];
            JButton primaryButton = null;

            for(int i = 0; i < buttonTexts.length; ++i) {
                String string = buttonTexts[i];
                if ("".equals(string)) {
                    return null;
                }

                JButton btn = new JButton(string);
                primaryButton = btn;
                btn.setName(string);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Modified_GUI_BoardController.this.userInput = ((JButton)e.getSource()).getName();
                        Modified_GUI_BoardController.this.board.clearInputPanel();
                        latch.countDown();
                    }
                });
                btn.addKeyListener(new KeyListener() {
                    public void keyTyped(KeyEvent e) {
                    }

                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == 10) {
                            Modified_GUI_BoardController.this.userInput = ((JButton)e.getSource()).getName();
                            Modified_GUI_BoardController.this.board.clearInputPanel();
                            latch.countDown();
                        }

                    }

                    public void keyPressed(KeyEvent e) {
                    }
                });
                buttons[i] = btn;
            }

            this.board.getUserInput(msg, buttons);
            this.getFocus(primaryButton);

            try {
                latch.await();
                return this.userInput;
            } catch (InterruptedException var9) {
                var9.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public String getUserSelection(String msg, String... options) {
        if (options != null && options.length >= 1) {
            final CountDownLatch latch = new CountDownLatch(1);

            for(int i = 0; i < options.length; ++i) {
                String string = options[i];
                if ("".equals(string)) {
                    return null;
                }
            }

            final JComboBox<String> dropdown = new JComboBox(options);
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Modified_GUI_BoardController.this.userInput = dropdown.getSelectedItem().toString();
                    Modified_GUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }
            });
            okButton.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == 10) {
                        Modified_GUI_BoardController.this.userInput = dropdown.getSelectedItem().toString();
                        Modified_GUI_BoardController.this.board.clearInputPanel();
                        latch.countDown();
                    }

                }

                public void keyPressed(KeyEvent e) {
                }
            });
            this.board.getUserInput(msg, new Component[]{dropdown, okButton});
            okButton.requestFocusInWindow();

            try {
                latch.await();
                return this.userInput;
            } catch (InterruptedException var7) {
                var7.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean addPlayer(GUI_Player player) {
        return this.board.addPlayer(player);
    }

    /*
    public void setDice(int faceValue1, int faceValue2) {
        int rotation1 = rand().nextInt(360);
        int rotation2 = rand().nextInt(360);
        this.setDice(faceValue1, rotation1, faceValue2, rotation2);
    }
    */

    public void setDice(int faceValue1, int x1, int y1, int faceValue2, int x2, int y2) {
        int rotation1 = rand().nextInt(360);
        int rotation2 = rand().nextInt(360);
        this.setDice(faceValue1, rotation1, x1, y1, faceValue2, rotation2, x2, y2);
    }

    /*
    public void setDice(int faceValue1, int rotation1, int faceValue2, int rotation2) {
        List<Point> dicePlaces = new ArrayList();
        int x;
        int y;
        if (this.getFields().length == 40) {
            for(x = 1; x < 10; ++x) {
                for(y = 1; y < 10; ++y) {
                    if ((x < 4 || x > 6 || y < 4 || y > 6) && (x <= 6 || y <= 9 - this.board.getPlayerCount())) {
                        dicePlaces.add(new Point(x, y));
                    }
                }
            }
        } else if (this.getFields().length < 40 && this.getFields().length >= 32) {
            for(x = 2; x < 4; ++x) {
                for(y = 2; y < 9; ++y) {
                    dicePlaces.add(new Point(x, y));
                }
            }
        } else {
            for(x = 1; x < 9; ++x) {
                for(y = 0; y < 2; ++y) {
                    dicePlaces.add(new Point(x, y));
                }
            }
        }

        int index1 = false;
        x = (int)(Math.random() * (double)dicePlaces.size());
        Point dice1Position = (Point)dicePlaces.remove(x);
        int MAX_DISTANCE = true;
        ArrayList<Point> toBeRemoved = new ArrayList();
        Iterator var10 = dicePlaces.iterator();

        while(true) {
            Point p;
            do {
                if (!var10.hasNext()) {
                    dicePlaces.removeAll(toBeRemoved);
                    int index2 = false;
                    int index2 = (int)(Math.random() * (double)dicePlaces.size());
                    p = (Point)dicePlaces.get(index2);
                    this.setDice(faceValue1, rotation1, dice1Position.x, dice1Position.y, faceValue2, rotation2, p.x, p.y);
                    return;
                }

                p = (Point)var10.next();
            } while(p.x >= dice1Position.x - 2 && p.x <= dice1Position.x + 2 && p.y >= dice1Position.y - 2 && p.y <= dice1Position.y + 2);

            toBeRemoved.add(p);
        }
    }
    */

    public void setDice(int faceValue1, int rotation1, int x1, int y1, int faceValue2, int rotation2, int x2, int y2) {
        boolean faceValuesAreValid = this.areFaceValuesValid(faceValue1, faceValue2);
        boolean rotationsAreValid = this.areRotationsValid(rotation1, rotation2);
        boolean positionsAreValid = this.arePositionsValid(x1, y1, x2, y2);
        if (faceValuesAreValid && rotationsAreValid && positionsAreValid) {
            this.board.setDice(x1, y1, faceValue1, rotation1, x2, y2, faceValue2, rotation2);
        }

    }

    private boolean arePositionsValid(int x1, int y1, int x2, int y2) {
        return x1 >= 0 && x1 <= 10 && y1 >= 0 && y1 <= 10 && x2 >= 0 && x2 <= 10 && y2 >= 0 && y2 <= 10;
    }

    private boolean areRotationsValid(int rotation1, int rotation2) {
        return rotation1 >= 0 && rotation1 <= 359 && rotation2 >= 0 && rotation2 <= 359;
    }

    private boolean areFaceValuesValid(int faceValue1, int faceValue2) {
        return faceValue1 >= 1 && faceValue1 <= 6 && faceValue2 >= 1 && faceValue2 <= 6;
    }

    public void displayChanceCard(String txt) {
        GUI_Center.getInstance().setChanceCard(txt);
        GUI_Center.getInstance().displayChanceCard();
    }

    public void setChanceCard(String txt) {
        GUI_Center.getInstance().setChanceCard(txt);
    }

    public void displayChanceCard() {
        GUI_Center.getInstance().displayChanceCard();
    }

    public GUI_Field[] getFields() {
        return this.board.getFields();
    }

    public void setDie(int faceValue) {
        int rotation1 = rand().nextInt(360);
        int x = rand().nextInt(9);
        this.setDice(faceValue, rotation1, x, 9, faceValue, rotation1, x, 9);
    }
}
