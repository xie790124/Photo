

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
		super("史迪奇顯示");
		setTray();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//添加托盤顯示：1.先判斷當前平台是否支持托盤顯示
	public void setTray() {
		if(SystemTray.isSupported()){//判斷當前平台是否支持托盤功能
			//創建托盤實例
			tray = SystemTray.getSystemTray();
			//創建托盤圖標：1.顯示圖標Image 2.停留提示text 3.彈出菜單popupMenu 4.創建托盤圖標實例
			//1.創建Image圖像
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("photo/43.png"));
			//3.彈出菜單popupMenu
			PopupMenu popMenu = new PopupMenu();
			MenuItem itmExit = new MenuItem("退出");
			itmExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					timer.cancel();
					System.exit(0);
				}
			});
			popMenu.add(itmExit);
			//創建托盤圖標
			trayIcon = new TrayIcon(image,"史迪奇顯示",popMenu);
			trayIcon.setImageAutoSize(true);
			//將托盤圖標加到托盤上
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
		final JLabel jLabel2=new JLabel("時間:"+formatDate("yyyy/MM/dd HH:mm:ss",Calendar.getInstance().getTime()));
		jLabel2.setFont(new java.awt.Font("新細明體", 1, 12)); // NOI18N
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
				jLabel2.setText("時間:"+formatDate("yyyy/MM/dd HH:mm:ss",Calendar.getInstance().getTime()));
//				w.setLocation((ran.nextInt(toolkit.getScreenSize().width-250)+1), (ran2.nextInt(toolkit.getScreenSize().height-250)+1));
			}
		};
		long delay = 1000;
		long period = 1000;
		timer.schedule(task, delay, period);
		w.add(jLabel2);
		w.add(jLabel1);
	
		AWTUtilitiesWrapper.setWindowShape(w,
				new Ellipse2D.Double(0, 0, w.getWidth(), w.getHeight()));//視窗形狀
		AWTUtilitiesWrapper.setWindowOpacity(w, 1.0f);//透明度
		w.setVisible(true);
		w.setAlwaysOnTop(true);//置頂
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
