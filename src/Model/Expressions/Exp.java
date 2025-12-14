package Model.Expressions;

import Model.ADT.MyIDictionary;
import Exceptions.MyException;
import Model.ADT.MyIHeap;
import Model.Values.Value;

public interface Exp
{
    //Value eval(MyIDictionary<String, Value> dict) throws MyException;

    Value eval(MyIDictionary<String, Value> dict, MyIHeap<Integer, Value> heap) throws MyException;

    Exp deepCopy();
}
