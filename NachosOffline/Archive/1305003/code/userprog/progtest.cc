// progtest.cc 
//	Test routines for demonstrating that Nachos can load
//	a user program and execute it.  
//
//	Also, routines for testing the Console hardware device.
//
// Copyright (c) 1992-1993 The Regents of the University of California.
// All rights reserved.  See copyright.h for copyright notice and limitation 
// of liability and disclaimer of warranty provisions.


#include "copyright.h"
#include "system.h"
#include "console.h"
#include "addrspace.h"
#include "synch.h"
#include "MemoryManager.h"
#include "ProcessTable.h"
#include "SynchConsole.h"

#define MaxProcessNumber 64

//----------------------------------------------------------------------
// StartProcess
// 	Run a user program.  Open the executable, load it into
//	memory, and jump to it.
//----------------------------------------------------------------------
MemoryManager* MMU;
Lock* MMU_lock;
ProcessTable* processTable;
Lock* processTable_lock;
Lock* syscallLock;
SynchConsole* myconsole;

 Semaphore *readAvail;
 Semaphore *writeDone;
 Console *console;
 void ReadAvail(int arg) { readAvail->V(); }
 void WriteDone(int arg) { writeDone->V(); }


void
StartProcess(char *filename)
{
    OpenFile *executable = fileSystem->Open(filename);
    AddrSpace *space;
    MMU = new MemoryManager(NumPhysPages);
    MMU_lock = new Lock("Another MMU_lock");
    processTable = new ProcessTable(MaxProcessNumber);
    processTable_lock = new Lock("Another processTable_lock");
    syscallLock = new Lock("Syscall lock");


    console = new Console(NULL, NULL, ReadAvail, WriteDone, 0);
    myconsole = new SynchConsole();
    readAvail = new Semaphore("read avail", 0);
    writeDone = new Semaphore("write done", 0);


    if (executable == NULL) {
	printf("Unable to open file %s\n", filename);
	interrupt->Halt();
    }
    space = new AddrSpace(executable);    
    currentThread->space = space;
    int processId = processTable->Alloc((void*) currentThread);
    currentThread->processId = processId;

    delete executable;			// close file

    space->InitRegisters();		// set the initial register values
    space->RestoreState();		// load page table register

    machine->Run();			// jump to the user progam
    ASSERT(FALSE);			// machine->Run never returns;
					// the address space exits
					// by doing the syscall "exit"
}

// Data structures needed for the console test.  Threads making
// I/O requests wait on a Semaphore to delay until the I/O completes.



//----------------------------------------------------------------------
// ConsoleInterruptHandlers
// 	Wake up the thread that requested the I/O.
//----------------------------------------------------------------------



//----------------------------------------------------------------------
// ConsoleTest
// 	Test the console by echoing characters typed at the input onto
//	the output.  Stop when the user types a 'q'.
//----------------------------------------------------------------------

void 
ConsoleTest (char *in, char *out)
{
    char ch;

    console = new Console(in, out, ReadAvail, WriteDone, 0);
    readAvail = new Semaphore("read avail", 0);
    writeDone = new Semaphore("write done", 0);
    
    for (;;) {
	readAvail->P();		// wait for character to arrive
	ch = console->GetChar();
	console->PutChar(ch);	// echo it!
	writeDone->P() ;        // wait for write to finish
	if (ch == 'q') return;  // if q, quit
    }
}
