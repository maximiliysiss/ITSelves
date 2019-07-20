#pragma once
#include <vector>
#include "Nerons.h"
#include "IMethodNetwork.h"

namespace NerousNetworkDLL::Methods
{
	class IMethodNetwork;
	class MORMethod;
}

namespace NerousNetworkDLL::Nerons
{
	class InputNeron;
	class OutputNeron;
}

namespace NerousNetworkDLL
{
	class NerousNetwork
	{
		typedef void(*GenerateNS)(std::vector<NerousNetworkDLL::Nerons::InputNeron*> inputs, std::vector<NerousNetworkDLL::Nerons::OutputNeron*> outputs, std::vector<NerousNetworkDLL::Nerons::Neron*> nerons);
	protected:
		std::vector<NerousNetworkDLL::Nerons::InputNeron*> inputNerons;
		std::vector<NerousNetworkDLL::Nerons::OutputNeron*> outputNerons;
		std::vector<NerousNetworkDLL::Nerons::Neron*> hiddenNerons;
		NerousNetworkDLL::Methods::IMethodNetwork * method;
	public:
		inline auto getInputs() const { return inputNerons; }
		inline auto getOutputs() const { return outputNerons; }
		inline auto getHiddens() const { return hiddenNerons; }
		void calculate();
		void calculateMethod();
		void setInput(std::vector<double> data);
		static double E;
		static double ALFA;
		NerousNetwork(unsigned int inputCount, unsigned int outputCount, unsigned int hiddemCount, NerousNetworkDLL::Methods::IMethodNetwork * method, GenerateNS generateMethod);
		virtual ~NerousNetwork();
	};
}