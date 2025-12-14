package Model.Expressions;

import Model.ADT.MyIDictionary;
import Exceptions.MyException;
import Model.ADT.MyIHeap;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.Value;
import Model.Values.IntValue;

public class RelExp implements Exp {
    private final Exp exp1;
    private final Exp exp2;
    private final String op;

    public RelExp(Exp exp1, Exp exp2, String op)
    {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> heap) throws MyException
    {
        Value v1 = exp1.eval(symTable, heap);
        if (!(v1.getType() instanceof IntType))
            throw new MyException("First operand is not an integer!");

        Value v2 = exp2.eval(symTable, heap);
        if (!(v2.getType() instanceof IntType))
            throw new MyException("Second operand is not an integer!");

        int n1 = ((IntValue) v1).getValue();
        int n2 = ((IntValue) v2).getValue();

        return switch (op)
        {
            case "<" -> new BoolValue(n1 < n2);
            case "<=" -> new BoolValue(n1 <= n2);
            case "==" -> new BoolValue(n1 == n2);
            case "!=" -> new BoolValue(n1 != n2);
            case ">" -> new BoolValue(n1 > n2);
            case ">=" -> new BoolValue(n1 >= n2);
            default -> throw new MyException("Invalid relational operator: " + op);
        };
    }

    @Override
    public String toString()
    {
        return exp1.toString() + " " + op + " " + exp2.toString();
    }

    @Override
    public Exp deepCopy()
    {
        return new RelExp(exp1.deepCopy(), exp2.deepCopy(), op);
    }
}