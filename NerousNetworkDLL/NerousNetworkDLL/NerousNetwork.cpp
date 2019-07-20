#include "NerousNetwork.h"
#include <iostream>

namespace NerousNetworkDLL
{
	float NerousNetwork::ALFA = 0.01f;
	float NerousNetwork::E = 0.1f;


	void NerousNetwork::calculate()
	{
		for (auto output : outputNerons)
			output->calculateThrough();
	}

	void NerousNetwork::calculateMethod()
	{
		(*method)(this);
	}

	void NerousNetwork::setInput(std::vector<float> data)
	{
		for (int i = 0; i < inputNerons.size(); i++)
			inputNerons[i]->setInput(data[i]);
	}

	NerousNetwork::NerousNetwork(unsigned int inputCount, unsigned int outputCount, unsigned int hiddemCount,
		NerousNetworkDLL::Methods::IMethodNetwork* method, NerousNetwork::GenerateNS generateMethod)
		:method(method)
	{
		for (unsigned int i = 0; i < inputCount; i++)
			inputNerons.push_back(new NerousNetworkDLL::Nerons::InputNeron(nullptr));
		for (unsigned int i = 0; i < outputCount; i++)
			outputNerons.push_back(new NerousNetworkDLL::Nerons::OutputNeron(nullptr));
		for (unsigned int i = 0; i < hiddemCount; i++)
			hiddenNerons.push_back(new NerousNetworkDLL::Nerons::Neron(nullptr));
		generateMethod(inputNerons, outputNerons, hiddenNerons);
	}

	void NerousNetwork::train(float *** trainSet, int count)
	{
		auto e = 0.01;
		float temp_mses[4]{ 0 };
		float temp_cost = 0;
		do {
			for (int i = 0; i < count; i++) {
				for (int j = 0; j < outputNerons.size(); j++)
					inputNerons[j]->setInput(trainSet[i][0][j]);
				for (int j = 0; j < outputNerons.size(); j++) {
					Nerons::OutputNeronWithTeacher * output = (Nerons::OutputNeronWithTeacher*)outputNerons[j];
					output->setIdeal(trainSet[i][1][j]);
				}

				calculate();
				float error = 0;
				for (auto n : outputNerons)
					error += pow(((Nerons::OutputNeronWithTeacher*)n)->getError(), 2);
				temp_mses[i] = error / outputNerons.size();
				calculateMethod();
			}
			temp_cost = std::accumulate(temp_mses, temp_mses + count, 0.0f) / 4.0f;
			std::cout << temp_cost << std::endl;
		} while (temp_cost > e);
	}

	float NerousNetwork::get(std::vector<float> input, int index)
	{
		setInput(input);
		outputNerons[index]->calculateThrough();
		return outputNerons[index]->getOutput();
	}

	NerousNetwork::~NerousNetwork()
	{
	}

}