import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PantallaMuerte extends JPanel {
	private Principal principal;
	private Image semaforo;
	private String tiempo="";
	private JTextField txtFgjdfyujhgfgskgfdsrfdstfyghjygftfgdsfghjk;
	private Image fondo;
	private Image gameover;

	public PantallaMuerte(Principal principal) {
		this.principal=principal;

		setBounds(0, 0, 890, 691);
		setLayout(null);

		fondo=Toolkit.getDefaultToolkit().getImage("./images/carretera.png");
		semaforo=Toolkit.getDefaultToolkit().getImage("./images/semaforo.png");
		gameover=Toolkit.getDefaultToolkit().getImage("./images/gameover.png");
	}

	public void paint(Graphics g){
		g.drawImage(fondo, 0, 0, 840, 650, this);
		g.drawImage(semaforo, 0 , 50, 400, 400, this);
		g.drawImage(gameover, 300 , 50, 400, 400, this);
	}

	public void mostrartiempo(){
		tiempo=principal.getLblCronometro().getText();
		System.out.println(tiempo);
	}
}