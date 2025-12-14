package View;

import Controller.Controller;
import Model.ADT.*;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;

public class Interpreter
{
    public static void main(String[] args)
    {

        IStmt ex1 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))
                )
        );

        PrgState prg1 = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyTable(),
                new MyHeap(),
                ex1
        );
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);


        IStmt ex2 = new CompStmt(
                new VarDeclStmt("a", new IntType()),
                new CompStmt(
                        new VarDeclStmt("b", new IntType()),
                        new CompStmt(
                                new AssignStmt("a",
                                        new ArithExp("+",
                                                new ValueExp(new IntValue(2)),
                                                new ArithExp("*",
                                                        new ValueExp(new IntValue(3)),
                                                        new ValueExp(new IntValue(5)))
                                        )
                                ),
                                new CompStmt(new AssignStmt("b",
                                        new ArithExp("+",
                                                new VarExp("a"),
                                                new ValueExp(new IntValue(1)))
                                ),
                                        new PrintStmt(new VarExp("b"))
                                )
                        )
                )
        );

        PrgState prg2 = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyTable(),
                new MyHeap(),
                ex2
        );
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);


        IStmt ex3 = new CompStmt(
                new VarDeclStmt("a", new BoolType()),
                new CompStmt(
                        new VarDeclStmt("v", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("a"),
                                                new AssignStmt("v", new ValueExp(new IntValue(2))),
                                                new AssignStmt("v", new ValueExp(new IntValue(3)))
                                        ),
                                        new PrintStmt(new VarExp("v"))
                                )
                        )
                )
        );

        PrgState prg3 = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyTable(),
                new MyHeap(),
                ex3
        );

        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

        IStmt ex4 =
                new CompStmt(
                        new VarDeclStmt("varf", new StringType()),
                        new CompStmt(
                                new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                                new CompStmt(
                                        new OpenRFileStmt(new VarExp("varf")),
                                        new CompStmt(
                                                new VarDeclStmt("varc", new IntType()),
                                                new CompStmt(
                                                        new ReadFileStmt(new VarExp("varf"), "varc"),
                                                        new CompStmt(
                                                                new PrintStmt(new VarExp("varc")),
                                                                new CompStmt(
                                                                        new ReadFileStmt(new VarExp("varf"), "varc"),
                                                                        new CompStmt(
                                                                                new PrintStmt(new VarExp("varc")),
                                                                                new CloseRFileStmt(new VarExp("varf"))
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                );

        PrgState prg4 = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyTable(),
                new MyHeap(),
                ex4
        );

        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);


        IStmt ex5 =
                new CompStmt(
                        new VarDeclStmt("a", new IntType()),
                        new CompStmt(
                                new VarDeclStmt("v", new IntType()),
                                new CompStmt(
                                        new AssignStmt("a", new ValueExp(new IntValue(2))),
                                                new IfStmt(
                                                        new RelExp(
                                                                new VarExp("a"),
                                                                new ValueExp(new IntValue(2)),
                                                                "=="),
                                                        new PrintStmt(new ValueExp(new StringValue("true"))),
                                                        new PrintStmt(new ValueExp(new StringValue("false")))



                                                )
                                )
                        )
                );

        PrgState prg5 = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyTable(),
                new MyHeap(),
                ex5
        );

        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

        IStmt ex6 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(
                                        new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(
                                                new ArithExp("+",
                                                        new ReadHeapExp(new VarExp("v")),
                                                        new ValueExp(new IntValue(5))
                                                )
                                        )
                                )
                        )
                )
        );


        PrgState prg6 = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyTable(),
                new MyHeap(),
                ex6
        );

        IRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        IStmt ex7 =
                new CompStmt(
                        new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(
                                new NewStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(
                                        new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStmt(
                                                new NewStmt("a", new VarExp("v")),
                                                new CompStmt(
                                                        new NewStmt("v", new ValueExp(new IntValue(30))),
                                                        new PrintStmt(
                                                                new ArithExp("+",
                                                                        new ReadHeapExp(
                                                                                new ReadHeapExp(
                                                                                        new VarExp("a")
                                                                                )
                                                                        ),
                                                                        new ValueExp(new IntValue(0))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                );

        PrgState prg7 = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyTable(),
                new MyHeap(),
                ex7
        );

        IRepository repo7 = new Repository(prg7, "log7_gc.txt");
        Controller ctr7 = new Controller(repo7);

        IStmt ex8 =
                new CompStmt(
                        new VarDeclStmt("v", new IntType()),
                        new CompStmt(
                                new AssignStmt("v", new ValueExp(new IntValue(4))),

                                new CompStmt(
                                        new WhileStmt(
                                                new RelExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new AssignStmt("v",
                                                                new ArithExp("-",
                                                                        new VarExp("v"),
                                                                        new ValueExp(new IntValue(1))))
                                                )
                                        ),
                                        new PrintStmt(new VarExp("v"))
                                )
                        )
                );

        PrgState prg8 = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyTable(),
                new MyHeap(),
                ex8
        );
        IRepository repo8 = new Repository(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));


        menu.show();
    }
}
