import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PantallaRecords extends JPanel {
	private Principal principal;
	private Image semaforo;
	private String tiempo="";
	private Image fondo;
	private Image gameover;
	private JTable table;
	private JTextField txtCghjmdcjhgh;
	
	public PantallaRecords(Principal principal) {
		this.principal=principal;
		
		setBounds(0, 0, 890, 691);
		setLayout(null);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(156, 83, 403, 233);
		add(table);
		
		JLabel lblHola = new JLabel("hola");
		lblHola.setBounds(104, 25, 69, 27);
		add(lblHola);
		
		txtCghjmdcjhgh = new JTextField();
		txtCghjmdcjhgh.setText("cghjmdcjhgh");
		txtCghjmdcjhgh.setBounds(279, 28, 86, 20);
		add(txtCghjmdcjhgh);
		txtCghjmdcjhgh.setColumns(10);
		
		semaforo=Toolkit.getDefaultToolkit().getImage("./images/semaforo.png");
		
	}
	
	public void mostrartiempo(){
		tiempo=principal.getLblCronometro().getText();
		System.out.println(tiempo);
	}
}