package com.ml.sevlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encog.app.analyst.EncogAnalyst;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.EncogUtility;

import com.google.gson.Gson;
import com.ml.encog.MpgPrediction;
import com.ml.model.Evaluation;
import com.ml.util.FilesPath;

/**
 * Servlet implementation class EncogServlet
 */
@WebServlet("/EncogServlet")
public class EncogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EncogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String btn = request.getParameter("btnName");
		if(btn.equals("Train")){
			MpgPrediction.prediction();
			System.out.println("Train neural network...");
			List<Double> errors = MpgPrediction.trainNetwork();
			String errorsJson = new Gson().toJson(errors);
			out.println(errorsJson);
		}else{
			System.out.println("Evaluate Netork...");
			List<Evaluation> expList = evaluate();
			String evalsJson = new Gson().toJson(expList);
			out.println(evalsJson);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public List<Evaluation> evaluate(){
		BasicNetwork network = (BasicNetwork)EncogDirectoryPersistence.loadObject(new File(FilesPath.TRAINED_NETWORK_FILE));
		EncogAnalyst analyst = new EncogAnalyst();
		analyst.load(FilesPath.ANALYST_FILE);
		MLDataSet evaluationSet = EncogUtility.loadCSV2Memory(FilesPath.NORMALIZED_EVAL_FILE, 
				network.getInputCount(), network.getOutputCount(), true, CSVFormat.ENGLISH, false);
		Evaluation experiment;
		List<Evaluation> expList = new ArrayList<>();
		for (MLDataPair item : evaluationSet) {
			BasicMLData normalizeActualOutput = (BasicMLData) network.compute(item.getInput());
			double actualOutput = analyst.getScript().getNormalize().getNormalizedFields().get(8).deNormalize(normalizeActualOutput.getData(0));
			double idealOutput = analyst.getScript().getNormalize().getNormalizedFields().get(8).deNormalize(item.getIdeal().getData(0));
			experiment = new Evaluation();

			experiment.setIdeal(idealOutput);
			experiment.setPredicted(actualOutput);
			expList.add(experiment);
			System.out.println(idealOutput + ", " + actualOutput);
		}
		return expList;
	}

}
