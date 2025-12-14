package Controller;

import Exceptions.EmptyStackException;
import Exceptions.MyException;
import Model.ADT.MyIStack;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;


public class Controller
{
    private final IRepository repo;
    private boolean displayFlag = true;

    public Controller(IRepository repo)
    {
        this.repo = repo;
    }

    public void setDisplayFlag(boolean v)
    {
        this.displayFlag = v;
    }

    public PrgState oneStep(PrgState prgState) throws MyException
    {
        MyIStack<IStmt> stack = prgState.getExeStack();

        if(stack.isEmpty())
        {
            throw new EmptyStackException();
        }

        IStmt stmt = stack.pop();
        return stmt.execute(prgState);
    }

    public void allStep() throws MyException
    {
        PrgState prog = repo.getCrtPrg();
        repo.logPrgStateExec();

        if(displayFlag)
        {
            System.out.println("Initial state:\n" + prog.toString());
        }

        while(!prog.getExeStack().isEmpty())
        {
            oneStep(prog);
            repo.logPrgStateExec();

            List<Integer> symTableAddress = getAddressFromSymTable(prog.getSymTable().getAll().values());
            Map<Integer, Value> newHeap = safeGarbageCollector(symTableAddress, prog.getHeap().getContent());
            prog.getHeap().setContent(newHeap);

            repo.logPrgStateExec();

            if(displayFlag)
            {
                System.out.println("After step:\n" + prog.toString());
            }
        }

        if(displayFlag)
        {
            System.out.println("Final state:\n" + prog.toString());
        }
    }

    private List<Integer> getAddressFromSymTable(Collection<Value> symTableValues)
    {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue)v).getAddress())
                .collect(Collectors.toList());
    }

    private Set<Integer> getReachableAddresses(List<Integer> symTableAddresses, Map<Integer, Value> heap)
    {
        Set<Integer> reachable = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for(Integer a : symTableAddresses)
        {
            if(a != null && a!= 0 && heap.containsKey(a))
            {
                reachable.add(a);
                stack.push(a);
            }
        }

        while(!stack.isEmpty())
        {
            Integer a = stack.pop();
            Value val = heap.get(a);
            if(val instanceof RefValue)
            {
                int nestedAddr = ((RefValue)val).getAddress();
                if(nestedAddr != 0 && heap.containsKey(nestedAddr) && !reachable.contains(nestedAddr))
                {
                    reachable.add(nestedAddr);
                    stack.push(nestedAddr);
                }
            }
        }
        return reachable;
    }

    private Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddresses, Map<Integer, Value> heap)
    {
        Set<Integer> reachable = getReachableAddresses(symTableAddresses, heap);

        return heap.entrySet().stream()
                .filter(e -> reachable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}