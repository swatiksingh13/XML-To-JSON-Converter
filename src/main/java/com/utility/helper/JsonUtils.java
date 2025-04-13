package com.utility.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.logging.Logger;
import org.xml.sax.InputSource;

public class JsonUtils {

    private static final Logger LOGGER = Logger.getLogger(JsonUtils.class.getName());

    public static String convertXmlToJsonWithMatchScore(String xmlInput) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(new InputSource(new StringReader(xmlInput)));
            xmlDoc.getDocumentElement().normalize();

            BigInteger totalScore = calculateTotalScore(xmlDoc);

            JSONObject xmlAsJson = XML.toJSONObject(xmlInput);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(xmlAsJson.toString());

            JsonNode resultBlock = root.path("Response").path("ResultBlock");
            if (!resultBlock.isMissingNode() && resultBlock.isObject()) {
                ((ObjectNode) resultBlock).putObject("MatchSummary")
                        .put("TotalMatchScore", totalScore.toString());
            } else {
                LOGGER.warning("'ResultBlock' tag not found in XML. MatchSummary not added.");
            }

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);

        } catch (Exception e) {
            throw new RuntimeException("XML processing failed: " + e.getMessage(), e);
        }
    }

    private static BigInteger calculateTotalScore(Document xmlDoc) {
        BigInteger total = BigInteger.ZERO;
        try {
            NodeList scoreNodes = xmlDoc.getElementsByTagName("Score");
            for (int i = 0; i < scoreNodes.getLength(); i++) {
                Node scoreNode = scoreNodes.item(i);
                if (scoreNode != null && scoreNode.getTextContent() != null) {
                    try {
                        BigInteger score = new BigInteger(scoreNode.getTextContent().trim());
                        total = total.add(score);
                    } catch (NumberFormatException e) {
                        LOGGER.warning("Invalid score format at node " + i + ": " + scoreNode.getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.warning("Error while calculating total score: " + e.getMessage());
        }
        return total;
    }
}
