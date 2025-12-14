package Repository;

import Exceptions.MyException;
import Model.PrgState;

public interface IRepository
{
    PrgState getCrtPrg();

    void logPrgStateExec() throws MyException;
}
