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
		virtual float Activate(float input) = 0;
		virtual float DefActivate(float input) = 0;
		virtual float Delta(Nerons::Neron * neron) = 0;
		virtual void MOR(Nerons::Neron * neron) = 0;
		virtual ~ActivationPack();
	};

	class SigmoidActivation : public ActivationPack {
	private:
		float delta;
		float preDelta{ 0 };
	public:
		inline void setDelta(float delta) { this->delta = delta; }
		inline float getDelta() const { return this->delta; }
		virtual float Activate(float input) override;
		virtual float DefActivate(float input) override;
		virtual float Delta(Nerons::Neron * neron) override;
		virtual void MOR(Nerons::Neron * neron) override;
	};

	class SigmoidOutputActivation : public SigmoidActivation {
	public:
		virtual float Delta(Nerons::Neron* neron) override;
		virtual void MOR(Nerons::Neron * neron) override;
	};
}

