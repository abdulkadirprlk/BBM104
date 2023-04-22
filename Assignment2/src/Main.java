import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    /**
     * stores the smart lamps' name and smart lamps in the system
     */
    public static HashMap<String, SmartLamp> smartLamps = new HashMap<>();
    /**
     * stores the smart color lamps' name and smart color lamps in the system
     */
    public static HashMap<String, SmartColorLamp> smartColorLamps = new HashMap<>();
    /**
     * stores the smart cameras' name and smart cameras in the system
     */
    public static HashMap<String, SmartCamera> smartCameras = new HashMap<>();
    /**
     * stores the smart plugs' name and smart plugs in the system
     */
    public static HashMap<String, SmartPlug> smartPlugs = new HashMap<>();
    /**
     * stores all the devices' name and their switch times in the system
     */
    public static HashMap<String, LocalDateTime> allDevices = new HashMap<>();//name - switchTime
    /**
     * stores all the devices' name in the system
     */
    public static ArrayList<String> deviceNames = new ArrayList<>();//all names
    /**
     * global time object(keeps the initial time and present time of the system
     * in both LocalDateTime type and String type)
     */
    public static Time time = new Time();

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        processInput(inputFile, outputFile);
    }

    /**
     * This method takes the input, processes it and converts it to a useful output.
     * Always prints ZReport of the system at the end even if it is not stated.
     * @param inputFile input is taken from a txt file
     * @param outputFile output is written to a txt file
     */
    public static void processInput(String inputFile, String outputFile){
        SmartLampManager smartLampManager = new SmartLampManager();
        SmartColorLampManager smartColorLampManager = new SmartColorLampManager();
        SmartCameraManager smartCameraManager = new SmartCameraManager();
        SmartPlugManager smartPlugManager = new SmartPlugManager();
        TimeManager timeManager = new TimeManager();
        String lastLine = null;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while((line = reader.readLine()) != null){
                if(line.equals("")){
                    continue;
                }
                ArrayList<String> firstLineList = new ArrayList<>(Arrays.asList(line.split("\t")));
                if(firstLineList.get(0).equals("SetInitialTime")){
                    if(firstLineList.size() != 2){
                        writer.write("COMMAND: " + line + "\n");
                        writer.write("ERROR: First command must be set initial time! Program is going to terminate!\n");
                        writer.close();
                        System.exit(0);
                    }
                    try{
                        writer.write("COMMAND: " + line + "\n");
                        timeManager.setInitialTime(firstLineList.get(1));
                    }catch(DateTimeException e){
                        writer.write("ERROR: Format of the initial date is wrong! Program is going to terminate!\n");
                        writer.close();
                        System.exit(0);
                    }
                    writer.write("SUCCESS: Time has been set to " + time.getPresentTimeStr() + "!\n");
                    break;
                }
                else if(!firstLineList.get(0).equals("")){
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("ERROR: First command must be set initial time! Program is going to terminate!\n");
                    writer.close();
                    System.exit(0);
                }
            }
            while((line = reader.readLine()) != null){
                if(line.equals("")){
                    continue;
                }
                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(line.split("\t")));
                writer.write("COMMAND: " + line + "\n");
                //split function but returns an ArrayList
                if(lineList.get(0).equals("Add")){//ADD
                    if(lineList.get(1).equals("SmartLamp")){
                        if(lineList.size() == 3){//Add SmartLamp Lamp1
                            SmartLamp smartLamp = new SmartLamp(lineList.get(2));
                            writer.write(smartLampManager.validate(smartLamp));
                            smartLampManager.add(smartLamp);
                        }
                        else if(lineList.size() == 4){//Add SmartLamp Lamp1 On
                            SmartLamp smartLamp = new SmartLamp(lineList.get(2), lineList.get(3));
                            writer.write(smartLampManager.validate(smartLamp));
                            smartLampManager.add(smartLamp);
                        }
                        else if(lineList.size() == 6){//Add SmartLamp Lamp1 On 2000 50
                            SmartLamp smartLamp = new SmartLamp(lineList.get(2), lineList.get(3), Integer.parseInt(lineList.get(4)),
                                    Integer.parseInt(lineList.get(5)));
                            writer.write(smartLampManager.validate(smartLamp));
                            smartLampManager.add(smartLamp);
                        }else{
                            writer.write("Erroneous Command!\n");
                        }

                    }else if(lineList.get(1).equals("SmartColorLamp")){
                        if(lineList.size() == 3){//Add SmartColorLamp CLamp1
                            SmartColorLamp smartColorLamp = new SmartColorLamp(lineList.get(2));
                            writer.write(smartColorLampManager.validate(smartColorLamp));
                            smartColorLampManager.add(smartColorLamp);
                        }
                        else if(lineList.size() == 4){//Add SmartColorLamp CLamp1 On
                            SmartColorLamp smartColorLamp = new SmartColorLamp(lineList.get(2), lineList.get(3));
                            writer.write(smartColorLampManager.validate(smartColorLamp));
                            smartColorLampManager.add(smartColorLamp);
                        }
                        else if(lineList.size() == 6) {//Add SmartColorLamp CLamp1  On   2000/0x00GG00 50
                            try{//Add SmartColorLamp CLamp1 On 2000 50
                                SmartColorLamp smartColorLamp = new SmartColorLamp(lineList.get(2), lineList.get(3),
                                        Integer.parseInt(lineList.get(4)), Integer.parseInt(lineList.get(5)));
                                writer.write(smartColorLampManager.validate(smartColorLamp));
                                smartColorLampManager.add(smartColorLamp);
                            }catch(NumberFormatException e){//Add SmartColorLamp CLamp1  On   0x00GG00 50
                                try{
                                    String hexString = lineList.get(4).replaceAll("[^0-9A-Fa-f]", "");
                                    if (hexString.length() < 8) {
                                        hexString = String.format("%0" + (8 - hexString.length()) + "d%s", 0, hexString);
                                    }
                                    SmartColorLamp smartColorLamp = new SmartColorLamp(lineList.get(2), lineList.get(3),
                                            Long.parseLong(hexString,16), Integer.parseInt(lineList.get(5)));
                                    writer.write(smartColorLampManager.validate(smartColorLamp));
                                    smartColorLampManager.add(smartColorLamp);
                                }catch(NumberFormatException i){
                                    writer.write("ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n");
                                }
                            }
                        } else{
                            writer.write("Erroneous Command!\n");
                        }
                    }
                    else if(lineList.get(1).equals("SmartCamera")){
                        if(lineList.size() == 4){//Add	SmartCamera	Camera1	120
                            SmartCamera smartCamera = new SmartCamera(lineList.get(2), Double.parseDouble(lineList.get(3)));
                            writer.write(smartCameraManager.validate(smartCamera));
                            smartCameraManager.add(smartCamera);
                        }else if(lineList.size() == 5){//Add	SmartCamera	Camera1	120 On
                            SmartCamera smartCamera = new SmartCamera(lineList.get(2), Double.parseDouble(lineList.get(3)),
                                    lineList.get(4));
                            writer.write(smartCameraManager.validate(smartCamera));
                            smartCameraManager.add(smartCamera);
                        }else{
                            writer.write("Erroneous Command!\n");
                        }
                    }
                    else if(lineList.get(1).equals("SmartPlug")){
                        if(lineList.size() == 3){//Add	SmartPlug	Plug1
                            SmartPlug smartPlug = new SmartPlug(lineList.get(2));
                            writer.write(smartPlugManager.validate(smartPlug));
                            smartPlugManager.add(smartPlug);
                        }else if(lineList.size() == 4){//Add	SmartPlug	Plug2	On
                            SmartPlug smartPlug = new SmartPlug(lineList.get(2), lineList.get(3));
                            writer.write(smartPlugManager.validate(smartPlug));
                            smartPlugManager.add(smartPlug);
                        }
                        else if(lineList.size() == 5){//Add	SmartPlug	Plug2	On  5.7
                            SmartPlug smartPlug = new SmartPlug(lineList.get(2), lineList.get(3),
                                    Double.parseDouble(lineList.get(4)));
                            writer.write(smartPlugManager.validate(smartPlug));
                            smartPlugManager.add(smartPlug);
                        }else{
                            writer.write("Erroneous Command!\n");
                        }
                    }else if (!deviceNames.contains(lineList.get(1))){
                        writer.write("ERROR: Erroneous Command!\n");
                    }
                }
                else if(lineList.get(0).equals("Remove")){//REMOVE
                    if(smartLamps.containsKey(lineList.get(1))){
                        writer.write(smartLampManager.remove(lineList.get(1)));
                    }
                    else if(smartColorLamps.containsKey(lineList.get(1))){
                        writer.write(smartColorLampManager.remove(lineList.get(1)));
                    }
                    else if(smartCameras.containsKey(lineList.get(1))){
                        writer.write(smartCameraManager.remove(lineList.get(1)));
                    }
                    else if(smartPlugs.containsKey(lineList.get(1))){
                        writer.write(smartPlugManager.remove(lineList.get(1)));
                    }else{
                        writer.write("ERROR: There is not such a device!\n");
                    }
                }
                else if(lineList.get(0).equals("Switch")){//SWITCH
                    if(smartLamps.containsKey(lineList.get(1))){
                        writer.write(smartLampManager._switch(lineList.get(1), lineList.get(2)));
                    }
                    else if(smartColorLamps.containsKey(lineList.get(1))){
                        writer.write(smartColorLampManager._switch(lineList.get(1), lineList.get(2)));
                    }
                    else if(smartCameras.containsKey(lineList.get(1))){
                        writer.write(smartCameraManager._switch(lineList.get(1), lineList.get(2)));
                    }
                    else if(smartPlugs.containsKey(lineList.get(1))){
                        writer.write(smartPlugManager._switch(lineList.get(1), lineList.get(2)));
                    }else{
                        writer.write("ERROR: There is not such a device!\n");
                    }
                }
                else if (lineList.get(0).equals("SetKelvin")) {//SET KELVIN
                    if(smartLamps.containsKey(lineList.get(1))){
                        writer.write(smartLampManager.setKelvin(lineList.get(1), Integer.parseInt(lineList.get(2))));
                    }
                    else if(smartColorLamps.containsKey(lineList.get(1))){
                        writer.write(smartColorLampManager.setKelvin(lineList.get(1), Integer.parseInt(lineList.get(2))));
                    }
                    else if (!deviceNames.contains(lineList.get(1))){
                        writer.write("ERROR: There is not such a device!\n");
                    }else{
                        writer.write("ERROR: This device is not a smart lamp!\n");
                    }
                }
                else if (lineList.get(0).equals("SetBrightness")) {//SET BRIGHTNESS
                    if(smartLamps.containsKey(lineList.get(1))){
                        writer.write(smartLampManager.setBrightness(lineList.get(1), Integer.parseInt(lineList.get(2))));
                    }
                    else if(smartColorLamps.containsKey(lineList.get(1))){
                        writer.write(smartColorLampManager.setBrightness(lineList.get(1), Integer.parseInt(lineList.get(2))));
                    }
                    else if (!deviceNames.contains(lineList.get(1))){
                        writer.write("ERROR: There is not such a device!\n");
                    }
                    else{
                        writer.write("ERROR: This device is not a smart lamp!\n");
                    }
                }
                else if(lineList.get(0).equals("SetColorCode")){//SET COLOR CODE
                    if(smartColorLamps.containsKey(lineList.get(1))){
                        writer.write(smartColorLampManager.setColorCode(lineList.get(1), Integer.parseInt(lineList.get(2))));
                    }
                    else if (!deviceNames.contains(lineList.get(1))){
                        writer.write("ERROR: There is not such a device!\n");
                    }else{
                        writer.write("ERROR: This device is not a smart color lamp!\n");
                    }
                }
                else if(lineList.get(0).equals("ChangeName")){//CHANGE NAME
                    if(smartLamps.containsKey(lineList.get(1))){
                        writer.write(smartLampManager.changeName(lineList.get(1), lineList.get(2)));
                    }
                    else if(smartColorLamps.containsKey(lineList.get(1))){
                        writer.write(smartColorLampManager.changeName(lineList.get(1), lineList.get(2)));
                    }
                    else if(smartCameras.containsKey(lineList.get(1))){
                        writer.write(smartCameraManager.changeName(lineList.get(1), lineList.get(2)));
                    }
                    else if(smartPlugs.containsKey(lineList.get(1))){
                        writer.write(smartPlugManager.changeName(lineList.get(1), lineList.get(2)));
                    }else{
                        writer.write("ERROR: There is not such a device!\n");
                    }
                }
                else if(lineList.get(0).equals("SetWhite")){//SetWhite	CLamp2	2000	55
                    if(lineList.size() != 4){
                        writer.write("Erroneous Command!\n");
                    }else{
                        writer.write(smartColorLampManager.setWhite(lineList.get(1), Integer.parseInt(lineList.get(2)),
                                Integer.parseInt(lineList.get(3))));
                    }
                }
                else if(lineList.get(0).equals("SetColor")){//SetColor	CLamp1	0xFFFFFF	100
                    if(lineList.size() != 4){
                        writer.write("Erroneous Command!\n");
                    }else if(!Main.smartColorLamps.containsKey(lineList.get(1))){
                        writer.write("ERROR: This device is not a smart color lamp!\n");
                    }
                    else{
                        String hexString = lineList.get(2).replaceAll("[^0-9A-Fa-f]", "");
                        if (hexString.length() < 8) {
                            hexString = String.format("%0" + (8 - hexString.length()) + "d%s", 0, hexString);
                        }
                        writer.write(smartColorLampManager.setColor(lineList.get(1), Long.parseLong(hexString,16),
                                Integer.parseInt(lineList.get(3))));
                    }
                }
                else if(lineList.get(0).equals("PlugIn")){//PlugIn	Plug2	10
                    if(lineList.size() != 3){
                        writer.write("Erroneous Command!\n");
                    }else{
                        writer.write(smartPlugManager.plugIn(lineList.get(1), Integer.parseInt(lineList.get(2))));
                    }
                }
                else if(lineList.get(0).equals("PlugOut")){//PlugOut	Plug2
                    if(lineList.size() != 2){
                        writer.write("Erroneous Command!\n");
                    }else{
                        writer.write(smartPlugManager.plugOut(lineList.get(1)));
                    }
                }
                else if(lineList.get(0).equals("SkipMinutes")){//SkipMinutes    5
                    if(lineList.size() != 2){
                        writer.write("Erroneous Command!\n");
                    }else{
                        try{
                            writer.write(timeManager.skipMinutes(Integer.parseInt(lineList.get(1))));
                        }catch(Exception e){
                            writer.write("Erroneous command!\n");
                        }
                    }
                }
                else if(lineList.get(0).equals("SetTime")){
                    if(lineList.size() != 2){
                        writer.write("Erroneous Command!\n");
                    }else{
                        writer.write(timeManager.setTime(lineList.get(1)));
                    }
                }
                else if(lineList.get(0).equals("SetSwitchTime")){
                    if(lineList.size() != 3){
                        writer.write("Erroneous Command!\n");
                    }else{
                        writer.write(timeManager.setSwitchTime(lineList.get(1), lineList.get(2)));
                    }
                }
                else if(lineList.get(0).equals("Nop")){
                    if(lineList.size() != 1){
                        writer.write("Erroneous Command!\n");
                    }else{
                        writer.write(timeManager.nop());
                        smartLampManager.switchByTime();
                        smartColorLampManager.switchByTime();
                        smartCameraManager.switchByTime();
                        smartPlugManager.switchByTime();
                    }
                }
                else if(lineList.get(0).equals("ZReport")){
                    writer.write("Time is:\t" + time.getPresentTimeStr() + "\n");
                    writer.write(timeManager.zReport());
                }
                else{
                    writer.write("Erroneous Command!\n");
                }
                lastLine = line;//keeps the last line of the input file
            }
            if (!lastLine.equals("ZReport")) {
                writer.write("ZReport:\n");
                writer.write("Time is:\t" + time.getPresentTimeStr() + "\n");
                writer.write(timeManager.zReport());
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.close();
    }catch(IOException e) {
            throw new RuntimeException(e);
        }
}
}