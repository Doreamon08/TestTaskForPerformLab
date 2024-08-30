package org.example.task3;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class task3 {

    private static void fillValues(JsonNode node, Map<Integer, String> valuesMap) {
        if (node.isArray()) {
            for (JsonNode childNode : node) {
                fillValues(childNode, valuesMap);
            }
        } else if (node.isObject()) {
            if (node.has("id") && node.has("value")) {
                int id = node.get("id").asInt();
                if (valuesMap.containsKey(id)) {
                    ((ObjectNode) node).put("value", valuesMap.get(id));
                }
            }
            if (node.has("values")) {
                fillValues(node.get("values"), valuesMap);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java task3 <path_to_tests_json> <path_to_values_json> <path_to_output_report_json>");
            return;
        }

        String testsFilePath = args[0];
        String valuesFilePath = args[1];
        String outputReportFilePath = args[2];

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, Object> valuesData = objectMapper.readValue(new File(valuesFilePath), new TypeReference<Map<String, Object>>() {});
            Map<Integer, String> valuesMap = new HashMap<>();
            for (Map<String, Object> valueEntry : (List<Map<String, Object>>) valuesData.get("values")) {
                int id = (Integer) valueEntry.get("id");
                String value = (String) valueEntry.get("value");
                valuesMap.put(id, value);
            }

            JsonNode testsData = objectMapper.readTree(new File(testsFilePath));

            fillValues(testsData.get("tests"), valuesMap);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputReportFilePath), testsData);

            System.out.println("Report generated successfully " + outputReportFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

