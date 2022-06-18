import RMI_Implementation.InstructionClient;
import RMI_Implementation.Job;
import RMI_Implementation.Stats;
import RMI_Implementation.Time;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class InstructionController {
    private static InstructionController instructionController;
    ArrayList<String> instructionBuffer;
    private final InstructionClient instructionClient;

    private InstructionController() throws Exception {
        instructionBuffer = new ArrayList<>();
        instructionClient = InstructionClient.getInstance();
    }

    public static InstructionController getInstance() throws Exception {// singleton
        if (instructionController == null) {
            instructionController = new InstructionController();
        }
        return instructionController;
    }

    public Stats getRobotValues() throws RemoteException {
        return instructionClient.getStats();
    }

    public void printInstructionToConsole() {
        for (String instruction : instructionBuffer) {
            System.out.println(instruction);
        }
    }

    public void addInstruction(String instruction) {
        try {
            instructionBuffer.add("instruction = " + instruction + " Instance = " + instructionClient.getStats().getRobotName() + " time = " + new Time().getString());
            Job job = new Job(instruction, 0);
            instructionClient.sendJob(job);
            Stats.printData(instructionClient.getStats());
            System.out.println("Instruction Sent Successfully!");
        } catch (Exception e) {
            System.out.println("Error Sending Instruction");
        }
    }

    public void addInstruction(String instruction, Time scheduleTime) {
        try {
            instructionBuffer.add(instruction + " Instance = " + instructionClient.getStats().getRobotName() + " time = " + scheduleTime.getString());
            Job job = new Job(instruction, 0, scheduleTime);
            instructionClient.sendJob(job);
            Stats.printData(instructionClient.getStats());
            System.out.println("Instruction Sent Successfully!");
        } catch (Exception e) {
            System.out.println("Error Sending Instruction");
        }
    }
}
