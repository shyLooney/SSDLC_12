package Quests;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class QuestFour {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private ArrayList<Employee> array = new ArrayList<>();
    private File file;
    public QuestFour () throws ParserConfigurationException, IOException, SAXException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        file = new File("src/main/resources/QuestFour.xml");
        document = builder.parse(file);


    }

    public void showXML() {
        NodeList employeeElements = document.getDocumentElement().getElementsByTagName("employee");

        for (int i = 0; i < employeeElements.getLength(); i++) {
            Node employee = employeeElements.item(i);
            NamedNodeMap attributes = employee.getAttributes();

            array.add(new Employee(attributes.getNamedItem("name").getNodeValue(),
                    attributes.getNamedItem("profession").getNodeValue(),
                    Integer.valueOf(attributes.getNamedItem("age").getNodeValue())));
        }

        array.forEach(System.out::println);
    }

    public void writeXML(Employee emp) throws TransformerException {
        Element root = document.getDocumentElement();
        Element employees = document.createElement("employees");
        Element employee2 = document.createElement("employee");

        employee2.setAttribute("name", emp.getName());
        employee2.setAttribute("profession", emp.getProfession());
        employee2.setAttribute("age", emp.getAge().toString());

        employees.appendChild(employee2);
        root.appendChild(employees);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

    public void remove() {
        file.delete();
    }

    public class Employee {
        final private String name;
        final private String profession;
        final private Integer age;

        public Employee(String name, String profession, Integer age) {
            this.name = name;
            this.profession = profession;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getProfession() {
            return profession;
        }

        public Integer getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "{Name: " + name + "; Profession: " + profession + "; Age: " + age + "}";
        }
    }
}
