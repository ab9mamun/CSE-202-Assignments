#include "MemoryManager.h"
/* Create a manager to track the allocation of numPages of physical memory.
   You will create one by calling the constructor with NumPhysPages as
   the parameter.  All physical pages start as free, unallocated pages. */
MemoryManager::MemoryManager(int numPages){
	lock = new Lock("MemManagerLock");
	map = new BitMap(numPages);
}

/* Allocate a free page, returning its physical page number or -1
   if there are no free pages available. */
int MemoryManager::AllocPage(){
	lock->Acquire();
	int pageNum = map->Find();
	lock->Release();
	return pageNum;
}

/* Free the physical page and make it available for future allocation. */
void MemoryManager::FreePage(int physPageNum){
	lock->Acquire();
	map->Clear(physPageNum);
	lock->Release();

}

/* True if the physical page is allocated, false otherwise. */
bool MemoryManager::PageIsAllocated(int physPageNum){
	lock->Acquire();
	bool result = map->Test(physPageNum);
	lock->Release();
	return result;
}

MemoryManager::~MemoryManager(){

	delete lock;
	delete map;
}
