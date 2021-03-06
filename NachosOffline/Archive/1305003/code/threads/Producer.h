#ifndef PRODUCER_H
#define PRODUCER_H

#include "thread.h"
#include "copyright.h"
#include "system.h"
#include "Container.h"

class Producer{
	int id;
	Container* container;
public:

	Producer(int id, Container* container);
	void produce(int item);
	~Producer();

};

#endif //PRODUCER_H
