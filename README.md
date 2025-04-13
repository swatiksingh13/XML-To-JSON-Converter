# 🛠️ XML to JSON Converter - Production Ready Utility

A reliable and scalable command-line Java utility for converting XML files into structured JSON, enhanced with custom logic, robust validations, and logging. Designed with real-world edge cases and production-level standards in mind.

## 🔍 Use Case

This tool can be integrated into ETL pipelines, backend services, or automation tools where XML-to-JSON transformation is required—especially in domains like e-commerce, finance, or APIs dealing with XML payloads.

---

## 🚀 What Makes It Stand Out

✅ **End-to-end XML to JSON Conversion** using `org.json.XML`  
✅ **Custom Business Logic**: Computes and injects `MatchSummary.TotalMatchScore` field  
✅ **Robust Error Handling**:  
- Invalid/malformed XML  
- Empty files or unsupported encodings  
- Duplicate file processing  
- Invalid file extensions  
✅ **UTF-8 Encoding Support**: Handles multilingual content and special characters  
✅ **Command-line support for multiple files**  
✅ **Detailed Logging** using `java.util.logging`  
✅ **Well-structured and modular code** with Java 17+

---

## 🧠 Key Concepts & Skills Demonstrated

- Java 17, Maven
- JSON/XML Parsing with `org.json`, `jackson`
- Error handling & validations
- File I/O and encoding management
- Logging and user feedback
- Clean code and SOLID principles
- Build automation using Maven plugins (`shade`, `jar`)

---

## 🔧 Setup & Build

### 📦 Prerequisites
- Java 17+
- Maven

### 📥 Clone & Build
```bash
git clone git@github.com:swatiksingh13/XML-To-JSON-Converter.git
cd XML-To-JSON-Converter
mvn clean package

This generates: target/XMLToJSON-1.0-SNAPSHOT.jar

⚙️ How to Run
Single XML File: java -jar target/XMLToJSON-1.0-SNAPSHOT.jar /absolute/path/input1.xml
Multiple Files: java -jar target/XMLToJSON-1.0-SNAPSHOT.jar file1.xml file2.xml file3.xml

Output
Each file will be converted to: <original-filename>_output.json

✅ Example: If your input is `src/input/input1.xml`, the output will be generated as `src/input/input1_output.json`.


Happy Coding !! 😃
