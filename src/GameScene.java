import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameScene extends JPanel implements KeyListener {
    public static Player player;
    private boolean[] pressedKeys;
    public static ArrayList<Obstacle> obstacleList = new ArrayList<>();
    public static ArrayList<Heart> hearts = new ArrayList<>();

    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int GAME_SPEED_FAST = 3;
    public static final int GAME_SPEED_NORMAL = 10;
    public static final int GAME_SPEED_SLOW = 25;
    public static int TOTAL_OBSTACLES = 5;

    public static final int HOME_PAGE = 1;
    public static final int GAME_PAGE = 2;
    public static int page;


    public static final int wine1 = 1;
    public static final int wine2 = 2;
    public static final int wine3 = 3;
    public static final int wine4 = 4;
    public static int wine = wine1;
    public static int clock = 30;
    public static int counter = 3000;
    public static boolean startGame;

    public JLabel gameOver = new JLabel("אתה הפסדת,נסה שוב!");
    public JLabel youWinner = new JLabel("אתה ניצחת");
    public int newGame;
    public JLabel newGameTitle = new JLabel("המשחק יטען מחדש בעוד" + newGame);
    public static JButton start = new JButton("התחל משחק");
    public static JButton instartc = new JButton("הוראות המשחק");

    JTextArea instruction = new JTextArea();

    public static JLabel timer = new JLabel("00" + ":" + clock);
    public static JLabel level = new JLabel("Level" + wine);

    public static final int Button_HEIGHT = 100;
    public static final int Button_WIDTH = 300;
    public static final int Button_X = (Window.WINDOW_WIDTH - Button_WIDTH) / 2;
    public static final int Button_Y = 300;
    public static Font font;

    private Image image;

    public void newHearts() {
        hearts = new ArrayList<>();
        for (int i = 0; i < player.life; i++) {
            Heart heart = new Heart(((i + 1) * 150) - 140, 30);
            hearts.add(heart);
        }
    }


    public GameScene() {
        this.setLayout(null);
        this.page = HOME_PAGE;
        image = Toolkit.getDefaultToolkit().getImage("Image\\backGround.jpg");
        font = new Font(Font.SANS_SERIF, Font.PLAIN, 22);
        this.player = new Player(60, 200);
        this.pressedKeys = new boolean[4];
        for (int i = 0; i < TOTAL_OBSTACLES; i++) {
            Obstacle obstacle = new Obstacle(this.player.getX() + (i + 1) * 200, Window.WINDOW_HEIGHT / 2);
            obstacleList.add(obstacle);
            obstacle.start();
        }

        gameOver.setBounds(Window.WINDOW_WIDTH / 2 - 300, 0, 250, 250);
        gameOver.setVisible(false);
        gameOver.setFont(new Font("v", 2, 22));
        gameOver.setForeground(Color.red);
        this.add(gameOver);

        youWinner.setBounds(Window.WINDOW_WIDTH / 2 - 300, 0, 250, 250);
        youWinner.setVisible(false);
        youWinner.setFont(font);
        youWinner.setForeground(Color.BLUE);
        this.add(youWinner);

        newGameTitle.setBounds(Window.WINDOW_WIDTH / 2, 0, 250, 250);
        newGameTitle.setVisible(false);
        newGameTitle.setFont(font);
        newGameTitle.setForeground(Color.CYAN);
        this.add(newGameTitle);


        level.setFont(new Font("v", 2, 22));
        level.setForeground(Color.white);
        level.setBounds(Window.WINDOW_WIDTH / 2, 0, 100, 100);
        this.add(level);

        timer.setBounds(Window.WINDOW_WIDTH / 2 - 100, 0, 100, 100);
        timer.setForeground(Color.white);
        timer.setFont(new Font("v", 2, 22));
        this.add(timer);


        start.setBounds(Button_X, Button_Y, Button_WIDTH, Button_HEIGHT);
        start.setFont(new Font("Ariel", 2, 22));
        this.add(start);
        start.setBackground(Color.red);


        instartc.setBounds(Button_X, Button_Y + Button_HEIGHT + 20, Button_WIDTH, Button_HEIGHT);
        instartc.setFont(new Font("Ariel", 2, 22));
        this.add(instartc);
        instartc.setBackground(Color.CYAN);

        instruction.setBounds(0, 150, 400, 500);
        instruction.setFont(font);
        instruction.setForeground(Color.white);
        instruction.setBackground(Color.BLUE
        );



        instartc.addActionListener(e -> {
            instartc.setVisible(false);
            instruction.setEditable(false);
            instruction.setLineWrap(true);
            instruction.setWrapStyleWord(true);
            this.add(instruction);
            instruction.setText("יש להזיז את ארבעת מקשי החצים על מנת לשלוט בחללית.\n\n" +
                    "בתחילת המשחק עומדות לרשותך שלושה לבבות, בכל פסילה אתה מאבד אחת.\n\n" +
                    "כמו כן יש למשחק ארבעה שלבים, כל פעם שתצליח לברוח מהמטאורים ולעבור לצד השני תעלה שלב, והמשחק יתקשה בכל פעם שתעבור שלב כי המטאורים ירחפו מהר יותר.\n\n" +
                    "הזמן הקצוב שיש לך לעבור כל שלב הוא  30 שניות, אם לא תעבור בזמן תאבד לבבה אחת.\n\n" +
                    "ניצחת - כאשר עברת את כל השלבים.\n\n" +
                    "הפסדת - כאשר תאבד את כל הלבבות.\n\n");

        });
        start.addActionListener(e -> {
            GameScene.page = GameScene.GAME_PAGE;
            instartc.setVisible(false);
            start.setVisible(false);
            instruction.setVisible(false);
            GameScene.startGame = true;
        });


        this.newHearts();
        this.mainGameLoop();
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);


    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        for (int i = 0; i < hearts.size(); i++) {
            hearts.get(i).paint(graphics);
        }
        if (hearts.size() > 0 && wine != 20 && page == GAME_PAGE) {
            this.player.paint(graphics);
            for (int i = 0; i < obstacleList.size(); i++) {
                obstacleList.get(i).paint(graphics);
            }
        }
    }


    private void mainGameLoop() {
        new Thread(() -> {
            int waitBeforeRevive = 0;
            int whitBeforeNewGame = 2000;

            while (true) {
                int dx = 0;
                int dy = 0;
                if (this.pressedKeys[DOWN] && this.player.getY() + (this.player.height + 30) <= Window.WINDOW_HEIGHT) {
                    dy++;
                }
                if (this.pressedKeys[UP] && this.player.getY() >= 0) {
                    dy--;
                }
                if (this.pressedKeys[RIGHT]) {
                    dx++;
                }
                if (this.pressedKeys[LEFT] && this.player.getX() > 0) {
                    dx--;
                }
                this.player.move(dx, dy);
                Rectangle playerRect = this.player.calculateRectangle();
                if (wine < 20 && hearts.size() > 0 && page == GAME_PAGE) {
                    for (int i = 0; i < obstacleList.size(); i++) {
                        Rectangle obstacleRect = this.obstacleList.get(i).calculateRectangle();
                        if (Utils.checkCollision(playerRect, obstacleRect)) {
                            this.player.kill();
                        }
                    }
                }

                if (!this.player.isAlive() || counter == 0 && wine != 20 && hearts.size() > 0) {
                    waitBeforeRevive++;
                    if (waitBeforeRevive > 300) {
                        this.player.ChangeLife();
                        this.newHearts();

                        this.player.revive();
                        waitBeforeRevive = 0;
                        counter = 3000;
                    }
                }
                if (counter > 0 && page == GAME_PAGE && wine != 20 && hearts.size() > 0) {
                    counter--;
                    if (counter % 100 == 0) {
                        clock = counter / 100;
                        timer.setText("00" + ":" + clock);
                    }
                }
                if (hearts.size() == 0 || wine == 20) {
                    counter = 0;
                    timer.setVisible(false);
                    level.setVisible(false);
                    if (hearts.size() == 0) {
                        gameOver.setVisible(true);
                    } else if (wine == 20) {
                        youWinner.setVisible(true);

                    }
                    newGameTitle.setVisible(true);
                    whitBeforeNewGame--;
                    if (whitBeforeNewGame % 100 == 0) {
                        newGame = (whitBeforeNewGame / 100);
                    }
                    newGameTitle.setText("המשחק יטען מחדש בעוד" + newGame);
                    if (whitBeforeNewGame == 0) {
                        start.setVisible(true);
                        instartc.setVisible(true);
                        newGameTitle.setVisible(false);
                        gameOver.setVisible(false);
                        youWinner.setVisible(false);
                        whitBeforeNewGame = 2000;
                        page = HOME_PAGE;
                        timer.setVisible(true);
                        level.setVisible(true);
                        player.life = player.life3;
                        this.newHearts();
                        wine = wine1;
                        level.setText("Level" + wine);
                        counter = 3000;
                        timer.setText("00" + ":" + counter / 100);

                    }
                }


                this.player.overStepsTheBounds();
                level.setText("Level" + wine);

                repaint();
                Utils.sleep(GAME_SPEED_FAST);
            }
        }).start();
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
//            System.out.println("keyPressed");
        Integer toPress = null;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            toPress = RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            toPress = LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            toPress = UP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            toPress = DOWN;
        }
        if (toPress != null) {
            this.pressedKeys[toPress] = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        Integer toRelease = null;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            toRelease = RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            toRelease = LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            toRelease = UP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            toRelease = DOWN;
        }
        if (toRelease != null) {
            this.pressedKeys[toRelease] = false;
        }

    }
}
