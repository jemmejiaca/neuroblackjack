package view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import entities.Baraja;
import entities.Carta;
import entities.Jugador;
import entities.RNA;
import neural.Neural1H;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	/* Model */
	private Baraja mazo;
	private Jugador dealer;
	private Jugador jugador1; // un solo jugador de momentoO

	/* Hitting/Standing Neural Network */
	public static final int NUM_INPUTS = 6;
	public static final int NUM_HIDDEN = 3;
	public static final int NUM_OUTPUTS = 1;
	private Neural1H neuralNetHS = new Neural1H(NUM_INPUTS, NUM_HIDDEN, NUM_OUTPUTS);

	// private RNA neurona;

	/* Timer */
	private Timer timer;

	/* Images */
	private ImageIcon fondo = new ImageIcon(getClass().getResource("/images/fondo.png"));
	private ImageIcon imgCartas[][];

	/* componentes */
	private JPanel contentPane;
	private JPanel panelDealer;
	private JPanel panelPlayer;
	private JButton btnCardDealer1;
	private JButton btnCardDealer2;
	private JButton btnCardDealer3;
	private JButton btnCardDealer4;
	private JButton btnCardDealer5;
	private JButton btnCardPlayer1;
	private JButton btnCardPlayer2;
	private JButton btnCardPlayer3;
	private JButton btnCardPlayer4;
	private JButton btnCardPlayer5;
	private JButton btnPedirCarta;
	private JButton btnPlantarse;
	private JButton btnNuevoJuego;
	private JTextField textPuntosPlayer;
	private JTextField textPuntosDealer;
	private JLabel lblTuPuntaje;
	private JLabel lblPuntajeDelDealer;
	private JLabel lblEstado;
	private JButton btnAprender;
	private JLabel lblPesosActuales;
	private JLabel lblX1;
	private JLabel lblX2;
	private JLabel lblX3;
	private JLabel lblX4;
	private JLabel lblX5;
	private JLabel lblX6;
	private JTextField textX1;
	private JTextField textX2;
	private JTextField textX3;
	private JTextField textX4;
	private JTextField textX5;
	private JTextField textX6;
	private JLabel lblSugerencia;

	public Window() {
		setTitle("BlackJack UN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logoun.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		imagenesCartas();

		panelDealer = new JPanel();
		panelDealer
				.setBorder(new TitledBorder(null, "Cartas Dealer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDealer.setBounds(164, 52, 542, 165);
		contentPane.add(panelDealer);
		panelDealer.setLayout(null);

		btnCardDealer1 = new JButton("");
		btnCardDealer1.setBounds(10, 25, 95, 129);
		btnCardDealer1.setIcon(fondo);
		panelDealer.add(btnCardDealer1);

		btnCardDealer2 = new JButton("");
		btnCardDealer2.setBounds(115, 25, 95, 129);
		btnCardDealer2.setIcon(fondo);
		panelDealer.add(btnCardDealer2);

		btnCardDealer3 = new JButton("");
		btnCardDealer3.setBounds(220, 25, 95, 129);
		btnCardDealer3.setIcon(fondo);
		panelDealer.add(btnCardDealer3);

		btnCardDealer4 = new JButton("");
		btnCardDealer4.setBounds(325, 25, 95, 129);
		btnCardDealer4.setIcon(fondo);
		panelDealer.add(btnCardDealer4);

		btnCardDealer5 = new JButton("");
		btnCardDealer5.setBounds(430, 25, 95, 129);
		btnCardDealer5.setIcon(fondo);
		panelDealer.add(btnCardDealer5);

		panelPlayer = new JPanel();
		panelPlayer.setBorder(new TitledBorder(null, "Tus cartas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPlayer.setBounds(28, 233, 542, 165);
		contentPane.add(panelPlayer);
		panelPlayer.setLayout(null);

		btnCardPlayer1 = new JButton("");
		btnCardPlayer1.setBounds(10, 25, 95, 129);
		btnCardPlayer1.setIcon(fondo);
		panelPlayer.add(btnCardPlayer1);

		btnCardPlayer2 = new JButton("");
		btnCardPlayer2.setBounds(115, 25, 95, 129);
		btnCardPlayer2.setIcon(fondo);
		panelPlayer.add(btnCardPlayer2);

		btnCardPlayer3 = new JButton("");
		btnCardPlayer3.setBounds(220, 25, 95, 129);
		btnCardPlayer3.setIcon(fondo);
		panelPlayer.add(btnCardPlayer3);

		btnCardPlayer4 = new JButton("");
		btnCardPlayer4.setBounds(325, 25, 95, 129);
		btnCardPlayer4.setIcon(fondo);
		panelPlayer.add(btnCardPlayer4);

		btnCardPlayer5 = new JButton("");
		btnCardPlayer5.setBounds(430, 25, 95, 129);
		btnCardPlayer5.setIcon(fondo);
		panelPlayer.add(btnCardPlayer5);

		textPuntosPlayer = new JTextField();
		textPuntosPlayer.setEditable(false);
		textPuntosPlayer.setBounds(134, 479, 86, 20);
		contentPane.add(textPuntosPlayer);
		textPuntosPlayer.setColumns(10);

		textPuntosDealer = new JTextField();
		textPuntosDealer.setEditable(false);
		textPuntosDealer.setBounds(478, 476, 86, 20);
		contentPane.add(textPuntosDealer);
		textPuntosDealer.setColumns(10);

		lblTuPuntaje = new JLabel("Tu puntaje");
		lblTuPuntaje.setBounds(28, 482, 96, 14);
		contentPane.add(lblTuPuntaje);

		lblPuntajeDelDealer = new JLabel("Puntaje del dealer");
		lblPuntajeDelDealer.setBounds(353, 482, 115, 14);
		contentPane.add(lblPuntajeDelDealer);

		btnPedirCarta = new JButton("Pedir Carta");
		btnPedirCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugador1.pedirCarta(mazo.obtenerCarta());
				switch (jugador1.cartasEnMano()) {
				case 3:
					btnCardPlayer3.setIcon(imgCartas[jugador1.mostarCarta(2)[0]][jugador1.mostarCarta(2)[1] - 1]);
					break;
				case 4:
					btnCardPlayer4.setIcon(imgCartas[jugador1.mostarCarta(3)[0]][jugador1.mostarCarta(3)[1] - 1]);
					break;
				case 5:
					btnCardPlayer5.setIcon(imgCartas[jugador1.mostarCarta(4)[0]][jugador1.mostarCarta(4)[1] - 1]);
					break;
				default:
					System.out.println("Error");
					break;
				}

				textPuntosPlayer.setText(String.valueOf(jugador1.obtenerTotal()));
				// System.out.println(jugador1);

				if (jugador1.obtenerTotal() > 21) {
					btnPedirCarta.setEnabled(false);
					btnPlantarse.setEnabled(false);
					lblEstado.setText("Perdiste, puntaje mayor a 21");
				} else if (jugador1.obtenerTotal() == 21) {
					btnPedirCarta.setEnabled(false);
					btnNuevoJuego.setEnabled(false);
					btnPlantarse.setEnabled(false);
					timer.start();
				} else if (jugador1.obtenerTotal() < 21 && jugador1.cartasEnMano() == 5) {
					btnPedirCarta.setEnabled(false);
					btnNuevoJuego.setEnabled(false);
					btnPlantarse.setEnabled(false);
					timer.start();
				} else {
					System.out.println(jugador1.obtenerTotal());
					System.out.println(dealer.mostarCarta(0)[1]);
					// float entradas[] = {jugador1.obtenerTotal(),
					// dealer.mostarCarta(0)[1],-1};
					// float valorArrojado = neurona.testing(entradas);
					ArrayList<Carta> jugada = jugador1.getJugada();
					float[] inputs = new float[NUM_INPUTS];
					for (int i = 0; i < jugada.size(); ++i)
						inputs[i] = (float) (jugada.get(i).getValor() / 100.0);
					for (int j = jugada.size(); j < NUM_INPUTS - 1; j++)
							inputs[j] = 0.0f;
					inputs[NUM_INPUTS - 1] = dealer.mostarCarta(0)[1];

					float valorArrojado = neuralNetHS.recall(inputs)[0];
					if (valorArrojado < 0.5) {
						System.out.println(valorArrojado);
						lblSugerencia.setText("Sugerencia: Prueba plantandose");
					} else {
						System.out.println(valorArrojado);
						lblSugerencia.setText("Sugerencia: Pruebe pidiendo carta");
					}
				}
			}
		});
		btnPedirCarta.setEnabled(false);
		btnPedirCarta.setBounds(579, 243, 127, 23);
		contentPane.add(btnPedirCarta);

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dealer.pedirCarta(mazo.obtenerCarta());
				// System.out.println(dealer.cartasEnMano());
				switch (dealer.cartasEnMano()) {
				case 2:
					btnCardDealer2.setIcon(imgCartas[dealer.mostarCarta(1)[0]][dealer.mostarCarta(1)[1] - 1]);
					break;
				case 3:
					btnCardDealer3.setIcon(imgCartas[dealer.mostarCarta(2)[0]][dealer.mostarCarta(2)[1] - 1]);
					break;
				case 4:
					btnCardDealer4.setIcon(imgCartas[dealer.mostarCarta(3)[0]][dealer.mostarCarta(3)[1] - 1]);
					break;
				case 5:
					btnCardDealer5.setIcon(imgCartas[dealer.mostarCarta(4)[0]][dealer.mostarCarta(4)[1] - 1]);
					break;
				default:
					System.out.println("Error");
					break;
				}

				textPuntosDealer.setText(String.valueOf(dealer.obtenerTotal()));
				// System.out.println(dealer);

				if (dealer.obtenerTotal() < 17) {
					// seguir pidiendo
					if (dealer.cartasEnMano() == 5) {
						timer.stop();
						if (dealer.obtenerTotal() < jugador1.obtenerTotal()) {
							lblEstado.setText("Ganaste");
						} else if (dealer.obtenerTotal() == jugador1.obtenerTotal()) {
							lblEstado.setText("Empate");
						} else {
							lblEstado.setText("Perdiste");
						}
						btnNuevoJuego.setEnabled(true);
					}
				} else if (dealer.obtenerTotal() > 21) {
					timer.stop();
					btnNuevoJuego.setEnabled(true);
					lblEstado.setText("Ganaste, el dealer tiene puntaje mayor a 21");
				} else {
					// ya tiene mas de 17 y menos de 22
					timer.stop();
					if (dealer.obtenerTotal() < jugador1.obtenerTotal()) {
						lblEstado.setText("Ganaste");
					} else if (dealer.obtenerTotal() == jugador1.obtenerTotal()) {
						lblEstado.setText("Empate");
					} else {
						lblEstado.setText("Perdiste");
					}
					btnNuevoJuego.setEnabled(true);
				}

			}
		});

		btnPlantarse = new JButton("Plantarse");
		btnPlantarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPedirCarta.setEnabled(false);
				btnNuevoJuego.setEnabled(false);
				btnPlantarse.setEnabled(false);
				timer.start();
				
				ArrayList<Carta> jugada = jugador1.getJugada();
				System.out.println("Tamaño de la jugada: " + jugada.size());
				float[] inputs = new float[NUM_INPUTS];
				for (int i = 0; i < jugada.size(); ++i)
					inputs[i] = (float) (jugada.get(i).getValor() / 100.0);
				for (int j = jugada.size(); j < NUM_INPUTS - 1; ++j)
					inputs[j] = 0.0f;
				inputs[NUM_INPUTS - 1] = (float) (dealer.mostarCarta(0)[1] / 100.0);
				for (int i = 0; i < NUM_INPUTS; ++i)
					System.out.print(inputs[i] + ", ");

				float valorArrojado = neuralNetHS.recall(inputs)[0];

				if (valorArrojado < 0.5) {
					System.out.println(valorArrojado);
					lblSugerencia.setText("Sugerencia: Prueba plantandose");
				} else {
					System.out.println(valorArrojado);
					lblSugerencia.setText("Sugerencia: Pruebe pidiendo carta");
				}
			}
		});
		btnPlantarse.setEnabled(false);
		btnPlantarse.setBounds(579, 277, 127, 23);
		contentPane.add(btnPlantarse);

		btnNuevoJuego = new JButton("Nuevo Juego");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltearCartas();
				lblEstado.setText(" ");
				lblSugerencia.setText(" ");

				mazo = new Baraja();
				jugador1 = new Jugador();
				dealer = new Jugador();

				mazo.barajar();
				// mazo.barajarConElitismo();
				// System.out.println(mazo.enLaBaraja());

				jugador1.pedirCarta(mazo.obtenerCarta());
				jugador1.pedirCarta(mazo.obtenerCarta());
				dealer.pedirCarta(mazo.obtenerCarta());

				btnCardPlayer1.setIcon(imgCartas[jugador1.mostarCarta(0)[0]][jugador1.mostarCarta(0)[1] - 1]);
				btnCardPlayer2.setIcon(imgCartas[jugador1.mostarCarta(1)[0]][jugador1.mostarCarta(1)[1] - 1]);
				btnCardDealer1.setIcon(imgCartas[dealer.mostarCarta(0)[0]][dealer.mostarCarta(0)[1] - 1]);

				textPuntosPlayer.setText(String.valueOf(jugador1.obtenerTotal()));
				textPuntosDealer.setText(String.valueOf(dealer.obtenerTotal()));

				btnPedirCarta.setEnabled(true);
				btnPlantarse.setEnabled(true);

				if (jugador1.blackjack()) {
					lblEstado.setText("Tienes Blackjack");
					btnPedirCarta.setEnabled(false);
					btnNuevoJuego.setEnabled(false);
					btnPlantarse.setEnabled(false);
					timer.start();
				} else {
					System.out.println(jugador1.obtenerTotal());
					System.out.println(dealer.mostarCarta(0)[1]);
					// float entradas[] = {jugador1.obtenerTotal(),
					// dealer.mostarCarta(0)[1],-1};
					// float valorArrojado = neurona.testing(entradas);
					ArrayList<Carta> jugada = jugador1.getJugada();
					System.out.println("Tamaño de la jugada: " + jugada.size());
					float[] inputs = new float[NUM_INPUTS];
					for (int i = 0; i < jugada.size(); ++i)
						inputs[i] = (float) (jugada.get(i).getValor() / 100.0);
					for (int j = jugada.size(); j < NUM_INPUTS - 1; ++j)
						inputs[j] = 0.0f;
					inputs[NUM_INPUTS - 1] = (float) (dealer.mostarCarta(0)[1] / 100.0);
					for (int i = 0; i < NUM_INPUTS; ++i)
						System.out.print(inputs[i] + ", ");

					float valorArrojado = neuralNetHS.recall(inputs)[0];

					if (valorArrojado < 0.5) {
						System.out.println(valorArrojado);
						lblSugerencia.setText("Sugerencia: Prueba plantandose");
					} else {
						System.out.println(valorArrojado);
						lblSugerencia.setText("Sugerencia: Pruebe pidiendo carta");
					}

				}
			}
		});
		btnNuevoJuego.setBounds(580, 311, 126, 23);
		contentPane.add(btnNuevoJuego);

		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstado.setBounds(28, 430, 321, 20);
		contentPane.add(lblEstado);

		btnAprender = new JButton("Aprender");
		btnAprender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//neurona = new RNA();
				//neuralNetHS = new Neural1H(NUM_INPUTS, NUM_HIDDEN, NUM_OUTPUTS);

				float[] inExample01 = { 0.07f, 0.03f, 0.05f, 0.02f, 0.0f , 0.08f };
				float[] inExample02 = { 0.04f, 0.02f, 0.04f, 0.03f, 0.01f, 0.1f  };
				float[] inExample03 = { 0.1f , 0.4f , 0.0f , 0.00f, 0.00f, 0.04f };
				float[] inExample04 = { 0.04f, 0.03f, 0.01f, 0.00f, 0.00f, 0.03f };
				float[] inExample05 = { 0.03f, 0.03f, 0.02f, 0.00f, 0.00f, 0.1f  };
				
				float[] outExample01 = { 0.001f };
				float[] outExample02 = { 0.999f };
				float[] outExample03 = { 0.001f };
				float[] outExample04 = { 0.999f };
				float[] outExample05 = { 0.999f };

				//System.out.println("x1: " + String.valueOf(neurona.getPesos()[0]));
				//System.out.println("x2: " + String.valueOf(neurona.getPesos()[1]));
				//System.out.println("x3: " + String.valueOf(neurona.getPesos()[2]));
				
				neuralNetHS.addTrainingExample(inExample01, outExample01);
				neuralNetHS.addTrainingExample(inExample02, outExample02);
				neuralNetHS.addTrainingExample(inExample03, outExample03);
				neuralNetHS.addTrainingExample(inExample04, outExample04);
				neuralNetHS.addTrainingExample(inExample05, outExample05);
				
				//neurona.aprender();
				neuralNetHS.train();
				
				//textX1.setText(String.valueOf(neurona.getPesos()[0]));
				//textX2.setText(String.valueOf(neurona.getPesos()[1]));
				//textX3.setText(String.valueOf(neurona.getPesos()[2]));
			}
		});
		btnAprender.setBounds(580, 345, 126, 23);
		contentPane.add(btnAprender);

		lblPesosActuales = new JLabel("Pesos Actuales");
		lblPesosActuales.setBounds(10, 52, 127, 14);
		contentPane.add(lblPesosActuales);

		lblX1 = new JLabel("x1");
		lblX1.setBounds(10, 77, 46, 14);
		contentPane.add(lblX1);

		lblX2 = new JLabel("x2");
		lblX2.setBounds(10, 102, 46, 14);
		contentPane.add(lblX2);

		lblX3 = new JLabel("x3");
		lblX3.setBounds(10, 127, 46, 14);
		contentPane.add(lblX3);

		lblX4 = new JLabel("x4");
		lblX4.setBounds(10, 152, 46, 14);
		contentPane.add(lblX4);

		lblX5 = new JLabel("x5");
		lblX5.setBounds(10, 177, 46, 14);
		contentPane.add(lblX5);

		lblX6 = new JLabel("x6");
		lblX6.setBounds(10, 202, 46, 14);
		contentPane.add(lblX6);

		textX1 = new JTextField();
		textX1.setEditable(false);
		textX1.setBounds(51, 77, 86, 20);
		contentPane.add(textX1);
		textX1.setColumns(10);

		textX2 = new JTextField();
		textX2.setEditable(false);
		textX2.setColumns(10);
		textX2.setBounds(51, 102, 86, 20);
		contentPane.add(textX2);

		textX3 = new JTextField();
		textX3.setEditable(false);
		textX3.setColumns(10);
		textX3.setBounds(51, 127, 86, 20);
		contentPane.add(textX3);

		textX4 = new JTextField();
		textX4.setEditable(false);
		textX4.setColumns(10);
		textX4.setBounds(51, 152, 86, 20);
		contentPane.add(textX4);

		textX5 = new JTextField();
		textX5.setEditable(false);
		textX5.setColumns(10);
		textX5.setBounds(51, 177, 86, 20);
		contentPane.add(textX5);

		textX6 = new JTextField();
		textX6.setEditable(false);
		textX6.setColumns(10);
		textX6.setBounds(51, 202, 86, 20);
		contentPane.add(textX6);

		lblSugerencia = new JLabel("");
		lblSugerencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSugerencia.setBounds(385, 430, 321, 20);
		contentPane.add(lblSugerencia);
	}

	/* LLenado del arreglo de imagenes */
	public void imagenesCartas() {
		imgCartas = new ImageIcon[4][13];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				imgCartas[i][j] = new ImageIcon(
						getClass().getResource("/images/" + String.valueOf(j + 1) + "_" + String.valueOf(i) + ".png"));
			}
		}

	}

	public void voltearCartas() {
		btnCardPlayer1.setIcon(fondo);
		btnCardPlayer2.setIcon(fondo);
		btnCardPlayer3.setIcon(fondo);
		btnCardPlayer4.setIcon(fondo);
		btnCardPlayer5.setIcon(fondo);

		btnCardDealer1.setIcon(fondo);
		btnCardDealer2.setIcon(fondo);
		btnCardDealer3.setIcon(fondo);
		btnCardDealer4.setIcon(fondo);
		btnCardDealer5.setIcon(fondo);
	}

	/* Getters de los componentes */

	public JButton getBtnCardDealer1() {
		return btnCardDealer1;
	}

	public JPanel getPanelDealer() {
		return panelDealer;
	}

	public JPanel getPanel() {
		return panelPlayer;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnCardDealer3() {
		return btnCardDealer3;
	}

	public JButton getBtnCardDealer4() {
		return btnCardDealer4;
	}

	public JButton getBtnCardDealer5() {
		return btnCardDealer5;
	}

	public JButton getBtnCardPlayer1() {
		return btnCardPlayer1;
	}

	public JButton getBtnCardPlayer2() {
		return btnCardPlayer2;
	}

	public JButton getBtnCardPlayer3() {
		return btnCardPlayer3;
	}

	public JButton getBtnCardPlayer4() {
		return btnCardPlayer4;
	}

	public JButton getBtnCardPlayer5() {
		return btnCardPlayer5;
	}

	public JButton getBtnCardDealer2() {
		return btnCardDealer2;
	}

	public JButton getBtnPedirCarta() {
		return btnPedirCarta;
	}

	public JButton getBtnPlantarse() {
		return btnPlantarse;
	}

	public JButton getBtnNuevoJuego() {
		return btnNuevoJuego;
	}

	public JLabel getLblTuPuntaje() {
		return lblTuPuntaje;
	}

	public JTextField getTextPuntosPlayer() {
		return textPuntosPlayer;
	}

	public JLabel getLblPuntajeDelDealer() {
		return lblPuntajeDelDealer;
	}

	public JTextField getTextPuntosDealer() {
		return textPuntosDealer;
	}

	public JButton getBtnAprender() {
		return btnAprender;
	}

	public JLabel getLblPesosActuales() {
		return lblPesosActuales;
	}

	public JLabel getLblX1() {
		return lblX1;
	}

	public void setLblX1(JLabel lblX1) {
		this.lblX1 = lblX1;
	}

	public JLabel getLblX2() {
		return lblX2;
	}

	public void setLblX2(JLabel lblX2) {
		this.lblX2 = lblX2;
	}

	public JLabel getLblX3() {
		return lblX3;
	}

	public void setLblX3(JLabel lblX3) {
		this.lblX3 = lblX3;
	}

	public JLabel getLblX4() {
		return lblX4;
	}

	public void setLblX4(JLabel lblX4) {
		this.lblX4 = lblX4;
	}

	public JLabel getLblX5() {
		return lblX5;
	}

	public void setLblX5(JLabel lblX5) {
		this.lblX5 = lblX5;
	}

	public JLabel getLblX6() {
		return lblX6;
	}

	public void setLblX6(JLabel lblX6) {
		this.lblX6 = lblX6;
	}

	public JTextField getTextX1() {
		return textX1;
	}

	public void setTextX1(JTextField textX1) {
		this.textX1 = textX1;
	}

	public JTextField getTextX2() {
		return textX2;
	}

	public void setTextX2(JTextField textX2) {
		this.textX2 = textX2;
	}

	public JTextField getTextX3() {
		return textX3;
	}

	public void setTextX3(JTextField textX3) {
		this.textX3 = textX3;
	}

	public JTextField getTextX4() {
		return textX4;
	}

	public void setTextX4(JTextField textX4) {
		this.textX4 = textX4;
	}

	public JTextField getTextX5() {
		return textX5;
	}

	public void setTextX5(JTextField textX5) {
		this.textX5 = textX5;
	}

	public JTextField getTextX6() {
		return textX6;
	}

	public void setTextX6(JTextField textX6) {
		this.textX6 = textX6;
	}

	public JLabel getLblSugerencia() {
		return lblSugerencia;
	}
}
