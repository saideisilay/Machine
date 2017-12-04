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
	MachineLearning fonksiyon = new MachineLearning();
	Instances trainDataSet, testDataSet, instances;

	private TextArea txtLinearResult;
	private TextArea txtIbk;
	private TextArea txtSmo;
	private TextArea txtRandomTree;
	private TextArea txtRandFore;
	private TextArea txtSgd;
	private TextArea txtMultiLayer;
	private TextArea txtDecision;
	private TextArea txtLogic;
	private TextArea txtAdditive;
	private TextArea txtCVLinear;
	private TextArea txtCvIbk;
	private TextArea txtSmoCV;
	private TextArea txtCvRandomTree;
	private TextArea txtCvDecision;
	private TextArea txtCvRandFore;
	private TextArea txtCvAdditive;
	private TextArea txtCvLogic;
	private JTextField readFileText;
	private JTextField readTestText;
	private JTextField txtNeighbor;
	private JTextField txtFldCVLinear;
	private JTextField txtCvIbkFold;
	private JTextField txtCVSmo;
	private JTextField txtRandCV;
	private JTextField txtCvForTree;
	private JTextField txtCvSGD;
	private JTextField txtMultiCv;
	private JTextField txtDecisionCv;
	private JTextField txtLogicCv;
	private JTextField txtAdditiveCv;

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
		setBounds(100, 100, 866, 600);
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

		btnChooseFile.setBounds(10, 11, 163, 22);
		panel_1.add(btnChooseFile);

		readFileText = new JTextField();
		readFileText.setBounds(187, 11, 460, 22);
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
		btnChooseTestDataset.setBounds(10, 52, 163, 22);
		panel_1.add(btnChooseTestDataset);

		readTestText = new JTextField();
		readTestText.setBounds(187, 53, 460, 20);
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
		btnGetData.setBounds(667, 32, 116, 23);
		panel_1.add(btnGetData);
		
		JLabel lblNewLabel = new JLabel("* In order to start kFold just Choose TrainDataSet");
		lblNewLabel.setBounds(10, 85, 311, 22);
		panel_1.add(lblNewLabel);

		JTabbedPane LinearReg = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Linear Regression", null, LinearReg, null);

		JLayeredPane layeredPane_4 = new JLayeredPane();
		LinearReg.addTab("Results", null, layeredPane_4, null);

		JButton btnPredict = new JButton("Predict");
		btnPredict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List output = fonksiyon.linear_Regression(trainDataSet, testDataSet);
					txtLinearResult.setText(ResultFunctionList(output));
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
		txtLinearResult.setBounds(10, 51, 400, 418);
		layeredPane_4.add(txtLinearResult);
		
		txtCVLinear = new TextArea();
		txtCVLinear.setBounds(416, 51, 400, 418);
		layeredPane_4.add(txtCVLinear);
		
		txtFldCVLinear = new JTextField();
		txtFldCVLinear.setBounds(527, 12, 86, 20);
		layeredPane_4.add(txtFldCVLinear);
		txtFldCVLinear.setColumns(10);
		
		JLabel lblEnterCvFold = new JLabel("Enter CV fold");
		lblEnterCvFold.setBounds(444, 15, 73, 19);
		layeredPane_4.add(lblEnterCvFold);
		
		JButton btnPredictKfold = new JButton("Predict K-Fold");
		btnPredictKfold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int kFold=Integer.parseInt(txtFldCVLinear.getText());
				try {
					List outputCVLinear = fonksiyon.linearCV_Regression(trainDataSet, kFold);
					txtCVLinear.setText(ResultFunctionList(outputCVLinear));
					txtCVLinear.getText();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPredictKfold.setBounds(623, 11, 115, 23);
		layeredPane_4.add(btnPredictKfold);

		JLayeredPane IBK = new JLayeredPane();
		tabbedPane.addTab("IBK", null, IBK, null);

		JButton btnPredickIbk = new JButton("Predict");
		btnPredickIbk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int neighbor = Integer.parseInt(txtNeighbor.getText());
				try {
					List outputIBK = fonksiyon.IBK_Regression(trainDataSet, testDataSet, neighbor);
					txtIbk.setText(ResultFunctionList(outputIBK));
					txtIbk.getText();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPredickIbk.setBounds(225, 12, 89, 23);
		IBK.add(btnPredickIbk);

		JLabel lblChooseNeighbors = new JLabel("Choose neighbors");
		lblChooseNeighbors.setBounds(10, 15, 109, 20);
		IBK.add(lblChooseNeighbors);

		JTextField txtNeighbor = new JTextField();
		txtNeighbor.setBounds(129, 13, 86, 20);
		IBK.add(txtNeighbor);
		txtNeighbor.setColumns(10);

		txtIbk = new TextArea();
		txtIbk.setBounds(10, 56, 400, 441);
		IBK.add(txtIbk);
		
		txtCvIbk = new TextArea();
		txtCvIbk.setBounds(425, 56, 400, 441);
		IBK.add(txtCvIbk);
		
		JLabel label = new JLabel("Enter CV fold");
		label.setBounds(449, 15, 73, 19);
		IBK.add(label);
		
		txtCvIbkFold = new JTextField();
		txtCvIbkFold.setColumns(10);
		txtCvIbkFold.setBounds(532, 12, 86, 20);
		IBK.add(txtCvIbkFold);
		
		JButton btnIbkCV = new JButton("Predict K-Fold");
		btnIbkCV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int neighbors = Integer.parseInt(txtNeighbor.getText());
				int kFold= Integer.parseInt(txtCvIbkFold.getText());
				try {
					List outputCvIBK = fonksiyon.IbkCV_Reg(trainDataSet, neighbors, kFold);
					txtCvIbk.setText(ResultFunctionList(outputCvIBK));
					txtCvIbk.getText();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnIbkCV.setBounds(628, 11, 115, 23);
		IBK.add(btnIbkCV);

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
					txtSmo.setText(ResultFunctionList(output));
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
		txtSmo.setBounds(10, 57, 400, 441);
		panel.add(txtSmo);
		
		txtSmoCV = new TextArea();
		txtSmoCV.setBounds(425, 57, 400, 441);
		panel.add(txtSmoCV);
		
		JLabel label_1 = new JLabel("Enter CV fold");
		label_1.setBounds(449, 32, 73, 19);
		panel.add(label_1);
		
		txtCVSmo = new JTextField();
		txtCVSmo.setColumns(10);
		txtCVSmo.setBounds(532, 29, 86, 20);
		panel.add(txtCVSmo);
		
		JButton btnSmoCV = new JButton("Predict K-Fold");
		btnSmoCV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int kFolds= Integer.parseInt(txtCVSmo.getText());
				try {
					List outputCvSMO = fonksiyon.SmoCV_Regression(trainDataSet, kFolds);
					txtSmoCV.setText(ResultFunctionList(outputCvSMO));
					txtSmoCV.getText();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		btnSmoCV.setBounds(628, 28, 115, 23);
		panel.add(btnSmoCV);

		JLayeredPane RandomTree = new JLayeredPane();
		tabbedPane.addTab("Random Tree", null, RandomTree, null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 859, 525);
		RandomTree.add(panel_2);
		panel_2.setLayout(null);

		JButton btnPre = new JButton("Predict");
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					List output = fonksiyon.RandomTree_Regression(trainDataSet, testDataSet);
					txtRandomTree.setText(ResultFunctionList(output));
					txtRandomTree.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPre.setBounds(10, 11, 89, 23);
		panel_2.add(btnPre);

		txtRandomTree = new TextArea();
		txtRandomTree.setBounds(10, 46, 400, 468);
		panel_2.add(txtRandomTree);
		
		txtCvRandomTree = new TextArea();
		txtCvRandomTree.setBounds(439, 46, 400, 468);
		panel_2.add(txtCvRandomTree);
		
		JLabel label_2 = new JLabel("Enter CV fold");
		label_2.setBounds(442, 15, 73, 19);
		panel_2.add(label_2);
		
		txtRandCV = new JTextField();
		txtRandCV.setColumns(10);
		txtRandCV.setBounds(525, 12, 86, 20);
		panel_2.add(txtRandCV);
		
		JButton btnCvRandomTree = new JButton("Predict K-Fold");
		btnCvRandomTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kFold = Integer.parseInt(txtRandCV.getText());
				try {
					List outputCvRTree = fonksiyon.RandomTreeCv_Regression(trainDataSet, kFold);
					txtCvRandomTree.setText(ResultFunctionList(outputCvRTree));
					txtCvRandomTree.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCvRandomTree.setBounds(621, 11, 115, 23);
		panel_2.add(btnCvRandomTree);

		JLayeredPane RandomForest = new JLayeredPane();
		tabbedPane.addTab("Random Forest Reg", null, RandomForest, null);

		JButton btnPredictRandomForest = new JButton("Predict");
		btnPredictRandomForest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					List output = fonksiyon.RandomForest_Regression(trainDataSet, testDataSet);
					txtRandFore.setText(ResultFunctionList(output));
					txtRandFore.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPredictRandomForest.setBounds(10, 11, 89, 23);
		RandomForest.add(btnPredictRandomForest);

		txtRandFore = new TextArea();
		txtRandFore.setBounds(10, 42, 400, 455);
		RandomForest.add(txtRandFore);
		
		txtCvRandFore = new TextArea();
		txtCvRandFore.setBounds(416, 42, 400, 455);
		RandomForest.add(txtCvRandFore);
		
		JLabel label_3 = new JLabel("Enter CV fold");
		label_3.setBounds(437, 15, 73, 19);
		RandomForest.add(label_3);
		
		txtCvForTree = new JTextField();
		txtCvForTree.setColumns(10);
		txtCvForTree.setBounds(520, 12, 86, 20);
		RandomForest.add(txtCvForTree);
		
		JButton btnCvRandForest = new JButton("Predict K-Fold");
		btnCvRandForest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kFold = Integer.parseInt(txtRandCV.getText());
				try {
					List CVRandForestList = fonksiyon.RandomForestCV_Regression(trainDataSet, kFold);
					txtCvRandFore.setText(ResultFunctionList(CVRandForestList));
					txtCvRandFore.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCvRandForest.setBounds(616, 11, 115, 23);
		RandomForest.add(btnCvRandForest);

		JLayeredPane SGD = new JLayeredPane();
		tabbedPane.addTab("SGD Regression", null, SGD, null);

		JButton PredictPureRand = new JButton("Predict");
		PredictPureRand.addActionListener(new ActionListener() {// BURAYA TEKRARDAN BAK
			public void actionPerformed(ActionEvent e) {
				try {
					List outputSGD = fonksiyon.SGD_Regression(trainDataSet, testDataSet);
					txtSgd.setText(ResultFunctionList(outputSGD));
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
		txtSgd.setBounds(10, 46, 400, 450);
		SGD.add(txtSgd);
		
		TextArea txtCvSgd = new TextArea();
		txtCvSgd.setBounds(434, 46, 400, 450);
		SGD.add(txtCvSgd);
		
		JLabel label_4 = new JLabel("Enter CV fold");
		label_4.setBounds(433, 15, 73, 19);
		SGD.add(label_4);
		
		txtCvSGD = new JTextField();
		txtCvSGD.setColumns(10);
		txtCvSGD.setBounds(516, 12, 86, 20);
		SGD.add(txtCvSGD);
		
		JButton btnSGDCV = new JButton("Predict K-Fold");
		btnSGDCV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kfold  = Integer.parseInt(txtCvSGD.getText());
				try {
					List CvSGDList = fonksiyon.LogisticCv_Regression(trainDataSet, kfold);
					txtCvSgd.setText(ResultFunctionList(CvSGDList));
					txtCvSgd.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnSGDCV.setBounds(612, 11, 115, 23);
		SGD.add(btnSGDCV);

		JLayeredPane MultiLayer = new JLayeredPane();
		tabbedPane.addTab("Multilayer Perceptron", null, MultiLayer, null);

		JButton btnPredictMulti = new JButton("Predict");
		btnPredictMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List outputMulti = fonksiyon.MultilayerPerceptron_Regression(trainDataSet, testDataSet);
					txtMultiLayer.setText(ResultFunctionList(outputMulti));
					txtMultiLayer.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPredictMulti.setBounds(10, 11, 89, 23);
		MultiLayer.add(btnPredictMulti);

		txtMultiLayer = new TextArea();
		txtMultiLayer.setBounds(10, 46, 400, 450);
		MultiLayer.add(txtMultiLayer);
		
		TextArea txtCvMulti = new TextArea();
		txtCvMulti.setBounds(426, 46, 400, 450);
		MultiLayer.add(txtCvMulti);
		
		JLabel label_5 = new JLabel("Enter CV fold");
		label_5.setBounds(426, 15, 73, 19);
		MultiLayer.add(label_5);
		
		txtMultiCv = new JTextField();
		txtMultiCv.setColumns(10);
		txtMultiCv.setBounds(509, 12, 86, 20);
		MultiLayer.add(txtMultiCv);
		
		JButton btnMultiCv = new JButton("Predict K-Fold");
		btnMultiCv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kFol =Integer.parseInt(txtMultiCv.getText());
				try {
					List listMultiCv = fonksiyon.MultilayerCvRegression(trainDataSet, kFol);
					txtCvMulti.setText(ResultFunctionList(listMultiCv));
					txtCvMulti.getText();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnMultiCv.setBounds(605, 11, 115, 23);
		MultiLayer.add(btnMultiCv);

		JLayeredPane DecisionTree = new JLayeredPane();
		tabbedPane.addTab("Decision Tree", null, DecisionTree, null);

		JButton btnPredictDecision = new JButton("Predict");
		btnPredictDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List outputDes = fonksiyon.DecisionTree_Regression(trainDataSet, testDataSet);
					txtDecision.setText(ResultFunctionList(outputDes));
					txtDecision.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPredictDecision.setBounds(10, 11, 89, 23);
		DecisionTree.add(btnPredictDecision);

		txtDecision = new TextArea();
		txtDecision.setBounds(10, 42, 400, 450);
		DecisionTree.add(txtDecision);
		
		txtCvDecision = new TextArea();
		txtCvDecision.setBounds(425, 42, 400, 450);
		DecisionTree.add(txtCvDecision);
		
		JLabel label_6 = new JLabel("Enter CV fold");
		label_6.setBounds(425, 15, 73, 19);
		DecisionTree.add(label_6);
		
		txtDecisionCv = new JTextField();
		txtDecisionCv.setColumns(10);
		txtDecisionCv.setBounds(508, 12, 86, 20);
		DecisionTree.add(txtDecisionCv);
		
		JButton btnDecision = new JButton("Predict K-Fold");
		btnDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int kFold = Integer.parseInt(txtDecisionCv.getText());
				try {
					List cvDecisionList = fonksiyon.DecisionTreeCv_Regression(trainDataSet, kFold);
					txtCvDecision.setText(ResultFunctionList(cvDecisionList));
					txtCvDecision.getText();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnDecision.setBounds(604, 11, 115, 23);
		DecisionTree.add(btnDecision);

		JLayeredPane Logistic = new JLayeredPane();
		tabbedPane.addTab("Logistic", null, Logistic, null);

		JButton btnPredictLogic = new JButton("Predict");
		btnPredictLogic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List outputLogic = fonksiyon.Logistic_Regression(trainDataSet, testDataSet);
					txtLogic.setText(ResultFunctionList(outputLogic));
					txtLogic.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPredictLogic.setBounds(10, 11, 89, 23);
		Logistic.add(btnPredictLogic);

		txtLogic = new TextArea();
		txtLogic.setBounds(10, 45, 400, 450);
		Logistic.add(txtLogic);
		
		txtCvLogic = new TextArea();
		txtCvLogic.setBounds(423, 45, 400, 450);
		Logistic.add(txtCvLogic);
		
		JLabel label_7 = new JLabel("Enter CV fold");
		label_7.setBounds(423, 15, 73, 19);
		Logistic.add(label_7);
		
		txtLogicCv = new JTextField();
		txtLogicCv.setColumns(10);
		txtLogicCv.setBounds(506, 12, 86, 20);
		Logistic.add(txtLogicCv);
		
		JButton btnLogicCv = new JButton("Predict K-Fold");
		btnLogicCv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int key = Integer.parseInt(txtLogicCv.getText());
				try {
					List CvLogicList= fonksiyon.LogisticCv_Regression(trainDataSet, key);
					txtCvLogic.setText(ResultFunctionList(CvLogicList));
					txtCvLogic.getText();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLogicCv.setBounds(602, 11, 115, 23);
		Logistic.add(btnLogicCv);

		JLayeredPane Additive = new JLayeredPane();
		tabbedPane.addTab("Additive", null, Additive, null);

		JButton btnPredictAdditive = new JButton("Predict");
		btnPredictAdditive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List outputAdditive = fonksiyon.Additive_Regression(trainDataSet, testDataSet);
					txtAdditive.setText(ResultFunctionList(outputAdditive));
					txtAdditive.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPredictAdditive.setBounds(10, 11, 89, 23);
		Additive.add(btnPredictAdditive);

		txtAdditive = new TextArea();
		txtAdditive.setBounds(10, 45, 400, 450);
		Additive.add(txtAdditive);
		
		txtCvAdditive = new TextArea();
		txtCvAdditive.setBounds(416, 45, 400, 450);
		Additive.add(txtCvAdditive);
		
		JLabel label_8 = new JLabel("Enter CV fold");
		label_8.setBounds(416, 15, 73, 19);
		Additive.add(label_8);
		
		txtAdditiveCv = new JTextField();
		txtAdditiveCv.setColumns(10);
		txtAdditiveCv.setBounds(499, 12, 86, 20);
		Additive.add(txtAdditiveCv);
		
		JButton btnAdditiveCv = new JButton("Predict K-Fold");
		btnAdditiveCv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int keyFold= Integer.parseInt(txtAdditiveCv.getText());
				try {
					List lstAdditiveCv = fonksiyon.AdditiveCv_Regression(trainDataSet, keyFold);
					txtCvAdditive.setText(ResultFunctionList(lstAdditiveCv));
					txtCvAdditive.getText();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnAdditiveCv.setBounds(595, 11, 115, 23);
		Additive.add(btnAdditiveCv);
	}

	public Instances loadInstances(String path) throws Exception{
		instances = fonksiyon.loadCustomDataset(path);
		fonksiyon.setDataSet(instances);
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

	public String ResultFunctionList(List function)throws Exception
	{
		String res = " ";
		for (int j = 0; j < function.getItems().length; j++) {
			res+=function.getItem(j);
		}
		return res;
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
