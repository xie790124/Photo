

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame{
	static java.util.Timer timer = new java.util.Timer(true);
	TrayIcon trayIcon;
	SystemTray tray;
	public Main(){
		super("�v�}�_���");
		setTray();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//�K�[���L��ܡG1.���P�_��e���x�O�_������L���
	public void setTray() {
		if(SystemTray.isSupported()){//�P�_��e���x�O�_������L�\��
			//�Ыئ��L���
			tray = SystemTray.getSystemTray();
			//�Ыئ��L�ϼСG1.��ܹϼ�Image 2.���d����text 3.�u�X���popupMenu 4.�Ыئ��L�ϼй��
			//1.�Ы�Image�Ϲ�
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("photo/43.png"));
			//3.�u�X���popupMenu
			PopupMenu popMenu = new PopupMenu();
			MenuItem itmExit = new MenuItem("�h�X");
			itmExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					timer.cancel();
					System.exit(0);
				}
			});
			popMenu.add(itmExit);
			//�Ыئ��L�ϼ�
			trayIcon = new TrayIcon(image,"�v�}�_���",popMenu);
			trayIcon.setImageAutoSize(true);
			//�N���L�ϼХ[�즫�L�W
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
		 ClassLoader cl=this.getClass().getClassLoader();
		this.setIconImage(this.getToolkit().getImage(cl.getResource("photo/43.png"))); 
	}
	public static void main(String args[]){
		final Window w = new Main();
		
		final JLabel jLabel1=new JLabel();
		final JLabel jLabel2=new JLabel("�ɶ�:"+formatDate("yyyy/MM/dd HH:mm:ss",Calendar.getInstance().getTime()));
		jLabel2.setFont(new java.awt.Font("�s�ө���", 1, 12)); // NOI18N
		jLabel2.setForeground(Color.RED);
		  w.setLocationRelativeTo(null); 
//		jLabel1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jLabel1.setBounds(30,30,300,300);
		jLabel2.setBounds(20,180,300,30);
		((JFrame) w).setUndecorated(true);
		((JFrame) w).setBackground(new Color(0,0,0,0));
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = toolkit.getScreenSize().width-330 ;
		int y = toolkit.getScreenSize().height -240;
		w.setSize(330, 200);
		w.setLocation(0, y);
		final ClassLoader cl=w.getClass().getClassLoader();
		jLabel1.setIcon(new ImageIcon(cl.getResource("photo/2.gif")));
		java.util.TimerTask task = new java.util.TimerTask(){
			@Override
			public void run() {
				Random ran = new Random();
				Random ran2 = new Random();
				Random ran3 = new Random();
//				jLabel1.setIcon(new ImageIcon(cl.getResource("photo/1.gif")));
				jLabel2.setText("�ɶ�:"+formatDate("yyyy/MM/dd HH:mm:ss",Calendar.getInstance().getTime()));
//				w.setLocation((ran.nextInt(toolkit.getScreenSize().width-250)+1), (ran2.nextInt(toolkit.getScreenSize().height-250)+1));
			}
		};
		long delay = 1000;
		long period = 1000;
		timer.schedule(task, delay, period);
		w.add(jLabel2);
		w.add(jLabel1);
	
		AWTUtilitiesWrapper.setWindowShape(w,
				new Ellipse2D.Double(0, 0, w.getWidth(), w.getHeight()));//�����Ϊ�
		AWTUtilitiesWrapper.setWindowOpacity(w, 1.0f);//�z����
		w.setVisible(true);
		w.setAlwaysOnTop(true);//�m��
	}
	public static String formatDate(String pettern,Date date){
		String strDate = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(pettern); 
			strDate = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}   
}
