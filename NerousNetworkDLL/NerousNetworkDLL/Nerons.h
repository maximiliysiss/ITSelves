#pragma once
#include <string>
#include <map>
#include "ActicationPack.h"


namespace NerousNetworkDLL
{
	class ActivationPack;
}

namespace NerousNetworkDLL::Methods
{
	class IMethodNetwork;
}

namespace NerousNetworkDLL::Nerons
{

	struct Synaps {
		double weight;
		Synaps(double weight);
	};

	enum NeronType {
		HIDDEN,
		OUTPUT,
		INPUT,
		MOVE
	};

	class Neron
	{
	public:
		Neron(ActivationPack*, NeronType = NeronType::HIDDEN);
		virtual ~Neron();
		inline auto getInputNerons() const { return inputNerons; }
		inline auto getOutputNerons() const { return outputNerons; }
		inline double getInput() const { return input; }
		inline double getOutput() const { return output; }
		inline NerousNetworkDLL::ActivationPack* getActivationPack() { return activeFunction; }
		inline void setActivationPack(ActivationPack * activation) { this->activeFunction = activation; }
		virtual void calculateThrough();
		virtual void createSynaps(Neron * from, double weight);
		inline NeronType getNeronType() const { return neronType; }
	protected:
		NerousNetworkDLL::ActivationPack * activeFunction;
		std::map<Neron*, Synaps*> inputNerons;
		std::map<Neron*, Synaps*> outputNerons;
		double input{ 0 }, output{ 0 };
		double delta{ 0 }, prevDelta{ 0 };
		NeronType neronType;
	};

	class InputNeron : public Neron
	{
	public:
		InputNeron(ActivationPack*, double value = 0);
		virtual void calculateThrough() override {}
		inline void setInput(double value) { input = output = value; }
	};

	class OutputNeron : public Neron {
	public:
		OutputNeron(ActivationPack*);
	};

	class OutputNeronWithTeacher : public OutputNeron {
	private:
		double ideal;
	public:
		OutputNeronWithTeacher(ActivationPack*, double ideal = 0);
		inline void setIdeal(double ideal) { this->ideal = ideal; }
		template<typename T>
		T get() {
			calculateThrough();
			return (T)output;
		}
		inline double getError() { return ideal - output; };
	};
}

