#include "NerousNetwork.h"
#include <iostream>

namespace NerousNetworkDLL
{
	float NerousNetwork::ALFA = 0.01f;
	float NerousNetwork::E = 0.1f;


	void NerousNetwork::save()
	{
		std::ofstream file("nn.output");
		for (auto n : hiddenNerons) {
			for (auto i : n->getInputNerons())
				file << i.second->weight << ' ';
			for (auto o : n->getOutputNerons())
				file << o.second->weight << ' ';
		}
		file.close();
	}

	void NerousNetwork::load()
	{
		std::ifstream file("nn.output");
		if (!file.is_open()) {
			return;
		}
		for (auto n : hiddenNerons) {
			for (auto i : n->getInputNerons())
				file >> i.second->weight;
			for (auto o : n->getOutputNerons())
				file >> o.second->weight;
		}
		file.close();
	}

	void NerousNetwork::closeInput(std::vector<int> indxs)
	{
		for (int i = 1; i < indxs.size(); i++)
			indxs[i] -= i;
		for (int i = 0; i < indxs.size(); i++)
			inputNerons.erase(inputNerons.begin() + indxs[i]);
	}

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
		load();
		auto e = 0.01;
		float *temp_mses = new float[count];
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
		save();
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