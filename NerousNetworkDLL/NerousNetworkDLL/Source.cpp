#include "NerousNetwork.h"
#include <iostream>

using namespace std;


void GenerateNS(std::vector<NerousNetworkDLL::Nerons::InputNeron*> inputs, std::vector<NerousNetworkDLL::Nerons::OutputNeron*> outputs, std::vector<NerousNetworkDLL::Nerons::Neron*> nerons) {
	nerons[0]->createSynaps(inputs[0], -0.5f);
	nerons[0]->createSynaps(inputs[1], -0.12f);
	nerons[1]->createSynaps(inputs[0], -1.2f);
	nerons[1]->createSynaps(inputs[1], 4.0f);
	nerons[2]->createSynaps(inputs[0], -6.0f);
	nerons[2]->createSynaps(inputs[1], 8.0f);
	nerons[3]->createSynaps(inputs[0], -2.0f);
	nerons[3]->createSynaps(inputs[1], -4.0f);
	outputs[0]->createSynaps(nerons[0], -2.0f);
	outputs[0]->createSynaps(nerons[1], -3.0f);
	outputs[0]->createSynaps(nerons[2], 0.5f);
	outputs[0]->createSynaps(nerons[3], 0.2f);
	outputs[1]->createSynaps(nerons[0], 14.2969f);
	outputs[1]->createSynaps(nerons[1], 3.8250f);
	outputs[1]->createSynaps(nerons[2], -7.2964f);
	outputs[1]->createSynaps(nerons[3], -4.2420f);

	outputs[0]->setActivationPack(new NerousNetworkDLL::SigmoidOutputActivation());
	outputs[1]->setActivationPack(new NerousNetworkDLL::SigmoidOutputActivation());

	for (auto& n : inputs) {
		n->setActivationPack(new NerousNetworkDLL::SigmoidActivation());
	}

	for (auto& n : nerons) {
		n->setActivationPack(new NerousNetworkDLL::SigmoidActivation());
	}
}

int main() {
	NerousNetworkDLL::NerousNetwork ns(2, 2, 4, new NerousNetworkDLL::Methods::MORMethod(), GenerateNS);
	float trainSet[][4][2]{ {{0,0},{0,0}}, {{0,1},{1,0}}, {{1,0},{1,0}}, {{1,1},{0,1}} };
	float *** train = new float**[4];
	for (int i = 0; i < 4; i++) {
		train[i] = new float*[2];
		for (int j = 0; j < 2; j++) {
			train[i][j] = new float[2]{ 0 };
			for (int k = 0; k < 2; k++)
				train[i][j][k] = trainSet[i][j][k];
		}
	}
	ns.train(train, 4);

	int test[][4]{ {0,0},{0,1},{1,0},{1,1} };
	for (int i = 0; i < 4; i++) {
		cout << "line #" << i + 1 << " " << boolalpha << (bool)test[i][0] << "^" << boolalpha << (bool)test[i][1]
			<< "=" << boolalpha << (bool)(int)roundf(ns.get({ (float)test[i][0], (float)test[i][1] }, 0));
		cout << " : " << boolalpha << (bool)test[i][0] << "&&" << (bool)test[i][1]
			<< "=" << (bool)(int)roundf(ns.get({ (float)test[i][0], (float)test[i][1] }, 1)) << endl;
	}

	system("pause");
	return 0;
}