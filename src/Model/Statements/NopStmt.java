package Model.Statements;

import Model.PrgState;

public class NopStmt implements IStmt
{
    @Override
    public PrgState execute(PrgState state)
    {
        return state;
    }

    @Override
    public String toString()
    {
        return "nop";
    }

    @Override
    public IStmt deepCopy()
    {
        return new NopStmt();
    }
}
