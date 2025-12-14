package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Values.RefValue;
import Model.Values.Value;

public class ReadHeapExp implements Exp
{
    private final Exp exp;

    public ReadHeapExp(Exp exp)
    {
        this.exp = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> dict, MyIHeap<Integer, Value> heap) throws MyException
    {
        Value val = exp.eval(dict, heap);
        if(!(val instanceof RefValue))
        {
            throw new MyException("rH: expression is not a RefValue");
        }

        int address = ((RefValue)val).getAddress();
        if(!heap.isDefined(address))
        {
            throw new MyException("rH: address is not defined");
        }
        return heap.get(address);
    }

    @Override
    public String toString()
    {
        return "rH(" + exp.toString() + ")";
    }

    @Override
    public Exp deepCopy()
    {
        return new ReadHeapExp(exp.deepCopy());
    }
}
