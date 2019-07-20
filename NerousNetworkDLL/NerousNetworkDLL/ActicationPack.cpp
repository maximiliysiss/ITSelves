#include "ActicationPack.h"

NerousNetworkDLL::ActivationPack::ActivationPack()
{
}

NerousNetworkDLL::ActivationPack::~ActivationPack()
{
}

double NerousNetworkDLL::SigmoidActivation::Activate(double input)
{
	return 1 / (1 + exp(-input));
}

double NerousNetworkDLL::SigmoidActivation::DefActivate(double input)
{
	return (1 - input)*input;
}

double NerousNetworkDLL::SigmoidActivation::Delta(Nerons::Neron * neron)
{
	double res = 0;
	for (auto n : neron->getOutputNerons()) {
		ActivationPack * act = n.first->getActivationPack();
		res += n.second->weight*act->Delta(n.first);
	}
	((SigmoidActivation*)neron->getActivationPack())->setDelta(res * this->DefActivate(neron->getOutput()));
	return ((SigmoidActivation*)neron)->getDelta();
}

void NerousNetworkDLL::SigmoidActivation::MOR(Nerons::Neron * neron)
{
	for (auto n : neron->getOutputNerons()) {
		ActivationPack * pack = n.first->getActivationPack();
		auto grad = neron->getOutput()*((SigmoidActivation*)pack)->getDelta();
		auto wDelta = NerousNetwork::E * grad + NerousNetwork::ALFA*preDelta;
		preDelta = wDelta;
		n.second->weight += wDelta;
	}
}

double NerousNetworkDLL::SigmoidOutputActivation::Delta(Nerons::Neron * neron)
{
	Nerons::OutputNeronWithTeacher * output = (Nerons::OutputNeronWithTeacher*)neron;
	((SigmoidActivation*)neron->getActivationPack())->setDelta((output->getError())*this->DefActivate(output->getOutput()));
	return ((SigmoidActivation*)neron)->getDelta();
}

void NerousNetworkDLL::SigmoidOutputActivation::MOR(Nerons::Neron * neron)
{
}
