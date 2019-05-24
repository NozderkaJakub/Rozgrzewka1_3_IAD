package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class Program {
	LinearNeuron linear;
	List<RadialNeuron> radial;

	public Program(int noOfPatterns) {
		linear = new LinearNeuron(noOfPatterns);
		radial = new ArrayList<RadialNeuron>();
		for (int i = 0; i < noOfPatterns; i++) {
			radial.add(i, new RadialNeuron(Math.random() * 3));
		}
	}

	public void algorithm() throws IOException {
		double iterator = 0;
		File linearOutputFile = new File("output0.txt");
		if (linearOutputFile.exists())
			linearOutputFile.delete();
		linearOutputFile.createNewFile();
		for (int i = 0; i < radial.size(); i++) {
			File radialOutputFile = new File("output" + i + ".txt");
			if (radialOutputFile.exists())
				radialOutputFile.delete();
			radialOutputFile.createNewFile();
		}
		for (int i = 0; i < radial.size(); i++) {
			radial.get(i).setNumber(i + 1);
		}
		do {
			for (int i = 0; i < radial.size(); i++) {
				radial.get(i).setInput(iterator);
				radial.get(i).saveOutput(iterator, linear.getWeights()[i]);
			}
			double[] inputs = new double[radial.size()];
			for (int i = 0; i < radial.size(); i++) {
				inputs[i] = radial.get(i).getOutput();
			}
			linear.setInputs(inputs);
			saveOutput(iterator, linear.getOutput(), linearOutputFile);
			iterator += 0.01;
		} while (iterator <= 10);
	}

	void saveOutput(double iterator, double output, File file) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.println(iterator + ";" + output);
		out.close();
	}

}
