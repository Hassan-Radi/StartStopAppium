/*Copyright 2014 Hassan Radi
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.*/
package startstopappium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple code example on how to start/stop the Appium server programmatically
 * using Java. For a complete guide please refer to
 *
 * @author Hassan Radi
 */
public class StartStopAppium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Starting the Appium server assuming that Appium file are located in
         * the following directory: "C:/Appium". Change the next three variables
         * to match your Appium binary location and server configurations.
         */
        String AppiumNodeFilePath = "C:/Appium/node.exe";
        String AppiumJavaScriptServerFile = "C:/Appium/node_modules/appium/bin/Appium.js";
        String AppiumServerConfigurations = "--no-reset --local-timezone";
        executeCommand("\"" + AppiumNodeFilePath + "\" \"" + AppiumJavaScriptServerFile + "\" " + AppiumServerConfigurations);

        /**
         * Stopping the Appium server. Uncomment the following lines and make
         * sure to provide the Appium server port number to be able to
         * successfully stop the Appium server.
         */
//        String AppiumServerPortNumber = "4723";
//        executeCommand("cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in"
//                + " (`netstat -nao ^| findstr /R /C:\"" + AppiumServerPortNumber + "\"`) do (FOR /F \"usebackq\" %b in"
//                + " (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)");
    }

    /**
     * Executes a command line command using Java's Process class.
     *
     * @param command A String representation of the command line to execute.
     */
    private static void executeCommand(String command) {
        String s = null;

        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            System.out.println("exception happened: ");
            e.printStackTrace();
        }
    }
}
