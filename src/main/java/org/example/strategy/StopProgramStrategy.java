package org.example.strategy;

public class StopProgramStrategy implements MenuStrategy{
    @Override
    public void execute(){
        System.exit(0);
    }
}
