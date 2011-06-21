import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CVisual extends JFrame implements ActionListener, KeyListener{
	private JMenuBar menuBarraOpciones;
	private JMenu menuListaEdicion;
	private JMenu menuListaAyuda;
	private JMenuItem menuItemCopiar;
	private JMenuItem menuItemPegar;
	private JMenuItem menuItemAcerca;
	private JTextField txtValor= new JTextField("0",15);
	private JButton[] btnNumeros = new JButton[15];
	private JButton[] btnOperaciones = new JButton[8];
	private JButton[] btnOperacionesControl = new JButton[3];
	private JPanel panelObjetos = new JPanel();
	private JPanel panelPaginaOperaciones = new JPanel();
	private JPanel panelPaginaNumeros= new JPanel();
	private JPanel panelPaginaOperacionesControl = new JPanel();
	private double dval1 = Double.NaN;
	private double dval2 = Double.NaN;
	byte operator = 7;
	byte bOperator = 7;
	
	public CVisual(){
		super("Calculadora");
		addKeyListener(this);
		requestFocus();
		this.setDefaultCloseOperation(3);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		inicializarMenu();
		InicializarObjetosNumeros();
		this.setJMenuBar(menuBarraOpciones);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void inicializarMenu(){
		menuBarraOpciones = new JMenuBar();
		menuListaEdicion = new JMenu("Edición");
		menuListaEdicion.setMnemonic(KeyEvent.VK_E);
		menuListaAyuda = new JMenu("Ayuda");
		menuListaAyuda.setMnemonic(KeyEvent.VK_U);
		menuItemCopiar = new JMenuItem("Copiar",KeyEvent.VK_C);
		menuItemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuItemPegar = new JMenuItem("Pegar",KeyEvent.VK_P);
		menuItemPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		menuItemAcerca = new JMenuItem("Acerca de Calculadora",KeyEvent.VK_A);
		menuItemAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		menuItemAcerca.addActionListener(this);
		menuListaEdicion.add(menuItemCopiar);
		menuListaEdicion.add(menuItemPegar);
		menuBarraOpciones.add(menuListaEdicion);
		menuListaAyuda.add(menuItemAcerca);
		menuBarraOpciones.add(menuListaAyuda);
	}
	
	private void InicializarObjetosNumeros(){
		txtValor.setFocusable(false);
		txtValor.setHorizontalAlignment(JTextField.RIGHT);
		add(txtValor);
		panelObjetos.setLayout(new GridLayout(1,2,6,6));
		panelPaginaOperaciones.setLayout(new GridLayout(4, 2,2,6));
		panelPaginaNumeros.setLayout(new GridLayout(4, 3,6,6));
		panelPaginaOperacionesControl.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnOperaciones[0] = new JButton("/");
		btnOperaciones[1] = new JButton("sqrt");
		btnOperaciones[2] = new JButton("*");
		btnOperaciones[3] = new JButton("%");
		btnOperaciones[4] = new JButton("-");
		btnOperaciones[5] = new JButton("1/x");
		btnOperaciones[6] = new JButton("+");
		btnOperaciones[7] = new JButton("=");
		btnOperacionesControl[0] = new JButton ("Retroceso");
		btnOperacionesControl[1] = new JButton ("CE");
		btnOperacionesControl[2] = new JButton ("C");
		btnNumeros[10] = new JButton("+/-");
		btnNumeros[11] = new JButton(".");
		btnNumeros[10].addActionListener(this);
		btnNumeros[11].addActionListener(this);
		//Inicializar Los Numeros Y Agregarlos A Su Panel
		for(int i= 7; i<=17; i++){
			if (i<10){
				btnNumeros[i] = new JButton(String.valueOf(i));
				panelPaginaNumeros.add(btnNumeros[i]);
				btnNumeros[i].addActionListener(this);
				btnNumeros[i].setFocusable(false);
			}else if(i<13){
				btnNumeros[i-6] = new JButton(String.valueOf(i-6));
				panelPaginaNumeros.add(btnNumeros[i-6]);
				btnNumeros[i-6].addActionListener(this);
				btnNumeros[i-6].setFocusable(false);
			}else if(i<16){
				btnNumeros[i-12] = new JButton(String.valueOf(i-12));
				panelPaginaNumeros.add(btnNumeros[i-12]);
				btnNumeros[i-12].addActionListener(this);
				btnNumeros[i-12].setFocusable(false);
			}else if(i<17){
				btnNumeros[i-i] = new JButton(String.valueOf(i-i));
				panelPaginaNumeros.add(btnNumeros[i-i]);
				btnNumeros[i-i].addActionListener(this);
				btnNumeros[i-i].setFocusable(false);
			}else if(i==17){
				btnNumeros[i-7].setFocusable(false);
				btnNumeros[i-6].setFocusable(false);
				panelPaginaNumeros.add(btnNumeros[i-7]);
				panelPaginaNumeros.add(btnNumeros[i-6]);
			}
		}
		//Agregar las operaciones A Su Panel
		for(int i= 0; i<(btnOperaciones.length); i++){
			btnOperaciones[i].setFocusable(false);
			btnOperaciones[i].addActionListener(this);
			panelPaginaOperaciones.add(btnOperaciones[i]);
		}
		
		for(int i= 0; i<(btnOperacionesControl.length); i++){
			btnOperacionesControl[i].setFocusable(false);
			btnOperacionesControl[i].addActionListener(this);
			panelPaginaOperacionesControl.add(btnOperacionesControl[i]);
		}
		panelObjetos.add(panelPaginaNumeros);
		panelObjetos.add(panelPaginaOperaciones);
		add(panelPaginaOperacionesControl);
		add(panelObjetos);
		add(new JLabel("	"));
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String temp = txtValor.getText();
		double val1;
		//Con matriz Operaciones
		//Funciones de dos Valor
		for(int i=0; i<7; i+=2){
			if (arg0.getSource() == btnOperaciones[i]){ 
				dval1 = Double.parseDouble(txtValor.getText());
				operator = (byte)i;
			}
		}
		//Funciones de Un Valor
		for(int i=1; i<7;i+=2){
			if (arg0.getSource() == btnOperaciones[i]){
				dval2 = Double.parseDouble(txtValor.getText());
				switch (i){
					case 1://sqrt
						dval2 = CLogica.raizCuadrada(dval2);
						break;
					case 3://%
						dval2 =(CLogica.porCiento(dval2));
						break;
					case 5:// 1/x
						dval2 = CLogica.unoEntreX(dval2);
						break;		
				}
				temp = String.valueOf(dval2);
				if ((temp.substring(temp.indexOf(".")+1, temp.length())).equals("0") == true){
					txtValor.setText(String.valueOf((long)dval2));
				}else{
					txtValor.setText(String.valueOf(dval2));
				}
			}
		}
		
		if (arg0.getSource()==btnOperaciones[7]){
			switch (operator){
				case 0:// \
						dval2 = Double.parseDouble(txtValor.getText());
						dval1 = CLogica.division(dval1, dval2);
						bOperator = operator;
						break;
				case 2:// *
						dval2 = Double.parseDouble(txtValor.getText());
						dval1 = CLogica.multiplicacion(dval1, dval2);
						bOperator = operator;
						break;
				case 4:// -
						dval2 = Double.parseDouble(txtValor.getText());
						dval1 = CLogica.resta(dval1, dval2);	
						bOperator = operator;
						break;
				case 6://+
						dval2 = Double.parseDouble(txtValor.getText());						
						dval1 = CLogica.suma(dval1, dval2);
						bOperator = operator;
						break;
				case 7://=
						switch (bOperator){
							case 0:
								dval1 = CLogica.division(dval1, dval2);
								break;
							case 2:
								dval1 = CLogica.multiplicacion(dval1, dval2);
								break;
							case 4:
								dval1 = CLogica.resta(dval1, dval2);						
								break;
							case 6:
								dval1 = CLogica.suma(dval1, dval2);
								break;
							default :
								dval1 = Double.parseDouble(txtValor.getText());
								//System.out.println("Por Aqui Ando Yo");
							}
						break;
			}
			temp = String.valueOf(dval1);
			operator = 7;
			if ((temp.substring(temp.indexOf(".")+1, temp.length())).equals("0") == true){
				txtValor.setText(String.valueOf((long)dval1));
			}else{
				txtValor.setText(String.valueOf(dval1));
			}
		}
		
		//Con matriz Control
		if (arg0.getSource() == btnOperacionesControl[0]){ //Retroceso
			if (temp.equals("0")){
			//nada Porque esta en Cero	
			}else{
				txtValor.setText(temp.substring(0,temp.length() -1));
				temp = txtValor.getText();
				if (temp.equals("")){
					txtValor.setText("0");
				}
			}
		}else if (arg0.getSource() == btnOperacionesControl[1]){ //CE
			txtValor.setText("0");
		}else if (arg0.getSource() == btnOperacionesControl[2]){//C
			dval1 = 0;
			dval2 = 0;
			operator = 7;
			bOperator = 7;
			txtValor.setText("0");
		}
		
		//Con matriz Numero	
		for (int i = 0; i < btnNumeros.length ;i++){ 
			temp = CLogica.anidarComa(txtValor.getText());//normales
			if ((arg0.getSource() == btnNumeros[i]) &&  (i<10)){
				if (operator != 7 && dval1 == Double.parseDouble(txtValor.getText())){
					txtValor.setText("0");
				}
				temp = CLogica.anidarComa(txtValor.getText());//normales
				val1 = Double.parseDouble(txtValor.getText() + btnNumeros[i].getLabel());
				if (temp.equals("")){//si tienes punto trabaja con el double
					txtValor.setText(String.valueOf(val1));
				}else if (temp.equals(".")) { //si no tienes punto trabaja con el long
					txtValor.setText(String.valueOf((long)val1));
				}
			}else if((arg0.getSource() == btnNumeros[i]) &&  (i==10)){ //Mas O Menos						
				val1 = Double.parseDouble(txtValor.getText());
				temp = txtValor.getText();
				if (temp.indexOf(".")==-1){
					txtValor.setText(String.valueOf((long)CLogica.masMenos(val1)));
				}else if ((temp.substring(temp.indexOf("."), temp.length())).equals(".")== true) {
					txtValor.setText(String.valueOf((long)CLogica.masMenos(val1))+".");
				}else {
					txtValor.setText(String.valueOf(CLogica.masMenos(val1)));
				}
			}else if((arg0.getSource() == btnNumeros[i]) &&  (i==11)){//agragar el punto
				txtValor.setText(txtValor.getText() + temp );
			}
		}
		if (arg0.getSource() == menuItemAcerca){
			JOptionPane.showMessageDialog(null, new JLabel("Creada By JMX.",JLabel.CENTER), "Acerca de Calculadora", JOptionPane.DEFAULT_OPTION);
		}
	}

	public void keyPressed(KeyEvent arg0) {}
	
	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {
		double tempD;
		try {
			tempD = (Double.parseDouble(String.valueOf(arg0.getKeyChar())));
		}catch (Exception e){
			tempD = -1;
		}
		if ((tempD < 10) && (tempD >-1)){
			btnNumeros[(int)tempD].doClick();
		}else if ((String.valueOf(arg0.getKeyChar())).equals(".")){
			btnNumeros[11].doClick();
		}else if ((String.valueOf(arg0.getKeyChar())).equals("")){
			btnOperacionesControl[0].doClick();
		}
	}
}
