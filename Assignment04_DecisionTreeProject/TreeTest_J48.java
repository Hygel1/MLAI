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
		// TODO Auto-generated method stub
		
		// Create Instance and Test
		TreeTest_J48 decisionTree = new TreeTest_J48();
		
		// Define Paths for Training and Testing Data
		//
		//
		String trainingPath = "Assignment04_DecisionTreeProject/diabetes_data/diabetes_data_train.csv";
		String testingPath = "Assignment04_DecisionTreeProject/diabetes_data/diabetes_data_test.csv";
		int classIndex = 4;
		
		// Run Tree Build and Test
		//decisionTree.testTreeJ48(trainingPath, testingPath, classIndex);

		//Get Instances for Data
		Instances data=decisionTree.getData(testingPath, classIndex);

		//Get J48 Tree
		J48 treeClassifier=decisionTree.getJ48Tree(trainingPath, classIndex);

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
