package com.allion;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter Search text :");

            String searchTerm = myObj.nextLine();  // Read user input
            System.out.println("Username is: " + searchTerm);  // Output user input
            List<String> files = searchPdfInExternalStorage(new File("D:\\pdf\\"));
            files.forEach(f -> {
                try {
                    readPdf(f, searchTerm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        String jsonStr = "\n" +
                "\n" +
                "{\n" +
                "  \"type\":\"json\",\n" +
                "  \"sessionRef\": \"8CD8F1E7D54B47CDA74A6F295A3DD85F\",\n" +
                "  \"workflowRef\": \"5FC411570F5E455FB215D6C034389D33\",\n" +
                "  \"workflowId\": \"176\",\n" +
                "  \"workflowName\": \"Tipsløsning - Under utvikling\",\n" +
                "  \"workflowVersion\": \"9\",\n" +
                "  \"baseURL\": \"https://cngdemo.more.no:8443/cng-webapp-designer/\",\n" +
                "  \"metadata\": {\n" +
                "     \"date\": {\"value\": \"24.11.2022, kl. 07:01\", \"type\" : \"STRING\"}, \"registrationTime\": {\"value\": \"24.11.2022 07:01:44\", \"type\" : \"STRING\"}, \"role\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"exerciseId\": {\"value\": \"Test Ekstern\", \"type\" : \"STRING\"}, \"contactInfo\": {\"value\": \"Kan ikke kontaktes\", \"type\" : \"STRING\"}, \"criticality\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"municipality\": {\"value\": \"Arendal\", \"type\" : \"STRING\"}, \"lon\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"gradations\": {\"value\": \"  ,   ,    \", \"type\" : \"STRING\"}, \"tools\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"escapeCounts\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"content\": {\"value\": \"82\\nquestion\\ncsfs\", \"type\" : \"STRING\"}, \"distCode\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"trustworthiness\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"escapeLocation\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"riskAreas\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"theme\": {\"value\": \"1\", \"type\" : \"STRING\"}, \"escapeNr\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"linkYggdrasil\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"lat\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"toolDuration\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"observationType\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"species\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"escapeCount\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"entryPoint\": {\"value\": \"Eksternt tipsmottak\", \"type\" : \"STRING\"}, \"validity\": {\"value\": \"  \", \"type\" : \"STRING\"}, \"category\": {\"value\": \"Avfall fra fiskeri\", \"type\" : \"STRING\"}, \"incidentDate\": {\"value\": \"26.10.2022\", \"type\" : \"STRING\"}, \"region\": {\"value\": \"Agder\", \"type\" : \"STRING\"}, \"uniqueId\": {\"value\": \"TIPS-22-Rjtsj\", \"type\" : \"STRING\"}, \"escapeSize\": {\"value\": \"  \", \"type\" : \"STRING\"},\n" +
                "     \"createTimestamp\": {\"value\" :\"24.11.2022T11:35:32+0530\", \"type\" : \"STRING\"}  \t\n" +
                "  },\n" +
                "  \"continuationURL\": \"https://cngdemo.more.no:8443/cng-webapp-designer/workflowContinuation.action?referenceNumber=ABD3160C944E4A2DBA3269C0973001A3\",\n" +
                "  \"stateName\": \"CCM (New records)\",\n" +
                "  \"stateType\": \"PAUSE\",\n" +
                "  \"caseState\": \"Submitted\"\n" +
                "}";

        JSONObject json = JSONObject.fromObject(jsonStr);
        json.entrySet().forEach(jsonNode ->{
            System.out.println(jsonNode.toString());
        });
    }

    private static void readPdf(String filePath, String searchTerm) throws IOException {
        //Create PdfReader instance.
        PdfReader pdfReader = new PdfReader(filePath);

        //Get the number of pages in pdf.
        int pages = pdfReader.getNumberOfPages();

        //Iterate the pdf through pages.
        for(int i=1; i<=pages; i++) {
            //Extract the page content using PdfTextExtractor.
            String pageContent =
                    PdfTextExtractor.getTextFromPage(pdfReader, i);

            if(pageContent.toLowerCase().contains(searchTerm.toLowerCase())){
                //Print the page content on console.
                System.out.println("Content on Page " + i + ": " + pageContent);
            }

        }

        //Close the PdfReader.
        pdfReader.close();
    }

    public static ArrayList<String> searchPdfInExternalStorage(File folder) {
        ArrayList<String> myFiles = new ArrayList<String>();
        if (folder != null) {
            if (folder.listFiles() != null) {
                for (File file : folder.listFiles()) {
                    if (file.isFile()) {
                        //.pdf files
                        if (file.getName().contains(".pdf")) {
                            myFiles.add(file.getPath());
                            System.out.println("filePath-------" + file.getPath());
                        }
                    } else {
                        searchPdfInExternalStorage(file);
                    }
                }
            }
        }
        return myFiles;
    }
}
