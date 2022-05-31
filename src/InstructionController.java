import java.util.ArrayList;

public class InstructionController {
    ArrayList<String> instructionBuffer;
    private static InstructionController instructionController;
    private InstructionController() {
        instructionBuffer = new ArrayList<>();
    }

    public static InstructionController getInstance(){// singleton
        if(instructionController == null){
            instructionController = new InstructionController();
        }
        return instructionController;
    }

    public void printInstructionToConsole() {
        for (String instruction : instructionBuffer) {
            System.out.println(instruction);
        }
    }

    public void addInstruction(String instruction, RobotInstance robotInstance){
        try {
            instructionBuffer.add("instruction = " + instruction + " Instance = " + String.valueOf(robotInstance.getRobotName()) + " time = " +new Time().getString());
            robotInstance.SetJob(new Job(instruction, 0));
            System.out.println("Instruction Sent Successfully!");
        } catch (Exception e){
            System.out.println("Error Sending Instruction");
        }
    }
    public void addInstruction(String instruction, RobotInstance robotInstance, Time scheduleTime){
        try {
            instructionBuffer.add(instruction + "robot Instance = " + String.valueOf(robotInstance.getRobotName()) + " time = " + scheduleTime.getString());
            robotInstance.SetJob(new Job(instruction, 0, scheduleTime));
            System.out.println("Instruction Sent Successfully!");
        } catch (Exception e){
            System.out.println("Error Sending Instruction");
        }
    }
}
