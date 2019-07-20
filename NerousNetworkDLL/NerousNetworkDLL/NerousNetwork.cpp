#include "NerousNetwork.h"


namespace NerousNetworkDLL
{
	double NerousNetwork::ALFA = 0.2;
	double NerousNetwork::E = 0.1;


	void NerousNetwork::calculate()
	{
		for (auto output : outputNerons)
			output->calculateThrough();
	}

	void NerousNetwork::calculateMethod()
	{
		(*method)(this);
	}

	void NerousNetwork::setInput(std::vector<double> data)
	{
		for (int i = 0; i < inputNerons.size(); i++)
			inputNerons[i]->setInput(data[i]);
	}

	NerousNetwork::NerousNetwork(unsigned int inputCount, unsigned int outputCount, unsigned int hiddemCount,
		NerousNetworkDLL::Methods::IMethodNetwork* method, NerousNetwork::GenerateNS generateMethod)
		:method(method)
	{
		for (int i = 0; i < inputCount; i++)
			inputNerons.push_back(new NerousNetworkDLL::Nerons::InputNeron(nullptr));
		for (int i = 0; i < outputCount; i++)
			outputNerons.push_back(new NerousNetworkDLL::Nerons::OutputNeron(nullptr));
		for (int i = 0; i < hiddemCount; i++)
			hiddenNerons.push_back(new NerousNetworkDLL::Nerons::Neron(nullptr));
		generateMethod(inputNerons, outputNerons, hiddenNerons);
	}

	NerousNetwork::~NerousNetwork()
	{
		for (auto n : inputNerons)
			delete n;
		for (auto n : outputNerons)
			delete n;
		for (auto n : hiddenNerons)
			delete n;
	}

}