import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import java.awt.List;
import java.util.Random;
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
		Double actualValue = 0.0;
		Double predict = 0.0;
		String result = " ";
		for (int i = 0; i < testDataSet.numInstances(); i++) {
			// get class double value for current instance
			actualValue = testDataSet.instance(i).classValue();
			// get Instance object of current instance
			Instance newInst = testDataSet.instance(i);
			// call classifyInstance, which returns a double value for the class
			predict = regressionModel.classifyInstance(newInst);

			result.concat("Actual Value: " + actualValue + ", \t" + " Predict: " + predict + "\n");
		}
		return result;
	}

	public String evaluationTest(Instances trainDataSet, Instances testDataSet, Classifier regressionModel) throws Exception {
		String resultEvuTest;
		Evaluation evaluationModel = new Evaluation(trainDataSet);
		evaluationModel.evaluateModel(regressionModel, testDataSet);

		// System.out.println("Estimated Accuracy:
		// "+Double.toString(evaluationModel.pctCorrect()));
		
		// System.out.println(regressionModel.toString()); //burası çıktı alsın diye
		// System.out.println("Results of test scores :" +
		// evaluationModel.toSummaryString()); //burası çıktı alsın diye

		// System.out.println("Results2:" + evaluationModel.toClassDetailsString());
		// System.out.println("Results3:" + evaluationModel.toMatrixString());

		resultEvuTest = regressionModel.toString() + "\n" + "Results of test scores :"
				+ evaluationModel.toSummaryString() + " \n ";
		return resultEvuTest;
	}
	
	public String evaluationTrain(Instances trainDataSet, Classifier regressionModel) throws Exception {
		Evaluation evaluationModel = new Evaluation(trainDataSet);
		evaluationModel.evaluateModel(regressionModel, trainDataSet);

		// System.out.println(regressionModel.toString()); //burası çıktı alsın diye String döndürdük
		// System.out.println("Results of train scores:" + evaluationModel.toSummaryString()); //burası çıktı alsın diye String döndürdük

		// System.out.println("Results2:" + evaluationModel.toClassDetailsString());
		// System.out.println("Results3:" + evaluationModel.toMatrixString());
		String resultEvuTrain;
		resultEvuTrain = regressionModel.toString() + " \n " + evaluationModel.toSummaryString() + " \n ";
		return resultEvuTrain;
	}

	public String evaluationCV(Instances DataSet, Classifier regressionModel,int kFold) throws Exception {
		String resultKFold;
		Evaluation evaluationModel = new Evaluation(DataSet);
		evaluationModel.crossValidateModel(regressionModel, DataSet, kFold, new Random(1));
		resultKFold= regressionModel.toString() + "\n" + "Results of test scores :" + evaluationModel.toSummaryString() + " \n ";
		return resultKFold;
		}
	
	public List linearCV_Regression(Instances DataSet,int kFold) throws Exception {
		LinearRegression linearCV= new LinearRegression();
		linearCV.buildClassifier(DataSet);
		
		List cVList = new List();
		cVList.add(evaluationCV(DataSet, linearCV, kFold));
		// cVList.add(printActualPredict(DataSet, linearCV));
		return cVList; 
		
	}

	public List linear_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		LinearRegression LinearReg = new LinearRegression();
		LinearReg.buildClassifier(trainDataSet);
		// output model
		List lastResult = new List();
		lastResult.add(evaluationTrain(trainDataSet, LinearReg));
		lastResult.add(evaluationTest(trainDataSet, testDataSet, LinearReg));
	//	lastResult.add(printActualPredict(testDataSet, LinearReg));
		return lastResult;
	}
	
	public List IBK_Regression(Instances trainDataSet, Instances testDataSet, int neighbors) throws Exception {
		// GaussianProcesses regs = new GaussianProcesses();

		Classifier regsIBk = new IBk(neighbors);
		regsIBk.buildClassifier(trainDataSet);

		// output model
		List IbkResult = new List();
		IbkResult.add(evaluationTrain(trainDataSet, regsIBk));
		IbkResult.add(evaluationTest(trainDataSet, testDataSet, regsIBk));
		//IbkResult.add(printActualPredict(testDataSet, regsIBk));
		return IbkResult;

	}

	public List IbkCV_Reg(Instances DataSet,int neighbors, int kFold) throws Exception{
		Classifier IbkCVRegression = new IBk(neighbors);
		IbkCVRegression.buildClassifier(DataSet);
		
		List resultIbkCV = new List();
		resultIbkCV.add(evaluationCV(DataSet, IbkCVRegression, kFold));
		//resultIbkCV.add(printActualPredict(DataSet, IbkCVRegression));
		return resultIbkCV;
	}
	
	public List smo_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		SMOreg smo = new SMOreg();
		smo.buildClassifier(trainDataSet);

		List result = new List();
		result.add(evaluationTrain(trainDataSet, smo));
		result.add(evaluationTest(trainDataSet, testDataSet, smo));
		//result.add(printActualPredict(testDataSet, smo));

		// SimpleLinearRegression model = new SimpleLinearRegression();
		// model.buildClassifier(dataset);
		// System.out.println(model);
		return result;
	}
	
	public List SmoCV_Regression(Instances DataSet,int kFold) throws Exception{
		SMOreg smoCV= new SMOreg();
		smoCV.buildClassifier(DataSet);
		
		List cvResultSMO = new List();
		cvResultSMO.add(evaluationCV(DataSet, smoCV, kFold));
		//cvResultSMO.add(printActualPredict(DataSet, smoCV));
		return cvResultSMO;
	}
	
	public List RandomTree_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		// GaussianProcesses regs = new GaussianProcesses();

		RandomTree regsRandomTree = new RandomTree();
		regsRandomTree.buildClassifier(trainDataSet);

		List lstRandomTree = new List();
		lstRandomTree.add(evaluationTrain(trainDataSet, regsRandomTree));
		lstRandomTree.add(evaluationTest(trainDataSet, testDataSet, regsRandomTree));
		//lstRandomTree.add(printActualPredict(testDataSet, regsRandomTree));
		return lstRandomTree;
	}
	
	public List RandomTreeCv_Regression(Instances dataSets,int kFolds) throws Exception
	{
		RandomTree cvRandomTree = new RandomTree();
		cvRandomTree.buildClassifier(dataSets);
		
		List cvRandTreeList = new List();
		cvRandTreeList.add(evaluationCV(dataSets, cvRandomTree, kFolds));
		//cvRandTreeList.add(printActualPredict(dataSets, cvRandomTree));
		return cvRandTreeList;
	}
	
 	public List RandomForest_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		// GaussianProcesses regs = new GaussianProcesses();

		RandomForest regsRandomForest = new RandomForest();
		regsRandomForest.buildClassifier(trainDataSet);

		// output model
		List randForest = new List();
		randForest.add(evaluationTrain(trainDataSet, regsRandomForest));
		randForest.add(evaluationTest(trainDataSet, testDataSet, regsRandomForest));
	//	randForest.add(printActualPredict(testDataSet, regsRandomForest));
		return randForest;
	}

 	public List RandomForestCV_Regression(Instances DataSet, int kFold) throws Exception
 	{
 		RandomForest cvRandForest = new RandomForest();
 		cvRandForest.buildClassifier(DataSet);
 		
 		List listCvRandForest = new List();
 		listCvRandForest.add(evaluationCV(DataSet, cvRandForest, kFold));
 		listCvRandForest.add(printActualPredict(DataSet, cvRandForest));
 		return listCvRandForest;
 	}
 	
 	public List SGD_Regression(Instances trainDataSet, Instances testDataSet) throws Exception { // SORUN VAR BURDA
		System.out.println("................. SGD_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		SGD regsSGD = new SGD();
		regsSGD.buildClassifier(trainDataSet);

		// output model
		List lstSGD = new List();
		lstSGD.add(evaluationTrain(trainDataSet, regsSGD));
		lstSGD.add(evaluationTest(trainDataSet, testDataSet, regsSGD));
		//lstSGD.add(printActualPredict(testDataSet, regsSGD));
		return lstSGD;
	}

 	public List SGDCv_Regression(Instances DataSet, int kFold)throws Exception	//burda sorun var sanırım
 	{
 		SGD cvSGDReg = new SGD();
 		cvSGDReg.buildClassifier(DataSet);
 		
 		List listCvSGD = new List();
 		listCvSGD.add(evaluationCV(DataSet, cvSGDReg, kFold));
 		listCvSGD.add(printActualPredict(DataSet, cvSGDReg));
 		return listCvSGD;
 	}
 	
 	public List MultilayerPerceptron_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		// GaussianProcesses regs = new GaussianProcesses();

		MultilayerPerceptron regsMulPer = new MultilayerPerceptron();
		regsMulPer.buildClassifier(trainDataSet);

		// output model
		List multi = new List();
		multi.add(evaluationTrain(trainDataSet, regsMulPer));
		multi.add(evaluationTest(trainDataSet, testDataSet, regsMulPer));
	//	multi.add(printActualPredict(testDataSet, regsMulPer));
		return multi;
	}

 	public List MultilayerCvRegression(Instances dataSet, int kFold) throws Exception{
 		MultilayerPerceptron MultiCvReg = new MultilayerPerceptron();
 		MultiCvReg.buildClassifier(dataSet);
 		
 		List multiCv = new List();
 		multiCv.add(evaluationCV(dataSet, MultiCvReg, kFold));
 		multiCv.add(printActualPredict(dataSet, MultiCvReg));
 		return multiCv;
 	}
 	
 	public List DecisionTree_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
 		// GaussianProcesses regs = new GaussianProcesses();

		REPTree regsRepTree = new REPTree();
		regsRepTree.buildClassifier(trainDataSet);

		// output model
		List decision = new List();
		decision.add(evaluationTrain(trainDataSet, regsRepTree));
		decision.add(evaluationTest(trainDataSet, testDataSet, regsRepTree));
		//decision.add(printActualPredict(testDataSet, regsRepTree));
		return decision;
	}

 	public List DecisionTreeCv_Regression(Instances dataSet,int kfold)throws Exception{
 		REPTree RepCVTree = new REPTree();
 		RepCVTree.buildClassifier(dataSet);
 		
 		List listCvRepTree = new List();
 		listCvRepTree.add(evaluationCV(dataSet, RepCVTree, kfold));
 		listCvRepTree.add(printActualPredict(dataSet, RepCVTree));
 		return listCvRepTree;
 	}
 	
 	public List Logistic_Regression(Instances trainDataSet, Instances testDataSet) throws Exception { // BURADA SORUN VAR
		System.out.println("................. Logistic_Regression..................");
		// GaussianProcesses regs = new GaussianProcesses();

		Logistic regsLogistic = new Logistic();
		regsLogistic.buildClassifier(trainDataSet);

		// output model
		List logistic = new List();
		logistic.add(evaluationTrain(trainDataSet, regsLogistic));
		logistic.add(evaluationTest(trainDataSet, testDataSet, regsLogistic));
		//logistic.add(printActualPredict(testDataSet, regsLogistic));
		return logistic;
	}

 	public List LogisticCv_Regression(Instances dataSet,int kfold)throws Exception {	//burda sorun var sanırım
 		Logistic LogicCvReg = new Logistic();
 		LogicCvReg.buildClassifier(dataSet);
 		
 		List listCvLogic = new List();
 		listCvLogic.add(evaluationCV(dataSet, LogicCvReg, kfold));
 		listCvLogic.add(printActualPredict(dataSet, LogicCvReg));
 		return listCvLogic;
 	}
	
 	public List Additive_Regression(Instances trainDataSet, Instances testDataSet) throws Exception {
		// GaussianProcesses regs = new GaussianProcesses();

		AdditiveRegression regsAdditive = new AdditiveRegression();
		regsAdditive.buildClassifier(trainDataSet);

		// output model
		List additive = new List();
		additive.add(evaluationTrain(trainDataSet, regsAdditive));
		additive.add(evaluationTest(trainDataSet, testDataSet, regsAdditive));
		//additive.add(printActualPredict(testDataSet, regsAdditive));
		return additive;
	}
 	
 	public List AdditiveCv_Regression(Instances dataSet,int kFold)throws Exception {
 		AdditiveRegression additiveCv = new AdditiveRegression();
 		additiveCv.buildClassifier(dataSet);
 		
 		List listAdditiveCv = new List();
 		listAdditiveCv.add(evaluationCV(dataSet, additiveCv, kFold));
 		listAdditiveCv.add(printActualPredict(dataSet, additiveCv));
 		return listAdditiveCv;
	}
}
