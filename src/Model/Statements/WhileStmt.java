package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

public class WhileStmt implements IStmt
{
    private final Exp exp;
    private final IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt)
    {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        Value val = exp.eval(state.getSymTable(), state.getHeap());

        if(!val.getType().equals(new BoolType()))
        {
            throw new MyException("While condition is not boolean");
        }

        BoolValue boolVal = (BoolValue) val;
        MyIStack<IStmt> stack = state.getExeStack();

        if(!boolVal.getValue())
        {
            return null;
        }
        else
        {
            stack.push(this);
            stack.push(stmt);
            return null;
        }
    }

    @Override
    public String toString()
    {
        return "while("  + exp.toString() + ")" + stmt.toString();
    }

    @Override
    public IStmt deepCopy()
    {
        return new WhileStmt(exp.deepCopy(), stmt.deepCopy());
    }

}
