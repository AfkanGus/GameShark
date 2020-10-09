package puh;

// Для работы с сетью 
import java.net.*;


//Для работы с потоками ввода-вывода
import java.io.*;
//ДЛя вывода диалогового окна
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class prog extends JFrame {

	private JPanel contentPane;
	private JTable tableTovar;

	// Метод для создания GET-запроса
	private String makeGet() {
		// Переменная для формирования Get-запроса
		String rez ="?";
		// Перебираем десять сторче таблицы в цикле
		for (int i = 0; i < 10; i++) {
			// Прибавляс к конец строки значениеЖ tov{номер}
			rez += ("tov" + (i + 1) + "=");
			// переменная для количества
			int kol;
			// Попытка
			try {
				// Получаем занчегие из ячейки таблицы
				// проводим к типу String и срезаем пробелы справа и слева
				String str = tableTovar.getValueAt(i, 3).toString().trim();
				// Приводим к целому типу
				kol = Integer.parseInt(str);

			} catch (Exception e) // ИСключение
			{
				// Если в ячейке ничего не было - считаем,
				// что нулевое значение
				kol = 0;
			}
			// Прибавляем в конец строки количество и символ: &
			rez += ""+kol+"&";
		}
		// Возврат значения
		return rez;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prog frame = new prog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public prog() {
		setResizable(false);
		setTitle(
				"\u041F\u043E\u0441\u0442\u0443\u043F\u043B\u0435\u043D\u0438\u0435 \u0442\u043E\u0432\u0430\u0440\u0430");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 347);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 499, 191);
		contentPane.add(scrollPane);

		tableTovar = new JTable();
		scrollPane.setViewportView(tableTovar);
		tableTovar.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "\u041C\u0435\u0434", "", null},
				{"2", "\u041F\u0440\u043E\u043F\u043E\u043B\u0441", null, null},
				{"3", "\u0412\u043E\u0441\u043A \u043F\u0447\u0435\u043B\u0438\u043D\u044B\u0439", null, null},
				{"4", "\u0426\u0432\u0435\u0442\u043E\u0447\u043D\u0430\u044F \u043F\u044B\u043B\u0446\u0430", null, null},
				{"5", "\u041F\u0435\u0440\u0433\u0430", null, null},
				{"6", "\u041C\u0430\u0442\u043E\u0447\u043D\u043E\u0435 \u043C\u043E\u043B\u043E\u0447\u043A\u043E", null, null},
				{"7", "\u0422\u0440\u0443\u0442\u043D\u0435\u0432\u043E \u043C\u043E\u043B\u043E\u0447\u043A\u043E", null, null},
				{"8", "\u041F\u0447\u0435\u043B\u0438\u043D\u044B\u0439 \u044F\u0434", null, null},
				{"9", "\u041F\u0447\u0435\u043B\u0438\u043D\u0430\u044F \u043E\u0433\u043D\u0435\u0432\u043A\u0430", null, null},
				{"10", "\u041F\u0447\u0435\u043B\u0438\u043D\u044B\u0439 \u043F\u043E\u0434\u043C\u043E\u0440", null, null},
			},
			new String[] {
				"\u2116", "\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435 \u0442\u043E\u0432\u0430\u0440\u0430", "\u041E\u0441\u0442\u0430\u0442\u043E\u043A \u043D\u0430 \u0441\u043A\u043B\u0430\u0434\u0435", "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u043E\u0441\u0442\u0443\u043F\u043B\u0435\u043D\u0438\u044F"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableTovar.getColumnModel().getColumn(0).setResizable(false);
		tableTovar.getColumnModel().getColumn(0).setPreferredWidth(26);
		tableTovar.getColumnModel().getColumn(1).setResizable(false);
		tableTovar.getColumnModel().getColumn(1).setPreferredWidth(130);
		tableTovar.getColumnModel().getColumn(2).setResizable(false);
		tableTovar.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableTovar.getColumnModel().getColumn(3).setResizable(false);
		tableTovar.getColumnModel().getColumn(3).setPreferredWidth(150);

		JButton buttonOK = new JButton("\u0412\u044B\u043F\u043E\u043B\u043D\u0438\u0442\u044C");
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Формируем Get-запрос для отправки
				
				String str ="http://y99145px.beget.tech/index.php?id=2"+ makeGet();
				
			
				// Переменная для проверки успешной отправки данных
				boolean flag = true; 
				try {
					// Адрес подулючения
					URL url = new URL(str);
					// HTTP-подключение
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					// Подключаемся
					conn.connect();

					// Поток чтения данных
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					// Получаем строку -ответ
					String inputLine = in.readLine().trim();

					// Проверяес ответную строку
					if (inputLine.indexOf('~') >= 0)
						
					{
						// Меняем флаг на признак успешной передачи
						flag = true;
						// Получаем массив значений
						String[] mas = inputLine.split("~");
						// Перебираем знаения в цикле
						for (int i = 0; i < mas.length; i++) {
							// Помещаем занчения в таблицу
							tableTovar.setValueAt(mas[i], i, 2);

						}
					}
					// Закрываем поток
					in.close();
					// отключаемся
					conn.disconnect();
					conn = null;
				} catch (Exception e) {
				}
				// Если отправка данных не удалась
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "Возможно интернет не подключен", " Ошибка отправки данный", 0);
				}
			}
		});
		buttonOK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonOK.setForeground(Color.GREEN);
		buttonOK.setBounds(216, 247, 122, 23);
		contentPane.add(buttonOK);
	}
}
