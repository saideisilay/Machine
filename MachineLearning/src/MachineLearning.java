import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.awt.List;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SGD;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.AdditiveRegression;
import weka.classifiers.trees.RandomTree;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;

public class MachineLearning {

	public Instances loadCustomDataset(String filePath) throws Exception {
		CSVLoader loader = new CSVLoader();
		loader.setFile(new java.io.File(filePath));
		return loader.getDataSet();
	}

	public void setDataSet(Instances setData) {
		setData.setClassIndex(setData.numAttributes() - 1);
	}

	public String printActualPredict(Instances testDataSet, Classifier regressionModel) throws Exception {
		Double actualValue =0.0;
		Double predict = 0.0;
		String result = null;
		for (int i = 0; i < testDataSet.numInstances(); i++) {
			// get class double value for current instance
			actualValue = testDataSet.instance(i).classValue();
			// get Instance object of current instance
			Instance newInst = testDataSet.instance(i);
			// call classifyInstance, which returns a double value for the class
			predict = regressionModel.classifyInstance(newInst);
			
			 //System.out.println("actual " + actualValue+", " +" predict "+predict);
			result += "Actual Value: " + actualValue+", \t" +" Predict: "+predict +"\n";
		}
		return result;
	}

	public String evaluationTest(Instances trainDataSet,Instances testDataSet,Classifier regressionModel) throws Exception	
	 {
	  System.out.println("___________________________________TEST__________________________________");
	  String resultEvuTest;
	  Evaluation evaluationModel = new Evaluation(trainDataSet);
	 // evaluationModel.crossValidateModel(regressionModel, testDataSet, 10,new Random(1));
	//  System.out.println("Estimated Accuracy: "+Double.toString(evaluationModel.pctCorrect()));
	  evaluationModel.evaluateModel(regressionModel, testDataSet);
	 
	 // System.out.println(regressionModel.toString());	//burası çıktı alsın diye 
	  // System.out.println("Results of test scores :" + evaluationModel.toSummaryString()); //burası çıktı alsın diye 
	  
	  //System.out.println("Results2:" + evaluationModel.toClassDetailsString());
	  //System.out.println("Results3:" + evaluationModel.toMatrixString());
	  
	  resultEvuTest = regressionModel.toString() + "\n"+ "Results of test scores :" +evaluationModel.toSummaryString()+" \n ";
	  return resultEvuTest;
	 }
	 
	public String evaluationTrain(Instances trainDataSet,Classifier  regressionModel) throws Exception
	 {
	  System.out.println("___________________________TRAIN__________________________________________");
	  Evaluation evaluationModel = new Evaluation(trainDataSet);
	  //evaluationModel.crossValidateModel(regressionModel, trainDataSet, 10,new Random(1));
	  //System.out.println("Estimated Accuracy: "+Double.toString(evaluationModel.pctCorrect()));
	  evaluationModel.evaluateModel(regressionModel, trainDataSet);
	 
	 //System.out.println(regressionModel.toString());	//burası çıktı alsın diye String döndürdük
	 //System.out.println("Results of train scores:" + evaluationModel.toSummaryString()); //burası çıktı alsın diye String döndürdük
	  
	  //System.out.println("Results2:" + evaluationModel.toClassDetailsString());
	  //System.out.println("Results3:" + evaluationModel.toMatrixString());
	  String resultEvuTrain;
	  resultEvuTrain = regressionModel.toString() + " \n " + evaluationModel.toSummaryString()+" \n ";
	  return resultEvuTrain;
	 }

	public List smo_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. smo_Regression..................");
		SMOreg smo = new SMOreg();
		smo.buildClassifier(trainDataSet);
		
		// output model
		List result=new List();
		
		result.add(evaluationTrain(trainDataSet, smo));
		result.add(evaluationTest(trainDataSet, testDataSet, smo));
		result.add(printActualPredict(testDataSet, smo));

		// SimpleLinearRegression model = new SimpleLinearRegression();
		// model.buildClassifier(dataset);
		// System.out.println(model);
		return result;
	}

	public List linear_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. linear_Regression..................");
		LinearRegression LinearReg = new LinearRegression();
		LinearReg.buildClassifier(trainDataSet);
		// output model
		List lastResult=new List();
		lastResult.add(evaluationTrain(trainDataSet, LinearReg));
		lastResult.add(evaluationTest(trainDataSet, testDataSet, LinearReg));
		lastResult.add(printActualPredict(testDataSet, LinearReg));
		return lastResult;
	}

	public List IBK_Regression(Instances trainDataSet, Instances testDataSet, int neighbors) throws Exception {
		System.out.println("................. IBK_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		Classifier regsIBk = new IBk(neighbors); // IBK(n) n kom�u say�s�
		regsIBk.buildClassifier(trainDataSet);

		// output model
		List IbkResult=new List();
		IbkResult.add(evaluationTrain(trainDataSet, regsIBk));
		IbkResult.add(evaluationTest(trainDataSet, testDataSet, regsIBk));
		IbkResult.add(printActualPredict(testDataSet, regsIBk));
		return IbkResult;
		
	}

	public List RandomTree_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. RandomTree_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		RandomTree regsRandomTree = new RandomTree();
		regsRandomTree.buildClassifier(trainDataSet);

		// output model
		List lstRandomTree = new List();
		lstRandomTree.add(evaluationTrain(trainDataSet, regsRandomTree));
		lstRandomTree.add(evaluationTest(trainDataSet, testDataSet, regsRandomTree));
		lstRandomTree.add(printActualPredict(testDataSet, regsRandomTree));
		return lstRandomTree;
	}

	public void RandomForest_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. RandomForest_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		RandomForest regsRandomForest = new RandomForest();
		regsRandomForest.buildClassifier(trainDataSet);

		// output model
		evaluationTrain(trainDataSet, regsRandomForest);
		evaluationTest(trainDataSet, testDataSet, regsRandomForest);
		printActualPredict(testDataSet, regsRandomForest);
	}

	public void SGD_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. SGD_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		SGD regsSGD = new SGD();
		regsSGD.buildClassifier(trainDataSet);

		// output model
		evaluationTest(trainDataSet, testDataSet, regsSGD);

		evaluationTrain(trainDataSet, regsSGD);
		printActualPredict(testDataSet, regsSGD);
	}

	public void MultilayerPerceptron_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. MultilayerPerceptron_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		MultilayerPerceptron regsMulPer = new MultilayerPerceptron();
		regsMulPer.buildClassifier(trainDataSet);

		// output model
		evaluationTest(trainDataSet, testDataSet, regsMulPer);

		evaluationTrain(trainDataSet, regsMulPer);
		printActualPredict(testDataSet, regsMulPer);
	}

	public void DecisionTree_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. DecisionTree_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		REPTree regsRepTree = new REPTree();
		regsRepTree.buildClassifier(trainDataSet);

		// output model
		evaluationTest(trainDataSet, testDataSet, regsRepTree);

		evaluationTrain(trainDataSet, regsRepTree);
		printActualPredict(testDataSet, regsRepTree);
	}

	public void Logistic_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. Logistic_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		Logistic regsLogistic = new Logistic();
		regsLogistic.buildClassifier(trainDataSet);

		// output model
		evaluationTest(trainDataSet, testDataSet, regsLogistic);

		evaluationTrain(trainDataSet, regsLogistic);
		printActualPredict(testDataSet, regsLogistic);
	}

	public void Additive_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		System.out.println("................. Additive_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		AdditiveRegression regsAdditive = new AdditiveRegression();
		regsAdditive.buildClassifier(trainDataSet);

		// output model
		evaluationTest(trainDataSet, testDataSet, regsAdditive);

		evaluationTrain(trainDataSet, regsAdditive);
		printActualPredict(testDataSet, regsAdditive);
	}
}
