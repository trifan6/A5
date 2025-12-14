package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Values.Value;

public class ValueExp implements Exp
{
    private final Value val;

    public ValueExp(Value val)
    {
        this.val = val;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> dict, MyIHeap<Integer, Value> heap)
    {
        return this.val;
    }

    @Override
    public String toString()
    {
        return this.val.toString();
    }

    @Override
    public Exp deepCopy()
    {
        return new ValueExp(val.deepCopy());
    }
}
