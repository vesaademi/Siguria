import java.awt.EventQueue
;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JFileChooser;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.Color;
import java.awt.Font;


public class Desi extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    Desi frame = new Desi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void encrypt(String key, InputStream is, OutputStream os) throws Exception {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE,is,os);
	}
	public static void decrypt(String key, InputStream is, OutputStream os) throws Exception {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE,is,os);
	}
	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os)throws Exception { 

	DESKeySpec dks=new DESKeySpec(key.getBytes());
	SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
	SecretKey desKey = skf.generateSecret(dks);
	Cipher cipher = Cipher.getInstance("DES"); //DES/ECB/PKCS5Padding
	
	if(mode == Cipher.ENCRYPT_MODE) {
		cipher.init(Cipher.ENCRYPT_MODE, desKey);
		CipherInputStream cis=new CipherInputStream(is,cipher);
		makeFile(cis,os);
	} else if(mode == Cipher.DECRYPT_MODE){
	    cipher.init(Cipher.DECRYPT_MODE, desKey);
		CipherOutputStream cos=new CipherOutputStream(os,cipher);
		makeFile(is,cos);
	}
	}
	
	public static void makeFile(InputStream is, OutputStream os)throws IOException { //ky eshte funksioni i pare
		byte[] bytes = new byte[64];
		int numBytes;
		while((numBytes=is.read(bytes))!=-1){
			os.write(bytes,0,numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	/**
	 * Create the frame.
	 */
	public Desi() {
		setBackground(new Color(255, 255, 255));
		
		
		
		
				setTitle("Enkriptimi dhe dekriptimi");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 523, 378);
				contentPane = new JPanel();
				contentPane.setBackground(new Color(204, 204, 255));
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				JLabel lblNewLabel = new JLabel("Fajlli Origjinal");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel.setBackground(new Color(176, 224, 230));
				lblNewLabel.setBounds(4, 116, 121, 16);
				contentPane.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Fajlli i Enkriptuar/Dekriptuar");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel_1.setForeground(new Color(0, 0, 0));
				lblNewLabel_1.setBounds(4, 186, 183, 16);
				contentPane.add(lblNewLabel_1);
				
				JTextArea textArea = new JTextArea();
				textArea.setBackground(new Color(255, 250, 250));
				textArea.setBounds(31, 133, 437, 44);
				contentPane.add(textArea);
				
				JTextArea textArea_1 = new JTextArea();
				textArea_1.setBackground(new Color(255, 250, 250));
				textArea_1.setBounds(31, 202, 437, 44);
				contentPane.add(textArea_1);
				
				JLabel lblCelesi = new JLabel("CELESI:");
				lblCelesi.setBackground(new Color(0, 0, 0));
				lblCelesi.setFont(new Font("Yu Gothic", Font.BOLD, 11));
				lblCelesi.setBounds(186, 67, 50, 16);
				contentPane.add(lblCelesi);
				
				JTextArea txtCelesi = new JTextArea();
				txtCelesi.setBackground(new Color(255, 250, 250));
				txtCelesi.setBounds(246, 60, 222, 32);
				contentPane.add(txtCelesi);
				
				
				
				JButton btnOpen = new JButton("Hap follderin me fajllin e enkriptuar/dekprituar");
				btnOpen.setBackground(new Color(255, 250, 250));
				btnOpen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Runtime runtime=Runtime.getRuntime();
						 try{
							 runtime.exec("explorer.exe C:\\Users\\loni3\\eclipse-workspace\\ProjektiSiguri");
						 } catch (IOException ex){
							 Logger.getLogger(javax.swing.JFrame.class.getName()).log(Level.SEVERE,null,ex);
						 }
					}
				});
				btnOpen.setBounds(72, 274, 343, 25);
				contentPane.add(btnOpen);
			
				JButton btnDekripto = new JButton("Dekripto");
				btnDekripto.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnDekripto.setBackground(new Color(255, 250, 250));
				btnDekripto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String key=txtCelesi.getText();
						try{
							
							 JFileChooser fc=new JFileChooser();
							 fc.setCurrentDirectory(new File("C:\\Users\\loni3\\eclipse-workspace\\ProjektiSiguri"));
							 fc.showOpenDialog(null);
							 String path = fc.getSelectedFile().getAbsolutePath();
							 textArea.setText(path);
							 File f = fc.getSelectedFile();
							 FileInputStream fis=new FileInputStream(f);
							 FileOutputStream fos=new FileOutputStream("decrypted");
							 Desi.decrypt(key,fis,fos);
							 Thread.sleep(2000);
							 textArea_1.setText("explorer.exe C:\\Users\\loni3\\eclipse-workspace\\ProjektiSiguri\\decrypted");
						}catch(Exception ef)
						{
							System.out.print("Exception in EncryptTXT");
						}
						
					}
				});
				btnDekripto.setBounds(31, 67, 97, 25);
				contentPane.add(btnDekripto);
				
			
				
				JButton btnEnkripto = new JButton("Enkripto");
				btnEnkripto.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnEnkripto.setBackground(new Color(255, 250, 250));
				btnEnkripto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try{
							 String key=txtCelesi.getText();
							 JFileChooser fc=new JFileChooser();
							 fc.showOpenDialog(null);
							 String path = fc.getSelectedFile().getAbsolutePath();
							 textArea.setText(path);
							 File f = fc.getSelectedFile();
							 FileInputStream fis=new FileInputStream(f);
							 FileOutputStream fos=new FileOutputStream("ecrypted");
							 Thread.sleep(2000);
							 Desi.encrypt(key,fis,fos);
							 textArea_1.setText("C:\\Users\\loni3\\eclipse-workspace\\ProjektiSiguri\\ecrypted");
						}catch(Exception e)
						{
							System.out.print("Exception in EncryptTXT");
						}
						
					
						
					}
				});
				btnEnkripto.setBounds(31, 29, 97, 25);
				contentPane.add(btnEnkripto);
				
		
	}
}
