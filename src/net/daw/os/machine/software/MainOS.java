package net.daw.os.machine.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.awt.event.MouseAdapter;





public class MainOS extends JFrame {

	private JPanel contentPane;
	JInternalFrame internalFrame_1;
    WebView webView;
    WebEngine webEngine;
    private JTextField textField;
    private BufferedImage img;
    JDesktopPane desktopPane;
    private JTextField textField_1;
    private JTextField textField_2;
    JButton writebtn;
    JButton mewchatbtn;
    JButton dropkickbtn;
    JTextArea textArea_1;
    private String name = "";
	private String message = "";
	private BufferedReader br;
	private PrintWriter pw;
	private Socket sock;
	private JTextField textField_3;
	JTextArea chatArea;
	private JTextField textField_4;

	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}
					MainOS frame = new MainOS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainOS() throws PropertyVetoException {
		setAlwaysOnTop(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0)  {
				setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 300);
		setUndecorated(true);
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnStart = new JMenu("Start");
		menuBar_1.add(mnStart);
		
	
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane(){
			@Override
            protected void paintComponent(Graphics grphcs) {
               super.paintComponent(grphcs);
               grphcs.drawImage(img, 0, 0, null);

            }
		};
		desktopPane.setBorder(null);
		

		
		
		desktopPane.setForeground(Color.BLACK);
		
		JInternalFrame MewChat = new JInternalFrame("MewChat");
		MewChat.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/chat-bubbles-icon.png")));
		MewChat.setIconifiable(true);
		MewChat.setMaximizable(true);
		MewChat.setResizable(true);
		MewChat.setClosable(true);
		MewChat.setBounds(274, 326, 473, 335);
		desktopPane.add(MewChat);
		
		JMenuBar menuBar = new JMenuBar();
		MewChat.setJMenuBar(menuBar);
		
		textField_3 = new JTextField();
		textField_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				write(e.getActionCommand());
				textField_3.setText("");
			}
		});
		MewChat.getContentPane().add(textField_3, BorderLayout.SOUTH);
		textField_3.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		MewChat.getContentPane().add(scrollPane_2, BorderLayout.CENTER);
		
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		scrollPane_2.setViewportView(chatArea);
		
		JInternalFrame internalFrame = new JInternalFrame("WordFactory");
		internalFrame.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/Apps-notes-icon.png")));
		internalFrame.setClosable(true);
		internalFrame.setResizable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(10, 11, 252, 185);
		desktopPane.add(internalFrame);
		
		JMenuBar menuBar_2 = new JMenuBar();
		internalFrame.setJMenuBar(menuBar_2);
		
		JMenu mnFile_1 = new JMenu("File");
		menuBar_2.add(mnFile_1);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
        final JFXPanel fxPanel = new JFXPanel();
        
		internalFrame_1 = new JInternalFrame("DropKick Browser");
		internalFrame_1.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/karate-punch-icon.png")));
		internalFrame_1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
		        webView.setPrefSize(internalFrame_1.getWidth(),internalFrame_1.getHeight());
		        webView.setMinSize(internalFrame_1.getWidth(),internalFrame_1.getHeight() );
		        webView.autosize();
			}
		});

		internalFrame_1.setClosable(true);
		internalFrame_1.setResizable(true);
		internalFrame_1.setMaximizable(true);
		internalFrame_1.setIconifiable(true);
		internalFrame_1.setBounds(10, 206, 252, 206);
		desktopPane.add(internalFrame_1);
		internalFrame_1.getContentPane().add(fxPanel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Platform.runLater(new Runnable(){
					@Override
					public void run(){
						webEngine.load(e.getActionCommand());

					}
				});
			}
		});
		internalFrame_1.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JInternalFrame bg = new JInternalFrame("Set Background");
		bg.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/background-icon.png")));
		bg.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		bg.setIconifiable(true);
		bg.setClosable(true);
		bg.setBounds(10, 424, 252, 77);
		desktopPane.add(bg);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			JInternalFrame ex = new JInternalFrame("File Excavator ");
			ex.setResizable(true);
			ex.setMaximizable(true);
			ex.setIconifiable(true);
			ex.setClosable(true);
			ex.setBounds(274, 326, 475, 338);
			desktopPane.add(ex);
			JFileChooser jfc = new JFileChooser();
			jfc.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					String command = event.getActionCommand();
					if(command.equals(JFileChooser.CANCEL_SELECTION)){
						ex.setVisible(false);
					}else if (command.equals(JFileChooser.APPROVE_SELECTION)){
						File f = new File(jfc.getSelectedFile().getAbsolutePath());
						textField_1.setText(f.getAbsolutePath().toString());
						ex.dispose();
					
					}
				}
			});
			
			ex.getContentPane().add(jfc);
			ex.setVisible(true);
			}
		});
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bg.getContentPane().add(textField_1, BorderLayout.NORTH);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Set Background");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					img = ImageIO.read(new File(textField_1.getText()));
		            desktopPane.repaint();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		bg.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
		dropkickbtn = new JButton("");
		dropkickbtn.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				dropkickbtn.setLocation(arg0.getXOnScreen()-45, arg0.getYOnScreen()-45);
			}
		});
		dropkickbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_1.setVisible(true);

			}
		});
		dropkickbtn.setIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/karate-punch-icon (1).png")));
		dropkickbtn.setBounds(1571, 11, 89, 58);
		desktopPane.add(dropkickbtn);
		
		writebtn = new JButton("");
		writebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(true);

			}
		});
		writebtn.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				writebtn.setLocation(arg0.getXOnScreen()-45, arg0.getYOnScreen()-45);

			}
		});
		writebtn.setIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/Apps-notes-icon (1).png")));
		writebtn.setBounds(1571, 80, 89, 58);
		desktopPane.add(writebtn);
		
		
		
		JInternalFrame cmd = new JInternalFrame("Command Runner");
		cmd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		cmd.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/App-konsole-icon.png")));
		cmd.setResizable(true);
		cmd.setIconifiable(true);
		cmd.setClosable(true);
		cmd.setBounds(272, 11, 475, 303);
		desktopPane.add(cmd);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equalsIgnoreCase("loadDesktopIcons.1")){
					mewchatbtn.setVisible(true);
					writebtn.setVisible(true);
					dropkickbtn.setVisible(true);
					textField_2.setText("");
					textArea_1.append("Enabling Desktop Icons\n");
				}else if(e.getActionCommand().equalsIgnoreCase("loadDesktopIcons.0")){
					mewchatbtn.setVisible(false);
					writebtn.setVisible(false);
					dropkickbtn.setVisible(false);
					textField_2.setText("");
					textArea_1.append("Disabling Desktop Icons\n");

				}else{
					//TODO check for more codes
				}
			}
		});
		cmd.getContentPane().add(textField_2, BorderLayout.SOUTH);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		cmd.getContentPane().add(scrollPane_1, BorderLayout.CENTER);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		scrollPane_1.setViewportView(textArea_1);
		
		JInternalFrame ex = new JInternalFrame("File Excavator ");
		ex.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/File-Downloads-icon.png")));
		ex.setResizable(true);
		ex.setMaximizable(true);
		ex.setIconifiable(true);
		ex.setClosable(true);
		ex.setBounds(21, 673, 475, 338);
		desktopPane.add(ex);
		Platform.runLater(new Runnable() { // this will run initFX as JavaFX-Thread
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });
		
		JMenu mnFile = new JMenu("MewChat");
		menuBar.add(mnFile);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);

		
		JMenuItem mntmMewchat = new JMenuItem("MewChat");
		mntmMewchat.setIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/chat-bubbles-icon.png")));
		mntmMewchat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame MewChat = new JInternalFrame("MewChat");
				MewChat.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/chat-bubbles-icon.png")));
				MewChat.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				MewChat.setIconifiable(true);
				MewChat.setMaximizable(true);
				MewChat.setResizable(true);
				MewChat.setClosable(true);
				MewChat.setBounds(274, 326, 473, 335);
				desktopPane.add(MewChat);
				
				JMenuBar menuBar = new JMenuBar();
				MewChat.setJMenuBar(menuBar);
				
				textField_3 = new JTextField();
				textField_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						write(e.getActionCommand());
						textField_3.setText("");
					}
				});
				MewChat.getContentPane().add(textField_3, BorderLayout.SOUTH);
				textField_3.setColumns(10);
				
				JScrollPane scrollPane_2 = new JScrollPane();
				MewChat.getContentPane().add(scrollPane_2, BorderLayout.CENTER);
				
				chatArea = new JTextArea();
				chatArea.setEditable(false);
				scrollPane_2.setViewportView(chatArea);
				MewChat.setVisible(true);
			}
		});
		mnStart.add(mntmMewchat);
		
		JMenuItem mntmWordFactory = new JMenuItem("Word Factory");
		mntmWordFactory.setIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/Apps-notes-icon.png")));
		mntmWordFactory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame internalFrame = new JInternalFrame("WordFactory");
				internalFrame.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/Apps-notes-icon.png")));
				internalFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				internalFrame.setClosable(true);
				internalFrame.setResizable(true);
				internalFrame.setMaximizable(true);
				internalFrame.setIconifiable(true);
				internalFrame.setBounds(10, 11, 252, 185);
				desktopPane.add(internalFrame);
				internalFrame.setVisible(true);

				}
		});
		mnStart.add(mntmWordFactory);
		
		JMenuItem mntmDropkick = new JMenuItem("DropKick");
		mntmDropkick.setIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/karate-punch-icon.png")));
		mntmDropkick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextArea textArea = new JTextArea();
				scrollPane.setViewportView(textArea);
		        final JFXPanel fxPanel = new JFXPanel();
		        
				internalFrame_1 = new JInternalFrame("DropKick Browser");
				internalFrame_1.setFrameIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/karate-punch-icon.png")));
				internalFrame_1.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent arg0) {
				        webView.setPrefSize(internalFrame_1.getWidth(),internalFrame_1.getHeight());
				        webView.setMinSize(internalFrame_1.getWidth(),internalFrame_1.getHeight() );
				        webView.autosize();
					}
				});

				internalFrame_1.setClosable(true);
				internalFrame_1.setResizable(true);
				internalFrame_1.setMaximizable(true);
				internalFrame_1.setIconifiable(true);
				internalFrame_1.setBounds(10, 206, 252, 206);
				desktopPane.add(internalFrame_1);
				internalFrame_1.getContentPane().add(fxPanel);
				
				textField = new JTextField();
				textField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Platform.runLater(new Runnable(){
							@Override
							public void run(){
								webEngine.load(e.getActionCommand());

							}
						});
					}
				});
				internalFrame_1.getContentPane().add(textField, BorderLayout.NORTH);
				textField.setColumns(10);
				internalFrame_1.setVisible(true);
				Platform.runLater(new Runnable() { // this will run initFX as JavaFX-Thread
		            @Override
		            public void run() {
		                initFX(fxPanel);
		            }
		        });
			}
		});
		mnStart.add(mntmDropkick);
		
		JSeparator separator = new JSeparator();
		mnStart.add(separator);
		
		JMenuItem mntmSetBackground = new JMenuItem("Set BackGround");
		mntmSetBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bg.setVisible(true);
			}
		});
		
		mewchatbtn = new JButton("");
		mewchatbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MewChat.setVisible(true);
			}
		});
		mewchatbtn.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				mewchatbtn.setLocation(arg0.getXOnScreen()-45, arg0.getYOnScreen()-45);

			}
		});
		mewchatbtn.setIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/chat-bubbles-icon (1).png")));
		mewchatbtn.setBounds(1571, 149, 89, 58);
		desktopPane.add(mewchatbtn);
		
		JInternalFrame internalFrame_2 = new JInternalFrame("Set Your Name");
		internalFrame_2.setBounds(20, 513, 242, 58);
		desktopPane.add(internalFrame_2);
		
		textField_4 = new JTextField();
		textField_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = e.getActionCommand();
				internalFrame_2.dispose();
			}
		});
		internalFrame_2.getContentPane().add(textField_4, BorderLayout.CENTER);
		textField_4.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 1676, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
					.addGap(0))
		);
		
		Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
		JInternalFrame Micron = new JInternalFrame("Micron Browser");
		Micron.setResizable(true);
		Micron.setMaximizable(true);
		Micron.setIconifiable(true);
		Micron.setClosable(true);
		Micron.setBounds(508, 673, 242, 338);
		Micron.add(browserView, BorderLayout.CENTER);
		desktopPane.add(Micron);
		Micron.setVisible(true);
		contentPane.setLayout(gl_contentPane);
		browser.loadURL("http://www.google.com/");
		
		JMenuItem mntmConnect = new JMenuItem("Connect");
		mntmConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_2.setVisible(true);
				sock = new Socket();
				try {
					sock.connect(new InetSocketAddress("65.185.25.166",777), 10000);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pw = new PrintWriter(sock.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				read();
			}
		});
		mnFile.add(mntmConnect);
		
		JMenuItem mntmSaveFile = new JMenuItem("Save File");
		mntmSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame ex = new JInternalFrame("File Excavator ");
				ex.setResizable(true);
				ex.setMaximizable(true);
				ex.setIconifiable(true);
				ex.setClosable(true);
				ex.setBounds(274, 326, 475, 338);
				desktopPane.add(ex);
				JFileChooser jfc = new JFileChooser();
				jfc.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						String command = event.getActionCommand();
						if(command.equals(JFileChooser.CANCEL_SELECTION)){
							ex.setVisible(false);
						}else if (command.equals(JFileChooser.APPROVE_SELECTION)){
							File f = new File(jfc.getSelectedFile().getAbsolutePath());
							try {
								textArea.write(new FileWriter(f));
								ex.dispose();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}
					}
				});
				
				ex.getContentPane().add(jfc);
				ex.setVisible(true);
				
				
			}
		});
		mnFile_1.add(mntmSaveFile);
		
		JMenuItem mntmOpenFikle = new JMenuItem("Open File");
		mntmOpenFikle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame ex = new JInternalFrame("File Excavator ");
				ex.setResizable(true);
				ex.setMaximizable(true);
				ex.setIconifiable(true);
				ex.setClosable(true);
				ex.setBounds(274, 326, 475, 338);
				desktopPane.add(ex);
				JFileChooser jfc = new JFileChooser();
				jfc.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						String command = event.getActionCommand();
						if(command.equals(JFileChooser.CANCEL_SELECTION)){
							ex.setVisible(false);
						}else if (command.equals(JFileChooser.APPROVE_SELECTION)){
							File f = new File(jfc.getSelectedFile().getAbsolutePath());
							try {
								textArea.read(new FileReader(f), "Reading a doc");
								ex.dispose();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}
					}
				});
				
				ex.getContentPane().add(jfc);
				ex.setVisible(true);
			}
		});
		mnFile_1.add(mntmOpenFikle);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Command Runner");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmd.setVisible(true);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainOS.class.getResource("/net/daw/os/machine/software/App-konsole-icon.png")));
		mnStart.add(mntmNewMenuItem);
		mnStart.add(mntmSetBackground);
	}
	
	private void initFX(final JFXPanel fxPanel) {
        Group group = new Group();
        Scene scene = new Scene(group);
        fxPanel.setScene(scene);

        webView = new WebView();
        
        group.getChildren().add(webView);
        
        webView.setMaxSize(internalFrame_1.getWidth(),internalFrame_1.getHeight());
        webView.setPrefSize(internalFrame_1.getWidth(),internalFrame_1.getHeight());
        webView.setMinSize(internalFrame_1.getWidth(),internalFrame_1.getHeight() );
        webView.autosize();
        webEngine = webView.getEngine();
        webEngine.load("http://www.google.com/");
        webEngine.setJavaScriptEnabled(true);
        
        
    }
	
	public void loadBackground(String URL){
		try {
			img = ImageIO.read(new URL(URL));
			Graphics g = desktopPane.getGraphics();
			g.drawImage(img, 0, 0, desktopPane.getWidth(),desktopPane.getHeight(),null);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	 public void walk( String path ) { 

	        File root = new File( path ); 
	        File[] list = root.listFiles(); 

	        for ( File f : list ) { 
	            if ( f.isDirectory() ) { 
	                walk( f.getAbsolutePath() ); 
	                
	                System.err.println( "Dir:" + f.getAbsoluteFile() ); 
	            } 
	            else { 
	                System.err.println( "File:" + f.getAbsoluteFile() ); 
	            } 
	        } 
	    } 
	 
	 public void read(){
		 new Thread(new Runnable(){public void run(){
				try{
					while((message = br.readLine()) !=null){
						if(!message.equals("PING")){
							chatArea.append(message+"\n");
						}
					}
				}catch(Exception ex){}
			}}).start();
	 }
	 
	 public void write(String packet){
			pw.println("["+name+"]: "+packet);
			pw.flush();
		}
}
