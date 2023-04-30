// Testing of Weka J48 Decision Tree
// Iris Data Set

/*
 * Decision Tree Tester
 * For testing of WEKA's J48 Decision Tree Implementation
 * Mr. Michaud
 * www.nebomusic.net
 */

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import java.awt.BorderLayout;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;

public class TreeTest_J48 {
	
	public void testTreeJ48(String trainingPath, String testingPath, int classIndex) {
		
		Instances data;
		Instances dataTest;
		
		try {
			System.out.println("Building J48 Tree");
			// Training Set
			DataSource source = new DataSource(trainingPath);
			data = source.getDataSet();
			data.setClassIndex(classIndex);
			// Testing Set
			DataSource sourceTest = new DataSource(testingPath);
			dataTest = sourceTest.getDataSet();
			dataTest.setClassIndex(classIndex);

			// Build the J48 Tree and Train with Training Data
			J48 cls = new J48();
			cls.buildClassifier(data);

			// Evaluate Tree - Training Set
			System.out.println("Evaluating Tree for Training Set:");
			Evaluation eval = new Evaluation(data);
			eval.evaluateModel(cls, data);
			System.out.println("Error rate Training Set: " + eval.errorRate());
			System.out.println(eval.toSummaryString());
			double [][] confusionMatrix = eval.confusionMatrix();
			String out = getMatrixString(confusionMatrix);
			System.out.println("Confusion Matrix Training Set:");
			System.out.println(out);

			// Evaluate Tree - Testing Set
			System.out.println("Evaluating Tree for Testing Set:");
			eval = new Evaluation(dataTest);
			eval.evaluateModel(cls, dataTest);
			System.out.println("Error rate Testing Set: " + eval.errorRate());
			System.out.println(eval.toSummaryString());
			confusionMatrix = eval.confusionMatrix();
			System.out.println("Confusion Matrix Testing Set:");
			out = getMatrixString(confusionMatrix);
			System.out.println(out);
		}
		catch (Exception e) {
			System.out.println(":(1");
			e.printStackTrace();
		}
	}
	
	private String getMatrixString(double [][] m) {
		String out = "";
		
		for (int r = 0; r < m.length; r++) {
			String line = "";
			for (int c = 0; c < m[0].length; c++) {
				line += (int)m[r][c] + " ";
			}
			line += "\n";
			out += line;
		}
		
		return out;
	}

	public static void main(String[] args) {
		
		// Create Instance and Test
		TreeTest_J48 decisionTree = new TreeTest_J48();
		
		// Define Paths for Training and Testing Data
		String trainingPathIris = "Assignment04_DecisionTreeProject/iris_data/iris_train.csv";
		String testingPathIris = "Assignment04_DecisionTreeProject/iris_data/iris_test.csv";
		int classIndexIris=4;
		

		//Define Paths for Training and Testing Data for Diabetes
		String trainingPathDiabetes = "Assignment04_DecisionTreeProject/diabetes_data/diabetes_data_train.csv";
		String testingPathDiabetes = "Assignment04_DecisionTreeProject/diabetes_data/diabetes_data_test.csv";
		int classIndexDiabetes = 16;

		//Define path for Training and Testing Data for Income
		String trainingPathIncome="Assignment04_DecisionTreeProject/income_data/income_data_test.csv";
		String testingPathIncome="Assignment04_DecisionTreeProject/income_data/income_data_train.csv";
		int classIndexIncome=14;

		//Define path for Training and Testing Data for Horse Race Placement
		String trainingPathHorse="Assignment04_DecisionTreeProject/horse_data/horse_data_train.csv";
		String testingPathHorse="Assignment04_DecisionTreeProject/horse_data/horse_data_test.csv";
		int classIndexHorse=2;
		
		//Define path for Training and Testing Data for Horse Race Placement
		String trainingPathCredit="Assignment04_DecisionTreeProject/credit_data/credit_train.csv";
		String testingPathCredit="Assignment04_DecisionTreeProject/credit_data/credit_test.csv";
		int classIndexCredit=15;

		//Define used datasets
		String trainingPath=trainingPathIncome;
		String testingPath=testingPathIncome;
		int classIndex=classIndexIncome;
		
		// Run Tree Build and Test
		decisionTree.testTreeJ48(trainingPath, testingPath, classIndex);


		//Get Instances for Data
		Instances data=decisionTree.getData(testingPath, classIndex);

		//Get J48 Tree
		J48 treeClassifier=decisionTree.getJ48Tree(trainingPath, classIndex);
		showTree(treeClassifier);
		//Test Classification for one instance
		// Instance 0
		Instance sample=data.instance(6);
		try{
			double type=treeClassifier.classifyInstance(sample);
			System.out.println(type);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void showTree(J48 cls){
		try{
			String graphicDescriptor=cls.graph();
			//System.out.println(graphicDescriptor);
			final javax.swing.JFrame jF=new javax.swing.JFrame("Weka Tree Visualizer: J48");

			jF.setSize(10000,1000);
			jF.getContentPane().setLayout(new BorderLayout());

			TreeVisualizer tv=new TreeVisualizer(null, graphicDescriptor, new PlaceNode2());
			jF.getContentPane().add(tv,BorderLayout.CENTER);

			jF.addWindowListener(new java.awt.event.WindowAdapter(){
				public void windowClosing(java.awt.event.WindowEvent e){
					jF.dispose();
				}
			});
			jF.setVisible(true);
			tv.fitToScreen();
			}
			catch(Exception e){e.printStackTrace();}
	}
	
	public Instances getData(String dataPath, int classIndex){
		Instances data=null;
		try{
			System.out.println("Getting Data Instances");
			DataSource source=new DataSource(dataPath);
			data=source.getDataSet();
			data.setClassIndex(classIndex);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public J48 getJ48Tree(String dataPath, int classIndex){
		Instances data;
		J48 cls=null;
		try{
			System.out.println("Building J48 Tree");
			//Get Data set from path
			DataSource source=new DataSource(dataPath);
			data=source.getDataSet();
			data.setClassIndex(classIndex);
			//build tree
			cls=new J48();
			cls.buildClassifier(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cls;
	}
}
