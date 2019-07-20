#pragma once
#include "Nerons.h"
#include "NerousNetwork.h"

namespace NerousNetworkDLL::Nerons
{
	class Neron;
}

namespace NerousNetworkDLL
{
	class ActivationPack
	{
	public:
		ActivationPack();
		virtual double Activate(double input) = 0;
		virtual double DefActivate(double input) = 0;
		virtual double Delta(Nerons::Neron * neron) = 0;
		virtual void MOR(Nerons::Neron * neron) = 0;
		virtual ~ActivationPack();
	};

	class SigmoidActivation : public ActivationPack {
	private:
		double delta;
		double preDelta{ 0 };
	public:
		inline void setDelta(double delta) { this->delta = delta; }
		inline double getDelta() const { return this->delta; }
		// Inherited via ActivationPack
		virtual double Activate(double input) override;
		virtual double DefActivate(double input) override;
		virtual double Delta(Nerons::Neron * neron) override;
		virtual void MOR(Nerons::Neron * neron) override;
	};

	class SigmoidOutputActivation : public SigmoidActivation {
	public:
		virtual double Delta(Nerons::Neron* neron) override;
		virtual void MOR(Nerons::Neron * neron) override;
	};
}

