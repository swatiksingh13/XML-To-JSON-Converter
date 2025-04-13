package com.utility.driver;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.utility.helper.FileUtils;
import com.utility.helper.JsonUtils;

public class XmlToJsonEnhanced {

    private static final Logger LOGGER = Logger.getLogger(XmlToJsonEnhanced.class.getName());
    private static final Set<String> processedFiles = new HashSet<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: No XML files provided as input arguments.");
            return;
        }

        for (String filePath : args) {
            File file = new File(filePath);

            if (!file.exists()) {
                System.err.println("File not found: " + filePath);
                continue;
            }

            if (!file.getName().endsWith(".xml")) {
                System.err.println("Invalid file extension (must be .xml): " + filePath);
                continue;
            }

            if (!file.canRead()) {
                System.err.println("Cannot read file (permissions issue): " + filePath);
                continue;
            }

            if (!processedFiles.add(file.getAbsolutePath())) {
                System.out.println("Skipping already processed file: " + filePath);
                continue;
            }

            try {
                String xmlInput = FileUtils.readFileContent(file);

                if (xmlInput.trim().isEmpty()) {
                    System.err.println("Skipping empty XML file: " + filePath);
                    continue;
                }

                String jsonOutput = JsonUtils.convertXmlToJsonWithMatchScore(xmlInput);
                String outputFileName = new File(file.getParent(), file.getName().replace(".xml", "_output.json")).getAbsolutePath();
                FileUtils.writeJsonToFile(jsonOutput, outputFileName);
                System.out.println("Successfully Converted " + file.getName() + " to " + outputFileName);

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to process file: " + filePath + ". Reason: " + e.getMessage(), e);
            }
        }
    }
}