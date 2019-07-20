#include "Nerons.h"

namespace NerousNetworkDLL::Nerons
{
	Neron::Neron(ActivationPack * activationPack, NeronType neronType)
		:activeFunction(activationPack), neronType(neronType)
	{
	}


	Neron::~Neron()
	{
		for (auto synaps : this->outputNerons)
			delete synaps.second;
	}

	void Neron::calculateThrough()
	{
		input = output = 0;
		for (auto synaps : inputNerons) {
			synaps.first->calculateThrough();
			input += synaps.first->output * synaps.second->weight;
		}
		output = activeFunction->Activate(input);
	}

	void Neron::createSynaps(Neron * from, double weight)
	{
		Synaps * synaps = new Synaps(weight);
		inputNerons[from] = synaps;
		from->outputNerons[this] = synaps;
	}

	Synaps::Synaps(double weight)
		:weight(weight)
	{
	}
	InputNeron::InputNeron(ActivationPack * activationPack, double value)
		: Neron(activationPack, NeronType::INPUT)
	{
		output = input = value;
	}

	OutputNeronWithTeacher::OutputNeronWithTeacher(ActivationPack * activation, double ideal)
		: OutputNeron(activation), ideal(ideal)
	{
	}
	OutputNeron::OutputNeron(ActivationPack * activationPack)
		:Neron(activationPack, NeronType::OUTPUT)
	{
	}
}
