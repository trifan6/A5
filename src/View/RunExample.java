package View;

import Controller.Controller;

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
        catch (Exception e)
        {
            System.out.println("Execution error:"+e.getMessage());
        }
    }
}
