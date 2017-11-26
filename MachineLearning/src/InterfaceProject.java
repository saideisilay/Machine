import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.List;
import weka.core.Instances;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.TextArea;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class InterfaceProject extends JFrame {

	/**
	 * Interface i
	 */
	private static final long serialVersionUID = -2435137644007794100L;
	private JPanel contentPane;
	private JTextField readFileText;
	private JTextField readTestText;
	private TextArea txtLinearResult;
	private TextArea txtIbk;
	private TextArea txtSmo;
	private TextArea txtRandomTree;
	private TextArea txtRandFore;
	private JTextField txtNeighbor;
	private TextArea txtSgd;

	MachineLearning fonksiyon = new MachineLearning();
	Instances trainDataSet, testDataSet, instances;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// System.clearProperty("java.classpath");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceProject frame = new InterfaceProject();
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
	public InterfaceProject() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JTabbedPane ChooseSets = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Getting Data Sets", null, ChooseSets, null);

		JPanel panel_1 = new JPanel();
		ChooseSets.addTab("Data Sets", null, panel_1, null);
		panel_1.setLayout(null);

		JButton btnChooseFile = new JButton("Choose Train Dataset");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readFileText.setText(FileReaderFunction());
				try {
					trainDataSet = loadInstances(readFileText.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnChooseFile.setBounds(10, 43, 163, 22);
		panel_1.add(btnChooseFile);

		readFileText = new JTextField();
		readFileText.setBounds(187, 43, 460, 22);
		panel_1.add(readFileText);
		readFileText.setColumns(10);

		JButton btnChooseTestDataset = new JButton("Choose Test Dataset");
		btnChooseTestDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readTestText.setText(FileReaderFunction());
				try {
					testDataSet = loadInstances(readTestText.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		btnChooseTestDataset.setBounds(10, 88, 163, 22);
		panel_1.add(btnChooseTestDataset);

		readTestText = new JTextField();
		readTestText.setBounds(187, 89, 460, 20);
		panel_1.add(readTestText);
		readTestText.setColumns(10);

		JTextArea txtTrain = new JTextArea();
		txtTrain.setBounds(78, 136, 559, 160);
		panel_1.add(txtTrain);

		JTextArea txtTest = new JTextArea();
		txtTest.setBounds(78, 325, 559, 160);
		panel_1.add(txtTest);

		JButton btnGetData = new JButton("Get Summary");
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					trainDataSet = loadInstances(readTestText.getText());

					testDataSet = loadInstances(readTestText.getText());

					String dataTrain = trainDataSet.toSummaryString();
					txtTrain.setText(dataTrain);
					txtTrain.getText();

					String dataTest = testDataSet.toSummaryString();
					txtTest.setText(dataTest);
					txtTest.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnGetData.setBounds(650, 65, 116, 23);
		panel_1.add(btnGetData);

		JTabbedPane LinearReg = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Linear Regression", null, LinearReg, null);

		JLayeredPane layeredPane_4 = new JLayeredPane();
		LinearReg.addTab("Results", null, layeredPane_4, null);

		JButton btnPredict = new JButton("Predict");
		btnPredict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					List output = fonksiyon.linear_Regression(trainDataSet, testDataSet);

					String res = " ";
					for (int j = 0; j < output.getItems().length; j++) {
						res += output.getItem(j);
					}

					txtLinearResult.setText(res);
					txtLinearResult.getText();
				}

				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		});
		btnPredict.setBounds(10, 11, 89, 23);
		layeredPane_4.add(btnPredict);

		txtLinearResult = new TextArea();
		txtLinearResult.setBounds(10, 51, 738, 435);
		layeredPane_4.add(txtLinearResult);

		JLayeredPane IBK = new JLayeredPane();
		tabbedPane.addTab("IBK", null, IBK, null);

		JButton btnPredickIbk = new JButton("Predict");
		btnPredickIbk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int neighbor= Integer.parseInt(txtNeighbor.getText()); //burda kaldým
				 try {
					 List outputIBK = fonksiyon.IBK_Regression(trainDataSet,testDataSet,neighbor);


					String res = " ";
					for (int j = 0; j < outputIBK.getItems().length; j++) {
						res += outputIBK.getItem(j);
					}

					txtIbk.setText(res);
					txtIbk.getText();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPredickIbk.setBounds(196, 11, 89, 23);
		IBK.add(btnPredickIbk);
		
		JLabel lblChooseNeighbors = new JLabel("Choose neighbors");
		lblChooseNeighbors.setBounds(10, 15, 90, 14);
		IBK.add(lblChooseNeighbors);
		
		txtNeighbor = new JTextField();
		txtNeighbor.setBounds(100, 12, 86, 20);
		IBK.add(txtNeighbor);
		txtNeighbor.setColumns(10);
		
		txtIbk = new TextArea();
		txtIbk.setBounds(10, 56, 761, 441);
		IBK.add(txtIbk);

		JLayeredPane SMO_Regression = new JLayeredPane();
		tabbedPane.addTab("SMO Regression", null, SMO_Regression, null);
		SMO_Regression.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		SMO_Regression.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Predict");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List output;
				try {
					output = fonksiyon.smo_Regression(trainDataSet, testDataSet);
					String res = " ";
					for (int j = 0; j < output.getItems().length; j++) {
						res += output.getItem(j);
					}

					txtSmo.setText(res);
					txtSmo.getText();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		});
		btnNewButton.setBounds(10, 28, 89, 23);
		panel.add(btnNewButton);
		
		txtSmo = new TextArea();
		txtSmo.setBounds(10, 57, 761, 441);
		panel.add(txtSmo);

		JLayeredPane RandomTree = new JLayeredPane();
		tabbedPane.addTab("Random Tree", null, RandomTree, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 771, 525);
		RandomTree.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnPre = new JButton("Predict");
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					List output = fonksiyon.RandomTree_Regression(trainDataSet, testDataSet);

					String res = " ";
					for (int j = 0; j < output.getItems().length; j++) {
						res += output.getItem(j);
					}

					txtRandomTree.setText(res);
					txtRandomTree.getText();
				}

				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
				
			}
		});
		btnPre.setBounds(10, 11, 89, 23);
		panel_2.add(btnPre);
		
		txtRandomTree = new TextArea();
		txtRandomTree.setBounds(10, 46, 751, 468);
		panel_2.add(txtRandomTree);

		JLayeredPane RandomForest = new JLayeredPane();
		tabbedPane.addTab("Random Forest Reg", null, RandomForest, null);
		
		JButton btnPredictRandomForest = new JButton("Predict");
		btnPredictRandomForest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					List output = fonksiyon.RandomForest_Regression(trainDataSet, testDataSet);

					String res = " ";
					for (int j = 0; j < output.getItems().length; j++) {
						res += output.getItem(j);
					}

					txtRandFore.setText(res);
					txtRandFore.getText();
				}

				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		});
		btnPredictRandomForest.setBounds(10, 11, 89, 23);
		RandomForest.add(btnPredictRandomForest);
		
		txtRandFore = new TextArea();
		txtRandFore.setBounds(10, 42, 751, 472);
		RandomForest.add(txtRandFore);
		
		JLayeredPane SGD = new JLayeredPane();
		tabbedPane.addTab("SGD Regression", null, SGD, null);
		
		JButton PredictPureRand = new JButton("Predict");
		PredictPureRand.addActionListener(new ActionListener() {//BURAYA TEKRARDAN BAK
			public void actionPerformed(ActionEvent e) {
				try {

					List output = fonksiyon.SGD_Regression(trainDataSet, testDataSet);

					String res = " ";
					for (int j = 0; j < output.getItems().length; j++) {
						res += output.getItem(j);
					}

					txtSgd.setText(res);
					txtSgd.getText();
				}

				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		});
		PredictPureRand.setBounds(10, 11, 89, 23);
		SGD.add(PredictPureRand);
		
		txtSgd = new TextArea();
		txtSgd.setBounds(10, 46, 751, 468);
		SGD.add(txtSgd);
	}

	public Instances loadInstances(String path) {
		try {
			instances = fonksiyon.loadCustomDataset(path);
			fonksiyon.setDataSet(instances);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instances;

	}

	public String FileReaderFunction() {
		String path = "";
		CSVFilter filter = new CSVFilter();
		final JFileChooser fileChoose = new JFileChooser();
		fileChoose.setFileFilter(filter);
		int returnVal = fileChoose.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChoose.getSelectedFile();
			path = file.getAbsolutePath();
		}
		return path;
	}

	class CSVFilter extends javax.swing.filechooser.FileFilter {
		@Override
		public boolean accept(File file) {
			return file.isDirectory() || file.getAbsolutePath().endsWith(".csv");
		}

		@Override
		public String getDescription() {
			return "CSV Files (*.csv)";
		}
	}
}
