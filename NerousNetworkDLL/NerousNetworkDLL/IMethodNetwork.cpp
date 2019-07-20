#include "IMethodNetwork.h"

namespace NerousNetworkDLL::Methods
{
	IMethodNetwork::IMethodNetwork()
	{
	}


	IMethodNetwork::~IMethodNetwork()
	{
	}

	void MORMethod::MORCalculate(NerousNetworkDLL::Nerons::Neron * neron)
	{
		auto delta = neron->getActivationPack()->Delta(neron);
		neron->getActivationPack()->MOR(neron);
		for (auto n : neron->getInputNerons())
			n.first->getActivationPack()->MOR(n.first);
	}

	void MORMethod::operator()(NerousNetwork * ns)
	{
		for (auto n : ns->getOutputs())
			n->getActivationPack()->MOR(n);
	}
}
