package View;

import Controller.Controller;
import Exceptions.MyException;

public class RunExample extends Command
{
    private Controller controller;

    public RunExample(String key, String desc, Controller controller)
    {
        super(key, desc);
        this.controller = controller;
    }

    @Override
    public void execute()
    {
        try
        {
            controller.allStep();
        }
        catch (MyException e)
        {
            System.out.println("Runtime error:"+e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error:"+e.getMessage());
        }
    }
}
