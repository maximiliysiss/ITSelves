#include "NerousNetwork.h"
#include <iostream>

using namespace std;


void GenerateNS(std::vector<NerousNetworkDLL::Nerons::InputNeron*> inputs, std::vector<NerousNetworkDLL::Nerons::OutputNeron*> outputs, std::vector<NerousNetworkDLL::Nerons::Neron*> nerons) {
	for (int i = 0; i < 6; i++)
		for (int j = 0; j < 6; j++)
			nerons[i]->createSynaps(inputs[j], 0.5);
	for (int i = 6; i < 11; i++)
		for (int j = 0; j < 6; j++)
			nerons[i]->createSynaps(nerons[j], 0.5);
	for (int i = 6; i < 11; i++)
		outputs[0]->createSynaps(nerons[i], 0.5);

	for (auto &n : outputs)
		n->setActivationPack(new NerousNetworkDLL::SigmoidOutputActivation());

	for (auto& n : inputs)
		n->setActivationPack(new NerousNetworkDLL::SigmoidActivation());

	for (auto& n : nerons)
		n->setActivationPack(new NerousNetworkDLL::SigmoidActivation());
}

int main() {
	NerousNetworkDLL::NerousNetwork ns(6, 1, 11, new NerousNetworkDLL::Methods::MORMethod(), GenerateNS);
	const int COUNT = 50;
	const int ARG = 6;
	float trainSet[][2][ARG]{
{0,20,6.8,5,5,1},
{1,14.2419,1.05405,5,5,1},
{2,39.9982,0.928571,5,5,1},
{3,14.7525,0.951219,5,5,1},
{4,19.469,0.75,5,5,1},
{5,39.3673,0.416667,5,5,1},
{6,9.87269,0,5,5,1},
{7,24.7332,1.14286,5,5,1},
{8,37.3793,4.5,5,5,1},
{9,5.70247,0.157895,5,5,1},
{10,29.6658,0.355556,5,5,1},
{11,34.1736,0.684211,5,5,1},
{12,2.53405,1.06061,5,5,1},
{13,33.9212,0.8125,5,5,1},
{14,29.9748,0.772727,5,5,1},
{15,0.58944,0.953488,5,5,1},
{16,37.2011,0.0384615,5,5,1},
{17,25.0771,0.176471,5,5,1},
{18,0.0048832,0.0967742,5,5,1},
{19,39.2757,1.3,5,5,1},
{20,19.8236,3,5,5,1},
{21,0.821343,2.69231,5,5,1},
{22,39.9998,7.75,5,5,1},
{23,14.5825,0.136364,5,5,1},
{24,2.98161,0.214286,5,5,1},
{25,39.3225,0.382979,5,5,1},
{26,9.72095,0.444444,5,5,1},
{27,6.33433,0.588235,5,5,1},
{28,37.2913,0.911111,5,5,1},
{29,5.57966,2.73333,5,5,1},
{30,10.6446,2.125,5,5,1},
{31,34.0486,1.17857,5,5,1},
{32,2.44878,12.3333,5,5,1},
{33,15.6103,1,5,5,1},
{34,29.8215,3.625,5,5,1},
{35,0.547679,7,5,5,1},
{36,20.8836,1.5,5,5,1},
{37,24.9062,1.4375,5,5,1},
{38,0.00955952,0.478261,5,5,1},
{39,26.0951,0.7,5,5,1},
{40,19.6472,8,5,5,1},
{41,0.872129,1.16667,5,5,1},
{42,30.8794,1,5,5,1},
{43,14.4128,0.375,5,5,1},
{44,3.07495,0.322581,5,5,1},
{45,34.9015,1.21429,5,5,1},
{46,9.57001,0.189189,5,5,1},
{47,6.46367,1.38889,5,5,1},
{48,37.8794,4,5,5,1},
{49,5.45798,1.90476,5,5,1}
	}
	;
	float *** train = new float**[COUNT];
	for (int i = 0; i < COUNT; i++) {
		train[i] = new float*[2];
		for (int j = 0; j < 1; j++) {
			train[i][j] = new float[ARG] { 0 };
			for (int k = 0; k < ARG; k++)
				train[i][j][k] = trainSet[i][j][k];
		}
		train[i][1] = new float(trainSet[i][1][0]);
	}
	ns.train(train, COUNT);

	/*int test[][4]{ {0,0},{0,1},{1,0},{1,1} };
	for (int i = 0; i < 4; i++) {
		cout << "line #" << i + 1 << " " << boolalpha << (bool)test[i][0] << "^" << boolalpha << (bool)test[i][1]
			<< "=" << boolalpha << ns.get({ (float)test[i][0], (float)test[i][1] }, 0) * 100 << endl;
	}*/

	system("pause");
	return 0;
}