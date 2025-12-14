package Model;

import Model.ADT.*;
import Model.Statements.IStmt;
import Model.Values.Value;

import java.io.BufferedReader;

public class PrgState
{
    private final MyIStack<IStmt> exeStack;
    private final MyIDictionary<String, Value> symTable;
    private final MyIList<Value> out;
    private MyITable fileTable;
    private MyIHeap<Integer, Value> heap;

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out, MyITable fileTable, MyIHeap<Integer, Value> heap,IStmt originalProgram)
    {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.exeStack.push(originalProgram.deepCopy());
    }

    public MyIStack<IStmt> getExeStack()
    {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable()
    {
        return symTable;
    }

    public MyIList<Value> getOut()
    {
        return out;
    }

    public MyITable getFileTable()
    {
        return fileTable;
    }

    public MyIHeap<Integer, Value> getHeap()
    {
        return heap;
    }

    public void setFileTable(MyITable table)
    {
        this.fileTable = table;
    }

    public void setHeap(MyIHeap<Integer, Value> heap)
    {
        this.heap = heap;
    }

    @Override
    public String toString()
    {
        return "ExeStack: " + exeStack.toString() + "\n" + "SymTable: " + symTable.toString() + "\n" + "Out: " + out.toString() + "\n" + "FileTable: " + fileTable.toString() + "\n" + "Heap: " + heap.toString();
    }

}
