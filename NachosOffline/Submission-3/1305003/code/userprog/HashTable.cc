#include "HashTable.h"
#include <stdio.h>

Node::Node(int key, void* item){
	this->key = key;
	this->item = item;
	next = 0;
}
int Node::getKey(){
	return key;
}
void Node::setNext(Node* next){
	this->next = next;
}
Node* Node::getNext(){
	return next;
}
void* Node::getItem(){
	return item;
}

Node::~Node(){
	item = 0;
}


int HashTable::hashcode(int key){
      //  cout<<"\""<<key<<"\""<<endl;
        return key % size;
}

HashTable::HashTable(int size){
		this->size = size;
		table = new Node*[size];
		for(int i=0; i<size; i++){
           table[i] = 0;
		}

}

HashTable::~HashTable(){
	for(int i=0; i<size; i++){
		Node* temp = table[i];
		Node* prev;

		while(temp){
			prev = temp;
			temp= temp->getNext();
			delete prev;
		}
	}
	delete[] table;
}


bool HashTable::put(int key, void* value){

		int index = hashcode(key);
		Node* temp = table[index];
		if(temp==0){
			table[index] = new Node(key, value);
			return true;
		}
		Node* prev;

		while(temp){
            if(temp->getKey()==key) return false;
            prev = temp;
            temp = temp->getNext();

		}

		prev->setNext(new Node(key, value));

		return true;
}

void* HashTable::get(int key) {
		int index = hashcode(key);
		Node*temp = table[index];

		while(temp){
			if(temp->getKey()==key) return temp->getItem();
			temp = temp->getNext();
		}
		return null;
}

bool HashTable::remove(int key){
	//printf("inside remove key: %d\n", key);
	int index = hashcode(key);
	//printf("inside remove index: %d\n", index);
			Node*temp = table[index];
		//	printf("inside remove temp: %d %d\n", temp, temp->getKey());
			if(!temp){
				return false;
			}
			if(temp->getKey()==key){
				table[index] = temp->getNext();
				//printf("inside remove table[%d]: %d\n", index, temp->getNext());
				delete temp;
				//printf("inside remove temp deleted\n");
				return true;  ///this was missing, that's why a core dump was occuring----------
			}
			Node* prev = temp;
			temp = temp->getNext();
			while(temp){
				if(temp->getKey()==key) {
					prev->setNext(temp->getNext());
					delete temp;
					return true;
				}
				prev = temp;
				temp = temp->getNext();
			}
			return false;
}

