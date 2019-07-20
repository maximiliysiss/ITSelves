#pragma once
#include "NerousNetwork.h"
#include "Nerons.h"

namespace NerousNetworkDLL::Nerons
{
	class Neron;
}

namespace NerousNetworkDLL
{
	class NerousNetwork;
}

namespace NerousNetworkDLL::Methods
{
	class IMethodNetwork
	{
	public:
		IMethodNetwork();
		virtual void operator()(NerousNetwork* ns) = 0;
		virtual ~IMethodNetwork();
	};

	class MORMethod : public IMethodNetwork {
		std::map<NerousNetworkDLL::Nerons::Neron*, float> previousDeltas;
		void MORCalculate(NerousNetworkDLL::Nerons::Neron* neron);
	public:
		virtual void operator()(NerousNetwork * ns) override;
	};
}

