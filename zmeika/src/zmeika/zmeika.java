package zmeika;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class zmeika {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Создаем объект окна игрового поля
		myFrame okno = new myFrame();
	}
}

//Класс окна игрового поля
class myFrame extends JFrame

{
	// Конструктор класса
	public myFrame() {
		// Создаем объект панелт и подключаем ее к окну
		myPanel pan = new myPanel();
		Container cont = getContentPane();
		cont.add(pan);

		// Заголовок окна
		setTitle("Игра\" Змейка\"");

		// границы окна: расположение и размеры
		setBounds(0, 0, 800, 650);

		// Операция при закрытии окна - звершение приложения
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// запретить изменения размеров окна
		setResizable(false);

		// отображение(показ) окна
		setVisible(true);

	}
}

class myPanel extends JPanel {
	
	// переменная для пеализации логики игры
	private game myGame;

	// Таймер отрисовки и таймер изменения логики игры
	private Timer tmDraw, tmUpdate;

	// изображения использованные в игры
	private Image fon, telo, golova, ob, endg;

	// надпись для количества очков
	private JLabel lb;

	// ДВе кнопки
	private JButton btn1, btn2;
	// Сылка на панель
	private myPanel pan;

	// Класс для обработки событий от клавиатуры
	private class myKey implements KeyListener {
		// Методо при нажатии на клавищу
		public void keyPressed(KeyEvent e) {
			// Получения кода нажатой клавиши
			int key = e.getKeyCode();
			// Если нажатие одной из четырех стрелочек, то изменение направления змейки
			if (key == KeyEvent.VK_LEFT)myGame.napr = 0;
				
			else if (key == KeyEvent.VK_UP)myGame.napr = 1;
				
			else if (key == KeyEvent.VK_RIGHT)myGame.napr = 2;
				
			else if (key == KeyEvent.VK_DOWN)myGame.napr= 3;
				
		}

		public void keyReleased(KeyEvent e) {}

		public void keyTyped(KeyEvent e) {}
	}

	// конструктор класса

	public myPanel() {
		// Помещаем ссылку на саму панель в переменную
		pan = this;
		// Подключение обработчика события дляо клавиатуры к панели
		this.addKeyListener(new myKey());
		// Делаем панель в фокусе - для приема события от клавиатуры
		this.setFocusable(true);
		// попытка загрузки всех изображений для игры
		try {
			fon = ImageIO.read(new File("c:\\png\\fon.png"));
			telo = ImageIO.read(new File("c:\\png\\telo.png"));
			golova = ImageIO.read(new File("c:\\png\\golova.png"));
			ob = ImageIO.read(new File("c:\\png\\ob.png"));
			endg = ImageIO.read(new File("c:\\png\\endg.png"));
		} catch (Exception ex) {
		}

		// Создаем объект новой игры
		myGame = new game();
		// Запускаем игру
		myGame.start();

		// создаем,настраиваем и запусаем таймер
		// для отрисовки
		tmDraw = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// вызываем перерисовку - paintComponent()
				repaint();
			}
		});
		tmDraw.start();

		// Создаем,настроиваем и запускаем таймер для изменения логики игры
		tmUpdate = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// если не конец игры, то перемещем замейку
				if (myGame.endg == false) {
					myGame.perem();
				}

				// Выводим игфрмацию о количесиве очков
				lb.setText("Счет: " + myGame.kol);
			}
		});
		tmUpdate.start();

		// Включаем возможность произвольного размещения элементов интерфейса на панели
		setLayout(null);

		lb = new JLabel("Счет: 0 ");
		lb.setForeground(Color.white);
		lb.setFont(new Font("serif", 0, 30));
		lb.setBounds(630, 200, 150, 50);
		add(lb);
		// Создаем кнопку новая игра
		btn1 = new JButton();
		btn1.setText("Новая игра");
		btn1.setForeground(Color.blue);
		btn1.setFont(new Font("serif", 0, 20));
		btn1.setBounds(630, 30, 150, 50);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myGame.start();
				// Забираем фокус у кнопки Новая игра
				btn1.setFocusable(false);
				// Забираем фокус у кнопки Выход
				btn2.setFocusable(false);
				// Отдаем фокус панели
				pan.setFocusable(true);
			}

		});
		add(btn1);

		// СОздаем кнопку выход
		btn2 = new JButton();
		btn2.setText("Выход");
		btn2.setForeground(Color.red);
		btn2.setFont(new Font("serif", 0, 20));
		btn2.setBounds(630, 100, 150, 50);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(btn2);
	}

	// метод отрисовки
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);

		// Отрисовку фона

		gr.drawImage(fon, 0, 0, 800, 650, null);

		// орисовка игровогоп оля на основани массива
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				if (myGame.mas[i][j] != 0) {
					if (myGame.mas[i][j] == 1) {
						gr.drawImage(golova, 10 + j * 20, 10 + i * 20, 20, 20, null);
					} else if (myGame.mas[i][j] == -1) {// Выводим объект для поедания в ячейку игрового поля
						gr.drawImage(ob, 10 + j * 20, 10 + i * 20, 20, 20, null);
					} else if (myGame.mas[i][j] >= 2) {
						// Выводим тело змейки
						gr.drawImage(telo, 10 + j * 20, 10 + i * 20, 20, 20, null);
					}

				}
			}
		}
		// отрисовка сетки игрового поля
		gr.setColor(Color.blue);
		for (int i = 0; i <= 30; i++) {
			gr.drawLine(10 + i * 20, 10, 10 + i * 20, 610);
			gr.drawLine(10, 10 + i * 20, 610, 10 + i * 20);

		}

		// Вывод изображения конца игры - при окончании игры
		if (myGame.endg == true) {
			gr.drawImage(endg, 250, 200, 200, 100, null);
		}
	}
}
