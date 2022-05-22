import java.util.ArrayList;

public class InstructionController {
    ArrayList<String> instructionBuffer;

    public InstructionController() {
        instructionBuffer = new ArrayList<>();
    }

    public void printInstructionToConsole() {
        for (String instruction : instructionBuffer) {
            System.out.println(instruction);
        }
    }

    public void addInstruction(String instruction){
        instructionBuffer.add(instruction);
    }
}
